package com.sinosoft.ie.booster.seata.demo.tcc.impl;

import com.sinosoft.ie.booster.seata.demo.dao.StorageMapper;
import com.sinosoft.ie.booster.seata.demo.po.Storage;
import com.sinosoft.ie.booster.seata.demo.tcc.StorageTccAction;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageTccActionImpl implements StorageTccAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageTccActionImpl.class);

	@Resource
	private StorageMapper storageMapper;

	@Override
	public void reduceStorage(Long productId, Integer count) throws Exception {
		LOGGER.info("当前 XID: {}", RootContext.getXID());

		//模拟超时异常，全局事务回滚
//		try {
//			Thread.sleep(30*1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		// 查询库存信息
		Storage storage = storageMapper.selectById(productId);

		if (null != storage) {
			// 判断库存是否存足
			if (storage.getResidue() - storage.getUsed() < 0) {
				throw new RuntimeException("库存不足");
			}

			// 冻结库存
			storage.setUsed(storage.getUsed() + count);
			storageMapper.updateById(storage);

			LOGGER.info("冻结商品 {} 库存成功",productId);
		} else {
			throw new RuntimeException("查询库存信息失败");
		}

	}

	/**
	 * tcc模式的 commit 方法
	 * 可以空确认
	 * @param context 上下文
	 * @return
	 */
	@Override
	public boolean commitStorage(BusinessActionContext context) {
		// 判断 XID 是否存在
		if (StringUtils.isEmpty(context.getXid()) || null == context.getXid()) {
			return false;
		}

		// 获取数据
		Integer productId = (Integer) context.getActionContext("productId");
		Integer count = (Integer) context.getActionContext("count");

		// 执行减少库存
		Storage storage = storageMapper.selectById(productId);
		storage.setUsed(storage.getUsed() - count);
		storage.setResidue(storage.getResidue() - count);
		storageMapper.updateById(storage);

		LOGGER.info("减少商品 {} 库存成功, XID=" + context.getXid(), productId);
		LOGGER.info("Confirm阶段, commitStorage --> xid = " + context.getXid() + ", commitStorage提交成功");

		return true;
	}

	/**
	 * tcc模式 rollback 方法
	 * @param context 上下文
	 * @return
	 */
	@Override
	public boolean rollbackStorage(BusinessActionContext context) {
		LOGGER.info("Cancel阶段, rollbackStorage --> xid = " + context.getXid() + ", rollbackStorage提交失败");

		//TODO 这里可以实现中间件、非关系型数据库的回滚操作
		LOGGER.info("Cancel阶段, rollbackStorage this data: {}, {}", context.getActionContext("productId"), context.getActionContext("count"));

		// 获取回滚的数据
		Integer productId = (Integer) context.getActionContext("productId");
		Integer count = (Integer) context.getActionContext("count");

		// 恢复减少库存
		Storage storage = storageMapper.selectById(productId);
		storage.setUsed(storage.getUsed() - count);
		storage.setResidue(storage.getResidue() + count);
		storageMapper.updateById(storage);

		LOGGER.info("回滚成功");

		return true;
	}
}
