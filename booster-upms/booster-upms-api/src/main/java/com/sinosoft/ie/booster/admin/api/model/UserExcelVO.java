package com.sinosoft.ie.booster.admin.api.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户excel 对应的实体
 *
 * @author lengleng
 * @since 2021/8/4
 */
@Data
@ColumnWidth(30)
public class UserExcelVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ExcelProperty("用户编号")
	private String userId;

	/**
	 * 用户名
	 */
	@ExcelProperty("用户名")
	private String username;

	/**
	 * 手机号
	 */
	@ExcelProperty("手机号")
	private String phone;

	/**
	 * 部门名称
	 */
	@ExcelProperty("部门名称")
	private String deptName;

	/**
	 * 上级主管
	 */
	@ExcelProperty("上级主管")
	private String manager;

	/**
	 * 角色列表
	 */
	@ExcelProperty("角色")
	private String roleNameList;

	/**
	 * 锁定标记
	 */
	@ExcelProperty("锁定标记,0:正常,9:已锁定")
	private String lockFlag;

	/**
	 * 创建时间
	 */
	@ExcelProperty(value = "创建时间")
	private LocalDateTime createTime;

}
