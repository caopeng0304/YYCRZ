package com.bstek.ureport.console.ureport.model;

import lombok.Data;

@Data
public class ReportListVO {
    private String id;
    private String fullName;
    private String enCode;
    private String createBy;
    private Long createTime;
    private String categoryId;
    private String updateBy;
    private Long updateTime;
    private Integer enabledFlag;
}
