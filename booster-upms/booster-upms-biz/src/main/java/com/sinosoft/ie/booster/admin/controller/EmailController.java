package com.sinosoft.ie.booster.admin.controller;

import com.sinosoft.ie.booster.admin.api.entity.ExtEmailConfigEntity;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailReceiveEntity;
import com.sinosoft.ie.booster.admin.api.entity.ExtEmailSendEntity;
import com.sinosoft.ie.booster.admin.api.model.email.*;
import com.sinosoft.ie.booster.admin.service.ExtEmailReceiveService;
import com.sinosoft.ie.booster.admin.util.Pop3Util;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationTime;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.constant.type.StringNumber;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 邮件配置
 *
 * @author booster开发平台组
 * @since 2019年9月26日 上午9:18
 */
@Api(tags = "邮件收发", value = "Email")
@RestController
@RequestMapping("/Email")
public class EmailController {

	@Autowired
	private ExtEmailReceiveService emailReceiveService;
	@Autowired
	private Pop3Util pop3Util;

	/**
	 * 获取邮件列表(收件箱、标星件、草稿箱、已发送)
	 * 此接口不符合单一职责设计原则，建议用特定类型的获取邮件列表接口替代
	 *
	 * @param paginationEmail
	 * @return
	 */
	@ApiOperation("获取邮件列表(收件箱、标星件、草稿箱、已发送)")
	@GetMapping
	@Deprecated
	public R<?> receiveList(PaginationEmail paginationEmail) {
		String type = paginationEmail.getType() != null ? paginationEmail.getType() : "inBox";
		switch (type) {
			case "inBox":
				return receiveInBoxList(paginationEmail);
			case "star":
				return receiveStarList(paginationEmail);
			case "draft":
				return receiveDraftList(paginationEmail);
			case "sent":
				return receiveSentList(paginationEmail);
			default:
				return R.failed("获取失败");
		}
	}

	/**
	 * 获取收件箱邮件列表
	 *
	 * @param paginationTime
	 * @return
	 */
	@ApiOperation("获取收件箱邮件列表")
	@GetMapping("/inBox")
	public R<PageListVO<EmailReceiveListVO>> receiveInBoxList(PaginationTime paginationTime) {
		List<ExtEmailReceiveEntity> entity = emailReceiveService.getReceiveList(paginationTime);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationTime, PaginationVO.class);
		List<EmailReceiveListVO> listVO = JsonUtil.getJsonToList(entity, EmailReceiveListVO.class);
		PageListVO<EmailReceiveListVO> vo = new PageListVO<>();
		vo.setList(listVO);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 获取标星件邮件列表
	 *
	 * @param paginationTime
	 * @return
	 */
	@ApiOperation("获取标星件邮件列表")
	@GetMapping("/star")
	public R<PageListVO<EmailStarredListVO>> receiveStarList(PaginationTime paginationTime) {
		List<ExtEmailReceiveEntity> entity1 = emailReceiveService.getStarredList(paginationTime);
		PaginationVO paginationVo1 = JsonUtil.getJsonToBean(paginationTime, PaginationVO.class);
		List<EmailStarredListVO> listVo1 = JsonUtil.getJsonToList(entity1, EmailStarredListVO.class);
		PageListVO<EmailStarredListVO> vo1 = new PageListVO<>();
		vo1.setList(listVo1);
		vo1.setPagination(paginationVo1);
		return R.ok(vo1);
	}

	/**
	 * 获取草稿箱邮件列表
	 *
	 * @param paginationTime
	 * @return
	 */
	@ApiOperation("获取草稿箱邮件列表")
	@GetMapping("/draft")
	public R<PageListVO<EmailDraftListVO>> receiveDraftList(PaginationTime paginationTime) {
		List<ExtEmailSendEntity> entity2 = emailReceiveService.getDraftList(paginationTime);
		PaginationVO paginationVo2 = JsonUtil.getJsonToBean(paginationTime, PaginationVO.class);
		List<EmailDraftListVO> listVo2 = JsonUtil.getJsonToList(entity2, EmailDraftListVO.class);
		PageListVO<EmailDraftListVO> vo2 = new PageListVO<>();
		vo2.setList(listVo2);
		vo2.setPagination(paginationVo2);
		return R.ok(vo2);
	}

