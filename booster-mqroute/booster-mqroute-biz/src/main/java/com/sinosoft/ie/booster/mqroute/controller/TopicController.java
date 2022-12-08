package com.sinosoft.ie.booster.mqroute.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.annotation.Inner;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicCrForm;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicInfoVO;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicListVO;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicPagination;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicUpForm;
import com.sinosoft.ie.booster.mqroute.service.TopicService;
import com.sinosoft.ie.booster.mqroute.topic.TopicHandle;
import com.sinosoft.ie.booster.visualdev.util.GeneraterSwapUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 消息队列组件 @版本： V1.0.0 @作者： booster开发平台组 @日期： 2022-03-15 15:12:51
 */
@Slf4j
@RestController
@Api(tags = "消息队列组件")
@RequestMapping("/Topic")
public class TopicController {
	@Autowired
	private GeneraterSwapUtil generaterSwapUtil;
	@Autowired
	private TopicService topicservice;

	/**
	 * 列表分页
	 *
	 * @param topicPagination
	 * @return
	 */
	@GetMapping
	public R list(TopicPagination topicPagination) throws IOException {
		List<TopicEntity> list = topicservice.getList(topicPagination);
		// 处理id字段转名称，若无需转或者为空可删除

		List<TopicListVO> listVO = JsonUtil.getJsonToList(list, TopicListVO.class);
		PageListVO vo = new PageListVO();
		vo.setList(listVO);
		PaginationVO page = JsonUtil.getJsonToBean(topicPagination, PaginationVO.class);
		vo.setPagination(page);
		return R.ok(vo);
	}

	@GetMapping("/api")
	public R list() {
		QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("state", 2);
		List<TopicEntity> list = topicservice.list(queryWrapper);
		return R.ok(list);
	}

	/**
	 * 创建
	 *
	 * @param topicCrForm
	 * @return
	 */
	@PostMapping
	@Transactional
	public R create(@RequestBody @Valid TopicCrForm topicCrForm) throws DataException {
		TopicEntity entity = JsonUtil.getJsonToBean(topicCrForm, TopicEntity.class);
		return topicservice.create(entity);
	}

	/**
	 * 信息
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public R<TopicInfoVO> info(@PathVariable("id") Long id) {
		TopicEntity entity = topicservice.getInfo(id);
		TopicInfoVO vo = JsonUtil.getJsonToBean(entity, TopicInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 更新状态
	 * 
	 * @param id
	 * @param topicUpForm
	 * @return
	 */
	@PutMapping("/{id}")
	@Transactional
	public R updateState(@PathVariable("id") Long id, @RequestBody @Valid TopicUpForm topicUpForm) {
		topicUpForm.setId(id);
		return topicservice.updateSatae(topicUpForm);
	}

	/**
	 * 更新
	 *
	 * @param id
	 * @return
	 */
	// @PutMapping("/{id}") 主题不能更新
	@Transactional
	public R update(@PathVariable("id") Long id, @RequestBody @Valid TopicUpForm topicUpForm) throws DataException {
		TopicEntity entity = topicservice.getInfo(id);
		if (entity != null) {
			topicservice.delete(entity);
			entity = JsonUtil.getJsonToBean(topicUpForm, TopicEntity.class);
			entity.setId(id);
			topicservice.create(entity);
			return R.ok(null, "更新成功");
		} else {
			return R.failed("更新失败，数据不存在");
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@Transactional
	public R delete(@PathVariable("id") Long id) {
		TopicEntity entity = topicservice.getInfo(id);
		if (entity != null) {
			topicservice.delete(entity);
			return R.ok(null, "删除成功");
		} else {
			return R.failed("删除失败，数据不存在");
		}
	}

	@Autowired
	private TopicHandle topicHandle;

	@PostMapping("/deleteById")
	@Transactional
	public R deleteById(String name) {
		topicHandle.deleteTopic(name);
		return R.ok();
	}

}
