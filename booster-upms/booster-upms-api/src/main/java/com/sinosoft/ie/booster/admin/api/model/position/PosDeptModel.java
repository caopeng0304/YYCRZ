package com.sinosoft.ie.booster.admin.api.model.position;

import com.alibaba.fastjson.annotation.JSONField;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PosDeptModel extends SumTree {
	private String fullName;
	@ApiModelProperty(value = "状态")
	private String enabledFlag;
	@JSONField(name = "category")
	private String type;
	@ApiModelProperty(value = "图标")
	private String icon;
}
