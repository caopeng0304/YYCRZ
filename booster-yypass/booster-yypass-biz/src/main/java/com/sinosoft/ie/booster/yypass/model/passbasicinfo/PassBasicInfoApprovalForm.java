package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 *
 * ApsSystem模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:38
 */
@Data
@ApiModel
public class PassBasicInfoApprovalForm {

    /**
     * 业务系统id
     */
    @ApiModelProperty(value = "业务系统id")
    private List<String> ids;
	private String personState;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date delayStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date delayToTime;

    private String desc;

    private String personId;

    private String personName;

}