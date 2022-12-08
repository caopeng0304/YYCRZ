package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

	@Setter
	private Throwable cause;

	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     内外标志
	 * @return R
	 */
	@Override
	public R<UserInfoModel> info(String username, String from) {
		log.error("feign 查询用户信息失败:{}", username, cause);
		return null;
	}

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@Override
	public R<UserInfoModel> social(String inStr, String from) {
		log.error("feign 查询用户信息失败:{}", inStr, cause);
		return null;
	}

	@Override
	public R<List<SysUserEntity>> ancestorUsers(String username) {
		log.error("feign 查询上级部门的用户信息失败:{}", username, cause);
		return null;
	}

	@Override
	public R<List<UserVO>> getAll() {
		log.error("feign 查询所有用户信息失败", cause);
		return null;
	}

	@Override
	public R<UserVO> getUserByUserName(String username) {
		log.error("feign 根据用户名查询用户信息失败", cause);
		return null;
	}

	@Override
	public R<UserVO> getUserById(Long id) {
		log.error("feign 通过ID查询用户信息失败", cause);
		return null;
	}

	@Override
	public R<List<SysUserEntity>> getUserByIds(String ids) {
		log.error("feign 根据多个ID查询用户信息失败", cause);
		return null;
	}

	@Override
	public R<Boolean> lockUser(String username, String from) {
		log.error("feign 锁定用户失败:{}", username, cause);
		return null;
	}

	@Override
	public R<Boolean> synUser(UserDTO userDto) {
		log.error("feign 新增用户失败:{}", userDto, cause);
		return null;
	}

	@Override
	public R<Boolean> syndeleteUser(UserDTO userDto) {
		log.error("feign 删除用户失败:{}", userDto, cause);
		return null;
	}

	@Override
	public R<List<SysUserEntity>> getNoPowerUser(UserDTO userDto) {
		log.error("feign 查询用户失败:{}", userDto, cause);
		return null;
	}
}
