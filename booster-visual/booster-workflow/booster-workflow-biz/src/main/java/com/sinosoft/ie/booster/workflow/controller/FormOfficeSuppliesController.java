package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormOfficeSuppliesEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formofficesupplies.OfficeSuppliesForm;
import com.sinosoft.ie.booster.workflow.model.formofficesupplies.OfficeSuppliesInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormOfficeSuppliesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 领用办公用品申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "领用办公用品申请表", value = "OfficeSupplies")
@RestController
@RequestMapping("/Form/OfficeSupplies")
public class FormOfficeSuppliesController {

	@Autowired
	private FormOfficeSuppliesService officeSuppliesService;

	/**
	 * 获取领用办公用品申请表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取领用办公用品申请表信息")
	@GetMapping("/{id}")
	public R<OfficeSuppliesInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormOfficeSuppliesEntity entity = officeSuppliesService.getInfo(id);
		OfficeSuppliesInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, OfficeSuppliesInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建领用办公用品申请表
	 *
	 * @param officeSuppliesForm 表单对象
	 * @return
	 */
	@ApiOperation("新建领用办公用品申请表")
	@PostMapping
	public R<Boolean> create(@RequestBody OfficeSuppliesForm officeSuppliesForm) throws WorkFlowException {
		FormOfficeSuppliesEntity entity = JsonUtil.getJsonToBean(officeSuppliesForm, FormOfficeSuppliesEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(officeSuppliesForm.getStatus())) {
			officeSuppliesService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		officeSuppliesService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改领用办公用品申请表
	 *
	 * @param officeSuppliesForm 表单对象
	 * @param id                 主键
	 * @return
	 */
	@ApiOperation("修改领用办公用品申请表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody OfficeSuppliesForm officeSuppliesForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormOfficeSuppliesEntity entity = JsonUtil.getJsonToBean(officeSuppliesForm, FormOfficeSuppliesEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(officeSuppliesForm.getStatus())) {
			officeSuppliesService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		officeSuppliesService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
