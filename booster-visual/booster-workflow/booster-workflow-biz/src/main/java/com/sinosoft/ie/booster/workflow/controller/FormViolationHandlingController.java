package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormViolationHandlingEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formviolationhandling.ViolationHandlingForm;
import com.sinosoft.ie.booster.workflow.model.formviolationhandling.ViolationHandlingInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormViolationHandlingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 违章处理申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "违章处理申请表", value = "ViolationHandling")
@RestController
@RequestMapping("/Form/ViolationHandling")
public class FormViolationHandlingController {

	@Autowired
	private FormViolationHandlingService violationHandlingService;

	/**
	 * 获取违章处理申请表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取违章处理申请表信息")
	@GetMapping("/{id}")
	public R<ViolationHandlingInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormViolationHandlingEntity entity = violationHandlingService.getInfo(id);
		ViolationHandlingInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ViolationHandlingInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建违章处理申请表
	 *
	 * @param violationHandlingForm 表单对象
	 * @return
	 */
	@ApiOperation("新建违章处理申请表")
	@PostMapping
	public R<Boolean> create(@RequestBody ViolationHandlingForm violationHandlingForm) throws WorkFlowException {
		FormViolationHandlingEntity entity = JsonUtil.getJsonToBean(violationHandlingForm, FormViolationHandlingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(violationHandlingForm.getStatus())) {
			violationHandlingService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		violationHandlingService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改违章处理申请表
	 *
	 * @param violationHandlingForm 表单对象
	 * @param id                    主键
	 * @return
	 */
	@ApiOperation("修改违章处理申请表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody ViolationHandlingForm violationHandlingForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormViolationHandlingEntity entity = JsonUtil.getJsonToBean(violationHandlingForm, FormViolationHandlingEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(violationHandlingForm.getStatus())) {
			violationHandlingService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		violationHandlingService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
