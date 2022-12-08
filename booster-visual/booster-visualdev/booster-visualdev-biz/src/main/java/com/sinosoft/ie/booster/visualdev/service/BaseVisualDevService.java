
package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.model.base.PaginationVisualdev;

import java.util.List;

/**
 * 可视化开发功能表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
public interface BaseVisualDevService extends IService<BaseVisualDevEntity> {
	List<BaseVisualDevEntity> getList(PaginationVisualdev paginationVisualdev);

	List<BaseVisualDevEntity> getList();

	BaseVisualDevEntity getInfo(Long id);


	void create(BaseVisualDevEntity entity);

	boolean update(Long id, BaseVisualDevEntity entity);

	void delete(BaseVisualDevEntity entity);
}