	/**
	 * 获取已发送邮件列表
	 *
	 * @param paginationTime
	 * @return
	 */
	@ApiOperation("获取已发送邮件列表")
	@GetMapping("/sent")
	public R<PageListVO<EmailSentListVO>> receiveSentList(PaginationTime paginationTime) {
		List<ExtEmailSendEntity> entity3 = emailReceiveService.getSentList(paginationTime);
		PaginationVO paginationVo3 = JsonUtil.getJsonToBean(paginationTime, PaginationVO.class);
		List<EmailSentListVO> listVo3 = JsonUtil.getJsonToList(entity3, EmailSentListVO.class);
		PageListVO<EmailSentListVO> vo3 = new PageListVO<>();
		vo3.setList(listVo3);
		vo3.setPagination(paginationVo3);
		return R.ok(vo3);
	}

	/**
	 * 获取邮箱配置
	 *
	 * @return
	 */
	@ApiOperation("获取邮箱配置")
	@GetMapping("/Config")
	public R<EmailCofigInfoVO> configInfo() {
		ExtEmailConfigEntity entity = emailReceiveService.getConfigInfo();
		EmailCofigInfoVO vo = JsonUtil.getJsonToBean(entity, EmailCofigInfoVO.class);
		if (vo == null) {
			vo = new EmailCofigInfoVO();
		}
		return R.ok(vo);
	}

	/**
	 * 获取邮件信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取邮件信息")
	@GetMapping("/{id}")
	public R<EmailInfoVO> info(@PathVariable("id") Long id) throws DataException {
		Object entity = emailReceiveService.getInfo(id);
		EmailInfoVO vo = JsonUtil.getJsonToBeanEx(entity, EmailInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 删除
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("删除邮件")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		boolean flag = emailReceiveService.delete(id);
		if (!flag) {
			return R.failed("删除失败，邮件不存在");
		}
		return R.ok(null, "删除成功");
	}

	/**
	 * 设置已读邮件
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("设置已读邮件")
	@PutMapping("/{id}/Actions/Read")
	public R<Boolean> receiveRead(@PathVariable("id") Long id) {
		boolean flag = emailReceiveService.receiveRead(id, 1);
		if (!flag) {
			return R.failed("操作失败，邮件不存在");
		}
		return R.ok(null, "操作成功");
	}

	/**
	 * 设置未读邮件
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("设置未读邮件")
	@PutMapping("/{id}/Actions/Unread")
	public R<Boolean> receiveUnread(@PathVariable("id") Long id) {
		boolean flag = emailReceiveService.receiveRead(id, 0);
		if (!flag) {
			return R.failed("操作失败，邮件不存在");
		}
		return R.ok(null, "操作成功");
	}

	/**
	 * 设置星标邮件
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("设置星标邮件")
	@PutMapping("/{id}/Actions/Star")
	public R<Boolean> receiveYesStarred(@PathVariable("id") Long id) {
		boolean flag = emailReceiveService.receiveStarred(id, 1);
		if (!flag) {
			return R.failed("操作失败，邮件不存在");
		}
		return R.ok(null, "操作成功");
	}

	/**
	 * 设置取消星标
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("设置取消星标")
	@PutMapping("/{id}/Actions/Unstar")
	public R<Boolean> receiveNoStarred(@PathVariable("id") Long id) {
		boolean flag = emailReceiveService.receiveStarred(id, 0);
		if (!flag) {
			return R.failed("操作失败，邮件不存在");
		}
		return R.ok(null, "操作成功");
	}

	/**
	 * 收邮件
	 *
	 * @return
	 */
	@ApiOperation("收邮件")
	@PostMapping("/Receive")
	public R<Integer> receive() {
		ExtEmailConfigEntity configEntity = emailReceiveService.getConfigInfo();
		if (configEntity != null) {
			MailAccount mailAccount = new MailAccount();
			mailAccount.setAccount(configEntity.getAccount());
			mailAccount.setPassword(configEntity.getPassword());
			mailAccount.setPop3Host(configEntity.getPop3Host());
			mailAccount.setPop3Port(configEntity.getPop3Port());
			mailAccount.setSmtpHost(configEntity.getSmtpHost());
			mailAccount.setSmtpPort(configEntity.getSmtpPort());
			mailAccount.setSsl(StringNumber.ONE.equals(String.valueOf(configEntity.getEmailSsl())));
			if (pop3Util.checkConnected(mailAccount)) {
				int mailCount = emailReceiveService.receive(configEntity);
				return R.ok(mailCount, "操作成功");
			} else {
				return R.failed("账户认证错误");
			}
		} else {
			return R.failed("你还没有设置邮件的帐户");
		}
	}

