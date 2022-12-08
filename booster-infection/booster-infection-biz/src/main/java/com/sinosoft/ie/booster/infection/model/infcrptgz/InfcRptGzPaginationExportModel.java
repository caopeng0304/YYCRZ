package com.sinosoft.ie.booster.infection.model.infcrptgz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.sinosoft.ie.booster.common.core.model.Pagination;

/**
 *
 * InfcRptGz模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:51
 */
@Data
@ApiModel(value = "InfcRptGz模型")
public class InfcRptGzPaginationExportModel extends Pagination {

    private String selectKey;

    private String json;

    private String dataType;


    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名")
    private String patientName;

    /**
     * 疾病诊断代码
     */
    @ApiModelProperty(value = "疾病诊断代码")
    private String diagDiseCode;
}