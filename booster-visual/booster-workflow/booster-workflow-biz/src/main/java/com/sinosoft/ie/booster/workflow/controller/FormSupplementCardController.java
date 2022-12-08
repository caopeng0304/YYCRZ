package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormSupplementCardEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formsupplementcard.SupplementCardForm;
import com.sinosoft.ie.booster.workflow.model.formsupplementcard.SupplementCardInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormSupplementCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 补卡申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "补卡申请", value = "SupplementCard")
@RestController
@RequestMapping("/Form/SupplementCard")
public class FormSupplementCardController {

	@Autowired
	private FormSupplementCardService supplementCardService;

	/**
	 * 获取补卡申请信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("补卡申请信息")
	@GetMapping("/{id}")
	public R<SupplementCardInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormSupplementCardEntity entity = supplementCardService.getInfo(id);
		SupplementCardInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, SupplementCardInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建补卡申请
	 *
	 * @param supplementCardForm 表单对象
	 * @return
	 */
	@ApiOperation("新建补卡申请")
	@PostMapping
	public R<Boolean> create(@RequestBody SupplementCardForm supplementCardForm) throws WorkFlowException {
		if (supplementCardForm.getStartTime() > supplementCardForm.getEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormSupplementCardEntity entity = JsonUtil.getJsonToBean(supplementCardForm, FormSupplementCardEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(supplementCardForm.getStatus())) {
			supplementCardService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		supplementCardService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改补卡申请
	 *
	 * @param supplementCardForm 表单对象
	 * @param id                 主键
	 * @return
	 */
	@ApiOperation("修改补卡申请")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody SupplementCardForm supplementCardForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (supplementCardForm.getStartTime() > supplementCardForm.getEndTime()) {
			return R.failed("结束时间不能小于起始时间");
		}
		FormSupplementCardEntity entity = JsonUtil.getJsonToBean(supplementCardForm, FormSupplementCardEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(supplementCardForm.getStatus())) {
			supplementCardService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		supplementCardService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
