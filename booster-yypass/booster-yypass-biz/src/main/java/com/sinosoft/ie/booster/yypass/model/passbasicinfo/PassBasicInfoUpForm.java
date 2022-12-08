package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * ApsSystem模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class PassBasicInfoUpForm extends PassBasicInfoCrForm {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
}