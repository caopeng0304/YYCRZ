package com.sinosoft.ie.booster.admin.api.feign.fallback;

import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author haocy
 * @since 2021-08-18
 */
@Slf4j
@Component
public class RemoteDeptServiceFallbackImpl implements RemoteDeptService {

	@Setter
	private Throwable cause;

	/**
	 * 通过部门Id查询部门信息
	 *
	 * @param id 部门Id
	 * @return R
	 */
	@Override
	public R<SysDeptEntity> info(Long id) {
		log.error("feign 查询部门信息失败:{}", id, cause);
		return null;
	}

	@Override
	public R<SysDeptEntity> infoByName(String deptName) {
		log.error("feign 查询部门信息失败:{}", deptName, cause);
		return null;
	}

	@Override
	public R<List<SysDeptEntity>> getList() {
		log.error("feign 查询部门列表失败", cause);
		return null;
	}

}
