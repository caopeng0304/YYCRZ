package com.sinosoft.ie.booster.admin.websocket;

/**
 * WebSocket指令
 *
 * @author haocy
 * @since 2021-11-12
 */
public enum WebSocketCommand {
	ON_CONNECTION("OnConnection"),
	INIT_MESSAGE("initMessage"),
	ONLINE("Online"),
	OFFLINE("Offline"),
	PING("ping"),
	PONG("pong"),
	SEND_MESSAGE("SendMessage"),
	RECEIVE_MESSAGE("receiveMessage"),
	UPDATE_READ_MESSAGE("UpdateReadMessage"),
	MESSAGE_LIST("MessageList");

	private final String method;

	WebSocketCommand(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	/**
	 * 根据method获取枚举值
	 */
	public static WebSocketCommand parse(String method) {
		for (WebSocketCommand webSocketCommand : WebSocketCommand.values()) {
			if (webSocketCommand.getMethod().equals(method)) {
				return webSocketCommand;
			}
		}
		return null;
	}
}
