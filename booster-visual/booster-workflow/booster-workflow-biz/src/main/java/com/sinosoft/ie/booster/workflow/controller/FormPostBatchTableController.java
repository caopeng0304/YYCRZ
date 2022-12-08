package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.workflow.entity.FormPostBatchTableEntity;
import com.sinosoft.ie.booster.workflow.enums.FlowStatusEnum;
import com.sinosoft.ie.booster.workflow.model.formpostbatchtab.PostBatchTabForm;
import com.sinosoft.ie.booster.workflow.model.formpostbatchtab.PostBatchTabInfoVO;
import com.sinosoft.ie.booster.workflow.service.FormPostBatchTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 发文呈批表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Api(tags = "发文呈批表", value = "PostBatchTab")
@RestController
@RequestMapping("/Form/PostBatchTab")
public class FormPostBatchTableController {

	@Autowired
	private FormPostBatchTableService postBatchTabService;

	/**
	 * 获取发文呈批表信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取发文呈批表信息")
	@GetMapping("/{id}")
	public R<PostBatchTabInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FormPostBatchTableEntity entity = postBatchTabService.getInfo(id);
		PostBatchTabInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, PostBatchTabInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建发文呈批表
	 *
	 * @param postBatchTabForm 表单对象
	 * @return
	 */
	@ApiOperation("新建发文呈批表")
	@PostMapping
	public R<Boolean> create(@RequestBody PostBatchTabForm postBatchTabForm) throws WorkFlowException {
		FormPostBatchTableEntity entity = JsonUtil.getJsonToBean(postBatchTabForm, FormPostBatchTableEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(postBatchTabForm.getStatus())) {
			postBatchTabService.save(entity.getId(), entity);
			return R.ok(null, "保存成功");
		}
		postBatchTabService.submit(entity.getId(), entity);
		return R.ok(null, "提交成功，请耐心等待");
	}

	/**
	 * 修改发文呈批表
	 *
	 * @param postBatchTabForm 表单对象
	 * @param id               主键
	 * @return
	 */
	@ApiOperation("修改发文呈批表")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody PostBatchTabForm postBatchTabForm, @PathVariable("id") Long id) throws WorkFlowException {
		FormPostBatchTableEntity entity = JsonUtil.getJsonToBean(postBatchTabForm, FormPostBatchTableEntity.class);
		if (FlowStatusEnum.save.getMessage().equals(postBatchTabForm.getStatus())) {
			postBatchTabService.save(id, entity);
			return R.ok(null, "保存成功");
		}
		postBatchTabService.submit(id, entity);
		return R.ok(null, "提交成功，请耐心等待");
	}
}
