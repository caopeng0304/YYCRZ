package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.workflow.entity.FormArchivalBorrowEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormArchivalBorrowMapper;
import com.sinosoft.ie.booster.workflow.model.formarchivalborrow.ArchivalBorrowForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormArchivalBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 档案借阅申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormArchivalBorrowServiceImpl extends ServiceImpl<FormArchivalBorrowMapper, FormArchivalBorrowEntity> implements FormArchivalBorrowService {

	@Autowired
	private FlowTaskService flowTaskService;

	@Override
	public FormArchivalBorrowEntity getInfo(Long id) {
		QueryWrapper<FormArchivalBorrowEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormArchivalBorrowEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormArchivalBorrowEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			save(entity);
		} else {
			entity.setId(id);
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormArchivalBorrowEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			save(entity);
		} else {
			entity.setId(id);
			this.updateById(entity);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		ArchivalBorrowForm archivalBorrowForm = JsonUtil.getJsonToBean(data, ArchivalBorrowForm.class);
		FormArchivalBorrowEntity entity = JsonUtil.getJsonToBean(archivalBorrowForm, FormArchivalBorrowEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
