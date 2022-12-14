package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import com.sinosoft.ie.booster.common.core.util.treeutil.TreeDotUtils;
import com.sinosoft.ie.booster.visualdev.constant.enums.DicTypeEnum;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataField;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.model.dictionarydata.DictionaryDataModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormAllModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormEnum;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineVisibleEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowTaskOperatorEnum;
import com.sinosoft.ie.booster.workflow.model.flowengine.*;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode.ChildNode;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode.Properties;
import com.sinosoft.ie.booster.workflow.service.FlowEngineService;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.util.FormCloumnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ????????????
 *
 * @author booster???????????????
 * @since 2021???9???27??? ??????9:18
 */
@Api(tags = "????????????", value = "FlowEngine")
@RestController
@RequestMapping("/Engine/FlowEngine")
public class FlowEngineController {

	@Autowired
	private RemoteUserService usersApi;
	@Autowired
	private FlowEngineService flowEngineService;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private RemoteDictService dictionaryDataApi;

	/**
	 * ????????????????????????
	 *
	 * @return
	 */
	@ApiOperation("????????????????????????")
	@GetMapping
	public R<ListVO<FlowEngineListVO>> list(PaginationFlowEngine pagination) {
		List<FlowEngineEntity> list = flowEngineService.getList(pagination);
		List<FlowEngineListVO> result = JsonUtil.getJsonToList(list, FlowEngineListVO.class);
		if (result.size() > 0) {
			for (FlowEngineListVO model : result) {
				UserVO creatorUser = usersApi.getUserByUserName(model.getCreateBy()).getData();
				if (creatorUser != null) {
					model.setCreateBy(creatorUser.getUsername());
				} else {
					model.setCreateBy("");
				}
				UserVO lastModifyUser = usersApi.getUserByUserName(model.getUpdateBy()).getData();
				if (lastModifyUser != null) {
					model.setUpdateBy(lastModifyUser.getUsername());
				} else {
					model.setUpdateBy("");
				}
			}
		}
		ListVO<FlowEngineListVO> vo = new ListVO<>();
		vo.setList(result);
		return R.ok(vo);
	}

	/**
	 * ????????????????????????
	 *
	 * @return
	 */
	@ApiOperation("?????????????????????")
	@GetMapping("/Selector")
	public R<ListVO<FlowEngineListSelectVO>> listSelect(Integer type) {
		List<FlowEngineEntity> flowlist = flowEngineService.getList().stream().filter(t -> "1".equals(String.valueOf(t.getEnabledFlag()))).collect(Collectors.toList());
		if (type != null) {
			flowlist = flowlist.stream().filter(t -> type.equals(t.getFormType())).collect(Collectors.toList());
		}
		List<SysDictItemEntity> dictionList = dictionaryDataApi.getDictByType(DicTypeEnum.WORKFLOW_FLOW_ENGINE_TYPE.getType()).getData();
		List<DictionaryDataModel> data = new ArrayList<>();
		for (SysDictItemEntity dataEntity : dictionList) {
			List<FlowEngineEntity> flowData = flowlist.stream().filter(t -> String.valueOf(t.getCategory()).equals(dataEntity.getValue())).collect(Collectors.toList());
			if (flowData.size() > 0) {
				DictionaryDataModel model = new DictionaryDataModel();
				model.setParentId("0");
				model.setId(dataEntity.getValue());
				model.setFullName(dataEntity.getLabel());
				data.add(model);
				for (FlowEngineEntity engineEntity : flowData) {
					DictionaryDataModel childModel = new DictionaryDataModel();
					childModel.setParentId(dataEntity.getValue());
					childModel.setId(String.valueOf(engineEntity.getId()));
					childModel.setFullName(engineEntity.getFullName());
					data.add(childModel);
				}
			}
		}
		//??????
		List<SumTree<DictionaryDataModel>> sumTrees = TreeDotUtils.convertListToTreeDot(data);
		List<FlowEngineListSelectVO> result = JsonUtil.getJsonToList(sumTrees, FlowEngineListSelectVO.class);
		ListVO<FlowEngineListSelectVO> vo = new ListVO<>();
		vo.setList(result);
		return R.ok(vo);
	}

	/**
	 * ????????????
	 *
	 * @return
	 */
	@ApiOperation("??????????????????")
	@GetMapping("/{id}/FormDataFields")
	public R<ListVO<FormDataField>> getFormDataField(@PathVariable("id") Long id) throws WorkFlowException {
		FlowEngineEntity entity = flowEngineService.getInfo(id);
		//formTempJson
		FormDataModel formData = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
		List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
		List<FormAllModel> formAllModel = new ArrayList<>();
		FormCloumnUtil.recursionForm(list, formAllModel);
		//????????????
		List<FormAllModel> mast = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormDataField> formDataFieldList = new ArrayList<>();
		for (FormAllModel model : mast) {
			FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
			String vmodel = fieLdsModel.getVModel();
			String boosKey = fieLdsModel.getConfig().getBoosKey();
			if (StrUtil.isNotEmpty(vmodel) && !"relationForm".equals(boosKey) && !"relationFlow".equals(boosKey)) {
				FormDataField formDataField = new FormDataField();
				formDataField.setLabel(fieLdsModel.getConfig().getLabel());
				formDataField.setVModel(fieLdsModel.getVModel());
				formDataFieldList.add(formDataField);
			}
		}
		ListVO<FormDataField> listVO = new ListVO<>();
		listVO.setList(formDataFieldList);
		return R.ok(listVO);
	}

