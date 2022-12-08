package com.sinosoft.ie.booster.common.core.constant;

/**
 * @author lengleng
 * @since 2019/2/1
 */
public interface SecurityConstants {

	/**
	 * 角色前缀
	 */
	String ROLE = "ROLE_";

	/**
	 * 前缀
	 */
	String PROJECT_PREFIX = "booster_";

	/**
	 * 项目的license
	 */
	String PROJECT_LICENSE = "made by booster";

	/**
	 * 内部
	 */
	String FROM_IN = "Y";

	/**
	 * 标志
	 */
	String FROM = "from";

	/**
	 * 默认登录URL
	 */
	String OAUTH_TOKEN_URL = "/oauth/token";

	/**
	 * grant_type
	 */
	String REFRESH_TOKEN = "refresh_token";

	/**
	 * {bcrypt} 加密的特征码
	 */
	String BCRYPT = "{bcrypt}";

	/**
	 * 手机号登录URL
	 */
	String SMS_TOKEN_URL = "/mobile/token/sms";

	/**
	 * 社交登录URL
	 */
	String SOCIAL_TOKEN_URL = "/mobile/token/social";

	/**
	 * 自定义登录URL
	 */
	String MOBILE_TOKEN_URL = "/mobile/token/*";

	/**
	 * 微信获取OPENID
	 */
	String WX_AUTHORIZATION_CODE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token" +
			"?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

	/**
	 * 码云获取token
	 */
	String GITEE_AUTHORIZATION_CODE_URL = "https://gitee.com/oauth/token?grant_type=" +
			"authorization_code&code=%S&client_id=%s&redirect_uri=" +
			"%s&client_secret=%s";

	/**
	 * 开源中国获取token
	 */
	String OSC_AUTHORIZATION_CODE_URL = "https://www.oschina.net/action/openapi/token";

	/**
	 * 码云获取用户信息
	 */
	String GITEE_USER_INFO_URL = "https://gitee.com/api/v5/user?access_token=%s";

	/**
	 * 开源中国用户信息
	 */
	String OSC_USER_INFO_URL = "https://www.oschina.net/action/openapi/user?access_token=%s&dataType=json";

	/**
	 * sys_oauth_client_details 表的字段，不包括client_id、client_secret
	 */
	String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
			+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
			+ "refresh_token_validity, additional_information, autoapprove";

	/**
	 * JdbcClientDetailsService 查询语句
	 */
	String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

	/**
	 * 默认的查询语句
	 */
	String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

	/**
	 * 按条件client_id 查询
	 */
	String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

	/***
	 * 资源服务器默认bean名称
	 */
	String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

	/**
	 * 用户ID字段
	 */
	String DETAILS_USER_ID = "user_id";

	/**
	 * 用户名字段
	 */
	String DETAILS_USERNAME = "username";

	/**
	 * 用户部门字段
	 */
	String DETAILS_DEPT_ID = "dept_id";

	/**
	 * 协议字段
	 */
	String DETAILS_LICENSE = "license";

	/**
	 * 验证码有效期,默认 60秒
	 */
	long CODE_TIME = 60;

	/**
	 * 验证码长度
	 */
	String CODE_SIZE = "4";

	/**
	 * 用户锁定信息有效期
	 */
	long USER_LOCK_TIME = 3600;

	/**
	 * 允许最大的密码错误次数
	 */
	long ALLOW_MAX_PASSWORD_ERROR_COUNT = 5;
}
