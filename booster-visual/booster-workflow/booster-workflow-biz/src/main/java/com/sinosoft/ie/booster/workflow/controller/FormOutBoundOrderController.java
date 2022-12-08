package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormOutBoundOrderEntity;
import com.sinosoft.ie.booster.workflow.entity.FormOutBoundOrderEntryEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formoutboundorder.OutboundEntryEntityInfoModel;
import com.sinosoft.ie.booster.workflow.model.formoutboundorder.OutboundOrderForm;
import com.sinosoft.ie.booster.workflow.model.formoutboundorder.OutboundOrderInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormOutBoundOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 出库单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "出库单", value = "OutboundOrder")
@RestController
@RequestMapping("/Form/OutboundOrder")
public class FormOutBoundOrderController {

	@Autowired
	private FormOutBoundOrderService outboundOrderService;

	/**
	 * 获取出库单信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取出库单信息")
	@GetMapping("/{id}")
	public R<OutboundOrderInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormOutBoundOrderEntity entity = outboundOrderService.getInfo(id);
		List<FormOutBoundOrderEntryEntity> entityList = outboundOrderService.getOutboundEntryList(id);
		OutboundOrderInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, OutboundOrderInfoVO.class);
		vo.setEntryList(JsonUtil.getJsonToList(entityList, OutboundEntryEntityInfoModel.class));
		return R.ok(vo);
	}

	/**
	 * 新建出库单
	 *
	 * @param outboundOrderForm 表单对象
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("新建出库单")
	@PostMapping
	public R<Boolean> create(@RequestBody OutboundOrderForm outboundOrderForm) throws WorkFlowException {
		FormOutBoundOrderEntity outbound = JsonUtil.getJsonToBean(outboundOrderForm, FormOutBoundOrderEntity.class);
		List<FormOutBoundOrderEntryEntity> outboundEntryList = JsonUtil.getJsonToList(outboundOrderForm.getEntryList(), FormOutBoundOrderEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(outboundOrderForm.getStatus())) {
			outboundOrderService.save(outbound.getId(), outbound, outboundEntryList);
			return R.ok(null, "保存成功");
		}
		outboundOrderService.submit(outbound.getId(), outbound, outboundEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改出库单
	 *
	 * @param outboundOrderForm 表单对象
	 * @param id                主键
	 * @return
	 * @throws WorkFlowException
	 */
	@ApiOperation("修改出库单")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody OutboundOrderForm outboundOrderForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormOutBoundOrderEntity outbound = JsonUtil.getJsonToBean(outboundOrderForm, FormOutBoundOrderEntity.class);
		List<FormOutBoundOrderEntryEntity> outboundEntryList = JsonUtil.getJsonToList(outboundOrderForm.getEntryList(), FormOutBoundOrderEntryEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(outboundOrderForm.getStatus())) {
			outboundOrderService.save(id, outbound, outboundEntryList);
			return R.ok(null, "保存成功");
		}
		outboundOrderService.submit(id, outbound, outboundEntryList);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
