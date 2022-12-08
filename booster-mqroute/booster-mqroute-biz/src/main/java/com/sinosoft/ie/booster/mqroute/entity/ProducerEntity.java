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
 * @日期： 2022-03-16 15:53:23
 */
@Data
@TableName("producer")
@ApiModel(value = "")
public class ProducerEntity  {

    /**
     * 
     */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 消费者名称
     */
    @ApiModelProperty(value = "消费者名称")
    private String producename;

    /**
     * 主题id
     */
    @ApiModelProperty(value = "主题id")
    private String topids;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String state;
}
