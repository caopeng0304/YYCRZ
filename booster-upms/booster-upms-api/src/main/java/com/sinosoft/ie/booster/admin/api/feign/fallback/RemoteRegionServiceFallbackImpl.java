package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.entity.SysRegionEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteRegionService;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author haocy
 * @since 2021-11-09
 */
@Slf4j
@Component
public class RemoteRegionServiceFallbackImpl implements RemoteRegionService {

	@Setter
	private Throwable cause;

	/**
	 * 通过字典类型查找字典
	 *
	 * @param idList Id列表
	 * @return 行政区划数据
	 */
	@Override
	public R<List<SysRegionEntity>> listByIds(Collection<? extends Serializable> idList) {
		log.error("feign 通过Id列表查找行政区划数据失败:{}" , idList, cause);
		return null;
	}

}
