
package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.visualdev.entity.BaseDbLinkEntity;

import java.util.List;

/**
 * 数据连接
 *
 * @author booster code generator
 * @since 2021-09-14
 */
public interface BaseDbLinkService extends IService<BaseDbLinkEntity> {
	/**
	 * 列表
	 *
	 * @return
	 */
	List<BaseDbLinkEntity> getList();

	/**
	 * 列表关键字查询
	 *
	 * @param keyWord
	 * @return
	 */
	List<BaseDbLinkEntity> getList(String keyWord);

	/**
	 * 信息
	 *
	 * @param id
	 * @return
	 */
	BaseDbLinkEntity getInfo(Long id);

	/**
	 * 验证名称
	 *
	 * @param fullName 名称
	 * @param id       主键值
	 * @return
	 */
	boolean isExistByFullName(String fullName, Long id);

	/**
	 * 创建
	 *
	 * @param entity 实体对象
	 */
	void create(BaseDbLinkEntity entity);

	/**
	 * 更新
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 * @return
	 */
	boolean update(Long id, BaseDbLinkEntity entity);

	/**
	 * 删除
	 *
	 * @param entity 实体对象
	 */
	void delete(BaseDbLinkEntity entity);

	/**
	 * 上移
	 *
	 * @param id 主键值
	 * @return
	 */
	boolean first(Long id);

	/**
	 * 下移
	 *
	 * @param id 主键值
	 * @return
	 */
	boolean next(Long id);

	/**
	 * 测试连接
	 *
	 * @param entity 实体对象
	 * @return
	 */
	boolean testDbConnection(BaseDbLinkEntity entity);
}
