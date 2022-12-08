package com.sinosoft.ie.booster.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysOauthClientDetailsEntity;
import com.sinosoft.ie.booster.admin.mapper.SysOauthClientDetailsMapper;
import com.sinosoft.ie.booster.admin.service.SysOauthClientDetailsService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetailsEntity>
		implements SysOauthClientDetailsService {

	/**
	 * 通过ID删除客户端
	 * @param id
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#id")
	public Boolean removeClientDetailsById(String id) {
		return this.removeById(id);
	}

	/**
	 * 根据客户端信息
	 * @param clientDetails
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
	public Boolean updateClientDetailsById(SysOauthClientDetailsEntity clientDetails) {
		return this.updateById(clientDetails);
	}

}
