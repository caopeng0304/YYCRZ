package com.sinosoft.ie.booster.workflow.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.constant.BoosKeyConst;
import com.sinosoft.ie.booster.visualdev.feign.DataInterFaceApi;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormAllModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormColumnModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormEnum;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VMDEnum;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.props.PropsBeanModel;
import com.sinosoft.ie.booster.visualdev.util.ConfigValueUtil;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.JdbcUtil;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 在线工作流开发
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Component
public class FlowDataUtil {

	@Autowired
	private DataSourceUtil dataSourceUtil;
	@Autowired
	private ConfigValueUtil configValueUtil;
	@Autowired
	private RemoteDeptService remoteDeptService;
	@Autowired
	private RemoteBillRuleService billRuleApi;
	@Autowired
	private DataInterFaceApi dataInterFaceApi;
	@Autowired
	private RemotePositionService positionApi;

	/**
	 * 获取有表的数据库连接
	 *
	 * @return
	 */
	private Connection getTableConn() {
		return JdbcUtil.getConn(dataSourceUtil.getUserName(), dataSourceUtil.getPassword(), dataSourceUtil.getUrl(), dataSourceUtil.getDriverClassName());
	}

	/**
	 * 获取有子表数据
	 *
	 * @param sql sql语句
	 * @return
	 * @throws DataException
	 */
	private List<Map<String, Object>> getTableList(Connection conn, String sql) throws DataException {
		ResultSet rs = JdbcUtil.query(conn, sql);
		List<Map<String, Object>> dataList = JdbcUtil.convertListString(rs);
		List<Map<String, Object>> childData = new ArrayList<>();
		for (Map<String, Object> data : dataList) {
			Map<String, Object> child = new HashMap<>(16);
			for (String key : data.keySet()) {
				child.put(key.toLowerCase(), data.get(key));
			}
			childData.add(child);
		}
		return childData;
	}

	/**
	 * 获取主表数据
	 *
	 * @param sql sql语句
	 * @return
	 * @throws DataException
	 */
	private Map<String, Object> getMast(Connection conn, String sql) throws DataException {
		ResultSet rs = JdbcUtil.query(conn, sql);
		Map<String, Object> mast = JdbcUtil.convertMapString(rs);
		Map<String, Object> mastData = new HashMap<>(16);
		for (String key : mast.keySet()) {
			mastData.put(key.toLowerCase(), mast.get(key));
		}
		return mastData;
	}

	/**
	 * 返回主键名称
	 *
	 * @param conn
	 * @param mainTable
	 * @return
	 */
	public String getPKey(Connection conn, String mainTable) throws SQLException {
		String pKeyName = "id";
		//catalog 数据库名
		String catalog = conn.getCatalog();
		ResultSet primaryKeyResultSet = conn.getMetaData().getPrimaryKeys(catalog, null, mainTable);
		while (primaryKeyResultSet.next()) {
			pKeyName = primaryKeyResultSet.getString("COLUMN_NAME");
		}
		primaryKeyResultSet.close();
		return pKeyName;
	}

	//--------------------------------------------信息-----------------------------------------------------

	/**
	 * 详情查询
	 **/
	public Map<String, Object> info(List<FieLdsModel> fieLdslist, FlowTaskEntity entity, List<FlowTableModel> tableList, boolean convert) throws SQLException, DataException {
		List<FormAllModel> formAllModel = new ArrayList<>();
		//递归遍历模板
		FormCloumnUtil.recursionForm(fieLdslist, formAllModel);
		//处理好的数据
		Map<String, Object> result;
		if (tableList.size() > 0) {
			result = tableInfo(entity, tableList, formAllModel, convert);
		} else {
			Map<String, Object> dataMap = JsonUtil.stringToMap(entity.getFlowFormContentJson());
			result = info(dataMap, formAllModel, convert);
		}
		return result;
	}

