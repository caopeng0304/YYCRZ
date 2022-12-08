package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.model.PageModel;
import com.sinosoft.ie.booster.admin.api.entity.BaseImContentEntity;
import com.sinosoft.ie.booster.admin.api.model.message.IMUnreadNumModel;

import java.util.List;

/**
 * 聊天内容
 *
 * @author booster code generator
 * @since 2021-09-18
 */
public interface BaseImContentService extends IService<BaseImContentEntity> {
	/**
	 * 获取消息列表
	 *
	 * @param sendUserId    发送者
	 * @param receiveUserId 接收者
	 * @param pageModel
	 * @return
	 */
	List<BaseImContentEntity> getMessageList(String sendUserId, String receiveUserId, PageModel pageModel);

	/**
	 * 获取未读消息
	 *
	 * @param receiveUserId 接收者
	 * @return
	 */
	List<IMUnreadNumModel> getUnreadList(String receiveUserId);

	/**
	 * 获取未读消息
	 *
	 * @param receiveUserId 接收者
	 * @return
	 */
	long getUnreadCount(String receiveUserId);

	/**
	 * 发送消息
	 *
	 * @param sendUserId    发送者
	 * @param receiveUserId 接收者
	 * @param message       消息内容
	 * @param messageType   消息类型
	 * @return
	 */
	void sendMessage(String sendUserId, String receiveUserId, String message, String messageType);

	/**
	 * 已读消息
	 *
	 * @param sendUserId    发送者
	 * @param receiveUserId 接收者
	 * @return
	 */
	void readMessage(String sendUserId, String receiveUserId);
}
