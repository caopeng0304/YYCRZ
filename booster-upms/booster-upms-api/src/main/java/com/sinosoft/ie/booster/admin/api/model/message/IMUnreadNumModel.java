package com.sinosoft.ie.booster.admin.api.model.message;

import lombok.Data;

/**
 * 未读消息模型
 *
 * @author booster开发平台组
 * @since 2019年9月26日 上午9:18
 */
@Data
public class IMUnreadNumModel {
	// 发送者Id
//    @JSONField(name = "SendUserId")
	private String sendUserId;
	//租户id
//    @JSONField(name = "TenantId")
	private String tenantId;
	// 未读数量
//    @JSONField(name = "UnreadNum")
	private int unreadNum;
	// 默认消息
//    @JSONField(name = "DefaultMessage")
	private String defaultMessage;
	// 默认消息类型
//    @JSONField(name = "DefaultMessageType")
	private String defaultMessageType;
	// 默认消息时间
//    @JSONField(name = "DefaultMessageTime")
	private String defaultMessageTime;
}
