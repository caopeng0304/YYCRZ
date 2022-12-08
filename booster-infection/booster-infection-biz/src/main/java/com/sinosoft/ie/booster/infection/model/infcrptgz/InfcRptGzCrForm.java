package com.sinosoft.ie.booster.infection.model.infcrptgz;

import com.sinosoft.ie.booster.infection.entity.FlupEntity;
import com.sinosoft.ie.booster.infection.entity.TestEntity;
import com.sinosoft.ie.booster.infection.entity.TracEntity;
import com.sinosoft.ie.booster.infection.entity.TrtEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * InfcRptGz模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:51
 */
@Data
@ApiModel(value = "InfcRptGz模型")
public class InfcRptGzCrForm  {

    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名")
    private String patientName;

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
    private Long birthdate;

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
     * 联系人/监护人电话号码
     */
    @ApiModelProperty(value = "联系人/监护人电话号码")
    private String cntaTel;

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
    private Long repDate;

    /**
     * 发病日期
     */
    @ApiModelProperty(value = "发病日期")
    private Long illBegDate;

    /**
     * 诊断日期时间
     */
    @ApiModelProperty(value = "诊断日期时间")
    private Long diagDate;

    /**
     * 疾病诊断代码
     */
    @ApiModelProperty(value = "疾病诊断代码")
    private String diagDiseCode;

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
     * 死亡日期
     */
    @ApiModelProperty(value = "死亡日期")
    private Long dieDate;

    /**
     * 医师姓名
     */
    @ApiModelProperty(value = "医师姓名")
    private String drName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String rmk;

    /**
     * 子表数据
     */
    @ApiModelProperty(value = "子表数据")
    private List<FlupEntity> flupEntityList;

    /**
     * 子表数据
     */
    @ApiModelProperty(value = "子表数据")
    private List<TestEntity> testEntityList;

    /**
     * 子表数据
     */
    @ApiModelProperty(value = "子表数据")
    private List<TracEntity> tracEntityList;

    /**
     * 子表数据
     */
    @ApiModelProperty(value = "子表数据")
    private List<TrtEntity> trtEntityList;

	private String freeApprover;

	private String status;

	private String flowId;
}