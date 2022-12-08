package com.sinosoft.ie.booster.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.FormMonthlyReportEntity;
import com.sinosoft.ie.booster.workflow.mapper.FormMonthlyReportMapper;
import com.sinosoft.ie.booster.workflow.model.formmonthlyreport.MonthlyReportForm;
import com.sinosoft.ie.booster.workflow.service.FlowTaskService;
import com.sinosoft.ie.booster.workflow.service.FormMonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 月工作总结
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Service
public class FormMonthlyReportServiceImpl extends ServiceImpl<FormMonthlyReportMapper, FormMonthlyReportEntity> implements FormMonthlyReportService {

	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FileManageUtil fileManageUtil;

	@Override
	public FormMonthlyReportEntity getInfo(Long id) {
		QueryWrapper<FormMonthlyReportEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormMonthlyReportEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void save(Long id, FormMonthlyReportEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			this.updateById(entity);
			//更新附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.updateFile(data);
		}
		//流程信息
		flowTaskService.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo());
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, FormMonthlyReportEntity entity) throws WorkFlowException {
		//表单信息
		if (id == null) {
			entity.setId(IdWorker.getId());
			this.save(entity);
			//添加附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.createFile(data);
		} else {
			entity.setId(id);
			this.updateById(entity);
			//更新附件
			List<FileModel> data = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
			fileManageUtil.updateFile(data);
		}
		//流程信息
		flowTaskService.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
	}

	@Override
	public void data(Long id, String data) {
		MonthlyReportForm monthlyReportForm = JsonUtil.getJsonToBean(data, MonthlyReportForm.class);
		FormMonthlyReportEntity entity = JsonUtil.getJsonToBean(monthlyReportForm, FormMonthlyReportEntity.class);
		entity.setId(id);
		this.updateById(entity);
	}
}
