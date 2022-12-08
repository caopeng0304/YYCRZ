package com.sinosoft.ie.booster.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.admin.api.entity.SysLogPasswordEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.api.model.*;
import com.sinosoft.ie.booster.admin.service.SysLogPasswordService;
import com.sinosoft.ie.booster.admin.service.SysUserService;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.PasswordOperationTypeEnum;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.log.annotation.SysLog;
import com.sinosoft.ie.booster.common.security.annotation.Inner;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理模块")
public class UserController {

	private final SysUserService userService;
	private final SysLogPasswordService sysLogPasswordService;
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	public R<UserInfoModel> info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUserEntity user = userService.getOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUsername, username));
		if (user == null) {
			return R.failed("获取当前用户信息失败");
		}
		// 防止泄露密码
		user.setPassword("[PROTECTED]");
		return R.ok(userService.getUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public R<UserInfoModel> info(@PathVariable String username) {
		SysUserEntity user = userService.getOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUsername, username));
		if (user == null) {
			return R.failed(String.format("用户信息为空 %s", username));
		}
		return R.ok(userService.getUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public R<UserVO> user(@PathVariable Long id) {
		return R.ok(userService.getUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	public R<UserVO> user(@PathVariable String username) {
		return R.ok(userService.getUserVoByUsername(username));
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	public R<Boolean> userDel(@PathVariable Long id) {
		SysUserEntity sysUser = userService.getById(id);
		return R.ok(userService.removeUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public R<Boolean> user(@RequestBody UserDTO userDto) {
		return R.ok(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R<Boolean> updateUser(@Valid @RequestBody UserDTO userDto) {
		return R.ok(userService.updateUser(userDto));
	}

	/**
	 * 锁定用户
	 *
	 * @param username 用户名
	 * @return R
	 */
	@SysLog("锁定用户")
	@Inner
	@PutMapping("/lockUser/{username}")
	public R<Boolean> lockUser(@PathVariable String username) {
		return R.ok(userService.updateUserLockFlag(username, CommonConstants.STATUS_LOCK));
	}

	/**
	 * 重置用户密码
	 *
	 * @param username 用户名
	 * @return R
	 */
	@SysLog("重置用户密码")
	@PutMapping("/resetUserPassword/{username}")
	public R<String> resetUserPassword(@PathVariable String username) {
		return R.ok(userService.resetUserPassword(username));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public R<IPage<UserVO>> getUserPage(Page<UserVO> page, UserDTO userDTO) {
		return R.ok(userService.getUserWithRolePage(page, userDTO));
	}

	/**
	 * 查询所有用户信息
	 *
	 * @return 用户集合
	 */
	@GetMapping("/getAll")
	public R<List<UserVO>> getAllUser() {
		UserDTO userDTO = new UserDTO();
		return R.ok(userService.selectVoList(userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param personalInfoUpForm 个人信息更新表单
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/updatePersonalInfo")
	public R<Boolean> updatePersonalInfo(@Valid @RequestBody PersonalInfoUpForm personalInfoUpForm) {
		UserDTO userDto = BeanUtil.copyProperties(personalInfoUpForm, UserDTO.class);
		//防止恶意修改别人的信息
		userDto.setUsername(SecurityUtils.getUser().getUsername());
		return R.ok(userService.updatePersonalInfo(userDto));
	}

	/**
	 * 修改个人密码
	 *
	 * @param personalPasswordUpForm 个人密码更新表单
	 * @return success/false
	 */
	@SysLog("修改个人密码")
	@PutMapping("/updatePersonalPassword")
	public R<Boolean> updatePersonalPassword(@Valid @RequestBody PersonalPasswordUpForm personalPasswordUpForm) {
		UserDTO userDto = BeanUtil.copyProperties(personalPasswordUpForm, UserDTO.class);
		//防止恶意修改别人的密码
		userDto.setUsername(SecurityUtils.getUser().getUsername());
		// 校验更换的密码不能和以往用过的密码相同
		boolean matchResult = sysLogPasswordService.list(
				Wrappers.<SysLogPasswordEntity>query().lambda()
						.eq(SysLogPasswordEntity::getOperationType, PasswordOperationTypeEnum.USER.getType())
						.eq(SysLogPasswordEntity::getUsername, userDto.getUsername())
		).stream().anyMatch(r -> ENCODER.matches(userDto.getNewpassword1(), r.getPassword()));
		if (matchResult) {
			return R.failed("更换的密码不能和以往用过的密码相同");
		}
		return R.ok(userService.updatePersonalInfo(userDto));
	}

	/**
	 * @param username 用户名称
	 * @return 上级部门用户列表
	 */
	@GetMapping("/ancestor/{username}")
	public R<List<UserVO>> listAncestorUsers(@PathVariable String username) {
		return R.ok(userService.listAncestorUsersByUsername(username));
	}

	/**
	 * 导出excel 表格
	 *
	 * @param userDTO 查询条件
	 * @return
	 */
	@ResponseExcel
	@GetMapping("/export")
	@PreAuthorize("@pms.hasPermission('sys_user_import_export')")
	public List<UserExcelVO> export(UserDTO userDTO) {
		return userService.listUser(userDTO);
	}

	/**
	 * 导入用户
	 *
	 * @param excelVOList   用户列表
	 * @param bindingResult 错误信息列表
	 * @return R
	 */
	@PostMapping("/import")
	@PreAuthorize("@pms.hasPermission('sys_user_import_export')")
	public R<List<ErrorMessage>> importUser(@RequestExcel List<UserExcelVO> excelVOList, BindingResult bindingResult) {
		return userService.importUser(excelVOList, bindingResult);
	}

	@GetMapping("/selector")
	public R<List<Tree<String>>> selector() {
		return R.ok(userService.selector());
	}

	/**
	 * 根据多个ID查询用户信息
	 *
	 * @param ids 逗号分割的Id
	 * @return 用户信息列表
	 */
	@GetMapping("/getUserByIds")
	public R<List<SysUserEntity>> getUserByIds(String ids) {
		List<SysUserEntity> userEntityList = new ArrayList<>();
		if (StrUtil.isNotEmpty(ids)) {
			userEntityList = userService.listByIds(Arrays.asList(ids.split(",")));
		}
		return R.ok(userEntityList);
	}
}
