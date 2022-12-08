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
 * 检验检测信息
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:52
 */
@Data
@TableName("test")
@ApiModel(value = "检验检测信息")
public class TestEntity  {

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
     * 检查标本号
     */
    @ApiModelProperty(value = "检查标本号")
    private String sampNo;

    /**
     * 标本采样日期时间
     */
    @ApiModelProperty(value = "标本采样日期时间")
    private Date sampTakeTime;

    /**
     * 检查日期
     */
    @ApiModelProperty(value = "检查日期")
    private Date takeSampDate;

    /**
     * 样本类别代码
     */
    @ApiModelProperty(value = "样本类别代码")
    private String sampTypeCode;

    /**
     * 采样单位所属地区代码
     */
    @ApiModelProperty(value = "采样单位所属地区代码")
    private String takeOrgAreaCode;

    /**
     * 采样单位代码
     */
    @ApiModelProperty(value = "采样单位代码")
    private String takeOrgCode;

    /**
     * 接收标本日期时间
     */
    @ApiModelProperty(value = "接收标本日期时间")
    private Date recvSampDate;

    /**
     * 检测类别代码
     */
    @ApiModelProperty(value = "检测类别代码")
    private String testTypeCode;

    /**
     * 检验项目代码
     */
    @ApiModelProperty(value = "检验项目代码")
    private String testItemCode;

    /**
     * 检测方法代码
     */
    @ApiModelProperty(value = "检测方法代码")
    private String testMethCode;

    /**
     * 检测结果代码
     */
    @ApiModelProperty(value = "检测结果代码")
    private String testRestCode;

    /**
     * 检测结果计量单位代码
     */
    @ApiModelProperty(value = "检测结果计量单位代码")
    private String testRestMunitCode;

    /**
     * 检测报告机构所属地区代码
     */
    @ApiModelProperty(value = "检测报告机构所属地区代码")
    private String testRptOrgAreaCode;

    /**
     * 检测报告机构代码
     */
    @ApiModelProperty(value = "检测报告机构代码")
    private String testRptOrgCode;

    /**
     * 检测报告日期
     */
    @ApiModelProperty(value = "检测报告日期")
    private Date testRptDate;

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
