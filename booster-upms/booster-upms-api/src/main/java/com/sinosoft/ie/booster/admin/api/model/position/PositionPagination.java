package com.sinosoft.ie.booster.admin.api.model.position;

import com.sinosoft.ie.booster.common.core.model.Pagination;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SysPosition模型
 *
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-14 15:10:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class PositionPagination extends Pagination {

	private String deptId;
}