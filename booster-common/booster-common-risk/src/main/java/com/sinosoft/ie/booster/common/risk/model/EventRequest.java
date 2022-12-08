package com.sinosoft.ie.booster.common.risk.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 事件信息
 *
 * @author haocy
 * @since 2022-11-24
 */
@Getter
@RequiredArgsConstructor
public class EventRequest {

	/**
	 * 模型guid
	 */
	private final String guid;


	/**
	 * 请求流水号
	 */
	private final String reqId;

	/**
	 * 事件内容
	 */
	private final JSONObject jsonInfo;
}
