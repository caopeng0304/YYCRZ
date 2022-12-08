package com.sinosoft.ie.booster.workflow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.model.FileModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.workflow.entity.*;
import com.sinosoft.ie.booster.workflow.enums.FlowHandleEventEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowModuleEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowTaskStatusEnum;
import com.sinosoft.ie.booster.workflow.mapper.FormOrderMapper;
import com.sinosoft.ie.booster.workflow.model.FlowHandleModel;
import com.sinosoft.ie.booster.workflow.model.formorder.*;
import com.sinosoft.ie.booster.workflow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单信息
 *
 * @author booster code generator
 * @since 2021-09-28
 */
@Service
public class FormOrderServiceImpl extends ServiceImpl<FormOrderMapper, FormOrderEntity> implements FormOrderService {

	@Autowired
	private FormOrderReceivableService orderReceivableService;
	@Autowired
	private FormOrderEntryService orderEntryService;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private FlowTaskNodeService flowTaskNodeService;
	@Autowired
	private FileManageUtil fileManageUtil;
	@Autowired
	private FlowEngineService flowEngineService;

	/**
	 * 前单
	 **/
	private static String PREV = "prev";
	/**
	 * 后单
	 **/
	private static String NEXT = "next";

	@Override
	public List<FormOrderEntity> getList(PaginationOrder paginationOrder) {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<FormOrderEntity> queryWrapper = new QueryWrapper<>();
		//关键字（订单编码、客户名称、业务人员）
		String keyWord = paginationOrder.getKeyword() != null ? paginationOrder.getKeyword() : null;
		if (!StrUtil.isEmpty(keyWord)) {
			queryWrapper.lambda().and(
					t -> t.like(FormOrderEntity::getOrderCode, keyWord)
							.or().like(FormOrderEntity::getCustomerName, keyWord)
							.or().like(FormOrderEntity::getSalesManName, keyWord)
			);
		}
		//起始日期-结束日期
		String startTime = paginationOrder.getStartTime() != null ? paginationOrder.getStartTime() : null;
		String endTime = paginationOrder.getEndTime() != null ? paginationOrder.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			Date startTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00");
			Date endTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59");
			queryWrapper.lambda().ge(FormOrderEntity::getOrderDate, startTimes).le(FormOrderEntity::getOrderDate, endTimes);
		}
		//订单状态
		String mark = paginationOrder.getEnabledFlag() != null ? paginationOrder.getEnabledFlag() : null;
		if (!StrUtil.isEmpty(mark)) {
			queryWrapper.lambda().eq(FormOrderEntity::getEnabledFlag, mark);
		}
		//排序
		if (StrUtil.isEmpty(paginationOrder.getSidx())) {
			queryWrapper.lambda().orderByDesc(FormOrderEntity::getCreateTime);
		} else {
			queryWrapper = "asc".equalsIgnoreCase(paginationOrder.getSort()) ? queryWrapper.orderByAsc(paginationOrder.getSidx()) : queryWrapper.orderByDesc(paginationOrder.getSidx());
		}
		Page<FormOrderEntity> page = new Page<>(paginationOrder.getCurrentPage(), paginationOrder.getPageSize());
		IPage<FormOrderEntity> orderEntityPage = this.page(page, queryWrapper);
		List<FormOrderEntity> data = orderEntityPage.getRecords();
		List<Long> id = data.stream().map(FormOrderEntity::getId).collect(Collectors.toList());
		if (data.size() > 0) {
			List<FlowTaskEntity> orderStaList = flowTaskService.getOrderStaList(id);
			for (FormOrderEntity entity : data) {
				FlowTaskEntity taskEntity = orderStaList.stream().filter(t -> t.getId().equals(entity.getId())).findFirst().orElse(null);
				if (taskEntity != null) {
					entity.setCurrentState(taskEntity.getStatus());
				}
			}
		}
		return paginationOrder.setData(data, page.getTotal());
	}

	@Override
	public List<FormOrderEntryEntity> getOrderEntryList(Long id) {
		QueryWrapper<FormOrderEntryEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOrderEntryEntity::getOrderId, id).orderByDesc(FormOrderEntryEntity::getSort);
		return orderEntryService.list(queryWrapper);
	}

	@Override
	public List<FormOrderReceivableEntity> getOrderReceivableList(Long id) {
		QueryWrapper<FormOrderReceivableEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOrderReceivableEntity::getOrderId, id).orderByDesc(FormOrderReceivableEntity::getSort);
		return orderReceivableService.list(queryWrapper);
	}

	@Override
	public FormOrderEntity getPrevOrNextInfo(Long id, String method) {
		QueryWrapper<FormOrderEntity> result = new QueryWrapper<>();
		FormOrderEntity orderEntity = getInfo(id);
		String orderBy = "desc";
		if (PREV.equals(method)) {
			result.lambda().gt(FormOrderEntity::getCreateTime, orderEntity.getCreateTime());
			orderBy = "";
		} else if (NEXT.equals(method)) {
			result.lambda().lt(FormOrderEntity::getCreateTime, orderEntity.getCreateTime());
		}
		result.lambda().notIn(FormOrderEntity::getId, orderEntity.getId());
		if (StrUtil.isNotEmpty(orderBy)) {
			result.lambda().orderByDesc(FormOrderEntity::getCreateTime);
		}
		List<FormOrderEntity> data = this.list(result);
		if (data.size() > 0) {
			return data.get(0);
		}
		return null;
	}

	@Override
	public OrderInfoVO getInfoVo(Long id, String method) throws DataException {
		OrderInfoVO infoModel = null;
		FormOrderEntity orderEntity = this.getPrevOrNextInfo(id, method);
		if (orderEntity != null) {
			List<FormOrderEntryEntity> orderEntryList = this.getOrderEntryList(orderEntity.getId());
			List<FormOrderReceivableEntity> orderReceivableList = this.getOrderReceivableList(orderEntity.getId());
			infoModel = JsonUtilEx.getJsonToBeanEx(orderEntity, OrderInfoVO.class);
			List<OrderInfoOrderEntryModel> orderEntryModels = JsonUtil.getJsonToList(orderEntryList, OrderInfoOrderEntryModel.class);
			infoModel.setGoodsList(orderEntryModels);
			List<OrderInfoOrderReceivableModel> orderReceivableModels = JsonUtil.getJsonToList(orderReceivableList, OrderInfoOrderReceivableModel.class);
			infoModel.setCollectionPlanList(orderReceivableModels);
		}
		return infoModel;
	}

	@Override
	public FormOrderEntity getInfo(Long id) {
		QueryWrapper<FormOrderEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FormOrderEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(FormOrderEntity entity) {
		QueryWrapper<FormOrderEntity> orderWrapper = new QueryWrapper<>();
		orderWrapper.lambda().eq(FormOrderEntity::getId, entity.getId());
		this.remove(orderWrapper);
		QueryWrapper<FormOrderEntryEntity> entryWrapper = new QueryWrapper<>();
		entryWrapper.lambda().eq(FormOrderEntryEntity::getOrderId, entity.getId());
		orderEntryService.remove(entryWrapper);
		QueryWrapper<FormOrderReceivableEntity> receivableWrapper = new QueryWrapper<>();
		receivableWrapper.lambda().eq(FormOrderReceivableEntity::getOrderId, entity.getId());
		orderReceivableService.remove(receivableWrapper);
		fileManageUtil.deleteFile(JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class));
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void create(FormOrderEntity entity, List<FormOrderEntryEntity> orderEntryList, List<FormOrderReceivableEntity> orderReceivableList, OrderForm orderForm) throws WorkFlowException {
		try {
			entity.setEnabledFlag("1");
			entity.setId(IdWorker.getId());
			for (int i = 0; i < orderEntryList.size(); i++) {
				orderEntryList.get(i).setOrderId(entity.getId());
				orderEntryList.get(i).setSort(i);
				orderEntryService.save(orderEntryList.get(i));
			}
			for (int i = 0; i < orderReceivableList.size(); i++) {
				orderReceivableList.get(i).setOrderId(entity.getId());
				orderReceivableList.get(i).setSort(i);
				orderReceivableService.save(orderReceivableList.get(i));
			}
			if (FlowStatusEnum.submit.getMessage().equals(orderForm.getStatus())) {
				String flowModuleMark = FlowModuleEnum.CRM_Order.getMessage();
				FlowEngineEntity flowEngineEntity = flowEngineService.getInfoByEnCode(flowModuleMark);
				entity.setCurrentState(FlowTaskStatusEnum.Handle.getCode());
				flowSubmit(entity.getId(), flowEngineEntity, orderForm.getFreeApprover(), entity);
			}
			fileManageUtil.createFile(JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class));
			this.save(entity);
		} catch (WorkFlowException e) {
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new WorkFlowException(e.getMessage());
		}
	}

	@Override
	public boolean update(Long id, FormOrderEntity entity, List<FormOrderEntryEntity> orderEntryList, List<FormOrderReceivableEntity> orderReceivableList, OrderForm orderForm) throws WorkFlowException {
		//删除原本的文件
		FormOrderEntity deEntity = getInfo(id);
		List<FileModel> list1 = JsonUtil.getJsonToList(deEntity.getFileJson(), FileModel.class);
		for (FileModel model : list1) {
			model.setFileType("delete");
		}
		fileManageUtil.updateFile(list1);
		entity.setId(id);
		QueryWrapper<FormOrderEntryEntity> entryWrapper = new QueryWrapper<>();
		entryWrapper.lambda().eq(FormOrderEntryEntity::getOrderId, entity.getId());
		orderEntryService.remove(entryWrapper);
		QueryWrapper<FormOrderReceivableEntity> receivableWrapper = new QueryWrapper<>();
		receivableWrapper.lambda().eq(FormOrderReceivableEntity::getOrderId, entity.getId());
		orderReceivableService.remove(receivableWrapper);
		for (int i = 0; i < orderEntryList.size(); i++) {
			orderEntryList.get(i).setOrderId(entity.getId());
			orderEntryList.get(i).setSort(i);
			orderEntryService.save(orderEntryList.get(i));
		}
		for (int i = 0; i < orderReceivableList.size(); i++) {
			orderReceivableList.get(i).setOrderId(entity.getId());
			orderReceivableList.get(i).setSort(i);
			orderReceivableService.save(orderReceivableList.get(i));
		}
		//添加新的文件
		List<FileModel> list2 = JsonUtil.getJsonToList(entity.getFileJson(), FileModel.class);
		for (FileModel model : list2) {
			model.setFileType("add");
		}
		fileManageUtil.updateFile(list2);
		if (FlowStatusEnum.submit.getMessage().equals(orderForm.getStatus())) {
			String flowModuleMark = FlowModuleEnum.CRM_Order.getMessage();
			FlowEngineEntity flowEngineEntity = flowEngineService.getInfoByEnCode(flowModuleMark);
			entity.setCurrentState(FlowTaskStatusEnum.Handle.getCode());
			flowSubmit(id, flowEngineEntity, orderForm.getFreeApprover(), entity);
		}
		return this.updateById(entity);
	}

	@Override
	public void flowSubmit(Long id, FlowEngineEntity flowEngineEntity, String freeApprover, FormOrderEntity orderEntity) throws WorkFlowException {
		BoosterUser userInfo = SecurityUtils.getUser();
		flowTaskService.submit(id, flowEngineEntity.getId(), userInfo.getUsername() + "的订单示例", 1, orderEntity.getOrderCode(), orderEntity, freeApprover);
	}

	@Override
	public void flowRevoke(FlowTaskEntity flowTaskEntity, FlowHandleModel flowHandleModel) throws WorkFlowException {
		FormOrderEntity orderEntity = getInfo(flowTaskEntity.getProcessId());
		orderEntity.setCurrentState(FlowTaskStatusEnum.Revoke.getCode());
		this.updateById(orderEntity);
		List<FlowTaskNodeEntity> flowTaskNodeEntityList = flowTaskNodeService.getList(flowTaskEntity.getId());
		FlowTaskNodeEntity flowTaskNodeEntity = flowTaskNodeEntityList.stream().filter(m -> "2".equals(String.valueOf(m.getSort()))).findFirst().get();
		if (flowTaskNodeEntity.getCompletion() > 0) {
			throw new WorkFlowException("当前流程被处理，无法撤回流程");
		} else {
			flowTaskService.revoke(flowTaskEntity, flowHandleModel);
		}
	}

	@Override
	public void flowHandleEvent(FlowHandleEventEnum flowHandleEvent, FlowTaskEntity flowTaskEntity) {
		FormOrderEntity orderEntity = this.getInfo(flowTaskEntity.getProcessId());
		if (orderEntity != null) {
			orderEntity.setCurrentState(flowTaskEntity.getStatus());
			this.updateById(orderEntity);
		}
	}

	@Override
	public void data(Long id, String data) {
		OrderForm orderForm = JsonUtil.getJsonToBean(data, OrderForm.class);
		FormOrderEntity entity = JsonUtil.getJsonToBean(orderForm, FormOrderEntity.class);
		List<FormOrderEntryEntity> orderEntryList = JsonUtil.getJsonToList(orderForm.getGoodsList(), FormOrderEntryEntity.class);
		List<FormOrderReceivableEntity> orderReceivableList = JsonUtil.getJsonToList(orderForm.getCollectionPlanList(), FormOrderReceivableEntity.class);
		entity.setId(id);
		QueryWrapper<FormOrderEntryEntity> entryWrapper = new QueryWrapper<>();
		entryWrapper.lambda().eq(FormOrderEntryEntity::getOrderId, entity.getId());
		orderEntryService.remove(entryWrapper);
		QueryWrapper<FormOrderReceivableEntity> receivableWrapper = new QueryWrapper<>();
		receivableWrapper.lambda().eq(FormOrderReceivableEntity::getOrderId, entity.getId());
		orderReceivableService.remove(receivableWrapper);
		for (int i = 0; i < orderEntryList.size(); i++) {
			orderEntryList.get(i).setOrderId(entity.getId());
			orderEntryList.get(i).setSort(i);
			orderEntryService.save(orderEntryList.get(i));
		}
		for (int i = 0; i < orderReceivableList.size(); i++) {
			orderReceivableList.get(i).setOrderId(entity.getId());
			orderReceivableList.get(i).setSort(i);
			orderReceivableService.save(orderReceivableList.get(i));
		}
		this.updateById(entity);
	}
}
