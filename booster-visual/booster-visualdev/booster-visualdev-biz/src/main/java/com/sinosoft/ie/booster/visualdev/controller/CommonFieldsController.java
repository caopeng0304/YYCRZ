package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseCommonFieldsEntity;
import com.sinosoft.ie.booster.visualdev.model.comfields.ComFieldsCrForm;
import com.sinosoft.ie.booster.visualdev.model.comfields.ComFieldsInfoVO;
import com.sinosoft.ie.booster.visualdev.model.comfields.ComFieldsListVO;
import com.sinosoft.ie.booster.visualdev.model.comfields.ComFieldsUpForm;
import com.sinosoft.ie.booster.visualdev.service.BaseCommonFieldsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 常用字段表
 *
 * @author booster开发平台组
 * @since 2021-03-15 10:29
 */
@RestController
@Api(tags = "常用字段", value = "CommonFields")
@RequestMapping("/Base/CommonFields")
public class CommonFieldsController {

	@Autowired
	private BaseCommonFieldsService commonFieldsService;

	@ApiOperation("常用字段列表")
	@GetMapping
	public R<ListVO<ComFieldsListVO>> list() {
		List<BaseCommonFieldsEntity> data = commonFieldsService.getList();
		List<ComFieldsListVO> list = JsonUtil.getJsonToList(data, ComFieldsListVO.class);
		ListVO<ComFieldsListVO> vo = new ListVO<>();
		vo.setList(list);
		return R.ok(vo);
	}

	@ApiOperation("常用字段详情")
	@GetMapping("/{id}")
	public R<ComFieldsInfoVO> info(@PathVariable("id") Long id) throws DataException {
		BaseCommonFieldsEntity entity = commonFieldsService.getInfo(id);
		ComFieldsInfoVO vo = JsonUtil.getJsonToBeanEx(entity, ComFieldsInfoVO.class);
		return R.ok(vo);
	}

	@ApiOperation("新建常用字段")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid ComFieldsCrForm comFieldsCrForm) {
		BaseCommonFieldsEntity entity = JsonUtil.getJsonToBean(comFieldsCrForm, BaseCommonFieldsEntity.class);
		if (commonFieldsService.isExistByFullName(entity.getFieldName(), entity.getId())) {
			return R.failed("名称不能重复");
		}
		commonFieldsService.create(entity);
		return R.ok(null, "新建成功");
	}

	@ApiOperation("修改常用字段")
	@PutMapping("/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid ComFieldsUpForm comFieldsUpForm) {
		BaseCommonFieldsEntity entity = JsonUtil.getJsonToBean(comFieldsUpForm, BaseCommonFieldsEntity.class);
		if (commonFieldsService.isExistByFullName(entity.getFieldName(), id)) {
			return R.failed("名称不能重复");
		}
		boolean flag = commonFieldsService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok(null, "更新成功");
	}

	@ApiOperation("删除常用字段")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		BaseCommonFieldsEntity entity = commonFieldsService.getInfo(id);
		if (entity != null) {
			commonFieldsService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败,数据不存在");
	}
}

