package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import com.sinosoft.ie.booster.common.core.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * ApsSystem模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class PassBasicInfoPaginationExportModel extends Pagination {

    private String selectKey;

    private String json;

    private String dataType;


    /**
     * 业务系统id
     */
    @ApiModelProperty(value = "业务系统id")
    private String systemId;

    /**
     * 业务系统key
     */
    @ApiModelProperty(value = "业务系统key")
    private String systemKey;

    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调地址")
    private String systemUrl;

    /**
     * 系统名称
     */
    @ApiModelProperty(value = "系统名称")
    private String systemName;

	/**
	 * 数据字典code
	 */
	@ApiModelProperty(value = "数据字典code")
	private String systemLibraryCode;

	/**
	 * 创建日期
	 */
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
}