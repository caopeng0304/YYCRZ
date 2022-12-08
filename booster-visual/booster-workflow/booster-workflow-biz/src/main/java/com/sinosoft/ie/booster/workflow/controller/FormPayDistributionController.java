package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormPayDistributionEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formpaydistribution.PayDistributionForm;
import com.sinosoft.ie.booster.workflow.model.formpaydistribution.PayDistributionInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormPayDistributionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 薪酬发放
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "薪酬发放", value = "PayDistribution")
@RestController
@RequestMapping("/Form/PayDistribution")
public class FormPayDistributionController {

	@Autowired
	private FormPayDistributionService payDistributionService;

	/**
	 * 获取薪酬发放信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取薪酬发放信息")
	@GetMapping("/{id}")
	public R<PayDistributionInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormPayDistributionEntity entity = payDistributionService.getInfo(id);
		PayDistributionInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, PayDistributionInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建薪酬发放
	 *
	 * @param payDistributionForm 表单对象
	 * @return
	 */
	@ApiOperation("新建薪酬发放")
	@PostMapping
	public R<Boolean> create(@RequestBody PayDistributionForm payDistributionForm) throws WorkFlowException {
		FormPayDistributionEntity entity = JsonUtil.getJsonToBean(payDistributionForm, FormPayDistributionEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(payDistributionForm.getStatus())) {
			payDistributionService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		payDistributionService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改薪酬发放
	 *
	 * @param payDistributionForm 表单对象
	 * @param id                  主键
	 * @return
	 */
	@ApiOperation("修改薪酬发放")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody PayDistributionForm payDistributionForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormPayDistributionEntity entity = JsonUtil.getJsonToBean(payDistributionForm, FormPayDistributionEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(payDistributionForm.getStatus())) {
			payDistributionService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		payDistributionService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
