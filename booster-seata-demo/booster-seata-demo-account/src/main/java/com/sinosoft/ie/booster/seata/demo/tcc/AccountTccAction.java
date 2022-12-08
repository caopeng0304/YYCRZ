package com.sinosoft.ie.booster.seata.demo.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * 减少余额
 */
@LocalTCC
public interface AccountTccAction {

	/**
	 * 减余额
	 *
	 * 定义两段提交
	 * name = reduceBalance 为一阶段try方法
	 * commitMethod = commitTcc 为二阶段确认方法
	 * rollbackMethod = cancel 为二阶段取消方法
	 * BusinessActionContextParameter注解 可传递参数到二阶段方法
	 *
	 * @param userId 用户id
	 * @param money  扣减金额
	 * @throws Exception 失败时抛出异常
	 */
	@TwoPhaseBusinessAction(name = "reduceBalance", commitMethod = "commitBalance", rollbackMethod = "rollbackBalance")
	void reduceBalance(BusinessActionContext context, @BusinessActionContextParameter(paramName = "userId") Long userId,
					   @BusinessActionContextParameter(paramName = "money") BigDecimal money) throws Exception;


	/**
	 * 确认方法、可以另命名，但要保证与commitMethod一致
	 * context 可以传递 try 方法的参数
	 * @param context 上下文
	 * @return
	 */
	boolean  commitBalance(BusinessActionContext context);

	/**
	 * 二阶段取消方法
	 * @param context 上下文
	 * @return
	 */
	boolean  rollbackBalance(BusinessActionContext context);
}
