package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;

import java.util.List;

/**
 * 字典项
 *
 * @author lengleng
 * @since 2019/03/19
 */
public interface SysDictItemService extends IService<SysDictItemEntity> {

	/**
	 * 删除字典项
	 *
	 * @param id 字典项ID
	 * @return
	 */
	void removeDictItem(Long id);

	/**
	 * 更新字典项
	 *
	 * @param item 字典项
	 * @return
	 */
	void updateDictItem(SysDictItemEntity item);

	/**
	 * 获取树形分页列表
	 *
	 * @param page        分页对象
	 * @param sysDictItem 字典项实体
	 * @return 树形分页列表
	 */
	IPage<SysDictItemEntity> treePage(IPage<?> page, SysDictItemEntity sysDictItem);

	/**
	 * 根据父Id查找指定类型下爸爸的孩子
	 *
	 * @param type     类型
	 * @param parentId 父Id
	 * @return 同类型字典
	 */
	List<SysDictItemEntity> listChildren(String type, Long parentId);
}
