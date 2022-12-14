package com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.DbType;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.DownloadVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.visualdev.constant.BoosKeyConst;
import com.sinosoft.ie.booster.visualdev.constant.DataTypeConst;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevModelDataEntity;
import com.sinosoft.ie.booster.visualdev.model.base.ColumnDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.TableFields;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.TimeControl;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.props.PropsBeanModel;
import com.sinosoft.ie.booster.visualdev.util.*;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ??????????????????
 *
 * @author booster???????????????
 * @since 2021???3???13???16:37:40
 */
public class VisualUtils {


	private static DataSourceUtil dataSourceUtil;

	/**
	 * ?????????
	 */
	public static void init() {
		dataSourceUtil = SpringContextHolder.getBean(DataSourceUtil.class);
	}


	/**
	 * ?????????????????????????????????????????????
	 *
	 * @return
	 */
	public static List<FieLdsModel> deleteVmodel(List<FieLdsModel> modelList) {
		List<FieLdsModel> newModelList = new ArrayList<>();
		for (FieLdsModel model : modelList) {
			if (BoosKeyConst.CHILD_TABLE.equals(model.getConfig().getBoosKey())) {
				List<FieLdsModel> childModelList = JsonUtil.getJsonToList(model.getConfig().getChildren(), FieLdsModel.class);
				List<FieLdsModel> newchildModelList = new ArrayList<>();
				for (FieLdsModel childModel : childModelList) {
					if (StrUtil.isNotEmpty(childModel.getVModel())) {
						newchildModelList.add(childModel);
					}
				}
				model.getConfig().setChildren(newchildModelList);
				newModelList.add(model);
			} else {
				if (StrUtil.isNotEmpty(model.getVModel())) {
					newModelList.add(model);
				}
			}
		}
		return newModelList;
	}

	/**
	 * ????????????????????????
	 *
	 * @return
	 */
	public static List<FieLdsModel> deleteMoreVmodel(FieLdsModel model) {
		if ("".equals(model.getVModel()) && model.getConfig().getChildren() != null) {
			return JsonUtil.getJsonToList(model.getConfig().getChildren(), FieLdsModel.class);
		}
		return null;
	}

	public static List<FieLdsModel> deleteMore(List<FieLdsModel> modelList) {
		List<FieLdsModel> newModelList = new ArrayList<>();
		for (FieLdsModel model : modelList) {
			List<FieLdsModel> newList = deleteMoreVmodel(model);
			if (newList == null || BoosKeyConst.CHILD_TABLE.equals(model.getConfig().getBoosKey())) {
				newModelList.add(model);
			} else {
				newModelList.addAll(deleteMore(newList));
			}
		}
		return newModelList;
	}

	/**
	 * ????????????????????????
	 *
	 * @param conn
	 * @param sql
	 * @param pKeyName
	 * @return
	 * @throws DataException
	 */
	public static List<BaseVisualDevModelDataEntity> getTableDataList(Connection conn, String sql, String pKeyName) throws DataException {
		List<BaseVisualDevModelDataEntity> list = new ArrayList<>();
		ResultSet rs = JdbcUtil.query(conn, sql);
		List<Map<String, Object>> dataList = JdbcUtil.convertListString(rs);
		for (Map<String, Object> dataMap : dataList) {
			BaseVisualDevModelDataEntity dataEntity = new BaseVisualDevModelDataEntity();
			dataMap = toLowerKey(dataMap);
			dataEntity.setData(JsonUtil.getObjectToStringDateFormat(dataMap, "yyyy-MM-dd HH:mm"));
			if (dataMap.containsKey(pKeyName.toUpperCase())) {
				dataEntity.setId(Convert.toLong(dataMap.get(pKeyName.toUpperCase())));
			}
			list.add(dataEntity);
		}
		return list;
	}

