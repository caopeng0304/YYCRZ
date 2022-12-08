package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.BaseImContentEntity;
import com.sinosoft.ie.booster.admin.api.model.message.IMUnreadNumModel;
import com.sinosoft.ie.booster.admin.mapper.BaseImContentMapper;
import com.sinosoft.ie.booster.admin.service.BaseImContentService;
import com.sinosoft.ie.booster.common.core.model.PageModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 聊天内容
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Service
public class BaseImContentServiceImpl extends ServiceImpl<BaseImContentMapper, BaseImContentEntity> implements BaseImContentService {
	@Override
	public List<BaseImContentEntity> getMessageList(String sendUserId, String receiveUserId, PageModel pageModel) {
		QueryWrapper<BaseImContentEntity> queryWrapper = new QueryWrapper<>();
		//发件人、收件人
		if (!StrUtil.isEmpty(sendUserId) && !StrUtil.isEmpty(receiveUserId)) {
			queryWrapper.lambda().and(wrapper -> {
				wrapper.eq(BaseImContentEntity::getSendUserId, sendUserId);
				wrapper.eq(BaseImContentEntity::getReceiveUserId, receiveUserId);
				wrapper.or().eq(BaseImContentEntity::getSendUserId, receiveUserId);
				wrapper.eq(BaseImContentEntity::getReceiveUserId, sendUserId);
			});
		}
		//关键字查询
		if (pageModel != null && pageModel.getKeyword() != null) {
			queryWrapper.lambda().like(BaseImContentEntity::getContent, pageModel.getKeyword());
		}
		//排序
		assert pageModel != null;
		pageModel.setSidx("F_SendTime");
		if (StrUtil.isEmpty(pageModel.getSidx())) {
			queryWrapper.lambda().orderByDesc(BaseImContentEntity::getSendTime);
		} else {
			queryWrapper = "asc".equals(pageModel.getSord().toLowerCase()) ? queryWrapper.orderByAsc(pageModel.getSidx()) : queryWrapper.orderByDesc(pageModel.getSidx());
		}
		Page<BaseImContentEntity> page = new Page<>(pageModel.getPage(), pageModel.getRows());
		IPage<BaseImContentEntity> userIPage = this.page(page, queryWrapper);
		return pageModel.setData(userIPage.getRecords(), page.getTotal());
	}

	@Override
	public List<IMUnreadNumModel> getUnreadList(String receiveUserId) {
		List<IMUnreadNumModel> list = this.baseMapper.getUnreadList(receiveUserId);
		List<IMUnreadNumModel> list1 = this.baseMapper.getUnreadLists(receiveUserId);
		for (IMUnreadNumModel item : list) {
			IMUnreadNumModel defaultItem = list1.stream().filter(q -> q.getSendUserId().equals(item.getSendUserId())).findFirst().get();
			item.setDefaultMessage(defaultItem.getDefaultMessage());
			item.setDefaultMessageType(defaultItem.getDefaultMessageType());
			item.setDefaultMessageTime(defaultItem.getDefaultMessageTime());
		}
		return list;
	}

	@Override
	public long getUnreadCount(String receiveUserId) {
		QueryWrapper<BaseImContentEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseImContentEntity::getReceiveUserId, receiveUserId).eq(BaseImContentEntity::getState, 0);
		return this.count(queryWrapper);
	}

	@Override
	public void sendMessage(String sendUserId, String receiveUserId, String message, String messageType) {
		BaseImContentEntity entity = new BaseImContentEntity();
		entity.setSendUserId(sendUserId);
		entity.setSendTime(LocalDateTime.now());
		entity.setReceiveUserId(receiveUserId);
		entity.setState(0);
		entity.setContent(message);
		entity.setContentType(messageType);
		this.save(entity);
	}

	@Override
	public void readMessage(String sendUserId, String receiveUserId) {
		QueryWrapper<BaseImContentEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseImContentEntity::getSendUserId, sendUserId);
		queryWrapper.lambda().eq(BaseImContentEntity::getReceiveUserId, receiveUserId);
		queryWrapper.lambda().eq(BaseImContentEntity::getState, 0);
		List<BaseImContentEntity> list = this.list(queryWrapper);
		for (BaseImContentEntity entity : list) {
			entity.setState(1);
			entity.setReceiveTime(LocalDateTime.now());
			this.updateById(entity);
		}
	}
}
