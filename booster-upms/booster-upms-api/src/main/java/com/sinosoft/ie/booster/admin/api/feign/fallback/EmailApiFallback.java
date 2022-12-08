package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.entity.ExtEmailReceiveEntity;
import com.sinosoft.ie.booster.admin.api.feign.EmailApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailApiFallback implements EmailApi {
	@Override
	public List<ExtEmailReceiveEntity> getReceiveList() {
		return null;
	}
}
