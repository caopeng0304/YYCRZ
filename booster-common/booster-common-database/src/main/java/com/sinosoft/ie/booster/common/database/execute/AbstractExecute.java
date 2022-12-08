package com.sinosoft.ie.booster.common.database.execute;

import com.sinosoft.ie.booster.common.database.Configuration;
import com.sinosoft.ie.booster.common.database.util.Assert;
import com.sinosoft.ie.booster.common.database.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.DESCRIPTION;

/**
 * 抽象执行
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/4/1 22:51
 */
public abstract class AbstractExecute implements Execute {
	/**
	 * LOGGER
	 */
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Configuration config;

	public AbstractExecute(Configuration config) {
		Assert.notNull(config, "Configuration can not be empty!");
		this.config = config;
	}

	/**
	 * 获取文档名称
	 *
	 * @param database {@link String}
	 * @return {@link String} 名称
	 */
	String getDocName(String database) {
		//自定义文件名称不为空
		if (StringUtils.isNotBlank(config.getEngineConfig().getFileName())) {
			return config.getEngineConfig().getFileName();
		}
		//描述
		String description = config.getDescription();
		if (StringUtils.isBlank(description)) {
			description = DESCRIPTION;
		}
		//版本号
		String version = config.getVersion();
		if (StringUtils.isBlank(version)) {
			return database + "_" + description;
		}
		return database + "_" + description + "_" + version;
	}
}
