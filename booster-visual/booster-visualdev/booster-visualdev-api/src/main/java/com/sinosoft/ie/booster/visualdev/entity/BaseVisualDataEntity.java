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
 * 大屏数据
 *
 * @author booster code generator
 * @since 2021-09-16
 */
@Data
@TableName("base_visual_data")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "大屏数据")
public class BaseVisualDataEntity extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 名称
     */
    @NotEmpty(message = "名称必须填写")
    @ApiModelProperty(value = "名称")
    private String fullName;
    /**
     * 编号
     */
    @NotEmpty(message = "编号必须填写")
    @ApiModelProperty(value = "编号")
    private String encode;
    /**
     * 描述或说明
     */
    @NotEmpty(message = "描述或说明必须填写")
    @ApiModelProperty(value = "描述或说明")
    private String description;
    /**
     * 分类ID
     */
    @NotEmpty(message = "分类ID必须填写")
    @ApiModelProperty(value = "分类ID")
    private String categoryId;
    /**
     * 大屏截图
     */
    @NotEmpty(message = "大屏截图必须填写")
    @ApiModelProperty(value = "大屏截图")
    private String screenShot;
    /**
     * 访问密码
     */
    @NotEmpty(message = "访问密码必须填写")
    @ApiModelProperty(value = "访问密码")
    private String password;
    /**
     * 控件属性JSON包
     */
    @NotEmpty(message = "控件属性JSON包必须填写")
    @ApiModelProperty(value = "控件属性JSON包")
    private String component;
    /**
     * 大屏配置JSON包
     */
    @NotEmpty(message = "大屏配置JSON包必须填写")
    @ApiModelProperty(value = "大屏配置JSON包")
    private String detail;
    /**
     * 复制id
     */
    @NotEmpty(message = "复制id必须填写")
    @ApiModelProperty(value = "复制id")
    private Long copyId;
    /**
     * 排序
     */
    @NotNull(message = "排序必须填写")
    @ApiModelProperty(value = "排序")
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
