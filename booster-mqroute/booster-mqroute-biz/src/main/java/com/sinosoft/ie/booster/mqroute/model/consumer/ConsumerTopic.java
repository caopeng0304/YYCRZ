package com.sinosoft.ie.booster.mqroute.model.consumer;

import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;

import io.swagger.annotations.ApiModelProperty;

public class ConsumerTopic  extends ConsumerEntity{

    /**
     * 主题名称
     */
    @ApiModelProperty(value = "主题名称")
    private String topicname;
	
}
