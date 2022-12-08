package com.sinosoft.ie.booster.admin.controller;

import com.sinosoft.ie.booster.admin.api.entity.BaseMessageEntity;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.admin.api.model.message.*;
import com.sinosoft.ie.booster.admin.service.BaseMessageService;
import com.sinosoft.ie.booster.admin.service.SysUserService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统公告
 *
 * @author booster开发平台组
 * @since 2019年9月27日 上午9:18
 */
@Api(tags = "系统公告")
@RestController
@RequestMapping("/Message")
public class NoticeController {

	@Autowired
	private BaseMessageService messageService;
	@Autowired
	private SysUserService userService;

	/**
	 * 列表（通知公告）
	 *
	 * @param pagination
	 * @return
	 */
	@ApiOperation("获取系统公告列表（带分页）")
	@GetMapping("/Notice")
	public R<PageListVO<MessageNoticeVO>> NoticeList(Pagination pagination) {
		List<BaseMessageEntity> list = messageService.getNoticeList(pagination);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(pagination, PaginationVO.class);
		List<MessageNoticeVO> listVOS = JsonUtil.getJsonToList(list, MessageNoticeVO.class);
		PageListVO<MessageNoticeVO> vo = new PageListVO<>();
		vo.setList(listVOS);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 添加系统公告
	 *
	 * @param noticeCrForm 实体对象
	 * @return
	 */
	@ApiOperation("添加系统公告")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid NoticeCrForm noticeCrForm) {
		BaseMessageEntity entity = JsonUtil.getJsonToBean(noticeCrForm, BaseMessageEntity.class);
		messageService.create(entity);
		return R.ok(null, "新建成功");
	}

	/**
	 * 修改系统公告
	 *
	 * @param id            主键值
	 * @param messageUpForm 实体对象
	 * @return
	 */
	@ApiOperation("修改系统公告")
	@PutMapping("/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid NoticeUpForm messageUpForm) {
		BaseMessageEntity entity = JsonUtil.getJsonToBean(messageUpForm, BaseMessageEntity.class);
		boolean flag = messageService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok(null, "更新成功");
	}

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取/查看系统公告信息")
	@GetMapping("/{id}")
	public R<NoticeInfoVO> Info(@PathVariable("id") Long id) throws DataException {
		BaseMessageEntity entity = messageService.getInfo(id);
		NoticeInfoVO vo = JsonUtil.getJsonToBeanEx(entity, NoticeInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 删除
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("删除系统公告")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		BaseMessageEntity entity = messageService.getInfo(id);
		if (entity != null) {
			messageService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	/**
	 * 发布公告
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("发布系统公告")
	@PutMapping("/{id}/Actions/Release")
	public R<Boolean> release(@PathVariable("id") Long id, @RequestBody List<String> userIds) {
		BaseMessageEntity entity = messageService.getInfo(id);
		if (entity != null) {
			if (userIds.isEmpty()) {
				List<UserVO> userList = userService.selectVoList(new UserDTO());
				userIds = userList.stream().map(u -> String.valueOf(u.getUsername())).collect(Collectors.toList());
			}
			entity.setToUserIds(String.join(",", userIds));
			messageService.sentNotice(userIds, entity);
			return R.ok(null, "发布成功");
		}
		return R.failed("发布失败");
	}

	/**
	 * 发布工作流消息
	 *
	 * @param
	 * @return
	 */
	@ApiOperation("发布工作流消息")
	@GetMapping("/flow/sentMessage")
	public R<Boolean> sentMessage(List<String> toUserIds, String title, String bodyText) {
		messageService.sentMessage(toUserIds, title, bodyText);
		return R.failed("发布成功");
	}
//=======================================站内消息、消息中心=================================================


	/**
	 * 获取消息中心列表
	 *
	 * @param pagination
	 * @return
	 */
	@ApiOperation("列表（通知公告/系统消息/私信消息）")
	@GetMapping
	public R<PageListVO<MessageInfoVO>> messageList(PaginationMessage pagination) {
		List<BaseMessageEntity> list = messageService.getMessageList(pagination, pagination.getType());
		List<MessageInfoVO> listVO = JsonUtil.getJsonToList(list, MessageInfoVO.class);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(pagination, PaginationVO.class);
		PageListVO<MessageInfoVO> vo = new PageListVO<>();
		vo.setList(listVO);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}


	/**
	 * 读取消息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("读取消息")
	@GetMapping("/ReadInfo/{id}")
	public R<NoticeInfoVO> readInfo(@PathVariable("id") Long id) throws DataException {
		BaseMessageEntity entity = messageService.getInfo(id);
		if (entity != null) {
			messageService.messageRead(id);
		}
		NoticeInfoVO vo = JsonUtil.getJsonToBeanEx(entity, NoticeInfoVO.class);
		return R.ok(vo);
	}


	/**
	 * 全部已读
	 *
	 * @return
	 */
	@ApiOperation("全部已读")
	@PostMapping("/Actions/ReadAll")
	public R<Boolean> allRead() {
		messageService.messageRead();
		return R.ok(null, "已读成功");
	}

	/**
	 * 删除记录
	 *
	 * @return
	 */
	@ApiOperation("删除消息")
	@DeleteMapping("/Record")
	public R<Boolean> deleteRecord(@RequestBody MessageRecordForm recordForm) {
		String[] id = recordForm.getIds().split(",");
		List<String> list = Arrays.asList(id);
		messageService.deleteRecord(list);
		return R.ok(null, "删除成功");
	}

	/**
	 * 列表（通知公告）
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/GetNoticeList")
	public List<BaseMessageEntity> getNoticeList() {
		return messageService.getNoticeList();
	}

	/**
	 * 发送消息
	 *
	 * @param sentMessageModel
	 * @return
	 */
	@PostMapping("/SentMessage")
	public void sentMessage(@RequestBody SentMessageModel sentMessageModel) {
		messageService.sentMessage(sentMessageModel.getToUserNames(), sentMessageModel.getTitle(), sentMessageModel.getBodyText());
	}

}
