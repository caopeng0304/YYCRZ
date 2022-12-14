package com.sinosoft.ie.booster.visualdev.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.constant.BoosKeyConst;
import com.sinosoft.ie.booster.visualdev.constant.DataTypeConst;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseVisualDevMapper;
import com.sinosoft.ie.booster.visualdev.model.base.DownloadCodeForm;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.SubClassModel;
import com.sinosoft.ie.booster.visualdev.model.base.TableModel;
import com.sinosoft.ie.booster.visualdev.model.base.Template6.ColumnListField;
import com.sinosoft.ie.booster.visualdev.model.base.Template6.Template6Model;
import com.sinosoft.ie.booster.visualdev.model.base.Template7.ChildrenModel;
import com.sinosoft.ie.booster.visualdev.model.generater.GenBaseInfo;
import com.sinosoft.ie.booster.visualdev.model.generater.GenFileNameSuffix;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.slot.SlotModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.slot.SlotOptionModel;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevService;
import com.sinosoft.ie.booster.visualdev.service.VisualDevGenService;
import com.sinosoft.ie.booster.visualdev.util.ConfigValueUtil;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.visualdev.util.base.GetGenDataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.AppIndexGenUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.VueGenUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.WorkVueGenUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.CustomGenerator;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.VelocityEnum;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.VisualUtils;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ????????????????????????
 *
 * @author booster???????????????
 * @since 2021-04-02
 */
@Service
public class VisualDevGenServiceImpl extends ServiceImpl<BaseVisualDevMapper, BaseVisualDevEntity> implements VisualDevGenService {

	@Autowired
	private ConfigValueUtil configValueUtil;
	@Autowired
	private DataSourceUtil dataSourceUtil;
	@Autowired
	private BaseVisualDevService visualDevService;

	@Override
	public String codeGenerate(Long id, DownloadCodeForm downloadCodeForm) throws SQLException {
		BoosterUser userInfo = SecurityUtils.getUser();
		BaseVisualDevEntity entity = visualDevService.getInfo(id);
		//???????????????????????????
		VisualUtils.delete(entity);

		if (!StrUtil.isEmpty(entity.getRefTables())) {
			FormDataModel model = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
			List<TableModel> list = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
			TableModel mainTableModel = list.stream().filter(t -> "1".equals(t.getTypeId())).findFirst().get();
			String mainTable = mainTableModel.getTable();
			String mainTableComment = mainTableModel.getTableName();
			model.setClassName(new Template6Model().toUpperAttr(mainTable));
			model.setAreasName(downloadCodeForm.getModuleName());
			model.setServiceDirectory(configValueUtil.getCodeTempPath());
			FormDataModel htmlModel = JsonUtil.getJsonToBean(model, FormDataModel.class);


			List<FieLdsModel> filterFieldList = JsonUtil.getJsonToList(model.getFields(), FieLdsModel.class);
			//?????????????????????
			filterFieldList = VisualUtils.deleteMore(filterFieldList);
			//????????????????????????
			filterFieldList = VisualUtils.deleteVmodel(filterFieldList);

			model.setFields(JSON.toJSONString(filterFieldList));
			String fileName = RandomUtil.uuId();
			//???????????????
			VelocityEnum.init.initVelocity();

			//????????????
			@Cleanup Connection conn = VisualUtils.getTableConn();
			//????????????
			String pKeyName = VisualUtils.getpKey(conn, mainTable);

			//???????????????????????????
			Map<String, Object> childPKeyMap = new HashMap<>(16);
			for (TableModel tableModel : list) {
				String childKey = VisualUtils.getpKey(conn, mainTable);
				childPKeyMap.put(tableModel.getTable(), childKey);
			}
			//??????????????????
			List<String> childTb = list.stream().skip(1L).map(TableModel::getTable).collect(Collectors.toList());

			Set<String> set = new HashSet<>(childTb);
			boolean result = childTb.size() == set.size();
			if (!result) {
				return "??????????????????";
			}
			if (entity.getType() == 4) {
				this.htmlTemplates(fileName, entity, model, htmlModel, childTb, pKeyName, downloadCodeForm);
				//????????????
				this.modelTemplates(fileName, entity, model, pKeyName, downloadCodeForm);
				//???????????????
				this.generate(entity, model, dataSourceUtil, fileName, downloadCodeForm, pKeyName, childPKeyMap);
			}
			if (entity.getType() == 3) {
				//???????????????
				downloadCodeForm.setPackageName("com.sinosoft.ie.booster");
				downloadCodeForm.setModuleName("workflow");
				downloadCodeForm.setDescription(mainTableComment);
				WorkVueGenUtil.htmlTemplates(fileName, entity, downloadCodeForm, model, configValueUtil, pKeyName);
				WorkVueGenUtil.generate(entity, dataSourceUtil, fileName, downloadCodeForm, userInfo, configValueUtil);
			}
			if (entity.getType() == 5) {
				//app???????????????
				AppIndexGenUtil.htmlTemplates(fileName, entity, downloadCodeForm, model, configValueUtil, pKeyName);
				AppIndexGenUtil.generate(entity, dataSourceUtil, fileName, downloadCodeForm, userInfo, configValueUtil);
			}
			return fileName;
		}
		return null;
	}


