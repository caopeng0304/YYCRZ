package com.sinosoft.ie.booster.mqroute.model.topic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * Topic模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-15 15:12:51
 */
@Data
@ApiModel(value = "Topic模型")
public class TopicListVO{

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 消息名称
     */
    @ApiModelProperty(value = "消息名称")
    private String topicname;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 状态，0：删除，1：停用，2：正常使用
     */
    @ApiModelProperty(value = "状态，0：删除，1：停用，2：正常使用")
    private Long state;

}