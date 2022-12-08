package com.bstek.ureport.console.ureport.model;

import lombok.Data;

@Data
public class ReportCrForm {
    private String fullName;
    private String content;
    private String categoryId;
    private String enCode;
    private String enabledFlag;
    private Long sort;
    private String description;
    private String createBy;

}