	@Override
	public void modelTemplates(String fileName, BaseVisualDevEntity entity, FormDataModel model, String pKeyName, DownloadCodeForm downloadCodeForm) {
		List<TableModel> list = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
		TableModel mainTableModel = list.stream().filter(t -> "1".equals(t.getTypeId())).findFirst().get();
		//???????????????????????????????????????????????????????????????????????????
		Map<String, Object> columnDataMap = JsonUtil.stringToMap(entity.getColumnData());
		//?????????????????????
		Boolean hasPage = (Boolean) columnDataMap.get("hasPage");

		List<FieLdsModel> modelList = JsonUtil.getJsonToList(model.getFields(), FieLdsModel.class);

		List<FieLdsModel> searchList = JsonUtil.getJsonToList(columnDataMap.get("searchList"), FieLdsModel.class);
		List<ColumnListField> columnList = JsonUtil.getJsonToList(columnDataMap.get("columnList"), ColumnListField.class);

		Template6Model temModel = new Template6Model();
		temModel.setServiceDirectory(configValueUtil.getCodeTempPath());
		temModel.setCreateDate(DateUtil.getNow());
		temModel.setCreateUser(GenBaseInfo.AUTHOR);
		temModel.setCopyright(GenBaseInfo.COPYRIGHT);
		temModel.setVersion(GenBaseInfo.VERSION);
		temModel.setDescription(model.getClassName() + "??????");

		Map<String, Object> modelMap = new HashMap<>(16);
		modelMap.put("tableModel", mainTableModel);
		modelMap.put("searchList", searchList);
		modelMap.put("columnList", columnList);
		modelMap.put("formDataList", modelList);
		modelMap.put("className", model.getClassName());
		modelMap.put("areasName", model.getAreasName());
		modelMap.put("pKeyName", pKeyName);
		//?????????????????????
		list.remove(0);
		List<SubClassModel> subList = new ArrayList<>();
		for (TableModel tableModel : list) {
			SubClassModel subClassModel = new SubClassModel();
			subClassModel.setSubKey(tableModel.getTable());
			subClassModel.setSubValue(tableModel.getTable());
			subList.add(subClassModel);
		}
		modelMap.put("subtables", subList);
		//?????????????????????
		modelMap.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		modelMap.put("genInfo", temModel);
		VueGenUtil.htmlTemplates(configValueUtil.getCodeTempPath() + fileName, modelMap, "model", model.getClassName(), hasPage);
	}

