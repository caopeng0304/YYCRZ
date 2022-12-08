package com.sinosoft.ie.booster.mqroute.model.topic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * Topic模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-15 15:12:51
 */
@Data
@ApiModel(value = "Topic模型")
public class TopicCrForm  {

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 消息名称
     */
    @ApiModelProperty(value = "主题名称")
    private String topicname;

    /**
     * 消息名称
     */
    @ApiModelProperty(value = "主题状态")
    private String state;

}