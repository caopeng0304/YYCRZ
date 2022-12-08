package com.sinosoft.ie.booster.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysDictEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.mapper.SysDictItemMapper;
import com.sinosoft.ie.booster.admin.service.SysDictItemService;
import com.sinosoft.ie.booster.admin.service.SysDictService;
import com.sinosoft.ie.booster.common.core.constant.CacheConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.DictTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 字典项
 *
 * @author lengleng
 * @since 2019/03/19
 */
@Service
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItemEntity> implements SysDictItemService {

	private final SysDictService dictService;

	/**
	 * 删除字典项
	 *
	 * @param id 字典项ID
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public void removeDictItem(Long id) {
		//判断是否存在子项
		Assert.isTrue(this.list(Wrappers.<SysDictItemEntity>query().lambda().eq(SysDictItemEntity::getParentId, id)).isEmpty(), "字典项存在子项不能删除");
		// 根据ID查询字典ID
		SysDictItemEntity dictItem = this.getById(id);
		SysDictEntity dict = dictService.getById(dictItem.getDictId());
		// 系统内置
		Assert.isTrue(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag()), "系统内置字典项目不能删除");
		this.removeById(id);
	}

	/**
	 * 更新字典项
	 *
	 * @param item 字典项
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public void updateDictItem(SysDictItemEntity item) {
		// 查询字典
		SysDictEntity dict = dictService.getById(item.getDictId());
		// 系统内置
		Assert.isTrue(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag()), "系统内置字典项目不能修改");
		this.updateById(item);
	}

	/**
	 * 获取树形分页列表
	 *
	 * @param page        分页对象
	 * @param sysDictItem 字典项实体
	 * @return 树形分页列表
	 */
	@Override
	public IPage<SysDictItemEntity> treePage(IPage<?> page, SysDictItemEntity sysDictItem) {
		return baseMapper.treePage(page, sysDictItem);
	}

	/**
	 * 根据父Id查找指定类型下爸爸的孩子
	 *
	 * @param type     类型
	 * @param parentId 父Id
	 * @return 同类型字典
	 */
	@Override
	public List<SysDictItemEntity> listChildren(String type, Long parentId) {
		return baseMapper.listChildren(type, parentId);
	}
}
