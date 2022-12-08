
package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysBillRuleEntity;

import java.util.List;

/**
 * 单据规则
 *
 * @author booster code generator
 * @since 2021-08-13 16:44:44
 */
public interface SysBillRuleService extends IService<SysBillRuleEntity> {

	/**
	 * 验证名称
	 *
	 * @param fullName 名称
	 * @param id       主键值
	 * @return
	 */
	boolean isExistByFullName(String fullName, Long id);

	/**
	 * 验证编码
	 *
	 * @param enCode 编码
	 * @param id     主键值
	 * @return
	 */
	boolean isExistByEnCode(String enCode, Long id);

	/**
	 * 创建
	 *
	 * @param entity 实体对象
	 */
	boolean create(SysBillRuleEntity entity);

	/**
	 * 更新
	 *
	 * @param entity 实体对象
	 * @return
	 */
	boolean update(SysBillRuleEntity entity);

	/**
	 * 获取流水号
	 *
	 * @param enCode 流水编码
	 * @return
	 */
	String getBillNumber(String enCode);

	/**
	 * 获取列表
	 *
	 * @return
	 */
	List<SysBillRuleEntity> getList();

}
