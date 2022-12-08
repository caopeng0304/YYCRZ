package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormWorkContactSheetEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formworkcontactsheet.WorkContactSheetForm;
import com.sinosoft.ie.booster.workflow.model.formworkcontactsheet.WorkContactSheetInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormWorkContactSheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 工作联系单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "工作联系单", value = "WorkContactSheet")
@RestController
@RequestMapping("/Form/WorkContactSheet")
public class FormWorkContactSheetController {

	@Autowired
	private FormWorkContactSheetService workContactSheetService;

	/**
	 * 获取工作联系单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取工作联系单信息")
	@GetMapping("/{id}")
	public R<WorkContactSheetInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormWorkContactSheetEntity entity = workContactSheetService.getInfo(id);
		WorkContactSheetInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, WorkContactSheetInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建工作联系单
	 *
	 * @param workContactSheetForm 表单对象
	 * @return
	 */
	@ApiOperation("新建工作联系单")
	@PostMapping
	public R<Boolean> create(@RequestBody WorkContactSheetForm workContactSheetForm) throws WorkFlowException {
		FormWorkContactSheetEntity entity = JsonUtil.getJsonToBean(workContactSheetForm, FormWorkContactSheetEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(workContactSheetForm.getStatus())) {
			workContactSheetService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		workContactSheetService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改工作联系单
	 *
	 * @param workContactSheetForm 表单对象
	 * @param id                   主键
	 * @return
	 */
	@ApiOperation("修改工作联系单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody WorkContactSheetForm workContactSheetForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormWorkContactSheetEntity entity = JsonUtil.getJsonToBean(workContactSheetForm, FormWorkContactSheetEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(workContactSheetForm.getStatus())) {
			workContactSheetService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		workContactSheetService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
