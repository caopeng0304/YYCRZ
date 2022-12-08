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
@TableName("task_from")
@ApiModel(value = "")
public class TaskFromEntity  {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 管养单位
     */
    @ApiModelProperty(value = "管养单位")
    private String manageUnit;

    /**
     * 养护单位
     */
    @ApiModelProperty(value = "养护单位")
    private String maintainUnit;

    /**
     * 工程类别/任务类型:1零星维修  2设施恢复  3完善增设
     */
    @ApiModelProperty(value = "工程类别/任务类型:1零星维修  2设施恢复  3完善增设")
    private String taskType;

    /**
     * 任务单编号
     */
    @ApiModelProperty(value = "任务单编号")
    private String taskNumber;

    /**
     * 工程名称
     */
    @ApiModelProperty(value = "工程名称")
    private String projectName;

    /**
     * 工程部位
     */
    @ApiModelProperty(value = "工程部位")
    private String projectPosition;

    /**
     * 路线编号
     */
    @ApiModelProperty(value = "路线编号")
    private String roadCode;

    /**
     * 路线名称
     */
    @ApiModelProperty(value = "路线名称")
    private String roadName;

    /**
     * 路线开始桩号
     */
    @ApiModelProperty(value = "路线开始桩号")
    private String startStation;

    /**
     * 路线结束桩号
     */
    @ApiModelProperty(value = "路线结束桩号")
    private String endStation;

    /**
     * 任务来源1日常巡查单 2经常检查记录单  3文件类  4会议纪要类  5工作联系单  6路产赔偿单
     */
    @ApiModelProperty(value = "任务来源1日常巡查单 2经常检查记录单  3文件类  4会议纪要类  5工作联系单  6路产赔偿单")
    private String taskSource;

    /**
     * 任务来源编号
     */
    @ApiModelProperty(value = "任务来源编号")
    private String taskSourceNum;

    /**
     * 下发费用
     */
    @ApiModelProperty(value = "下发费用")
    private String issueCost;

    /**
     * 任务描述
     */
    @ApiModelProperty(value = "任务描述")
    private String taskDescribe;

    /**
     * 签收状态1待接收 2待分派 3施工中 4完工
     */
    @ApiModelProperty(value = "签收状态1待接收 2待分派 3施工中 4完工")
    private Integer signStatus;

    /**
     * 任务单状态：1待提交  2审核中  3已通过  4驳回
     */
    @ApiModelProperty(value = "任务单状态：1待提交  2审核中  3已通过  4驳回")
    private Integer taskStatus;

    /**
     * 任务要求
     */
    @ApiModelProperty(value = "任务要求")
    private String taskRequire;

    /**
     * 完成时限
     */
    @ApiModelProperty(value = "完成时限")
    private Integer completeTimeLimit;

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