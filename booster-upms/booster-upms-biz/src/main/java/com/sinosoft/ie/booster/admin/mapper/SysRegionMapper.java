package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.admin.api.entity.SysRegionEntity;
import com.sinosoft.ie.booster.common.core.util.node.INode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 行政区划表 Mapper 接口
 *
 * @author haocy
 */
@Mapper
public interface SysRegionMapper extends BaseMapper<SysRegionEntity> {

	/**
	 * 懒加载列表
	 *
	 * @param parentCode
	 * @param param
	 * @return
	 */
	List<INode> lazyList(String parentCode, Map<String, Object> param);

	/**
	 * 懒加载列表
	 *
	 * @param parentCode
	 * @param param
	 * @return
	 */
	List<INode> lazyTree(String parentCode, Map<String, Object> param);

}
