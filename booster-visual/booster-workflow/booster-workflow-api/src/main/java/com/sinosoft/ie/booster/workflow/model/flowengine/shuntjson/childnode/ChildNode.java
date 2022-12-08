package com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode;

import lombok.Data;

import java.util.List;

/**
 * 解析引擎
 *
 * @author booster开发平台组
 * @since 2021/3/15 9:12
 */
@Data
public class ChildNode {
	private String type;
	private String content;
	private Properties properties;
	private String nodeId;
	private String prevId;
	private ChildNode childNode;
	private String conditionType;
	private List<ChildNode> conditionNodes;
	private Boolean isInterflow;

}
