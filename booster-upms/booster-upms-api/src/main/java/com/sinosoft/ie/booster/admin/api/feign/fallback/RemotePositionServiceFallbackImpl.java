package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.admin.api.model.position.PositionInfoVO;
import com.sinosoft.ie.booster.admin.api.model.position.UserPositionNamesVO;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author haocy
 * @since 2022-09-16
 */
@Slf4j
@Component
public class RemotePositionServiceFallbackImpl implements RemotePositionService {

	@Setter
	private Throwable cause;

	/**
	 * 获取岗位管理信息
	 *
	 * @param id 岗位Id
	 * @return R
	 */
	@Override
	public R<PositionInfoVO> getInfo(String id) {
		log.error("feign 获取岗位管理信息:{}", id, cause);
		return null;
	}

	@Override
	public R<UserPositionNamesVO> getUserPositionByIds(String ids) {
		log.error("feign 获取多个岗位下的用户信息:{}", ids, cause);
		return null;
	}

	@Override
	public R<List<SysPositionEntity>> getListByUserName(String userName) {
		log.error("feign 获取用户下的岗位列表:{}", userName, cause);
		return null;
	}

	@Override
	public R<List<SysPositionEntity>> getListAll() {
		log.error("feign 获取所有岗位", cause);
		return null;
	}

	@Override
	public R<List<SysPositionEntity>> getListByIds(String ids) {
		log.error("feign 获取多个Id的岗位列表:{}", ids, cause);
		return null;
	}
}
