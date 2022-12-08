package com.sinosoft.ie.booster.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinosoft.ie.booster.admin.api.entity.SysBillRuleEntity;
import com.sinosoft.ie.booster.admin.api.model.BillRuleListVO;
import com.sinosoft.ie.booster.admin.service.SysBillRuleService;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 单据规则
 *
 * @author booster code generator
 * @since 2021-08-13 16:44:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/billrule")
@Api(value = "billrule", tags = "单据规则管理")
public class BillRuleController {

	private final SysBillRuleService billRuleService;

	/**
	 * 分页查询
	 *
	 * @param page     分页对象
	 * @param billRule 单据规则
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('admin_billrule_get')")
	public R<IPage<SysBillRuleEntity>> getBillRulePage(Page<SysBillRuleEntity> page, SysBillRuleEntity billRule) {
		return R.ok(billRuleService.page(page, Wrappers.lambdaQuery(billRule).orderByAsc(SysBillRuleEntity::getId)));
	}


	/**
	 * 通过id查询单据规则
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('admin_billrule_get')")
	public R<SysBillRuleEntity> getById(@PathVariable("id") Long id) {
		return R.ok(billRuleService.getById(id));
	}

	/**
	 * 新增单据规则
	 *
	 * @param billRule 单据规则
	 * @return R
	 */
	@ApiOperation(value = "新增单据规则", notes = "新增单据规则")
	@SysLog("新增单据规则")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('admin_billrule_add')")
	public R<Boolean> save(@RequestBody @Valid SysBillRuleEntity billRule) {
		if (billRuleService.isExistByFullName(billRule.getFullName(), billRule.getId())) {
			return R.failed("名称不能重复");
		}
		if (billRuleService.isExistByEnCode(billRule.getEncode(), billRule.getId())) {
			return R.failed("编码不能重复");
		}
		return R.ok(billRuleService.create(billRule));
	}

	/**
	 * 修改单据规则
	 *
	 * @param billRule 单据规则
	 * @return R
	 */
	@ApiOperation(value = "修改单据规则", notes = "修改单据规则")
	@SysLog("修改单据规则")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('admin_billrule_edit')")
	public R<Boolean> updateById(@RequestBody SysBillRuleEntity billRule) {
		SysBillRuleEntity entity = billRuleService.getById(billRule.getId());
		if (entity != null && StringUtils.hasLength(entity.getOutputNumber())) {
			return R.failed("单据已经被使用，不允许被编辑");
		}
		if (billRuleService.isExistByFullName(billRule.getFullName(), billRule.getId())) {
			return R.failed("名称不能重复");
		}
		if (billRuleService.isExistByEnCode(billRule.getFullName(), billRule.getId())) {
			return R.failed("编码不能重复");
		}
		return R.ok(billRuleService.update(billRule));
	}

	/**
	 * 通过id删除单据规则
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除单据规则", notes = "通过id删除单据规则")
	@SysLog("通过id删除单据规则")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('admin_billrule_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		SysBillRuleEntity entity = billRuleService.getById(id);
		if (entity != null) {
			if (StringUtils.hasLength(entity.getOutputNumber())) {
				return R.failed("单据已经被使用，不允许被删除");
			} else {
				billRuleService.removeById(id);
				return R.ok(null, "删除成功");
			}
		}
		return R.failed("删除失败，数据不存在");
	}

	/**
	 * 获取单据流水号
	 *
	 * @param enCode 参数编码
	 * @return
	 */
	@ApiOperation("获取单据流水号")
	@GetMapping("/getBillNumber/{enCode}")
	public R<String> getBillNumber(@PathVariable("enCode") String enCode) {
		String billNumber = billRuleService.getBillNumber(enCode);
		return R.ok(billNumber);
	}

	/**
	 * 获取单据规则下拉框
	 *
	 * @param
	 * @return
	 */
	@ApiOperation("获取单据规则下拉框")
	@GetMapping("/Selector")
	public R<ListVO<BillRuleListVO>> selectList() {
		List<SysBillRuleEntity> list = billRuleService.getList();
		List<BillRuleListVO> vo = JsonUtil.getJsonToList(list, BillRuleListVO.class);
		ListVO<BillRuleListVO> listVO = new ListVO<>();
		listVO.setList(vo);
		return R.ok(listVO);
	}
}