	/**
	 * 存草稿
	 *
	 * @return
	 */
	@ApiOperation("存草稿")
	@PostMapping("/Actions/SaveDraft")
	public R<Boolean> saveDraft(@RequestBody @Valid EmailSendCrForm emailSendCrForm) {
		ExtEmailSendEntity entity = JsonUtil.getJsonToBean(emailSendCrForm, ExtEmailSendEntity.class);
		emailReceiveService.saveDraft(entity);
		return R.ok(null, "保存成功");
	}

	/**
	 * 发邮件
	 *
	 * @return
	 */
	@ApiOperation("发邮件")
	@PostMapping
	public R<Boolean> saveSent(@RequestBody @Valid EmailCrForm emailCrForm) {
		ExtEmailSendEntity entity = JsonUtil.getJsonToBean(emailCrForm, ExtEmailSendEntity.class);
		ExtEmailConfigEntity configEntity = emailReceiveService.getConfigInfo();
		if (configEntity != null) {
			MailAccount mailAccount = new MailAccount();
			mailAccount.setAccount(configEntity.getAccount());
			mailAccount.setPassword(configEntity.getPassword());
			mailAccount.setPop3Host(configEntity.getPop3Host());
			mailAccount.setPop3Port(configEntity.getPop3Port());
			mailAccount.setSmtpHost(configEntity.getSmtpHost());
			mailAccount.setSmtpPort(configEntity.getSmtpPort());
			mailAccount.setSsl(StringNumber.ONE.equals(String.valueOf(configEntity.getEmailSsl())));
			int flag = emailReceiveService.saveSent(entity, configEntity);
			if (flag == 0) {
				return R.ok(null, "发送成功");
			} else {
				return R.failed("账户认证错误");
			}
		} else {
			return R.failed("你还没有设置邮件的帐户");
		}
	}

	/**
	 * 更新邮件配置
	 *
	 * @return
	 */
	@ApiOperation("更新邮件配置")
	@PutMapping("/Config")
	public R<Boolean> saveConfig(@RequestBody @Valid EmailCheckForm emailCheckForm) throws DataException {
		ExtEmailConfigEntity entity = JsonUtil.getJsonToBean(emailCheckForm, ExtEmailConfigEntity.class);
		emailReceiveService.saveConfig(entity);
		return R.ok(null, "保存成功");
	}

	/**
	 * 邮箱配置-测试连接
	 *
	 * @return
	 */
	@ApiOperation("邮箱配置-测试连接")
	@PostMapping("/Config/Actions/CheckMail")
	public R<Boolean> checkLogin(@RequestBody @Valid EmailCheckForm emailCheckForm) {
		ExtEmailConfigEntity entity = JsonUtil.getJsonToBean(emailCheckForm, ExtEmailConfigEntity.class);
		boolean result = emailReceiveService.checkLogin(entity);
		if (result) {
			return R.ok(null, "验证成功");
		} else {
			return R.failed("账户认证错误");
		}
	}

	/**
	 * 列表（收件箱）
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/GetReceiveList")
	public List<ExtEmailReceiveEntity> getReceiveList() {
		return emailReceiveService.getReceiveList();
	}
}
