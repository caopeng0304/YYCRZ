package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDataEntity;
import com.sinosoft.ie.booster.visualdev.model.datascreen.PaginationData;

import java.util.List;

/**
 * 大屏数据
 *
 * @author booster code generator
 * @since 2021-09-16
 */
public interface BaseVisualDataService extends IService<BaseVisualDataEntity> {
	/**
	 * 获取大屏列表(分页)
	 *
	 * @param pagination 分类
	 */
	List<BaseVisualDataEntity> getList(PaginationData pagination);

	/**
	 * 获取大屏
	 */
	List<BaseVisualDataEntity> getList();

	/**
	 * 获取大屏基本信息
	 *
	 * @param id 主键
	 */
	BaseVisualDataEntity getInfo(Long id);

	/**
	 * 新增
	 *
	 * @param entity 大屏基本对象
	 */
	void create(BaseVisualDataEntity entity);

	/**
	 * 修改
	 *  @param id     主键
	 * @param entity 实体
	 */
	boolean update(Long id, BaseVisualDataEntity entity);


	/**
	 * 删除
	 *
	 * @param entity 实体
	 */
	void delete(BaseVisualDataEntity entity);

	/**
	 * 验证重复名称
	 *
	 * @param id   id
	 * @param name 名称
	 * @return
	 */
	boolean isExistByName(Long id, String name);
}
