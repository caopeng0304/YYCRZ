package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormVehicleApplyEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formvehicleapply.VehicleApplyForm;
import com.sinosoft.ie.booster.workflow.model.formvehicleapply.VehicleApplyInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormVehicleApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 车辆申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "车辆申请", value = "VehicleApply")
@RestController
@RequestMapping("/Form/VehicleApply")
public class FormVehicleApplyController {

	@Autowired
	private FormVehicleApplyService vehicleApplyService;

	/**
	 * 获取车辆申请信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取车辆申请信息")
	@GetMapping("/{id}")
	public R<VehicleApplyInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormVehicleApplyEntity entity = vehicleApplyService.getInfo(id);
		VehicleApplyInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, VehicleApplyInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建车辆申请
	 *
	 * @param vehicleApplyForm 表单对象
	 * @return
	 */
	@ApiOperation("新建车辆申请")
	@PostMapping
	public R<Boolean> create(@RequestBody VehicleApplyForm vehicleApplyForm) throws WorkFlowException {
		FormVehicleApplyEntity entity = JsonUtil.getJsonToBean(vehicleApplyForm, FormVehicleApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(vehicleApplyForm.getStatus())) {
			vehicleApplyService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		vehicleApplyService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 提交车辆申请
	 *
	 * @param vehicleApplyForm 表单对象
	 * @param id               主键
	 * @return
	 */
	@ApiOperation("修改车辆申请")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody VehicleApplyForm vehicleApplyForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormVehicleApplyEntity entity = JsonUtil.getJsonToBean(vehicleApplyForm, FormVehicleApplyEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(vehicleApplyForm.getStatus())) {
			vehicleApplyService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		vehicleApplyService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
