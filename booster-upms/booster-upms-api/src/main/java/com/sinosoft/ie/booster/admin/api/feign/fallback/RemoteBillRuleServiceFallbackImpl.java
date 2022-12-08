package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author haocy
 * @since 2021-08-19
 */
@Slf4j
@Component
public class RemoteBillRuleServiceFallbackImpl implements RemoteBillRuleService {

	@Setter
	private Throwable cause;

	/**
	 * 获取单据流水号
	 *
	 * @param enCode 单据编号
	 * @return R
	 */
	@Override
	public R<String> getBillNumber(String enCode) {
		log.error("feign 获取单据流水号失败:{}", enCode, cause);
		return null;
	}

}
