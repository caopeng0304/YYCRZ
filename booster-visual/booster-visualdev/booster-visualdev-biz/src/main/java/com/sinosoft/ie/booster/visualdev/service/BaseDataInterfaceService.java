
package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseDataInterfaceEntity;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.visualdev.model.datainterface.PaginationDataInterface;

import java.util.List;
import java.util.Map;

/**
 * 数据接口
 *
 * @author booster code generator
 * @since 2021-09-14
 */
public interface BaseDataInterfaceService extends IService<BaseDataInterfaceEntity> {
	/**
	 * 获取接口列表(分页)
	 *
	 * @param pagination
	 * @return
	 */
	List<BaseDataInterfaceEntity> getList(PaginationDataInterface pagination);

	/**
	 * 获取接口列表下拉框
	 *
	 * @return
	 */
	List<BaseDataInterfaceEntity> getList();

	/**
	 * 获取接口数据
	 *
	 * @param id
	 * @return
	 */
	BaseDataInterfaceEntity getInfo(Long id);

	/**
	 * 添加数据接口
	 *
	 * @param entity
	 * @throws DataException
	 */
	void create(BaseDataInterfaceEntity entity) throws DataException;

	/**
	 * 修改接口
	 *
	 * @param entity
	 * @param id
	 * @return
	 * @throws DataException
	 */
	boolean update(BaseDataInterfaceEntity entity, Long id) throws DataException;

	/**
	 * 删除接口
	 *
	 * @param entity
	 */
	void delete(BaseDataInterfaceEntity entity);

	/**
	 * 判断接口名称是否重复
	 *
	 * @param fullName
	 * @param id
	 * @return
	 */
	boolean isExistByFullName(String fullName, Long id);

	/**
	 * 访问接口
	 *
	 * @param id
	 * @param sql
	 * @return
	 * @throws DataException
	 */
	List<Map<String, Object>> get(Long id, String sql) throws DataException;

	/**
	 * 访问接口路径
	 *
	 * @param id
	 * @return
	 */
	R infoToId(Long id);
}
