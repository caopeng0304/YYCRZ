package com.sinosoft.ie.booster.seata.demo.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 处理库存的Tcc
 */
@LocalTCC
public interface StorageTccAction {

	/**
	 * 减库存
	 *
	 * @param productId 商品 ID
	 * @param count    扣减数量
	 * @throws Exception 扣减失败时抛出异常
	 */
	@TwoPhaseBusinessAction(name = "reduceStorage", commitMethod = "commitStorage", rollbackMethod = "rollbackStorage")
	void reduceStorage(@BusinessActionContextParameter(paramName = "productId") Long productId,
					   @BusinessActionContextParameter(paramName = "count") Integer count) throws Exception;

	/**
	 * 二阶段提交方法
	 *
	 * 确认方法，命名必须与commitMethod = "commitStorage"保持一致
	 * context 可以传递 try 方法的参数
	 * @param context   上下文
	 * @return
	 */
	boolean commitStorage(BusinessActionContext context);

	/**
	 * 二阶段回滚方法
	 * @param context    上下文
	 * @return
	 */
	boolean rollbackStorage(BusinessActionContext context);


}
