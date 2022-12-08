package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.model.position.PositionPagination;

import java.util.List;

/**
 * sys_position
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-09-14 15:10:15
 */
public interface SysPositionService extends IService<SysPositionEntity> {

	/**
	 * 列表
	 *
	 * @return
	 */
	List<SysPositionEntity> getList();

	/**
	 * 列表
	 *
	 * @param paginationPosition 条件
	 * @return
	 */
	List<SysPositionEntity> getList(PositionPagination paginationPosition);

	/**
	 * 列表
	 *
	 * @param userName 用户名
	 * @return
	 */
	List<SysPositionEntity> getListByUserName(String userName);

	/**
	 * 获取多个岗位下的用户信息
	 *
	 * @param ids 逗号分割的岗位Id
	 * @return
	 */
	List<String> getUserPositionByIds(String ids);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	SysPositionEntity getInfo(String id);

	/**
	 * 验证名称
	 *
	 * @param fullName 名称
	 * @param id       主键值
	 * @return
	 */
	boolean isExistByFullName(String fullName, String id);

	/**
	 * 验证编码
	 *
	 * @param enCode 编码
	 * @param id     主键值
	 * @return
	 */
	boolean isExistByEnCode(String enCode, String id);

	/**
	 * 创建
	 *
	 * @param entity 实体对象
	 */
	void create(SysPositionEntity entity);

	/**
	 * 更新
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 */
	boolean update(String id, SysPositionEntity entity);

	/**
	 * 删除
	 *
	 * @param entity 实体对象
	 */
	void delete(SysPositionEntity entity);

	/**
	 * 上移
	 *
	 * @param id 主键值
	 */
	boolean first(String id);

	/**
	 * 下移
	 *
	 * @param id 主键值
	 */
	boolean next(String id);

	/**
	 * 获取名称
	 *
	 * @param id
	 * @return
	 */
	List<SysPositionEntity> getPositionName(List<String> id);
}
