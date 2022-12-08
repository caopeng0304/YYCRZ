package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.admin.api.entity.*;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.admin.api.model.UserExcelVO;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.admin.mapper.SysDeptMapper;
import com.sinosoft.ie.booster.admin.mapper.SysRoleMapper;
import com.sinosoft.ie.booster.admin.mapper.SysUserMapper;
import com.sinosoft.ie.booster.admin.service.*;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.PasswordOperationTypeEnum;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.util.PasswordGenUtil;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	private final SysMenuService sysMenuService;

	private final SysRoleService sysRoleService;

	private final SysPositionService sysPositionService;

	private final SysDeptService sysDeptService;

	private final SysUserRoleService sysUserRoleService;

	private final SysUserPositionService sysUserPositionService;

	private final SysRoleMapper sysRoleMapper;

	private final SysDeptMapper sysDeptMapper;

	private final SysLogPasswordService sysLogPasswordService;

	/**
	 * 保存用户信息
	 *
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(UserDTO userDto) {
		SysUserEntity sysUser = new SysUserEntity();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
		//新建用户的密码为手机号
		sysUser.setPassword(ENCODER.encode(userDto.getPhone()));
		baseMapper.insert(sysUser);
		//添加角色
		saveBatchUserRoleEntity(sysUser.getUserId(), userDto.getRole());
		//添加岗位
		List<Long> position = userDto.getPosition();
		if (CollUtil.isNotEmpty(position)) {
			saveBatchUserPositionEntity(sysUser.getUserId(), position);
		}
		return Boolean.TRUE;
	}

	/**
	 * 通过查用户的全部信息
	 *
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserInfoModel getUserInfo(SysUserEntity sysUser) {
		UserInfoModel userInfo = new UserInfoModel();
		userInfo.setSysUser(sysUser);
		userInfo.setSysDept(sysDeptService.getById(sysUser.getDeptId()));
		// 设置角色列表 （ID）
		List<SysRoleEntity> roleEntityList = sysRoleService.findRolesByUserId(sysUser.getUserId());
		List<Long> roleIds = roleEntityList.stream().map(SysRoleEntity::getRoleId)
				.collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));
		// 设置岗位列表 （ID）
		List<SysPositionEntity> positionEntityList = sysPositionService.getListByUserName(sysUser.getUsername());
		List<Long> positionIds = positionEntityList.stream().map(SysPositionEntity::getId)
				.collect(Collectors.toList());
		userInfo.setPositions(ArrayUtil.toArray(positionIds, Long.class));
		// 设置密码更新周期
		Integer minPasswordUpdateCycle = roleEntityList.stream()
				.map(SysRoleEntity::getPasswordUpdateCycle)
				.min(Comparator.comparing(Integer::intValue))
				.orElse(365);
		userInfo.setPasswordUpdateCycle(minPasswordUpdateCycle);
		// 设置权限列表（menu.permission）
		Set<String> permissions = new HashSet<>();
		roleIds.forEach(roleId -> {
			List<String> permissionList = sysMenuService.findMenuByRoleId(roleId).stream()
					.map(SysMenuEntity::getPermission).filter(StrUtil::isNotEmpty)
					.collect(Collectors.toList());
			permissions.addAll(permissionList);
		});
		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
		return userInfo;
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	@Override
	public IPage<UserVO> getUserWithRolePage(Page<UserVO> page, UserDTO userDTO) {
		return baseMapper.getUserVosPage(page, userDTO);
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO getUserVoById(Long id) {
		return baseMapper.getUserVoById(id);
	}

	/**
	 * 通过用户名查询用户信息（含有角色信息）
	 *
	 * @param username 用户名
	 * @return userVo
	 */
	@Override
	public UserVO getUserVoByUsername(String username) {
		return baseMapper.getUserVoByUsername(username);
	}

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return Boolean
	 */
	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#sysUser.username")
	public Boolean removeUserById(SysUserEntity sysUser) {
		sysUserRoleService.removeRoleByUserId(sysUser.getUserId());
		this.removeById(sysUser.getUserId());
		return Boolean.TRUE;
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
	@Transactional(rollbackFor = Exception.class)
	public Boolean updatePersonalInfo(UserDTO userDto) {
		SysUserEntity userEntity = getOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUsername, userDto.getUsername()));
		SysUserEntity sysUser = new SysUserEntity();
		if (StrUtil.isNotBlank(userDto.getPassword())
				&& StrUtil.isNotBlank(userDto.getNewpassword1())) {
			Assert.isTrue(ENCODER.matches(userDto.getPassword(), userEntity.getPassword()), "原密码错误，修改失败");
			String encodePassword = ENCODER.encode(userDto.getNewpassword1());
			sysUser.setPassword(encodePassword);
			//设置上次密码更新时间并记录密码操作日志
			sysUser.setLastPasswordUpdated(LocalDateTime.now());
			SysLogPasswordEntity logPasswordEntity = new SysLogPasswordEntity();
			logPasswordEntity.setOperationType(PasswordOperationTypeEnum.USER.getType());
			logPasswordEntity.setUsername(userDto.getUsername());
			logPasswordEntity.setPassword(encodePassword);
			sysLogPasswordService.save(logPasswordEntity);
		}
		sysUser.setPhone(userDto.getPhone());
		sysUser.setUserId(userEntity.getUserId());
		sysUser.setAvatar(userDto.getAvatar());
		return this.updateById(sysUser);
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateUser(UserDTO userDto) {
		SysUserEntity sysUser = new SysUserEntity();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());
		this.updateById(sysUser);
		//更新角色
		sysUserRoleService
				.remove(Wrappers.<SysUserRoleEntity>lambdaQuery().eq(SysUserRoleEntity::getUserId, userDto.getUserId()));
		saveBatchUserRoleEntity(sysUser.getUserId(), userDto.getRole());
		//更新岗位
		sysUserPositionService
				.remove(Wrappers.<SysUserPositionEntity>lambdaQuery().eq(SysUserPositionEntity::getUserId, userDto.getUserId()));
		List<Long> position = userDto.getPosition();
		if (CollUtil.isNotEmpty(position)) {
			saveBatchUserPositionEntity(sysUser.getUserId(), position);
		}
		return Boolean.TRUE;
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#username")
	public Boolean updateUserLockFlag(String username, String lockFlag) {
		return this.update(
				Wrappers.<SysUserEntity>lambdaUpdate()
						.eq(SysUserEntity::getUsername, username)
						.set(SysUserEntity::getLockFlag, lockFlag)
		);
	}

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@Override
	public List<UserVO> listAncestorUsersByUsername(String username) {
		SysUserEntity sysUser = this.getOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUsername, username));

		SysDeptEntity sysDept = sysDeptService.getById(sysUser.getDeptId());
		if (sysDept == null) {
			return null;
		}

		Long parentId = sysDept.getParentId();
		return BeanUtil.copyToList(this.list(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getDeptId, parentId)), UserVO.class);
	}

	/**
	 * 查询全部的用户
	 *
	 * @param userDTO 查询条件
	 * @return list
	 */
	@Override
	public List<UserExcelVO> listUser(UserDTO userDTO) {
		List<UserVO> voList = baseMapper.selectVoList(userDTO);
		// 转换成execl 对象输出
		return voList.stream().map(userVO -> {
			UserExcelVO excelVO = new UserExcelVO();
			BeanUtils.copyProperties(userVO, excelVO);
			excelVO.setUserId(String.valueOf(userVO.getUserId()));
			String roleNameList = userVO.getRoleList().stream().map(SysRoleEntity::getRoleName)
					.collect(Collectors.joining(StrUtil.COMMA));
			excelVO.setRoleNameList(roleNameList);
			return excelVO;
		}).collect(Collectors.toList());
	}

	/**
	 * excel 导入用户, 插入正确的 错误的提示行号
	 *
	 * @param excelVOList   excel 列表数据
	 * @param bindingResult 错误数据
	 * @return ok fail
	 */
	@Override
	public R<List<ErrorMessage>> importUser(List<UserExcelVO> excelVOList, BindingResult bindingResult) {
		// 通用校验获取失败的数据
		List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();

		// 个性化校验逻辑
		List<SysUserEntity> userList = this.list();
		List<SysDeptEntity> deptList = sysDeptMapper.selectList(Wrappers.emptyWrapper());
		List<SysRoleEntity> roleList = sysRoleMapper.selectList(Wrappers.emptyWrapper());

		// 执行数据插入操作 组装 UserDto
		for (int i = 0; i < excelVOList.size(); i++) {
			UserExcelVO excel = excelVOList.get(i);
			Set<String> errorMsg = new HashSet<>();
			Optional<SysDeptEntity> deptOptional = Optional.empty();
			List<SysRoleEntity> roleCollList = new ArrayList<>();
			// 校验用户名不能为空
			if (StrUtil.isEmpty(excel.getUsername())) {
				errorMsg.add("用户名不能为空");
			} else {
				// 校验用户名是否存在
				boolean existUserName = userList.stream()
						.anyMatch(sysUser -> excel.getUsername().equals(sysUser.getUsername()));
				if (existUserName) {
					errorMsg.add(String.format("%s 用户名已存在", excel.getUsername()));
				}
			}
			// 校验手机号不能为空
			if (StrUtil.isEmpty(excel.getPhone())) {
				errorMsg.add("手机号不能为空");
			}
			// 校验部门名称不能为空
			if (StrUtil.isEmpty(excel.getDeptName())) {
				errorMsg.add("部门名称不能为空");
			} else {
				// 判断输入的部门名称列表是否合法
				deptOptional = deptList.stream()
						.filter(dept -> excel.getDeptName().equals(dept.getName())).findFirst();
				if (!deptOptional.isPresent()) {
					errorMsg.add(String.format("%s 部门名称不存在", excel.getDeptName()));
				}
			}
			// 校验上级主管不能为空
			if (StrUtil.isEmpty(excel.getManager())) {
				errorMsg.add("上级主管不能为空");
			} else {
				// 校验上级主管是否存在
				boolean existManager = userList.stream()
						.anyMatch(sysUser -> excel.getManager().equals(sysUser.getUsername()));
				if (!existManager) {
					errorMsg.add(String.format("%s 上级主管不存在", excel.getUsername()));
				}
			}
			// 校验角色不能为空
			if (StrUtil.isEmpty(excel.getRoleNameList())) {
				errorMsg.add("角色不能为空");
			} else {
				// 判断输入的角色名称列表是否合法
				List<String> roleNameList = StrUtil.split(excel.getRoleNameList(), StrUtil.COMMA);
				roleCollList = roleList.stream()
						.filter(role -> roleNameList.stream().anyMatch(name -> role.getRoleName().equals(name)))
						.collect(Collectors.toList());
				if (roleCollList.size() != roleNameList.size()) {
					errorMsg.add(String.format("%s 角色名称不存在", excel.getRoleNameList()));
				}
			}
			// 校验锁定标记不能为空
			if (StrUtil.isEmpty(excel.getLockFlag())) {
				errorMsg.add("锁定标记不能为空");
			} else {
				// 判断输入的锁定标记是否合法
				if (!CommonConstants.STATUS_NORMAL.equals(excel.getLockFlag())
						&& !CommonConstants.STATUS_LOCK.equals(excel.getLockFlag())) {
					errorMsg.add(String.format("%s 锁定标记不合法", excel.getRoleNameList()));
				}
			}

			// 数据合法情况
			if (CollUtil.isEmpty(errorMsg)) {
				insertExcelUser(excel, deptOptional, roleCollList);
			} else {
				// 数据不合法情况
				assert errorMessageList != null;
				errorMessageList.add(new ErrorMessage((long) (i + 2), errorMsg));
			}
		}

		if (CollUtil.isNotEmpty(errorMessageList)) {
			return R.failed(errorMessageList);
		}
		return R.ok();
	}

	@Override
	public List<Tree<String>> selector() {
		List<TreeNode<String>> deptTreeNodeList = sysDeptService.list().stream()
				.filter(dept -> dept.getDeptId().intValue() != dept.getParentId())
				.sorted(Comparator.comparingInt(SysDeptEntity::getSort)).map(dept -> {
					TreeNode<String> treeNode = new TreeNode<>();
					treeNode.setId("department_" + dept.getDeptId());
					treeNode.setParentId("department_" + dept.getParentId());
					treeNode.setName(dept.getName());
					treeNode.setWeight(dept.getSort());
					// 扩展属性
					Map<String, Object> extra = new HashMap<>(4);
					extra.put("type", "department");
					extra.put("icon", "icon-ym icon-ym-tree-department1");
					treeNode.setExtra(extra);
					return treeNode;
				}).collect(Collectors.toList());
		List<TreeNode<String>> userTreeNodeList = this.list().stream()
				.map(user -> {
					TreeNode<String> treeNode = new TreeNode<>();
					treeNode.setId("user_" + user.getUserId());
					treeNode.setParentId("department_" + user.getDeptId());
					treeNode.setName(user.getUsername());
					// 扩展属性
					Map<String, Object> extra = new HashMap<>(4);
					extra.put("type", "user");
					extra.put("icon", "icon-ym icon-ym-tree-user2");
					treeNode.setExtra(extra);
					return treeNode;
				}).collect(Collectors.toList());
		deptTreeNodeList.addAll(userTreeNodeList);
		return TreeUtil.build(deptTreeNodeList, "department_0");
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#username")
	@Transactional(rollbackFor = Exception.class)
	public String resetUserPassword(String username) {
		String defaultPassword = PasswordGenUtil.gen(12);
		String encodePassword = ENCODER.encode(defaultPassword);
		this.update(
				Wrappers.<SysUserEntity>lambdaUpdate()
						.eq(SysUserEntity::getUsername, username)
						.set(SysUserEntity::getPassword, encodePassword)
						.set(SysUserEntity::getLastPasswordUpdated, null)
						.set(SysUserEntity::getUpdateTime, LocalDateTime.now())
						.set(SysUserEntity::getUpdateBy, SecurityUtils.getUser().getUsername())
		);
		SysLogPasswordEntity logPasswordEntity = new SysLogPasswordEntity();
		logPasswordEntity.setOperationType(PasswordOperationTypeEnum.SYSTEM.getType());
		logPasswordEntity.setUsername(username);
		logPasswordEntity.setPassword(encodePassword);
		sysLogPasswordService.save(logPasswordEntity);
		return defaultPassword;
	}

	@Override
	public List<UserVO> selectVoList(UserDTO userDTO) {
		return baseMapper.selectVoList(userDTO);
	}

	/**
	 * 插入excel User
	 */
	private void insertExcelUser(UserExcelVO excel, Optional<SysDeptEntity> deptOptional, List<SysRoleEntity> roleCollList) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(excel.getUsername());
		userDTO.setManager(excel.getManager());
		userDTO.setPhone(excel.getPhone());
		userDTO.setLockFlag(excel.getLockFlag());
		// 根据部门名称查询部门ID
		userDTO.setDeptId(deptOptional.get().getDeptId());
		// 根据角色名称查询角色ID
		List<Long> roleIdList = roleCollList.stream().map(SysRoleEntity::getRoleId).collect(Collectors.toList());
		userDTO.setRole(roleIdList);
		// 插入用户
		this.saveUser(userDTO);
	}

	private Boolean saveBatchUserRoleEntity(Long userId, List<Long> role) {
		List<SysUserRoleEntity> userRoleList = role.stream().map(roleId -> {
			SysUserRoleEntity userRole = new SysUserRoleEntity();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			return userRole;
		}).collect(Collectors.toList());
		return sysUserRoleService.saveBatch(userRoleList);
	}

	private Boolean saveBatchUserPositionEntity(Long userId, List<Long> position) {
		List<SysUserPositionEntity> userPositionList = position.stream().map(positionId -> {
			SysUserPositionEntity userPosition = new SysUserPositionEntity();
			userPosition.setUserId(userId);
			userPosition.setPositionId(positionId);
			return userPosition;
		}).collect(Collectors.toList());
		return sysUserPositionService.saveBatch(userPositionList);
	}

}
