package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.BaseMessageEntity;
import com.sinosoft.ie.booster.admin.api.entity.BaseMessageReceiveEntity;
import com.sinosoft.ie.booster.admin.mapper.BaseMessageMapper;
import com.sinosoft.ie.booster.admin.service.BaseMessageReceiveService;
import com.sinosoft.ie.booster.admin.service.BaseMessageService;
import com.sinosoft.ie.booster.admin.websocket.OnlineUserModel;
import com.sinosoft.ie.booster.admin.websocket.OnlineUserProvider;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息实例
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Service
public class BaseMessageServiceImpl extends ServiceImpl<BaseMessageMapper, BaseMessageEntity> implements BaseMessageService {
	@Autowired
	private BaseMessageReceiveService messageReceiveService;

	@Override
	public List<BaseMessageEntity> getNoticeList(Pagination pagination) {
		QueryWrapper<BaseMessageEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageEntity::getType, 1);
		//关键词（消息标题）
		if (!StrUtil.isEmpty(pagination.getKeyword())) {
			queryWrapper.lambda().like(BaseMessageEntity::getTitle, pagination.getKeyword());
		}
		//默认排序
		queryWrapper.lambda().orderByDesc(BaseMessageEntity::getCreateTime);
		Page<BaseMessageEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
		IPage<BaseMessageEntity> userIPage = this.page(page, queryWrapper);
		return pagination.setData(userIPage.getRecords(), page.getTotal());
	}

	@Override
	public List<BaseMessageEntity> getNoticeList() {
		QueryWrapper<BaseMessageEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageEntity::getType, 1);

		return this.baseMapper.selectList(queryWrapper);
	}

	@Override
	public List<BaseMessageEntity> getMessageList(Pagination pagination, String type) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", String.valueOf(SecurityUtils.getUser().getUsername()));
		StringBuilder sql = new StringBuilder();
		//关键词（消息标题）
		if (!StrUtil.isEmpty(pagination.getKeyword())) {
			sql.append(" AND m.title like '%").append(pagination.getKeyword()).append("%' ");
		}
		//消息类别
		if (!StrUtil.isEmpty(type)) {
			sql.append(" AND m.type = '").append(type).append("'");
		}
		sql.append(" ORDER BY update_time desc");
		map.put("sql", sql.toString());
		List<BaseMessageEntity> lists = this.baseMapper.getMessageList(map);
		return pagination.setData(PageUtil.getListPage((int) pagination.getCurrentPage(), (int) pagination.getPageSize(), lists), lists.size());
	}

	@Override
	public List<BaseMessageEntity> getMessageList(Pagination pagination) {
		return this.getMessageList(pagination, null);
	}

	@Override
	public BaseMessageEntity getInfo(Long id) {
		QueryWrapper<BaseMessageEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public BaseMessageEntity getInfoDefault(int type) {
		List<BaseMessageEntity> list = this.baseMapper.getInfoDefault(type);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new BaseMessageEntity();
		}
	}

	@Override
	@Transactional
	public void delete(BaseMessageEntity entity) {
		this.removeById(entity.getId());
		QueryWrapper<BaseMessageReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageReceiveEntity::getMessageId, entity.getId());
		messageReceiveService.remove(queryWrapper);
	}

	@Override
	public void create(BaseMessageEntity entity) {
		entity.setBodyText(entity.getBodyText());
		entity.setType(1);
		entity.setEnabledFlag("0");
		this.save(entity);
	}

	@Override
	public boolean update(Long id, BaseMessageEntity entity) {
		entity.setId(id);
		entity.setBodyText(entity.getBodyText());
		return this.updateById(entity);
	}

	@Override
	public void messageRead(Long messageId) {
		String userId = String.valueOf(SecurityUtils.getUser().getUsername());
		QueryWrapper<BaseMessageReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageReceiveEntity::getUserId, userId).eq(BaseMessageReceiveEntity::getMessageId, messageId);
		BaseMessageReceiveEntity entity = messageReceiveService.getOne(queryWrapper);
		if (entity != null) {
			entity.setIsRead(1);
			entity.setReadCount(entity.getReadCount() == null ? 1 : entity.getReadCount() + 1);
			entity.setReadTime(LocalDateTime.now());
			messageReceiveService.updateById(entity);
		}
	}

	@Override
	@Transactional
	public void messageRead() {
		String userId = String.valueOf(SecurityUtils.getUser().getUsername());
		QueryWrapper<BaseMessageReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageReceiveEntity::getUserId, userId).eq(BaseMessageReceiveEntity::getIsRead, 0);
		List<BaseMessageReceiveEntity> entitys = messageReceiveService.list(queryWrapper);
		if (entitys.size() > 0) {
			for (BaseMessageReceiveEntity entity : entitys) {
				entity.setIsRead(1);
				entity.setReadCount(entity.getReadCount() == null ? 1 : entity.getReadCount() + 1);
				entity.setReadTime(LocalDateTime.now());
				messageReceiveService.updateById(entity);
			}
		}
	}

	@Override
	@Transactional
	public void deleteRecord(List<String> messageIds) {
		String userId = String.valueOf(SecurityUtils.getUser().getUsername());
		QueryWrapper<BaseMessageReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageReceiveEntity::getUserId, userId).in(BaseMessageReceiveEntity::getMessageId, messageIds);
		messageReceiveService.remove(queryWrapper);
	}

	@Override
	public long getUnreadCount(String userId) {
		QueryWrapper<BaseMessageReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseMessageReceiveEntity::getUserId, userId).eq(BaseMessageReceiveEntity::getIsRead, 0);
		return messageReceiveService.count(queryWrapper);
	}

	@Override
	public int getUnreadNoticeCount(String userId) {
		return this.baseMapper.getUnreadNoticeCount(userId);
	}

	@Override
	public int getUnreadMessageCount(String userId) {
		return this.baseMapper.getUnreadMessageCount(userId);
	}

	@Override
	@Transactional
	public void sentNotice(List<String> toUserIds, BaseMessageEntity entity) {
		entity.setEnabledFlag("1");
		entity.setUpdateTime(LocalDateTime.now());
		entity.setUpdateBy(SecurityUtils.getUser().getUsername());
		this.updateById(entity);
		List<BaseMessageReceiveEntity> receiveEntityList = new ArrayList<>();
		for (String item : toUserIds) {
			BaseMessageReceiveEntity messageReceiveEntity = new BaseMessageReceiveEntity();
			messageReceiveEntity.setMessageId(entity.getId());
			messageReceiveEntity.setUserId(item);
			messageReceiveEntity.setIsRead(0);
			receiveEntityList.add(messageReceiveEntity);
			messageReceiveService.save(messageReceiveEntity);
		}
		//消息推送 - PC端
		for (int i = 0; i < toUserIds.size(); i++) {
			for (OnlineUserModel item : OnlineUserProvider.getOnlineUserList()) {
				if (toUserIds.get(i).equals(item.getUserId())) {
					JSONObject map = new JSONObject();
					map.put("method", "messagePush");
					map.put("unreadNoticeCount", 1);
					map.put("userId", item.getUserId());
					map.put("toUserId", toUserIds);
					map.put("title", entity.getTitle());
					synchronized (map) {
						item.getWebSocket().getAsyncRemote().sendText(map.toJSONString());
					}
				}
			}
		}
	}

	@Override
	public void sentMessage(List<String> toUserIds, String title) {
		this.sentMessage(toUserIds, title, null);
	}

	@Override
	@Transactional
	public void sentMessage(List<String> toUserIds, String title, String bodyText) {
		BaseMessageEntity entity = new BaseMessageEntity();
		entity.setTitle(title);
		entity.setBodyText(bodyText);
		entity.setId(IdWorker.getId());
		entity.setType(2);
		entity.setCreateBy(SecurityUtils.getUser().getUsername());
		entity.setUpdateTime(entity.getCreateTime());
		entity.setUpdateBy(entity.getCreateBy());
		List<BaseMessageReceiveEntity> receiveEntityList = new ArrayList<>();
		for (String item : toUserIds) {
			BaseMessageReceiveEntity messageReceiveEntity = new BaseMessageReceiveEntity();
			messageReceiveEntity.setMessageId(entity.getId());
			messageReceiveEntity.setUserId(item);
			messageReceiveEntity.setIsRead(0);
			receiveEntityList.add(messageReceiveEntity);
		}
		this.save(entity);
		for (BaseMessageReceiveEntity messageReceiveEntity : receiveEntityList) {
			messageReceiveService.save(messageReceiveEntity);
		}
		//消息推送 - PC端
		for (int i = 0; i < toUserIds.size(); i++) {
			for (OnlineUserModel item : OnlineUserProvider.getOnlineUserList()) {
				if (toUserIds.get(i).equals(item.getUserId())) {
					JSONObject map = new JSONObject();
					map.put("method", "messagePush");
					map.put("unreadNoticeCount", 1);
					map.put("userId", item.getUserId());
					map.put("toUserId", toUserIds);
					map.put("title", entity.getTitle());
					synchronized (map) {
						item.getWebSocket().getAsyncRemote().sendText(map.toJSONString());
					}
				}
			}
		}
	}
}
