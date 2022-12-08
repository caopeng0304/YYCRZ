package com.sinosoft.ie.booster.workflow.controller;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.workflow.entity.FlowDelegateEntity;
import com.sinosoft.ie.booster.workflow.model.flowdelegate.FlowDelegatListVO;
import com.sinosoft.ie.booster.workflow.model.flowdelegate.FlowDelegateCrForm;
import com.sinosoft.ie.booster.workflow.model.flowdelegate.FlowDelegateInfoVO;
import com.sinosoft.ie.booster.workflow.model.flowdelegate.FlowDelegateUpForm;
import com.sinosoft.ie.booster.workflow.service.FlowDelegateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 流程委托
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "流程委托", value = "FlowDelegate")
@RestController
@RequestMapping("/Engine/FlowDelegate")
public class FlowDelegateController {

	@Autowired
	private FlowDelegateService flowDelegateService;

	/**
	 * 获取流程委托列表
	 *
	 * @param pagination
	 * @return
	 */
	@ApiOperation("获取流程委托列表")
	@GetMapping
	public R<PageListVO<FlowDelegatListVO>> list(Pagination pagination) {
		List<FlowDelegateEntity> list = flowDelegateService.getList(pagination);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(pagination, PaginationVO.class);
		List<FlowDelegatListVO> listVO = JsonUtil.getJsonToList(list, FlowDelegatListVO.class);
		PageListVO<FlowDelegatListVO> vo = new PageListVO<>();
		vo.setList(listVO);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 获取流程委托信息
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("获取流程委托信息")
	@GetMapping("/{id}")
	public R<FlowDelegateInfoVO> info(@PathVariable("id") Long id) throws DataException {
		FlowDelegateEntity entity = flowDelegateService.getInfo(id);
		FlowDelegateInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, FlowDelegateInfoVO.class);
		return R.ok(vo);
	}

	/**
	 * 新建流程委托
	 *
	 * @param flowDelegateCrForm 实体对象
	 * @return
	 */
	@ApiOperation("新建流程委托")
	@PostMapping
	public R<Boolean> create(@RequestBody @Valid FlowDelegateCrForm flowDelegateCrForm) {
		FlowDelegateEntity entity = JsonUtil.getJsonToBean(flowDelegateCrForm, FlowDelegateEntity.class);
		BoosterUser userInfo = SecurityUtils.getUser();
		if (userInfo.getUsername().equals(entity.getToUserName())) {
			return R.failed("委托人为自己，委托失败");
		}
		flowDelegateService.create(entity);
		return R.ok(null, "新建成功");
	}

	/**
	 * 更新流程委托
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("更新流程委托")
	@PutMapping("/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid FlowDelegateUpForm flowDelegateUpForm) {
		FlowDelegateEntity entity = JsonUtil.getJsonToBean(flowDelegateUpForm, FlowDelegateEntity.class);
		BoosterUser userInfo = SecurityUtils.getUser();
		if (userInfo.getUsername().equals(entity.getToUserName())) {
			return R.failed("委托人为自己，委托失败");
		}
		boolean flag = flowDelegateService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok(null, "更新成功");
	}

	/**
	 * 删除流程委托
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("删除流程委托")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		FlowDelegateEntity entity = flowDelegateService.getInfo(id);
		if (entity != null) {
			flowDelegateService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	/**
	 * 列表
	 *
	 * @return
	 */
	@GetMapping("/getList")
	public List<FlowDelegateEntity> getList() {
		return flowDelegateService.getList();
	}
}
