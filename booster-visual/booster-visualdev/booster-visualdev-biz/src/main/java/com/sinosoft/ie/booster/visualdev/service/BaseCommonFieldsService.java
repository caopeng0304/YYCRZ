package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.visualdev.entity.BaseCommonFieldsEntity;

import java.util.List;

/**
 * 常用表字段
 *
 * @author booster code generator
 * @since 2021-09-17
 */
public interface BaseCommonFieldsService extends IService<BaseCommonFieldsEntity> {
	List<BaseCommonFieldsEntity> getList();

	BaseCommonFieldsEntity getInfo(Long id);

	void create(BaseCommonFieldsEntity entity);

	boolean update(Long id, BaseCommonFieldsEntity entity);

	/**
	 * 验证名称
	 *
	 * @param fullName 名称
	 * @param id       主键值
	 * @return
	 */
	boolean isExistByFullName(String fullName, Long id);

	void delete(BaseCommonFieldsEntity entity);
}