	/**
	 * ??????
	 *
	 * @return
	 */
	@ApiOperation("????????????")
	@GetMapping("/{id}/FieldDataSelect")
	public R<ListVO<FlowEngineSelectVO>> getFormData(@PathVariable("id") Long id) {
		List<FlowTaskEntity> flowTaskList = flowTaskService.getTaskList(id).stream().filter(t -> t.getStatus() == 2).collect(Collectors.toList());
		List<FlowEngineSelectVO> vo = new ArrayList<>();
		for (FlowTaskEntity taskEntity : flowTaskList) {
			FlowEngineSelectVO selectVO = JsonUtil.getJsonToBean(taskEntity, FlowEngineSelectVO.class);
			selectVO.setFullName(taskEntity.getFullName() + "/" + taskEntity.getEncode());
			vo.add(selectVO);
		}
		ListVO<FlowEngineSelectVO> listVO = new ListVO<>();
		listVO.setList(vo);
		return R.ok(listVO);
	}

	/**
	 * ??????
	 *
	 * @return
	 */
	@ApiOperation("??????????????????????????????")
	@GetMapping("/ListAll")
	public R<ListVO<FlowEngineListVO>> listAll() {
		List<FlowEngineEntity> data = flowEngineService.getFlowFormList();
		List<FlowEngineListVO> result = JsonUtil.getJsonToList(data, FlowEngineListVO.class);
		ListVO<FlowEngineListVO> vo = new ListVO<>();
		vo.setList(result);
		return R.ok(vo);
	}

	/**
	 * ????????????????????????
	 *
	 * @param id ?????????
	 * @return
	 */
	@ApiOperation("????????????????????????")
	@GetMapping("/{id}")
	public R<FlowEngineInfoVO> info(@PathVariable("id") Long id) throws DataException, WorkFlowException {
		FlowEngineEntity flowEntity = flowEngineService.getInfo(id);
		FlowEngineInfoVO vo = JsonUtil.getJsonToBeanEx(flowEntity, FlowEngineInfoVO.class);
		ChildNode childNode = JsonUtil.getJsonToBean(flowEntity.getFlowTemplateJson(), ChildNode.class);
		//??????????????????????????????????????????
		int freeApprover = 0;
		if (childNode.getChildNode() != null) {
			String type = childNode.getChildNode().getProperties().getAssigneeType();
			if (String.valueOf(FlowTaskOperatorEnum.FreeApprover.getCode()).equals(type)) {
				freeApprover = 1;
			}
		}
		vo.setFreeApprover(freeApprover);
		return R.ok(vo);
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@ApiOperation("??????????????????")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid FlowEngineCrForm flowEngineCrForm) {
		FlowEngineEntity flowEngineEntity = JsonUtil.getJsonToBean(flowEngineCrForm, FlowEngineEntity.class);
		if (flowEngineService.isExistByFullName(flowEngineEntity.getFullName(), flowEngineEntity.getId())) {
			return R.failed("????????????????????????");
		}
		if (flowEngineService.isExistByEnCode(flowEngineEntity.getEncode(), flowEngineEntity.getId())) {
			return R.failed("????????????????????????");
		}
		ChildNode childNode = JsonUtil.getJsonToBean(flowEngineEntity.getFlowTemplateJson(), ChildNode.class);
		Properties properties = childNode.getProperties();
		List<FlowEngineVisibleEntity> flowVisibleList = new ArrayList<>();
		//???????????????
		for (String user : properties.getInitiator()) {
			FlowEngineVisibleEntity entity = new FlowEngineVisibleEntity();
			entity.setOperatorId(user);
			entity.setOperatorType("user");
			flowVisibleList.add(entity);
		}
		//???????????????
		for (String position : properties.getInitiatePos()) {
			FlowEngineVisibleEntity entity = new FlowEngineVisibleEntity();
			entity.setOperatorId(position);
			entity.setOperatorType("position");
			flowVisibleList.add(entity);
		}
		flowEngineService.create(flowEngineEntity, flowVisibleList);
		return R.ok(null, "????????????");
	}

