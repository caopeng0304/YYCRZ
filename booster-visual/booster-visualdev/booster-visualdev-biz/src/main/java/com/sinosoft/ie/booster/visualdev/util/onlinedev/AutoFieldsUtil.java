package com.sinosoft.ie.booster.visualdev.util.onlinedev;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.admin.api.model.position.PositionInfoVO;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.constant.BoosKeyConst;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevModelDataEntity;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;

import java.util.List;
import java.util.Map;

/**
 * 处理自动生成字段
 *
 * @author booster开发平台组
 * @since 2021-03-15
 */
public class AutoFieldsUtil {
	private static RemoteDeptService organizeService;
	private static RemoteBillRuleService billRuleService;
	private static RemotePositionService positionApi;


	//初始化

	public static void init() {
		billRuleService = SpringContextHolder.getBean(RemoteBillRuleService.class);
		organizeService = SpringContextHolder.getBean(RemoteDeptService.class);
		positionApi = SpringContextHolder.getBean(RemotePositionService.class);
	}


	/**
	 * 生成系统自动生成字段
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> createFeilds(List<FieLdsModel> fieLdsModelList, Map<String, Object> allDataMap) throws DataException {
		init();
		//模型循环
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			if ("table".equals(fieLdsModel.getConfig().getBoosKey()) && allDataMap.get(fieLdsModel.getVModel()) != null) {
				List<FieLdsModel> childFieLdsModelList = fieLdsModel.getConfig().getChildren();
				Object object = allDataMap.get(fieLdsModel.getVModel());
				if (object instanceof List) {
					List<Map<String, Object>> childAllDataMapList = JsonUtil.getJsonToListMap(JsonUtilEx.getObjectToString(object));
					for (Map<String, Object> childmap : childAllDataMapList) {
						for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
							//数据循环
							for (Map.Entry<String, Object> data : childmap.entrySet()) {
								BoosterUser userInfo = SecurityUtils.getUser();
								if (childFieLdsModel.getVModel().equals(data.getKey())) {
									String boosKeyType = childFieLdsModel.getConfig().getBoosKey();
									switch (boosKeyType) {
										case BoosKeyConst.BILLRULE:
											R<String> billNumber = billRuleService.getBillNumber(childFieLdsModel.getConfig().getRule());
											if (billNumber == null || billNumber.getData() == null) {
												data.setValue("");
											} else {
												data.setValue(billNumber.getData());
											}
											break;
										case BoosKeyConst.CREATEUSER:
											data.setValue(userInfo.getId());
											break;
										case BoosKeyConst.CREATETIME:
											data.setValue(DateUtil.getNow("+8"));
											break;
										case BoosKeyConst.MODIFYUSER:
										case BoosKeyConst.MODIFYTIME:
										case BoosKeyConst.CURRORGANIZE:
										case BoosKeyConst.CURRPOSITION:
											List<SysPositionEntity> positionEntityList = positionApi.getListByUserName(userInfo.getUsername()).getData();
											data.setValue(CollUtil.isNotEmpty(positionEntityList) ? positionEntityList.get(0).getId() : "");
											break;
										case BoosKeyConst.CURRDEPT:
											if (userInfo.getDeptId() != null) {
												data.setValue(String.valueOf(userInfo.getDeptId()));
											} else {
												data.setValue("");
											}
											break;
										default:
									}
								}
							}
						}
						allDataMap.put(fieLdsModel.getVModel(), childAllDataMapList);
					}
				} else {
					Map<String, Object> childAllDataMap = JsonUtil.stringToMap(JsonUtilEx.getObjectToString(object));
					for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
						//数据循环
						for (Map.Entry<String, Object> data : childAllDataMap.entrySet()) {
							BoosterUser userInfo = SecurityUtils.getUser();
							if (childFieLdsModel.getVModel().equals(data.getKey())) {
								String boosKeyType = childFieLdsModel.getConfig().getBoosKey();
								switch (boosKeyType) {
									case BoosKeyConst.BILLRULE:
										R<String> billNumber = billRuleService.getBillNumber(childFieLdsModel.getConfig().getRule());
										if (billNumber == null || billNumber.getData() == null) {
											data.setValue("");
										} else {
											data.setValue(billNumber.getData());
										}
										break;
									case BoosKeyConst.CREATEUSER:
										data.setValue(userInfo.getId());
										break;
									case BoosKeyConst.CREATETIME:
										data.setValue(DateUtil.getNow("+8"));
										break;
									case BoosKeyConst.MODIFYUSER:
									case BoosKeyConst.MODIFYTIME:
									case BoosKeyConst.CURRORGANIZE:
									case BoosKeyConst.CURRPOSITION:
										List<SysPositionEntity> positionEntityList = positionApi.getListByUserName(userInfo.getUsername()).getData();
										data.setValue(CollUtil.isNotEmpty(positionEntityList) ? positionEntityList.get(0).getId() : "");
										break;
									case BoosKeyConst.CURRDEPT:
										if (userInfo.getDeptId() != null) {
											data.setValue(String.valueOf(userInfo.getDeptId()));
										} else {
											data.setValue("");
										}
										break;
									default:
								}
							}
						}
					}
					allDataMap.put(fieLdsModel.getVModel(), childAllDataMap);
				}

			}
			//数据循环
			for (Map.Entry<String, Object> data : allDataMap.entrySet()) {
				BoosterUser userInfo = SecurityUtils.getUser();
				if (fieLdsModel.getVModel().equals(data.getKey())) {
					String boosKeyType = fieLdsModel.getConfig().getBoosKey();
					switch (boosKeyType) {
						case BoosKeyConst.BILLRULE:
							R<String> billNumber = billRuleService.getBillNumber(fieLdsModel.getConfig().getRule());
							if (billNumber == null || billNumber.getData() == null) {
								data.setValue("");
							} else {
								data.setValue(billNumber.getData());
							}
							break;
						case BoosKeyConst.CREATEUSER:
							data.setValue(userInfo.getId());
							break;
						case BoosKeyConst.CREATETIME:
							data.setValue(DateUtil.getNow("+8"));
							break;
						case BoosKeyConst.MODIFYUSER:
						case BoosKeyConst.MODIFYTIME:
						case BoosKeyConst.CURRORGANIZE:
						case BoosKeyConst.CURRPOSITION:
							List<SysPositionEntity> positionEntityList = positionApi.getListByUserName(userInfo.getUsername()).getData();
							data.setValue(CollUtil.isNotEmpty(positionEntityList) ? positionEntityList.get(0).getId() : "");
							break;
						case BoosKeyConst.CURRDEPT:
							if (userInfo.getDeptId() != null) {
								data.setValue(String.valueOf(userInfo.getDeptId()));
							} else {
								data.setValue("");
							}
							break;
						default:
					}
				}
			}

		}
		return allDataMap;
	}

	/**
	 * 生成系统自动生成字段
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> updateFeilds(List<FieLdsModel> fieLdsModelList, Map<String, Object> allDataMap) {
		init();
		//模型循环
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			if ("table".equals(fieLdsModel.getConfig().getBoosKey()) && allDataMap.get(fieLdsModel.getVModel()) != null) {
				List<FieLdsModel> childFieLdsModelList = fieLdsModel.getConfig().getChildren();
				Object object = allDataMap.get(fieLdsModel.getVModel());
				if (object instanceof List) {
					List<Map<String, Object>> childAllDataMapList = JsonUtil.getJsonToListMap(JsonUtilEx.getObjectToString(object));
					for (Map<String, Object> childmap : childAllDataMapList) {
						for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
							//数据循环
							for (Map.Entry<String, Object> data : childmap.entrySet()) {
								BoosterUser userInfo = SecurityUtils.getUser();
								if (childFieLdsModel.getVModel().equals(data.getKey())) {
									String boosKeyType = childFieLdsModel.getConfig().getBoosKey();
									switch (boosKeyType) {
										case BoosKeyConst.MODIFYUSER:
											data.setValue(userInfo.getId());
											break;
										case BoosKeyConst.MODIFYTIME:
											data.setValue(DateUtil.getNow("+8"));
											break;
										case BoosKeyConst.CURRORGANIZE:
										case BoosKeyConst.CURRPOSITION:
											List<SysPositionEntity> positionEntityList = positionApi.getListByUserName(userInfo.getUsername()).getData();
											data.setValue(CollUtil.isNotEmpty(positionEntityList) ? positionEntityList.get(0).getId() : "");
											break;
										case BoosKeyConst.CURRDEPT:
											if (userInfo.getDeptId() != null) {
												data.setValue(String.valueOf(userInfo.getDeptId()));
											} else {
												data.setValue("");
											}
											break;
										default:
									}
								}
							}
						}
						allDataMap.put(fieLdsModel.getVModel(), childAllDataMapList);
					}
				} else {
					Map<String, Object> childAllDataMap = JsonUtil.stringToMap(JsonUtilEx.getObjectToString(object));
					for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
						//数据循环
						for (Map.Entry<String, Object> data : childAllDataMap.entrySet()) {
							BoosterUser userInfo = SecurityUtils.getUser();
							if (childFieLdsModel.getVModel().equals(data.getKey())) {
								String boosKeyType = childFieLdsModel.getConfig().getBoosKey();
								switch (boosKeyType) {
									case BoosKeyConst.MODIFYUSER:
										data.setValue(userInfo.getId());
										break;
									case BoosKeyConst.MODIFYTIME:
										data.setValue(DateUtil.getNow("+8"));
										break;
									case BoosKeyConst.CURRORGANIZE:
									case BoosKeyConst.CURRPOSITION:
										List<SysPositionEntity> positionEntityList = positionApi.getListByUserName(userInfo.getUsername()).getData();
										data.setValue(CollUtil.isNotEmpty(positionEntityList) ? positionEntityList.get(0).getId() : "");
										break;
									case BoosKeyConst.CURRDEPT:
										if (userInfo.getDeptId() != null) {
											data.setValue(String.valueOf(userInfo.getDeptId()));
										} else {
											data.setValue("");
										}
										break;
									default:
								}
							}
						}
					}
					allDataMap.put(fieLdsModel.getVModel(), childAllDataMap);
				}

			}
			//数据循环
			for (Map.Entry<String, Object> data : allDataMap.entrySet()) {
				BoosterUser userInfo = SecurityUtils.getUser();
				if (data.getKey().contains(fieLdsModel.getVModel())) {
					String boosKeyType = fieLdsModel.getConfig().getBoosKey();
					switch (boosKeyType) {
						case BoosKeyConst.MODIFYUSER:
							data.setValue(userInfo.getId());
							break;
						case BoosKeyConst.MODIFYTIME:
							data.setValue(DateUtil.getNow("+8"));
							break;
						case BoosKeyConst.CURRORGANIZE:
						case BoosKeyConst.CURRPOSITION:
							List<SysPositionEntity> positionEntityList = positionApi.getListByUserName(userInfo.getUsername()).getData();
							data.setValue(CollUtil.isNotEmpty(positionEntityList) ? positionEntityList.get(0).getId() : "");
							break;
						case BoosKeyConst.CURRDEPT:
							if (userInfo.getDeptId() != null) {
								data.setValue(String.valueOf(userInfo.getDeptId()));
							} else {
								data.setValue("");
							}
							break;
						default:
					}
				}
			}

		}
		return allDataMap;
	}

	/**
	 * 列表系统自动生成字段转换
	 *
	 * @return List<BaseVisualDevModelData>
	 */
	public static List<BaseVisualDevModelDataEntity> autoFeildsList(List<FieLdsModel> fieLdsModelList, List<BaseVisualDevModelDataEntity> list) {
		init();
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			for (BaseVisualDevModelDataEntity entity : list) {
				Map<String, Object> dataMap = JsonUtil.stringToMap(entity.getData());
				for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
					if (fieLdsModel.getVModel().equals(entry.getKey())) {
						String boosKeyType = fieLdsModel.getConfig().getBoosKey();
						switch (boosKeyType) {
							case BoosKeyConst.CURRORGANIZE:
							case BoosKeyConst.CURRDEPT:
								if (StrUtil.isNotEmpty(String.valueOf(entry.getValue()))) {
									R<SysDeptEntity> organizeEntity = organizeService.info((Long) entry.getValue());
									if (organizeEntity != null) {
										entry.setValue(organizeEntity.getData().getName());
									}
								}
								break;
							case BoosKeyConst.CREATEUSER:
							case BoosKeyConst.MODIFYUSER:
							case BoosKeyConst.CURRPOSITION:
								if (StrUtil.isNotEmpty(String.valueOf(entry.getValue()))) {
									PositionInfoVO positionInfoVO = positionApi.getInfo(String.valueOf(entry.getValue())).getData();
									if (positionInfoVO != null) {
										entry.setValue(positionInfoVO.getFullName());
									}
								}
								break;
							default:
						}
					}
				}
				entity.setData(JsonUtilEx.getObjectToString(dataMap));
			}
		}
		return list;
	}

	/**
	 * 列表系统自动生成字段转换
	 *
	 * @return String
	 */
	public static String autoFeilds(List<FieLdsModel> fieLdsModelList, String data) {
		init();
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			Map<String, Object> dataMap = JsonUtil.stringToMap(data);
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				if (fieLdsModel.getVModel().equals(entry.getKey())) {
					String boosKeyType = fieLdsModel.getConfig().getBoosKey();
					switch (boosKeyType) {
						case BoosKeyConst.CURRORGANIZE:
						case BoosKeyConst.CURRDEPT:
							R<SysDeptEntity> organizeEntity = organizeService.info((Long) entry.getValue());
							if (organizeEntity != null) {
								entry.setValue(organizeEntity.getData().getName());
							}
							break;
						case BoosKeyConst.CREATEUSER:
						case BoosKeyConst.MODIFYUSER:
						case BoosKeyConst.CURRPOSITION:
							if (StrUtil.isNotEmpty(String.valueOf(entry.getValue()))) {
								PositionInfoVO positionInfoVO = positionApi.getInfo(String.valueOf(entry.getValue())).getData();
								if (positionInfoVO != null) {
									entry.setValue(positionInfoVO.getFullName());
								}
							}
							break;
						default:
					}
				}
			}
			data = JsonUtilEx.getObjectToString(dataMap);

		}
		data = data.replaceAll("\"\\[", "\\[");
		data = data.replaceAll("\\]\"", "\\]");
		return data.replaceAll("\\\\", "");
	}


}
