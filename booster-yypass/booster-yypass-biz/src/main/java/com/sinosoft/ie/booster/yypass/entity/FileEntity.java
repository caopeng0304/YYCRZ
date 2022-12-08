package com.sinosoft.ie.booster.yypass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Data
@TableName("file")
@ApiModel(value = "")
public class FileEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

	/**
	 * 文件名
	 */
	@ApiModelProperty(value = "文件名")
	private String fileName;
	/**
	 * 原文件名
	 */
	@ApiModelProperty(value = "原始文件名")
	private String original;
	/**
	 * 容器名称
	 */
	@ApiModelProperty(value = "存储桶名称")
	private String bucketName;
	/**
	 * 文件类型
	 */
	@ApiModelProperty(value = "文件类型")
	private String type;
	/**
	 * 文件大小
	 */
	@ApiModelProperty(value = "文件大小")
	private Long fileSize;
	/**
	 * 删除标识：1-删除，0-正常
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

	private String url;
}
