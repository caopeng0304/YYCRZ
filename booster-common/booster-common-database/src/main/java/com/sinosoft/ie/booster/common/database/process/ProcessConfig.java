package com.sinosoft.ie.booster.common.database.process;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 数据处理
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/4/1 22:22
 */
@Data
@Builder
public class ProcessConfig implements Serializable {
	/**
	 * 忽略表名
	 */
	private List<String> ignoreTableName;
	/**
	 * 忽略表前缀
	 */
	private List<String> ignoreTablePrefix;
	/**
	 * 忽略表后缀
	 */
	private List<String> ignoreTableSuffix;
	/**
	 * 指定生成表名
	 *
	 * @see "1.0.3"
	 */
	private List<String> designatedTableName;
	/**
	 * 指定生成表前缀
	 *
	 * @see "1.0.3"
	 */
	private List<String> designatedTablePrefix;
	/**
	 * 指定生成表后缀
	 *
	 * @see "1.0.3"
	 */
	private List<String> designatedTableSuffix;
}
