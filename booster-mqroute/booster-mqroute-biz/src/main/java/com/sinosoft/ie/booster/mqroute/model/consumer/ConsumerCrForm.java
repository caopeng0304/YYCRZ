package com.sinosoft.ie.booster.mqroute.model.consumer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * Consumer模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-16 15:50:58
 */
@Data
@ApiModel(value = "Consumer模型")
public class ConsumerCrForm  {

    /**
     * 消费者名称
     */
    @ApiModelProperty(value = "消费者名称")
    private String consumename;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Integer id;
    
    @ApiModelProperty(value = "消费者主题")
    private Integer  topid;
    
    /**
     * 回调url
     */
    @ApiModelProperty(value = "回调url")
    private String callbackurl;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;
}