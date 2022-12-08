package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormRewardPunishmentEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formrewardpunishment.RewardPunishmentForm;
import com.sinosoft.ie.booster.workflow.model.formrewardpunishment.RewardPunishmentInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormRewardPunishmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 行政赏罚单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "行政赏罚单", value = "RewardPunishment")
@RestController
@RequestMapping("/Form/RewardPunishment")
public class FormRewardPunishmentController {

	@Autowired
	private FormRewardPunishmentService rewardPunishmentService;

	/**
	 * 获取行政赏罚单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取行政赏罚单信息")
	@GetMapping("/{id}")
	public R<RewardPunishmentInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormRewardPunishmentEntity entity = rewardPunishmentService.getInfo(id);
		RewardPunishmentInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, RewardPunishmentInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建行政赏罚单
	 *
	 * @param rewardPunishmentForm 表单对象
	 * @return
	 */
	@ApiOperation("新建行政赏罚单")
	@PostMapping
	public R<Boolean> create(@RequestBody RewardPunishmentForm rewardPunishmentForm) throws WorkFlowException {
		FormRewardPunishmentEntity entity = JsonUtil.getJsonToBean(rewardPunishmentForm, FormRewardPunishmentEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(rewardPunishmentForm.getStatus())) {
			rewardPunishmentService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		rewardPunishmentService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改行政赏罚单
	 *
	 * @param rewardPunishmentForm 表单对象
	 * @param id                   主键
	 * @return
	 */
	@ApiOperation("修改行政赏罚单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody RewardPunishmentForm rewardPunishmentForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormRewardPunishmentEntity entity = JsonUtil.getJsonToBean(rewardPunishmentForm, FormRewardPunishmentEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(rewardPunishmentForm.getStatus())) {
			rewardPunishmentService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		rewardPunishmentService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
