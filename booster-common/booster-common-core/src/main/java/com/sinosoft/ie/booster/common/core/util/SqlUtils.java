package com.sinosoft.ie.booster.common.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;

import java.util.List;

/**
 * Sql语句生成工具
 *
 * @author haocy
 */
public class SqlUtils {

	/**
	 * 生成建表Sql
	 *
	 * @param fieldInfos     以tab字符分割的字段信息，包括字段名、字段类型、字段注释
	 * @param tableName      表名
	 * @param tableComment   表注释
	 * @param primaryKeyName 主键名
	 * @param primaryKeyType 主键类型
	 * @return 建表Sql
	 */
	public static String genTableCreateSql(List<String> fieldInfos, String tableName, String tableComment, String primaryKeyName, String primaryKeyType) {
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("-- ----------------------------\n");
		sqlSb.append(String.format("-- Table structure for %s\n", tableName));
		sqlSb.append("-- ----------------------------\n");
		sqlSb.append(String.format("DROP TABLE IF EXISTS `%s`;\n", tableName));
		sqlSb.append(String.format("CREATE TABLE `%s`  (\n", tableName));
		sqlSb.append(String.format("  `%s` %s NOT NULL AUTO_INCREMENT COMMENT '主键',\n", primaryKeyName, primaryKeyType));
		for (String fieldInfo : fieldInfos) {
			String[] itemArray = fieldInfo.split("\t");
			if (itemArray.length == 3) {
				sqlSb.append(String.format("  `%s` %s COMMENT '%s',\n", itemArray[0], itemArray[1], itemArray[2]));
			}
		}
		sqlSb.append(String.format("  PRIMARY KEY (`%s`) USING BTREE\n", primaryKeyName));
		sqlSb.append(String.format(") ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '%s';\n", tableComment));
		return sqlSb.toString();
	}

	/**
	 * 生成字典Insert Sql
	 *
	 * @param rows            以tab字符分割的字典项信息，包括值、值含义
	 * @param dictType        字典类型
	 * @param dictName        字典名称
	 * @return 字典Insert Sql
	 */
	public static String genDictInsertSql(List<String> rows, String dictType, String dictName) {
		StringBuilder sqlSb = new StringBuilder();
		String now = DateUtil.now();
		sqlSb.append("-- ----------------------------\n");
		sqlSb.append(String.format("-- Records of %s\n", dictName));
		sqlSb.append("-- ----------------------------\n");
		sqlSb.append("BEGIN;\n");
		sqlSb.append("INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)\n");
		long dictId = IdUtil.getSnowflakeNextId();
		sqlSb.append(String.format("VALUES (%s, '%s', '%s', '%s', '0', '0', '%s', '%s');\n", dictId, dictType, dictName, dictName, now, now));
		int sort = 0;
		for (String row : rows) {
			String[] rowValues = row.split("\t");
			if (rowValues.length == 2) {
				sqlSb.append("INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)\n");
				long dictItemId = IdUtil.getSnowflakeNextId();
				sqlSb.append(String.format("VALUES (%s, %s, '%s', '%s', '%s', '%s', %s, '', '0', '%s', '%s');\n", dictItemId, dictId, rowValues[0], rowValues[1], dictType, rowValues[1], sort++, now, now));
			}
		}
		sqlSb.append("COMMIT;\n");
		return sqlSb.toString();
	}
}
