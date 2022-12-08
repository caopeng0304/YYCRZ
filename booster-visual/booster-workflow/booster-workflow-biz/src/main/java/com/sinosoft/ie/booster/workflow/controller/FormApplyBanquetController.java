package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormApplyBanquetEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formapplybanquet.ApplyBanquetForm;
import com.sinosoft.ie.booster.workflow.model.formapplybanquet.ApplyBanquetInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormApplyBanquetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 宴请申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "宴请申请", value = "ApplyBanquet")
@RestController
@RequestMapping("/Form/ApplyBanquet")
public class FormApplyBanquetController {

	@Autowired
	private FormApplyBanquetService applyBanquetService;

	/**
	 * 获取宴请申请信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取宴请申请信息")
	@GetMapping("/{id}")
	public R<ApplyBanquetInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormApplyBanquetEntity entity = applyBanquetService.getInfo(id);
		ApplyBanquetInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ApplyBanquetInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建宴请申请
	 *
	 * @param applyBanquetForm 表单对象
	 * @return
	 */
	@ApiOperation("新建宴请申请")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ApplyBanquetForm applyBanquetForm) throws WorkFlowException {
		if (applyBanquetForm.getBanquetNum() != null && StrUtil.isNotEmpty(applyBanquetForm.getBanquetNum()) && !RegexUtils.checkDigit2(applyBanquetForm.getBanquetNum())) {
			return R.failed("宴请人数必须大于0");
		}
		if (applyBanquetForm.getTotal() != null && StrUtil.isNotEmpty(applyBanquetForm.getTotal()) && !RegexUtils.checkDigit2(applyBanquetForm.getTotal())) {
			return R.failed("人员总数必须大于0");
		}
		if (applyBanquetForm.getExpectedCost() != null && !RegexUtils.checkDecimals2(String.valueOf(applyBanquetForm.getExpectedCost()))) {
			return R.failed("预计费用必须大于0，最多只能有两位小数");
		}
		FormApplyBanquetEntity entity = JsonUtil.getJsonToBean(applyBanquetForm, FormApplyBanquetEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(applyBanquetForm.getStatus())) {
			applyBanquetService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		applyBanquetService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改宴请申请
	 *
	 * @param applyBanquetForm 表单对象
	 * @param id               主键
	 * @return
	 */
	@ApiOperation("修改宴请申请")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ApplyBanquetForm applyBanquetForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (applyBanquetForm.getBanquetNum() != null && StrUtil.isNotEmpty(applyBanquetForm.getBanquetNum()) && !RegexUtils.checkDigit2(applyBanquetForm.getBanquetNum())) {
			return R.failed("宴请人数必须大于0");
		}
		if (applyBanquetForm.getTotal() != null && StrUtil.isNotEmpty(applyBanquetForm.getTotal()) && !RegexUtils.checkDigit2(applyBanquetForm.getTotal())) {
			return R.failed("人员总数必须大于0");
		}
		if (applyBanquetForm.getExpectedCost() != null && !RegexUtils.checkDecimals2(String.valueOf(applyBanquetForm.getExpectedCost()))) {
			return R.failed("预计费用必须大于0，最多只能有两位小数");
		}
		FormApplyBanquetEntity entity = JsonUtil.getJsonToBean(applyBanquetForm, FormApplyBanquetEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(applyBanquetForm.getStatus())) {
			applyBanquetService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		applyBanquetService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
