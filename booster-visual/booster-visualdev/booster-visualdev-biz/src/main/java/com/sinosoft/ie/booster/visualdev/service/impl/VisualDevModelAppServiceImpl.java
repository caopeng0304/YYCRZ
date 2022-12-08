package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevModelDataEntity;
import com.sinosoft.ie.booster.visualdev.model.base.ColumnDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.TableModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormAllModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormColumnModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormEnum;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.PaginationModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VisualdevModelDataCrForm;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VisualdevModelDataInfoVO;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VisualdevModelDataUpForm;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevModelDataService;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevService;
import com.sinosoft.ie.booster.visualdev.service.VisualDevModelAppService;
import com.sinosoft.ie.booster.visualdev.util.FormCloumnUtil;
import com.sinosoft.ie.booster.visualdev.util.onlinedev.AutoFieldsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * App可视化开发功能表
 *
 * @author booster开发平台组
 * @since 2021-04-02
 */
@Service
public class VisualDevModelAppServiceImpl implements VisualDevModelAppService {
	@Autowired
	private BaseVisualDevService visualDevService;
	@Autowired
	private BaseVisualDevModelDataService visualDevModelDataService;

	@Override
	public List<Map<String, Object>> resultList(Long modelId, PaginationModel paginationModel) throws DataException, ParseException, SQLException, IOException {
		BaseVisualDevEntity entity = visualDevService.getInfo(modelId);
		//赋值type
		ColumnDataModel columnDataModel = JsonUtil.getJsonToBean(entity.getColumnData(), ColumnDataModel.class);
		columnDataModel.setType(1);
		entity.setColumnData(JsonUtilEx.getObjectToString(columnDataModel));
		common(entity);
		List<Map<String, Object>> realList = visualDevModelDataService.getListResult(entity, paginationModel);
		//排序
		if (StrUtil.isNotEmpty(paginationModel.getSidx()) && realList.size() > 0) {
			Object value = realList.get(0).get(paginationModel.getSidx());
			if (value != null) {
				if ("desc".equals(paginationModel.getSort())) {
					realList.sort(Comparator.comparing((Map<String, Object> h) -> ((String) h.get(paginationModel.getSidx()))).reversed());
				} else {
					realList.sort(Comparator.comparing((Map<String, Object> h) -> ((String) h.get(paginationModel.getSidx()))));
				}
			}
		}
		return realList;
	}

	@Override
	public void create(BaseVisualDevEntity entity, String data) throws DataException, SQLException {
		VisualdevModelDataCrForm form = new VisualdevModelDataCrForm();
		form.setData(data);
		common(entity);
		visualDevModelDataService.create(entity, form);
	}

	@Override
	public boolean update(Long id, BaseVisualDevEntity entity, String data) throws DataException, SQLException {
		VisualdevModelDataUpForm form = new VisualdevModelDataUpForm();
		form.setData(data);
		common(entity);
		return visualDevModelDataService.update(id, entity, form);
	}

	@Override
	public boolean delete(Long id, BaseVisualDevEntity entity) throws DataException, SQLException {
		boolean flag = false;
		List<TableModel> tableModelList = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
		if (tableModelList.size() > 0) {
			flag = visualDevModelDataService.tableDelete(id, entity);
		} else {
			BaseVisualDevModelDataEntity dataEntity = visualDevModelDataService.getInfo(id);
			if (dataEntity != null) {
				visualDevModelDataService.delete(dataEntity);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public Map<String, Object> info(Long id, BaseVisualDevEntity entity) throws DataException, ParseException, SQLException, IOException {
		List<TableModel> tableModelList = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
		VisualdevModelDataInfoVO vo = new VisualdevModelDataInfoVO();
		if (tableModelList.size() > 0) {
			common(entity);
			vo = visualDevModelDataService.tableInfo(id, entity);
		} else {
			BaseVisualDevModelDataEntity dataEntity = visualDevModelDataService.getInfo(id);
			List<FieLdsModel> list = info(entity);
			if (dataEntity != null) {
				String data = AutoFieldsUtil.autoFeilds(list, dataEntity.getData());
				vo.setData(data);
				vo.setId(String.valueOf(id));
			}
		}
		return JsonUtil.entityToMap(vo);
	}

	/**
	 * 信息去多余控件
	 *
	 * @param entity
	 * @return
	 */
	private List<FieLdsModel> info(BaseVisualDevEntity entity) {
		//修改app属性没有默认值
		FormDataModel formDataModel = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
		List<FieLdsModel> formModel = JsonUtil.getJsonToList(formDataModel.getFields(), FieLdsModel.class);
		return fieldsAll(formModel);
	}

	/**
	 * 查询、新增和修改属性
	 *
	 * @param entity
	 */
	private void common(BaseVisualDevEntity entity) {
		//修改app属性没有默认值
		FormDataModel formDataModel = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
		List<FieLdsModel> formModel = JsonUtil.getJsonToList(formDataModel.getFields(), FieLdsModel.class);
		List<FieLdsModel> fieldsAll = fieldsAll(formModel);
		Map<String, Object> map = new HashMap<>(16);
		map.put("fields", fieldsAll);
		entity.setFormData(JsonUtil.getObjectToString(map));
	}

	/**
	 * app的默认值
	 *
	 * @param formModel
	 * @return
	 */
	private List<FieLdsModel> fieldsAll(List<FieLdsModel> formModel) {
		//修改app属性没有默认值
		List<FormAllModel> formAllModel = new ArrayList<>();
		FormCloumnUtil.recursionForm(formModel, formAllModel);
		//赋值主表的日期类型
		List<FormAllModel> formAll = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey()) || FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FieLdsModel> fieldsAll = new ArrayList<>();
		for (FormAllModel model : formAll) {
			if (FormEnum.mast.getMessage().equals(model.getBoosKey())) {
				FieLdsModel fieLdsModel = model.getFormColumnModel().getFieLdsModel();
				model(fieLdsModel);
				fieldsAll.add(fieLdsModel);
			} else {
				String tabVmodel = model.getChildList().getTableModel();
				FieLdsModel child = new FieLdsModel();
				child.setVModel(tabVmodel);
				String tableName = model.getChildList().getTableName();
				ConfigModel configModel = new ConfigModel();
				configModel.setTableName(tableName);
				configModel.setBoosKey(FormEnum.table.getMessage());
				List<FieLdsModel> childAll = new ArrayList<>();
				List<FormColumnModel> childList = model.getChildList().getChildList();
				for (FormColumnModel column : childList) {
					FieLdsModel fieLdsModel = column.getFieLdsModel();
					model(fieLdsModel);
					childAll.add(fieLdsModel);
				}
				configModel.setChildren(childAll);
				child.setConfig(configModel);
				fieldsAll.add(child);
			}
		}
		return fieldsAll;
	}

	/**
	 * app日期赋默认属性
	 *
	 * @param fieLdsModel
	 */
	private void model(FieLdsModel fieLdsModel) {
		String boosKey = fieLdsModel.getConfig().getBoosKey();
		if (StrUtil.isNotEmpty(fieLdsModel.getVModel())) {
			if ("date".equals(boosKey) || "dateRange".equals(boosKey)) {
				fieLdsModel.setFormat("yyyy-MM-dd");
				fieLdsModel.setValueformat("timestamp");
			} else if ("timeRange".equals(boosKey) || "time".equals(boosKey)) {
				fieLdsModel.setFormat("HH:mm:ss");
				fieLdsModel.setValueformat("HH:mm:ss");
			}
		}
	}

}
