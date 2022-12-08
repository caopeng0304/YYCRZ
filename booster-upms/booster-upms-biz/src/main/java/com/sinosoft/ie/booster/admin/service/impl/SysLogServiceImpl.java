package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import com.sinosoft.ie.booster.admin.api.model.PreLogVO;
import com.sinosoft.ie.booster.admin.api.model.SysLogDTO;
import com.sinosoft.ie.booster.admin.mapper.SysLogMapper;
import com.sinosoft.ie.booster.admin.service.SysLogService;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements SysLogService {

	@Override
	public Page<SysLogEntity> getLogByPage(Page<SysLogEntity> page, SysLogDTO sysLog) {
		LambdaQueryWrapper<SysLogEntity> wrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(sysLog.getType())) {
			wrapper.eq(SysLogEntity::getType, sysLog.getType());
		}

		if (ArrayUtil.isNotEmpty(sysLog.getCreateTime())) {
			wrapper.ge(SysLogEntity::getCreateTime, sysLog.getCreateTime()[0]).le(SysLogEntity::getCreateTime,
					sysLog.getCreateTime()[1]);
		}

		return baseMapper.selectPage(page, wrapper);
	}

	@Override
	public Boolean saveBatchLogs(List<PreLogVO> preLogVoList) {
		List<SysLogEntity> sysLogs = preLogVoList.stream()
				.map(pre -> {
					SysLogEntity log = new SysLogEntity();
					log.setType(CommonConstants.STATUS_LOCK);
					log.setTitle(pre.getInfo());
					log.setException(pre.getStack());
					log.setParams(pre.getMessage());
					log.setCreateTime(LocalDateTime.now());
					log.setRequestUri(pre.getUrl());
					log.setCreateBy(pre.getUser());
					return log;
				})
				.collect(Collectors.toList());
		return this.saveBatch(sysLogs);
	}

	/**
	 * 列表查询日志
	 *
	 * @param sysLog 查询条件
	 * @return List
	 */
	@Override
	public List<SysLogEntity> getLogList(SysLogDTO sysLog) {
		return baseMapper.selectList(buildQueryWrapper(sysLog));
	}

	/**
	 * 构建查询的 wrapper
	 *
	 * @param sysLog 查询条件
	 * @return LambdaQueryWrapper
	 */
	private LambdaQueryWrapper<SysLogEntity> buildQueryWrapper(SysLogDTO sysLog) {
		LambdaQueryWrapper<SysLogEntity> wrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(sysLog.getType())) {
			wrapper.eq(SysLogEntity::getType, sysLog.getType());
		}

		if (ArrayUtil.isNotEmpty(sysLog.getCreateTime())) {
			wrapper.ge(SysLogEntity::getCreateTime, sysLog.getCreateTime()[0]).le(SysLogEntity::getCreateTime,
					sysLog.getCreateTime()[1]);
		}

		return wrapper;
	}

}
