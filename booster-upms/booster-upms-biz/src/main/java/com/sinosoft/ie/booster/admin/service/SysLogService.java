package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.admin.api.model.PreLogVO;
import com.sinosoft.ie.booster.admin.api.model.SysLogDTO;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysLogService extends IService<SysLogEntity> {

	/**
	 * 分页查询日志
	 *
	 * @param page
	 * @param sysLog
	 * @return
	 */
	Page<SysLogEntity> getLogByPage(Page<SysLogEntity> page, SysLogDTO sysLog);

	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	Boolean saveBatchLogs(List<PreLogVO> preLogVoList);

	/**
	 * 列表查询日志
	 *
	 * @param sysLog 查询条件
	 * @return List
	 */
	List<SysLogEntity> getLogList(SysLogDTO sysLog);

}
