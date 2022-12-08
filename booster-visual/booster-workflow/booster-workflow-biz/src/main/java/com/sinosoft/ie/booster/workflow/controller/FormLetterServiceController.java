package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormLetterServiceEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formletterservice.LetterServiceForm;
import com.sinosoft.ie.booster.workflow.model.formletterservice.LetterServiceInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormLetterServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 发文单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "发文单", value = "LetterService")
@RestController
@RequestMapping("/Form/LetterService")
public class FormLetterServiceController {

	@Autowired
	private FormLetterServiceService letterServiceService;

	/**
	 * 获取发文单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取发文单信息")
	@GetMapping("/{id}")
	public R<LetterServiceInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormLetterServiceEntity entity = letterServiceService.getInfo(id);
		LetterServiceInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, LetterServiceInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建发文单
	 *
	 * @param letterServiceForm 表单对象
	 * @return
	 */
	@ApiOperation("新建发文单")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid LetterServiceForm letterServiceForm) throws WorkFlowException {
		R<Boolean> result;
		if (letterServiceForm.getShareNum() != null && StrUtil.isNotEmpty(letterServiceForm.getShareNum()) && !RegexUtils.checkDigit2(letterServiceForm.getShareNum())) {
			result = R.failed("份数只能输入正整数");
		} else {
			FormLetterServiceEntity entity = JsonUtil.getJsonToBean(letterServiceForm, FormLetterServiceEntity.class);
			if (FlowStatusEnum.save.getMessage().equals(letterServiceForm.getStatus())) {
				letterServiceService.save(entity.getId(), entity);
				result = R.ok(null, "保存成功");
			} else {
				letterServiceService.submit(entity.getId(), entity);
				result = R.ok(null, "提交成功，请耐心等待");
			}
		}
		return result;
	}

	/**
	 * 修改发文单
	 *
	 * @param letterServiceForm 表单对象
	 * @param id                主键
	 * @return
	 */
	@ApiOperation("修改发文单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid LetterServiceForm letterServiceForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (letterServiceForm.getShareNum() != null && StrUtil.isNotEmpty(letterServiceForm.getShareNum()) && !RegexUtils.checkDigit2(letterServiceForm.getShareNum())) {
			return R.failed("份数只能输入正整数");
		}
		FormLetterServiceEntity entity = JsonUtil.getJsonToBean(letterServiceForm, FormLetterServiceEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(letterServiceForm.getStatus())) {
			letterServiceService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		letterServiceService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
