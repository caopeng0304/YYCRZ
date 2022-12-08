package com.sinosoft.ie.booster.common.database.common;

import com.sinosoft.ie.booster.common.database.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Properties
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/6/20 22:45
 */
public interface Properties {
	/**
	 * 获取配置文件
	 *
	 * @return {@link java.util.Properties}
	 */
	String getConfigProperties();

	/**
	 * 获取驱动
	 *
	 * @return {@link String}
	 */
	default String getDriver() throws IOException {
		java.util.Properties properties = getProperties();
		return properties.get("driver").toString();
	}

	/**
	 * 获取URL
	 *
	 * @return {@link String}
	 */
	default String getUrl() throws IOException {
		java.util.Properties properties = getProperties();
		return properties.get("url").toString();
	}

	/**
	 * 获取password
	 *
	 * @return {@link String}
	 */
	default String getPassword() throws IOException {
		java.util.Properties properties = getProperties();
		return properties.get("password").toString();
	}

	/**
	 * 获取username
	 *
	 * @return {@link String}
	 */
	default String getUserName() throws IOException {
		java.util.Properties properties = getProperties();
		return properties.get("username").toString();
	}

	/**
	 * 获取username
	 *
	 * @return {@link String
	 * @throws IOException
	 */
	default String getSchema() throws IOException {
		java.util.Properties properties = getProperties();
		return properties.get("schema").toString();
	}

	/**
	 * 获取properties内容
	 *
	 * @return {@link java.util.Properties}
	 * @throws IOException IOException
	 */
	default java.util.Properties getProperties() throws IOException {
		File path = FileUtils.getFileByPath(getConfigProperties());
		java.util.Properties properties = new java.util.Properties();
		FileInputStream inputStream = new FileInputStream(Objects.requireNonNull(path));
		properties.load(inputStream);
		return properties;
	}
}