	/**
	 * 有表详情
	 **/
	private Map<String, Object> tableInfo(FlowTaskEntity entity, List<FlowTableModel> tableList, List<FormAllModel> formAllModel, boolean convert) throws SQLException, DataException {
		//处理好的数据
		Map<String, Object> result = new HashMap<>(16);
		List<SysPositionEntity> mapList = positionApi.getListAll().getData();
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> tableForm = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		Long mainId = entity.getId();
		Connection conn = getTableConn();
		String mastTableName = tableList.stream().filter(t -> "1".equals(t.getTypeId())).findFirst().get().getTable();
		List<String> mastFile = mastForm.stream().map(t -> t.getFormColumnModel().getFieLdsModel().getVModel()).filter(StrUtil::isNotEmpty).collect(Collectors.toList());
		String pKeyName = getPKey(conn, mastTableName);
		//主表数据
		String mastInfo = " select " + String.join(",", mastFile) + " from " + mastTableName + " where " + pKeyName + " = '" + mainId + "'";
		Map<String, Object> mastData = getMast(conn, mastInfo);
		for (String key : mastData.keySet()) {
			FormAllModel model = mastForm.stream().filter(t -> key.equals(t.getFormColumnModel().getFieLdsModel().getVModel())).findFirst().orElse(null);
			if (model != null) {
				FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
				String format = fieLdsModel.getFormat();
				String boosKey = fieLdsModel.getConfig().getBoosKey();
				Object data = mastData.get(key);
				data = info(boosKey, data, mapList);
				data = infoTable(boosKey, data, format);
				if (convert) {
					data = converData(fieLdsModel, data);
				}
				result.put(key, data);
			}
		}
		//子表数据
		if (!convert) {
			List<FlowTableModel> tableListAll = tableList.stream().filter(t -> !"1".equals(t.getTypeId())).collect(Collectors.toList());
			for (FlowTableModel tableModel : tableListAll) {
				String tableName = tableModel.getTable();
				FormAllModel childModel = tableForm.stream().filter(t -> tableName.equalsIgnoreCase(t.getChildList().getTableName())).findFirst().orElse(null);
				if (childModel != null) {
					String childKey = childModel.getChildList().getTableModel();
					List<String> childFile = childModel.getChildList().getChildList().stream().map(t -> t.getFieLdsModel().getVModel()).collect(Collectors.toList());
					String tableInfo = "select " + String.join(",", childFile) + " from " + tableName + " where " + tableModel.getTableField() + "='" + mainId + "'";
					List<Map<String, Object>> tableData = getTableList(conn, tableInfo);
					List<Map<String, Object>> childdataAll = new ArrayList<>();
					for (Map<String, Object> data : tableData) {
						Map<String, Object> tablValue = new HashMap<>(16);
						for (String key : data.keySet()) {
							FormColumnModel columnModel = childModel.getChildList().getChildList().stream().filter(t -> key.equals(t.getFieLdsModel().getVModel())).findFirst().orElse(null);
							if (columnModel != null) {
								FieLdsModel fieLdsModel = columnModel.getFieLdsModel();
								String format = fieLdsModel.getFormat();
								String boosKey = fieLdsModel.getConfig().getBoosKey();
								Object childValue = data.get(key);
								childValue = info(boosKey, childValue, mapList);
								childValue = infoTable(boosKey, childValue, format);
								tablValue.put(key, childValue);
							}
						}
						childdataAll.add(tablValue);
					}
					result.put(childKey, childdataAll);
				}
			}
		}
		return result;
	}

