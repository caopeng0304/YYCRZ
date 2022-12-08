package com.sinosoft.ie.booster.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinosoft.ie.booster.admin.api.entity.SysDictEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.service.SysDictItemService;
import com.sinosoft.ie.booster.admin.service.SysDictService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019-03-19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", tags = "字典管理模块")
public class DictController {

	private final SysDictItemService sysDictItemService;

	private final SysDictService sysDictService;

	/**
	 * 通过ID查询字典信息
	 *
	 * @param id ID
	 * @return 字典信息
	 */
	@GetMapping("/{id}")
	public R<SysDictEntity> getById(@PathVariable Long id) {
		return R.ok(sysDictService.getById(id));
	}

	/**
	 * 分页查询字典信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage<SysDictEntity>> getDictPage(Page<SysDictEntity> page, SysDictEntity sysDict) {
		return R.ok(sysDictService.page(page, Wrappers.lambdaQuery(sysDict).orderByAsc(SysDictEntity::getId)));
	}

	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	@Cacheable(value = CacheConstants.DICT_DETAILS, key = "#type")
	public R<List<SysDictItemEntity>> getDictByType(@PathVariable String type) {
		return R.ok(sysDictItemService.list(Wrappers.<SysDictItemEntity>query().lambda()
				.eq(SysDictItemEntity::getType, type)
				.and(t -> t.eq(SysDictItemEntity::getParentId, 0L))
		));
	}

	/**
	 * 根据父Id查找指定类型下爸爸的孩子
	 *
	 * @param type     类型
	 * @param parentId 父Id
	 * @return 同类型字典
	 */
	@GetMapping("/children/{type}")
	public R<List<SysDictItemEntity>> getChildrenDictByType(@PathVariable String type, Long parentId) {
		return R.ok(sysDictItemService.listChildren(type, parentId));
	}

	/**
	 * 根据父Id查找指定类型下爸爸的兄弟
	 *
	 * @param type     类型
	 * @param parentId 父Id
	 * @return 同类型字典
	 */
	@GetMapping("/brother/{type}")
	public R<List<SysDictItemEntity>> getBrotherDictByType(@PathVariable String type, Long parentId) {
		SysDictItemEntity father = sysDictItemService.getById(parentId);
		if (father != null) {
			return R.ok(sysDictItemService.list(Wrappers.<SysDictItemEntity>query().lambda()
					.eq(SysDictItemEntity::getType, type)
					.and(t -> t.eq(SysDictItemEntity::getParentId, father.getParentId()))
			));
		}
		return R.ok();
	}

	/**
	 * 添加字典
	 *
	 * @param sysDict 字典信息
	 * @return success、false
	 */
	@SysLog("添加字典")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dict_add')")
	public R<Boolean> save(@Valid @RequestBody SysDictEntity sysDict) {
		return R.ok(sysDictService.save(sysDict));
	}

	/**
	 * 删除字典，并且清除字典缓存
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除字典")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dict_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		sysDictService.removeDict(id);
		return R.ok(Boolean.TRUE);
	}

	/**
	 * 修改字典
	 *
	 * @param sysDict 字典信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改字典")
	@PreAuthorize("@pms.hasPermission('sys_dict_edit')")
	public R<Boolean> updateById(@Valid @RequestBody SysDictEntity sysDict) {
		sysDictService.updateDict(sysDict);
		return R.ok(Boolean.TRUE);
	}

	/**
	 * 分页查询
	 *
	 * @param page        分页对象
	 * @param sysDictItem 字典项
	 * @return
	 */
	@GetMapping("/item/page")
	public R<IPage<SysDictItemEntity>> getSysDictItemPage(Page<SysDictItemEntity> page, SysDictItemEntity sysDictItem) {
		return R.ok(sysDictItemService.treePage(page, sysDictItem));
	}

	/**
	 * 通过id查询字典项
	 *
	 * @param id id
	 * @return R
	 */
	@GetMapping("/item/{id}")
	public R<SysDictItemEntity> getDictItemById(@PathVariable("id") Long id) {
		return R.ok(sysDictItemService.getById(id));
	}

	/**
	 * 新增字典项
	 *
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("新增字典项")
	@PostMapping("/item")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R<Boolean> save(@RequestBody SysDictItemEntity sysDictItem) {
		return R.ok(sysDictItemService.save(sysDictItem));
	}

	/**
	 * 修改字典项
	 *
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("修改字典项")
	@PutMapping("/item")
	public R<Boolean> updateById(@RequestBody SysDictItemEntity sysDictItem) {
		sysDictItemService.updateDictItem(sysDictItem);
		return R.ok(Boolean.TRUE);
	}

	/**
	 * 通过id删除字典项
	 *
	 * @param id id
	 * @return R
	 */
	@SysLog("删除字典项")
	@DeleteMapping("/item/{id}")
	public R<Boolean> removeDictItemById(@PathVariable Long id) {
		sysDictItemService.removeDictItem(id);
		return R.ok(Boolean.TRUE);
	}
}
