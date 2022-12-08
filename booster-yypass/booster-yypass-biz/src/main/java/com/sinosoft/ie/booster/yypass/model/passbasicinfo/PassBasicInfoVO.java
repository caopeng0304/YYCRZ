package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
public class PassBasicInfoVO extends PassBasicInfoCrForm {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

	public List<String> getFileId() {
		return fileId;
	}

	public void setFileId(List<String> fileId) {
		this.fileId = fileId;
	}

	private List<String> fileId;

	private List<FileEntity> file1;
	private List<FileEntity> file2;
	private List<PassLogEntity> list;

}