package com.sinosoft.ie.booster.common.database;

import com.sinosoft.ie.booster.common.database.engine.EngineConfig;
import com.sinosoft.ie.booster.common.database.process.ProcessConfig;
import com.sinosoft.ie.booster.common.database.util.Assert;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.sql.DataSource;
import java.io.Serializable;

/**
 * Screw 配置入口
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2020/3/19
 */
@Data
@NoArgsConstructor
public class Configuration implements Serializable {

	/**
	 * 组织
	 */
	private String organization;
	/**
	 * url
	 */
	private String organizationUrl;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 数据源，这里直接使用@see{@link DataSource}接口，好处就，可以使用任何数据源
	 */
	private DataSource dataSource;
	/**
	 * 生成配置
	 */
	private ProcessConfig produceConfig;
	/**
	 * 引擎配置，关于数据库文档生成相关配置
	 */
	private EngineConfig engineConfig;

	/**
	 * 构造函数
	 *
	 * @param title         {@link String} 标题
	 * @param organization  {@link String} 机构
	 * @param version       {@link String} 版本
	 * @param description   {@link String} 描述
	 * @param dataSource    {@link DataSource} 数据源
	 * @param produceConfig {@link ProcessConfig} 生成配置
	 * @param engineConfig  {@link EngineConfig} 生成配置
	 */
	private Configuration(String organization, String organizationUrl, String title, String version,
						  String description, DataSource dataSource, ProcessConfig produceConfig,
						  EngineConfig engineConfig) {
		Assert.notNull(dataSource, "DataSource can not be empty!");
		Assert.notNull(engineConfig, "EngineConfig can not be empty!");
		this.title = title;
		this.organizationUrl = organizationUrl;
		this.organization = organization;
		this.version = version;
		this.description = description;
		this.dataSource = dataSource;
		this.engineConfig = engineConfig;
		this.produceConfig = produceConfig;
	}

	/**
	 * builder
	 *
	 * @return {@link ConfigurationBuilder}
	 */
	public static ConfigurationBuilder builder() {
		return new ConfigurationBuilder();
	}

	@ToString
	public static class ConfigurationBuilder {
		private String organization;
		private String organizationUrl;
		private String title;
		private String version;
		private String description;
		private DataSource dataSource;
		private ProcessConfig produceConfig;
		private EngineConfig engineConfig;

		ConfigurationBuilder() {
		}

		public ConfigurationBuilder organization(String org) {
			this.organization = org;
			return this;
		}

		public ConfigurationBuilder organizationUrl(String orgUrl) {
			this.organizationUrl = orgUrl;
			return this;
		}

		public ConfigurationBuilder title(String title) {
			this.title = title;
			return this;
		}

		public ConfigurationBuilder version(String version) {
			this.version = version;
			return this;
		}

		public ConfigurationBuilder description(String description) {
			this.description = description;
			return this;
		}

		public ConfigurationBuilder dataSource(DataSource dataSource) {
			this.dataSource = dataSource;
			return this;
		}

		public ConfigurationBuilder produceConfig(ProcessConfig processConfig) {
			this.produceConfig = processConfig;
			return this;
		}

		public ConfigurationBuilder engineConfig(EngineConfig engineConfig) {
			this.engineConfig = engineConfig;
			return this;
		}

		/**
		 * build
		 *
		 * @return {@link Configuration}
		 */
		public Configuration build() {
			return new Configuration(this.organization, this.organizationUrl, this.title,
					this.version, this.description, this.dataSource, this.produceConfig,
					this.engineConfig);
		}
	}
}
