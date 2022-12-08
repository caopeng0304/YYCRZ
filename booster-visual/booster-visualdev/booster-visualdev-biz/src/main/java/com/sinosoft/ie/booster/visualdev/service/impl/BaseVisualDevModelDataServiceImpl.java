package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.visualdev.constant.DataTypeConst;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevModelDataEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseVisualDevModelDataMapper;
import com.sinosoft.ie.booster.visualdev.model.base.ColumnDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.Template6.ColumnListField;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.*;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevModelDataService;
import com.sinosoft.ie.booster.visualdev.util.PageUtil;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.VisualUtils;
import com.sinosoft.ie.booster.visualdev.util.onlinedev.*;
import lombok.Cleanup;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 0代码功能数据表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Service
public class BaseVisualDevModelDataServiceImpl extends ServiceImpl<BaseVisualDevModelDataMapper, BaseVisualDevModelDataEntity> implements BaseVisualDevModelDataService {
	@Override
	public List<BaseVisualDevModelDataEntity> getList(Long modelId) {
		QueryWrapper<BaseVisualDevModelDataEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDevModelDataEntity::getVisualDevId, modelId);
		return this.list(queryWrapper);
	}

	@Override
	public List<Map<String, Object>> getListResult(BaseVisualDevEntity visualdevEntity, PaginationModel paginationModel) throws IOException, ParseException, DataException, SQLException {
		List<Map<String, Object>> realList = new ArrayList<>();
		List<BaseVisualDevModelDataEntity> list = null;
		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			ColumnDataModel columnData = JsonUtil.getJsonToBean(visualdevEntity.getColumnData(), ColumnDataModel.class);
			List<ColumnListField> modelList = JsonUtil.getJsonToList(columnData.getColumnList(), ColumnListField.class);
			List<Map<String, Object>> mapList = JsonUtil.getJsonToListMap(visualdevEntity.getRefTables());
			String mainTable = mapList.get(0).get("table").toString();

			list = KeyDataUtil.getHasTableList(list, mainTable, modelList, columnData);
		} else {
			list = this.getList(visualdevEntity.getId());
		}

		//关键字过滤
		if (list.size() > 0) {
			if (StrUtil.isNotEmpty(paginationModel.getSidx())) {
				//排序处理
				list.sort((o1, o2) -> {
					Map<String, Object> i1 = JsonUtil.stringToMap(o1.getData());
					Map<String, Object> i2 = JsonUtil.stringToMap(o2.getData());
					String s1 = String.valueOf(i1.get(paginationModel.getSidx()));
					String s2 = String.valueOf(i2.get(paginationModel.getSidx()));
					if ("desc".equalsIgnoreCase(paginationModel.getSort())) {
						return s2.compareTo(s1);
					} else {
						return s1.compareTo(s2);
					}
				});
			}
			//将查询的关键字json转成map
			Map<String, Object> keyJsonMap = JsonUtil.stringToMap(paginationModel.getJson());
			//数据处理
			realList = ListDataHandler.swapListData(keyJsonMap, visualdevEntity, list, realList);
			//分页处理
			paginationModel.setTotal(realList.size());
			realList = PageUtil.getListPage((int) paginationModel.getCurrentPage(), (int) paginationModel.getPageSize(), realList);

			ColumnDataModel columnDataModel = JsonUtil.getJsonToBean(visualdevEntity.getColumnData(), ColumnDataModel.class);
			//判断数据是否分组
			if (OnlineDevData.TYPE_THREE_COLUMNDATA.equals(columnDataModel.getType())) {
				return KeyDataUtil.changeGroupDataList(columnDataModel, realList);
			}
			return realList;

		}
		return realList;
	}


	@Override
	public List<Map<String, Object>> exportData(String[] keys, PaginationModelExport paginationModelExport, BaseVisualDevEntity visualdevEntity) throws IOException, ParseException, SQLException, DataException {
		List<BaseVisualDevModelDataEntity> list = null;
		List<Map<String, Object>> realList = new ArrayList<>();
		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			ColumnDataModel columnData = JsonUtil.getJsonToBean(visualdevEntity.getColumnData(), ColumnDataModel.class);
			List<ColumnListField> modelList = JsonUtil.getJsonToList(columnData.getColumnList(), ColumnListField.class);
			List<Map<String, Object>> mapList = JsonUtil.getJsonToListMap(visualdevEntity.getRefTables());
			String mainTable = mapList.get(0).get("table").toString();
			list = KeyDataUtil.getHasTableList(list, mainTable, modelList, columnData);
		} else {
			list = this.getList(visualdevEntity.getId());
		}
		//删除多余字段
		list = VisualUtils.deleteKey(list, keys);
		//关键字过滤
		if (list.size() > 0) {
			//将查询的关键字json转成map
			Map<String, Object> keyJsonMap = JsonUtil.stringToMap(paginationModelExport.getJson());
			//数据处理
			realList = ListDataHandler.swapListData(keyJsonMap, visualdevEntity, list, realList);
			//判断分页
			if (!"1".equals(paginationModelExport.getDataType())) {
				paginationModelExport.setTotal(realList.size());
				realList = PageUtil.getListPage((int) paginationModelExport.getCurrentPage(), (int) paginationModelExport.getPageSize(), realList);
			}
			return realList;
		}
		return realList;
	}


	@Override
	public BaseVisualDevModelDataEntity getInfo(Long id) {
		QueryWrapper<BaseVisualDevModelDataEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDevModelDataEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public VisualdevModelDataInfoVO infoDataChange(Long id, BaseVisualDevEntity visualdevEntity) throws IOException, ParseException, DataException, SQLException {
		Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());
		List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);
		//去除模板多级控件
		modelList = VisualUtils.deleteMore(modelList);

		QueryWrapper<BaseVisualDevModelDataEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseVisualDevModelDataEntity::getId, id);
		BaseVisualDevModelDataEntity visualdevModelDataEntity = this.getOne(queryWrapper);
		if (visualdevModelDataEntity != null) {
			Map<String, Object> newDataMap = JsonUtil.stringToMap(visualdevModelDataEntity.getData());
			Map<String, Object> newDataMapOperate = new HashMap<>(16);
			newDataMapOperate.putAll(newDataMap);
			Map<String, Object> newMainDataMap = new HashMap<>(16);
			for (Map.Entry<String, Object> entryMap : newDataMapOperate.entrySet()) {
				String key = entryMap.getKey();
				if (!key.contains("table")) {
					newMainDataMap.put(key, entryMap.getValue());
					newDataMap.remove(key);
				}
			}
			//添加主表数据
			newDataMap.put("main", JSON.toJSONString(newMainDataMap));
			int t = 0;
			//转换主表和子表数据
			for (FieLdsModel fieLdsModel : modelList) {
				for (Map.Entry<String, Object> entryMap : newDataMap.entrySet()) {
					//转换为真实数据
					if (entryMap.getKey().equals(fieLdsModel.getVModel()) || "main".equals(entryMap.getKey())) {
						List<BaseVisualDevModelDataEntity> list = new ArrayList<>();
						//将查询的关键字json转成map
						Map<String, Object> keyJsonMap = new HashMap<>(16);
						List<FieLdsModel> formDatas;
						if ("main".equals(entryMap.getKey()) && t == 0) {
							formDatas = modelList;
							BaseVisualDevModelDataEntity entity = new BaseVisualDevModelDataEntity();
							entity.setData(entryMap.getValue().toString());
							list.add(entity);
							t++;
							//将关键字查询传输的id转换成名称
							Map<String, Object> keyAndList = KeyDataUtil.getKeyData(formDatas, keyJsonMap, list, visualdevEntity.getId());

							//替换静态数据模板选项值
							keyAndList = ListDataHandler.getRealData(formDatas, keyJsonMap, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), BaseVisualDevModelDataEntity.class));
							//系统自动生成字段转换
							list = VisualUtils.stringToList(formDatas, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), BaseVisualDevModelDataEntity.class));
							//字符串转数组
							list = AutoFieldsUtil.autoFeildsList(formDatas, list);
							entryMap.setValue(list);
						} else if (entryMap.getKey().equals(fieLdsModel.getVModel())) {
							formDatas = JsonUtil.getJsonToList(fieLdsModel.getConfig().getChildren(), FieLdsModel.class);
							List<Map<String, Object>> childMapList = (List<Map<String, Object>>) entryMap.getValue();
							for (Map<String, Object> objectMap : childMapList) {
								BaseVisualDevModelDataEntity entity = new BaseVisualDevModelDataEntity();
								entity.setData(JsonUtilEx.getObjectToString(objectMap));
								list.add(entity);
							}
							//将关键字查询传输的id转换成名称
							Map<String, Object> keyAndList = KeyDataUtil.getKeyData(formDatas, keyJsonMap, list, visualdevEntity.getId());

							//替换静态数据模板选项值
							keyAndList = ListDataHandler.getRealData(formDatas, keyJsonMap, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), BaseVisualDevModelDataEntity.class));
							//系统自动生成字段转换
							list = VisualUtils.stringToList(formDatas, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), BaseVisualDevModelDataEntity.class));
							//字符串转数组
							list = AutoFieldsUtil.autoFeildsList(formDatas, list);
							entryMap.setValue(list);
						}
					}
				}
			}

			List<BaseVisualDevModelDataEntity> mainList = (List<BaseVisualDevModelDataEntity>) newDataMap.get("main");

			Map<String, Object> realDataMap = JsonUtil.stringToMap(mainList.get(0).getData());
			newDataMap.remove("main");

			for (Map.Entry<String, Object> entryMap : newDataMap.entrySet()) {
				List<BaseVisualDevModelDataEntity> childList = (List<BaseVisualDevModelDataEntity>) entryMap.getValue();
				List<String> array = new ArrayList<>();
				for (BaseVisualDevModelDataEntity childEntity : childList) {
					array.add(childEntity.getData());
				}
				entryMap.setValue(array);
			}
			realDataMap.putAll(newDataMap);
			VisualdevModelDataInfoVO vo = new VisualdevModelDataInfoVO();
			String tmp1 = StringEscapeUtils.unescapeJavaScript(JsonUtilEx.getObjectToString(realDataMap));
			tmp1 = tmp1.replace("\"{", "{");
			tmp1 = tmp1.replace("}\"", "}");
			tmp1 = tmp1.replace("\"[", "[");
			tmp1 = tmp1.replace("]\"", "]");
			vo.setData(tmp1);
			vo.setId(String.valueOf(visualdevModelDataEntity.getId()));
			return vo;
		}
		return null;
	}

	/**
	 * 获取单条信息时，主表将时间字符串转时间戳，子表将字符串类型的数组时间戳转成Json数组时间戳
	 *
	 * @param id
	 * @param visualdevEntity
	 * @return
	 * @throws DataException
	 * @throws ParseException
	 */
	@Override
	public VisualdevModelDataInfoVO tableInfo(Long id, BaseVisualDevEntity visualdevEntity) throws DataException, SQLException, ParseException, IOException {
		Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());
		List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);

		//去除模板多级控件
		modelList = VisualUtils.deleteMore(modelList);

		List<Map<String, Object>> tableMapList = JsonUtil.getJsonToListMap(visualdevEntity.getRefTables());
		String mainTable = tableMapList.get(0).get("table").toString();
		@Cleanup Connection conn = VisualUtils.getTableConn();
		//获取主键
		String pKeyName = VisualUtils.getpKey(conn, mainTable);

		StringBuilder mainfeild = new StringBuilder();
		String main = "select * from" + " " + tableMapList.get(0).get("table") + " where " + pKeyName + "='" + id + "'";
		List<Map<String, Object>> mainMap = VisualUtils.getTableDataInfo(main);
		return TableInfoUtil.getTableInfo(pKeyName, mainfeild, modelList, mainMap, tableMapList);
	}


	/**
	 * 获取单条信息时，主表将时间字符串转时间戳，子表将字符串类型的数组时间戳转成Json数组时间戳(真实数据转换)
	 *
	 * @param id
	 * @param visualdevEntity
	 * @return
	 * @throws DataException
	 * @throws ParseException
	 */
	@Override
	public VisualdevModelDataInfoVO tableInfoDataChange(Long id, BaseVisualDevEntity visualdevEntity) throws DataException, IOException, SQLException, ParseException {
		Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());

		List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);
		//去除模板多级控件
		modelList = VisualUtils.deleteMore(modelList);
		List<Map<String, Object>> tableMapList = JsonUtil.getJsonToListMap(visualdevEntity.getRefTables());
		String mainTable = tableMapList.get(0).get("table").toString();

		return TableInfoUtil.getTableInfoDataChange(id, modelList, tableMapList, mainTable);
	}


	/**
	 * 主表添加时将时间转换成字符串时间，子表不做改变
	 *
	 * @param visualdevEntity
	 * @param visualdevModelDataCrForm
	 * @throws DataException
	 */
	@Override
	public void create(BaseVisualDevEntity visualdevEntity, VisualdevModelDataCrForm visualdevModelDataCrForm) throws DataException, SQLException {
		Map<String, Object> allDataMap = JsonUtil.stringToMap(visualdevModelDataCrForm.getData());
		List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(JsonUtil.stringToMap(visualdevEntity.getFormData()).get("fields"), FieLdsModel.class);
		//去除模板多级控件
		fieLdsModelList = VisualUtils.deleteMore(fieLdsModelList);

		//去除无意义控件
		fieLdsModelList = VisualUtils.deleteVmodel(fieLdsModelList);

		//生成系统自动生成字段
		allDataMap = AutoFieldsUtil.createFeilds(fieLdsModelList, allDataMap);

		Map<String, Object> newMainDataMap = JsonUtil.stringToMap(visualdevModelDataCrForm.getData());


		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			OnlineDevDbUtil.insertTable(visualdevEntity, fieLdsModelList, allDataMap, newMainDataMap);
		} else {
			BaseVisualDevModelDataEntity entity = new BaseVisualDevModelDataEntity();
			entity.setData(JsonUtilEx.getObjectToString(allDataMap));
			entity.setVisualDevId(visualdevEntity.getId());

			entity.setSort(RandomUtil.parses());
			entity.setEnabledFlag("1");
			this.save(entity);
		}
	}

	@Override
	public boolean update(Long id, BaseVisualDevEntity visualdevEntity, VisualdevModelDataUpForm visualdevModelDataUpForm) throws DataException, SQLException {

		Map<String, Object> allDataMap = JsonUtil.stringToMap(visualdevModelDataUpForm.getData());
		List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(JsonUtil.stringToMap(visualdevEntity.getFormData()).get("fields"), FieLdsModel.class);
		//去除模板多级控件
		fieLdsModelList = VisualUtils.deleteMore(fieLdsModelList);
		//去除无意义控件
		fieLdsModelList = VisualUtils.deleteVmodel(fieLdsModelList);

		//生成系统自动生成字段
		allDataMap = AutoFieldsUtil.updateFeilds(fieLdsModelList, allDataMap);

		Map<String, Object> newMainDataMap = JsonUtil.stringToMap(visualdevModelDataUpForm.getData());

		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			return OnlineDevDbUtil.updateTable(id, visualdevEntity, fieLdsModelList, allDataMap, newMainDataMap);
		}

		BaseVisualDevModelDataEntity entity = new BaseVisualDevModelDataEntity();
		entity.setData(JsonUtilEx.getObjectToString(allDataMap));
		entity.setVisualDevId(visualdevEntity.getId());
		entity.setId(id);
		return this.updateById(entity);
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(BaseVisualDevModelDataEntity entity) {
		if (entity != null) {
			this.removeById(entity.getId());
		}
	}

	@Override
	public boolean tableDelete(Long id, BaseVisualDevEntity visualdevEntity) throws DataException, SQLException {

		return OnlineDevDbUtil.deleteTable(id, visualdevEntity);
	}

	@Override
	public boolean tableDeleteMore(String ids, BaseVisualDevEntity visualdevEntity) throws DataException, SQLException {

		return OnlineDevDbUtil.deleteTables(ids, visualdevEntity);
	}


	@Override
	public void importData(List<BaseVisualDevModelDataEntity> list) {

	}
}
