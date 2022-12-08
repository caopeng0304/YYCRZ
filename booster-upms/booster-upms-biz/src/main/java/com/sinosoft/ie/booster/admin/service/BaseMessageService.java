package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.BaseMessageEntity;
import com.sinosoft.ie.booster.common.core.model.Pagination;

import java.util.List;

/**
 * 消息实例
 *
 * @author booster code generator
 * @since 2021-09-18
 */
public interface BaseMessageService extends IService<BaseMessageEntity> {
	/**
	 * 列表（通知公告）
	 *
	 * @param pagination
	 * @return
	 */
	List<BaseMessageEntity> getNoticeList(Pagination pagination);

	/**
	 * 列表（通知公告）
	 *
	 * @return
	 */
	List<BaseMessageEntity> getNoticeList();

	/**
	 * 列表（通知公告/系统消息/私信消息）
	 *
	 * @param pagination
	 * @param type       类别
	 * @return
	 */
	List<BaseMessageEntity> getMessageList(Pagination pagination, String type);

	/**
	 * 列表（通知公告/系统消息/私信消息）
	 *
	 * @param pagination
	 * @return
	 */
	List<BaseMessageEntity> getMessageList(Pagination pagination);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	BaseMessageEntity getInfo(Long id);

	/**
	 * 默认消息
	 *
	 * @param type 类别:1-通知公告/2-系统消息
	 * @return
	 */
	BaseMessageEntity getInfoDefault(int type);

	/**
	 * 删除
	 *
	 * @param entity 实体对象
	 */
	void delete(BaseMessageEntity entity);

	/**
	 * 创建
	 *
	 * @param entity 实体对象
	 */
	void create(BaseMessageEntity entity);

	/**
	 * 更新
	 *
	 *
	 * @param id
	 * @param entity 实体对象
	 * @return
	 */
	boolean update(Long id, BaseMessageEntity entity);

	/**
	 * 消息已读（单条）
	 *
	 * @param messageId 消息主键
	 */
	void messageRead(Long messageId);

	/**
	 * 消息已读（全部）
	 */
	void messageRead();

	/**
	 * 删除记录
	 *
	 * @param messageIds 消息Id
	 */
	void deleteRecord(List<String> messageIds);

	/**
	 * 获取未读数量（含 通知公告、系统消息）
	 *
	 * @param userId 用户主键
	 * @return
	 */
	long getUnreadCount(String userId);

	/**
	 * 获取公告未读数量
	 *
	 * @param userId 用户主键
	 * @return
	 */
	int getUnreadNoticeCount(String userId);

	/**
	 * 获取消息未读数量
	 *
	 * @param userId 用户主键
	 * @return
	 */
	int getUnreadMessageCount(String userId);

	/**
	 * 发送公告
	 * @param toUserIds 发送用户
	 * @param entity    消息信息
	 */
	void sentNotice(List<String> toUserIds, BaseMessageEntity entity);

	/**
	 * 发送消息
	 * @param toUserIds 发送用户
	 * @param title     标题
	 */
	void sentMessage(List<String> toUserIds, String title);

	/**
	 * 发送消息
	 * @param toUserIds 发送用户
	 * @param title     标题
	 * @param bodyText  内容
	 */
	void sentMessage(List<String> toUserIds, String title, String bodyText);
}
