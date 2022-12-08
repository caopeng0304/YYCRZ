package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.entity.BaseMessageEntity;
import com.sinosoft.ie.booster.admin.api.feign.NoticeApi;
import com.sinosoft.ie.booster.admin.api.model.message.MessageFlowForm;
import com.sinosoft.ie.booster.admin.api.model.message.SentMessageModel;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 调用系统消息Api降级处理
 *
 * @author booster开发平台组
 * @since 2021-03-24
 */
@Component
public class NoticeApiFallback implements NoticeApi {


	@Override
	public R<String> sentMessage(MessageFlowForm messageFlowForm) {
		return null;
	}

	@Override
	public List<BaseMessageEntity> getNoticeList() {
		return null;
	}

	@Override
	public void sentMessage(SentMessageModel sentMessageModel) {

	}
}

