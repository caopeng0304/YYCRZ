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
 * 可视化开发功能表
 *
 * @author booster开发平台组
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
		//去除模板字段下划线
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
			//先去除多级控件
			filterFieldList = VisualUtils.deleteMore(filterFieldList);
			//再去除无意义控件
			filterFieldList = VisualUtils.deleteVmodel(filterFieldList);

			model.setFields(JSON.toJSONString(filterFieldList));
			String fileName = RandomUtil.uuId();
			//初始化模板
			VelocityEnum.init.initVelocity();

			//获取主键
			@Cleanup Connection conn = VisualUtils.getTableConn();
			//获取主键
			String pKeyName = VisualUtils.getpKey(conn, mainTable);

			//获取其他子表的主键
			Map<String, Object> childPKeyMap = new HashMap<>(16);
			for (TableModel tableModel : list) {
				String childKey = VisualUtils.getpKey(conn, mainTable);
				childPKeyMap.put(tableModel.getTable(), childKey);
			}
			//判断子表名称
			List<String> childTb = list.stream().skip(1L).map(TableModel::getTable).collect(Collectors.toList());

			Set<String> set = new HashSet<>(childTb);
			boolean result = childTb.size() == set.size();
			if (!result) {
				return "名称不能重复";
			}
			if (entity.getType() == 4) {
				this.htmlTemplates(fileName, entity, model, htmlModel, childTb, pKeyName, downloadCodeForm);
				//生成模型
				this.modelTemplates(fileName, entity, model, pKeyName, downloadCodeForm);
				//生成功能类
				this.generate(entity, model, dataSourceUtil, fileName, downloadCodeForm, pKeyName, childPKeyMap);
			}
			if (entity.getType() == 3) {
				//工作流模板
				downloadCodeForm.setPackageName("com.sinosoft.ie.booster");
				downloadCodeForm.setModuleName("workflow");
				downloadCodeForm.setDescription(mainTableComment);
				WorkVueGenUtil.htmlTemplates(fileName, entity, downloadCodeForm, model, configValueUtil, pKeyName);
				WorkVueGenUtil.generate(entity, dataSourceUtil, fileName, downloadCodeForm, userInfo, configValueUtil);
			}
			if (entity.getType() == 5) {
				//app代码生成器
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
		//取出列表数据中的查询列表和数据列表，禁止用实体去接
		Map<String, Object> columnDataMap = JsonUtil.stringToMap(entity.getColumnData());
		//记录是否有分页
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
		temModel.setDescription(model.getClassName() + "模型");

		Map<String, Object> modelMap = new HashMap<>(16);
		modelMap.put("tableModel", mainTableModel);
		modelMap.put("searchList", searchList);
		modelMap.put("columnList", columnList);
		modelMap.put("formDataList", modelList);
		modelMap.put("className", model.getClassName());
		modelMap.put("areasName", model.getAreasName());
		modelMap.put("pKeyName", pKeyName);
		//表名与类名对应
		list.remove(0);
		List<SubClassModel> subList = new ArrayList<>();
		for (TableModel tableModel : list) {
			SubClassModel subClassModel = new SubClassModel();
			subClassModel.setSubKey(tableModel.getTable());
			subClassModel.setSubValue(tableModel.getTable());
			subList.add(subClassModel);
		}
		modelMap.put("subtables", subList);
		//设置包结构前缀
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

		//取出列表数据中的查询列表和数据列表，禁止用实体去接
		Map<String, Object> columnDataMap = JsonUtil.stringToMap(entity.getColumnData());
		map.put("columnData", columnDataMap);
		Template6Model genInfo = new Template6Model();
		map.put("genInfo", genInfo);
		//子表坐标
		int num = 0;
		//添加判断默认值类型的字段
		List<FieLdsModel> list = JsonUtil.getJsonToList(model.getFields(), FieLdsModel.class);
		for (FieLdsModel model1 : list) {
			ConfigModel configModel = model1.getConfig();
			if (configModel.getDefaultValue() instanceof String) {
				configModel.setValueType("String");
			}
			if (configModel.getDefaultValue() == null) {
				configModel.setValueType("undefined");
			}
			//级联判断多选还是单选
			if ("cascader".equals(configModel.getBoosKey())) {
				Map<String, Object> propsMap = JsonUtil.stringToMap(model1.getProps().getProps());
				model1.setMultiple(String.valueOf(propsMap.get("multiple")));
			}
			//子表
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
					//级联判断多选还是单选
					if (BoosKeyConst.CASCADER.equals(childConfig.getBoosKey())) {
						Map<String, Object> propsMap = JsonUtil.stringToMap(childModel.getProps().getProps());
						childModel.setMultiple(String.valueOf(propsMap.get("multiple")));
					}
					//选择框都转成字符串
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
		//取出列表数据中的查询列表和数据列表，禁止用实体去接
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

		//去除主表
		tablesList.remove(0);
		//设置子表主键
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
		//标识生成主表代码
		columnDataMap.put("main", new Object());


		CustomGenerator mpg = new CustomGenerator(columnDataMap);
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		// 不需要ActiveRecord特性的请改为false
		gc.setActiveRecord(false);
		// XML 二级缓存
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(true);
		gc.setAuthor(userInfo.getUsername());
		gc.setOpen(false);

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		String className = model.toUpperAttr(tableName);
		gc.setEntityName(className + GenFileNameSuffix.ENTITY);
		gc.setMapperName(className + GenFileNameSuffix.MAPPER);
		gc.setXmlName(className + GenFileNameSuffix.MAPPER_XML);
		gc.setServiceName(className + GenFileNameSuffix.SERVICE);
		gc.setServiceImplName(className + GenFileNameSuffix.SERVICEIMPL);
		gc.setControllerName(className + GenFileNameSuffix.CONTROLLER);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = GetGenDataSourceUtil.getGenDataSource(Convert.toLong(downloadCodeForm.getDataSourceId()));
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setEntityLombokModel(true);
		// 表名生成策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 需要生成的表
		strategy.setInclude(tableName);
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		mpg.setPackageInfo(pc);

		// 自定义配置
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
		// 执行生成
		mpg.execute();
	}

	/**
	 * 子表的model
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
		//获取主键
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
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		// 不需要ActiveRecord特性的请改为false
		gc.setActiveRecord(false);
		// XML 二级缓存
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(true);
		gc.setAuthor(model.getCreateUser());
		gc.setOpen(false);

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		String className = model.toUpperAttr(table);
		gc.setEntityName(className + GenFileNameSuffix.ENTITY);
		gc.setMapperName(className + GenFileNameSuffix.MAPPER);
		gc.setXmlName(className + GenFileNameSuffix.MAPPER_XML);
		gc.setServiceName(className + GenFileNameSuffix.SERVICE);
		gc.setServiceImplName(className + GenFileNameSuffix.SERVICEIMPL);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = GetGenDataSourceUtil.getGenDataSource(Convert.toLong(downloadCodeForm.getDataSourceId()));
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setEntityLombokModel(true);
		// 表名生成策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		if (dataSourceUtil.getUrl().contains(DbType.MYSQL.getDb())) {
			// 需要生成的表
			strategy.setInclude(table);
		} else if (dataSourceUtil.getUrl().contains(DbType.SQL_SERVER.getDb())) {
			// 需要生成的表
			strategy.setInclude(table);
		} else if (dataSourceUtil.getUrl().contains(DbType.ORACLE.getDb())) {
			// 需要生成的表
			strategy.setInclude(table);
		}
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		mpg.setPackageInfo(pc);

		// 自定义配置
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
		// 执行生成
		mpg.execute();
		return fileName;
	}


	/**
	 * 生成java代码
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
		//生成代码
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
