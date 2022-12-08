package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormArchivalBorrowEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formarchivalborrow.ArchivalBorrowForm;
import com.sinosoft.ie.booster.workflow.model.formarchivalborrow.ArchivalBorrowInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormArchivalBorrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 档案借阅申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "档案借阅申请", value = "ArchivalBorrow")
@RestController
@RequestMapping("/Form/ArchivalBorrow")
public class FormArchivalBorrowController {

	@Autowired
	private FormArchivalBorrowService archivalBorrowService;

	/**
	 * 获取档案借阅申请信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取档案借阅申请信息")
	@GetMapping("/{id}")
	public R<ArchivalBorrowInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormArchivalBorrowEntity entity = archivalBorrowService.getInfo(id);
		ArchivalBorrowInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ArchivalBorrowInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建档案借阅申请
	 *
	 * @param archivalBorrowForm 表单对象
	 * @return
	 */
	@ApiOperation("新建档案借阅申请")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ArchivalBorrowForm archivalBorrowForm) throws WorkFlowException {
		if (archivalBorrowForm.getBorrowingDate() > archivalBorrowForm.getReturnDate()) {
			return R.failed("归还时间不能小于借阅时间");
		}
		FormArchivalBorrowEntity entity = JsonUtil.getJsonToBean(archivalBorrowForm, FormArchivalBorrowEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(archivalBorrowForm.getStatus())) {
			archivalBorrowService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		archivalBorrowService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改档案借阅申请
	 *
	 * @param archivalBorrowForm 表单对象
	 * @param id                 主键
	 * @return
	 */
	@ApiOperation("修改档案借阅申请")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ArchivalBorrowForm archivalBorrowForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (archivalBorrowForm.getBorrowingDate() > archivalBorrowForm.getReturnDate()) {
			return R.failed("归还时间不能小于借阅时间");
		}
		FormArchivalBorrowEntity entity = JsonUtil.getJsonToBean(archivalBorrowForm, FormArchivalBorrowEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(archivalBorrowForm.getStatus())) {
			archivalBorrowService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		archivalBorrowService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
