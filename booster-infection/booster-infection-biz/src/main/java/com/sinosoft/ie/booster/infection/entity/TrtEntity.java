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
 * 治疗用药信息
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:52
 */
@Data
@TableName("trt")
@ApiModel(value = "治疗用药信息")
public class TrtEntity  {

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
     * 治疗日期
     */
    @ApiModelProperty(value = "治疗日期")
    private Date trtDate;

    /**
     * 治疗类型代码
     */
    @ApiModelProperty(value = "治疗类型代码")
    private String trtTypeCode;

    /**
     * 治疗方案代码
     */
    @ApiModelProperty(value = "治疗方案代码")
    private String trtPlanCode;

    /**
     * 开始治疗日期
     */
    @ApiModelProperty(value = "开始治疗日期")
    private Date begTrtDate;

    /**
     * 不良反应出现日期
     */
    @ApiModelProperty(value = "不良反应出现日期")
    private Date begAeDate;

    /**
     * 不良反应确诊日期
     */
    @ApiModelProperty(value = "不良反应确诊日期")
    private Date adrDiagDate;

    /**
     * 不良反应诊断代码
     */
    @ApiModelProperty(value = "不良反应诊断代码")
    private String adrDiagCode;

    /**
     * 不良反应发生药物代码
     */
    @ApiModelProperty(value = "不良反应发生药物代码")
    private String adrDrugCode;

    /**
     * 停止治疗日期
     */
    @ApiModelProperty(value = "停止治疗日期")
    private Date endTrtDate;

    /**
     * 停止治疗原因代码
     */
    @ApiModelProperty(value = "停止治疗原因代码")
    private String endTrtCausCode;

    /**
     * 病情转归代码
     */
    @ApiModelProperty(value = "病情转归代码")
    private String otcmCode;

    /**
     * 入院日期
     */
    @ApiModelProperty(value = "入院日期")
    private Date ihosDate;

    /**
     * 出院日期
     */
    @ApiModelProperty(value = "出院日期")
    private Date ohosDate;

    /**
     * 登记号/治疗号
     */
    @ApiModelProperty(value = "登记号/治疗号")
    private String caseNo;

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
