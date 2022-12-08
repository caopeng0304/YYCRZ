package com.sinosoft.ie.booster.common.risk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * risk 配置信息
 *
 * @author haocy
 * @since 2022-11-24
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "risk")
public class RiskProperties {

	/**
	 * 上报地址 如：http://192.168.2.140:6581/services/v1/upload
	 */
	private String uploadUrl;

	/**
	 * 模型Id 如：CACAB670-2CC8-42D1-BD08-550BE1AA6F1D
	 */
	private String modelGuid;

}
