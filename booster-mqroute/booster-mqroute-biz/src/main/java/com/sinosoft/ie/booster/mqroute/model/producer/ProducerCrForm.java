package com.sinosoft.ie.booster.mqroute.model.producer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * Producer模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-16 15:53:23
 */
@Data
@ApiModel(value = "Producer模型")
public class ProducerCrForm  {

    /**
     * 消费者名称
     */
    @ApiModelProperty(value = "生产者名称")
    private String producename;
    
    @ApiModelProperty(value = "")
    private Integer id;
    
    @ApiModelProperty(value = "主题ids")
    private String topids;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;
}