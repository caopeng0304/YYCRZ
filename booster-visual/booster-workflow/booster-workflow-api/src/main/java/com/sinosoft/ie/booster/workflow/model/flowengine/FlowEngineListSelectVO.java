package com.sinosoft.ie.booster.workflow.model.flowengine;

import lombok.Data;

import java.util.List;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
public class FlowEngineListSelectVO {

	private String id;
	private String fullName;
	private Boolean hasChildren;
	private List<FlowEngineListSelectVO> children;
}
