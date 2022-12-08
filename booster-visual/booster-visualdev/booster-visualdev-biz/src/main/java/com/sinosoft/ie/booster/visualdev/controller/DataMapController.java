package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDataMapEntity;
import com.sinosoft.ie.booster.visualdev.model.map.*;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDataMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * 数据地图
 *
 * @author booster开发平台组
 * @since 2021年9月26日 上午9:18
 */
@Api(tags = "数据地图", value = "Map")
@RestController
@RequestMapping("/Base/DataMap")
public class DataMapController {

	@Autowired
	private BaseVisualDataMapService visualDataMapService;

	/**
	 * 地图列表
	 *
	 * @param pagination 分页
	 * @return
	 */
	@ApiOperation("地图列表")
	@GetMapping
	public R<PageListVO<MapListVO>> list(Pagination pagination) {
		List<BaseVisualDataMapEntity> data = visualDataMapService.getList(pagination);
		List<MapListVO> list = JsonUtil.getJsonToList(data, MapListVO.class);
		PaginationVO pageModel = JsonUtil.getJsonToBean(pagination, PaginationVO.class);
		PageListVO<MapListVO> vo = new PageListVO<>();
		vo.setList(list);
		vo.setPagination(pageModel);
		return R.ok(vo);
	}

	/**
	 * 地图下拉框列表
	 *
	 * @param
	 * @return
	 */
	@ApiOperation("地图下拉框列表")
	@GetMapping("/Selector")
	public R<ListVO<MapSelectorVO>> selector() {
		List<BaseVisualDataMapEntity> list = visualDataMapService.getList();
		List<MapSelectorVO> listVOS = JsonUtil.getJsonToList(list, MapSelectorVO.class);
		ListVO<MapSelectorVO> vo = new ListVO<>();
		vo.setList(listVOS);
		return R.ok(vo);
	}

	/**
	 * 地图信息
	 *
	 * @param id 主键
	 * @return
	 */
	@ApiOperation("地图信息")
	@GetMapping("/{id}")
	public R<MapInfoVO> info(@PathVariable("id") Long id) throws DataException {
		BaseVisualDataMapEntity entity = visualDataMapService.getInfo(id);
		MapInfoVO vo = JsonUtil.getJsonToBeanEx(entity, MapInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 获取地图数据
	 *
	 * @param id 主键
	 * @return
	 */
	@ApiOperation("获取地图数据")
	@GetMapping("/{id}/Data")
	public Map<String, Object> data(@PathVariable("id") Long id) throws DataException {
		BaseVisualDataMapEntity entity = visualDataMapService.getInfo(id);
		MapInfoVO mapVO = JsonUtil.getJsonToBeanEx(entity, MapInfoVO.class);
		return JsonUtil.stringToMap(mapVO.getData());
	}

	/**
	 * 更新地图状态
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("更新地图状态")
	@PutMapping("/{id}/Actions/State")
	public R<Boolean> state(@PathVariable("id") Long id) {
		BaseVisualDataMapEntity entity = visualDataMapService.getInfo(id);
		if (entity != null) {
			entity.setEnabledFlag("1".equals(String.valueOf(entity.getEnabledFlag())) ? "0" : "1");
			visualDataMapService.update(id, entity);
			return R.ok(null, "更新地图成功");
		}
		return R.failed("更新失败，数据不存在");
	}

	/**
	 * 新增
	 *
	 * @param mapCrForm 实体对象
	 * @return
	 */
	@ApiOperation("新增地图")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid MapCrForm mapCrForm) {
		BaseVisualDataMapEntity entity = JsonUtil.getJsonToBean(mapCrForm, BaseVisualDataMapEntity.class);
		if (visualDataMapService.isExistByFullName(entity.getFullName(), entity.getId())) {
			return R.failed("名称不能重复");
		}
		visualDataMapService.create(entity);
		return R.ok(null, "新建成功");
	}

	/**
	 * 修改
	 *
	 * @param id        主键
	 * @param mapUpForm 实体对象
	 * @return
	 */
	@ApiOperation("修改地图")
	@PutMapping("/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid MapUpForm mapUpForm) {
		BaseVisualDataMapEntity entity = JsonUtil.getJsonToBean(mapUpForm, BaseVisualDataMapEntity.class);
		if (visualDataMapService.isExistByFullName(entity.getFullName(), id)) {
			return R.failed("名称不能重复");
		}
		boolean flag = visualDataMapService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok(null, "更新成功");
	}

	/**
	 * 删除
	 *
	 * @param id 主键
	 * @return
	 */
	@ApiOperation("删除地图")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		BaseVisualDataMapEntity entity = visualDataMapService.getInfo(id);
		if (entity != null) {
			visualDataMapService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}
}

