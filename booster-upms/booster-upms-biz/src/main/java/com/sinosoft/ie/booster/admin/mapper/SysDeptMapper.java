package com.sinosoft.ie.booster.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

	/**
	 * 关联dept——relation
	 * @return 数据列表
	 */
	List<SysDeptEntity> listDepts();

}
