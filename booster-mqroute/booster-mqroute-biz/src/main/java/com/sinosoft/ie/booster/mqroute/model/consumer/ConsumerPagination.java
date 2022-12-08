package com.sinosoft.ie.booster.mqroute.model.consumer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.sinosoft.ie.booster.common.core.model.Pagination;

/**
 *
 * Consumer模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-16 15:50:58
 */
@Data
@ApiModel(value = "Consumer模型")
public class ConsumerPagination extends Pagination {

    /**
     * 生产者名称
     */
    @ApiModelProperty(value = "消费者名称")
    private String consumename;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Integer id;
}