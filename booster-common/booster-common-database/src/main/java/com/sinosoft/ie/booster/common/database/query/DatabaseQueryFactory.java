package com.sinosoft.ie.booster.common.database.query;

import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;
import com.sinosoft.ie.booster.common.database.util.JdbcUtils;
import lombok.Getter;

import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * 数据库查询工厂
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/18 14:15
 */
public class DatabaseQueryFactory implements Serializable {
	/**
	 * DataSource
	 */
	@Getter
	private DataSource dataSource;

	/**
	 * 构造函数私有化
	 * 禁止通过new方式实例化对象
	 */
	private DatabaseQueryFactory() {
	}

	/**
	 * 构造函数
	 *
	 * @param source {@link DataSource}
	 */
	public DatabaseQueryFactory(DataSource source) {
		dataSource = source;
	}

	/**
	 * 获取配置的数据库类型实例
	 *
	 * @return {@link DatabaseQuery} 数据库查询对象
	 */
	public DatabaseQuery newInstance() {
		try {
			//获取数据库URL 用于判断数据库类型
			String url = this.getDataSource().getConnection().getMetaData().getURL();
			//获取实现类
			Class<? extends DatabaseQuery> query = JdbcUtils.getDbType(url).getImplClass();
			//获取有参构造
			Constructor<? extends DatabaseQuery> constructor = query
					.getConstructor(DataSource.class);
			//实例化
			return constructor.newInstance(dataSource);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
				| InvocationTargetException | SQLException e) {
			throw ExceptionUtils.mpe(e);
		}
	}
}
