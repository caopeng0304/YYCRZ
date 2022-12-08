package com.sinosoft.ie.booster.visualdev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.entity.BaseDbLinkEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseDbLinkMapper;
import com.sinosoft.ie.booster.visualdev.service.BaseDbLinkService;
import com.sinosoft.ie.booster.visualdev.util.JdbcUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据连接
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Service
public class BaseDbLinkServiceImpl extends ServiceImpl<BaseDbLinkMapper, BaseDbLinkEntity> implements BaseDbLinkService {
	@Override
	public List<BaseDbLinkEntity> getList() {
		QueryWrapper<BaseDbLinkEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByDesc(BaseDbLinkEntity::getCreateTime);
		return this.list(queryWrapper);
	}

	@Override
	public List<BaseDbLinkEntity> getList(String keyWord) {
		QueryWrapper<BaseDbLinkEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().and(
				t -> t.like(BaseDbLinkEntity::getFullName, keyWord)
		);
		queryWrapper.lambda().orderByDesc(BaseDbLinkEntity::getSort).orderByDesc(BaseDbLinkEntity::getCreateTime);
		return this.list(queryWrapper);
	}

	@Override
	public BaseDbLinkEntity getInfo(Long id) {
		QueryWrapper<BaseDbLinkEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseDbLinkEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public boolean isExistByFullName(String fullName, Long id) {
		QueryWrapper<BaseDbLinkEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseDbLinkEntity::getFullName, fullName);
		if (id != null) {
			queryWrapper.lambda().ne(BaseDbLinkEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public void create(BaseDbLinkEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(Long id, BaseDbLinkEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public void delete(BaseDbLinkEntity entity) {
		this.removeById(entity.getId());
	}

	@Override
	@Transactional
	public boolean first(Long id) {
		boolean isOk = false;
		//获取要上移的那条数据的信息
		BaseDbLinkEntity upEntity = this.getById(id);
		Integer upSortCode = upEntity.getSort() == null ? 0 : upEntity.getSort();
		//查询上几条记录
		QueryWrapper<BaseDbLinkEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda()
				.lt(BaseDbLinkEntity::getSort, upSortCode)
				.orderByDesc(BaseDbLinkEntity::getSort);
		List<BaseDbLinkEntity> downEntity = this.list(queryWrapper);
		if (downEntity.size() > 0) {
			//交换两条记录的sort值
			Integer temp = upEntity.getSort();
			upEntity.setSort(downEntity.get(0).getSort());
			downEntity.get(0).setSort(temp);
			this.updateById(downEntity.get(0));
			this.updateById(upEntity);
			isOk = true;
		}
		return isOk;
	}

	@Override
	@Transactional
	public boolean next(Long id) {
		boolean isOk = false;
		//获取要下移的那条数据的信息
		BaseDbLinkEntity downEntity = this.getById(id);
		Integer upSortCode = downEntity.getSort() == null ? 0 : downEntity.getSort();
		//查询下几条记录
		QueryWrapper<BaseDbLinkEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda()
				.gt(BaseDbLinkEntity::getSort, upSortCode)
				.orderByAsc(BaseDbLinkEntity::getSort);
		List<BaseDbLinkEntity> upEntity = this.list(queryWrapper);
		if (upEntity.size() > 0) {
			//交换两条记录的sort值
			Integer temp = downEntity.getSort();
			downEntity.setSort(upEntity.get(0).getSort());
			downEntity.setUpdateTime(LocalDateTime.now());
			upEntity.get(0).setSort(temp);
			this.updateById(upEntity.get(0));
			this.updateById(downEntity);
			isOk = true;
		}
		return isOk;
	}

	@Override
	public boolean testDbConnection(BaseDbLinkEntity entity) {
		Connection conn = JdbcUtil.getConn(entity.getDbType(), entity.getUserName(), entity.getPassword(), entity.getHost(), entity.getPort(), entity.getServiceName());
		return conn != null;
	}
}
