package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormAllModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormEnum;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTableModel;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskInfoVO;
import com.sinosoft.ie.booster.workflow.service.FlowDynamicService;
import com.sinosoft.ie.booster.workflow.service.FlowEngineService;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.util.FlowDataUtil;
import com.sinosoft.ie.booster.workflow.util.FormCloumnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 在线开发工作流
 *
 * @author booster开发平台组
 * @since 2021/3/15 9:19
 */
@Slf4j
@Service
public class FlowDynamicServiceImpl implements FlowDynamicService {

	@Autowired
	private RemoteBillRuleService billRuleApi;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FlowEngineService flowEngineService;
	@Autowired
	private FlowDataUtil flowDataUtil;

	@Override
	public FlowTaskInfoVO info(FlowTaskEntity entity) throws WorkFlowException, DataException, SQLException {
		FlowEngineEntity flowentity = flowEngineService.getInfo(entity.getFlowId());
		List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(flowentity.getRefTables(), FlowTableModel.class);
		FlowTaskInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, FlowTaskInfoVO.class);
		//formTempJson
		FormDataModel formData = JsonUtil.getJsonToBean(entity.getFlowForm(), FormDataModel.class);
		List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
		Map<String, Object> result = flowDataUtil.info(list, entity, tableModelList, false);
		vo.setData(JsonUtilEx.getObjectToString(result));
		return vo;
	}

	@Override
	public void save(Long id, Long flowId, String data) throws WorkFlowException, DataException, SQLException {
		FlowEngineEntity entity = flowEngineService.getInfo(flowId);
		BoosterUser info = SecurityUtils.getUser();
		String billNo = "单据规则不存在";
		String title = info.getUsername() + "的" + entity.getFullName();
		Long formId = IdWorker.getId();
		//formTempJson
		FormDataModel formData = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
		List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
		List<FormAllModel> formAllModel = new ArrayList<>();
		FormCloumnUtil.recursionForm(list, formAllModel);
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		//主表的单据数据
		Map<String, String> billData = new HashMap<>(16);
		boolean type = id != null;
		if (type) {
			formId = id;
		} else {
			FormAllModel formModel = mastForm.stream().filter(t -> "billRule".equals(t.getFormColumnModel().getFieLdsModel().getConfig().getBoosKey())).findFirst().orElse(null);
			if (formModel != null) {
				FieLdsModel fieLdsModel = formModel.getFormColumnModel().getFieLdsModel();
				String ruleKey = fieLdsModel.getConfig().getRule();
				billNo = billRuleApi.getBillNumber(ruleKey).getData();
				billData.put(fieLdsModel.getVModel().toLowerCase(), billNo);
			} else {
				throw new WorkFlowException("未找到单据控件");
			}
		}
		//tableJson
		List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(entity.getRefTables(), FlowTableModel.class);
		//表单值
		Map<String, Object> dataMap = JsonUtil.stringToMap(data);
		Map<String, Object> result = new HashMap<>(16);
		if (type) {
			result = flowDataUtil.update(dataMap, list, tableModelList, formId);
		} else {
			result = flowDataUtil.create(dataMap, list, tableModelList, formId, billData);
		}
		String resultData = JsonUtilEx.getObjectToString(result);
		if (tableModelList.size() > 0) {
			flowTaskService.save(id, flowId, formId, title, 1, billNo);
		} else {
			//流程信息
			flowTaskService.save(id, flowId, formId, title, 1, billNo, resultData);
		}
	}

	@Override
	public void submit(Long id, Long flowId, String data, String freeApprover) throws WorkFlowException, DataException, SQLException {
		FlowEngineEntity entity = flowEngineService.getInfo(flowId);
		BoosterUser info = SecurityUtils.getUser();
		String billNo = "单据规则不存在";
		String title = info.getUsername() + "的" + entity.getFullName();
		Long formId = IdWorker.getId();
		//formTempJson
		FormDataModel formData = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
		List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
		List<FormAllModel> formAllModel = new ArrayList<>();
		FormCloumnUtil.recursionForm(list, formAllModel);
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		//主表的单据数据
		Map<String, String> billData = new HashMap<>(16);
		boolean type = id != null;
		if (type) {
			formId = id;
		} else {
			FormAllModel formModel = mastForm.stream().filter(t -> "billRule".equals(t.getFormColumnModel().getFieLdsModel().getConfig().getBoosKey())).findFirst().orElse(null);
			if (formModel != null) {
				FieLdsModel fieLdsModel = formModel.getFormColumnModel().getFieLdsModel();
				String ruleKey = fieLdsModel.getConfig().getRule();
				billNo = billRuleApi.getBillNumber(ruleKey).getData();
				billData.put(fieLdsModel.getVModel().toLowerCase(), billNo);
			} else {
				throw new WorkFlowException("未找到单据控件");
			}
		}
		//tableJson
		List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(entity.getRefTables(), FlowTableModel.class);
		//表单值
		Map<String, Object> dataMap = JsonUtil.stringToMap(data);
		Map<String, Object> result = new HashMap<>(16);
		if (type) {
			result = flowDataUtil.update(dataMap, list, tableModelList, formId);
		} else {
			result = flowDataUtil.create(dataMap, list, tableModelList, formId, billData);
		}
		//流程信息
		flowTaskService.submit(id, flowId, formId, title, 1, billNo, result, freeApprover);
	}

	@Override
	public Map<String, Object> getData(Long flowId, Long id) throws WorkFlowException, SQLException, DataException {
		FlowTaskEntity entity = flowTaskService.getInfo(id);
		FlowEngineEntity flowentity = flowEngineService.getInfo(flowId);
		List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(flowentity.getRefTables(), FlowTableModel.class);
		//formTempJson
		FormDataModel formData = JsonUtil.getJsonToBean(entity.getFlowForm(), FormDataModel.class);
		List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
		return flowDataUtil.info(list, entity, tableModelList, true);
	}

}
