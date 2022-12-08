package com.sinosoft.ie.booster.visualdev.util;

import cn.hutool.core.date.DateUtil;
import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
@Slf4j
public class JdbcUtil {

	//-----------------------------jdbc工具类----------------------------------------------------

	/**
	 * 自定义sql语句(适合增、删、改)
	 */
	public static int custom(Connection conn, String sql) throws DataException {
		int result = 0;
		try {
			//开启事务
			conn.setAutoCommit(false);
			String[] sqls = sql.split(";");
			PreparedStatement preparedStatement;
			for (String sqlOne : sqls) {
				preparedStatement = conn.prepareStatement(sqlOne);
				preparedStatement.executeUpdate();
				result++;
			}
			//提交事务
			conn.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException q) {
				throw new DataException("数据错误:" + q.getMessage());
			}

			throw new DataException("数据错误:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * 自定义sql语句(查)
	 */
	public static ResultSet query(Connection conn, String sql) throws DataException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			//开启事务
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			//提交事务
			conn.commit();
			return resultSet;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DataException("数据错误:" + e.getMessage());
		}
	}

	/**
	 * 连接Connection
	 *
	 * @param userName 用户名
	 * @param password 密码
	 * @param host     ip地址
	 * @param port     端口
	 * @return
	 */
	public static Connection getConn(String dataType, String userName, String password, String host, Integer port, String dbName) {
		final Connection[] conn = {null};
		Callable<String> task = () -> {
			//执行耗时代码
			try {
				DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDbLinkType(dataType);
				Assert.notNull(dataSourceEnum, "datasource is null");
				Class.forName(dataSourceEnum.getDriverClassName());
				String connectionUrl = dataSourceEnum.getUrlTemplate()
						.replace("{host}", host)
						.replace("{port}", String.valueOf(port))
						.replace("{dbName}", dbName);
				conn[0] = DriverManager.getConnection(connectionUrl, userName, password);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return "jdbc连接成功";
		};
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(task);
		try {
			//设置超时时间
			String rst = future.get(3L, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			log.error("连接数据库" + dbName + "超时");
		} catch (Exception e) {
			log.error("获取数据异常204," + e.getMessage());
		} finally {
			executorService.shutdown();
		}

		return conn[0];
	}

	/**
	 * 连接Connection
	 *
	 * @param userName        用户名
	 * @param password        密码
	 * @param url             url
	 * @param driverClassName driverClassName
	 * @return
	 */
	public static Connection getConn(String userName, String password, String url, String driverClassName) {
		final Connection[] conn = {null};
		Callable<String> task = () -> {
			//执行耗时代码
			try {
				DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceByDriverClassName(driverClassName);
				Assert.notNull(dataSourceEnum, "datasource is null");
				Class.forName(dataSourceEnum.getDriverClassName());
				conn[0] = DriverManager.getConnection(url, userName, password);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return "jdbc连接成功";
		};
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(task);
		try {
			//设置超时时间
			String rst = future.get(3L, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			log.error("连接数据库超时");
		} catch (Exception e) {
			log.error("获取数据异常254," + Arrays.toString(e.getStackTrace()));
		} finally {
			executorService.shutdown();
		}

		return conn[0];
	}


	//----------------------jdbc转成对象String------------------

	/**
	 * jdbc 多条数据查询转成list
	 *
	 * @param rs result 查询的结果
	 * @return
	 */
	public static List<Map<String, Object>> convertListString(ResultSet rs) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<>(16);
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getString(i));
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * jdbc 单条数据查询转成map
	 *
	 * @param rs result 查询的结果
	 * @return
	 */
	public static Map<String, Object> convertMapString(ResultSet rs) {
		Map<String, Object> map = new TreeMap<>();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					map.put(md.getColumnName(i), rs.getString(i));
				}
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			return map;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * jdbc 多条数据查询转成list
	 *
	 * @param rs result 查询的结果，datetime转为时间戳
	 * @return
	 */
	public static List<Map<String, Object>> convertList2(ResultSet rs) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<>(16);
				for (int i = 1; i <= columnCount; i++) {
					if (rs.getObject(i) != null) {
						if (md.getColumnType(i) != 93) {
							rowData.put(md.getColumnLabel(i), String.valueOf(rs.getObject(i)));
						} else {
							rowData.put(md.getColumnLabel(i), DateUtil.parse(String.valueOf(rs.getObject(i))).getTime());
						}
					}
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * 数据建模更新表字段
	 */
	public static int upTableFields(Connection conn, String delSql, String crSql, String ramTable, String newTable) throws DataException {
		int result;
		try {
			//开启事务
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement = conn.prepareStatement(crSql);
			preparedStatement.executeUpdate();
			//提交事务
			conn.commit();
			PreparedStatement preparedStatementDrop = conn.prepareStatement(delSql + ";");
			preparedStatementDrop.executeUpdate();
			PreparedStatement preparedStatementReName = conn.prepareStatement("alter table " + ramTable + " rename to " + newTable + ";");
			result = preparedStatementReName.executeUpdate();
			//提交事务
			conn.commit();
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DataException("数据错误:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

}
