
package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.*;
import com.sinosoft.ie.booster.workflow.enums.FlowHandleEventEnum;
import com.sinosoft.ie.booster.workflow.model.FlowHandleModel;
import com.sinosoft.ie.booster.workflow.model.formorder.OrderForm;
import com.sinosoft.ie.booster.workflow.model.formorder.OrderInfoVO;
import com.sinosoft.ie.booster.workflow.model.formorder.PaginationOrder;

import java.util.List;

/**
 * 订单信息
 *
 * @author booster code generator
 * @since 2021-09-28
 */
public interface FormOrderService extends IService<FormOrderEntity> {

	/**
	 * 列表
	 *
	 * @param paginationOrder 分页
	 * @return
	 */
	List<FormOrderEntity> getList(PaginationOrder paginationOrder);

	/**
	 * 子列表（订单明细）
	 *
	 * @param id 主表Id
	 * @return
	 */
	List<FormOrderEntryEntity> getOrderEntryList(Long id);

	/**
	 * 子列表（订单收款）
	 *
	 * @param id 主表Id
	 * @return
	 */
	List<FormOrderReceivableEntity> getOrderReceivableList(Long id);

	/**
	 * 信息（前单、后单）
	 *
	 * @param id     主键值
	 * @param method 方法:prev、next
	 * @return
	 */
	FormOrderEntity getPrevOrNextInfo(Long id, String method);

	/**
	 * 信息（前单、后单）
	 *
	 * @param id     主键值
	 * @param method 方法:prev、next
	 * @return
	 * @throws DataException 异常
	 */
	OrderInfoVO getInfoVo(Long id, String method) throws DataException;

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormOrderEntity getInfo(Long id);

	/**
	 * 删除
	 *
	 * @param entity 订单信息
	 */
	void delete(FormOrderEntity entity);

	/**
	 * 新增
	 *
	 * @param entity              订单信息
	 * @param orderEntryList      订单明细
	 * @param orderReceivableList 订单收款
	 * @param orderForm           提交状态
	 * @throws WorkFlowException 异常
	 */
	void create(FormOrderEntity entity, List<FormOrderEntryEntity> orderEntryList, List<FormOrderReceivableEntity> orderReceivableList, OrderForm orderForm) throws WorkFlowException;

	/**
	 * 更新
	 *
	 * @param id                  主键值
	 * @param entity              订单信息
	 * @param orderEntryList      订单明细
	 * @param orderReceivableList 订单收款
	 * @param orderForm           提交状态
	 * @return
	 * @throws WorkFlowException 异常
	 */
	boolean update(Long id, FormOrderEntity entity, List<FormOrderEntryEntity> orderEntryList, List<FormOrderReceivableEntity> orderReceivableList, OrderForm orderForm) throws WorkFlowException;

	/**
	 * 提交审核
	 *
	 * @param id                 主键值
	 * @param flowEngineEntity   流程信息
	 * @param freeApprover 指定审批人
	 * @param orderEntity        订单实体
	 * @throws WorkFlowException 异常
	 */
	void flowSubmit(Long id, FlowEngineEntity flowEngineEntity, String freeApprover, FormOrderEntity orderEntity) throws WorkFlowException;

	/**
	 * 撤回审核
	 *
	 * @param flowTaskEntity  流程任务
	 * @param flowHandleModel 流程经办
	 * @throws WorkFlowException 异常
	 */
	void flowRevoke(FlowTaskEntity flowTaskEntity, FlowHandleModel flowHandleModel) throws WorkFlowException;

	/**
	 * 流程事件
	 *
	 * @param flowHandleEvent 经办事件
	 * @param flowTaskEntity  流程任务
	 */
	void flowHandleEvent(FlowHandleEventEnum flowHandleEvent, FlowTaskEntity flowTaskEntity);

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
