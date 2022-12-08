package com.sinosoft.ie.booster.admin.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysUserPositionEntity;
import com.sinosoft.ie.booster.admin.api.model.position.*;
import com.sinosoft.ie.booster.admin.service.SysDeptService;
import com.sinosoft.ie.booster.admin.service.SysPositionService;
import com.sinosoft.ie.booster.admin.service.SysUserPositionService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import com.sinosoft.ie.booster.common.core.util.treeutil.TreeDotUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * sys_position
 *
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-14 15:10:15
 */
@Slf4j
@RestController
@Api(tags = "sys_position")
@RequestMapping("/position")
public class PositionController {
	@Autowired
	private SysPositionService positionService;
	@Autowired
	private SysUserPositionService userPositionService;
	@Autowired
	private SysDeptService deptService;

	/**
	 * 获取岗位管理信息列表
	 *
	 * @param positionPagination
	 * @return
	 */
	@ApiOperation("获取岗位列表（分页）")
	@GetMapping
	public R<PageListVO<PositionListVO>> list(PositionPagination positionPagination) {
		List<SysDeptEntity> list = deptService.list(Wrappers.<SysDeptEntity>lambdaQuery().eq(SysDeptEntity::getDelFlag, 0));
		List<PositionListVO> voList = new ArrayList<>();
		if (positionPagination.getDeptId() != null) {
			List<TreeNode<Long>> collect = list.stream().map(deptEntity -> {
				TreeNode<Long> treeNode = new TreeNode<>();
				treeNode.setId(deptEntity.getDeptId());
				treeNode.setParentId(deptEntity.getParentId());
				treeNode.setName(deptEntity.getName());
				return treeNode;
			}).collect(Collectors.toList());
			Tree<Long> tree = TreeUtil.buildSingle(collect, Long.valueOf(positionPagination.getDeptId()));
			List<String> deptIds = new ArrayList<>();
			tree.walk(r -> deptIds.add(String.valueOf(r.getId())));
			positionPagination.setDeptId(String.join(",", deptIds));
			List<SysPositionEntity> data = positionService.getList(positionPagination);
			voList = JsonUtil.getJsonToList(data, PositionListVO.class);
			//添加部门信息，部门映射到organizeId
			//添加部门信息
			for (PositionListVO entity1 : voList) {
				SysDeptEntity entity = list.stream().filter(t -> String.valueOf(t.getDeptId()).equals(entity1.getDepartment())).findFirst().orElse(null);
				if (entity != null) {
					entity1.setDepartment(entity.getName());
				}
			}
		}
		PaginationVO paginationVO = JsonUtil.getJsonToBean(positionPagination, PaginationVO.class);
		PageListVO<PositionListVO> vo = new PageListVO<>();
		vo.setList(voList);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 列表
	 *
	 * @return
	 */
	@ApiOperation("列表")
	@GetMapping("/All")
	public R<ListVO<PositionListAllVO>> listAll() {
		List<SysPositionEntity> list = positionService.getList().stream().filter(
				t -> "1".equals(String.valueOf(t.getEnabledFlag()))
		).collect(Collectors.toList());
		List<PositionListAllVO> allVos = JsonUtil.getJsonToList(list, PositionListAllVO.class);
		ListVO<PositionListAllVO> vo = new ListVO<>();
		vo.setList(allVos);
		return R.ok(vo);
	}

	/**
	 * 列表
	 *
	 * @return
	 */
	@ApiOperation("列表")
	@GetMapping("/getListAll")
	public R<List<SysPositionEntity>> getListAll() {
		List<SysPositionEntity> list = positionService.getList();
		return R.ok(list);
	}

	/**
	 * 树形（机构+岗位）
	 *
	 * @return
	 */
	@ApiOperation("获取岗位下拉列表（公司+部门+岗位）")
	@GetMapping("/Selector")
	public R<ListVO<PositionSelectorVO>> selector() {
		List<SysPositionEntity> positionData = positionService.getList().stream().filter(
				t -> "1".equals(String.valueOf(t.getEnabledFlag()))
		).collect(Collectors.toList());
		List<SysDeptEntity> allDeptData = deptService.list();
		List<PosDeptModel> posDeptModels = new ArrayList<>();
		for (SysDeptEntity sysDeptEntity : allDeptData) {
			PosDeptModel posDeptModel = new PosDeptModel();
			posDeptModel.setId(String.valueOf(sysDeptEntity.getDeptId()));
			posDeptModel.setParentId(String.valueOf(sysDeptEntity.getParentId()));
			posDeptModel.setType("department");
			posDeptModel.setFullName(sysDeptEntity.getName());
			posDeptModel.setEnabledFlag("1");
			posDeptModel.setIcon("icon-ym icon-ym-tree-department1");
			posDeptModels.add(posDeptModel);
		}
		for (SysPositionEntity entity : positionData) {
			PosDeptModel posOrgModel = JsonUtil.getJsonToBean(entity, PosDeptModel.class);
			posOrgModel.setParentId(String.valueOf(entity.getDeptId()));
			posOrgModel.setType("position");
			posOrgModel.setIcon("icon-ym icon-ym-tree-position1");
			posDeptModels.add(posOrgModel);
		}
		List<SumTree<PosDeptModel>> trees = TreeDotUtils.convertListToTreeDot(posDeptModels);
		List<PositionSelectorVO> listVO = JsonUtil.getJsonToList(trees, PositionSelectorVO.class);
		List<SysDeptEntity> entities = allDeptData.stream().filter(
				t -> t.getParentId() == 0
		).collect(Collectors.toList());
		Iterator<PositionSelectorVO> iterator = listVO.iterator();
		while (iterator.hasNext()) {
			PositionSelectorVO positionSelectorVO = iterator.next();
			for (SysDeptEntity entity : entities) {
				if (String.valueOf(entity.getDeptId()).equals(positionSelectorVO.getParentId())) {
					//使用迭代器的删除方法删除
					iterator.remove();
				}
			}
		}
		ListVO<PositionSelectorVO> vo = new ListVO<>();
		vo.setList(listVO);
		return R.ok(vo);
	}

	/**
	 * 获取岗位管理信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取岗位管理信息")
	@GetMapping("/{id}")
	public R<PositionInfoVO> getInfo(@PathVariable("id") String id) throws DataException {
		SysPositionEntity entity = positionService.getInfo(id);
		PositionInfoVO vo = JsonUtil.getJsonToBeanEx(entity, PositionInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建岗位管理
	 *
	 * @param positionCrForm 实体对象
	 * @return
	 */
	@ApiOperation("新建岗位管理")
	@PostMapping
	public R<String> create(@RequestBody @Valid PositionCrForm positionCrForm) {
		SysPositionEntity entity = JsonUtil.getJsonToBean(positionCrForm, SysPositionEntity.class);
		if (positionService.isExistByFullName(positionCrForm.getFullName(), String.valueOf(entity.getId()))) {
			return R.failed("岗位名称不能重复");
		}
		if (positionService.isExistByEnCode(positionCrForm.getEnCode(), String.valueOf(entity.getId()))) {
			return R.failed("岗位编码不能重复");
		}
		positionService.create(entity);
		return R.ok("新建成功");
	}

	/**
	 * 更新岗位管理
	 *
	 * @param id             主键值
	 * @param positionUpForm 实体对象
	 * @return
	 */
	@ApiOperation("更新岗位管理")
	@PutMapping("/{id}")
	public R<String> update(@PathVariable("id") String id, @RequestBody @Valid PositionUpForm positionUpForm) {
		SysPositionEntity entity = JsonUtil.getJsonToBean(positionUpForm, SysPositionEntity.class);
		if (positionService.isExistByFullName(positionUpForm.getFullName(), id)) {
			return R.failed("岗位名称不能重复");
		}
		if (positionService.isExistByEnCode(positionUpForm.getEnCode(), id)) {
			return R.failed("岗位编码不能重复");
		}
		boolean flag = positionService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok("更新成功");
	}

	/**
	 * 删除岗位管理
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("删除岗位管理")
	@DeleteMapping("/{id}")
	public R<String> delete(@PathVariable("id") String id) {
		SysPositionEntity entity = positionService.getInfo(id);
		if (entity != null) {
			positionService.delete(entity);
			return R.ok("删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	/**
	 * 更新菜单状态
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("更新菜单状态")
	@PutMapping("/{id}/Actions/State")
	public R<String> upState(@PathVariable("id") String id) {
		SysPositionEntity entity = positionService.getInfo(id);
		if (entity != null) {
			if (entity.getEnabledFlag() == null || "1".equals(entity.getEnabledFlag())) {
				entity.setEnabledFlag("0");
			} else {
				entity.setEnabledFlag("1");
			}
			positionService.update(id, entity);
			return R.ok("更新成功");
		}
		return R.failed("更新失败,数据不存在");
	}

	/**
	 * 获取岗位下的用户信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取岗位下的用户信息")
	@GetMapping("/userPosition/{id}")
	public R<UserPositionIdsVO> getUserPosition(@PathVariable("id") String id) {
		List<SysUserPositionEntity> userPositionEntityList = userPositionService.list(Wrappers.<SysUserPositionEntity>lambdaQuery().eq(SysUserPositionEntity::getPositionId, id));
		List<String> ids = userPositionEntityList.stream().map(t -> String.valueOf(t.getUserId())).collect(Collectors.toList());
		UserPositionIdsVO vo = new UserPositionIdsVO();
		vo.setUserIds(ids);
		return R.ok(vo);
	}

	/**
	 * 保存岗位下的用户信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("保存岗位下的用户信息")
	@PostMapping("/userPosition/{id}")
	public R<String> saveUserPosition(@PathVariable("id") String id, @RequestBody UserPositionForm userPositionForm) {
		//删除岗位下的用户
		userPositionService.remove(Wrappers.<SysUserPositionEntity>lambdaQuery().eq(SysUserPositionEntity::getPositionId, id));
		//新增岗位下的用户
		for (String userId : userPositionForm.getUserIds()) {
			SysUserPositionEntity userPositionEntity = new SysUserPositionEntity();
			userPositionEntity.setPositionId(Long.valueOf(id));
			userPositionEntity.setUserId(Long.valueOf(userId));
			userPositionService.save(userPositionEntity);
		}
		return R.ok("保存成功");
	}

	/**
	 * 获取多个岗位下的用户信息
	 *
	 * @param ids 逗号分割的岗位Id
	 * @return
	 */
	@ApiOperation("获取多个岗位下的用户信息")
	@GetMapping("/getUserPositionByIds")
	public R<UserPositionNamesVO> getUserPositionByIds(String ids) {
		List<String> userNames = new ArrayList<>();
		if (StrUtil.isNotEmpty(ids)) {
			userNames = positionService.getUserPositionByIds(ids);
		}
		UserPositionNamesVO vo = new UserPositionNamesVO();
		vo.setUserNames(userNames);
		return R.ok(vo);
	}

	/**
	 * 获取用户下的岗位列表
	 *
	 * @param userName 用户名
	 * @return
	 */
	@ApiOperation("获取用户下的岗位列表")
	@GetMapping("/getListByUserName")
	public R<List<SysPositionEntity>> getListByUserName(String userName) {
		List<SysPositionEntity> positionEntities = positionService.getListByUserName(userName);
		return R.ok(positionEntities);
	}

	/**
	 * 获取多个Id的岗位列表
	 *
	 * @param ids 逗号分割的岗位Id
	 * @return
	 */
	@ApiOperation("获取多个Id的岗位列表")
	@GetMapping("/getListByIds")
	public R<List<SysPositionEntity>> getListByIds(String ids) {
		List<SysPositionEntity> positionEntities = positionService.listByIds(Arrays.asList(ids.split(",")));
		return R.ok(positionEntities);
	}
}
