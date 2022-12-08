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
 * 随访管理信息
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:52
 */
@Data
@TableName("flup")
@ApiModel(value = "随访管理信息")
public class FlupEntity  {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 外键
     */
    @ApiModelProperty(value = "外键")
    private Long infcRptId;

    /**
     * 本次随访日期
     */
    @ApiModelProperty(value = "本次随访日期")
    private Date flupDate;

    /**
     * 随访方式代码
     */
    @ApiModelProperty(value = "随访方式代码")
    private String flupTypeCode;

    /**
     * 随访状态代码
     */
    @ApiModelProperty(value = "随访状态代码")
    private String flupStsCode;

    /**
     * 症状代码
     */
    @ApiModelProperty(value = "症状代码")
    private String sympCode;

    /**
     * 本次随访内容代码
     */
    @ApiModelProperty(value = "本次随访内容代码")
    private String flupCont;

    /**
     * 调查人姓名
     */
    @ApiModelProperty(value = "调查人姓名")
    private String inveName;

    /**
     * 随访报告机构所属地区代码
     */
    @ApiModelProperty(value = "随访报告机构所属地区代码")
    private String flupRptOrgAreaCode;

    /**
     * 随访报告机构代码
     */
    @ApiModelProperty(value = "随访报告机构代码")
    private String flupRptOrgCode;

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
