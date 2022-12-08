package com.sinosoft.ie.booster.common.core.util.treeutil;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SumTree<T> {
	private String id;
	private String parentId;
	private Boolean hasChildren;
	private List<SumTree<T>> children;
}
