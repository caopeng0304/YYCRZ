package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典项
 *
 * @author lengleng
 * @since 2019/03/19
 */
@Mapper
public interface SysDictItemMapper extends BaseMapper<SysDictItemEntity> {

	/**
	 * 获取树形分页列表
	 *
	 * @param page        分页对象
	 * @param sysDictItem 字典项实体
	 * @return 树形分页列表
	 */
	IPage<SysDictItemEntity> treePage(IPage<?> page, @Param("query") SysDictItemEntity sysDictItem);

	/**
	 * 根据父Id查找指定类型下爸爸的孩子
	 *
	 * @param type     类型
	 * @param parentId 父Id
	 * @return 同类型字典
	 */
	List<SysDictItemEntity> listChildren(@Param("type") String type, @Param("parentId") Long parentId);
}
