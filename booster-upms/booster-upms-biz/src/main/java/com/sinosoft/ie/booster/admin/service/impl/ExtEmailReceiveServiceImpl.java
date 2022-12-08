package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailConfigEntity;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailReceiveEntity;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailSendEntity;
import com.sinosoft.ie.booster.admin.api.model.email.MailAccount;
import com.sinosoft.ie.booster.admin.api.model.email.MailFile;
import com.sinosoft.ie.booster.admin.api.model.email.MailModel;
import com.sinosoft.ie.booster.admin.mapper.ExtEmailReceiveMapper;
import com.sinosoft.ie.booster.admin.service.ExtEmailConfigService;
import com.sinosoft.ie.booster.admin.service.ExtEmailReceiveService;
import com.sinosoft.ie.booster.admin.service.ExtEmailSendService;
import com.sinosoft.ie.booster.admin.util.Pop3Util;
import com.sinosoft.ie.booster.admin.util.SmtpUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PaginationTime;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.constant.enums.FileTypeEnum;
import com.sinosoft.ie.booster.visualdev.constant.type.StringNumber;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.FileManageUtil;
import com.sinosoft.ie.booster.visualdev.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 邮件接收
 *
 * @author booster code generator
 * @since 2021-10-09
 */
@Service
public class ExtEmailReceiveServiceImpl extends ServiceImpl<ExtEmailReceiveMapper, ExtEmailReceiveEntity> implements ExtEmailReceiveService {

	@Autowired
	private ExtEmailSendService emailSendService;
	@Autowired
	private ExtEmailConfigService emailConfigService;
	@Autowired
	private Pop3Util pop3Util;
	@Autowired
	private FileManageUtil fileApi;

