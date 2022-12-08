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
 * 传染病个案报告卡
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:51
 */
@Data
@TableName("infc_rpt_gz")
@ApiModel(value = "传染病个案报告卡")
public class InfcRptGzEntity  {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 卡片编号
     */
    @ApiModelProperty(value = "卡片编号")
    private String infecCardNo;

    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名")
    private String patientName;

    /**
     * 身份证件类别代码
     */
    @ApiModelProperty(value = "身份证件类别代码")
    private String idDocTypeCode;

    /**
     * 身份证件号码
     */
    @ApiModelProperty(value = "身份证件号码")
    private String idNo;

    /**
     * 性别代码
     */
    @ApiModelProperty(value = "性别代码")
    private String gedCode;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthdate;

    /**
     * 国籍代码
     */
    @ApiModelProperty(value = "国籍代码")
    private String natiCode;

    /**
     * 国际地区代码
     */
    @ApiModelProperty(value = "国际地区代码")
    private String natiAreaCode;

    /**
     * 民族代码
     */
    @ApiModelProperty(value = "民族代码")
    private String raceCode;

    /**
     * 学历代码
     */
    @ApiModelProperty(value = "学历代码")
    private String eduCode;

    /**
     * 户籍代码
     */
    @ApiModelProperty(value = "户籍代码")
    private String hregTypeCode;

    /**
     * 户籍地区代码
     */
    @ApiModelProperty(value = "户籍地区代码")
    private String hergAreaCode;

    /**
     * 户籍详细地址
     */
    @ApiModelProperty(value = "户籍详细地址")
    private String hregAddrDetl;

    /**
     * 现住址类别代码
     */
    @ApiModelProperty(value = "现住址类别代码")
    private String preAddrTypeCode;

    /**
     * 现住地区代码
     */
    @ApiModelProperty(value = "现住地区代码")
    private String preAddrAreaCode;

    /**
     * 现住详细地址
     */
    @ApiModelProperty(value = "现住详细地址")
    private String preAddrDetl;

    /**
     * 病人所属地类型
     */
    @ApiModelProperty(value = "病人所属地类型")
    private String ptLocalTypeCode;

    /**
     * 工作单位/学校名称
     */
    @ApiModelProperty(value = "工作单位/学校名称")
    private String orgSchName;

    /**
     * 人群分类代码
     */
    @ApiModelProperty(value = "人群分类代码")
    private String popuTypeCode;

    /**
     * 人群分类其他
     */
    @ApiModelProperty(value = "人群分类其他")
    private String popuTypeOtr;

    /**
     * 婚姻状况代码
     */
    @ApiModelProperty(value = "婚姻状况代码")
    private String mariStaCode;

    /**
     * 联系人/监护人姓名
     */
    @ApiModelProperty(value = "联系人/监护人姓名")
    private String cnta;

    /**
     * 联系人/监护人电话号码
     */
    @ApiModelProperty(value = "联系人/监护人电话号码")
    private String cntaTel;

    /**
     * 联系人/监护人与本人关系代码
     */
    @ApiModelProperty(value = "联系人/监护人与本人关系代码")
    private String cntaRelCode;

    /**
     * 报告地区代码
     */
    @ApiModelProperty(value = "报告地区代码")
    private String rptAreaCode;

    /**
     * 报告单位代码
     */
    @ApiModelProperty(value = "报告单位代码")
    private String rptOrgCode;

    /**
     * 报告日期
     */
    @ApiModelProperty(value = "报告日期")
    private Date repDate;

    /**
     * 发病日期
     */
    @ApiModelProperty(value = "发病日期")
    private Date illBegDate;

    /**
     * 诊断日期时间
     */
    @ApiModelProperty(value = "诊断日期时间")
    private Date diagDate;

    /**
     * 疾病诊断代码
     */
    @ApiModelProperty(value = "疾病诊断代码")
    private String diagDiseCode;

    /**
     * 疾病名称
     */
    @ApiModelProperty(value = "疾病名称")
    private String diagDiseName;

    /**
     * 诊断状态
     */
    @ApiModelProperty(value = "诊断状态")
    private String diagStaTypeCode;

    /**
     * 病例分类
     */
    @ApiModelProperty(value = "病例分类")
    private String caseTypeCode;

    /**
     * 临床严重程度
     */
    @ApiModelProperty(value = "临床严重程度")
    private String clicServCode;

    /**
     * 输入病例类型
     */
    @ApiModelProperty(value = "输入病例类型")
    private String impCaseCode;

    /**
     * 输入来源地
     */
    @ApiModelProperty(value = "输入来源地")
    private String impSrcNationCode;

    /**
     * 输入来源地-其他国家
     */
    @ApiModelProperty(value = "输入来源地-其他国家")
    private String impSrcNationOtrName;

    /**
     * 死亡日期
     */
    @ApiModelProperty(value = "死亡日期")
    private Date dieDate;

    /**
     * 医师姓名
     */
    @ApiModelProperty(value = "医师姓名")
    private String drName;

    /**
     * 添加地区
     */
    @ApiModelProperty(value = "添加地区")
    private String crtAreaCode;

    /**
     * 添加机代号
     */
    @ApiModelProperty(value = "添加机代号")
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

    /**
     * 总体审核状态
     */
    @ApiModelProperty(value = "总体审核状态")
    private String totAudiSts;
}
