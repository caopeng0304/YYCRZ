package com.sinosoft.ie.booster.visualdev.feign.fallback;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.feign.DataInterFaceApi;
import org.springframework.stereotype.Component;

/**
 * 调用数据接口Api降级处理
 *
 * @author booster开发平台组
 * @since 2021-03-24
 */
@Component
public class DataInterFaceApiFallback implements DataInterFaceApi {

	@Override
	public R infoToId(String id) {
		return null;
	}
}
