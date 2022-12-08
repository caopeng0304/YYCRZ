package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author haocy
 * @since 2021-09-15
 */
@Slf4j
@Component
public class RemoteDictServiceFallbackImpl implements RemoteDictService {

	@Setter
	private Throwable cause;

	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@Override
	public R<List<SysDictItemEntity>> getDictByType(String type) {
		log.error("feign 通过字典类型查找字典失败:{}", type, cause);
		return null;
	}

}
