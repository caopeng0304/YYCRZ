package com.sinosoft.ie.booster.yypass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * 
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Data
@TableName("person_transfer")
@ApiModel(value = "")
public class PersonTransferEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

	private String personId;

	private String personName;

	private String unitId;

	private String roleCode;

	private String transferPersonId;

	private String transferPersonName;

	private String transferPersonUnitId;

	private Date addTime;

}
