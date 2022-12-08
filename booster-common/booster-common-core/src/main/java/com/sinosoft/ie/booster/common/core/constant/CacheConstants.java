package com.sinosoft.ie.booster.common.core.constant;

/**
 * @author lengleng
 * @since 2020年01月01日
 * <p>
 * 缓存的key 常量
 */
public interface CacheConstants {

	/**
	 * 缓存默认有效期
	 */
	long CACHE_DEFAULT_TIME = 3600;

	/**
	 * oauth 缓存前缀
	 */
	String PROJECT_OAUTH_ACCESS = "booster_oauth:access:";

	/**
	 * oauth 缓存令牌前缀
	 */
	String PROJECT_OAUTH_TOKEN = "booster_oauth:token:";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY:";

	/**
	 * 菜单信息缓存
	 */
	String MENU_DETAILS = "menu_details";

	/**
	 * 用户信息缓存
	 */
	String USER_DETAILS = "user_details";

	/**
	 * 字典信息缓存
	 */
	String DICT_DETAILS = "dict_details";

	/**
	 * oauth 客户端信息
	 */
	String CLIENT_DETAILS_KEY = "booster_oauth:client:details";

	/**
	 * 参数缓存
	 */
	String PARAMS_DETAILS = "params_details";

	/**
	 * 用户锁定信息缓存
	 */
	String USER_LOCK_DETAILS = "user_lock_details:";
}
