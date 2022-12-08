package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormFinishedProductEntity;
import com.sinosoft.ie.booster.workflow.entity.FormFinishedProductEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formfinishedproduct.FinishedProductEntryEntityInfoModel;
import com.sinosoft.ie.booster.workflow.model.formfinishedproduct.FinishedProductForm;
import com.sinosoft.ie.booster.workflow.model.formfinishedproduct.FinishedProductInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormFinishedProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 成品入库单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "成品入库单", value = "FinishedProduct")
@RestController
@RequestMapping("/Form/FinishedProduct")
public class FormFinishedProductController {

	@Autowired
	private FormFinishedProductService finishedProductService;

	/**
	 * 获取成品入库单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取成品入库单信息")
	@GetMapping("/{id}")
	public R<FinishedProductInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormFinishedProductEntity entity = finishedProductService.getInfo(id);
		List<FormFinishedProductEntryEntity> entityList = finishedProductService.getFinishedEntryList(id);
		FinishedProductInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, FinishedProductInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, FinishedProductEntryEntityInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建成品入库单
	 *
	 * @param finishedProductForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建成品入库单")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid FinishedProductForm finishedProductForm) throws WorkFlowException {
		FormFinishedProductEntity finished = JsonUtil.getJsonToBean(finishedProductForm, FormFinishedProductEntity.class);
		List<FormFinishedProductEntryEntity> finishedEntryList = JsonUtil.getJsonToList(finishedProductForm.getEntryList(), FormFinishedProductEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(finishedProductForm.getStatus())) {
			finishedProductService.save(finished.getId(), finished, finishedEntryList);
			return R.ok(null, "保存成功");
		}
		finishedProductService.submit(finished.getId(), finished, finishedEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改成品入库单
	 *
	 * @param finishedProductForm 表单对象
	 * @param id                  主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改成品入库单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid FinishedProductForm finishedProductForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormFinishedProductEntity finished = JsonUtil.getJsonToBean(finishedProductForm, FormFinishedProductEntity.class);
		List<FormFinishedProductEntryEntity> finishedEntryList = JsonUtil.getJsonToList(finishedProductForm.getEntryList(), FormFinishedProductEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(finishedProductForm.getStatus())) {
			finishedProductService.save(id, finished, finishedEntryList);
			return R.ok(null, "保存成功");
		}
		finishedProductService.submit(id, finished, finishedEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