	/**
	 * ????????????????????????
	 *
	 * @param feilds
	 * @param pKeyName
	 * @return
	 */
	public static Boolean existKey(String feilds, String pKeyName) {
		String[] strs = feilds.split(",");
		if (strs.length > 0) {
			for (String feild : strs) {
				if (feild.equals(pKeyName)) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * ????????????????????????????????????
	 *
	 * @param table
	 * @param feilds
	 * @return
	 */
	public static String getInsertSql(String table, String feilds, String mainpKeyName, String childPkName, Long mainId) {
		StringBuilder insertSql = new StringBuilder();

		feilds = feilds.toLowerCase().trim();
		mainpKeyName = mainpKeyName.toLowerCase();
		childPkName = childPkName.toLowerCase();
		if (existKey(feilds, mainpKeyName) && existKey(feilds, childPkName)) {
			insertSql.append("INSERT INTO ").append(table).append("(").append(feilds).append(") ").append(" VALUES (");
		} else if (existKey(feilds, mainpKeyName) && !existKey(feilds, childPkName)) {
			insertSql.append("INSERT INTO ").append(table).append("(").append(childPkName).append(",").append(feilds).append(") ").append(" VALUES ('").append(IdUtil.getSnowflakeNextId()).append("',");
		} else if (!existKey(feilds, mainpKeyName) && existKey(feilds, childPkName)) {
			insertSql.append("INSERT INTO ").append(table).append("(").append(mainpKeyName).append(",").append(feilds).append(") ").append(" VALUES ('").append(mainId).append("',");
		} else {
			insertSql.append("INSERT INTO ").append(table).append("(").append(childPkName).append(",").append(mainpKeyName).append(",").append(feilds).append(") ").append(" VALUES ('").append(IdUtil.getSnowflakeNextId()).append("','").append(mainId).append("',");
		}
		return insertSql.toString();
	}

	/**
	 * ????????????????????????????????????
	 *
	 * @param baseSql
	 * @return
	 */
	public static String getRealSql(String baseSql, Long mainId) {
		String[] sql = baseSql.split(",");
		String realSql = baseSql;
		if (sql.length == 2) {
			realSql = "('" + IdUtil.getSnowflakeNextId() + "'," + sql[1] + ",";
		} else if (sql.length == 1 && !baseSql.contains(String.valueOf(mainId))) {
			realSql = "('" + IdUtil.getSnowflakeNextId() + "',";
		}
		return realSql;
	}

	/**
	 * ??????????????????
	 *
	 * @param conn
	 * @param mainTable
	 * @return
	 */
	public static String getpKey(Connection conn, String mainTable) throws SQLException {
		String pKeyName = "id";
		//catalog ????????????
		String catalog = conn.getCatalog();
		ResultSet primaryKeyResultSet = conn.getMetaData().getPrimaryKeys(catalog, null, mainTable);
		while (primaryKeyResultSet.next()) {
			pKeyName = primaryKeyResultSet.getString("COLUMN_NAME");
		}
		primaryKeyResultSet.close();
		return pKeyName;
	}


	/**
	 * ????????????????????????
	 *
	 * @param sql
	 * @return
	 * @throws DataException
	 */
	public static List<Map<String, Object>> getTableDataInfo(String sql) throws DataException {
		Connection conn = getTableConn();
		ResultSet rs = JdbcUtil.query(conn, sql);
		List<Map<String, Object>> dataList = JdbcUtil.convertListString(rs);
		//??????????????????????????????
		for (Map<String, Object> dataMap : dataList) {
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				//????????????????????????????????????????????????????????????Json???????????????
				if (entry.getValue() != null && String.valueOf(entry.getValue()).contains(",") && !"[\"".equals(String.valueOf(entry.getValue()).substring(0, 2))) {
					JSONArray list = JsonUtil.getJsonToJsonArray(String.valueOf(entry.getValue()));
					entry.setValue(list);
				}
			}
		}
		return dataList;
	}

	/**
	 * ???????????????????????????????????????????????????
	 *
	 * @param modelList
	 * @param dataMapList
	 * @return
	 * @throws ParseException
	 */
	public static List<Map<String, Object>> swapTableDataInfoList(List<FieLdsModel> modelList, List<Map<String, Object>> dataMapList) throws ParseException {
		List<Map<String, Object>> newDataMapList = new ArrayList<>();
		for (Map<String, Object> dataMap : dataMapList) {
			Map<String, Object> newDataMap = new HashMap<>(16);
			for (FieLdsModel fieLdsModel : modelList) {
				for (Map.Entry<String, Object> entryMap : dataMap.entrySet()) {
					if (entryMap.getKey().equals(fieLdsModel.getVModel())) {
						if (BoosKeyConst.UPLOADFZ.equals(fieLdsModel.getConfig().getBoosKey()) || BoosKeyConst.UPLOADIMG.equals(fieLdsModel.getConfig().getBoosKey())) {
							String value = String.valueOf(entryMap.getValue());
							if (!"[]".equals(value)) {
								List<Map<String, Object>> map = JsonUtil.getJsonToListMap(value);
								newDataMap.put(entryMap.getKey(), map);
							}
						} else if ("checkbox".equals(fieLdsModel.getConfig().getBoosKey())) {
							List<String> list = JsonUtil.getJsonToList(String.valueOf(entryMap.getValue()), String.class);
							newDataMap.put(entryMap.getKey(), list);
						} else if (BoosKeyConst.CREATETIME.equals(fieLdsModel.getConfig().getBoosKey()) || BoosKeyConst.MODIFYTIME.equals(fieLdsModel.getConfig().getBoosKey())) {
							newDataMap.put(entryMap.getKey(), String.valueOf(entryMap.getValue()));
						} else if (fieLdsModel.getConfig().getBoosKey().contains("date") && entryMap.getValue() != null) {
							if (entryMap.getValue().toString().contains(",")) {
								List<String> list = JsonUtil.getJsonToList(String.valueOf(entryMap.getValue()), String.class);
								SimpleDateFormat sdf = new SimpleDateFormat(fieLdsModel.getFormat());
								List<Object> newList = new ArrayList<>();
								for (String dateStr : list) {
									try {
										newList.add(Convert.toLong(dateStr));
									} catch (Exception e) {
										long s = sdf.parse(dateStr).getTime();
										newList.add(Long.toString(s));
									}
								}
								newDataMap.put(entryMap.getKey(), newList);
							} else {
								SimpleDateFormat sdf = new SimpleDateFormat(fieLdsModel.getFormat());
								Long s = sdf.parse(entryMap.getValue().toString()).getTime();
								newDataMap.put(entryMap.getKey(), s);
							}
						} else {
							newDataMap.put(entryMap.getKey(), entryMap.getValue());
						}
					}
				}
			}
			newDataMapList.add(newDataMap);
		}

		return newDataMapList;
	}


	/**
	 * ????????????????????????,????????????
	 *
	 * @param modelList
	 * @param dataMap
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Object> swapTableDataInfoOne(List<FieLdsModel> modelList, Map<String, Object> dataMap) throws ParseException {
		Map<String, Object> newDataMap = new HashMap<>(16);
		for (FieLdsModel fieLdsModel : modelList) {
			for (Map.Entry<String, Object> entryMap : dataMap.entrySet()) {
				if (entryMap.getKey().equals(fieLdsModel.getVModel())) {
					if (BoosKeyConst.UPLOADFZ.equals(fieLdsModel.getConfig().getBoosKey()) || BoosKeyConst.UPLOADIMG.equals(fieLdsModel.getConfig().getBoosKey())) {
						String value = String.valueOf(entryMap.getValue());
						if (!"[]".equals(value)) {
							List<Map<String, Object>> map = JsonUtil.getJsonToListMap(value);
							newDataMap.put(entryMap.getKey(), map);
						}
					} else if (BoosKeyConst.CHECKBOX.equals(fieLdsModel.getConfig().getBoosKey())) {
						List<String> list = JsonUtil.getJsonToList(String.valueOf(entryMap.getValue()), String.class);
						newDataMap.put(entryMap.getKey(), list);
					} else if (BoosKeyConst.CREATETIME.equals(fieLdsModel.getConfig().getBoosKey()) || BoosKeyConst.MODIFYTIME.equals(fieLdsModel.getConfig().getBoosKey())) {
						newDataMap.put(entryMap.getKey(), String.valueOf(entryMap.getValue()));
					} else if (fieLdsModel.getConfig().getBoosKey().contains("date") && entryMap.getValue() != null) {
						if (entryMap.getValue().toString().contains(",")) {
							List<String> list = JsonUtil.getJsonToList(String.valueOf(entryMap.getValue()), String.class);
							List<String> newList = new ArrayList<>();
							SimpleDateFormat sdf = new SimpleDateFormat(fieLdsModel.getFormat());
							for (String dateStr : list) {
								try {
									Long.valueOf(dateStr);
								} catch (Exception e) {
									long s = sdf.parse(dateStr).getTime();
									newList.add(Long.toString(s));
								}
							}
							newDataMap.put(entryMap.getKey(), newList);
						} else {
							SimpleDateFormat sdf = new SimpleDateFormat(fieLdsModel.getFormat());
							Long s = sdf.parse(entryMap.getValue().toString()).getTime();
							newDataMap.put(entryMap.getKey(), s);
						}
					} else {
						newDataMap.put(entryMap.getKey(), entryMap.getValue());
					}
				}
			}
		}
		return newDataMap;
	}


	/**
	 * ????????????????????????????????????
	 *
	 * @param sql
	 */
	public static void opaTableDataInfo(String sql) throws DataException {
		Connection conn = getTableConn();
		JdbcUtil.custom(conn, sql);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @return
	 */
	public static Connection getTableConn() {
		init();
		return JdbcUtil.getConn(dataSourceUtil.getUserName(), dataSourceUtil.getPassword(), dataSourceUtil.getUrl(), dataSourceUtil.getDriverClassName());
	}

	/**
	 * ???????????????????????????
	 *
	 * @param fieldList
	 * @param fieldStr
	 * @param props
	 * @param options
	 * @param fieLdsModel
	 * @return
	 */
	public static String setDicValue(List<String> fieldList, String fieldStr, PropsBeanModel props, List<Map<String, Object>> options, FieLdsModel fieLdsModel) {
		if (fieldList != null && fieldList.size() > 0) {
			for (String fieStr : fieldList) {

				for (Map<String, Object> optMap : options) {
					if (fieLdsModel.getSlot().getOptions() != null) {
						String label;
						String value;
						if (props != null) {
							label = props.getLabel();
							value = props.getValue();
							if (fieStr.equals(optMap.get(value).toString())) {
								return optMap.get(label).toString();
							}
						}
					}
				}
			}
		} else {
			for (Map<String, Object> optMap : options) {
				if (fieLdsModel.getSlot().getOptions() != null) {
					//??????prop?????????????????????????????????
					String label;
					String value;
					if (props != null) {
						label = props.getLabel();
						value = props.getValue();
						if (fieldStr.equals(optMap.get(value).toString())) {
							return optMap.get(label).toString();
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	public static List<BaseVisualDevModelDataEntity> deleteKey(List<BaseVisualDevModelDataEntity> list, String[] keys) {
		for (BaseVisualDevModelDataEntity entity : list) {
			if (!StrUtil.isEmpty(entity.getData()) && keys.length > 0) {
				Map<String, Object> keyMap = JsonUtil.stringToMap(entity.getData());
				Map<String, Object> keyResult = new HashMap<>(16);

				for (String selkey : keys) {
					for (Map.Entry<String, Object> entry : keyMap.entrySet()) {
						String key = entry.getKey();
						if (key.equals(selkey)) {
							keyResult.put(key, entry.getValue());
						}
					}
				}
				entity.setData(JSON.toJSONString(keyResult));
			}
		}
		return list;
	}

	/**
	 * ??????????????????????????????????????????
	 *
	 * @param field
	 * @return
	 */
	public static List<String> analysisField(String field) {
		List<String> keyList = new ArrayList<>();
		if (field != null) {
			try {
				keyList = JsonUtil.getJsonToList(field, String.class);
				if (keyList != null) {
					return keyList;
				}
				return new ArrayList<>();
			} catch (Exception e) {
				return keyList;
			}
		}
		return keyList;
	}

	/**
	 * ???????????????????????????
	 *
	 * @param formData
	 * @param path
	 * @param list
	 * @param keys
	 * @param userInfo
	 * @return
	 */
	public static DownloadVO createModelExcel(String formData, String path, List<Map<String, Object>> list, String[] keys, BoosterUser userInfo) {
		DownloadVO vo = DownloadVO.builder().build();
		try {
			FormDataModel formDataModel = JsonUtil.getJsonToBean(formData, FormDataModel.class);
			List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(formDataModel.getFields(), FieLdsModel.class);

			List<ExcelExportEntity> entitys = new ArrayList<>();
			for (FieLdsModel model : fieLdsModelList) {
				if (keys.length > 0) {
					for (String key : keys) {
						if (key.equals(model.getVModel())) {
							entitys.add(new ExcelExportEntity(model.getConfig().getLabel(), model.getVModel()));
						}
					}
				}
			}
			ExportParams exportParams = new ExportParams(null, "????????????");
			Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entitys, list);
			String fileName = "????????????" + DateUtil.dateNow("yyyyMMddHHmmss") + ".xls";
			vo.setName(fileName);
			vo.setUrl(UploaderUtil.uploaderFile(userInfo.getId() + "#" + fileName + "#" + "Temporary"));
			path = path + fileName;
			FileOutputStream fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vo;
	}

	public static List<String> getLabelMapSize(List<Map<String, Object>> optMapList, String label, String value, List<String> fieldList) {
		if (optMapList.size() > 0) {
			List<String> result = new ArrayList<>();
			for (Map<String, Object> optMap : optMapList) {
				List<Map<String, Object>> optChildMapList = JsonUtil.getJsonToListMap(JsonUtil.getObjectToString(optMap.get("children")));
				if (fieldList.contains(String.valueOf(optMap.get(value)))) {
					result.add(String.valueOf(optMap.get(label)));
				}
				if (optChildMapList != null && optChildMapList.size() > 0) {
					result.addAll(Objects.requireNonNull(getLabelMapSize(optChildMapList, label, value, fieldList)));
					return result;
				}
			}
			return result;
		}
		return null;
	}

	public static List<String> getValueMapSize(List<Map<String, Object>> optMapList, String value, List<String> fieldList) {
		if (optMapList.size() > 0) {
			List<String> result = new ArrayList<>();
			for (Map<String, Object> optMap : optMapList) {
				List<Map<String, Object>> optChildMapList = JsonUtil.getJsonToListMap(JsonUtil.getObjectToString(optMap.get("children")));
				if (fieldList.contains(String.valueOf(optMap.get(value)))) {
					result.add(String.valueOf(optMap.get(value)));
				}
				if (optChildMapList != null && optChildMapList.size() > 0) {
					result.addAll(Objects.requireNonNull(getValueMapSize(optChildMapList, value, fieldList)));
					return result;
				}
			}
			return result;
		}
		return null;
	}

	public static List<String> analysisLabelMap(Map<String, Object> optMap, FieLdsModel fieLdsModel, PropsBeanModel props, List<String> fieldList) {
		List<String> list = new ArrayList<>();
		if (fieLdsModel.getProps() != null && fieLdsModel.getProps().getProps() != null) {
			//??????prop?????????????????????????????????
			String label;
			String value;
			if (props != null) {
				label = props.getLabel();
				value = props.getValue();
				String lab = String.valueOf(optMap.get(label));
				String vau = String.valueOf(optMap.get(value));
				if (fieldList.contains(vau)) {
					list.add(lab);
				}
				if (optMap.containsKey("children")) {
					List<String> other = getLabelMapSize(JsonUtil.getJsonToListMap(JsonUtil.getObjectToString(optMap.get("children"))), label, value, fieldList);
					if (other != null) {
						list.addAll(other);
					}
				}
			}
		}
		return list;
	}

	public static List<String> analysisValueMap(Map<String, Object> optMap, FieLdsModel fieLdsModel, PropsBeanModel props, List<String> fieldList) {
		List<String> list = new ArrayList<>();
		if (fieLdsModel.getProps() != null && fieLdsModel.getProps().getProps() != null) {
			//??????prop?????????????????????????????????
			String value;
			if (props != null) {
				value = props.getValue();
				String vau = String.valueOf(optMap.get(value));
				if (fieldList.contains(vau)) {
					list.add(vau);
				}
				if (optMap.containsKey("children")) {
					List<String> other = getValueMapSize(JsonUtil.getJsonToListMap(JsonUtil.getObjectToString(optMap.get("children"))), value, fieldList);
					if (other != null) {
						list.addAll(other);
					}
				}
			}
		}
		return list;
	}

	/**
	 * ??????????????????????????????????????????????????????
	 *
	 * @param fieLdsModel
	 * @param fieldList
	 * @param keyJsonMap
	 * @return
	 */
	public static Map<String, Object> cascaderOperation(FieLdsModel fieLdsModel, List<String> fieldList, Map<String, Object> keyJsonMap) {
		Map<String, Object> cascaderMap = new HashMap<>(16);
		//??????????????????????????????
		if (fieLdsModel.getOptions() != null) {
			List<String> cascaderList = new ArrayList<>();
			List<String> keyListLast = new ArrayList<>();
			List<Map<String, Object>> options = JsonUtil.getJsonToListMap(fieLdsModel.getOptions());
			PropsBeanModel props = JsonUtil.getJsonToBean(fieLdsModel.getProps().getProps(), PropsBeanModel.class);
			for (Map<String, Object> optMap : options) {
				List<String> labelList = VisualUtils.analysisLabelMap(optMap, fieLdsModel, props, fieldList);
				List<String> valueList = VisualUtils.analysisValueMap(optMap, fieLdsModel, props, fieldList);
				if (labelList.size() == fieldList.size()) {
					for (int k = 0; k < labelList.size(); k++) {
						if (fieldList.get(k).equals(valueList.get(k))) {
							cascaderList.add(labelList.get(k));
						}
					}
					//?????????????????????????????????
					if (keyJsonMap != null && keyJsonMap.get(fieLdsModel.getVModel()) != null) {
						List<String> keyList = JsonUtil.getJsonToList(keyJsonMap.get(fieLdsModel.getVModel()), String.class);
						for (int k = 0; k < labelList.size(); k++) {
							if (keyList.get(k).equals(valueList.get(k))) {
								keyListLast.add(labelList.get(k));
							}
						}
						if (keyListLast.size() == labelList.size()) {
							keyJsonMap.put(fieLdsModel.getVModel(), keyListLast);

						}
					}
					cascaderMap.put("keyJsonMap", keyJsonMap);
					//???????????????????????????
					if (cascaderList.size() == labelList.size()) {
						cascaderMap.put("value", cascaderList);
					}
				}
			}
		}
		return cascaderMap;
	}


	/**
	 * ????????????????????????????????????
	 *
	 * @param fieLdsModel
	 * @param fieldStr
	 * @return
	 */
	public static String treeSelectOperation(FieLdsModel fieLdsModel, String fieldStr) {
		//??????????????????????????????
		if (fieLdsModel.getOptions() != null) {
			List<Map<String, Object>> options = JsonUtil.getJsonToListMap(fieLdsModel.getOptions());
			PropsBeanModel props = JsonUtil.getJsonToBean(fieLdsModel.getProps().getProps(), PropsBeanModel.class);
			String value = props.getValue();
			String label = props.getLabel();
			for (Map<String, Object> optMap : options) {
				if (optMap.get(value).toString().equals(fieldStr)) {
					return optMap.get(label).toString();
				} else if (optMap.get("children") != null) {
					List<Map<String, Object>> anotherOptions = (List<Map<String, Object>>) optMap.get("children");
					String another = treeSelectGetValue(value, label, anotherOptions, fieldStr);
					if (!fieldStr.equals(another)) {
						return another;
					}
				}
			}
		}
		return fieldStr;
	}

	/**
	 * ?????????????????????
	 *
	 * @param value
	 * @param label
	 * @param anotherOptions
	 * @param fieldStr
	 * @return
	 */
	public static String treeSelectGetValue(String value, String label, List<Map<String, Object>> anotherOptions, String fieldStr) {
		for (Map<String, Object> optMap : anotherOptions) {
			if (optMap.get(value).toString().equals(fieldStr)) {
				return optMap.get(label).toString();
			} else if (optMap.get("children") != null) {
				List<Map<String, Object>> childOptions = (List<Map<String, Object>>) optMap.get("children");
				String another = treeSelectGetValue(value, label, childOptions, fieldStr);
				if (StrUtil.isNotEmpty(another)) {
					return another;
				}
			}
		}
		return fieldStr;
	}


	/**
	 * ???????????????????????????
	 *
	 * @return
	 */
	public static List<Map<String, Object>> getRealList(Map<String, Object> keyJsonMap, List<BaseVisualDevModelDataEntity> list, TimeControl timeControl) throws ParseException, ParseException {
		List<Map<String, Object>> realList = new ArrayList<>();
		for (BaseVisualDevModelDataEntity entity : list) {
			Map<String, Object> m2 = JsonUtil.stringToMap(entity.getData());
			if (keyJsonMap != null && keyJsonMap.size() != 0) {
				//?????????????????????????????????????????????????????????
				int i = 0;
				for (Map.Entry<String, Object> entry1 : keyJsonMap.entrySet()) {
					Object m1value = entry1.getValue() == null ? "" : entry1.getValue();
					Object m2value = m2.get(entry1.getKey()) == null ? "" : m2.get(entry1.getKey());
					//?????????map?????????key?????????value??????
					if (!StrUtil.isEmpty(m1value.toString()) && !StrUtil.isEmpty(m2value.toString())) {
						if (m2value.toString().contains(m1value.toString()) && !DateUtil.isValidDate(m2value.toString()) && !entry1.getKey().contains("date")) {
							m2.put("id", entity.getId());
							i++;
						}
						if (timeControl != null) {
							if (!StrUtil.isEmpty(timeControl.getDate()) && timeControl.getDate().contains(entry1.getKey())) {
								//??????????????????
								String valuee = m1value.toString().substring(0, 10);
								if (m2value.toString().contains(valuee)) {
									m2.put("id", entity.getId());
									i++;
								}
							}
							if (!StrUtil.isEmpty(timeControl.getTimeRange()) && timeControl.getTimeRange().contains(entry1.getKey())) {

								List<String> list1 = JsonUtil.getJsonToList(m1value, String.class);
								List<String> list2 = JsonUtil.getJsonToList(m2value, String.class);
								if (list1.size() == 2 && list2.size() == 2) {
									list1.add(0, list1.get(0).substring(0, list1.get(0).length() - 1));
									list2.add(0, list2.get(0).substring(0, list2.get(0).length() - 1));
									SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
									Date dayTimeStart1 = df.parse(list1.get(0));
									Date dayTimeEnd1 = df.parse(list1.get(2));

									Date dayTimeStart2 = df.parse(list2.get(0));
									Date dayTimeEnd2 = df.parse(list2.get(2));
									boolean cont = DateUtil.isOverlap(dayTimeStart1, dayTimeEnd1, dayTimeStart2, dayTimeEnd2);
									if (cont) {
										m2.put("id", entity.getId());
										i++;
									}
								}
							}
							if (!StrUtil.isEmpty(timeControl.getDateRange()) && timeControl.getDateRange().contains(entry1.getKey())) {
								List<String> list1 = JsonUtil.getJsonToList(m1value, String.class);
								List<String> list2 = JsonUtil.getJsonToList(m2value, String.class);
								if (list1.size() == 2 && list2.size() == 2) {
									list1.add(0, list1.get(0).substring(0, list1.get(0).length() - 1));
									list2.add(0, list2.get(0).substring(0, list2.get(0).length() - 1));
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
									if (list1.get(0).length() > 10) {
										df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									}
									Date dayTimeStart1 = df.parse(list1.get(0));
									Date dayTimeEnd1 = df.parse(list2.get(0));

									Date dayTimeStart2 = df.parse(list2.get(0));
									Date dayTimeEnd2 = df.parse(list2.get(2));

									boolean cont = DateUtil.isOverlap(dayTimeStart1, dayTimeEnd1, dayTimeStart2, dayTimeEnd2);
									if (cont) {
										m2.put("id", entity.getId());
										i++;
									}
								}
							}
						}
					}

					if (i == keyJsonMap.size()) {
						realList.add(m2);
					}
				}
			} else {
				m2.put("id", entity.getId());
				realList.add(m2);
			}
		}
		return realList;
	}

	/**
	 * String?????????
	 *
	 * @return
	 */
	public static List<BaseVisualDevModelDataEntity> stringToList(List<FieLdsModel> fieLdsModelList, List<BaseVisualDevModelDataEntity> list) {
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			for (BaseVisualDevModelDataEntity entity : list) {
				Map<String, Object> dataMap = JsonUtil.stringToMap(entity.getData());
				if (BoosKeyConst.UPLOADFZ.equals(fieLdsModel.getConfig().getBoosKey()) || BoosKeyConst.UPLOADIMG.equals(fieLdsModel.getConfig().getBoosKey())) {
					for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
						if (entry.getKey().equals(fieLdsModel.getVModel())) {
							entry.setValue(JsonUtil.getJsonToListMap(entry.getValue().toString()));
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param fieldList
	 * @param fieldStr
	 * @param fieLdsModel
	 * @return
	 */
	public static Object setSelect(List<String> fieldList, String fieldStr, FieLdsModel fieLdsModel) {
		//????????????????????????
		if (fieLdsModel.getSlot() != null && fieLdsModel.getSlot().getOptions() != null) {
			String value = fieLdsModel.getConfig().getProps().getValue();
			String label = fieLdsModel.getConfig().getProps().getLabel();
			//??????????????????
			List<Map<String, Object>> options = JsonUtil.getJsonToListMap(fieLdsModel.getSlot().getOptions());
			if (fieldList != null && fieldList.size() > 0) {
				//??????????????????
				List<String> moreValue = new ArrayList<>();
				for (String fieStr : fieldList) {
					for (Map<String, Object> optMap : options) {
						if (fieStr.equals(optMap.get(value).toString())) {
							moreValue.add(optMap.get(label).toString());
						}
					}
				}
				//??????????????????????????????????????????
				return moreValue;
			} else {
				for (Map<String, Object> optMap : options) {
					if (fieLdsModel.getSlot() != null && fieLdsModel.getSlot().getOptions() != null) {
						if (optMap.get(value) != null && fieldStr.equals(optMap.get(value).toString())) {
							return optMap.get(label).toString();
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * ????????????????????????
	 *
	 * @param value
	 * @param type
	 * @param configModel
	 * @return
	 */
	public static Map<String, Object> relaField(String value, String type, ConfigModel configModel) {
		HashSet<String> keyList = new HashSet<>(16);

		Map<String, Object> allKey = new HashMap<>(16);
		switch (type) {
			//?????????
			case BoosKeyConst.RADIO:
				//?????????
			case BoosKeyConst.SELECT:
				if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
					keyList.add(value);
				}
				break;
			//?????????
			case BoosKeyConst.CHECKBOX:
				if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
					//????????????id
					List<String> add = VisualUtils.analysisField(value);
					if (add.size() > 0) {
						keyList.addAll(add);
					} else {
						keyList.add(value);
					}
				}
				break;
			//??????
			case BoosKeyConst.COMSELECT:
				//??????
			case BoosKeyConst.DEPSELECT:
				if (value.contains(",")) {
					String[] depSelects = value.split(",");
					Collections.addAll(keyList, depSelects);
				} else {
					keyList.add(value);
				}
				break;
			//??????
			case BoosKeyConst.POSSELECT:
				if (value.contains(",")) {
					String[] posSelects = value.split(",");
					Collections.addAll(keyList, posSelects);
				} else {
					keyList.add(value);
				}
				break;
			//??????
			case BoosKeyConst.USERSELECT:
				if (value.contains(",")) {
					String[] userSelects = value.split(",");
					Collections.addAll(keyList, userSelects);
				} else {
					keyList.add(value);
				}
				break;
			//????????????
			case BoosKeyConst.DICSELECT:
				keyList.add(value);
				break;
			//?????????
			case BoosKeyConst.ADDRESS:
				List<String> add = JsonUtil.getJsonToList(value, String.class);
				keyList.addAll(add);
				break;
			default:
		}
		allKey.put(configModel.getBoosKey(), keyList);
		return allKey;
	}

	/**
	 * ????????????????????????
	 *
	 * @param dataMap
	 * @param keyMap
	 * @param type
	 * @param key
	 * @param value
	 * @param configModel
	 * @param model
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> relaFieldValue(Map<String, Object> dataMap, Map<String, Object> keyMap, String type, String key, String value, ConfigModel configModel, FieLdsModel model) throws IOException {
		Map<String, Object> result = new HashMap<>(16);
		switch (type) {
			//?????????
			case BoosKeyConst.RADIO:
				//?????????
			case BoosKeyConst.SELECT:
				if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
					if (keyMap.containsKey(key)) {
						result.put("value", keyMap.get(key));
					}
				}
				if (DataTypeConst.STATIC.equals(configModel.getDataType())) {
					List<Map<String, Object>> modelOpt = JsonUtil.getJsonToListMap(model.getSlot().getOptions());
					for (Map<String, Object> map : modelOpt) {
						if (map.get(configModel.getProps().getValue()).toString().equals(value)) {
							result.put("value", map.get(model.getConfig().getProps().getLabel()).toString());
						}
					}
				}
				if (DataTypeConst.DYNAMIC.equals(configModel.getDataType())) {
					DynamicUtil dynamicUtil = new DynamicUtil();
					dataMap = dynamicUtil.dynamicKeyData(model, dataMap);
					result.put("dataMap", dataMap);
				}
				break;
			//?????????
			case BoosKeyConst.CHECKBOX:
				if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
					//????????????id
					List<String> add = VisualUtils.analysisField(value);
					StringBuilder addName = new StringBuilder();
					if (add.size() > 0) {
						for (String str : add) {
							if (keyMap.containsKey(str)) {
								addName.append(keyMap.get(str));
							}
						}
					} else {
						if (keyMap.containsKey(value)) {
							addName.append(keyMap.get(value));
						}
					}
					if (addName.length() != 0) {
						result.put("value", addName);
					}
				}
				if (DataTypeConst.STATIC.equals(configModel.getDataType())) {
					if (model.getSlot() != null && model.getSlot().getOptions() != null) {
						List<Map<String, Object>> modelOpt = JsonUtil.getJsonToListMap(model.getSlot().getOptions());
						for (Map<String, Object> map : modelOpt) {
							if (map.get(model.getConfig().getProps().getValue()).toString().equals(value)) {
								result.put("value", map.get(model.getConfig().getProps().getLabel()));
							}

						}
					}
				}
				if (DataTypeConst.DYNAMIC.equals(configModel.getDataType())) {
					//?????????????????????????????????????????????????????????
					DynamicUtil dynamicUtil = new DynamicUtil();
					dataMap = dynamicUtil.dynamicKeyData(model, dataMap);
					result.put("dataMap", dataMap);
				}
				break;
			//??????
			case BoosKeyConst.COMSELECT:
				//??????
			case BoosKeyConst.DEPSELECT:
				if (value.contains(",")) {
					String[] depSelects = value.split(",");
					String[] newDepSelects = new String[depSelects.length];
					int i = 0;
					for (String depSelect : depSelects) {
						if (keyMap.containsKey(depSelect)) {
							newDepSelects[i] = String.valueOf(keyMap.get(depSelect));
						}
						i++;
					}
					result.put("value", newDepSelects);
				} else {
					if (keyMap.containsKey(value)) {
						result.put("value", keyMap.get(value));
					}
				}
				break;
			//??????
			case BoosKeyConst.POSSELECT:
				if (value.contains(",")) {
					String[] posSelects = value.split(",");
					String[] newposSelects = new String[posSelects.length];
					int i = 0;
					for (String posSelect : posSelects) {
						if (keyMap.containsKey(posSelect)) {
							newposSelects[i] = String.valueOf(keyMap.get(posSelect));
						}
						i++;
					}
					result.put("value", newposSelects);
				} else {
					if (keyMap.containsKey(value)) {
						result.put("value", keyMap.get(value));
					}
				}
				break;
			//??????
			case BoosKeyConst.USERSELECT:
				if (value.contains(",")) {
					String[] userSelects = value.split(",");
					String[] newuserSelects = new String[userSelects.length];
					int i = 0;
					for (String userSelect : userSelects) {
						if (keyMap.containsKey(userSelect)) {
							newuserSelects[i] = String.valueOf(keyMap.get(userSelect));
						}
						i++;
					}
					result.put("value", newuserSelects);
				} else {
					if (keyMap.containsKey(value)) {
						result.put("value", keyMap.get(value));
					}
				}
				break;
			//????????????
			case BoosKeyConst.DICSELECT:
				if (keyMap.containsKey(value)) {
					result.put("value", keyMap.get(value));
				}
				break;
			//?????????
			case BoosKeyConst.ADDRESS:
				List<String> add = JsonUtil.getJsonToList(value, String.class);
				StringBuilder addName = new StringBuilder();
				for (String str : add) {
					if (keyMap.containsKey(str)) {
						addName.append(keyMap.get(str)).append("/");
					}
				}
				if (addName.length() != 0) {
					addName.deleteCharAt(addName.length() - 1);
					result.put("value", addName);
				}
				break;
			//????????????
			case BoosKeyConst.TIMERANGE:
				JSONArray jsonArrayTime = JsonUtil.getJsonToJsonArray(String.valueOf(value));
				jsonArrayTime = DateUtil.addCon(jsonArrayTime, BoosKeyConst.TIMERANGE, "HH:mm:ss");
				result.put("value", jsonArrayTime.toJSONString());
				break;
			//????????????
			case BoosKeyConst.DATE:
				DateTimeFormatter ftf = DateTimeFormatter.ofPattern(model.getFormat());
				long time;
				try {
					time = Long.parseLong(String.valueOf(value));
					String values = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
					result.put("value", values);
				} catch (Exception e) {
					result.put("value", value);
				}
				break;
			//????????????
			case BoosKeyConst.DATERANGE:
				JSONArray jsonArray = JsonUtil.getJsonToJsonArray(String.valueOf(dataMap.get(key)));
				jsonArray = DateUtil.addCon(jsonArray, BoosKeyConst.DATERANGE, model.getFormat());
				result.put("value", jsonArray.toJSONString());
				break;
			default:
		}
		return result;
	}

	/**
	 * @param mapList
	 * @return List<Map < String, Object>>
	 * @Date 21:51 2020/11/11
	 * @Description ???map????????????key???????????????
	 */
	public static List<Map<String, Object>> toLowerKeyList(List<Map<String, Object>> mapList) {
		List<Map<String, Object>> newMapList = new ArrayList<>();
		for (Map<String, Object> map : mapList) {
			Map<String, Object> resultMap = new HashMap<>(16);
			Set<String> sets = map.keySet();
			for (String key : sets) {
				resultMap.put(key.toLowerCase(), map.get(key));
			}
			newMapList.add(resultMap);
		}
		return newMapList;
	}


	/**
	 * @param map
	 * @return java.util.Map<java.lang.String, java.lang.String>
	 * @Description ???map????????????key???????????????
	 */
	public static Map<String, Object> toLowerKey(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<>(16);
		Set<String> sets = map.keySet();
		for (String key : sets) {
			resultMap.put(key.toLowerCase(), map.get(key));
		}
		return resultMap;
	}

	/**
	 * @param entity
	 * @return
	 * @Description ???????????????????????????
	 */
	public static void delete(BaseVisualDevEntity entity) {
		//???????????????????????????????????????????????????
		if (StrUtil.isNotEmpty(entity.getColumnData())) {
			//app??????????????????
			if (entity.getType() != 5) {
				Map<String, Object> columnDataMap = JsonUtil.stringToMap(entity.getColumnData());
				List<FieLdsModel> columnField = JsonUtil.getJsonToList(columnDataMap.get("searchList"), FieLdsModel.class);
				columnDataMap.put("searchList", columnField);
				entity.setColumnData(JsonUtil.getObjectToString(columnDataMap));
			}
		}
		Map<String, Object> formData = JsonUtil.stringToMap(entity.getFormData());
		List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields"), FieLdsModel.class);
		formData.put("fields", modelList);
		entity.setFormData(JsonUtil.getObjectToString(formData));
	}


	/**
	 * @param entity
	 * @return
	 * @Description ???????????????????????????
	 */
	public static BaseVisualDevEntity changeType(BaseVisualDevEntity entity) {

		List<Map<String, Object>> list = JsonUtil.getJsonToListMap(entity.getRefTables());
		if (list.size() > 0) {
			for (Map<String, Object> tableModel : list) {
				if (tableModel.get("fields") != null) {
					List<TableFields> fields = JsonUtil.getJsonToList(JsonUtil.getObjectToString(tableModel.get("fields")), TableFields.class);
					for (TableFields tableField : fields) {
						String feildD = tableField.getField();
						String feildL = tableField.getField().toLowerCase();
						if (entity.getRefTables() != null) {
							entity.setRefTables(entity.getRefTables().replaceAll(feildD, feildL));
						}
						if (entity.getColumnData() != null) {
							entity.setColumnData(entity.getColumnData().replaceAll(feildD, feildL));
						}
						if (entity.getFormData() != null) {
							entity.setFormData(entity.getFormData().replaceAll(feildD, feildL));
						}
					}
				}
			}
		}
		return entity;
	}

	/**
	 * @param keyName
	 * @param dataEntityList
	 * @return
	 */
	public static List<BaseVisualDevModelDataEntity> setDataId(String keyName, List<BaseVisualDevModelDataEntity> dataEntityList) {
		keyName = keyName.toLowerCase();
		for (BaseVisualDevModelDataEntity entity : dataEntityList) {
			Map<String, Object> dataMap = JsonUtil.stringToMap(entity.getData());
			if (dataMap.get(keyName) != null) {
				entity.setId(Convert.toLong(dataMap.get(keyName)));
			}
		}
		return dataEntityList;
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param keyFlag
	 * @param feilds
	 * @param mainTable
	 * @param pKeyName
	 * @param columnData
	 * @return
	 */
	public static String getListResultSql(Boolean keyFlag, String feilds, String mainTable, String pKeyName, ColumnDataModel columnData) {
		//dataSourceUtil?????????
		init();
		StringBuilder sql = new StringBuilder();
		if (dataSourceUtil.getDataType().toLowerCase().equals(DbType.MYSQL.getDb())) {
			if (keyFlag) {
				sql.append("select ").append(feilds).append(" from").append(" ").append(mainTable).append(" ORDER BY ");
			} else {
				sql.append("select ").append(pKeyName).append(",").append(feilds).append(" from").append(" ").append(mainTable).append(" ORDER BY ");
			}
			if (!StrUtil.isEmpty(columnData.getDefaultSidx())) {
				sql.append(columnData.getDefaultSidx()).append(" ").append(columnData.getSort());
			} else {
				sql.append(pKeyName).append(" ").append(columnData.getSort());
			}
		} else if (dataSourceUtil.getDataType().toLowerCase().equals(DbType.SQL_SERVER.getDb())) {
			if (keyFlag) {
				sql.append("select ").append(feilds).append(" from").append(" ").append(mainTable).append(" ORDER BY ");
			} else {
				sql.append("select ").append(pKeyName).append(",").append(feilds).append(" from").append(" ").append(mainTable).append(" ORDER BY ");
			}
			if (!StrUtil.isEmpty(columnData.getDefaultSidx())) {
				sql.append(columnData.getDefaultSidx()).append(" ").append(columnData.getSort());
			} else {
				sql.append(pKeyName).append(" ").append(columnData.getSort());
			}
		} else if (dataSourceUtil.getDataType().toLowerCase().equals(DbType.ORACLE.getDb())) {
			if (keyFlag) {
				sql.append("select ").append(feilds).append(" from").append(" ").append(mainTable).append(" ORDER BY ");
			} else {
				sql.append("select ").append(pKeyName).append(",").append(feilds).append(" from").append(" ").append(mainTable).append(" ORDER BY ");
			}
			if (!StrUtil.isEmpty(columnData.getDefaultSidx())) {
				sql.append(columnData.getDefaultSidx()).append(" ").append(columnData.getSort());
			} else {
				sql.append(pKeyName).append(" ").append(columnData.getSort());
			}

		}
		return sql.toString();
	}

}
