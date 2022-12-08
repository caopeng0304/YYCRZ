package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BaseDbLinkEntity;
import com.sinosoft.ie.booster.visualdev.model.dblink.*;
import com.sinosoft.ie.booster.visualdev.service.BaseDbLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 数据连接
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "数据连接", value = "DataSource")
@RestController
@RequestMapping("/Base/DataSource")
public class DataSourceController {

	@Autowired
	private BaseDbLinkService dbLinkService;

	/**
	 * 列表
	 *
	 * @return
	 */
	@GetMapping("/Selector")
	@ApiOperation("获取数据连接下拉框列表")
	public R<ListVO<DbLinkSelectorListVO>> selectorList() {
		List<BaseDbLinkEntity> list = dbLinkService.getList();
		List<DbLinkSelectorListVO> result = JsonUtil.getJsonToList(list, DbLinkSelectorListVO.class);
		ListVO<DbLinkSelectorListVO> vo = new ListVO<>();
		vo.setList(result);
		return R.ok(vo);
	}

	@GetMapping("/DbLinkType/Selector")
	@ApiOperation("获取连接驱动下拉框列表")
	public R<List<String>> selectorDbLinkTypeList() {
		List<String> dbLinkTypeList = DataSourceEnum.getDbLinkTypeList();
		return R.ok(dbLinkTypeList);
	}

	/**
	 * 列表
	 *
	 * @param page 关键字
	 * @return
	 */
	@GetMapping
	@ApiOperation("获取数据连接列表")
	public R<ListVO<DbLinkListVO>> getList(Pagination page) {
		List<BaseDbLinkEntity> list = dbLinkService.getList(page.getKeyword());
		List<DbLinkListVO> result = JsonUtil.getJsonToList(list, DbLinkListVO.class);
		ListVO<DbLinkListVO> vo = new ListVO<>();
		vo.setList(result);
		return R.ok(vo);
	}

	/**
	 * 信息
	 *
	 * @param id 主键
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation("获取数据连接")
	public R<DbLinkInfoVO> get(@PathVariable("id") Long id) throws DataException {
		BaseDbLinkEntity entity = dbLinkService.getInfo(id);
		DbLinkInfoVO vo = JsonUtil.getJsonToBeanEx(entity, DbLinkInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建
	 *
	 * @param dbLinkCrForm dto实体
	 * @return
	 */
	@PostMapping
	@ApiOperation("添加数据连接")
	public R<Boolean> create(@RequestBody @Valid DbLinkCrForm dbLinkCrForm) {
		BaseDbLinkEntity entity = JsonUtil.getJsonToBean(dbLinkCrForm, BaseDbLinkEntity.class);
		if (dbLinkService.isExistByFullName(entity.getFullName(), entity.getId())) {
			return R.failed("名称不能重复");
		}
		dbLinkService.create(entity);
		return R.ok(null, "创建成功");
	}

	/**
	 * 更新
	 *
	 * @param id           主键
	 * @param dbLinkUpForm dto实体
	 * @return
	 */
	@PutMapping("/{id}")
	@ApiOperation("修改数据连接")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid DbLinkUpForm dbLinkUpForm) {
		BaseDbLinkEntity entity = JsonUtil.getJsonToBean(dbLinkUpForm, BaseDbLinkEntity.class);
		if (dbLinkService.isExistByFullName(entity.getFullName(), id)) {
			return R.failed("名称不能重复");
		}
		boolean flag = dbLinkService.update(id, entity);
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
	@DeleteMapping("/{id}")
	@ApiOperation("删除数据连接")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		BaseDbLinkEntity entity = dbLinkService.getInfo(id);
		if (entity != null) {
			dbLinkService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	@PostMapping("/Actions/Test")
	@ApiOperation("测试连接")
	public R<Boolean> test(@RequestBody DbLinkTestForm dbLinkTestForm) {
		BaseDbLinkEntity entity = JsonUtil.getJsonToBean(dbLinkTestForm, BaseDbLinkEntity.class);
		boolean data = dbLinkService.testDbConnection(entity);
		if (data) {
			return R.ok(null, "连接成功");
		} else {
			return R.failed("连接失败");
		}
	}
}
