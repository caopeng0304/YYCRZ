package com.sinosoft.ie.booster.visualdev.feign;

import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.feign.fallback.DataInterFaceApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 调用数据接口Api
 *
 * @author booster开发平台组
 * @since 2021-03-24
 */
@FeignClient(contextId = "dataInterFaceApi", name = ServiceNameConstants.VISUALDEV_SERVICE, fallback = DataInterFaceApiFallback.class, path = "/Base/DataInterface")
public interface DataInterFaceApi {

	/**
	 * 访问接口
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/Actions/Response")
	R infoToId(@PathVariable("id") String id);

}
