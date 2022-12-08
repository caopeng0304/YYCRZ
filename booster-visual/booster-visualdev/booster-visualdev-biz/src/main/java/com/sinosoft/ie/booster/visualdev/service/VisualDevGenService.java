package com.sinosoft.ie.booster.visualdev.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.model.base.DownloadCodeForm;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 可视化开发功能表
 *
 * @author booster开发平台组
 * @since 2021-04-02
 */
public interface VisualDevGenService extends IService<BaseVisualDevEntity> {


	void htmlTemplates(String fileName, BaseVisualDevEntity entity, FormDataModel model, FormDataModel htmlModel, List<String> childTable, String pKeyName, DownloadCodeForm downloadCodeForm) throws SQLException;

	void modelTemplates(String fileName, BaseVisualDevEntity entity, FormDataModel model, String pKeyName, DownloadCodeForm downloadCodeForm) throws SQLException;

	void generate(BaseVisualDevEntity entity, FormDataModel model, DataSourceUtil dataSourceUtil, String fileName, DownloadCodeForm downloadCodeForm, String pKeyName, Map<String, Object> childPKeyMap) throws SQLException;

	String codeGenerate(Long id, DownloadCodeForm downloadCodeForm) throws SQLException;
}
