package com.sinosoft.ie.booster.admin.api.feign;

import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.feign.factory.RemotePositionServiceFallbackFactory;
import com.sinosoft.ie.booster.admin.api.model.position.PositionInfoVO;
import com.sinosoft.ie.booster.admin.api.model.position.UserPositionNamesVO;
import com.sinosoft.ie.booster.common.core.constant.ServiceNameConstants;
import com.sinosoft.ie.booster.common.core.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author haocy
 * @since 2022-09-16
 */
@FeignClient(contextId = "remotePositionService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemotePositionServiceFallbackFactory.class)
public interface RemotePositionService {

	/**
	 * 获取岗位管理信息
	 *
	 * @param id 岗位Id
	 * @return R
	 */
	@GetMapping("/position/{id}")
	R<PositionInfoVO> getInfo(@PathVariable("id") String id);

	/**
	 * 获取岗位下的用户信息
	 *
	 * @param ids 逗号分割的岗位Id
	 * @return R
	 */
	@GetMapping("/position/getUserPositionByIds")
	R<UserPositionNamesVO> getUserPositionByIds(@RequestParam("ids") String ids);

	/**
	 * 获取用户下的岗位列表
	 *
	 * @param userName 用户名
	 * @return R
	 */
	@GetMapping("/position/getListByUserName")
	R<List<SysPositionEntity>> getListByUserName(@RequestParam("userName") String userName);

	/**
	 * 获取所有岗位
	 *
	 * @return R
	 */
	@GetMapping("/position/getListAll")
	R<List<SysPositionEntity>> getListAll();

	/**
	 * 获取多个Id的岗位列表
	 *
	 * @param ids 逗号分割的岗位Id
	 * @return R
	 */
	@ApiOperation("获取多个Id的岗位列表")
	@GetMapping("/position/getListByIds")
	R<List<SysPositionEntity>> getListByIds(@RequestParam("ids") String ids);
}
