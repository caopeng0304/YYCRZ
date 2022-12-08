package com.sinosoft.ie.booster.workflow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.workflow.entity.FlowDelegateEntity;
import com.sinosoft.ie.booster.workflow.mapper.FlowDelegateMapper;
import com.sinosoft.ie.booster.workflow.service.FlowDelegateService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 流程委托
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowDelegateServiceImpl extends ServiceImpl<FlowDelegateMapper, FlowDelegateEntity> implements FlowDelegateService {

	@Override
	public List<FlowDelegateEntity> getList(Pagination pagination) {
		String userName = SecurityUtils.getUser().getUsername();
		QueryWrapper<FlowDelegateEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowDelegateEntity::getCreateBy, userName);
		if (!StrUtil.isEmpty(pagination.getKeyword())) {
			queryWrapper.lambda().and(
					t -> t.like(FlowDelegateEntity::getFlowName, pagination.getKeyword())
							.or().like(FlowDelegateEntity::getToUserName, pagination.getKeyword())
			);
		}
		//排序
		queryWrapper.lambda().orderByDesc(FlowDelegateEntity::getSort);
		Page<FlowDelegateEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
		IPage<FlowDelegateEntity> flowDelegateEntityPage = this.page(page, queryWrapper);
		return pagination.setData(flowDelegateEntityPage.getRecords(), page.getTotal());
	}


	@Override
	public List<FlowDelegateEntity> getList() {
		String userName = SecurityUtils.getUser().getUsername();
		QueryWrapper<FlowDelegateEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowDelegateEntity::getCreateBy, userName);
		return this.baseMapper.selectList(queryWrapper);
	}

	@Override
	public FlowDelegateEntity getInfo(Long id) {
		QueryWrapper<FlowDelegateEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowDelegateEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(FlowDelegateEntity entity) {
		this.removeById(entity.getId());
	}

	@Override
	public void create(FlowDelegateEntity entity) {
		entity.setSort(RandomUtil.parses());
		this.save(entity);
	}

	@Override
	public List<FlowDelegateEntity> getUser(String userName) {
		Date thisTime = DateUtil.getNowDate();
		QueryWrapper<FlowDelegateEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowDelegateEntity::getToUserName, userName).le(FlowDelegateEntity::getStartTime, thisTime).ge(FlowDelegateEntity::getEndTime, thisTime);
		return this.list(queryWrapper);
	}

	@Override
	public boolean update(Long id, FlowDelegateEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}
}