	@Override
	public List<ExtEmailReceiveEntity> getReceiveList(PaginationTime paginationTime) {
		String userId = SecurityUtils.getUser().getUsername();
		QueryWrapper<ExtEmailReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ExtEmailReceiveEntity::getCreateBy, userId);
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationTime.getStartTime() != null ? paginationTime.getStartTime() : null;
		String endTime = paginationTime.getEndTime() != null ? paginationTime.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			Date startTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00");
			Date endTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59");
			queryWrapper.lambda().ge(ExtEmailReceiveEntity::getSentDate, startTimes).le(ExtEmailReceiveEntity::getSentDate, endTimes);
		}
		//关键字（用户、IP地址、功能名称）
		String keyWord = paginationTime.getKeyword() != null ? paginationTime.getKeyword() : null;
		//关键字（发件人、主题）
		if (!StrUtil.isEmpty(keyWord)) {
			queryWrapper.lambda().and(
					t -> t.like(ExtEmailReceiveEntity::getSender, keyWord)
							.or().like(ExtEmailReceiveEntity::getSubject, keyWord)
			);
		}
		//排序
		if (StrUtil.isEmpty(paginationTime.getSidx())) {
			queryWrapper.lambda().orderByDesc(ExtEmailReceiveEntity::getSentDate);
		} else {
			queryWrapper = "asc".equalsIgnoreCase(paginationTime.getSort()) ? queryWrapper.orderByAsc(paginationTime.getSidx()) : queryWrapper.orderByDesc(paginationTime.getSidx());
		}
		Page<ExtEmailReceiveEntity> page = new Page<>(paginationTime.getCurrentPage(), paginationTime.getPageSize());
		IPage<ExtEmailReceiveEntity> userIPage = this.page(page, queryWrapper);
		return paginationTime.setData(userIPage.getRecords(), page.getTotal());
	}

	@Override
	public List<ExtEmailReceiveEntity> getReceiveList() {
		String userId = SecurityUtils.getUser().getUsername();
		QueryWrapper<ExtEmailReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ExtEmailReceiveEntity::getCreateBy, userId);

		return this.baseMapper.selectList(queryWrapper);
	}


	@Override
	public List<ExtEmailReceiveEntity> getStarredList(PaginationTime paginationTime) {
		String userId = SecurityUtils.getUser().getUsername();
		QueryWrapper<ExtEmailReceiveEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ExtEmailReceiveEntity::getCreateBy, userId).eq(ExtEmailReceiveEntity::getStarred, 1);
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationTime.getStartTime() != null ? paginationTime.getStartTime() : null;
		String endTime = paginationTime.getEndTime() != null ? paginationTime.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			Date startTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00");
			Date endTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59");
			queryWrapper.lambda().ge(ExtEmailReceiveEntity::getCreateTime, startTimes).le(ExtEmailReceiveEntity::getCreateTime, endTimes);
		}
		//关键字（用户、IP地址、功能名称）
		String keyWord = paginationTime.getKeyword() != null ? paginationTime.getKeyword() : null;
		//关键字（发件人、主题）
		if (!StrUtil.isEmpty(keyWord)) {
			queryWrapper.lambda().and(
					t -> t.like(ExtEmailReceiveEntity::getSender, keyWord)
							.or().like(ExtEmailReceiveEntity::getSubject, keyWord)
			);
		}
		//排序
		if (StrUtil.isEmpty(paginationTime.getSidx())) {
			queryWrapper.lambda().orderByDesc(ExtEmailReceiveEntity::getCreateTime);
		} else {
			queryWrapper = "asc".equalsIgnoreCase(paginationTime.getSort()) ? queryWrapper.orderByAsc(paginationTime.getSidx()) : queryWrapper.orderByDesc(paginationTime.getSidx());
		}
		Page<ExtEmailReceiveEntity> page = new Page<>(paginationTime.getCurrentPage(), paginationTime.getPageSize());
		IPage<ExtEmailReceiveEntity> userIPage = this.page(page, queryWrapper);
		return paginationTime.setData(userIPage.getRecords(), page.getTotal());
	}

	@Override
	public List<ExtEmailSendEntity> getDraftList(PaginationTime paginationTime) {
		String userId = SecurityUtils.getUser().getUsername();
		QueryWrapper<ExtEmailSendEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ExtEmailSendEntity::getCreateBy, userId).eq(ExtEmailSendEntity::getState, -1);
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationTime.getStartTime() != null ? paginationTime.getEndTime() : null;
		String endTime = paginationTime.getEndTime() != null ? paginationTime.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			Date startTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00");
			Date endTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59");
			queryWrapper.lambda().ge(ExtEmailSendEntity::getCreateTime, startTimes).le(ExtEmailSendEntity::getCreateTime, endTimes);
		}
		//关键字（用户、IP地址、功能名称）
		String keyWord = paginationTime.getKeyword() != null ? paginationTime.getKeyword() : null;
		//关键字（发件人、主题）
		if (!StrUtil.isEmpty(keyWord)) {
			queryWrapper.lambda().and(
					t -> t.like(ExtEmailSendEntity::getSender, keyWord)
							.or().like(ExtEmailSendEntity::getSubject, keyWord)
			);
		}
		//排序
		if (StrUtil.isEmpty(paginationTime.getSidx())) {
			queryWrapper.lambda().orderByDesc(ExtEmailSendEntity::getCreateTime);
		} else {
			queryWrapper = "asc".equalsIgnoreCase(paginationTime.getSort()) ? queryWrapper.orderByAsc(paginationTime.getSidx()) : queryWrapper.orderByDesc(paginationTime.getSidx());
		}
		Page<ExtEmailSendEntity> page = new Page<>(paginationTime.getCurrentPage(), paginationTime.getPageSize());
		IPage<ExtEmailSendEntity> userIPage = emailSendService.page(page, queryWrapper);
		return paginationTime.setData(userIPage.getRecords(), page.getTotal());
	}

	@Override
	public List<ExtEmailSendEntity> getSentList(PaginationTime paginationTime) {
		String userId = SecurityUtils.getUser().getUsername();
		QueryWrapper<ExtEmailSendEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ExtEmailSendEntity::getCreateBy, userId).ne(ExtEmailSendEntity::getState, -1);
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationTime.getStartTime() != null ? paginationTime.getStartTime() : null;
		String endTime = paginationTime.getEndTime() != null ? paginationTime.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			Date startTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00");
			Date endTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59");
			queryWrapper.lambda().ge(ExtEmailSendEntity::getCreateTime, startTimes).le(ExtEmailSendEntity::getCreateTime, endTimes);
		}
		//关键字（用户、IP地址、功能名称）
		String keyWord = paginationTime.getKeyword() != null ? String.valueOf(paginationTime.getKeyword()) : null;
		//关键字（发件人、主题）
		if (!StrUtil.isEmpty(keyWord)) {
			String word = keyWord;
			queryWrapper.lambda().and(
					t -> t.like(ExtEmailSendEntity::getSender, word)
							.or().like(ExtEmailSendEntity::getSubject, word)
			);
		}
		//排序
		String sidx = paginationTime.getSidx() != null ? paginationTime.getSidx() : null;
		if (!StrUtil.isEmpty(sidx)) {
			queryWrapper.lambda().orderByDesc(ExtEmailSendEntity::getCreateTime);
		}
		Page<ExtEmailSendEntity> page = new Page<>(paginationTime.getCurrentPage(), paginationTime.getPageSize());
		IPage<ExtEmailSendEntity> userIPage = emailSendService.page(page, queryWrapper);
		return paginationTime.setData(userIPage.getRecords(), page.getTotal());
	}

	@Override
	public ExtEmailConfigEntity getConfigInfo() {
		String userId = SecurityUtils.getUser().getUsername();
		QueryWrapper<ExtEmailConfigEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ExtEmailConfigEntity::getCreateBy, userId);
		return emailConfigService.getOne(queryWrapper);
	}

	@Override
	public ExtEmailConfigEntity getConfigInfo(String userId) {
		QueryWrapper<ExtEmailConfigEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ExtEmailConfigEntity::getCreateBy, userId);
		return emailConfigService.getOne(queryWrapper);
	}

	@Override
	public Object getInfo(Long id) {
		ExtEmailReceiveEntity receiveInfo = this.getById(id);
		Object object;
		if (receiveInfo != null) {
			//解析内容
			receiveInfo.setBodyText(receiveInfo.getBodyText());
			//更新已读
			receiveInfo.setIsRead(1);
			this.updateById(receiveInfo);
			object = receiveInfo;
		} else {
			object = emailSendService.getById(id);
		}
		return object;
	}

	@Override
	public boolean delete(Long id) {
		Object object = getInfo(id);
		if (object instanceof ExtEmailReceiveEntity) {
			//删除邮件
			ExtEmailConfigEntity mailConfig = getConfigInfo();
			ExtEmailReceiveEntity mailReceiveEntity = (ExtEmailReceiveEntity) object;
			MailAccount mailAccount = new MailAccount();
			mailAccount.setAccount(mailConfig.getAccount());
			mailAccount.setPassword(mailConfig.getPassword());
			mailAccount.setPop3Port(mailConfig.getPop3Port());
			mailAccount.setPop3Host(mailConfig.getPop3Host());
			pop3Util.deleteMessage(mailAccount, mailReceiveEntity.getMailId());
			this.removeById(mailReceiveEntity.getId());
			return true;
		} else if (object != null) {
			//删除数据
			ExtEmailSendEntity entity = (ExtEmailSendEntity) object;
			emailSendService.removeById(entity.getId());
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void saveDraft(ExtEmailSendEntity entity) {
		entity.setState(-1);
		if (entity.getId() != null) {
			emailSendService.updateById(entity);
		} else {
			emailSendService.save(entity);
		}
	}

	@Override
	public boolean receiveRead(Long id, int isRead) {
		ExtEmailReceiveEntity entity = (ExtEmailReceiveEntity) getInfo(id);
		if (entity != null) {
			entity.setIsRead(isRead);
			return this.updateById(entity);
		}
		return false;
	}

	@Override
	public boolean receiveStarred(Long id, int isStarred) {
		ExtEmailReceiveEntity entity = (ExtEmailReceiveEntity) getInfo(id);
		if (entity != null) {
			entity.setStarred(isStarred);
			return this.updateById(entity);
		}
		return false;
	}

	@Override
	public boolean checkLogin(ExtEmailConfigEntity configEntity) {
		MailAccount mailAccount = new MailAccount();
		mailAccount.setAccount(configEntity.getAccount());
		mailAccount.setPassword(configEntity.getPassword());
		mailAccount.setPop3Host(configEntity.getPop3Host());
		mailAccount.setPop3Port(configEntity.getPop3Port());
		mailAccount.setSmtpHost(configEntity.getSmtpHost());
		mailAccount.setSmtpPort(configEntity.getSmtpPort());
		mailAccount.setSsl(StringNumber.ONE.equals(String.valueOf(configEntity.getEmailSsl())));
		if (mailAccount.getSmtpHost() != null) {
			return SmtpUtil.checkConnected(mailAccount);
		}
		if (mailAccount.getPop3Host() != null) {
			return pop3Util.checkConnected(mailAccount);
		}
		return false;
	}

	@Override
	public void saveConfig(ExtEmailConfigEntity configEntity) throws DataException {
		String userId = SecurityUtils.getUser().getUsername();
		if (getConfigInfo(userId) == null && userId != null) {
			emailConfigService.save(configEntity);
		} else if (SecurityUtils.getUser().getUsername() != null) {
			emailConfigService.updateById(configEntity);
		} else {
			throw new DataException("保存失败，请重新登陆");
		}
	}

	@Override
	@Transactional
	public int saveSent(ExtEmailSendEntity entity, ExtEmailConfigEntity mailConfig) {
		int flag = 1;
		//拷贝文件,注意：从临时文件夹拷贝到邮件文件夹
		List<MailFile> attachmentList = JsonUtil.getJsonToList(entity.getAttachment(), MailFile.class);
		//临时文件路径
		String temporaryFile = fileApi.getFilePath(FileTypeEnum.TEMPORARY);
		//邮件路径
		String mailFilePath = fileApi.getFilePath(FileTypeEnum.MAIL);
		for (MailFile mailFile : attachmentList) {
			FileUtil.copyFile(temporaryFile + mailFile.getFileId(), mailFilePath + mailFile.getFileId());
		}
		try {
			//写入数据
			//发送邮件
			//邮件发送信息
			MailModel mailModel = new MailModel();
			mailModel.setFrom(entity.getSender());
			mailModel.setRecipient(entity.getRecipient());
			mailModel.setCc(entity.getCc());
			mailModel.setBcc(entity.getBcc());
			mailModel.setSubject(entity.getSubject());
			mailModel.setBodyText(entity.getBodyText());
			mailModel.setAttachment(attachmentList);
			mailModel.setFromName(mailConfig.getSenderName());
			//账号验证信息
			MailAccount mailAccount = new MailAccount();
			mailAccount.setAccount(mailConfig.getAccount());
			mailAccount.setPassword(mailConfig.getPassword());
			mailAccount.setPop3Host(mailConfig.getPop3Host());
			mailAccount.setPop3Port(mailConfig.getPop3Port());
			mailAccount.setSmtpHost(mailConfig.getSmtpHost());
			mailAccount.setSmtpPort(mailConfig.getSmtpPort());
			mailAccount.setSsl(mailConfig.getEmailSsl() == 1);
			mailAccount.setAccountName(mailConfig.getSenderName());
			SmtpUtil smtpUtil = new SmtpUtil(mailAccount);
			smtpUtil.sendMail(mailFilePath, mailModel);
			flag = 0;
			//插入数据库
			if (entity.getId() != null) {
				entity.setState(1);
				emailSendService.updateById(entity);
			} else {
				if (mailConfig.getAccount() != null) {
					entity.setSender(mailConfig.getAccount());
				}
				entity.setState(1);
				emailSendService.save(entity);
			}
		} catch (Exception e) {
			for (MailFile mailFile : attachmentList) {
				FileUtil.deleteFile(mailFilePath + mailFile.getFileId());
			}
			log.error(e.getMessage());
		}
		return flag;
	}

	@Override
	@Transactional
	public int receive(ExtEmailConfigEntity mailConfig) {
		//账号验证信息
		MailAccount mailAccount = new MailAccount();
		mailAccount.setAccount(mailConfig.getAccount());
		mailAccount.setPassword(mailConfig.getPassword());
		mailAccount.setPop3Host(mailConfig.getPop3Host());
		mailAccount.setPop3Port(mailConfig.getPop3Port());
		mailAccount.setSmtpHost(mailConfig.getSmtpHost());
		mailAccount.setSmtpPort(mailConfig.getSmtpPort());
		mailAccount.setSsl(StringNumber.ONE.equals(mailConfig.getEmailSsl().toString()));
		Map<String, Object> map = pop3Util.popMail(mailAccount);
		int receiveCount = 0;
		if (map.get("receiveCount") != null) {
			receiveCount = (int) map.get("receiveCount");
		}
		List<ExtEmailReceiveEntity> mailList = new ArrayList<>();
		if (map.get("mailList") != null) {
			mailList = (List<ExtEmailReceiveEntity>) map.get("mailList");
		}
		if (mailList.size() > 0) {
			List<String> mids = mailList.stream().map(ExtEmailReceiveEntity::getMailId).collect(Collectors.toList());
			//查询数据库状态
			QueryWrapper<ExtEmailReceiveEntity> wrapper = new QueryWrapper<>();
			wrapper.lambda().in(ExtEmailReceiveEntity::getMailId, mids);
			List<ExtEmailReceiveEntity> emails = this.list(wrapper);
			this.remove(wrapper);
			//邮件赋值状态
			for (ExtEmailReceiveEntity entity : mailList) {
				//通过数据库进行赋值，没有就默认0
				int stat = emails.stream().anyMatch(m -> m.getMailId().equals(entity.getMailId())) ? emails.stream().filter(m -> m.getMailId().equals(entity.getMailId())).findFirst().get().getIsRead() : 0;
				long count = emails.stream().filter(m -> m.getMailId().equals(entity.getMailId())).count();
				entity.setIsRead(stat);
				if (count != 0) {
					receiveCount--;
				}
				this.save(entity);
			}
		}
		return receiveCount;
	}
}
