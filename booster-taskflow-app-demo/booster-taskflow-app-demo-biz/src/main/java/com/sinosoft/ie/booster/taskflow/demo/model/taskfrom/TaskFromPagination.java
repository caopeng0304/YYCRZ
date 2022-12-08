package com.sinosoft.ie.booster.taskflow.demo.model.taskfrom;

import com.sinosoft.ie.booster.common.core.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 *
 * task_from
 * 版本: V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class TaskFromPagination extends Pagination {

    /**
     * 管养单位
     */
    @ApiModelProperty(value = "管养单位")
    private String manageUnit;

    /**
     * 工程类别/任务类型:1零星维修  2设施恢复  3完善增设
     */
    @ApiModelProperty(value = "工程类别/任务类型:1零星维修  2设施恢复  3完善增设")
    private String taskType;

    /**
     * 工程名称
     */
    @ApiModelProperty(value = "工程名称")
    private String projectName;

    /**
     * 签收状态1待接收 2待分派 3施工中 4完工
     */
    @ApiModelProperty(value = "签收状态1待接收 2待分派 3施工中 4完工")
    private String signStatus;

    /**
     * 任务单编号
     */
    @ApiModelProperty(value = "任务单编号")
    private String taskNumber;

    /**
     * 路线编号
     */
    @ApiModelProperty(value = "路线编号")
    private String roadCode;

    /**
     * 养护单位
     */
    @ApiModelProperty(value = "养护单位")
    private String maintainUnit;

    /**
     * 任务要求
     */
    @ApiModelProperty(value = "任务要求")
    private String taskRequire;

}
