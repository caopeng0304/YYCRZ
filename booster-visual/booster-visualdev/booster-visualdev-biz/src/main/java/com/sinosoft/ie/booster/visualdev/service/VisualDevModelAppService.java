package com.sinosoft.ie.booster.visualdev.service;


import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.PaginationModel;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * App可视化开发功能表
 *
 * @author booster开发平台组
 * @since 2021-04-02
 */
public interface VisualDevModelAppService {
	/**
	 * 列表
	 *
	 * @param modelId         模板id
	 * @param paginationModel 条件
	 * @return
	 */
	List<Map<String, Object>> resultList(Long modelId, PaginationModel paginationModel) throws DataException, ParseException, SQLException, IOException;

	/**
	 * 新增
	 *
	 * @param entity 实体
	 * @param data   数据
	 */
	void create(BaseVisualDevEntity entity, String data) throws DataException, SQLException;

	/**
	 * 修改
	 *  @param id     主键
	 * @param entity 实体
	 * @param data   数据
	 */
	boolean update(Long id, BaseVisualDevEntity entity, String data) throws DataException, SQLException;

	/**
	 * 删除
	 *  @param id     主键
	 * @param entity 实体
	 */
	boolean delete(Long id, BaseVisualDevEntity entity) throws DataException, SQLException;

	/**
	 * 信息
	 *
	 * @param id     主键
	 * @param entity 实体
	 * @return
	 */
	Map<String, Object> info(Long id, BaseVisualDevEntity entity) throws DataException, ParseException, SQLException, IOException;
}

