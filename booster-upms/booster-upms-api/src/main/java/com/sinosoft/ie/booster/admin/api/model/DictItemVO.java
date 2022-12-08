package com.sinosoft.ie.booster.admin.api.model;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项树形视图实体类
 *
 * @author haocy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictItemVO对象", description = "字典项树形视图")
public class DictItemVO extends SysDictItemEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 是否有子孙节点
	 */
	private Boolean hasChildren;
}
