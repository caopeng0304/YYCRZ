
package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.visualdev.entity.BasePortalEntity;
import com.sinosoft.ie.booster.visualdev.model.portal.PortalPagination;

import java.util.List;

/**
 * 门户表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
public interface BasePortalService extends IService<BasePortalEntity> {
	List<BasePortalEntity> getList(PortalPagination pagination);

	List<BasePortalEntity> getList();

	BasePortalEntity getInfo(Long id);

	void create(BasePortalEntity entity);

	boolean update(Long id, BasePortalEntity entity);

	void delete(BasePortalEntity entity);
}
