package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PaginationTime;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailConfigEntity;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailReceiveEntity;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailSendEntity;

import java.util.List;

/**
 * 邮件接收
 *
 * @author booster code generator
 * @since 2021-10-09
 */
public interface ExtEmailReceiveService extends IService<ExtEmailReceiveEntity> {

	/**
	 * 列表（收件箱）
	 *
	 * @param paginationTime 分页条件
	 * @return
	 */
	List<ExtEmailReceiveEntity> getReceiveList(PaginationTime paginationTime);

	/**
	 * 列表（收件箱）
	 *
	 * @return
	 */
	List<ExtEmailReceiveEntity> getReceiveList();

	/**
	 * 列表（星标件）
	 *
	 * @param paginationTime 分页条件
	 * @return
	 */
	List<ExtEmailReceiveEntity> getStarredList(PaginationTime paginationTime);

	/**
	 * 列表（草稿箱）
	 *
	 * @param paginationTime 分页条件
	 * @return
	 */
	List<ExtEmailSendEntity> getDraftList(PaginationTime paginationTime);

	/**
	 * 列表（已发送）
	 *
	 * @param paginationTime 分页条件
	 * @return
	 */
	List<ExtEmailSendEntity> getSentList(PaginationTime paginationTime);

	/**
	 * 信息（配置）
	 *
	 * @return
	 */
	ExtEmailConfigEntity getConfigInfo();

	/**
	 * 信息（配置）
	 *
	 * @param userId
	 * @return
	 */
	ExtEmailConfigEntity getConfigInfo(String userId);

	/**
	 * 信息（收件/发件）
	 *
	 * @param id 主键值
	 * @return
	 */
	Object getInfo(Long id);

	/**
	 * 删除邮件（发、收）
	 *
	 * @param id 主键值
	 * @return
	 */
	boolean delete(Long id);

	/**
	 * 存草稿
	 *
	 * @param entity 实体对象
	 */
	void saveDraft(ExtEmailSendEntity entity);

	/**
	 * 收邮件设置 已读/未读
	 *
	 * @param id
	 * @param isRead
	 * @return
	 */
	boolean receiveRead(Long id, int isRead);

	/**
	 * 收邮件 星标邮件/取消星标
	 *
	 * @param id
	 * @param isStarred
	 * @return
	 */
	boolean receiveStarred(Long id, int isStarred);

	/**
	 * 邮箱验证
	 *
	 * @param configEntity
	 * @return
	 */
	boolean checkLogin(ExtEmailConfigEntity configEntity);

	/**
	 * 保存邮箱配置
	 *
	 * @param configEntity
	 * @return
	 * @throws DataException
	 */
	void saveConfig(ExtEmailConfigEntity configEntity) throws DataException;

	/**
	 * 发邮件
	 *
	 * @param entity     实体对象
	 * @param mailConfig 邮件配置
	 * @return
	 */
	int saveSent(ExtEmailSendEntity entity, ExtEmailConfigEntity mailConfig);

	/**
	 * 收邮件
	 *
	 * @param mailConfig 邮件配置
	 * @return
	 */
	int receive(ExtEmailConfigEntity mailConfig);
}
