package com.sinosoft.ie.booster.visualdev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

/**
 * 常用表字段
 *
 * @author booster code generator
 * @since 2021-09-17
 */
@Data
@TableName("base_common_fields")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "常用表字段")
public class BaseCommonFieldsEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 列名
     */
    @NotEmpty(message = "列名必须填写")
    @ApiModelProperty(value = "列名")
    private String fieldName;
    /**
     * 列说明
     */
    @NotEmpty(message = "列说明必须填写")
    @ApiModelProperty(value = "列说明")
    private String fieldComment;
    /**
     * 类型
     */
    @NotEmpty(message = "类型必须填写")
    @ApiModelProperty(value = "类型")
    private String dataType;
    /**
     * 长度
     */
    @NotEmpty(message = "长度必须填写")
    @ApiModelProperty(value = "长度")
    private String dataLength;
    /**
     * 是否为空（1允许，0不允许）
     */
    @NotNull(message = "是否为空（1允许，0不允许）必须填写")
    @ApiModelProperty(value = "是否为空（1允许，0不允许）")
    private Integer allowNull;
    /**
     * 描述说明
     */
    @NotEmpty(message = "描述说明必须填写")
    @ApiModelProperty(value = "描述说明")
    private String description;
    /**
     * 排序码
     */
    @NotNull(message = "排序码必须填写")
    @ApiModelProperty(value = "排序码")
    private Integer sort;
    /**
     * 有效标志
     */
    @NotEmpty(message = "有效标志必须填写")
    @ApiModelProperty(value = "有效标志")
    private String enabledFlag;
    /**
     * 删除标志
     */
    @NotEmpty(message = "删除标志必须填写")
    @ApiModelProperty(value = "删除标志")
	@TableLogic
    private String delFlag;
}
