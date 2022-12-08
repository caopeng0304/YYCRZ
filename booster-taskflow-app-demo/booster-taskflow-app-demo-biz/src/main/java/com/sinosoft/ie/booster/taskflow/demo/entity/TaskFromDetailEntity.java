package com.sinosoft.ie.booster.taskflow.demo.entity;

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
 * 
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-11-01
 */
@Data
@TableName("task_from_detail")
@ApiModel(value = "")
public class TaskFromDetailEntity  {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 主表id
     */
    @ApiModelProperty(value = "主表id")
    private Long parentId;

    /**
     * 子目编码
     */
    @ApiModelProperty(value = "子目编码")
    private String catalogCode;

    /**
     * 子目名称
     */
    @ApiModelProperty(value = "子目名称")
    private String catalogName;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private String price;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private String count;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String money;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @ApiModelProperty(value = "是否删除  -1：已删除  0：正常")
    private String delFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
}