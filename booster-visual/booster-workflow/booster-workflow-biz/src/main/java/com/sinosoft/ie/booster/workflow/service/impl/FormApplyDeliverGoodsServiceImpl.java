package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormApplyDeliverGoodsEntity;
import com.sinosoft.ie.booster.workflow.entity.FormApplyDeliverGoodsEntryEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormApplyDeliverGoodsMapper;
import com.sinosoft.ie.booster.workflow.model.formapplydelivergoods.ApplyDeliverGoodsForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormApplyDeliverGoodsEntryService;
import com.sinosoft.ie.booster.workflow.service.FormApplyDeliverGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 发货申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormApplyDeliverGoodsServiceImpl extends ServiceImpl<FormApplyDeliverGoodsMapper, FormApplyDeliverGoodsEntity> implements FormApplyDeliverGoodsService {

	@Autowired
	private FormApplyDeliverGoodsEntryService applyDeliverGoodsEntryService;
	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public List<FormApplyDeliverGoodsEntryEntity> getDeliverEntryList(Long id) {
		QueryWrapper<FormApplyDeliverGoodsEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormApplyDeliverGoodsEntryEntity::getInvoiceId, id).orderByDesc(FormApplyDeliverGoodsEntryEntity::getSort);
		return applyDeliverGoodsEntryService.list(queryWrapper);
	}

	@Override
	public FormApplyDeliverGoodsEntity getInfo(Long id) {
		QueryWrapper<FormApplyDeliverGoodsEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormApplyDeliverGoodsEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormApplyDeliverGoodsEntity entity, List<FormApplyDeliverGoodsEntryEntity> applyDeliverGoodsEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < applyDeliverGoodsEntryEntityList.size(); i++) {
				applyDeliverGoodsEntryEntityList.get(i).setInvoiceId(entity.getId());
				applyDeliverGoodsEntryEntityList.get(i).setSort(i);
				applyDeliverGoodsEntryService.save(applyDeliverGoodsEntryEntityList.get(i));
			}
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormApplyDeliverGoodsEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormApplyDeliverGoodsEntryEntity::getInvoiceId, entity.getId());
			applyDeliverGoodsEntryService.remove(queryWrapper);
			for (int i = 0; i < applyDeliverGoodsEntryEntityList.size(); i++) {
				applyDeliverGoodsEntryEntityList.get(i).setInvoiceId(entity.getId());
				applyDeliverGoodsEntryEntityList.get(i).setSort(i);
				applyDeliverGoodsEntryService.save(applyDeliverGoodsEntryEntityList.get(i));
			}
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormApplyDeliverGoodsEntity entity, List<FormApplyDeliverGoodsEntryEntity> applyDeliverGoodsEntryEntityList) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			for (int i = 0; i < applyDeliverGoodsEntryEntityList.size(); i++) {
				applyDeliverGoodsEntryEntityList.get(i).setInvoiceId(entity.getId());
				applyDeliverGoodsEntryEntityList.get(i).setSort(i);
				applyDeliverGoodsEntryService.save(applyDeliverGoodsEntryEntityList.get(i));
			}
			this.save(entity);
		} else {
			entity.setId(id);
			QueryWrapper<FormApplyDeliverGoodsEntryEntity> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(FormApplyDeliverGoodsEntryEntity::getInvoiceId, entity.getId());
			applyDeliverGoodsEntryService.remove(queryWrapper);
			for (int i = 0; i < applyDeliverGoodsEntryEntityList.size(); i++) {
				applyDeliverGoodsEntryEntityList.get(i).setInvoiceId(entity.getId());
				applyDeliverGoodsEntryEntityList.get(i).setSort(i);
				applyDeliverGoodsEntryService.save(applyDeliverGoodsEntryEntityList.get(i));
			}
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		ApplyDeliverGoodsForm applyDeliverGoodsForm = JsonUtil.getJsonToBean(data, ApplyDeliverGoodsForm.class);
		FormApplyDeliverGoodsEntity entity = JsonUtil.getJsonToBean(applyDeliverGoodsForm, FormApplyDeliverGoodsEntity.class);
		List<FormApplyDeliverGoodsEntryEntity> applyDeliverGoodsEntryEntityList = JsonUtil.getJsonToList(applyDeliverGoodsForm.getEntryList(), FormApplyDeliverGoodsEntryEntity.class);
		entity.setId(id);
		QueryWrapper<FormApplyDeliverGoodsEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormApplyDeliverGoodsEntryEntity::getInvoiceId, entity.getId());
		applyDeliverGoodsEntryService.remove(queryWrapper);
		for (int i = 0; i < applyDeliverGoodsEntryEntityList.size(); i++) {
			applyDeliverGoodsEntryEntityList.get(i).setInvoiceId(entity.getId());
			applyDeliverGoodsEntryEntityList.get(i).setSort(i);
			applyDeliverGoodsEntryService.save(applyDeliverGoodsEntryEntityList.get(i));
		}
		this.updateById(entity);
	}
}
