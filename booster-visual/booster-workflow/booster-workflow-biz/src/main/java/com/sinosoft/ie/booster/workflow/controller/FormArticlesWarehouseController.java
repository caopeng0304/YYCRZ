package com.sinosoft.ie.booster.workflow.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.RegexUtils;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormArticlesWarehouseEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formarticleswarehous.ArticlesWarehousForm;
import com.sinosoft.ie.booster.workflow.model.formarticleswarehous.ArticlesWarehousInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormArticlesWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 用品入库申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "用品入库申请表", value = "ArticlesWarehous")
@RestController
@RequestMapping("/Form/ArticlesWarehous")
public class FormArticlesWarehouseController {

	@Autowired
	private FormArticlesWarehouseService articlesWarehousService;

	/**
	 * 获取用品入库申请表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取用品入库申请表信息")
	@GetMapping("/{id}")
	public R<ArticlesWarehousInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormArticlesWarehouseEntity entity = articlesWarehousService.getInfo(id);
		ArticlesWarehousInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, ArticlesWarehousInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建用品入库申请表
	 *
	 * @param articlesWarehousForm 表单对象
	 * @return
	 */
	@ApiOperation("新建用品入库申请表")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ArticlesWarehousForm articlesWarehousForm) throws WorkFlowException {
		if (articlesWarehousForm.getEstimatePeople() != null && StrUtil.isNotEmpty(articlesWarehousForm.getEstimatePeople()) && !RegexUtils.checkDigit2(articlesWarehousForm.getEstimatePeople())) {
			return R.failed("数量只能输入正整数");
		}
		FormArticlesWarehouseEntity entity = JsonUtil.getJsonToBean(articlesWarehousForm, FormArticlesWarehouseEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(articlesWarehousForm.getStatus())) {
			articlesWarehousService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		articlesWarehousService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改用品入库申请表
	 *
	 * @param articlesWarehousForm 表单对象
	 * @param id                   主键
	 * @return
	 */
	@ApiOperation("修改用品入库申请表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid ArticlesWarehousForm articlesWarehousForm, @PathVariable("id") Long id) throws WorkFlowException {
		if (articlesWarehousForm.getEstimatePeople() != null && StrUtil.isNotEmpty(articlesWarehousForm.getEstimatePeople()) && !RegexUtils.checkDigit2(articlesWarehousForm.getEstimatePeople())) {
			return R.failed("数量只能输入正整数");
		}
		FormArticlesWarehouseEntity entity = JsonUtil.getJsonToBean(articlesWarehousForm, FormArticlesWarehouseEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(articlesWarehousForm.getStatus())) {
			articlesWarehousService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		articlesWarehousService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
