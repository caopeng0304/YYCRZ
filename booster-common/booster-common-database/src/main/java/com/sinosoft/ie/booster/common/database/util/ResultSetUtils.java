package com.sinosoft.ie.booster.common.database.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sinosoft.ie.booster.common.database.exception.QueryException;
import com.sinosoft.ie.booster.common.database.exception.ScrewException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import static com.alibaba.fastjson.serializer.SerializerFeature.WRITE_MAP_NULL_FEATURES;

/**
 * JDBC ResultSet Utils
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/20 15:32
 */
public class ResultSetUtils {

	/**
	 * 打印 ResultSet 结果
	 *
	 * @param rs {@link ResultSet}
	 * @throws SQLException SQLException
	 */
	public static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		// 获取列数
		int columnCount = resultSetMetaData.getColumnCount();
		// 保存当前列最大长度的数组
		int[] columnMaxLengths = new int[columnCount];
		// 缓存结果集,结果集可能有序,所以用ArrayList保存变得打乱顺序.
		ArrayList<String[]> results = new ArrayList<>();
		// 按行遍历
		while (rs.next()) {
			// 保存当前行所有列
			String[] columnStr = new String[columnCount];
			// 获取属性值.
			for (int i = 0; i < columnCount; i++) {
				// 获取一列
				columnStr[i] = rs.getString(i + 1);
				// 计算当前列的最大长度
				columnMaxLengths[i] = Math.max(columnMaxLengths[i],
						(columnStr[i] == null) ? 0 : columnStr[i].length());
				// 这里有个问题，获取的都是列的值，而非列名，如果列值少，而列名长呢，这里就是有问题的
				String columnName = resultSetMetaData.getColumnName(i + 1);
				columnMaxLengths[i] = Math.max(columnMaxLengths[i], columnName.length());
			}
			// 缓存这一行.
			results.add(columnStr);
		}
		printSeparator(columnMaxLengths);
		printColumnName(resultSetMetaData, columnMaxLengths);
		printSeparator(columnMaxLengths);
		// 遍历集合输出结果
		Iterator<String[]> iterator = results.iterator();
		String[] columnStr;
		while (iterator.hasNext()) {
			columnStr = iterator.next();
			for (int i = 0; i < columnCount; i++) {
				System.out.printf("|%" + columnMaxLengths[i] + "s", columnStr[i]);
			}
			System.out.println("|");
		}
		printSeparator(columnMaxLengths);
	}

	/**
	 * 输出列名.
	 *
	 * @param resultSetMetaData 结果集的元数据对象.
	 * @param columnMaxLengths  每一列最大长度的字符串的长度.
	 * @throws QueryException QueryException
	 */
	private static void printColumnName(ResultSetMetaData resultSetMetaData,
										int[] columnMaxLengths) throws SQLException {
		int columnCount = resultSetMetaData.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			System.out.printf("|%" + columnMaxLengths[i] + "s",
					resultSetMetaData.getColumnName(i + 1));
		}
		System.out.println("|");
	}

	/**
	 * 输出分隔符.
	 *
	 * @param columnMaxLengths 保存结果集中每一列的最长的字符串的长度.
	 */
	private static void printSeparator(int[] columnMaxLengths) {
		for (int columnMaxLength : columnMaxLengths) {
			System.out.print("+");
			for (int j = 0; j < columnMaxLength; j++) {
				System.out.print("-");
			}
		}
		System.out.println("+");
	}

	/**
	 * 结果转为JSON
	 *
	 * @param rs {@link ResultSet}
	 * @return {@link String}
	 */
	public static String resultSetToJson(ResultSet rs) {
		try {
			// json数组
			JSONArray array = new JSONArray();
			// 获取列数
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			// 遍历ResultSet中的每条数据
			while (rs.next()) {
				JSONObject object = new JSONObject();
				// 遍历每一列
				for (int i = 1; i <= columnCount; i++) {
					//列名
					String columnName = metaData.getColumnLabel(i);
					//根据列名取列值
					String value = rs.getString(columnName);
					object.put(columnName, value);
				}
				array.add(object);
			}
			return JSON.toJSONString(array, WRITE_MAP_NULL_FEATURES,
					SerializerFeature.PrettyFormat);
		} catch (SQLException e) {
			throw new ScrewException(e);
		}
	}
}
