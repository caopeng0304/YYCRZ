package com.sinosoft.ie.booster.workflow.feign.fallback;

import com.sinosoft.ie.booster.workflow.entity.FlowDelegateEntity;
import com.sinosoft.ie.booster.workflow.feign.FlowDelegateApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * api接口
 *
 * @author booster开发平台组
 * @since 2021/3/15 11:55
 */
@Component
public class FlowDelegateApiFallback implements FlowDelegateApi {
	@Override
	public List<FlowDelegateEntity> getList() {
		return null;
	}
}
