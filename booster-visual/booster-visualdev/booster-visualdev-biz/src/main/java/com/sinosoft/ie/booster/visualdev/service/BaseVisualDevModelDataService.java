
package com.sinosoft.ie.booster.visualdev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevModelDataEntity;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 0代码功能数据表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
public interface BaseVisualDevModelDataService extends IService<BaseVisualDevModelDataEntity> {
	List<Map<String, Object>> getListResult(BaseVisualDevEntity visualdevEntity, PaginationModel paginationModel) throws IOException, ParseException, DataException, SQLException;

	List<BaseVisualDevModelDataEntity> getList(Long modelId);

	BaseVisualDevModelDataEntity getInfo(Long id);

	VisualdevModelDataInfoVO infoDataChange(Long id, BaseVisualDevEntity visualdevEntity) throws IOException, ParseException, DataException, SQLException;

	VisualdevModelDataInfoVO tableInfo(Long id, BaseVisualDevEntity visualdevEntity) throws DataException, ParseException, SQLException, IOException;

	void create(BaseVisualDevEntity visualdevEntity, VisualdevModelDataCrForm visualdevModelDataCrForm) throws DataException, SQLException;

	boolean update(Long id, BaseVisualDevEntity visualdevEntity, VisualdevModelDataUpForm visualdevModelDataUpForm) throws DataException, SQLException;

	void delete(BaseVisualDevModelDataEntity entity);

	boolean tableDelete(Long id, BaseVisualDevEntity visualdevEntity) throws DataException, SQLException;

	boolean tableDeleteMore(String ids, BaseVisualDevEntity visualdevEntity) throws DataException, SQLException;

	void importData(List<BaseVisualDevModelDataEntity> list);

	List<Map<String, Object>> exportData(String[] keys, PaginationModelExport paginationModelExport, BaseVisualDevEntity visualdevEntity) throws IOException, ParseException, SQLException, DataException;

	VisualdevModelDataInfoVO tableInfoDataChange(Long id, BaseVisualDevEntity visualdevEntity) throws DataException, ParseException, IOException, SQLException;
}
