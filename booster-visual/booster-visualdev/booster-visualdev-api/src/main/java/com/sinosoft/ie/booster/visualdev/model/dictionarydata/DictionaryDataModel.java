package com.sinosoft.ie.booster.visualdev.model.dictionarydata;

import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictionaryDataModel extends SumTree {
	private String id;
	private String parentId;
	private String fullName;
	private String encode;
	private String enabledFlag;
	private String icon;
	private long sort;
}
