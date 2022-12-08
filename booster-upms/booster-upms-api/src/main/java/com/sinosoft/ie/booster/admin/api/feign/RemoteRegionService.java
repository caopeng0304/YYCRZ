package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.entity.SysRegionEntity;
import com.sinosoft.ie.booster.admin.api.feign.factory.RemoteRegionServiceFallbackFactory;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author haocy
 * @since 2021-11-09
 */
@FeignClient(contextId = "remoteRegionService" , value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteRegionServiceFallbackFactory.class)
public interface RemoteRegionService {

	/**
	 * 按Id列表获取行政区划数据
	 *
	 * @param idList Id列表
	 * @return 行政区划数据
	 */
	@GetMapping("/region/listByIds" )
	R<List<SysRegionEntity>> listByIds(@RequestParam("idList") Collection<? extends Serializable> idList);

}
