package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.PaginationModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VisualdevModelDataCrForm;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VisualdevModelDataInfoVO;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.VisualdevModelDataUpForm;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.visualdevmodelapp.AppDataInfoVO;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevService;
import com.sinosoft.ie.booster.visualdev.service.VisualDevModelAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 0代码app无表开发
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "0代码app无表开发")
@RestController
@RequestMapping("/OnlineDev/App")
public class VisualDevModelAppController {


	@Autowired
	private VisualDevModelAppService modelAppService;
	@Autowired
	private BaseVisualDevService visualDevService;

	@ApiOperation("获取数据列表")
	@GetMapping("/{modelId}/List")
	public R<PageListVO<Map<String, Object>>> list(@PathVariable("modelId") Long modelId, PaginationModel paginationModel) throws DataException, ParseException, SQLException, IOException {
		List<Map<String, Object>> realList = modelAppService.resultList(modelId, paginationModel);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationModel, PaginationVO.class);
		PageListVO<Map<String, Object>> vo = new PageListVO<>();
		vo.setList(realList);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	@ApiOperation("获取列表表单配置JSON")
	@GetMapping("/{modelId}/Config")
	public R<AppDataInfoVO> getData(@PathVariable("modelId") Long modelId) {
		BaseVisualDevEntity entity = visualDevService.getInfo(modelId);
		AppDataInfoVO vo = JsonUtil.getJsonToBean(entity, AppDataInfoVO.class);
		if (vo == null) {
			return R.failed("功能不存在");
		}
		return R.ok(vo);
	}

	@ApiOperation("添加数据")
	@PostMapping("/{modelId}")
	public R<Boolean> create(@PathVariable("modelId") Long modelId, @RequestBody VisualdevModelDataCrForm visualdevModelDataCrForm) throws DataException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		modelAppService.create(visualdevEntity, visualdevModelDataCrForm.getData());
		return R.ok(null, "新建成功");
	}

	@ApiOperation("修改数据")
	@PutMapping("/{modelId}/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @PathVariable("modelId") Long modelId, @RequestBody VisualdevModelDataUpForm visualdevModelDataUpForm) throws DataException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		boolean flag = modelAppService.update(id, visualdevEntity, visualdevModelDataUpForm.getData());
		if (flag) {
			return R.ok(null, "更新成功");
		}
		return R.failed("更新失败，数据不存在");
	}

	@ApiOperation("删除数据")
	@DeleteMapping("/{modelId}/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id, @PathVariable("modelId") Long modelId) throws DataException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		boolean result = modelAppService.delete(id, visualdevEntity);
		if (result) {
			return R.ok(null, "删除成功");
		} else {
			return R.failed("删除失败，数据不存在");
		}
	}

	@ApiOperation("获取数据信息")
	@GetMapping("/{modelId}/{id}")
	public R<VisualdevModelDataInfoVO> info(@PathVariable("modelId") Long modelId, @PathVariable("id") Long id) throws DataException, ParseException, SQLException, IOException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		Map<String, Object> info = modelAppService.info(id, visualdevEntity);
		VisualdevModelDataInfoVO vo = JsonUtil.getJsonToBean(info, VisualdevModelDataInfoVO.class);
		return R.ok(vo);
	}

}

