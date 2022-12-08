package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.factory.RemoteDictServiceFallbackFactory;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author haocy
 * @since 2021-09-15
 */
@FeignClient(contextId = "remoteDictService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteDictServiceFallbackFactory.class)
public interface RemoteDictService {

	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/dict/type/{type}")
	R<List<SysDictItemEntity>> getDictByType(@PathVariable("type") String type);

}
