package com.sinosoft.ie.booster.infection.model.infcrptgz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 *
 * InfcRptGz模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:51
 */
@Data
@ApiModel(value = "InfcRptGz模型")
public class InfcRptGzInfoVO extends InfcRptGzCrForm{

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
}