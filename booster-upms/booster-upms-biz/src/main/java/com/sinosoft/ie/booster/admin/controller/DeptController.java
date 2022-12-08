package com.sinosoft.ie.booster.admin.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.service.SysDeptService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
@Api(value = "dept", tags = "部门管理模块")
public class DeptController {

	private final SysDeptService sysDeptService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public R<SysDeptEntity> getById(@PathVariable Long id) {
		return R.ok(sysDeptService.getById(id));
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R<List<Tree<Long>>> listDeptTrees() {
		return R.ok(sysDeptService.listDeptTrees());
	}

	/**
	 * 返回当前用户树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/user-tree")
	public R<List<Tree<Long>>> listCurrentUserDeptTrees() {
		return R.ok(sysDeptService.listCurrentUserDeptTrees());
	}

	/**
	 * 添加
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("添加部门")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_add')")
	public R<Boolean> save(@Valid @RequestBody SysDeptEntity sysDept) {
		return R.ok(sysDeptService.saveDept(sysDept));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除部门")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dept_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		return R.ok(sysDeptService.removeDeptById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("编辑部门")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_edit')")
	public R<Boolean> update(@Valid @RequestBody SysDeptEntity sysDept) {
		sysDept.setUpdateTime(LocalDateTime.now());
		return R.ok(sysDeptService.updateDeptById(sysDept));
	}

	/**
	 * 根据部门名查询部门信息
	 *
	 * @param deptName 部门名
	 * @return
	 */
	@GetMapping("/details/{deptName}")
	public R<SysDeptEntity> getByName(@PathVariable String deptName) {
		SysDeptEntity condition = new SysDeptEntity();
		condition.setName(deptName);
		return R.ok(sysDeptService.getOne(new QueryWrapper<>(condition)));
	}

	/**
	 * 返回部门列表
	 *
	 * @return 部门列表
	 */
	@GetMapping(value = "/getList")
	public R<List<SysDeptEntity>> getList() {
		return R.ok(sysDeptService.list(Wrappers.emptyWrapper()));
	}
}
