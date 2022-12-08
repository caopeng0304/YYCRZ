package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDataMapEntity;
import com.sinosoft.ie.booster.common.core.model.Pagination;

import java.util.List;

/**
 * 大屏地图
 *
 * @author booster code generator
 * @since 2021-09-18
 */
public interface BaseVisualDataMapService extends IService<BaseVisualDataMapEntity> {
	/**
	 * 获取大屏列表(分页)
	 *
	 * @param pagination 分类
	 * @return
	 */
	List<BaseVisualDataMapEntity> getList(Pagination pagination);

	/**
	 * 获取大屏列表
	 *
	 * @param
	 * @return
	 */
	List<BaseVisualDataMapEntity> getList();

	/**
	 * 获取大屏基本信息
	 *
	 * @param id 主键
	 * @return
	 */
	BaseVisualDataMapEntity getInfo(Long id);

	/**
	 * 新增
	 *
	 * @param entity 实体
	 */
	void create(BaseVisualDataMapEntity entity);

	/**
	 * 修改
	 *
	 * @param id     主键
	 * @param entity 实体
	 * @return
	 */
	boolean update(Long id, BaseVisualDataMapEntity entity);

	/**
	 * 删除
	 *
	 * @param entity 实体
	 */
	void delete(BaseVisualDataMapEntity entity);

	/**
	 * 验证名称
	 *
	 * @param fullName 名称
	 * @param id       主键值
	 * @return
	 */
	boolean isExistByFullName(String fullName, Long id);

	/**
	 * 验证编码
	 *
	 * @param enCode 编码
	 * @param id     主键值
	 * @return
	 */
	boolean isExistByEnCode(String enCode, Long id);
}