	/**
	 * 无表详情
	 **/
	private Map<String, Object> info(Map<String, Object> dataMap, List<FormAllModel> formAllModel, boolean convert) {
		//处理好的数据
		Map<String, Object> result = new HashMap<>(16);
		List<SysPositionEntity> mapList = positionApi.getListAll().getData();
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> tableForm = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		for (String key : dataMap.keySet()) {
			FormAllModel model = mastForm.stream().filter(t -> key.equals(t.getFormColumnModel().getFieLdsModel().getVModel())).findFirst().orElse(null);
			if (model != null) {
				FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
				String boosKey = fieLdsModel.getConfig().getBoosKey();
				Object data = dataMap.get(key);
				data = info(boosKey, data, mapList);
				if (convert) {
					data = converData(fieLdsModel, data);
				}
				result.put(key, data);
			} else {
				if (!convert) {
					FormAllModel childModel = tableForm.stream().filter(t -> key.equals(t.getChildList().getTableModel())).findFirst().orElse(null);
					if (childModel != null) {
						String childKeyName = childModel.getChildList().getTableModel();
						List<Map<String, Object>> childDataMap = (List<Map<String, Object>>) dataMap.get(key);
						List<Map<String, Object>> childdataAll = new ArrayList<>();
						for (Map<String, Object> child : childDataMap) {
							Map<String, Object> tablValue = new HashMap<>(16);
							for (String childKey : child.keySet()) {
								FormColumnModel columnModel = childModel.getChildList().getChildList().stream().filter(t -> childKey.equals(t.getFieLdsModel().getVModel())).findFirst().orElse(null);
								if (columnModel != null) {
									FieLdsModel fieLdsModel = columnModel.getFieLdsModel();
									String boosKey = fieLdsModel.getConfig().getBoosKey();
									Object childValue = child.get(childKey);
									childValue = info(boosKey, childValue, mapList);
									tablValue.put(childKey, childValue);
								}
							}
							childdataAll.add(tablValue);
						}
						result.put(childKeyName, childdataAll);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 详情转换成中文
	 **/
	private Object converData(FieLdsModel fieLdsModel, Object dataValue) {
		Object value = dataValue;
		ConfigModel config = fieLdsModel.getConfig();
		String dataType = config.getDataType();
		String boosKey = config.getBoosKey();
		if (BoosKeyConst.RADIO.equals(boosKey) || BoosKeyConst.SELECT.equals(boosKey) || BoosKeyConst.CHECKBOX.equals(boosKey) || BoosKeyConst.CASCADER.equals(boosKey) || BoosKeyConst.TREESELECT.equals(boosKey)) {
			String props = "";
			if (BoosKeyConst.CASCADER.equals(boosKey) || BoosKeyConst.TREESELECT.equals(boosKey)) {
				props = fieLdsModel.getProps().getProps();
			} else {
				props = JsonUtilEx.getObjectToString(fieLdsModel.getConfig().getProps());
			}
			PropsBeanModel pro = JsonUtil.getJsonToBean(props, PropsBeanModel.class);
			String proFullName = pro.getLabel();
			String proId = pro.getValue();
			String proChildren = pro.getChildren();
			List<String> dataAll = new ArrayList<>();
			List<String> box = new ArrayList<>();
			if (dataValue instanceof String) {
				box = Arrays.asList((String.valueOf(dataValue)).split(","));
			} else if (dataValue instanceof List) {
				box = (List<String>) dataValue;
			}
			//获取list数据
			if (VMDEnum.STATIC.getType().equals(dataType)) {
				List<Map<String, Object>> staticList;
				if (BoosKeyConst.CASCADER.equals(boosKey)) {
					staticList = JsonUtil.getJsonToListMap(fieLdsModel.getOptions());
				} else {
					staticList = JsonUtil.getJsonToListMap(fieLdsModel.getSlot().getOptions());
				}
				if (BoosKeyConst.CASCADER.equals(boosKey) || BoosKeyConst.TREESELECT.equals(boosKey)) {
					JSONArray data = JsonUtil.getListToJsonArray(staticList);
					staticList = new ArrayList<>();
					treeToList(proId, proFullName, proChildren, data, staticList);
				}
				for (String id : box) {
					List<String> name = staticList.stream().filter(t -> String.valueOf(t.get(proId)).equals(String.valueOf(id))).map(t -> String.valueOf(t.get(proFullName))).collect(Collectors.toList());
					dataAll.addAll(name);
				}
				value = String.join(",", dataAll);
			} else if (VMDEnum.DYNAMIC.getType().equals(dataType)) {
				String dynId = config.getPropsUrl();
				//获取远端数据
				Object object = dataInterFaceApi.infoToId(dynId);
				Map<String, Object> dynamicMap = JsonUtil.entityToMap(object);
				String dataJson = "data";
				if (dynamicMap.get(dataJson) != null) {
					List<Map<String, Object>> dataList = JsonUtil.getJsonToListMap(dynamicMap.get("data").toString());
					if (BoosKeyConst.CASCADER.equals(boosKey) || BoosKeyConst.TREESELECT.equals(boosKey)) {
						JSONArray data = JsonUtil.getListToJsonArray(dataList);
						dataList = new ArrayList<>();
						treeToList(proId, proFullName, proChildren, data, dataList);
					}
					for (String id : box) {
						List<String> name = dataList.stream().filter(t -> String.valueOf(t.get(proId)).equals(String.valueOf(id))).map(t -> String.valueOf(t.get(proFullName))).collect(Collectors.toList());
						dataAll.addAll(name);
					}
					value = String.join(",", dataAll);
				}
			}
		}
		//省市区
		if (BoosKeyConst.ADDRESS.equals(boosKey)) {
			List<String> box = (List<String>) dataValue;
			List<String> dataAll = new ArrayList<>();
			if (box != null) {
//                List<ProvinceEntity> data = provinceService.getAllList();
//                for (String id : box) {
//                    List<String> name = data.stream().filter(t -> t.getId().equals(String.valueOf(id))).map(t -> String.valueOf(t.getFullName())).collect(Collectors.toList());
//                    dataAll.addAll(name);
//                }
//                value = String.join(",", dataAll);
			}
		}
		return value;
	}

	/**
	 * 树转成list
	 **/
	private static void treeToList(String id, String fullName, String children, JSONArray data, List<Map<String, Object>> result) {
		for (int i = 0; i < data.size(); i++) {
			JSONObject ob = data.getJSONObject(i);
			Map<String, Object> tree = new HashMap<>(16);
			tree.put(id, String.valueOf(ob.get(id)));
			tree.put(fullName, (String.valueOf(ob.get(fullName))));
			result.add(tree);
			if (ob.get(children) != null) {
				JSONArray childArray = ob.getJSONArray(children);
				treeToList(id, fullName, children, childArray, result);
			}
		}
	}

	/**
	 * 修改有表赋值
	 **/
	private Object infoTable(String boosKey, Object dataValue, String format) {
		Object value = dataValue;
		switch (boosKey) {
			case BoosKeyConst.UPLOADFZ:
			case BoosKeyConst.UPLOADIMG:
				value = JsonUtil.getJsonToListMap(String.valueOf(value));
				break;
			case BoosKeyConst.CHECKBOX:
			case BoosKeyConst.ADDRESS:
			case BoosKeyConst.DATERANGE:
			case BoosKeyConst.TIMERANGE:
			case BoosKeyConst.CASCADER:
				value = JsonUtil.getJsonToList(String.valueOf(value), String.class);
				break;
			case BoosKeyConst.DATE:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat(format);
					value = sdf.parse(String.valueOf(value)).getTime();
				} catch (Exception e) {
					value = dataValue;
				}
				break;
			case BoosKeyConst.SLIDER:
			case BoosKeyConst.SWITCH:
				try {
					value = Integer.valueOf(String.valueOf(value));
				} catch (Exception e) {
					value = dataValue;
				}
				break;
			default:
				break;
		}
		return value;
	}

	/**
	 * 修改系统赋值
	 **/
	private Object info(String boosKey, Object dataValue, List<SysPositionEntity> mapList) {
		Object value = dataValue;
		switch (boosKey) {
			case BoosKeyConst.CURRORGANIZE:
			case BoosKeyConst.CURRDEPT:
				if (StrUtil.isNotEmpty(String.valueOf(dataValue))) {
					R<SysDeptEntity> info = remoteDeptService.info(Convert.toLong(dataValue));
					if (info.getData() != null) {
						value = info.getData().getName();
					}
				}
				break;
			case BoosKeyConst.CREATEUSER:
			case BoosKeyConst.MODIFYUSER:
				break;
			case BoosKeyConst.CURRPOSITION:
				if (StrUtil.isNotEmpty(String.valueOf(dataValue))) {
					SysPositionEntity positionEntity = mapList.stream().filter(t -> String.valueOf(t.getId()).equals(String.valueOf(dataValue))).findFirst().orElse(null);
					if (positionEntity != null) {
						value = positionEntity.getFullName();
					}
				}
				break;
			default:
				break;
		}
		return value;
	}

	//--------------------------------------------修改-----------------------------------------------------

	/**
	 * 修改数据处理
	 **/
	public Map<String, Object> update(Map<String, Object> allDataMap, List<FieLdsModel> fieLdsModelList, List<FlowTableModel> tableModelList, Long mainId) throws SQLException {
		List<FormAllModel> formAllModel = new ArrayList<>();
		//递归遍历模板
		FormCloumnUtil.recursionForm(fieLdsModelList, formAllModel);
		//处理好的数据
		Map<String, Object> result;
		if (tableModelList.size() > 0) {
			result = tableUpdate(allDataMap, formAllModel, tableModelList, mainId);
		} else {
			result = update(allDataMap, formAllModel);
		}
		return result;
	}

	/**
	 * 修改有表数据
	 **/
	private Map<String, Object> tableUpdate(Map<String, Object> allDataMap, List<FormAllModel> formAllModel, List<FlowTableModel> tableModelList, Long mainId) throws SQLException {
		//处理好的数据
		Map<String, Object> result = new HashMap<>(16);
		List<SysPositionEntity> mapList = positionApi.getListAll().getData();
		//系统数据
		Connection conn = getTableConn();
		String mastTableName = tableModelList.stream().filter(t -> "1".equals(t.getTypeId())).findFirst().get().getTable();
		String pKeyName = getPKey(conn, mastTableName);
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> tableForm = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		//主表的语句
		StringBuffer mastSql = new StringBuffer("INSERT INTO " + mastTableName + " ");
		StringBuffer mastFile = new StringBuffer("(" + pKeyName + ",");
		StringBuffer mastFileValue = new StringBuffer("(?,");
		List<Object> mastValue = new LinkedList<>();
		for (String key : allDataMap.keySet()) {
			FormAllModel model = mastForm.stream().filter(t -> key.equals(t.getFormColumnModel().getFieLdsModel().getVModel())).findFirst().orElse(null);
			if (model != null) {
				FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
				String boosKey = fieLdsModel.getConfig().getBoosKey();
				String format = fieLdsModel.getFormat();
				Object data = allDataMap.get(key);
				//处理字段
				String file = "?,";
				//添加字段
				mastFile.append(key).append(",");
				//处理系统自动生成
				data = update(boosKey, data, mapList);
				data = temp(boosKey, data, format);
				mastValue.add(data);
				if (dataSourceUtil.getDataType().toLowerCase().contains(DataSourceEnum.ORACLE.getDbLinkType()) && "date".equals(boosKey)) {
					if (String.valueOf(data).length() < 11) {
						file = "to_date(?,'yyyy-mm-dd HH24:mi:ss'),";
					}
				}
				mastFileValue.append(file);
				result.put(key, data);
			} else {
				FormAllModel childModel = tableForm.stream().filter(t -> key.equals(t.getChildList().getTableModel())).findFirst().orElse(null);
				if (childModel != null) {
					Map<String, Object> childData = childTableUpdate(allDataMap, conn, childModel, tableModelList, key, mainId);
					result.putAll(childData);
				}
			}
		}
		//主表去掉最后
		mastFile = mastFile.deleteCharAt(mastFile.length() - 1).append(")");
		mastFileValue = mastFileValue.deleteCharAt(mastFileValue.length() - 1).append(")");
		mastSql.append(mastFile).append(" VALUES ").append(mastFileValue);
		String delSql = "delete from " + mastTableName + " where " + pKeyName + " =?";
		//插入主表数据
		mastSql(mastSql, mastValue, mainId, delSql, conn);
		return result;
	}

	/**
	 * 修改子表数据
	 **/
	private Map<String, Object> childTableUpdate(Map<String, Object> allDataMap, Connection conn, FormAllModel childModel, List<FlowTableModel> tableModelList, String key, Long mainId) throws SQLException {
		//处理好的子表数据
		Map<String, Object> result = new HashMap<>(16);
		List<SysPositionEntity> mapList = positionApi.getListAll().getData();
		//子表主键
		List<FormColumnModel> childList = childModel.getChildList().getChildList();
		String childTable = childModel.getChildList().getTableName();
		String childKeyName = getPKey(conn, childTable);
		//关联字段
		String mastKeyName = tableModelList.stream().filter(t -> t.getTable().equals(childTable)).findFirst().get().getTableField();
		StringBuffer childFile = new StringBuffer();
		List<List<Object>> childData = new LinkedList<>();
		List<Map<String, Object>> childDataMap = (List<Map<String, Object>>) allDataMap.get(key);
		//子表处理的数据
		List<Map<String, Object>> childResult = new ArrayList<>();
		//子表的字段
		Map<String, String> child = new HashMap<>(16);
		for (FormColumnModel columnModel : childList) {
			String vmodel = columnModel.getFieLdsModel().getVModel();
			String boosKey = columnModel.getFieLdsModel().getConfig().getBoosKey();
			child.put(vmodel, boosKey);
		}
		int num = 0;
		for (Map<String, Object> objectMap : childDataMap) {
			//子表处理的数据
			StringBuffer fileAll = new StringBuffer("(");
			StringBuffer fileValueAll = new StringBuffer("(");
			List<Object> value = new LinkedList<>();
			//子表主键
			value.add(IdUtil.getSnowflakeNextId());
			fileAll.append(childKeyName).append(",");
			fileValueAll.append("?,");
			//关联字段
			value.add(mainId);
			fileAll.append(mastKeyName).append(",");
			fileValueAll.append("?,");
			//子表单体处理的数据
			Map<String, Object> childOneResult = new HashMap<>(16);
			for (String childKey : child.keySet()) {
				FormColumnModel columnModel = childList.stream().filter(t -> childKey.equals(t.getFieLdsModel().getVModel())).findFirst().orElse(null);
				if (columnModel != null) {
					FieLdsModel fieLdsModel = columnModel.getFieLdsModel();
					String boosKey = fieLdsModel.getConfig().getBoosKey();
					String format = fieLdsModel.getFormat();
					Object data = objectMap.get(childKey);
					//处理系统自动生成
					data = update(boosKey, data, mapList);
					data = temp(boosKey, data, format);
					//添加字段
					fileAll.append(childKey).append(",");
					fileValueAll.append("?,");
					if (dataSourceUtil.getDataType().toLowerCase().contains(DataSourceEnum.ORACLE.getDbLinkType()) && "date".equals(boosKey)) {
						if (format.length() < 11) {
							data = data + " 00:00:00";
						}
						value.add("to_date('" + data + "','YYYY-MM-DD HH24:MI:SS'");
					} else {
						value.add(data);
					}
					childOneResult.put(childKey, data);
				}
			}
			childResult.add(childOneResult);
			//子表去掉最后
			if (num == 0) {
				fileAll = fileAll.deleteCharAt(fileAll.length() - 1).append(")");
				fileValueAll = fileValueAll.deleteCharAt(fileValueAll.length() - 1).append(")");
				//添加单行的数据
				childFile.append(fileAll).append(" VALUES ").append(fileValueAll);
				num++;
			}
			childData.add(value);
		}
		//删除子表
		String delSql = "delete from " + childTable + " where " + mastKeyName + "=?";
		String[] del = new String[]{delSql, String.valueOf(mainId)};
		//插入子表数据
		tableSql(childFile, childData, childTable, del, conn);
		result.put(key, childResult);
		return result;
	}

	/**
	 * 修改无表数据
	 **/
	private Map<String, Object> update(Map<String, Object> allDataMap, List<FormAllModel> formAllModel) {
		//处理好的数据
		Map<String, Object> result = new HashMap<>(16);
		List<SysPositionEntity> mapList = positionApi.getListAll().getData();
		//系统数据
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> tableForm = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		for (String key : allDataMap.keySet()) {
			FormAllModel model = mastForm.stream().filter(t -> key.equals(t.getFormColumnModel().getFieLdsModel().getVModel())).findFirst().orElse(null);
			if (model != null) {
				FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
				String boosKey = fieLdsModel.getConfig().getBoosKey();
				Object data = allDataMap.get(key);
				//处理系统自动生成
				data = update(boosKey, data, mapList);
				result.put(key, data);
			} else {
				FormAllModel childModel = tableForm.stream().filter(t -> key.equals(t.getChildList().getTableModel())).findFirst().orElse(null);
				if (childModel != null) {
					List<Map<String, Object>> childDataMap = (List<Map<String, Object>>) allDataMap.get(key);
					//子表处理的数据
					List<Map<String, Object>> childResult = new ArrayList<>();
					for (Map<String, Object> objectMap : childDataMap) {
						//子表单体处理的数据
						Map<String, Object> childOneResult = new HashMap<>(16);
						for (String childKey : objectMap.keySet()) {
							FormColumnModel columnModel = childModel.getChildList().getChildList().stream().filter(t -> childKey.equals(t.getFieLdsModel().getVModel())).findFirst().orElse(null);
							if (columnModel != null) {
								FieLdsModel fieLdsModel = columnModel.getFieLdsModel();
								String boosKey = fieLdsModel.getConfig().getBoosKey();
								Object data = objectMap.get(childKey);
								data = update(boosKey, data, mapList);
								childOneResult.put(childKey, data);
							}
						}
						childResult.add(childOneResult);
					}
					result.put(key, childResult);
				}
			}
		}
		return result;
	}

	/**
	 * 修改系统赋值
	 **/
	private Object update(String boosKey, Object dataValue, List<SysPositionEntity> mapList) {
		Object value = dataValue;
		switch (boosKey) {
			case BoosKeyConst.CURRORGANIZE:
			case BoosKeyConst.CURRDEPT:
				if (StrUtil.isNotEmpty(String.valueOf(dataValue))) {
					R<SysDeptEntity> info = remoteDeptService.infoByName(String.valueOf(dataValue));
					if (info.getData() != null) {
						value = info.getData().getDeptId();
					}
				}
				break;
			case BoosKeyConst.CREATEUSER:
				break;
			case BoosKeyConst.MODIFYUSER:
				if (StrUtil.isEmpty(String.valueOf(dataValue))) {
					value = SecurityUtils.getUser().getUsername();
				}
				break;
			case BoosKeyConst.MODIFYTIME:
				value = DateUtil.getNow("+8");
				break;
			case BoosKeyConst.CURRPOSITION:
				if (StrUtil.isNotEmpty(String.valueOf(dataValue))) {
					SysPositionEntity positionEntity = mapList.stream().filter(t -> t.getFullName().equals(String.valueOf(dataValue))).findFirst().orElse(null);
					if (positionEntity != null) {
						value = positionEntity.getId();
					}
				}
				break;
			default:
				break;
		}
		return value;
	}

	//--------------------------------------------新增-------------------------------------------------------

	/**
	 * 新增数据处理
	 **/
	public Map<String, Object> create(Map<String, Object> allDataMap, List<FieLdsModel> fieLdsModelList, List<FlowTableModel> tableModelList, Long mainId, Map<String, String> billData) throws SQLException, DataException {
		List<FormAllModel> formAllModel = new ArrayList<>();
		//递归遍历模板
		FormCloumnUtil.recursionForm(fieLdsModelList, formAllModel);
		//处理好的数据
		Map<String, Object> result;
		if (tableModelList.size() > 0) {
			result = tableCreate(allDataMap, formAllModel, tableModelList, mainId, billData);
		} else {
			result = create(allDataMap, formAllModel, billData);
		}
		return result;
	}

	/**
	 * 有表插入数据
	 **/
	private Map<String, Object> tableCreate(Map<String, Object> allDataMap, List<FormAllModel> formAllModel, List<FlowTableModel> tableModelList, Long mainId, Map<String, String> billData) throws SQLException, DataException {
		//处理好的数据
		Map<String, Object> result = new HashMap<>(16);
		Connection conn = getTableConn();
		String mastTableName = tableModelList.stream().filter(t -> "1".equals(t.getTypeId())).findFirst().get().getTable();
		String pKeyName = getPKey(conn, mastTableName);
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> tableForm = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		//主表的语句
		StringBuffer mastSql = new StringBuffer("INSERT INTO " + mastTableName + " ");
		StringBuffer mastFile = new StringBuffer("(" + pKeyName + ",");
		StringBuffer mastFileValue = new StringBuffer("(?,");
		List<Object> mastValue = new LinkedList<>();
		for (String key : allDataMap.keySet()) {
			FormAllModel model = mastForm.stream().filter(t -> key.equals(t.getFormColumnModel().getFieLdsModel().getVModel())).findFirst().orElse(null);
			if (model != null) {
				FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
				String boosKey = fieLdsModel.getConfig().getBoosKey();
				String rule = fieLdsModel.getConfig().getRule();
				String format = fieLdsModel.getFormat();
				Object data = allDataMap.get(key);
				//处理字段
				String file = "?,";
				//添加字段
				mastFile.append(key).append(",");
				//单据规则有值判断
				if (billData.get(key) != null) {
					mastValue.add(billData.get(key));
				} else {
					//处理系统自动生成
					data = create(boosKey, data, rule);
					data = temp(boosKey, data, format);
					mastValue.add(data);
				}
				if (dataSourceUtil.getDataType().toLowerCase().contains(DataSourceEnum.ORACLE.getDbLinkType()) && "date".equals(boosKey)) {
					if (String.valueOf(data).length() < 11) {
						file = "to_date(?,'yyyy-mm-dd HH24:mi:ss'),";
					}
				}
				mastFileValue.append(file);
				result.put(key, data);
			} else {
				Map<String, Object> childData = childCreate(allDataMap, conn, tableForm, tableModelList, mainId, key);
				result.putAll(childData);
			}
		}
		//主表去掉最后
		mastFile = mastFile.deleteCharAt(mastFile.length() - 1).append(")");
		mastFileValue = mastFileValue.deleteCharAt(mastFileValue.length() - 1).append(")");
		mastSql.append(mastFile).append(" VALUES ").append(mastFileValue);
		//插入主表数据
		mastSql(mastSql, mastValue, mainId, null, conn);
		return result;
	}

	/**
	 * 新增子表数据
	 **/
	private Map<String, Object> childCreate(Map<String, Object> allDataMap, Connection conn, List<FormAllModel> tableForm, List<FlowTableModel> tableModelList, Long mainId, String key) throws SQLException, DataException {
		//处理好的数据
		Map<String, Object> result = new HashMap<>(16);
		FormAllModel childModel = tableForm.stream().filter(t -> key.equals(t.getChildList().getTableModel())).findFirst().orElse(null);
		if (childModel != null) {
			//子表主键
			List<FormColumnModel> childList = childModel.getChildList().getChildList();
			String childTable = childModel.getChildList().getTableName();
			String childKeyName = getPKey(conn, childTable);
			//关联字段
			String mastKeyName = tableModelList.stream().filter(t -> t.getTable().equals(childTable)).findFirst().get().getTableField();
			StringBuffer childFile = new StringBuffer();
			List<List<Object>> childData = new LinkedList<>();
			List<Map<String, Object>> childDataMap = (List<Map<String, Object>>) allDataMap.get(key);
			//子表处理的数据
			List<Map<String, Object>> childResult = new ArrayList<>();
			//子表的字段
			Map<String, String> child = new HashMap<>(16);
			for (FormColumnModel columnModel : childList) {
				String vmodel = columnModel.getFieLdsModel().getVModel();
				String boosKey = columnModel.getFieLdsModel().getConfig().getBoosKey();
				child.put(vmodel, boosKey);
			}
			int num = 0;
			for (Map<String, Object> objectMap : childDataMap) {
				//子表处理的数据
				StringBuffer fileAll = new StringBuffer("(");
				StringBuffer fileValueAll = new StringBuffer("(");
				List<Object> value = new LinkedList<>();
				//子表主键
				value.add(IdUtil.getSnowflakeNextId());
				fileAll.append(childKeyName).append(",");
				fileValueAll.append("?,");
				//关联字段
				value.add(mainId);
				fileAll.append(mastKeyName).append(",");
				fileValueAll.append("?,");
				//子表单体处理的数据
				Map<String, Object> childOneResult = new HashMap<>(16);
				for (String childKey : child.keySet()) {
					FormColumnModel columnModel = childList.stream().filter(t -> childKey.equals(t.getFieLdsModel().getVModel())).findFirst().orElse(null);
					if (columnModel != null) {
						FieLdsModel fieLdsModel = columnModel.getFieLdsModel();
						String boosKey = fieLdsModel.getConfig().getBoosKey();
						String rule = fieLdsModel.getConfig().getRule();
						String format = fieLdsModel.getFormat();
						Object data = objectMap.get(childKey);
						//处理系统自动生成
						data = create(boosKey, data, rule);
						data = temp(boosKey, data, format);
						//添加字段
						fileAll.append(childKey).append(",");
						fileValueAll.append("?,");
						if (dataSourceUtil.getDataType().toLowerCase().contains(DataSourceEnum.ORACLE.getDbLinkType()) && "date".equals(boosKey)) {
							if (format.length() < 11) {
								data = data + " 00:00:00";
							}
							value.add("to_date('" + data + "','YYYY-MM-DD HH24:MI:SS'");
						} else {
							value.add(data);
						}
						childOneResult.put(childKey, data);
					}
				}
				childResult.add(childOneResult);
				//子表去掉最后
				if (num == 0) {
					fileAll = fileAll.deleteCharAt(fileAll.length() - 1).append(")");
					fileValueAll = fileValueAll.deleteCharAt(fileValueAll.length() - 1).append(")");
					//添加单行的数据
					childFile.append(fileAll).append(" VALUES ").append(fileValueAll);
					num++;
				}
				childData.add(value);
			}
			String[] delSql = new String[]{};
			//插入子表数据
			tableSql(childFile, childData, childTable, delSql, conn);
			result.put(key, childResult);
		}
		return result;
	}

	/**
	 * 无表插入数据
	 **/
	private Map<String, Object> create(Map<String, Object> allDataMap, List<FormAllModel> formAllModel, Map<String, String> billData) throws DataException {
		//处理好的数据
		Map<String, Object> result = new HashMap<>(16);
		List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> tableForm = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		for (String key : allDataMap.keySet()) {
			FormAllModel model = mastForm.stream().filter(t -> key.equals(t.getFormColumnModel().getFieLdsModel().getVModel())).findFirst().orElse(null);
			if (model != null) {
				FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
				String boosKey = fieLdsModel.getConfig().getBoosKey();
				String rule = fieLdsModel.getConfig().getRule();
				Object data = allDataMap.get(key);
				//单据规则有值判断
				String bill = billData.get(key.toLowerCase());
				if (bill != null) {
					result.put(key, bill);
				} else {
					//处理系统自动生成
					data = create(boosKey, data, rule);
					result.put(key, data);
				}
			} else {
				FormAllModel childModel = tableForm.stream().filter(t -> key.equals(t.getChildList().getTableModel())).findFirst().orElse(null);
				if (childModel != null) {
					//子表主键
					List<FormColumnModel> childList = childModel.getChildList().getChildList();
					List<Map<String, Object>> childDataMap = (List<Map<String, Object>>) allDataMap.get(key);
					//子表处理的数据
					List<Map<String, Object>> childResult = new ArrayList<>();
					for (Map<String, Object> objectMap : childDataMap) {
						//子表单体处理的数据
						Map<String, Object> childOneResult = new HashMap<>(16);
						for (String childKey : objectMap.keySet()) {
							FormColumnModel columnModel = childList.stream().filter(t -> childKey.equals(t.getFieLdsModel().getVModel())).findFirst().orElse(null);
							if (columnModel != null) {
								FieLdsModel fieLdsModel = columnModel.getFieLdsModel();
								String boosKey = fieLdsModel.getConfig().getBoosKey();
								String rule = fieLdsModel.getConfig().getRule();
								Object data = objectMap.get(childKey);
								//处理系统自动生成
								data = create(boosKey, data, rule);
								childOneResult.put(childKey, data);
							}
						}
						childResult.add(childOneResult);
					}
					result.put(key, childResult);
				}
			}
		}
		return result;
	}

	/**
	 * 子表插入数据
	 **/
	private void tableSql(StringBuffer childFile, List<List<Object>> childData, String childTable, String[] del, Connection conn) throws SQLException {
		if (del.length > 0) {
			PreparedStatement delete = conn.prepareStatement(del[0]);
			delete.setObject(1, del[1]);
			delete.addBatch();
			delete.executeBatch();
		}
		for (List<Object> childDatum : childData) {
			conn.setAutoCommit(false);
			boolean result = childDatum.size() > 2;
			String sql = "INSERT INTO " + childTable + " " + childFile.toString();
			PreparedStatement save = conn.prepareStatement(sql);
			if (result) {
				for (int k = 0; k < childDatum.size(); k++) {
					save.setObject(k + 1, childDatum.get(k));
				}
				save.addBatch();
			}
			save.executeBatch();
		}
		conn.commit();
	}

	/**
	 * 主表插入语句
	 **/
	private void mastSql(StringBuffer mastSql, List<Object> mastValue, Long mainId, String delteSql, Connection conn) throws SQLException {
		conn.setAutoCommit(false);
		if (StrUtil.isNotEmpty(delteSql)) {
			PreparedStatement delete = conn.prepareStatement(delteSql);
			delete.setObject(1, mainId);
			delete.addBatch();
			delete.executeBatch();
		}
		PreparedStatement save = conn.prepareStatement(mastSql.toString());
		int num = 1;
		save.setObject(num, mainId);
		num++;
		for (Object data : mastValue) {
			save.setObject(num, data);
			num++;
		}
		save.addBatch();
		save.executeBatch();
		conn.commit();
		conn.close();
	}

	/**
	 * 新增系统赋值
	 **/
	private Object create(String boosKey, Object dataValue, String rule) {
		BoosterUser userInfo = SecurityUtils.getUser();
		Object value = dataValue;
		switch (boosKey) {
			case BoosKeyConst.CREATEUSER:
				value = userInfo.getId();
				break;
			case BoosKeyConst.CREATETIME:
				value = DateUtil.getNow("+8");
				break;
			case BoosKeyConst.CURRORGANIZE:
				value = userInfo.getDeptId();
				break;
			case BoosKeyConst.CURRDEPT:
				value = userInfo.getDeptId();
				break;
			case BoosKeyConst.MODIFYTIME:
				value = null;
				break;
			case BoosKeyConst.MODIFYUSER:
				value = null;
				break;
			case BoosKeyConst.CURRPOSITION:
				List<SysPositionEntity> positionEntityList = positionApi.getListByUserName(userInfo.getUsername()).getData();
				value = CollUtil.isNotEmpty(positionEntityList) ? positionEntityList.get(0).getId() : "";
				break;
			case BoosKeyConst.BILLRULE:
				value = billRuleApi.getBillNumber(rule).getData();
				break;
			default:
				break;
		}
		return value;
	}

	/**
	 * 转换时间和其他的类型
	 **/
	private Object temp(String boosKey, Object dataValue, String format) {
		if (BoosKeyConst.DATE.equals(boosKey)) {
			if (dataValue != null) {
				dataValue = DateUtil.dateToString(new Date(Convert.toLong(dataValue)), format);
			}
		} else if (dataValue != null) {
			dataValue = String.valueOf(dataValue);
		}
		return dataValue;
	}

}
