package com.sinosoft.ie.booster.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.admin.api.model.UserExcelVO;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysUserService extends IService<SysUserEntity> {

	/**
	 * 查询用户信息
	 *
	 * @param sysUser 用户
	 * @return userInfo
	 */
	UserInfoModel getUserInfo(SysUserEntity sysUser);

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	IPage<UserVO> getUserWithRolePage(Page<UserVO> page, UserDTO userDTO);

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return boolean
	 */
	Boolean removeUserById(SysUserEntity sysUser);

	/**
	 * 更新当前用户基本信息
	 *
	 * @param userDto 用户信息
	 * @return Boolean 操作成功返回true,操作失败返回false
	 */
	Boolean updatePersonalInfo(UserDTO userDto);

	/**
	 * 更新指定用户信息
	 *
	 * @param userDto 用户信息
	 * @return
	 */
	Boolean updateUser(UserDTO userDto);

	/**
	 * 更新指定用户锁定标志
	 *
	 * @param username 用户名
	 * @param lockFlag 锁定标志
	 * @return 成功或失败状态
	 */
	Boolean updateUserLockFlag(String username, String lockFlag);

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	UserVO getUserVoById(Long id);

	/**
	 * 通过用户名查询用户信息（含有角色信息）
	 *
	 * @param username 用户名
	 * @return userVo
	 */
	UserVO getUserVoByUsername(String username);

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	List<UserVO> listAncestorUsersByUsername(String username);

	/**
	 * 保存用户信息
	 *
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveUser(UserDTO userDto);

	/**
	 * 查询全部的用户
	 *
	 * @param userDTO 查询条件
	 * @return list
	 */
	List<UserExcelVO> listUser(UserDTO userDTO);

	/**
	 * excel 导入用户
	 *
	 * @param excelVOList   excel 列表数据
	 * @param bindingResult 错误数据
	 * @return ok fail
	 */
	R<List<ErrorMessage>> importUser(List<UserExcelVO> excelVOList, BindingResult bindingResult);

	/**
	 * 获取按部门分组的用户下拉框列表
	 *
	 * @return
	 */
	List<Tree<String>> selector();

	/**
	 * 重置用户密码
	 *
	 * @param username 用户名（删缓存使用）
	 * @return 重置后的明文密码
	 */
	String resetUserPassword(String username);

	/**
	 * 查询用户列表
	 *
	 * @param userDTO 查询条件
	 * @return
	 */
	List<UserVO> selectVoList(UserDTO userDTO);
}
