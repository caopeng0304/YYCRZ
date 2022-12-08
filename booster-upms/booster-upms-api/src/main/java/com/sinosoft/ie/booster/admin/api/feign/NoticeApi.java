package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.entity.BaseMessageEntity;
import com.sinosoft.ie.booster.admin.api.feign.fallback.NoticeApiFallback;
import com.sinosoft.ie.booster.admin.api.model.message.MessageFlowForm;
import com.sinosoft.ie.booster.admin.api.model.message.SentMessageModel;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 调用系统消息Api
 *
 * @author booster开发平台组
 * @since 2021-03-24
 */
@FeignClient(contextId = "noticeApi", name = ServiceNameConstants.UMPS_SERVICE, fallback = NoticeApiFallback.class, path = "/Message")
public interface NoticeApi {

	/**
	 * 工作流发送消息
	 *
	 * @param messageFlowForm
	 * @return
	 */
	@GetMapping("/flow/sentMessage")
	R<String> sentMessage(MessageFlowForm messageFlowForm);

	/**
	 * 列表（通知公告）
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/GetNoticeList")
	List<BaseMessageEntity> getNoticeList();

	/**
	 * 发送消息
	 *
	 * @param sentMessageModel
	 * @return
	 */
	@PostMapping("/SentMessage")
	void sentMessage(@RequestBody SentMessageModel sentMessageModel);
}
