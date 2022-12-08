package com.sinosoft.ie.booster.visualdev.util.onlinedev;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.visualdev.constant.BoosKeyConst;
import com.sinosoft.ie.booster.visualdev.constant.DataTypeConst;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevModelDataEntity;
import com.sinosoft.ie.booster.visualdev.model.base.ColumnDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.Template6.ColumnListField;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.OnlineDevData;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.TimeControl;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VisualdevModelDataInfoVO;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.slot.SlotModel;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevModelDataService;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevService;
import com.sinosoft.ie.booster.visualdev.util.CacheKeyUtil;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.RedisUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.DynamicUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.VisualUtils;
import lombok.Cleanup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 处理KeyData
 *
 * @author booster开发平台组
 * @since 2021年3月15日10:16:01
 */
public class KeyDataUtil {

	private static RedisUtil redisUtil;
	private static BaseVisualDevModelDataService visualDevModelDataService;
	private static BaseVisualDevService visualDevService;
	private static RemotePositionService positionService;


	//初始化

	public static void init() {
		redisUtil = SpringContextHolder.getBean(RedisUtil.class);
		visualDevModelDataService = SpringContextHolder.getBean(BaseVisualDevModelDataService.class);
		visualDevService = SpringContextHolder.getBean(BaseVisualDevService.class);
		positionService = SpringContextHolder.getBean(RemotePositionService.class);
	}


	/**
	 * 获取有表list
	 *
	 * @param list
	 * @param mainTable
	 * @param modelList
	 * @param columnData
	 * @return
	 * @throws DataException
	 * @throws SQLException
	 */
	public static List<BaseVisualDevModelDataEntity> getHasTableList(List<BaseVisualDevModelDataEntity> list, String mainTable, List<ColumnListField> modelList, ColumnDataModel columnData) throws DataException, SQLException {
		@Cleanup Connection conn = VisualUtils.getTableConn();
		//获取主键
		String pKeyName = VisualUtils.getpKey(conn, mainTable);

		StringBuilder feilds = new StringBuilder();
		for (ColumnListField model : modelList) {
			feilds.append(model.getProp()).append(",");
		}
		if (modelList.size() > 0) {
			feilds.deleteCharAt(feilds.length() - 1);
		}

		String feildsBool = feilds.toString().toLowerCase().trim();
		//判断字段是否存在主键
		Boolean keyFlag = VisualUtils.existKey(feildsBool, pKeyName);

		//获取查询语句
		String listResultSql = VisualUtils.getListResultSql(keyFlag, feilds.toString(), mainTable, pKeyName, columnData);
		//获取有表列表数据
		list = VisualUtils.getTableDataList(conn, listResultSql, pKeyName);
		//Id赋值
		list = VisualUtils.setDataId(pKeyName, list);
		return list;
	}


