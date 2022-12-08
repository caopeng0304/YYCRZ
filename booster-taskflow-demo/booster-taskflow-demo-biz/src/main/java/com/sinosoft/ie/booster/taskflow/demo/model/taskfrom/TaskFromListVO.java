package com.sinosoft.ie.booster.taskflow.demo.model.taskfrom;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * TaskFrom模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-10 14:47:39
 */
@Data
@ApiModel
public class TaskFromListVO{

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

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
     * 任务来源1日常巡查单 2经常检查记录单  3文件类  4会议纪要类  5工作联系单  6路产赔偿单
     */
    @ApiModelProperty(value = "任务来源1日常巡查单 2经常检查记录单  3文件类  4会议纪要类  5工作联系单  6路产赔偿单")
    private String taskSource;

    /**
     * 下发费用
     */
    @ApiModelProperty(value = "下发费用")
    private String issueCost;

    /**
     * 任务单状态：1待提交  2审核中  3已通过  4驳回
     */
    @ApiModelProperty(value = "任务单状态：1待提交  2审核中  3已通过  4驳回")
    private Integer taskStatus;
}
