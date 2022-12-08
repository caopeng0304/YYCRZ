package com.sinosoft.ie.booster.mqroute.entity;

import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.validation.constraints.NotBlank;

@Data
public class MqMessage {

	@NotBlank(message = "消息主题不能为空")
	private String topics;
	//@NotBlank(message = "消息标签不能为空")
	private String tag;
	//@NotBlank(message = "消息KEYS不能为空")
	private String key;
	@NotBlank(message = "消息内容不能为空")
	private String body;

	public Message<String> build() {
		return MessageBuilder.withPayload(body)
				.setHeader("KEYS", key).build();
	}

}
