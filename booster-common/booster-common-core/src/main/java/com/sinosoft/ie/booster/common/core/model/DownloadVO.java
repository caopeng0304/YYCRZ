package com.sinosoft.ie.booster.common.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadVO {
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "请求接口")
	private String url;
}