	@Override
	public void htmlTemplates(String fileName, BaseVisualDevEntity entity, FormDataModel model, FormDataModel htmlModel, List<String> childTable, String pKeyName, DownloadCodeForm downloadCodeForm) {

		Map<String, Object> map = new HashMap<>(16);
		map.put("module", model.getAreasName());
		map.put("className", model.getClassName());
		Map<String, Object> modelMap = JsonUtil.entityToMap(model);
		map.put("pKeyName", pKeyName);
		map.putAll(modelMap);
		map.put("htmlFields", JsonUtil.getJsonToList(htmlModel.getFields(), FieLdsModel.class));

		//???????????????????????????????????????????????????????????????????????????
		Map<String, Object> columnDataMap = JsonUtil.stringToMap(entity.getColumnData());
		map.put("columnData", columnDataMap);
		Template6Model genInfo = new Template6Model();
		map.put("genInfo", genInfo);
		//????????????
		int num = 0;
		//????????????????????????????????????
		List<FieLdsModel> list = JsonUtil.getJsonToList(model.getFields(), FieLdsModel.class);
		for (FieLdsModel model1 : list) {
			ConfigModel configModel = model1.getConfig();
			if (configModel.getDefaultValue() instanceof String) {
				configModel.setValueType("String");
			}
			if (configModel.getDefaultValue() == null) {
				configModel.setValueType("undefined");
			}
			//??????????????????????????????
			if ("cascader".equals(configModel.getBoosKey())) {
				Map<String, Object> propsMap = JsonUtil.stringToMap(model1.getProps().getProps());
				model1.setMultiple(String.valueOf(propsMap.get("multiple")));
			}
			//??????
			if (BoosKeyConst.CHILD_TABLE.equals(configModel.getBoosKey())) {
				ChildrenModel child = new ChildrenModel();
				List<FieLdsModel> childList = JsonUtil.getJsonToList(configModel.getChildren(), FieLdsModel.class);
				for (FieLdsModel childModel : childList) {
					ConfigModel childConfig = childModel.getConfig();
					if (childConfig.getDefaultValue() instanceof String) {
						childConfig.setValueType("String");
					}
					if (childConfig.getDefaultValue() == null) {
						childConfig.setValueType("undefined");
					}
					//??????????????????????????????
					if (BoosKeyConst.CASCADER.equals(childConfig.getBoosKey())) {
						Map<String, Object> propsMap = JsonUtil.stringToMap(childModel.getProps().getProps());
						childModel.setMultiple(String.valueOf(propsMap.get("multiple")));
					}
					//???????????????????????????
					if (DataTypeConst.STATIC.equals(String.valueOf(childConfig.getDataType()))) {
						SlotModel slotModel = childModel.getSlot();
						if (slotModel != null) {
							List<SlotOptionModel> options = JsonUtil.getJsonToList(slotModel.getOptions(), SlotOptionModel.class);
							slotModel.setOptions(JsonUtilEx.getObjectToString(options));
							List<Map<String, Object>> appOptions = new ArrayList<>();
							for (SlotOptionModel option : options) {
								Map<String, Object> childMap = new HashMap<>(16);
								childMap.put("label", option.getFullName());
								childMap.put("value", option.getId());
								appOptions.add(childMap);
							}
							slotModel.setAppOptions(JsonUtilEx.getObjectToString(appOptions));
							childModel.setSlot(slotModel);
						}
					}
				}
				configModel.setChildren(childList);
				child.setChildrenList(childList);
				child.setTableModel(model1.getVModel());
				String name = childTable.get(num);
				child.setClassName(name);
				num++;
				String vModelName = name + "_entity_list";
				model1.setConfig(configModel);
				model1.setVModel(vModelName);
			}
		}
		map.put("fields", list);
		map.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		VueGenUtil.htmlTemplates(model.getServiceDirectory() + fileName, map, "vue", model.getClassName(), null);
	}


