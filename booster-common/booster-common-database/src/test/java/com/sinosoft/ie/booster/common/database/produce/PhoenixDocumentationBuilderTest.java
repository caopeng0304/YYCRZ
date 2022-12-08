package com.sinosoft.ie.booster.common.database.produce;

import com.sinosoft.ie.booster.common.database.Configuration;
import com.sinosoft.ie.booster.common.database.engine.EngineConfig;
import com.sinosoft.ie.booster.common.database.engine.EngineFileType;
import com.sinosoft.ie.booster.common.database.engine.EngineTemplateType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static com.sinosoft.ie.booster.common.database.common.Constants.fileOutputDir;

/**
 * Phoenix 文档生成测试
 *
 * @author xielong.wang
 * Created by siaron.wang@gmail.com 2020/3/30 18:36
 */
public class PhoenixDocumentationBuilderTest extends AbstractDocumentationExecute {

	/**
	 * 构建
	 */
	@Test
	void build() throws IOException {
		//数据源
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(getDriver());
		hikariConfig.setJdbcUrl(getUrl());
		hikariConfig.setUsername(getUserName());
		hikariConfig.setPassword(getPassword());
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaximumPoolSize(5);
		Properties properties = new Properties();
		properties.put("phoenix.schema.isNamespaceMappingEnabled", true);
		properties.put("phoenix.schema.mapSystemTablesToNamespace", true);
		hikariConfig.setDataSourceProperties(properties);
		DataSource dataSource = new HikariDataSource(hikariConfig);
		//生成配置
		EngineConfig engineConfig = EngineConfig.builder()
				//生成文件路径
				.fileOutputDir(fileOutputDir)
				//文件类型
				.fileType(EngineFileType.HTML)
				//生成模板实现
				.produceType(EngineTemplateType.freemarker).build();
		//配置
		Configuration config = Configuration.builder()
				//版本
				.version("1.0.0")
				//描述
				.description("数据库设计文档生成")
				//数据源
				.dataSource(dataSource)
				//生成配置
				.engineConfig(engineConfig).build();

		execute(config);
	}

	/**
	 * 获取配置文件
	 *
	 * @return {@link Properties}
	 */
	@Override
	public String getConfigProperties() {
		return System.getProperty("user.dir") + "/src/main/resources/properties/phoenix.properties";
	}
}
