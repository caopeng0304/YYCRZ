package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseDataInterfaceEntity;
import com.sinosoft.ie.booster.visualdev.model.datainterface.*;
import com.sinosoft.ie.booster.visualdev.service.BaseDataInterfaceService;
import com.sinosoft.ie.booster.visualdev.constant.enums.DicTypeEnum;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import com.sinosoft.ie.booster.common.core.util.treeutil.TreeDotUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 数据接口
 *
 * @author booster开发平台组
 * @since 2021-03-15 10:29
 */
@Api(tags = "数据接口", value = "DataInterface")
@RestController
@RequestMapping(value = "/Base/DataInterface")
public class DataInterfaceController {
	@Autowired
	private BaseDataInterfaceService dataInterfaceService;
	@Autowired
	private RemoteDictService dictionaryDataService;

	/**
	 * 获取接口列表(分页)
	 *
	 * @param pagination
	 * @return
	 */
	@ApiOperation("获取接口列表(分页)")
	@GetMapping
	public R<PageListVO<DataInterfaceListVO>> getList(PaginationDataInterface pagination) {
		List<BaseDataInterfaceEntity> data = dataInterfaceService.getList(pagination);
		List<DataInterfaceListVO> list = JsonUtil.getJsonToList(data, DataInterfaceListVO.class);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(pagination, PaginationVO.class);
		PageListVO<DataInterfaceListVO> vo = new PageListVO<>();
		vo.setList(list);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 获取接口列表下拉框
	 *
	 * @return
	 */
	@ApiOperation("获取接口列表下拉框")
	@GetMapping("/Selector")
	public R<List<DataInterfaceTreeVO>> getSelector() {
		List<DataInterfaceTreeModel> tree = new ArrayList<>();
		List<BaseDataInterfaceEntity> data = dataInterfaceService.getList();
		R<List<SysDictItemEntity>> result = dictionaryDataService.getDictByType(DicTypeEnum.DATA_INTERFACE_CATEGORY.getType());
		for (SysDictItemEntity entity : result.getData()) {
			DataInterfaceTreeModel firstModel = new DataInterfaceTreeModel();
			firstModel.setId(entity.getValue());
			firstModel.setFullName(entity.getLabel());
			firstModel.setCategoryId("0");
			firstModel.setParentId("0");
			tree.add(firstModel);
		}
		for (BaseDataInterfaceEntity entity : data) {
			DataInterfaceTreeModel treeModel = JsonUtil.getJsonToBean(entity, DataInterfaceTreeModel.class);
			treeModel.setCategoryId("1");
			treeModel.setParentId(entity.getCategoryId());
			tree.add(treeModel);
		}
		tree = tree.stream().distinct().collect(Collectors.toList());
		List<SumTree<DataInterfaceTreeModel>> sumTrees = TreeDotUtils.convertListToTreeDot(tree);
		List<DataInterfaceTreeVO> list = JsonUtil.getJsonToList(sumTrees, DataInterfaceTreeVO.class);
		return R.ok(list);
	}

	/**
	 * 获取接口数据
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("获取接口数据")
	@GetMapping("/{id}")
	public R<DataInterfaceVo> getInfo(@PathVariable("id") Long id) throws DataException {
		BaseDataInterfaceEntity entity = dataInterfaceService.getInfo(id);
		DataInterfaceVo vo = JsonUtil.getJsonToBeanEx(entity, DataInterfaceVo.class);
		return R.ok(vo);
	}

	/**
	 * 添加接口
	 *
	 * @param dataInterfaceCrForm
	 * @return
	 */
	@ApiOperation("添加接口")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid DataInterfaceCrForm dataInterfaceCrForm) throws DataException {
		BaseDataInterfaceEntity entity = JsonUtil.getJsonToBean(dataInterfaceCrForm, BaseDataInterfaceEntity.class);
		if (dataInterfaceService.isExistByFullName(entity.getFullName(), entity.getId())) {
			return R.failed("名称不能重复");
		}
		dataInterfaceService.create(entity);
		return R.ok(null, "接口创建成功");
	}

	/**
	 * 修改接口
	 *
	 * @param dataInterfaceUpForm
	 * @param id
	 * @return
	 */
	@ApiOperation("修改接口")
	@PutMapping("/{id}")
	public R<Boolean> update(@RequestBody @Valid DataInterfaceUpForm dataInterfaceUpForm, @PathVariable("id") Long id) throws DataException {
		BaseDataInterfaceEntity entity = JsonUtil.getJsonToBeanEx(dataInterfaceUpForm, BaseDataInterfaceEntity.class);
		if (dataInterfaceService.isExistByFullName(entity.getFullName(), id)) {
			return R.failed("名称不能重复");
		}
		boolean flag = dataInterfaceService.update(entity, id);
		if (!flag) {
			return R.failed("接口修改失败，数据不存在");
		}
		return R.ok(null, "接口修改成功");
	}

	/**
	 * 删除接口
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("删除接口")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable Long id) {
		BaseDataInterfaceEntity entity = dataInterfaceService.getInfo(id);
		if (entity != null) {
			dataInterfaceService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	/**
	 * 更新接口状态
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("更新接口状态")
	@PutMapping("/{id}/Actions/State")
	public R<Boolean> update(@PathVariable("id") Long id) throws DataException {
		BaseDataInterfaceEntity entity = dataInterfaceService.getInfo(id);
		if (entity != null) {
			if (Objects.equals(entity.getEnabledFlag(), "0")) {
				entity.setEnabledFlag("1");
			} else {
				entity.setEnabledFlag("0");
			}
			dataInterfaceService.update(entity, id);
			return R.ok(null, "更新接口状态成功");
		}
		return R.failed("更新接口状态失败，数据不存在");
	}

	/**
	 * 访问接口
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("访问接口")
	@GetMapping("/{id}/Actions/Response")
	public R infoToId(@PathVariable("id") Long id) {
		return dataInterfaceService.infoToId(id);
	}

}
