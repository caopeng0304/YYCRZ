package com.sinosoft.ie.booster.visualdev.model.datascreen;

import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DataTreeModel extends SumTree {
	private String fullName;
}
