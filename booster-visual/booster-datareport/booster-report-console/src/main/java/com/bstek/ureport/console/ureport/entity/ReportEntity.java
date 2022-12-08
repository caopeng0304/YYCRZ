package com.bstek.ureport.console.ureport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("data_report")
public class ReportEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 报表名称
     */
    private String fullName;

    /**
     * 报表内容
     */
    private String content;

    /**
     * 字典分类
     */
    private String categoryId;

    /**
     * 编码
     */
    private String encode;

    /**
     * 状态(0-默认，禁用，1-启用)
     */
    private String enabledFlag;

    /**
     * 排序码
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;

}
