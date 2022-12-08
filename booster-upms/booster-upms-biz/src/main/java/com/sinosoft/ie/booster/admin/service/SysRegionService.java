package com.sinosoft.ie.booster.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysRegionEntity;
import com.sinosoft.ie.booster.admin.api.model.RegionVO;
import com.sinosoft.ie.booster.common.core.util.node.INode;

import java.util.List;
import java.util.Map;

/**
 * 行政区划表 服务类
 *
 * @author haocy
 */
public interface SysRegionService extends IService<SysRegionEntity> {

	/**
	 * 提交
	 *
	 * @param region
	 * @return
	 */
	boolean submit(SysRegionEntity region);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	boolean removeRegion(String id);

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

	/**
	 * 获取RegionVO
	 * @param region
	 * @return
	 */
	RegionVO entityVO(SysRegionEntity region);

	/**
	 * 查询行政区划树
	 * @return 树
	 */
	List<Tree<String>> listRegionTrees();

}
