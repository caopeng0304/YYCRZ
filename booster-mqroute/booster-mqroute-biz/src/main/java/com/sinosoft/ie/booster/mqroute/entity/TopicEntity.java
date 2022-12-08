package com.sinosoft.ie.booster.mqroute.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-15 15:12:51
 */
@Data
@TableName("topic")
@ApiModel(value = "")
public class TopicEntity  {

    /**
     * 
     */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
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
    private String state;
}
