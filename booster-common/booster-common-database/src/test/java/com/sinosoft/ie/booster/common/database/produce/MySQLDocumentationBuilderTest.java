package com.sinosoft.ie.booster.common.database.produce;

import com.sinosoft.ie.booster.common.database.Configuration;
import com.sinosoft.ie.booster.common.database.engine.EngineConfig;
import com.sinosoft.ie.booster.common.database.engine.EngineFileType;
import com.sinosoft.ie.booster.common.database.engine.EngineTemplateType;
import com.sinosoft.ie.booster.common.database.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static com.sinosoft.ie.booster.common.database.common.Constants.fileOutputDir;

/**
 * 构建测试
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/22 18:13
 */
public class MySQLDocumentationBuilderTest extends AbstractDocumentationExecute {

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
		//设置可以获取tables remarks信息
		hikariConfig.addDataSourceProperty("useInformationSchema", "true");
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaximumPoolSize(5);
		DataSource dataSource = new HikariDataSource(hikariConfig);
		//生成配置
		EngineConfig engineConfig = EngineConfig.builder()
				//生成文件路径
				.fileOutputDir(fileOutputDir)
				//打开目录
				.openOutputDir(true)
				//文件类型
				.fileType(EngineFileType.WORD)
				//生成模板实现
				.produceType(EngineTemplateType.velocity).build();

		//忽略表
		ArrayList<String> ignoreTableName = new ArrayList<>();
		ignoreTableName.add("test");
		//忽略表前缀
		ArrayList<String> ignorePrefix = new ArrayList<>();
		ignorePrefix.add("test");
		//忽略表后缀
		ArrayList<String> ignoreSuffix = new ArrayList<>();
		ignoreSuffix.add("test");
		ProcessConfig processConfig = ProcessConfig.builder()
				//忽略表名
				.ignoreTableName(ignoreTableName)
				//忽略表前缀
				.ignoreTablePrefix(ignorePrefix)
				//忽略表后缀
				.ignoreTableSuffix(ignoreSuffix).build();
		//配置
		Configuration config = Configuration.builder()
				//版本
				.version("1.0.0")
				//描述
				.description("数据库设计文档生成")
				//数据源
				.dataSource(dataSource)
				//生成配置
				.engineConfig(engineConfig)
				//生成配置
				.produceConfig(processConfig).build();
		execute(config);
	}

	/**
	 * 获取配置文件
	 *
	 * @return {@link Properties}
	 */
	@Override
	public String getConfigProperties() {
		return System.getProperty("user.dir") + "/src/main/resources/properties/mysql.properties";
	}
}
