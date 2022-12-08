package com.sinosoft.ie.booster.mqroute.model.producer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.sinosoft.ie.booster.common.core.model.Pagination;

/**
 *
 * Producer模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-16 15:53:23
 */
@Data
@ApiModel(value = "Producer模型")
public class ProducerPaginationExportModel extends Pagination {

    private String selectKey;

    private String json;

    private String dataType;


    /**
     * 消费者名称
     */
    @ApiModelProperty(value = "生产者名称")
    private String producename;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Integer id;
}