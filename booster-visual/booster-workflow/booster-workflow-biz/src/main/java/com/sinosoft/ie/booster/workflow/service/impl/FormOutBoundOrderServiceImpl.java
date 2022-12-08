package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormOutBoundOrderEntity;
import com.sinosoft.ie.booster.workflow.entity.FormOutBoundOrderEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormOutBoundOrderMapper;
import com.sinosoft.ie.booster.workflow.model.formoutboundorder.OutboundOrderForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormOutBoundOrderEntryService;
import com.sinosoft.ie.booster.workflow.service.FormOutBoundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 出库单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormOutBoundOrderServiceImpl extends ServiceImpl<FormOutBoundOrderMapper, FormOutBoundOrderEntity> implements FormOutBoundOrderService {

	@Autowired
	private FormOutBoundOrderEntryService outboundEntryService;
	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public List<FormOutBoundOrderEntryEntity> getOutboundEntryList(Long id) {
		QueryWrapper<FormOutBoundOrderEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOutBoundOrderEntryEntity::getOutBoundId, id).orderByDesc(FormOutBoundOrderEntryEntity::getSort);
		return outboundEntryService.list(queryWrapper);
	}

	@Override
	public FormOutBoundOrderEntity getInfo(Long id) {
		QueryWrapper<FormOutBoundOrderEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOutBoundOrderEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormOutBoundOrderEntity entity, List<FormOutBoundOrderEntryEntity> outboundEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < outboundEntryEntityList.size(); i++) {
				outboundEntryEntityList.get(i).setOutBoundId(entity.getId());
				outboundEntryEntityList.get(i).setSort(i);
				outboundEntryService.save(outboundEntryEntityList.get(i));
			}
			//创建
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormOutBoundOrderEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormOutBoundOrderEntryEntity::getOutBoundId, entity.getId());
			outboundEntryService.remove(queryWrapper);
			for (int i = 0; i < outboundEntryEntityList.size(); i++) {
				outboundEntryEntityList.get(i).setOutBoundId(entity.getId());
				outboundEntryEntityList.get(i).setSort(i);
				outboundEntryService.save(outboundEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormOutBoundOrderEntity entity, List<FormOutBoundOrderEntryEntity> outboundEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < outboundEntryEntityList.size(); i++) {
				outboundEntryEntityList.get(i).setOutBoundId(entity.getId());
				outboundEntryEntityList.get(i).setSort(i);
				outboundEntryService.save(outboundEntryEntityList.get(i));
			}
			//创建
			save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormOutBoundOrderEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormOutBoundOrderEntryEntity::getOutBoundId, entity.getId());
			outboundEntryService.remove(queryWrapper);
			for (int i = 0; i < outboundEntryEntityList.size(); i++) {
				outboundEntryEntityList.get(i).setOutBoundId(entity.getId());
				outboundEntryEntityList.get(i).setSort(i);
				outboundEntryService.save(outboundEntryEntityList.get(i));
			}
			//编辑
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		OutboundOrderForm outboundOrderForm = JsonUtil.getJsonToBean(data, OutboundOrderForm.class);
		FormOutBoundOrderEntity entity = JsonUtil.getJsonToBean(outboundOrderForm, FormOutBoundOrderEntity.class);
		List<FormOutBoundOrderEntryEntity> outboundEntryEntityList = JsonUtil.getJsonToList(outboundOrderForm.getEntryList(), FormOutBoundOrderEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormOutBoundOrderEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOutBoundOrderEntryEntity::getOutBoundId, entity.getId());
		outboundEntryService.remove(queryWrapper);
		for (int i = 0; i < outboundEntryEntityList.size(); i++) {
			outboundEntryEntityList.get(i).setOutBoundId(entity.getId());
			outboundEntryEntityList.get(i).setSort(i);
			outboundEntryService.save(outboundEntryEntityList.get(i));
		}
		//编辑
		this.updateById(entity);
	}
}
