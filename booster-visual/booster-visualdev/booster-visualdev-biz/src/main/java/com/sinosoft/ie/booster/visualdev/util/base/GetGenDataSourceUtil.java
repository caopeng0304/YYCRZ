package com.sinosoft.ie.booster.visualdev.util.base;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.visualdev.entity.BaseDbLinkEntity;
import com.sinosoft.ie.booster.visualdev.service.BaseDbLinkService;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;

/**
 * 可视化开发功能表
 *
 * @author booster开发平台组
 * @since 2021-04-07
 */

public class GetGenDataSourceUtil {

	public static DataSourceConfig getGenDataSource(Long dataSourceId) {

		// 数据源配置
		SourceUtil sourceUtil = new SourceUtil();

		BaseDbLinkService dblinkService = SpringContextHolder.getBean(BaseDbLinkService.class);
		DataSourceConfig dsc;
		BaseDbLinkEntity linkEntity = dblinkService.getInfo(dataSourceId);
		if (linkEntity != null) {
			return sourceUtil.dbConfig(linkEntity);
		}

		DataSourceUtil dataSourceUtil = SpringContextHolder.getBean(DataSourceUtil.class);
		dsc = sourceUtil.dbConfig();
		if (dataSourceUtil.getUrl().contains(DbType.ORACLE.getDb())) {
			String schema = dataSourceUtil.getUserName();
			//oracle 默认 schema=username
			dsc.setSchemaName(schema.toUpperCase());
		}
		return dsc;
	}

}
