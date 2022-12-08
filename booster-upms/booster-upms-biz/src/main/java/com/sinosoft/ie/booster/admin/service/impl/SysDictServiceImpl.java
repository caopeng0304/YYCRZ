package com.sinosoft.ie.booster.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysDictEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.mapper.SysDictItemMapper;
import com.sinosoft.ie.booster.admin.mapper.SysDictMapper;
import com.sinosoft.ie.booster.admin.service.SysDictService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.DictTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 字典表
 *
 * @author lengleng
 * @since 2019/03/19
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {

	private final SysDictItemMapper dictItemMapper;

	/**
	 * 根据ID 删除字典
	 * @param id 字典ID
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void removeDict(Long id) {
		SysDictEntity dict = this.getById(id);
		// 系统内置
		Assert.isTrue(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag()), "系统内置字典项目不能删除");
		baseMapper.deleteById(id);
		dictItemMapper.delete(Wrappers.<SysDictItemEntity>lambdaQuery().eq(SysDictItemEntity::getDictId, id));
	}

	/**
	 * 更新字典
	 * @param dict 字典
	 * @return
	 */
	@Override
	public void updateDict(SysDictEntity dict) {
		SysDictEntity sysDict = this.getById(dict.getId());
		// 系统内置
		Assert.isTrue(!DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystemFlag()), "系统内置字典项目不能修改");
		this.updateById(dict);
	}

}
