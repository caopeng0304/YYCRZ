package com.sinosoft.ie.booster.seata.demo.tcc.impl;

import com.sinosoft.ie.booster.seata.demo.dao.AccountMapper;
import com.sinosoft.ie.booster.seata.demo.dao.TransactionMapper;
import com.sinosoft.ie.booster.seata.demo.po.Account;
import com.sinosoft.ie.booster.seata.demo.po.Transaction;
import com.sinosoft.ie.booster.seata.demo.tcc.AccountTccAction;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 目前没有加事务表,如果异常会出现: 空回滚, 幂等, 悬挂 等问题
 */
@Service
public class AccountTccActionImpl implements AccountTccAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountTccActionImpl.class);

	private static final Short INIT = 1;
	private static final Short SUBMITTED = 2;
	private static final Short ROLLBACKED = 3;

	@Resource
	private AccountMapper accountMapper;
	@Resource
	private TransactionMapper transactionMapper;

	@Override
	public void reduceBalance(BusinessActionContext context, Long userId, BigDecimal money) throws Exception {
		LOGGER.info("当前的 XID: {}", RootContext.getXID());

		//模拟超时异常，全局事务回滚
//        try {
//            Thread.sleep(30*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

		// 避免空回滚, 插入事务数据
		int res = insertTransaction(RootContext.getXID(), context.getBranchId(), userId, money);

		if (res < 0) {
			throw new RuntimeException("Confirm阶段已经执行或正在执行");
		}

		// 查询用户信息
		Account account = accountMapper.selectById(userId);

		BigDecimal residue = account.getResidue();
		BigDecimal used = account.getUsed();

		// 检查余额是否满足当前冻结金额
		if (residue.subtract(used).compareTo(money) < 0) {
			throw new RuntimeException("剩余金额不足");
		}

		// 冻结金额
		account.setUsed(account.getUsed().add(money));
		res = accountMapper.updateById(account);

		if (res > 0) {
			LOGGER.info("冻结用户ID {} 金额成功", +userId);
		} else {
			LOGGER.info("冻结金额失败， res {} ", +res);
		}
	}

	/**
	 * TCC服务（confirm）方法
	 *
	 * @param context 上下文
	 * @return
	 */
	@Override
	public boolean commitBalance(BusinessActionContext context) {

		// 判断当前上下文 XID 是否为空
		if (StringUtils.isEmpty(context.getXid()) || null == context.getXid()) {
			return false;
		}

		LOGGER.info("BranchId:-------" + context.getBranchId());

		// 判断事务表数据是否插入
		Transaction transaction = transactionMapper.selectByPrimaryKey(context.getXid(), String.valueOf(context.getBranchId()));

		if (null == transaction) {
			LOGGER.info("这是一个空提交 xid: " + context.getBranchId());
			return true;
		} else {
			// 判断事务状态是否是初始化 如果是已回滚的也当停止
			if (SUBMITTED.equals(transaction.getState())) {
				return true;
			}
			if (ROLLBACKED.equals(transaction.getState())) {
				LOGGER.info("一个已回滚的事务，不能重新提交 xid: " + context.getBranchId());
				return true;
			}
		}

		// 获取数据
		Long userId = Long.valueOf((Integer) context.getActionContext("userId"));
		BigDecimal money = BigDecimal.valueOf((Integer) context.getActionContext("money"));

		// 执行减少余额
		Account account = accountMapper.selectById(userId);
		account.setResidue(account.getResidue().subtract(money));
		account.setUsed(account.getUsed().subtract(money));
		int res = accountMapper.updateById(account);

		if (res > 0) {
			LOGGER.info("减少用户ID {} 金额成功, XID=" + context.getXid(), userId);
			LOGGER.info("Confirm阶段, commitBalance --> xid = " + context.getXid() + ", commitBalance提交成功");

			// 更新事务中的state
			transaction.setState(Short.valueOf(SUBMITTED));
			transaction.setUpdateTime(new Date());
			transactionMapper.updateByPrimaryKeySelective(transaction);
			LOGGER.info("Confirm阶段, commitBalance --> transaction 更新状态成功");
			return true;

		} else {
			LOGGER.info("减少用户ID {} 金额失败, XID=" + context.getXid() + "res {}", userId, res);
			return false;
		}
	}

	/**
	 * TCC服务（rollback）方法
	 *
	 * @param context 上下文
	 * @return
	 */
	@Override
	public boolean rollbackBalance(BusinessActionContext context) {

		LOGGER.info("BranchId:-------" + context.getBranchId());

		// 判断事务表数据是否插入
		Transaction transaction = transactionMapper.selectByPrimaryKey(context.getXid(), String.valueOf(context.getBranchId()));

		// 进行数据库回滚处理
		Long userId = Long.valueOf((Integer) context.getActionContext("userId"));
		BigDecimal money = BigDecimal.valueOf((Integer) context.getActionContext("money"));

		if (null != transaction) {

			// 再次判断state状态值
			if (SUBMITTED.equals(transaction.getState())) {
				LOGGER.info("事务已提交无法回滚!");
				return true;
			}

			if (INIT.equals(transaction.getState())) {
				LOGGER.info("Cancel阶段, cancelTcc --> xid = " + context.getXid() + ", cancelTcc提交失败");

				// 恢复减少余额
				Account account = accountMapper.selectById(userId);
				account.setResidue(account.getResidue().add(money));

				int res = accountMapper.updateById(account);

				if (res > 0) {
					LOGGER.info("回滚成功");
					// 更新状态
					transaction.setState(ROLLBACKED);
					transaction.setUpdateTime(new Date());
					transactionMapper.updateByPrimaryKeySelective(transaction);
					return true;

				} else {
					LOGGER.info("回滚失败----->" + context.getXid() + "  ------>res {}", userId, res);
					return false;
				}
			}

		} else {
			// 如果为空, 插入一条事务再次判断
			try {
				insertTransaction(context.getXid(), context.getBranchId(), userId, money);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;

	}

	private int insertTransaction(String xid, Long branchId, Long userId, BigDecimal money) {
		Transaction transaction = new Transaction();
		transaction.setTxId(xid);
		transaction.setActionId(String.valueOf(branchId));
		transaction.setAmount(money.toString());
		transaction.setCreateTime(new Date());
		transaction.setUserId(String.valueOf(userId));
		transaction.setState(INIT);

		int res = transactionMapper.insertSelective(transaction);

		return res;
	}

}
