package com.sinosoft.ie.booster.admin.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinosoft.ie.booster.admin.api.entity.SysRegionEntity;
import com.sinosoft.ie.booster.admin.api.model.RegionVO;
import com.sinosoft.ie.booster.admin.service.SysRegionService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.node.INode;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 行政区划表 控制器
 *
 * @author haocy
 */
@RestController
@AllArgsConstructor
@RequestMapping("region")
@Api(value = "行政区划表", tags = "行政区划表接口")
public class RegionController {

	private final SysRegionService regionService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入region")
	public R<RegionVO> detail(SysRegionEntity region) {
		return R.ok(regionService.entityVO(region));
	}

	/**
	 * 分页 行政区划表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入region")
	public R<IPage<SysRegionEntity>> list(Page<SysRegionEntity> page, SysRegionEntity region) {
		IPage<SysRegionEntity> pages = regionService.page(page, Wrappers.lambdaQuery(region).orderByAsc(SysRegionEntity::getCode));
		return R.ok(pages);
	}

	/**
	 * 懒加载列表
	 */
	@GetMapping("/lazy-list")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "区划编号", paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "name", value = "区划名称", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "懒加载列表", notes = "传入menu")
	public R<List<INode>> lazyList(String parentCode, @ApiIgnore @RequestParam Map<String, Object> menu) {
		List<INode> list = regionService.lazyList(parentCode, menu);
		return R.ok(list);
	}

	/**
	 * 懒加载列表
	 */
	@GetMapping("/lazy-tree")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "区划编号", paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "name", value = "区划名称", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "懒加载列表", notes = "传入menu")
	public R<List<INode>> lazyTree(String parentCode, @ApiIgnore @RequestParam Map<String, Object> menu) {
		List<INode> list = regionService.lazyTree(parentCode, menu);
		return R.ok(list);
	}

	/**
	 * 新增 行政区划表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入region")
	public R<Boolean> save(@Valid @RequestBody SysRegionEntity region) {
		return R.ok(regionService.save(region));
	}

	/**
	 * 修改 行政区划表
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入region")
	public R<Boolean> update(@Valid @RequestBody SysRegionEntity region) {
		return R.ok(regionService.updateById(region));
	}

	/**
	 * 新增或修改 行政区划表
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入region")
	public R<Boolean> submit(@Valid @RequestBody SysRegionEntity region) {
		return R.ok(regionService.submit(region));
	}


	/**
	 * 删除 行政区划表
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入主键")
	public R<Boolean> remove(@ApiParam(value = "主键", required = true) @RequestParam String id) {
		return R.ok(regionService.removeRegion(id));
	}

	/**
	 * 行政区划下拉数据源
	 */
	@GetMapping("/select")
	@ApiOperation(value = "下拉数据源", notes = "传入tenant")
	public R<List<SysRegionEntity>> select(@RequestParam(required = false, defaultValue = "00") String code) {
		List<SysRegionEntity> list = regionService.list(Wrappers.<SysRegionEntity>query().lambda().eq(SysRegionEntity::getParentCode, code));
		return R.ok(list);
	}

	/**
	 * 返回行政区划树
	 */
	@GetMapping(value = "/tree")
	public R<List<Tree<String>>> listDeptTrees() {
		return R.ok(regionService.listRegionTrees());
	}

	/**
	 * 按Id列表获取行政区划数据
	 */
	@GetMapping("/listByIds")
	@ApiOperation(value = "按Id列表获取行政区划数据")
	public R<List<SysRegionEntity>> listByIds(@RequestParam() Collection<? extends Serializable> idList) {
		List<SysRegionEntity> list = regionService.listByIds(idList);
		return R.ok(list);
	}
}
