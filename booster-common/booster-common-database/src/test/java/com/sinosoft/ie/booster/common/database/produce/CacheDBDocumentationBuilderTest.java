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
 * CacheDB 数据库文档生成测试
 *
 * @author <a href ='jxh98@foxmail.com'>Josway</a>
 * @date 2020/6/28
 * @since JDK 1.8
 */
public class CacheDBDocumentationBuilderTest extends AbstractDocumentationExecute {

	@Test
	void build() throws IOException {
		//数据源
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(getDriver());
		hikariConfig.setJdbcUrl(getUrl());
		hikariConfig.setUsername(getUserName());
		hikariConfig.setPassword(getPassword());
		hikariConfig.setSchema(getSchema());
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaximumPoolSize(5);
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
		return System.getProperty("user.dir") + "/src/main/resources/properties/cachedb.properties";
	}
}
