package com.sinosoft.ie.booster.taskflow.demo.model.taskfrom;

import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromDetailEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * TaskFrom模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-10 14:47:39
 */
@Data
@ApiModel
public class TaskFromCrForm  {

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
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * 子表数据
     */
    @ApiModelProperty(value = "子表数据")
    private List<TaskFromDetailEntity> taskFromDetailEntityList;

}
