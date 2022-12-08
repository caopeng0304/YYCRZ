package com.sinosoft.ie.booster.admin.api.entity;

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
import java.time.LocalDateTime;

/**
 * 邮件接收
 *
 * @author booster code generator
 * @since 2021-10-09
 */
@Data
@TableName("ext_email_receive")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "邮件接收")
public class ExtEmailReceiveEntity extends BaseEntity {

    /**
     * 自然主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "自然主键")
    private Long id;
    /**
     * 类型
     */
    @NotNull(message = "类型必须填写")
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 邮箱账户
     */
    @NotEmpty(message = "邮箱账户必须填写")
    @ApiModelProperty(value = "邮箱账户")
    private String mailAccount;
    /**
     * MID
     */
    @NotEmpty(message = "MID必须填写")
    @ApiModelProperty(value = "MID")
    private String mailId;
    /**
     * 发件人
     */
    @NotEmpty(message = "发件人必须填写")
    @ApiModelProperty(value = "发件人")
    private String sender;
    /**
     * 发件人名称
     */
    @NotEmpty(message = "发件人名称必须填写")
    @ApiModelProperty(value = "发件人名称")
    private String senderName;
    /**
     * 主题
     */
    @NotEmpty(message = "主题必须填写")
    @ApiModelProperty(value = "主题")
    private String subject;
    /**
     * 正文
     */
    @NotEmpty(message = "正文必须填写")
    @ApiModelProperty(value = "正文")
    private String bodyText;
    /**
     * 附件
     */
    @NotEmpty(message = "附件必须填写")
    @ApiModelProperty(value = "附件")
    private String attachment;
    /**
     * 阅读
     */
    @NotNull(message = "阅读必须填写")
    @ApiModelProperty(value = "阅读")
    private Integer isRead;
    /**
     * sentDate
     */
    @NotNull(message = "sentDate必须填写")
    @ApiModelProperty(value = "sentDate")
    private LocalDateTime sentDate;
    /**
     * 星标
     */
    @NotNull(message = "星标必须填写")
    @ApiModelProperty(value = "星标")
    private Integer starred;
    /**
     * 描述
     */
    @NotEmpty(message = "描述必须填写")
    @ApiModelProperty(value = "描述")
    private String description;
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
