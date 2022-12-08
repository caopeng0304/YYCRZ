package com.sinosoft.ie.booster.taskflow.demo.model.taskfrom;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * TaskFrom模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-10 14:47:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class TaskFromUpForm extends TaskFromCrForm{

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    private String flowId;

    private String operator;
}