	public void setCode(FormDataModel formDataModel, DataSourceUtil dataSourceUtil, String fileName, DownloadCodeForm downloadCodeForm, BaseVisualDevEntity entity, String pKeyName, Map<String, Object> childpKeyMap) throws SQLException {
		List<TableModel> tablesList = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
		TableModel mainTableModel = tablesList.stream().filter(t -> "1".equals(t.getTypeId())).findFirst().get();
		String tableName = mainTableModel.getTable();
		//???????????????????????????????????????????????????????????????????????????
		Map<String, Object> columnDataMap = JsonUtil.stringToMap(entity.getColumnData());
		List<FieLdsModel> filterFieldList = JsonUtil.getJsonToList(formDataModel.getFields(), FieLdsModel.class);

		BoosterUser userInfo = SecurityUtils.getUser();
		Template6Model model = new Template6Model();
		model.setColumnListFields(JsonUtil.getJsonToList(columnDataMap.get("columnList"), ColumnListField.class));
		model.setClassName(tableName);
		model.setServiceDirectory(configValueUtil.getCodeTempPath());
		model.setCreateDate(DateUtil.getNow());
		model.setCreateUser(GenBaseInfo.AUTHOR);
		model.setVersion(GenBaseInfo.VERSION);
		model.setCopyright(GenBaseInfo.COPYRIGHT);
		model.setDescription(downloadCodeForm.getDescription());

		columnDataMap.put("genInfo", model);
		columnDataMap.put("areasName", downloadCodeForm.getModuleName());
		columnDataMap.put("formList", filterFieldList);
		columnDataMap.put("pKeyName", pKeyName);
		columnDataMap.put("childPKeyMap", childpKeyMap);
		columnDataMap.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));

		if (dataSourceUtil.getUrl().contains(DbType.ORACLE.getDb())) {
			columnDataMap.put("dbType", DbType.ORACLE.getDb());
		}

		//????????????
		tablesList.remove(0);
		//??????????????????
		for (TableModel tableModel : tablesList) {
			for (Map.Entry<String, Object> entryMap : childpKeyMap.entrySet()) {
				if (tableModel.getTable().equals(entryMap.getKey())) {
					tableModel.setTableKey(String.valueOf(entryMap.getValue()));
				}
			}
		}
		model.setDbTableRelation(JsonUtil.getJsonToList(tablesList, TableModel.class));
		List<SubClassModel> subList = new ArrayList<>();
		for (TableModel tableModel : tablesList) {
			SubClassModel subClassModel = new SubClassModel();
			subClassModel.setSubKey(tableModel.getTable());
			subClassModel.setSubValue(tableModel.getTable());
			subList.add(subClassModel);
		}
		columnDataMap.put("subtables", subList);
		//????????????????????????
		columnDataMap.put("main", new Object());


		CustomGenerator mpg = new CustomGenerator(columnDataMap);
		// ????????????
		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		// ?????????ActiveRecord??????????????????false
		gc.setActiveRecord(false);
		// XML ????????????
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(true);
		gc.setAuthor(userInfo.getUsername());
		gc.setOpen(false);

		// ?????????????????????????????? %s ?????????????????????????????????
		String className = model.toUpperAttr(tableName);
		gc.setEntityName(className + GenFileNameSuffix.ENTITY);
		gc.setMapperName(className + GenFileNameSuffix.MAPPER);
		gc.setXmlName(className + GenFileNameSuffix.MAPPER_XML);
		gc.setServiceName(className + GenFileNameSuffix.SERVICE);
		gc.setServiceImplName(className + GenFileNameSuffix.SERVICEIMPL);
		gc.setControllerName(className + GenFileNameSuffix.CONTROLLER);
		mpg.setGlobalConfig(gc);

		// ???????????????
		DataSourceConfig dsc = GetGenDataSourceUtil.getGenDataSource(Convert.toLong(downloadCodeForm.getDataSourceId()));
		mpg.setDataSource(dsc);

		// ????????????
		StrategyConfig strategy = new StrategyConfig();
		strategy.setEntityLombokModel(true);
		// ??????????????????
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// ??????????????????
		strategy.setInclude(tableName);
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);

		// ?????????
		PackageConfig pc = new PackageConfig();
		pc.setParent(String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		mpg.setPackageInfo(pc);

		// ???????????????
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		String javaPath = model.getServiceDirectory() + fileName + File.separator
				+ CommonConstants.BACK_END_PROJECT + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + downloadCodeForm.getPackageName().replace(".", File.separator)
				+ File.separator + downloadCodeForm.getModuleName() + File.separator;
		String resourcesPath = model.getServiceDirectory() + fileName + File.separator
				+ CommonConstants.BACK_END_PROJECT + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator;
		focList.add(new FileOutConfig("template/TemplateCode6/java/Controller.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "controller" + File.separator + tableInfo.getControllerName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/Entity.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "entity" + File.separator + tableInfo.getEntityName() + StringPool.DOT_JAVA;
			}
		});

		focList.add(new FileOutConfig("template/TemplateCode6/java/Mapper.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/Mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return resourcesPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_XML;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/Service.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "service" + File.separator + tableInfo.getServiceName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/ServiceImpl.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "service" + File.separator + "impl" + File.separator + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setTemplate(new TemplateConfig().setXml(null).setMapper(null).setController(null).setEntity(null).setService(null).setServiceImpl(null));
		mpg.setCfg(cfg);
		// ????????????
		mpg.execute();
	}

	/**
	 * ?????????model
	 *
	 * @param dataSourceUtil
	 * @param fileName
	 * @param entity
	 * @param table
	 * @param downloadCodeForm
	 * @return
	 * @throws SQLException
	 */
	private String childTable(DataSourceUtil dataSourceUtil, String fileName, BaseVisualDevEntity entity, String table, DownloadCodeForm downloadCodeForm) throws SQLException {
		Map<String, Object> columnData = JsonUtil.stringToMap(entity.getColumnData());

		@Cleanup Connection conn = VisualUtils.getTableConn();
		//????????????
		String pKeyName = VisualUtils.getpKey(conn, table);
		columnData.put("pKeyName", pKeyName);

		Template6Model model = new Template6Model();
		model.setColumnListFields(JsonUtil.getJsonToList(columnData.get("columnList"), ColumnListField.class));

		model.setClassName(table);
		model.setServiceDirectory(configValueUtil.getCodeTempPath());
		model.setCreateDate(DateUtil.getNow());
		model.setCreateUser(GenBaseInfo.AUTHOR);
		model.setVersion(GenBaseInfo.VERSION);
		model.setCopyright(GenBaseInfo.COPYRIGHT);
		model.setDescription(table);
		columnData.put("areasName", downloadCodeForm.getModuleName());
		columnData.put("genInfo", model);
		columnData.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));

		CustomGenerator mpg = new CustomGenerator(columnData);
		// ????????????
		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		// ?????????ActiveRecord??????????????????false
		gc.setActiveRecord(false);
		// XML ????????????
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(true);
		gc.setAuthor(model.getCreateUser());
		gc.setOpen(false);

		// ?????????????????????????????? %s ?????????????????????????????????
		String className = model.toUpperAttr(table);
		gc.setEntityName(className + GenFileNameSuffix.ENTITY);
		gc.setMapperName(className + GenFileNameSuffix.MAPPER);
		gc.setXmlName(className + GenFileNameSuffix.MAPPER_XML);
		gc.setServiceName(className + GenFileNameSuffix.SERVICE);
		gc.setServiceImplName(className + GenFileNameSuffix.SERVICEIMPL);
		mpg.setGlobalConfig(gc);

		// ???????????????
		DataSourceConfig dsc = GetGenDataSourceUtil.getGenDataSource(Convert.toLong(downloadCodeForm.getDataSourceId()));
		mpg.setDataSource(dsc);

		// ????????????
		StrategyConfig strategy = new StrategyConfig();
		strategy.setEntityLombokModel(true);
		// ??????????????????
		strategy.setNaming(NamingStrategy.underline_to_camel);
		if (dataSourceUtil.getUrl().contains(DbType.MYSQL.getDb())) {
			// ??????????????????
			strategy.setInclude(table);
		} else if (dataSourceUtil.getUrl().contains(DbType.SQL_SERVER.getDb())) {
			// ??????????????????
			strategy.setInclude(table);
		} else if (dataSourceUtil.getUrl().contains(DbType.ORACLE.getDb())) {
			// ??????????????????
			strategy.setInclude(table);
		}
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);

		// ?????????
		PackageConfig pc = new PackageConfig();
		pc.setParent(String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		mpg.setPackageInfo(pc);

		// ???????????????
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		String javaPath = model.getServiceDirectory() + fileName + File.separator
				+ CommonConstants.BACK_END_PROJECT + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + downloadCodeForm.getPackageName().replace(".", File.separator)
				+ File.separator + downloadCodeForm.getModuleName() + File.separator;
		String resourcesPath = model.getServiceDirectory() + fileName + File.separator
				+ CommonConstants.BACK_END_PROJECT + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator;
		focList.add(new FileOutConfig("template/TemplateCode6/java/Entity.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "entity" + File.separator + tableInfo.getEntityName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/Mapper.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/Mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return resourcesPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_XML;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/Service.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "service" + File.separator + tableInfo.getServiceName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode6/java/ServiceImpl.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "service" + File.separator + "impl" + File.separator + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setTemplate(new TemplateConfig().setXml(null).setMapper(null).setController(null).setEntity(null).setService(null).setServiceImpl(null));
		mpg.setCfg(cfg);
		// ????????????
		mpg.execute();
		return fileName;
	}


	/**
	 * ??????java??????
	 *
	 * @param entity
	 * @param formDataModel
	 * @param dataSourceUtil
	 * @param fileName
	 * @param downloadCodeForm
	 * @param pKeyName
	 * @param childpKeyMap
	 * @throws SQLException
	 */
	@Override
	public void generate(BaseVisualDevEntity entity, FormDataModel formDataModel, DataSourceUtil dataSourceUtil, String fileName, DownloadCodeForm downloadCodeForm, String pKeyName, Map<String, Object> childpKeyMap) throws SQLException {
		List<TableModel> list = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
		//????????????
		int i = 0;
		for (TableModel model : list) {
			if ("1".equals(model.getTypeId())) {
				setCode(formDataModel, dataSourceUtil, fileName, downloadCodeForm, entity, pKeyName, childpKeyMap);
			} else if ("0".equals(model.getTypeId())) {
				childTable(dataSourceUtil, fileName, entity, model.getTable(), downloadCodeForm);
				i++;
			}
		}
	}
}
