package com.sinosoft.ie.booster.mqroute.model.consumer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * Consumer模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-16 15:50:58
 */
@Data
@ApiModel(value = "Consumer模型")
public class ConsumerListVO{

    /**
     * 消费者名称
     */
    @ApiModelProperty(value = "消费者名称")
    private String consumename;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;
    
    /**
     * 主题id
     */
    @ApiModelProperty(value = "主题id")
    private Integer  topid;
    /**
     * 主题名称
     */
    @ApiModelProperty(value = "主题名称")
    private String topicname;
    /**
     * 回调url
     */
    @ApiModelProperty(value = "回调url")
    private String callbackurl;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
	public void setState(Integer state) {
		this.state = state;
		if(state==1) {
			setStatus("禁用");
		}if(state==2) {
			setStatus("正常");
		}
	}
    
    
    
    
}