package com.sinosoft.ie.booster.visualdev.model.datainterface;

import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DataInterfaceTreeModel extends SumTree {
	//    private String id;
//    private String parentId;
	private String fullName;
	private String categoryId;
}