	/**
	 * 转换分组数据
	 *
	 * @param columnDataModel
	 * @param realList
	 * @return
	 */
	public static List<Map<String, Object>> changeGroupDataList(ColumnDataModel columnDataModel, List<Map<String, Object>> realList) {
		List<Map<String, Object>> columnList = JsonUtil.getJsonToListMap(columnDataModel.getColumnList());
		String firstField = "";
		for (Map<String, Object> modelMap : columnList) {
			if (!columnDataModel.getGroupField().equals(modelMap.get("prop").toString())) {
				firstField = modelMap.get("prop").toString();
				break;
			}
		}
		HashSet<String> parents = new HashSet<>(16);
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map<String, Object> realmap : realList) {
			parents.add(String.valueOf(realmap.get(columnDataModel.getGroupField())));
		}
		for (String parent : parents) {
			Map<String, Object> dataMap = new HashMap<>(16);
			List<Map<String, Object>> resultMapOneList = realList.stream().filter(t -> parent.equals(String.valueOf(t.get(columnDataModel.getGroupField())))).collect(Collectors.toList());
			for (Map<String, Object> resultMapOneMap : resultMapOneList) {
				resultMapOneMap.remove(columnDataModel.getGroupField());
			}
			dataMap.put(firstField, parent);
			dataMap.put("top", true);
			dataMap.put("children", resultMapOneList);
			resultList.add(dataMap);

		}
		return resultList;
	}


	/**
	 * 详情信息需要进行数据转换
	 *
	 * @param id
	 * @param modelId
	 * @return
	 * @throws DataException
	 * @throws ParseException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static VisualdevModelDataInfoVO infoWithDataChange(Long id, Long modelId) throws DataException, ParseException, IOException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		//有表
		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			return visualDevModelDataService.tableInfoDataChange(id, visualdevEntity);
		}
		//无表
		return visualDevModelDataService.infoDataChange(id, visualdevEntity);
	}


	/**
	 * 将关键字key查询传输的id转换成名称，还有动态数据id成名称
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getKeyData(List<FieLdsModel> formData, Map<String, Object> keyJsonMap, List<BaseVisualDevModelDataEntity> list, Long visualDevId) throws IOException, ParseException, DataException, SQLException {
		init();
		//创建时间类关键词查询对应的字段实体
		TimeControl timeControl = new TimeControl();
		//存储查询条件
		Map<String, Object> dicKeyMap = new HashMap<>(16);
		Map<String, Object> orgKeyMap = new HashMap<>(16);
		Map<String, Object> posKeyMap = new HashMap<>(16);
		Map<String, Object> userKeyMap = new HashMap<>(16);
		Map<String, Object> provKeyMap = new HashMap<>(16);
		Map<String, Object> dicTypeKeyMap = new HashMap<>(16);
		Map<String, Object> visualKeyMap = new HashMap<>(16);

		//存储查询结果
		HashSet<String> dicKeyList = new HashSet<>(16);
		HashSet<String> orgKeyList = new HashSet<>(16);
		HashSet<String> posKeyList = new HashSet<>(16);
		HashSet<String> userKeyList = new HashSet<>(16);
		HashSet<String> provKeyList = new HashSet<>(16);
		HashSet<String> dicTypeKeyList = new HashSet<>(16);
		HashSet<String> visualKeyList = new HashSet<>(16);
		//将查询map也转换成数据放进去进行数据转换
		if (keyJsonMap != null) {
			BaseVisualDevModelDataEntity keyEntity = new BaseVisualDevModelDataEntity();
			keyEntity.setId(Long.valueOf(DataTypeConst.KEY_JSON_MAP));
			keyEntity.setData(JsonUtilEx.getObjectToString(keyJsonMap));
			list.add(keyEntity);
		}
		//记录将要去数据库查询的所有id
		for (BaseVisualDevModelDataEntity entity : list) {
			//数据库保存的F_Data
			Map<String, Object> dataMap = JsonUtil.stringToMap(entity.getData());
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				for (FieLdsModel model : formData) {
					if (model != null && model.getVModel() != null && entry.getValue() != null) {
						ConfigModel configModel = model.getConfig();
						String field = model.getVModel();
						if (entry.getKey().equals(field)) {
							String type = configModel.getBoosKey();
							switch (type) {
								//单选框
								case BoosKeyConst.RADIO:
									//下拉框
								case BoosKeyConst.SELECT:
									if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
										dicKeyList.add(String.valueOf(entry.getValue()));
									}
									break;
								//复选框
								case BoosKeyConst.CHECKBOX:
									if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
										//字段数据id
										List<String> add = VisualUtils.analysisField(String.valueOf(entry.getValue()));
										String addStr = String.valueOf(entry.getValue());
										if (add.size() > 0) {
											dicKeyList.addAll(add);
										} else {
											dicKeyList.add(addStr);
										}
									}
									break;
								//公司
								case BoosKeyConst.COMSELECT:
									//部门
								case BoosKeyConst.DEPSELECT:
									if (String.valueOf(entry.getValue()).contains(",")) {
										String[] depSelects = String.valueOf(entry.getValue()).split(",");
										orgKeyList.addAll(Arrays.asList(depSelects));
									} else {
										orgKeyList.add(String.valueOf(entry.getValue()));
									}
									break;
								//岗位
								case BoosKeyConst.POSSELECT:
									if (String.valueOf(entry.getValue()).contains(",")) {
										String[] posSelects = String.valueOf(entry.getValue()).split(",");
										posKeyList.addAll(Arrays.asList(posSelects));
									} else {
										posKeyList.add(String.valueOf(entry.getValue()));
									}
									break;
								//用户
								case BoosKeyConst.USERSELECT:
									if (String.valueOf(entry.getValue()).contains(",")) {
										String[] userSelects = String.valueOf(entry.getValue()).split(",");
										userKeyList.addAll(Arrays.asList(userSelects));
									} else {
										userKeyList.add(String.valueOf(entry.getValue()));
									}
									break;
								//数据字典
								case BoosKeyConst.DICSELECT:
									dicTypeKeyList.add(String.valueOf(entry.getValue()));
									break;
								//省市区
								case BoosKeyConst.ADDRESS:
									List<String> add = JsonUtil.getJsonToList(String.valueOf(entry.getValue()), String.class);
									provKeyList.addAll(add);
									break;
								//关联表单
								case BoosKeyConst.RELATIONFORM:
									if (StrUtil.isNotEmpty(String.valueOf(entry.getValue()))) {
										visualKeyList.add(entry.getValue() + "_" + model.getModelId() + "_" + entry.getKey());
									}
									break;
								default:
							}
						}
					}
				}
			}
		}

		//从数据库或redis获取所有数据
		if (!redisUtil.exists(CacheKeyUtil.VISIUALDATA + visualDevId)) {
			Map<String, Object> dataMap = new HashMap<>(16);
			if (dicKeyList.size() > 0) {
				dataMap.put(KeyDataConst.DICKEYMAP, dicKeyMap);
			}
			if (dicTypeKeyList.size() > 0) {
				dataMap.put(KeyDataConst.DICTYPEKEYMAP, dicTypeKeyMap);
			}
			if (userKeyList.size() > 0) {
				dataMap.put(KeyDataConst.USERKEYMAP, userKeyMap);
			}
			if (orgKeyList.size() > 0) {
				dataMap.put(KeyDataConst.ORGKEYMAP, orgKeyMap);
			}
			if (provKeyList.size() > 0) {
				dataMap.put(KeyDataConst.PROVKEYMAP, provKeyMap);
			}
			if (posKeyList.size() > 0) {
				List<SysPositionEntity> positionEntities = positionService.getListByUserName(String.join(",", posKeyList)).getData();
				for (SysPositionEntity entity : positionEntities) {
					posKeyMap.put(String.valueOf(entity.getId()), entity.getFullName());
				}
				dataMap.put(KeyDataConst.POSKEYMAP, posKeyMap);
			}
			if (visualKeyList.size() > 0) {
				for (String visualId : visualKeyList) {
					String[] data = visualId.split("_");
					if (data.length == 3) {
						VisualdevModelDataInfoVO visualdevModelDataInfoVO = infoWithDataChange(Convert.toLong(data[0]), Convert.toLong(data[1]));
						if (visualdevModelDataInfoVO != null) {
							Map<String, Object> viDataMap = JsonUtil.stringToMap(visualdevModelDataInfoVO.getData());
							for (FieLdsModel fieLdsModel : formData) {
								for (Map.Entry<String, Object> entry : viDataMap.entrySet()) {
									if ((data[1] + entry.getKey()).equals(fieLdsModel.getModelId() + fieLdsModel.getRelationField())) {
										visualKeyMap.put(data[2] + "_" + visualdevModelDataInfoVO.getId(), entry.getValue());
									}
								}
							}
						}
					}
				}
				dataMap.put(KeyDataConst.VISUALKEYMAP, visualKeyMap);
			}
			redisUtil.insert(CacheKeyUtil.VISIUALDATA + visualDevId, JsonUtilEx.getObjectToString(dataMap), 300);
		} else {
			Map<String, Object> dataMap = JsonUtil.stringToMap(redisUtil.getString(CacheKeyUtil.VISIUALDATA + visualDevId).toString());
			if (dataMap.containsKey(KeyDataConst.DICKEYMAP)) {
				dicKeyMap.putAll(JsonUtil.entityToMap(dataMap.get(KeyDataConst.DICKEYMAP)));
			}
			if (dataMap.containsKey(KeyDataConst.DICTYPEKEYMAP)) {
				dicTypeKeyMap.putAll(JsonUtil.entityToMap(dataMap.get(KeyDataConst.DICTYPEKEYMAP)));
			}
			if (dataMap.containsKey(KeyDataConst.USERKEYMAP)) {
				userKeyMap.putAll(JsonUtil.entityToMap(dataMap.get(KeyDataConst.USERKEYMAP)));
			}
			if (dataMap.containsKey(KeyDataConst.ORGKEYMAP)) {
				orgKeyMap.putAll(JsonUtil.entityToMap(dataMap.get(KeyDataConst.ORGKEYMAP)));
			}
			if (dataMap.containsKey(KeyDataConst.PROVKEYMAP)) {
				provKeyMap.putAll(JsonUtil.entityToMap(dataMap.get(KeyDataConst.PROVKEYMAP)));
			}
			if (dataMap.containsKey(KeyDataConst.POSKEYMAP)) {
				posKeyMap.putAll(JsonUtil.entityToMap(dataMap.get(KeyDataConst.POSKEYMAP)));
			}
			if (dataMap.containsKey(KeyDataConst.VISUALKEYMAP)) {
				visualKeyMap.putAll(JsonUtil.entityToMap(dataMap.get(KeyDataConst.VISUALKEYMAP)));
			}
		}
		//数据赋值
		for (BaseVisualDevModelDataEntity entity : list) {
			Map<String, Object> relaMap = new HashMap<>(16);
			//数据库保存的F_Data
			Map<String, Object> dataMap = JsonUtil.stringToMap(entity.getData());
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				for (FieLdsModel model : formData) {
					if (model != null && model.getVModel() != null && entry.getValue() != null) {
						ConfigModel configModel = model.getConfig();
						String field = model.getVModel();
						if (entry.getKey().equals(field)) {
							String type = configModel.getBoosKey();
							switch (type) {
								//开关
								case BoosKeyConst.SWITCH:
									if ("1".equals(String.valueOf(entry.getValue()))) {
										dataMap.put(field, "开");
									} else {
										dataMap.put(field, "关");
									}
									//单选框
								case BoosKeyConst.RADIO:
									//下拉框
								case BoosKeyConst.SELECT:
									if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
										if (dicKeyMap.containsKey(String.valueOf(entry.getValue()))) {
											dataMap.put(field, dicKeyMap.get(String.valueOf(entry.getValue())));
										}
									}
									if (DataTypeConst.STATIC.equals(configModel.getDataType())) {
										SlotModel slotModel = model.getSlot();
										if (slotModel != null) {
											List<Map<String, Object>> modelOpt = JsonUtil.getJsonToListMap(slotModel.getOptions());
											for (Map<String, Object> map : modelOpt) {
												if (map.get(model.getConfig().getProps().getValue()).toString().equals(dataMap.get(field).toString())) {
													dataMap.put(field, map.get(model.getConfig().getProps().getLabel()).toString());
												}
											}
										}
									}
									if (DataTypeConst.DYNAMIC.equals(configModel.getDataType())) {
										DynamicUtil dynamicUtil = new DynamicUtil();
										dataMap = dynamicUtil.dynamicKeyData(model, dataMap);
									}
									break;
								//复选框
								case BoosKeyConst.CHECKBOX:
									if (DataTypeConst.DICTIONARY.equals(configModel.getDataType())) {
										//字段数据id
										List<String> add = VisualUtils.analysisField(String.valueOf(entry.getValue()));
										String addStr = String.valueOf(entry.getValue());
										StringBuilder addName = new StringBuilder();
										if (add.size() > 0) {
											for (String str : add) {
												if (dicKeyMap.containsKey(str)) {
													addName.append(dicKeyMap.get(str));
												}
											}
										} else {
											if (dicKeyMap.containsKey(addStr)) {
												addName.append(dicKeyMap.get(addStr));
											}
										}
										if (addName.length() != 0) {
											dataMap.put(field, addName);
										}
									}
									if (DataTypeConst.STATIC.equals(configModel.getDataType())) {
										if (model.getSlot() != null && model.getSlot().getOptions() != null) {
											List<Map<String, Object>> modelOpt = JsonUtil.getJsonToListMap(model.getSlot().getOptions());
											for (Map<String, Object> map : modelOpt) {
												if (map.get(model.getConfig().getProps().getValue()).toString().equals(dataMap.get(field).toString())) {
													dataMap.put(field, map.get(model.getConfig().getProps().getLabel()).toString());
												}

											}
										}
									}
									if (DataTypeConst.DYNAMIC.equals(configModel.getDataType())) {
										//获取最新远端数据转换远端数据查询关键词
										DynamicUtil dynamicUtil = new DynamicUtil();
										dataMap = dynamicUtil.dynamicKeyData(model, dataMap);
									}
									break;
								//公司
								case BoosKeyConst.COMSELECT:
									//部门
								case BoosKeyConst.DEPSELECT:
									if (String.valueOf(entry.getValue()).contains(",")) {
										String[] depSelects = String.valueOf(entry.getValue()).split(",");
										String[] newDepSelects = new String[depSelects.length];
										int i = 0;
										for (String depSelect : depSelects) {
											if (orgKeyMap.containsKey(depSelect)) {
												newDepSelects[i] = String.valueOf(orgKeyMap.get(depSelect));
											}
											i++;
										}
										dataMap.put(field, newDepSelects);
									} else {
										String str = String.valueOf(entry.getValue());
										if (orgKeyMap.containsKey(str)) {
											dataMap.put(field, orgKeyMap.get(str));
										}
									}
									break;
								//岗位
								case BoosKeyConst.POSSELECT:
									if (String.valueOf(entry.getValue()).contains(",")) {
										String[] posSelects = String.valueOf(entry.getValue()).split(",");
										String[] newposSelects = new String[posSelects.length];
										int i = 0;
										for (String posSelect : posSelects) {
											if (posKeyMap.containsKey(posSelect)) {
												newposSelects[i] = String.valueOf(posKeyMap.get(posSelect));
											}
											i++;
										}
										dataMap.put(field, newposSelects);
									} else {
										String str = String.valueOf(entry.getValue());
										if (posKeyMap.containsKey(str)) {
											dataMap.put(field, posKeyMap.get(str));
										}
									}
									break;
								//用户
								case BoosKeyConst.USERSELECT:
									if (String.valueOf(entry.getValue()).contains(",")) {
										String[] userSelects = String.valueOf(entry.getValue()).split(",");
										String[] newuserSelects = new String[userSelects.length];
										int i = 0;
										for (String userSelect : userSelects) {
											if (userKeyMap.containsKey(userSelect)) {
												newuserSelects[i] = String.valueOf(userKeyMap.get(userSelect));
											}
											i++;
										}
										dataMap.put(field, newuserSelects);
									} else {
										String str = String.valueOf(entry.getValue());
										if (userKeyMap.containsKey(str)) {
											dataMap.put(field, userKeyMap.get(str));
										}
									}
									break;
								//数据字典
								case BoosKeyConst.DICSELECT:
									if (dicTypeKeyMap.containsKey(String.valueOf(entry.getValue()))) {
										dataMap.put(field, dicTypeKeyMap.get(String.valueOf(entry.getValue())));
									}
									break;
								//省市区
								case BoosKeyConst.ADDRESS:
									List<String> add = JsonUtil.getJsonToList(String.valueOf(entry.getValue()), String.class);
									StringBuilder addName = new StringBuilder();
									for (String str : add) {
										if (provKeyMap.containsKey(str)) {
											addName.append(provKeyMap.get(str)).append("/");
										}
									}
									if (addName.length() != 0) {
										addName.deleteCharAt(addName.length() - 1);
										dataMap.put(field, addName);
									}
									break;
								//时间范围
								case BoosKeyConst.TIMERANGE:
									JSONArray jsonArrayTime = JsonUtil.getJsonToJsonArray(String.valueOf(dataMap.get(field)));
									jsonArrayTime = DateUtil.addCon(jsonArrayTime, BoosKeyConst.TIMERANGE, "HH:mm:ss");
									dataMap.put(field, jsonArrayTime);
									timeControl.setTimeRange(timeControl.getTimeRange() + field);
									break;
								//日期选择
								case BoosKeyConst.DATE:
									DateTimeFormatter ftf = DateTimeFormatter.ofPattern(model.getFormat());
									long time;
									try {
										time = Long.parseLong(String.valueOf(dataMap.get(field)));
										String value = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("+8")));
										dataMap.put(field, value);
										timeControl.setDate(timeControl.getDate() + field);
									} catch (Exception e) {
										dataMap.put(field, dataMap.get(field));
									}
									break;
								//日期范围
								case BoosKeyConst.DATERANGE:
									JSONArray jsonArray = JsonUtil.getJsonToJsonArray(String.valueOf(dataMap.get(field)));
									jsonArray = DateUtil.addCon(jsonArray, BoosKeyConst.DATERANGE, model.getFormat());
									dataMap.put(field, jsonArray);
									timeControl.setDateRange(timeControl.getDateRange() + field);
									break;
								//关联表单
								case BoosKeyConst.RELATIONFORM:
									Object mapKey = visualKeyMap.get(entry.getKey() + "_" + entry.getValue());
									if (mapKey != null) {
										relaMap.put(field + OnlineDevData.INFO_ID, String.valueOf(entry.getValue()));
										dataMap.put(field, mapKey);
									}
									break;
								default:

							}
						}
					}

				}
			}
			dataMap.putAll(relaMap);
			entity.setData(JsonUtilEx.getObjectToString(dataMap));
		}
		if (keyJsonMap != null) {
			BaseVisualDevModelDataEntity entity = list.stream().filter(x -> DataTypeConst.KEY_JSON_MAP.equals(String.valueOf(x.getId()))).findFirst().get();
			list.remove(entity);
			keyJsonMap = JsonUtil.stringToMap(entity.getData());
		}
		Map<String, Object> map = new HashMap<>(16);
		map.put(DataTypeConst.LIST, list);
		map.put(DataTypeConst.TIME_CONTROL, timeControl);
		map.put(DataTypeConst.KEY_JSON_MAP, keyJsonMap);
		return map;
	}


}
