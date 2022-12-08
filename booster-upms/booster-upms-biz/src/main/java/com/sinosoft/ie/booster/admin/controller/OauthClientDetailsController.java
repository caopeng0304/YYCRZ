package com.sinosoft.ie.booster.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinosoft.ie.booster.admin.api.entity.SysOauthClientDetailsEntity;
import com.sinosoft.ie.booster.admin.service.SysOauthClientDetailsService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2018-05-15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Api(value = "client", tags = "客户端管理模块")
public class OauthClientDetailsController {

	private final SysOauthClientDetailsService sysOauthClientDetailsService;

	/**
	 * 通过ID查询
	 *
	 * @param id clientId
	 * @return SysOauthClientDetails
	 */
	@GetMapping("/{id}")
	public R<SysOauthClientDetailsEntity> getById(@PathVariable String id) {
		return R.ok(sysOauthClientDetailsService.getById(id));
	}

	/**
	 * 简单分页查询
	 *
	 * @param page                  分页对象
	 * @param sysOauthClientDetails 系统终端
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<SysOauthClientDetailsEntity>> getOauthClientDetailsPage(Page<SysOauthClientDetailsEntity> page, SysOauthClientDetailsEntity sysOauthClientDetails) {
		return R.ok(sysOauthClientDetailsService.page(page, Wrappers.lambdaQuery(sysOauthClientDetails).orderByAsc(SysOauthClientDetailsEntity::getClientId)));
	}

	/**
	 * 添加
	 *
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@SysLog("添加终端")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_client_add')")
	public R<Boolean> add(@Valid @RequestBody SysOauthClientDetailsEntity sysOauthClientDetails) {
		return R.ok(sysOauthClientDetailsService.save(sysOauthClientDetails));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除终端")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_client_del')")
	public R<Boolean> removeById(@PathVariable String id) {
		return R.ok(sysOauthClientDetailsService.removeClientDetailsById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@SysLog("编辑终端")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_client_edit')")
	public R<Boolean> update(@Valid @RequestBody SysOauthClientDetailsEntity sysOauthClientDetails) {
		return R.ok(sysOauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
	}

}
