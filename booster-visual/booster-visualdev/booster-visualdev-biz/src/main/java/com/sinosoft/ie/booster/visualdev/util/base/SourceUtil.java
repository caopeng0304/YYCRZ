package com.sinosoft.ie.booster.visualdev.util.base;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.core.util.SpringContextHolder;
import com.sinosoft.ie.booster.visualdev.entity.BaseDbLinkEntity;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;
import org.springframework.util.Assert;

public class SourceUtil {
	public DataSourceConfig dbConfig() {
		DataSourceConfig dsc = new DataSourceConfig();
		DataSourceUtil dataSourceUtil = SpringContextHolder.getBean(DataSourceUtil.class);
		DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDriverClassName(dataSourceUtil.getDriverClassName());
		Assert.notNull(dataSourceEnum, "datasource is null");
		dsc.setDbType(dataSourceEnum.getDbType());
		dsc.setDriverName(dataSourceEnum.getDriverClassName());
		dsc.setUsername(dataSourceUtil.getUserName());
		dsc.setPassword(dataSourceUtil.getPassword());
		dsc.setUrl(dataSourceUtil.getUrl());
		return dsc;
	}

	public DataSourceConfig dbConfig(BaseDbLinkEntity linkEntity) {
		DataSourceConfig dsc = new DataSourceConfig();
		DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDbLinkType(linkEntity.getDbType());
		Assert.notNull(dataSourceEnum, "datasource is null");
		dsc.setDbType(dataSourceEnum.getDbType());
		dsc.setDriverName(dataSourceEnum.getDriverClassName());
		dsc.setUrl(dataSourceEnum.getUrlTemplate()
				.replace("{host}", linkEntity.getHost())
				.replace("{port}", String.valueOf(linkEntity.getPort()))
				.replace("{dbName}", linkEntity.getServiceName()));
		dsc.setUsername(linkEntity.getUserName());
		dsc.setPassword(linkEntity.getPassword());
		return dsc;
	}
}
