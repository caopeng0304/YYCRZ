package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysDictEntity;

/**
 * 字典表
 *
 * @author lengleng
 * @since 2019/03/19
 */
public interface SysDictService extends IService<SysDictEntity> {

	/**
	 * 根据ID 删除字典
	 * @param id
	 * @return
	 */
	void removeDict(Long id);

	/**
	 * 更新字典
	 * @param sysDict 字典
	 * @return
	 */
	void updateDict(SysDictEntity sysDict);

}
