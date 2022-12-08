package com.sinosoft.ie.booster.mqroute.model.topic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.sinosoft.ie.booster.common.core.model.Pagination;

/**
 *
 * Topic模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-15 15:12:51
 */
@Data
@ApiModel(value = "Topic模型")
public class TopicPaginationExportModel extends Pagination {

    private String selectKey;

    private String json;

    private String dataType;


    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Integer id;
}