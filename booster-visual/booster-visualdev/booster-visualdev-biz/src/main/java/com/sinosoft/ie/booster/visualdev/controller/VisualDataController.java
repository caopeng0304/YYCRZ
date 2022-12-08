package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDataEntity;
import com.sinosoft.ie.booster.visualdev.model.datascreen.*;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDataService;
import com.sinosoft.ie.booster.visualdev.util.FileUtil;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.visualdev.util.ConfigValueUtil;
import com.sinosoft.ie.booster.visualdev.constant.enums.DicTypeEnum;
import com.sinosoft.ie.booster.visualdev.util.datascreen.VisualImageEnum;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import com.sinosoft.ie.booster.common.core.util.treeutil.TreeDotUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 大屏数据
 *
 * @author booster开发平台组
 * @since 2021年9月26日 上午9:18
 */
@Api(tags = "大屏数据")
@RestController
@RequestMapping("/DataScreen")
public class VisualDataController {

	@Autowired
	private ConfigValueUtil configValueUtil;
	@Autowired
	private BaseVisualDataService visualDataService;
	@Autowired
	private RemoteDictService dictionaryDataService;

	/**
	 * 获取大屏列表(分页)
	 *
	 * @param pagination 分页
	 * @return
	 */
	@ApiOperation("获取大屏列表")
	@GetMapping
	public R<PageListVO<DataListVO>> list(PaginationData pagination) {
		List<BaseVisualDataEntity> data = visualDataService.getList(pagination);
		List<DataListVO> list = JsonUtil.getJsonToList(data, DataListVO.class);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(pagination, PaginationVO.class);
		PageListVO<DataListVO> vo = new PageListVO<>();
		vo.setList(list);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 大屏下拉框列表
	 *
	 * @return
	 */
	@ApiOperation("大屏下拉框列表")
	@GetMapping("/Selector")
	public R<ListVO<DataSelectorVO>> selector() {
		List<BaseVisualDataEntity> list = visualDataService.getList().stream().filter(t -> "1".equals(String.valueOf(t.getEnabledFlag()))).collect(Collectors.toList());
		List<DataTreeModel> treeModel = new ArrayList<>();
		R<List<SysDictItemEntity>> result = dictionaryDataService.getDictByType(DicTypeEnum.VISUADEV_SCREEN_DESIGN_CATEGORY.getType());
		for (SysDictItemEntity dataEntity : result.getData()) {
			DataTreeModel model = new DataTreeModel();
			model.setId(dataEntity.getValue());
			model.setParentId("-1");
			model.setFullName(dataEntity.getLabel());
			treeModel.add(model);
		}

		for (BaseVisualDataEntity children : list) {
			DataTreeModel chilModel = new DataTreeModel();
			chilModel.setParentId(children.getCategoryId());
			chilModel.setId(String.valueOf(children.getId()));
			chilModel.setFullName(children.getFullName());
			treeModel.add(chilModel);
		}
		List<SumTree<DataTreeModel>> tree = TreeDotUtils.convertListToTreeDot(treeModel);
		List<DataSelectorVO> listVos = JsonUtil.getJsonToList(tree, DataSelectorVO.class);
		ListVO<DataSelectorVO> vo = new ListVO<>();
		vo.setList(listVos);
		return R.ok(vo);
	}

	/**
	 * 获取大屏基本信息
	 *
	 * @param id 主键
	 * @return
	 */
	@ApiOperation("大屏信息")
	@GetMapping("/{id}")
	public R<DataInfoVO> info(@PathVariable("id") Long id) throws DataException {
		BaseVisualDataEntity entity = visualDataService.getInfo(id);
		DataInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, DataInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新增大屏
	 *
	 * @param dataCrForm 实体对象
	 * @return
	 */
	@ApiOperation("新增大屏")
	@PostMapping
	public R<DataCrVO> create(@RequestBody @Valid DataCrForm dataCrForm) {
		BaseVisualDataEntity basic = JsonUtil.getJsonToBean(dataCrForm, BaseVisualDataEntity.class);
		basic.setCopyId(0L);
		basic.setEnabledFlag("1");
		visualDataService.create(basic);
		DataCrVO vo = new DataCrVO();
		vo.setId(String.valueOf(basic.getId()));
		return R.ok(vo);
	}

	/**
	 * 修改大屏
	 *
	 * @param id          主键
	 * @param basicUpForm 实体对象
	 * @return
	 */
	@ApiOperation("修改大屏")
	@PutMapping("/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid DataUpForm basicUpForm) {
		BaseVisualDataEntity entity = JsonUtil.getJsonToBean(basicUpForm, BaseVisualDataEntity.class);
		boolean flag = visualDataService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok(null, "更新成功");
	}

	/**
	 * 删除大屏
	 *
	 * @param id 主键
	 * @return
	 */
	@ApiOperation("删除大屏")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		BaseVisualDataEntity entity = visualDataService.getInfo(id);
		if (entity != null) {
			visualDataService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	/**
	 * 拷贝大屏数据
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("拷贝大屏数据")
	@PostMapping("/{id}/Actions/Copy")
	public R<Boolean> copy(@PathVariable("id") Long id) {
		BaseVisualDataEntity basic = visualDataService.getInfo(id);
		if (basic != null) {
			List<BaseVisualDataEntity> list = visualDataService.getList().stream().filter(t -> t.getCopyId().equals(basic.getId())).collect(Collectors.toList());
			String filePath = configValueUtil.getBiVisualPath() + File.separator + VisualImageEnum.SCREENSHOT.getMessage() + File.separator;
			String fileType = FileUtil.getFileType(basic.getScreenShot());
			String fileNewName = RandomUtil.uuId() + "." + fileType;
			FileUtil.copyFile(filePath + basic.getScreenShot(), filePath + fileNewName);
			basic.setScreenShot(fileNewName);
			int num = list.size() + 1;
			basic.setFullName(basic.getFullName() + "_副本" + num);
			basic.setCopyId(basic.getId());
			basic.setEnabledFlag("0");
			basic.setId(null);
			visualDataService.create(basic);
			return R.ok(null, "拷贝成功");
		}
		return R.failed("拷贝失败");
	}

	/**
	 * 更新大屏状态
	 *
	 * @param id 主键
	 * @return
	 */
	@ApiOperation("更新大屏状态")
	@PutMapping("/{id}/Actions/State")
	public R<Boolean> state(@PathVariable("id") Long id) {
		BaseVisualDataEntity entity = visualDataService.getInfo(id);
		if (entity != null) {
			entity.setEnabledFlag("1".equals(String.valueOf(entity.getEnabledFlag())) ? "0" : "1");
			visualDataService.update(id, entity);
			return R.ok(null, "更新大屏成功");
		}
		return R.failed("更新失败，数据不存在");
	}

}

