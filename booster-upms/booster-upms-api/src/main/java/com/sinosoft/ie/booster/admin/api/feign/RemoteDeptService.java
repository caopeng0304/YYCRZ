package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.feign.factory.RemoteDeptServiceFallbackFactory;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author haocy
 * @since 2021-08-18
 */
@FeignClient(contextId = "remoteDeptService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteDeptServiceFallbackFactory.class)
public interface RemoteDeptService {

	/**
	 * 通过部门Id查询部门信息
	 *
	 * @param id 部门Id
	 * @return R
	 */
	@GetMapping("/dept/{id}")
	R<SysDeptEntity> info(@PathVariable("id") Long id);

	/**
	 * 通过部门名称查询部门信息
	 *
	 * @param deptName 部门Id
	 * @return R
	 */
	@GetMapping("/dept/details/{deptName}")
	R<SysDeptEntity> infoByName(@PathVariable("deptName") String deptName);

	/**
	 * 返回部门列表
	 *
	 * @return 部门列表
	 */
	@GetMapping(value = "/dept/getList")
	R<List<SysDeptEntity>> getList();

}
