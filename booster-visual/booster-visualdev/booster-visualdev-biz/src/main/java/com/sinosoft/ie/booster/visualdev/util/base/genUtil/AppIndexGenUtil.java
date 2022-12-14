package com.sinosoft.ie.booster.visualdev.util.base.genUtil;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.model.base.ColumnDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.DownloadCodeForm;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.model.base.TableModel;
import com.sinosoft.ie.booster.visualdev.model.base.Template7.Template7Model;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormAllModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormColumnModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormColumnTableModel;
import com.sinosoft.ie.booster.visualdev.model.flowdynamic.FormEnum;
import com.sinosoft.ie.booster.visualdev.model.generater.GenBaseInfo;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.slot.SlotModel;
import com.sinosoft.ie.booster.visualdev.util.ConfigValueUtil;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.FormCloumnUtil;
import com.sinosoft.ie.booster.visualdev.util.base.GetGenDataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.CustomGenerator;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.VisualUtils;
import lombok.Cleanup;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AppIndexGenUtil {

	/**
	 * ????????????
	 *
	 * @param fileName         ???????????????
	 * @param downloadCodeForm ????????????
	 * @param model            ??????
	 * @param configValueUtil  ????????????
	 */
	public static void htmlTemplates(String fileName, BaseVisualDevEntity entity, DownloadCodeForm downloadCodeForm, FormDataModel model, ConfigValueUtil configValueUtil, String pKeyName) {
		Map<String, Object> map = new HashMap<>();
		//formTempJson
		FormDataModel formData = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
		List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
		List<FormAllModel> formAllModel = new ArrayList<>();
		FormCloumnUtil.recursionForm(list, formAllModel);

		//form?????????
		List<FormAllModel> mast = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> table = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		//????????????
		List<Map<String, Object>> child = new ArrayList<>();
		for (FormAllModel allModel : table) {
			FormColumnTableModel childList = allModel.getChildList();
			List<FormColumnModel> tableList = childList.getChildList();
			for (FormColumnModel columnModel : tableList) {
				FieLdsModel fieLdsModel = columnModel.getFieLdsModel();
				SlotModel slot = fieLdsModel.getSlot();
				if (slot != null) {
					slot.setAppOptions(slot.getOptions());
					fieLdsModel.setSlot(slot);
				}
			}
			childList.setChildList(tableList);
			Map<String, Object> childs = JsonUtil.entityToMap(childList);
			childs.put("className", new Template7Model().toUpperAttr(childList.getTableName()));
			child.add(childs);
		}
		//????????????
		for (int i = 0; i < mast.size(); i++) {
			FieLdsModel fieLdsModel = mast.get(i).getFormColumnModel().getFieLdsModel();
			ConfigModel configModel = fieLdsModel.getConfig();
			SlotModel slot = fieLdsModel.getSlot();
			String vModel = fieLdsModel.getVModel();
			if (configModel.getDefaultValue() instanceof String) {
				configModel.setValueType("String");
			}
			if (configModel.getDefaultValue() == null) {
				configModel.setValueType("undefined");
			}
			fieLdsModel.setConfig(configModel);
			if (slot != null) {
				slot.setAppOptions(slot.getOptions());
				fieLdsModel.setSlot(slot);
			}
			if (StrUtil.isEmpty(vModel)) {
				mast.remove(i);
			}
		}
		//??????
		map.put("module", downloadCodeForm.getModuleName());
		map.put("className", model.getClassName());
		map.put("formRef", model.getFormRef());
		map.put("formModel", model.getFormModel());
		map.put("size", model.getSize());
		map.put("labelPosition", model.getLabelPosition());
		map.put("labelWidth", model.getLabelWidth());
		map.put("formRules", model.getFormRules());
		map.put("gutter", model.getGutter());
		map.put("disabled", model.getDisabled());
		map.put("span", model.getSpan());
		map.put("formBtns", model.getFormBtns());
		map.put("idGlobal", model.getIdGlobal());
		map.put("popupType", model.getPopupType());
		map.put("form", formAllModel);

		//form???model
		Template7Model temModel = new Template7Model();
		temModel.setServiceDirectory(configValueUtil.getCodeTempPath());
		temModel.setCreateDate(DateUtil.daFormat(new Date()));
		temModel.setCreateUser(GenBaseInfo.AUTHOR);
		temModel.setCopyright("");
		temModel.setClassName(model.getClassName());
		temModel.setDescription(downloadCodeForm.getDescription());
		map.put("genInfo", temModel);
		map.put("modelName", model.getClassName());
		map.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));

		//??????
		String columnData = entity.getColumnData().trim();
		ColumnDataModel columnDataModel = JsonUtil.getJsonToBean(columnData, ColumnDataModel.class);
		map.put("page", Boolean.parseBoolean(columnDataModel.getHasPage()) ? "0" : "1");
		map.put("defaultSidx", columnDataModel.getDefaultSidx());
		map.put("sort", columnDataModel.getSort());
		map.put("columnList", JsonUtil.getJsonToListMap(columnDataModel.getColumnList()));
		map.put("sortList", JsonUtil.getJsonToListMap(columnDataModel.getSortList()));
		map.put("pageSize", columnDataModel.getPageSize());

		//??????
		map.put("children", child);
		map.put("fields", mast);
		pKeyName = pKeyName.toLowerCase().trim();
		map.put("pKeyName", pKeyName);
		map.put("searchList", JsonUtil.getJsonToListMap(columnDataModel.getSearchList()));

		htmlTemplates(model.getServiceDirectory() + fileName, map);

		//??????model
		for (FormAllModel allModel : table) {
			FormColumnTableModel childList = allModel.getChildList();
			Map<String, Object> objectMap = JsonUtil.entityToMap(childList);
			String className = temModel.toUpperAttr(childList.getTableName());
			objectMap.put("children", childList);
			objectMap.put("genInfo", temModel);
			objectMap.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
			objectMap.put("module", model.getAreasName());
			objectMap.put("className", className);
			childrenTemplates(model.getServiceDirectory() + fileName, objectMap, className, model.getClassName());
		}
	}

	/**
	 * ???????????????
	 *
	 * @param path      ??????
	 * @param template  ????????????
	 * @param className ????????????
	 * @return
	 */
	private static String getFileName(String path, String template, String className, String packName) {
		String modelPath = path + File.separator + CommonConstants.BACK_END_PROJECT + File.separator + "src"
				+ File.separator + "main" + File.separator + "java" + File.separator
				+ packName.replace(".", File.separator) + File.separator + "model"
				+ File.separator + className.toLowerCase();
		String htmlPath = path + File.separator + "html" + File.separator + className.toLowerCase();
		File htmlfile = new File(htmlPath);
		File modelfile = new File(modelPath);
		if (!htmlfile.exists()) {
			htmlfile.mkdirs();
		}
		if (!modelfile.exists()) {
			modelfile.mkdirs();
		}
		if (template.contains("index.vue.vm")) {
			className = "index";
			return htmlPath + File.separator + className + ".vue";
		}
		if (template.contains("form.vue.vm")) {
			className = "form";
			return htmlPath + File.separator + className + ".vue";
		}
		if (template.contains("Form.java.vm")) {
			return modelPath + File.separator + className + "Form.java";
		}
		if (template.contains("InfoVO.java.vm")) {
			return modelPath + File.separator + className + "InfoVO.java";
		}
		if (template.contains("ListVO.java.vm")) {
			return modelPath + File.separator + className + "ListVO.java";
		}
		if (template.contains("Model.java.vm")) {
			return modelPath + File.separator + className + "Model.java";
		}
		if (template.contains("Pagination.java.vm")) {
			return modelPath + File.separator + className + "Pagination.java";
		}
		return null;
	}

	/**
	 * ???????????????
	 *
	 * @return
	 */
	private static List<String> getTemplates() {
		List<String> templates = new ArrayList<>();
		templates.add("template/TemplateCode8/html/form.vue.vm");
		templates.add("template/TemplateCode8/html/index.vue.vm");
		templates.add("template/TemplateCode8/java/Form.java.vm");
		templates.add("template/TemplateCode8/java/InfoVO.java.vm");
		templates.add("template/TemplateCode8/java/ListVO.java.vm");
		templates.add("template/TemplateCode8/java/Pagination.java.vm");
		return templates;
	}

	/**
	 * ??????html??????
	 *
	 * @param path   ??????
	 * @param object ????????????
	 */
	private static void htmlTemplates(String path, Map<String, Object> object) {
		String packName = object.containsKey("packageName") ? object.get("packageName").toString() : "";
		List<String> templates = getTemplates();
		//????????????
		VelocityContext context = new VelocityContext();
		context.put("context", object);
		for (String template : templates) {
			// ????????????
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF_8);
			tpl.merge(context, sw);
			try {
				String className = object.get("className").toString();
				String fileNames = getFileName(path, template, className, packName);
				if (fileNames != null) {
					File file = new File(fileNames);
					if (!file.exists()) {
						file.createNewFile();
					}
					FileOutputStream fos = new FileOutputStream(file);
					IOUtils.write(sw.toString(), fos, Constants.UTF_8);
					IOUtils.closeQuietly(sw);
					IOUtils.closeQuietly(fos);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("??????????????????????????????" + e);
			}
		}
	}

	/**
	 * ??????html??????
	 *
	 * @param path   ??????
	 * @param object ????????????
	 */
	private static void childrenTemplates(String path, Map<String, Object> object, String className, String parentClassName) {
		String packName = object.containsKey("packageName") ? object.get("packageName").toString() : "";
		List<String> templates = new ArrayList<>();
		templates.add("template/TemplateCode8/java/Model.java.vm");
		//????????????
		VelocityContext context = new VelocityContext();
		context.put("context", object);
		for (String template : templates) {
			// ????????????
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF_8);
			tpl.merge(context, sw);
			try {
				String modelPath = path + File.separator + CommonConstants.BACK_END_PROJECT + File.separator + "src"
						+ File.separator + "main" + File.separator + "java" + File.separator
						+ packName.replace(".", File.separator) + File.separator + "model"
						+ File.separator + parentClassName.toLowerCase();
				String fileNames = modelPath + File.separator + className + "Model.java";
				File file = new File(fileNames);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(file);
				IOUtils.write(sw.toString(), fos, Constants.UTF_8);
				IOUtils.closeQuietly(sw);
				IOUtils.closeQuietly(fos);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("??????????????????????????????" + e);
			}
		}
	}

	/**
	 * ????????????
	 *
	 * @param dataSourceUtil   ?????????
	 * @param fileName         ???????????????
	 * @param downloadCodeForm ????????????
	 * @param entity           ??????
	 * @param userInfo         ??????
	 * @param configValueUtil  ????????????
	 */
	private static void setCode(DataSourceUtil dataSourceUtil, String fileName, DownloadCodeForm downloadCodeForm, BaseVisualDevEntity entity, BoosterUser userInfo, ConfigValueUtil configValueUtil) throws SQLException {
		//tableJson
		List<TableModel> tableModelList = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
		//???????????????
		String tableName = tableModelList.stream().filter(t -> "1".equals(t.getTypeId())).findFirst().get().getTable();
		Map<String, Object> columnData = new HashMap<>();
		Template7Model model = new Template7Model();
		model.setClassName(model.toUpperAttr(tableName));
		model.setServiceDirectory(configValueUtil.getCodeTempPath());
		model.setCreateDate(DateUtil.daFormat(new Date()));
		model.setCreateUser(GenBaseInfo.AUTHOR);
		model.setCopyright("");
		model.setDescription(downloadCodeForm.getDescription());

		//formTempJson
		FormDataModel formData = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
		List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
		List<FormAllModel> formAllModel = new ArrayList<>();
		FormCloumnUtil.recursionForm(list, formAllModel);
		//????????????
		List<FormAllModel> mast = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		List<FormAllModel> table = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getBoosKey())).collect(Collectors.toList());
		String billNo = "";
		List<FieLdsModel> system = new ArrayList<>();
		for (FormAllModel mastModel : mast) {
			FieLdsModel fieLdsModel = mastModel.getFormColumnModel().getFieLdsModel();
			ConfigModel configModel = fieLdsModel.getConfig();
			String boosKey = configModel.getBoosKey();
			if ("billRule".equals(boosKey) && StrUtil.isEmpty(billNo)) {
				billNo = configModel.getRule();
			}
			if (!"BOOSText".equals(boosKey) && !"divider".equals(boosKey)) {
				system.add(fieLdsModel);
			}
		}

		@Cleanup Connection conn = VisualUtils.getTableConn();
		//??????????????????
		String pKeyName = VisualUtils.getpKey(conn, tableName).toLowerCase().trim();
		//???????????????
		List<Map<String, Object>> child = new ArrayList<>();
		for (FormAllModel allModel : table) {
			FormColumnTableModel childList = allModel.getChildList();
			Map<String, Object> childs = JsonUtil.entityToMap(childList);
			TableModel tableModel = tableModelList.stream().filter(t -> t.getTable().equals(childList.getTableName())).findFirst().get();
			//??????????????????
			String childKeyName = VisualUtils.getpKey(conn, tableModel.getTable());
			String tableField = tableModel.getTableField().trim();
			childs.put("tableField", tableField);
			String relationField = tableModel.getRelationField().trim();
			childs.put("relationField", relationField);
			childs.put("className", model.toUpperAttr(tableModel.getTable()));
			String keyName = childKeyName.trim().toLowerCase();
			childs.put("chidKeyName", keyName);
			child.add(childs);
		}
		//???????????????
		ColumnDataModel columnDataModel = JsonUtil.getJsonToBean(entity.getColumnData().trim(), ColumnDataModel.class);
		columnData.put("page", Boolean.parseBoolean(columnDataModel.getHasPage()) ? "0" : "1");
		columnData.put("defaultSidx", columnDataModel.getDefaultSidx());
		columnData.put("sort", columnDataModel.getSort());
		columnData.put("searchList", JsonUtil.getJsonToListMap(columnDataModel.getSearchList()));

		columnData.put("module", downloadCodeForm.getModuleName());
		columnData.put("genInfo", model);
		columnData.put("modelName", model.getClassName());
		columnData.put("typeId", 1);
		columnData.put("system", system);
		columnData.put("child", child);
		columnData.put("billNo", billNo);
		columnData.put("pKeyName", pKeyName);
		columnData.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));

		CustomGenerator mpg = new CustomGenerator(columnData);
		// ????????????
		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		gc.setActiveRecord(false);// ?????????ActiveRecord??????????????????false
		gc.setEnableCache(false);// XML ????????????
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor(userInfo.getUsername());
		gc.setOpen(false);

		// ?????????????????????????????? %s ?????????????????????????????????
		gc.setEntityName(model.getClassName() + "Entity");
		gc.setMapperName(model.getClassName() + "Mapper");
		gc.setXmlName(model.getClassName() + "Mapper");
		gc.setServiceName(model.getClassName() + "Service");
		gc.setServiceImplName(model.getClassName() + "ServiceImpl");
		gc.setControllerName(model.getClassName() + "Controller");
		mpg.setGlobalConfig(gc);

		// ???????????????
		DataSourceConfig dsc = GetGenDataSourceUtil.getGenDataSource(Convert.toLong(downloadCodeForm.getDataSourceId()));
		mpg.setDataSource(dsc);

		// ????????????
		StrategyConfig strategy = new StrategyConfig();
		strategy.setEntityLombokModel(true);
		strategy.setNaming(NamingStrategy.underline_to_camel);// ??????????????????
		if (dataSourceUtil.getUrl().contains(DbType.MYSQL.getDb())) {
			strategy.setInclude(tableName); // ??????????????????
		} else if (dataSourceUtil.getUrl().contains(DbType.SQL_SERVER.getDb())) {
			strategy.setInclude(tableName); // ??????????????????
		} else if (dataSourceUtil.getUrl().contains(DbType.ORACLE.getDb())) {
			strategy.setInclude(tableName); // ??????????????????
		}
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);

		// ?????????
		PackageConfig pc = new PackageConfig();
		pc.setParent(String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		//pc.setModuleName(model.getAreasName().toLowerCase());
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

		focList.add(new FileOutConfig("template/TemplateCode8/java/Controller.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "controller" + File.separator + tableInfo.getControllerName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/Entity.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "entity" + File.separator + tableInfo.getEntityName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/Mapper.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/Mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return resourcesPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_XML;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/Service.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "service" + File.separator + tableInfo.getServiceName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/ServiceImpl.java.vm") {
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
	 * ????????????
	 *
	 * @param dataSourceUtil   ?????????
	 * @param fileName         ???????????????
	 * @param downloadCodeForm ?????????
	 * @param table            ??????
	 * @param configValueUtil  ????????????
	 */
	private static void childTable(DataSourceUtil dataSourceUtil, String fileName, DownloadCodeForm downloadCodeForm, String table, ConfigValueUtil configValueUtil) throws SQLException {
		Map<String, Object> columnData = new HashMap<>();

		@Cleanup Connection conn = VisualUtils.getTableConn();
		//????????????
		String pKeyName = VisualUtils.getpKey(conn, table);
		columnData.put("pKeyName", pKeyName);

		Template7Model model = new Template7Model();
		model.setClassName(table);
		model.setServiceDirectory(configValueUtil.getCodeTempPath());
		model.setCreateDate(DateUtil.daFormat(new Date()));
		model.setCreateUser(GenBaseInfo.AUTHOR);
		model.setCopyright("");
		model.setDescription(table);

		columnData.put("genInfo", model);
		columnData.put("packageName", String.format("%s.%s", downloadCodeForm.getPackageName(), downloadCodeForm.getModuleName()));
		CustomGenerator mpg = new CustomGenerator(columnData);
		// ????????????
		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		gc.setActiveRecord(false);// ?????????ActiveRecord??????????????????false
		gc.setEnableCache(false);// XML ????????????
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor(model.getCreateUser());
		gc.setOpen(false);

		// ?????????????????????????????? %s ?????????????????????????????????
		String className = model.toUpperAttr(table);
		gc.setEntityName(className + "Entity");
		gc.setMapperName(className + "Mapper");
		gc.setXmlName(className + "Mapper");
		gc.setServiceName(className + "Service");
		gc.setServiceImplName(className + "ServiceImpl");
		mpg.setGlobalConfig(gc);

		// ???????????????
		DataSourceConfig dsc = GetGenDataSourceUtil.getGenDataSource(Convert.toLong(downloadCodeForm.getDataSourceId()));
		mpg.setDataSource(dsc);

		// ????????????
		StrategyConfig strategy = new StrategyConfig();
		strategy.setEntityLombokModel(true);
		strategy.setNaming(NamingStrategy.underline_to_camel);// ??????????????????
		if (dataSourceUtil.getUrl().contains(DbType.MYSQL.getDb())) {
			strategy.setInclude(table); // ??????????????????
		} else if (dataSourceUtil.getUrl().contains(DbType.SQL_SERVER.getDb())) {
			strategy.setInclude(table); // ??????????????????
		} else if (dataSourceUtil.getUrl().contains(DbType.ORACLE.getDb())) {
			strategy.setInclude(table); // ??????????????????
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
		focList.add(new FileOutConfig("template/TemplateCode8/java/Entity.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "entity" + File.separator + tableInfo.getEntityName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/Mapper.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/Mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return resourcesPath + "mapper" + File.separator + tableInfo.getMapperName() + StringPool.DOT_XML;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/Service.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return javaPath + "service" + File.separator + tableInfo.getServiceName() + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("template/TemplateCode8/java/ServiceImpl.java.vm") {
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
	 * ???????????????
	 *
	 * @param entity           ??????
	 * @param dataSourceUtil   ?????????
	 * @param fileName         ???????????????
	 * @param downloadCodeForm ????????????
	 * @param userInfo         ??????
	 * @param configValueUtil  ????????????
	 */
	public static void generate(BaseVisualDevEntity entity, DataSourceUtil dataSourceUtil, String fileName, DownloadCodeForm downloadCodeForm, BoosterUser userInfo, ConfigValueUtil configValueUtil) throws SQLException {
		List<TableModel> list = JsonUtil.getJsonToList(entity.getRefTables(), TableModel.class);
		//????????????
		int i = 0;
		for (TableModel model : list) {
			if ("1".equals(model.getTypeId())) {
				setCode(dataSourceUtil, fileName, downloadCodeForm, entity, userInfo, configValueUtil);
			} else if ("0".equals(model.getTypeId())) {
				childTable(dataSourceUtil, fileName, downloadCodeForm, model.getTable(), configValueUtil);
				i++;
			}
		}
	}

}
