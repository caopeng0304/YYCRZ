package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfoModel> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfoModel> social(@PathVariable("inStr") String inStr, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@GetMapping("/user/ancestor/{username}")
	R<List<SysUserEntity>> ancestorUsers(@PathVariable("username") String username);

	/**
	 * 查询所有用户信息
	 *
	 * @return R
	 */
	@GetMapping("/user/getAll")
	R<List<UserVO>> getAll();

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/user/details/{username}")
	R<UserVO> getUserByUserName(@PathVariable("username") String username);

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/user/{id}")
	R<UserVO> getUserById(@PathVariable("id") Long id);

	/**
	 * 根据多个ID查询用户信息
	 *
	 * @param ids 逗号分割的Id
	 * @return 用户信息列表
	 */
	@GetMapping("/user/getUserByIds")
	R<List<SysUserEntity>> getUserByIds(@RequestParam("ids") String ids);

	/**
	 * 锁定用户
	 *
	 * @param username 用户名
	 * @return R
	 */
	@PutMapping("/user/lockUser/{username}")
	R<Boolean> lockUser(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

	@PostMapping("/user/synUser")
	R<Boolean> synUser(@RequestBody UserDTO userDto);

	@PostMapping("/user/syndeleteUser")
	R<Boolean> syndeleteUser(@RequestBody UserDTO userDto);

	@PostMapping("/user/getNoPowerUser")
	R<List<SysUserEntity>> getNoPowerUser(@RequestBody UserDTO userDto);
}
