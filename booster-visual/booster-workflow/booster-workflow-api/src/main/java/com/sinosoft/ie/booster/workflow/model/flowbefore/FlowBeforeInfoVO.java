package com.sinosoft.ie.booster.workflow.model.flowbefore;

import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode.FormOperates;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:18
 */
@Data
public class FlowBeforeInfoVO {
	private Long freeApprover;
	private FlowTaskEntityInfoModel flowTaskInfo;
	private List<FlowTaskNodeEntityInfoModel> flowTaskNodeList;
	private List<FlowTaskOperatorEntityInfoModel> flowTaskOperatorList;
	private List<FlowTaskOperatorRecordEntityInfoModel> flowTaskOperatorRecordList;
	private String flowFormInfo;
	private List<FormOperates> formOperates = new ArrayList<>();
}
