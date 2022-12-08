package com.sinosoft.ie.booster.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.admin.api.model.PreLogVO;
import com.sinosoft.ie.booster.admin.api.model.SysLogDTO;
import com.sinosoft.ie.booster.admin.service.SysLogService;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
@Api(value = "log", tags = "日志管理模块")
public class LogController {

	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 *
	 * @param page   分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<SysLogEntity>> getLogPage(Page<SysLogEntity> page, SysLogDTO sysLog) {
		return R.ok(sysLogService.getLogByPage(page, sysLog));
	}

	/**
	 * 删除日志
	 *
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_log_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		return R.ok(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 *
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@Inner
	@PostMapping
	public R<Boolean> save(@Valid @RequestBody SysLogEntity sysLog) {
		return R.ok(sysLogService.save(sysLog));
	}

	/**
	 * 批量插入前端异常日志
	 *
	 * @param preLogVoList 日志实体
	 * @return success/false
	 */
	@PostMapping("/logs")
	public R<Boolean> saveBatchLogs(@RequestBody List<PreLogVO> preLogVoList) {
		return R.ok(sysLogService.saveBatchLogs(preLogVoList));
	}

	/**
	 * 导出excel 表格
	 *
	 * @param sysLog 查询条件
	 * @return EXCEL
	 */
	@ResponseExcel
	@GetMapping("/export")
	@PreAuthorize("@pms.hasPermission('sys_log_import_export')")
	public List<SysLogEntity> export(SysLogDTO sysLog) {
		return sysLogService.getLogList(sysLog);
	}
}
