package com.sinosoft.ie.booster.visualdev.model.portal;

import com.alibaba.fastjson.annotation.JSONField;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class PortalSelectModel extends SumTree {
	private String fullName;
	@JSONField(name = "category")
	private String parentId;
}
