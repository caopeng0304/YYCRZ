package com.sinosoft.ie.booster.visualdev.util;

import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.*;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.constant.BoosKeyConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线工作流开发
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
public class FormCloumnUtil {

	/**
	 * 引擎递归
	 **/
	public static void recursionForm(List<FieLdsModel> list, List<FormAllModel> formAllModel) {
		for (FieLdsModel fieLdsModel : list) {
			FormAllModel start = new FormAllModel();
			FormAllModel end = new FormAllModel();
			ConfigModel config = fieLdsModel.getConfig();
			String boosKey = config.getBoosKey();
			List<FieLdsModel> childrenList = config.getChildren();
			if (FormEnum.row.getMessage().equals(boosKey) || FormEnum.card.getMessage().equals(boosKey)) {
				start.setBoosKey(boosKey);
				FormModel formModel = new FormModel();
				formModel.setShadow(fieLdsModel.getShadow());
				formModel.setSpan(config.getSpan());
				start.setFormModel(formModel);
				formAllModel.add(start);
				recursionForm(childrenList, formAllModel);
				end.setIsEnd("1");
				end.setBoosKey(boosKey);
				formAllModel.add(end);
			} else if (FormEnum.table.getMessage().equals(boosKey)) {
				tableModel(fieLdsModel, formAllModel);
			} else {
				model(fieLdsModel, formAllModel);
			}
		}
	}

	/**
	 * 主表属性添加
	 **/
	private static void model(FieLdsModel model, List<FormAllModel> formAllModel) {
		FormColumnModel mastModel = formModel(model);
		FormAllModel formModel = new FormAllModel();
		formModel.setBoosKey(FormEnum.mast.getMessage());
		formModel.setFormColumnModel(mastModel);
		formAllModel.add(formModel);
	}

	/**
	 * 子表表属性添加
	 **/
	private static void tableModel(FieLdsModel model, List<FormAllModel> formAllModel) {
		FormColumnTableModel tableModel = new FormColumnTableModel();
		List<FormColumnModel> childList = new ArrayList<>();
		List<Map<String, Object>> child = new ArrayList<>();
		ConfigModel config = model.getConfig();
		List<FieLdsModel> childModelList = config.getChildren();
		String table = model.getVModel();
		for (FieLdsModel childmodel : childModelList) {
			FormColumnModel childModel = formModel(childmodel);
			//------------------app的json---------------------
			Map<String, Object> fieLdsModel = JsonUtil.entityToMap(childModel.getFieLdsModel());
			fieLdsModel.put("__config__", childmodel.getConfig());
			fieLdsModel.put("__vModel__", childmodel.getVModel());
			if (childmodel.getSlot() != null) {
				List<Map<String, Object>> option = JsonUtil.getJsonToListMap(childmodel.getSlot().getOptions());
				Map<String, Object> objectMap = new HashMap<>(16);
				objectMap.put("options", option);
				fieLdsModel.put("__slot__", objectMap);
				fieLdsModel.remove("slot");
			}
			fieLdsModel.remove("config");
			fieLdsModel.remove("vModel");
			child.add(fieLdsModel);
			//------------------app的json---------------------
			childList.add(childModel);
		}
		//------------------app的json---------------------
		Map<String, Object> filds = JsonUtil.entityToMap(model);
		Map<String, Object> configModel = (Map<String, Object>) filds.get("config");
		configModel.put("children", child);
		filds.put("__config__", configModel);
		filds.put("__vModel__", model.getVModel());
		filds.remove("config");
		filds.remove("vModel");
		tableModel.setFieLdsModel(JsonUtil.getObjectToString(filds));
		//------------------app的json---------------------
		tableModel.setLabel(config.getLabel());
		tableModel.setShowTitle(Boolean.getBoolean(config.getShowTitle()));
		tableModel.setSpan(config.getSpan());
		tableModel.setTableModel(table);
		tableModel.setChildList(childList);
		tableModel.setTableName(config.getTableName());
		FormAllModel formModel = new FormAllModel();
		formModel.setBoosKey(FormEnum.table.getMessage());
		formModel.setChildList(tableModel);
		formAllModel.add(formModel);
	}

	/**
	 * 属性赋值
	 **/
	private static FormColumnModel formModel(FieLdsModel model) {
		ConfigModel configModel = model.getConfig();
		if (configModel.getDefaultValue() instanceof String) {
			configModel.setValueType("String");
		}
		if (configModel.getDefaultValue() == null) {
			configModel.setValueType("undefined");
		}
		FormColumnModel formColumnModel = new FormColumnModel();
		//级联判断多选还是单选
		if (BoosKeyConst.CASCADER.equals(configModel.getBoosKey())) {
			Map<String, Object> propsMap = JsonUtil.stringToMap(model.getProps().getProps());
			model.setMultiple(String.valueOf(propsMap.get("multiple")));
		}
		formColumnModel.setFieLdsModel(model);
		return formColumnModel;
	}
}