	/**
	 * ??????????????????
	 *
	 * @param id ?????????
	 * @return
	 */
	@ApiOperation("??????????????????")
	@PutMapping("/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid FlowEngineUpForm flowEngineUpForm) {
		FlowEngineEntity flowEngineEntity = JsonUtil.getJsonToBean(flowEngineUpForm, FlowEngineEntity.class);
		if (flowEngineService.isExistByFullName(flowEngineUpForm.getFullName(), id)) {
			return R.failed("????????????????????????");
		}
		if (flowEngineService.isExistByEnCode(flowEngineUpForm.getEnCode(), id)) {
			return R.failed("????????????????????????");
		}
		ChildNode childNode = JsonUtil.getJsonToBean(flowEngineEntity.getFlowTemplateJson(), ChildNode.class);
		Properties properties = childNode.getProperties();
		List<FlowEngineVisibleEntity> flowVisibleList = new ArrayList<>();
		for (String user : properties.getInitiator()) {
			FlowEngineVisibleEntity entity = new FlowEngineVisibleEntity();
			entity.setOperatorId(user);
			entity.setOperatorType("user");
			flowVisibleList.add(entity);
		}
		for (String position : properties.getInitiatePos()) {
			FlowEngineVisibleEntity entity = new FlowEngineVisibleEntity();
			entity.setOperatorId(position);
			entity.setOperatorType("position");
			flowVisibleList.add(entity);
		}
		boolean flag = flowEngineService.update(id, flowEngineEntity, flowVisibleList);
		if (!flag) {
			return R.failed("??????????????????????????????");
		}
		return R.ok(null, "????????????");
	}

	/**
	 * ??????????????????
	 *
	 * @param id ?????????
	 * @return
	 */
	@ApiOperation("??????????????????")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) throws WorkFlowException {
		FlowEngineEntity entity = flowEngineService.getInfo(id);
		List<FlowTaskEntity> taskNodeList = flowTaskService.getTaskList(entity.getId());
		if (taskNodeList.size() > 0) {
			return R.failed("??????????????????????????????");
		}
		if (entity != null) {
			flowEngineService.delete(entity);
			return R.ok(null, "????????????");
		}
		return R.failed("??????????????????????????????");
	}

	/**
	 * ??????????????????
	 *
	 * @param id ?????????
	 * @return
	 */
	@ApiOperation("??????????????????")
	@PostMapping("/{id}/Actions/Copy")
	public R<Boolean> copy(@PathVariable("id") Long id) throws WorkFlowException {
		FlowEngineEntity flowEngineEntity = flowEngineService.getInfo(id);
		if (flowEngineEntity != null) {
			ChildNode childNode = JsonUtil.getJsonToBean(flowEngineEntity.getFlowTemplateJson(), ChildNode.class);
			Properties properties = childNode.getProperties();
			List<FlowEngineVisibleEntity> flowVisibleList = new ArrayList<>();
			//???????????????
			if (properties.getInitiator() != null) {
				for (String user : properties.getInitiator()) {
					FlowEngineVisibleEntity entity = new FlowEngineVisibleEntity();
					entity.setOperatorId(user);
					entity.setOperatorType("user");
					flowVisibleList.add(entity);
				}
			}
			//???????????????
			if (properties.getInitiatePos() != null) {
				for (String position : properties.getInitiatePos()) {
					FlowEngineVisibleEntity entity = new FlowEngineVisibleEntity();
					entity.setOperatorId(position);
					entity.setOperatorType("position");
					flowVisibleList.add(entity);
				}
			}
			long time = System.currentTimeMillis();
			flowEngineEntity.setFullName(flowEngineEntity.getFullName() + "_" + time);
			flowEngineEntity.setEncode(flowEngineEntity.getEncode() + "_" + time);
			flowEngineEntity.setCreateTime(LocalDateTime.now());
			flowEngineEntity.setId(null);
			flowEngineService.create(flowEngineEntity, flowVisibleList);
			return R.ok(null, "????????????");
		}
		return R.failed("??????????????????????????????");
	}

	/**
	 * ??????????????????
	 *
	 * @param id ?????????
	 * @return
	 */
	@ApiOperation("????????????????????????")
	@PutMapping("/{id}/Actions/State")
	public R<Boolean> state(@PathVariable("id") Long id) throws WorkFlowException {
		FlowEngineEntity entity = flowEngineService.getInfo(id);
		if (entity != null) {
			entity.setEnabledFlag("1".equals(entity.getEnabledFlag()) ? "0" : "1");
			flowEngineService.update(id, entity);
			return R.ok(null, "??????????????????");
		}
		return R.failed("??????????????????????????????");
	}

	/**
	 * ??????????????????
	 *
	 * @param id ?????????
	 * @return
	 */
	@ApiOperation("??????????????????")
	@PostMapping("/Release/{id}")
	public R<Boolean> release(@PathVariable("id") Long id) throws WorkFlowException {
		FlowEngineEntity entity = flowEngineService.getInfo(id);
		if (entity != null) {
			entity.setEnabledFlag("1");
			flowEngineService.update(id, entity);
			return R.ok(null, "????????????");
		}
		return R.failed("??????????????????????????????");
	}

	/**
	 * ??????????????????
	 *
	 * @param id ?????????
	 * @return
	 */
	@ApiOperation("??????????????????")
	@PostMapping("/Stop/{id}")
	public R<Boolean> stop(@PathVariable("id") Long id) throws WorkFlowException {
		FlowEngineEntity entity = flowEngineService.getInfo(id);
		if (entity != null) {
			entity.setEnabledFlag("0");
			flowEngineService.update(id, entity);
			return R.ok(null, "????????????");
		}
		return R.failed("??????????????????????????????");
	}
}
