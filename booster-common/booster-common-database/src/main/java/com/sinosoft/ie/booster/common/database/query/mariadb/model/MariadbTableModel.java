package com.sinosoft.ie.booster.common.database.query.mariadb.model;

import com.sinosoft.ie.booster.common.database.mapping.MappingField;
import com.sinosoft.ie.booster.common.database.metadata.Table;
import lombok.Data;

/**
 * 表信息
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 20:22
 */
@Data
public class MariadbTableModel implements Table {

	private static final long serialVersionUID = -1917761911812533673L;

	/**
	 * tableCat
	 */
	@MappingField(value = "TABLE_CAT")
	private String tableCat;
	/**
	 * 表名
	 */
	@MappingField(value = "TABLE_NAME")
	private String tableName;
	/**
	 *
	 */
	@MappingField(value = "SELF_REFERENCING_COL_NAME")
	private Object selfReferencingColName;
	/**
	 *
	 */
	@MappingField(value = "TABLE_CAT")
	private Object tableSchem;
	/**
	 *
	 */
	@MappingField(value = "TYPE_SCHEM")
	private Object typeSchem;
	/**
	 *
	 */
	@MappingField(value = "TABLE_CAT")
	private Object typeCat;
	/**
	 * 表类型
	 */
	@MappingField(value = "TABLE_TYPE")
	private String tableType;
	/**
	 * 备注
	 */
	@MappingField(value = "REMARKS")
	private String remarks;
	/**
	 *
	 */
	@MappingField(value = "REF_GENERATION")
	private Object refGeneration;
	/**
	 * 类型名称
	 */
	@MappingField(value = "TYPE_NAME")
	private Object typeName;

}
