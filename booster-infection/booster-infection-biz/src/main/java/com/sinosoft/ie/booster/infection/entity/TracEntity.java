package com.sinosoft.ie.booster.infection.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 追踪信息管理
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:52
 */
@Data
@TableName("trac")
@ApiModel(value = "追踪信息管理")
public class TracEntity  {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 报告卡主键
     */
    @ApiModelProperty(value = "报告卡主键")
    private Long infcRptId;

    /**
     * 追踪地区编码
     */
    @ApiModelProperty(value = "追踪地区编码")
    private String tracAreaCode;

    /**
     * 追踪单位编码
     */
    @ApiModelProperty(value = "追踪单位编码")
    private String tracOrgCode;

    /**
     * 开始追踪时间
     */
    @ApiModelProperty(value = "开始追踪时间")
    private Date begTracCode;

    /**
     * 追踪状态编码
     */
    @ApiModelProperty(value = "追踪状态编码")
    private String tracStsCode;

    /**
     * 追踪方法
     */
    @ApiModelProperty(value = "追踪方法")
    private String tracMethCode;

    /**
     * 追踪到位时间
     */
    @ApiModelProperty(value = "追踪到位时间")
    private Date tracYesTime;

    /**
     * 追踪未到位的原因
     */
    @ApiModelProperty(value = "追踪未到位的原因")
    private String tracNoCaus;

    /**
     * 添加地区
     */
    @ApiModelProperty(value = "添加地区")
    private String crtAreaCode;

    /**
     * 添加机构代号
     */
    @ApiModelProperty(value = "添加机构代号")
    private String crtOrgCode;

    /**
     * 添加机构名称
     */
    @ApiModelProperty(value = "添加机构名称")
    private String crtOrgName;

    /**
     * 添加用户ID
     */
    @ApiModelProperty(value = "添加用户ID")
    private Integer crtUsrId;

    /**
     * 添加用户名称
     */
    @ApiModelProperty(value = "添加用户名称")
    private String crtUsrName;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private Date crtTime;

    /**
     * 修改地区
     */
    @ApiModelProperty(value = "修改地区")
    private String uptAreaCode;

    /**
     * 修改机构
     */
    @ApiModelProperty(value = "修改机构")
    private String uptOrgCode;

    /**
     * 修改机构名称
     */
    @ApiModelProperty(value = "修改机构名称")
    private String uptOrgName;

    /**
     * 修改用户ID
     */
    @ApiModelProperty(value = "修改用户ID")
    private Integer uptUsrId;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date uptTime;

    /**
     * 是否有效
     */
    @ApiModelProperty(value = "是否有效")
    private String sts;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String rmk;
}
