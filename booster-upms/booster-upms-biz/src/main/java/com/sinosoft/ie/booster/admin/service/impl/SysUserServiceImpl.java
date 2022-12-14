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
	 * ??????????????????
	 *
	 * @param userDto DTO ??????
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(UserDTO userDto) {
		SysUserEntity sysUser = new SysUserEntity();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
		//?????????????????????????????????
		sysUser.setPassword(ENCODER.encode(userDto.getPhone()));
		baseMapper.insert(sysUser);
		//????????????
		saveBatchUserRoleEntity(sysUser.getUserId(), userDto.getRole());
		//????????????
		List<Long> position = userDto.getPosition();
		if (CollUtil.isNotEmpty(position)) {
			saveBatchUserPositionEntity(sysUser.getUserId(), position);
		}
		return Boolean.TRUE;
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param sysUser ??????
	 * @return
	 */
	@Override
	public UserInfoModel getUserInfo(SysUserEntity sysUser) {
		UserInfoModel userInfo = new UserInfoModel();
		userInfo.setSysUser(sysUser);
		userInfo.setSysDept(sysDeptService.getById(sysUser.getDeptId()));
		// ?????????????????? ???ID???
		List<SysRoleEntity> roleEntityList = sysRoleService.findRolesByUserId(sysUser.getUserId());
		List<Long> roleIds = roleEntityList.stream().map(SysRoleEntity::getRoleId)
				.collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));
		// ?????????????????? ???ID???
		List<SysPositionEntity> positionEntityList = sysPositionService.getListByUserName(sysUser.getUsername());
		List<Long> positionIds = positionEntityList.stream().map(SysPositionEntity::getId)
				.collect(Collectors.toList());
		userInfo.setPositions(ArrayUtil.toArray(positionIds, Long.class));
		// ????????????????????????
		Integer minPasswordUpdateCycle = roleEntityList.stream()
				.map(SysRoleEntity::getPasswordUpdateCycle)
				.min(Comparator.comparing(Integer::intValue))
				.orElse(365);
		userInfo.setPasswordUpdateCycle(minPasswordUpdateCycle);
		// ?????????????????????menu.permission???
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
	 * ????????????????????????????????????????????????
	 *
	 * @param page    ????????????
	 * @param userDTO ????????????
	 * @return
	 */
	@Override
	public IPage<UserVO> getUserWithRolePage(Page<UserVO> page, UserDTO userDTO) {
		return baseMapper.getUserVosPage(page, userDTO);
	}

	/**
	 * ??????ID??????????????????
	 *
	 * @param id ??????ID
	 * @return ????????????
	 */
	@Override
	public UserVO getUserVoById(Long id) {
		return baseMapper.getUserVoById(id);
	}

	/**
	 * ?????????????????????????????????????????????????????????
	 *
	 * @param username ?????????
	 * @return userVo
	 */
	@Override
	public UserVO getUserVoByUsername(String username) {
		return baseMapper.getUserVoByUsername(username);
	}

	/**
	 * ????????????
	 *
	 * @param sysUser ??????
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
			Assert.isTrue(ENCODER.matches(userDto.getPassword(), userEntity.getPassword()), "??????????????????????????????");
			String encodePassword = ENCODER.encode(userDto.getNewpassword1());
			sysUser.setPassword(encodePassword);
			//?????????????????????????????????????????????????????????
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
		//????????????
		sysUserRoleService
				.remove(Wrappers.<SysUserRoleEntity>lambdaQuery().eq(SysUserRoleEntity::getUserId, userDto.getUserId()));
		saveBatchUserRoleEntity(sysUser.getUserId(), userDto.getRole());
		//????????????
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
	 * ?????????????????????????????????
	 *
	 * @param username ?????????
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
	 * ?????????????????????
	 *
	 * @param userDTO ????????????
	 * @return list
	 */
	@Override
	public List<UserExcelVO> listUser(UserDTO userDTO) {
		List<UserVO> voList = baseMapper.selectVoList(userDTO);
		// ?????????execl ????????????
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
	 * excel ????????????, ??????????????? ?????????????????????
	 *
	 * @param excelVOList   excel ????????????
	 * @param bindingResult ????????????
	 * @return ok fail
	 */
	@Override
	public R<List<ErrorMessage>> importUser(List<UserExcelVO> excelVOList, BindingResult bindingResult) {
		// ?????????????????????????????????
		List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();

		// ?????????????????????
		List<SysUserEntity> userList = this.list();
		List<SysDeptEntity> deptList = sysDeptMapper.selectList(Wrappers.emptyWrapper());
		List<SysRoleEntity> roleList = sysRoleMapper.selectList(Wrappers.emptyWrapper());

		// ???????????????????????? ?????? UserDto
		for (int i = 0; i < excelVOList.size(); i++) {
			UserExcelVO excel = excelVOList.get(i);
			Set<String> errorMsg = new HashSet<>();
			Optional<SysDeptEntity> deptOptional = Optional.empty();
			List<SysRoleEntity> roleCollList = new ArrayList<>();
			// ???????????????????????????
			if (StrUtil.isEmpty(excel.getUsername())) {
				errorMsg.add("?????????????????????");
			} else {
				// ???????????????????????????
				boolean existUserName = userList.stream()
						.anyMatch(sysUser -> excel.getUsername().equals(sysUser.getUsername()));
				if (existUserName) {
					errorMsg.add(String.format("%s ??????????????????", excel.getUsername()));
				}
			}
			// ???????????????????????????
			if (StrUtil.isEmpty(excel.getPhone())) {
				errorMsg.add("?????????????????????");
			}
			// ??????????????????????????????
			if (StrUtil.isEmpty(excel.getDeptName())) {
				errorMsg.add("????????????????????????");
			} else {
				// ?????????????????????????????????????????????
				deptOptional = deptList.stream()
						.filter(dept -> excel.getDeptName().equals(dept.getName())).findFirst();
				if (!deptOptional.isPresent()) {
					errorMsg.add(String.format("%s ?????????????????????", excel.getDeptName()));
				}
			}
			// ??????????????????????????????
			if (StrUtil.isEmpty(excel.getManager())) {
				errorMsg.add("????????????????????????");
			} else {
				// ??????????????????????????????
				boolean existManager = userList.stream()
						.anyMatch(sysUser -> excel.getManager().equals(sysUser.getUsername()));
				if (!existManager) {
					errorMsg.add(String.format("%s ?????????????????????", excel.getUsername()));
				}
			}
			// ????????????????????????
			if (StrUtil.isEmpty(excel.getRoleNameList())) {
				errorMsg.add("??????????????????");
			} else {
				// ?????????????????????????????????????????????
				List<String> roleNameList = StrUtil.split(excel.getRoleNameList(), StrUtil.COMMA);
				roleCollList = roleList.stream()
						.filter(role -> roleNameList.stream().anyMatch(name -> role.getRoleName().equals(name)))
						.collect(Collectors.toList());
				if (roleCollList.size() != roleNameList.size()) {
					errorMsg.add(String.format("%s ?????????????????????", excel.getRoleNameList()));
				}
			}
			// ??????????????????????????????
			if (StrUtil.isEmpty(excel.getLockFlag())) {
				errorMsg.add("????????????????????????");
			} else {
				// ???????????????????????????????????????
				if (!CommonConstants.STATUS_NORMAL.equals(excel.getLockFlag())
						&& !CommonConstants.STATUS_LOCK.equals(excel.getLockFlag())) {
					errorMsg.add(String.format("%s ?????????????????????", excel.getRoleNameList()));
				}
			}

			// ??????????????????
			if (CollUtil.isEmpty(errorMsg)) {
				insertExcelUser(excel, deptOptional, roleCollList);
			} else {
				// ?????????????????????
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
					// ????????????
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
					// ????????????
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
	 * ??????excel User
	 */
	private void insertExcelUser(UserExcelVO excel, Optional<SysDeptEntity> deptOptional, List<SysRoleEntity> roleCollList) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(excel.getUsername());
		userDTO.setManager(excel.getManager());
		userDTO.setPhone(excel.getPhone());
		userDTO.setLockFlag(excel.getLockFlag());
		// ??????????????????????????????ID
		userDTO.setDeptId(deptOptional.get().getDeptId());
		// ??????????????????????????????ID
		List<Long> roleIdList = roleCollList.stream().map(SysRoleEntity::getRoleId).collect(Collectors.toList());
		userDTO.setRole(roleIdList);
		// ????????????
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
