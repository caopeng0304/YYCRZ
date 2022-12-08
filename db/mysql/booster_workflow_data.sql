SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `booster_workflow`;

-- ----------------------------
-- Records of flow_engine
-- ----------------------------
INSERT INTO `flow_engine` VALUES (1494552080740343809, 'testnotable', '测试流程无表', 0, 'warehouseManagement', 2, NULL, '[]', 0, 'icon-ym icon-ym-generator-Panel', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"aSy4Pr\",\"childNode\":{\"type\":\"approver\",\"content\":\"发起者本人\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[],\"approverPos\":[],\"assigneeType\":3,\"formOperates\":[{\"id\":\"billRuleField102\",\"name\":\"单据组件\",\"read\":true,\"write\":false},{\"id\":\"comInputField101\",\"name\":\"姓名\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"vQy4Pr\",\"prevId\":\"aSy4Pr\"}}', '{\"formRef\":\"elForm\",\"formModel\":\"dataForm\",\"size\":\"medium\",\"labelPosition\":\"right\",\"labelWidth\":100,\"formRules\":\"rules\",\"popupType\":\"general\",\"generalWidth\":\"600px\",\"fullScreenWidth\":\"100%\",\"gutter\":15,\"disabled\":false,\"span\":24,\"formBtns\":false,\"cancelButtonText\":\"取 消\",\"confirmButtonText\":\"确 定\",\"formStyle\":\"\",\"idGlobal\":102,\"fields\":[{\"__config__\":{\"boosKey\":\"billRule\",\"label\":\"单据组件\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"defaultValue\":null,\"tagIcon\":\"icon-ym icon-ym-generator-documents\",\"layout\":\"colFormItem\",\"required\":false,\"span\":24,\"dragDisabled\":false,\"trigger\":\"change\",\"rule\":\"testnotable\",\"formId\":102,\"renderKey\":1645165611608},\"readonly\":true,\"placeholder\":\"系统自动生成\",\"__vModel__\":\"billRuleField102\"},{\"__config__\":{\"boosKey\":\"comInput\",\"label\":\"姓名\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"icon-ym icon-ym-generator-input\",\"required\":false,\"layout\":\"colFormItem\",\"span\":24,\"dragDisabled\":false,\"regList\":[],\"trigger\":\"blur\",\"formId\":101,\"renderKey\":1645163897379},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"maxlength\":null,\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"comInputField101\"}]}', '', 0, '1', '0', '2022-02-18 13:59:18', 'admin', '2022-02-18 14:27:37', 'admin');
INSERT INTO `flow_engine` VALUES (1494556061268975618, 'testnotable_1645164907165', '测试流程无表_1645164907165', 0, 'warehouseManagement', 2, NULL, '[]', 0, 'icon-ym icon-ym-generator-Panel', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"GenxOr\",\"childNode\":{\"type\":\"approver\",\"content\":\"发起者本人\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[],\"approverPos\":[],\"assigneeType\":3,\"formOperates\":[{\"id\":\"comInputField101\",\"name\":\"姓名\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"DgnxOr\",\"prevId\":\"GenxOr\"}}', '{\"formRef\":\"elForm\",\"formModel\":\"dataForm\",\"size\":\"medium\",\"labelPosition\":\"right\",\"labelWidth\":100,\"formRules\":\"rules\",\"popupType\":\"general\",\"generalWidth\":\"600px\",\"fullScreenWidth\":\"100%\",\"gutter\":15,\"disabled\":false,\"span\":24,\"formBtns\":false,\"cancelButtonText\":\"取 消\",\"confirmButtonText\":\"确 定\",\"formStyle\":\"\",\"idGlobal\":101,\"fields\":[{\"__config__\":{\"boosKey\":\"comInput\",\"label\":\"姓名\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"icon-ym icon-ym-generator-input\",\"required\":false,\"layout\":\"colFormItem\",\"span\":24,\"dragDisabled\":false,\"regList\":[],\"trigger\":\"blur\",\"formId\":101,\"renderKey\":1645163897379},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"maxlength\":null,\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"comInputField101\"}]}', '', 0, '1', '1', '2022-02-18 14:15:07', 'admin', '2022-02-18 14:16:54', 'admin');
INSERT INTO `flow_engine` VALUES (1494584336359985153, 'testfortable', '测试流程有表', 0, 'personnelManage', 2, NULL, '[{\"relationField\":\"\",\"relationTable\":\"\",\"table\":\"form_staff_overtime\",\"tableName\":\"员工加班申请表\",\"tableField\":\"\",\"typeId\":\"1\",\"fields\":[{\"field\":\"id\",\"fieldName\":\"自然主键\",\"dataType\":\"bigint\"},{\"field\":\"flow_id\",\"fieldName\":\"流程标题\",\"dataType\":\"bigint\"},{\"field\":\"flow_title\",\"fieldName\":\"流程标题\",\"dataType\":\"varchar\"},{\"field\":\"flow_urgent\",\"fieldName\":\"紧急程度\",\"dataType\":\"int\"},{\"field\":\"bill_no\",\"fieldName\":\"流程单据\",\"dataType\":\"varchar\"},{\"field\":\"apply_user\",\"fieldName\":\"申请人\",\"dataType\":\"varchar\"},{\"field\":\"department\",\"fieldName\":\"申请部门\",\"dataType\":\"varchar\"},{\"field\":\"apply_date\",\"fieldName\":\"申请日期\",\"dataType\":\"datetime\"},{\"field\":\"total_time\",\"fieldName\":\"总计时间\",\"dataType\":\"varchar\"},{\"field\":\"start_time\",\"fieldName\":\"开始时间\",\"dataType\":\"datetime\"},{\"field\":\"end_time\",\"fieldName\":\"结束时间\",\"dataType\":\"datetime\"},{\"field\":\"category\",\"fieldName\":\"记入类别\",\"dataType\":\"varchar\"},{\"field\":\"cause\",\"fieldName\":\"加班事由\",\"dataType\":\"text\"}]}]', 0, 'icon-ym icon-ym-tree-user2', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"H2rVPr\",\"childNode\":{\"type\":\"approver\",\"content\":\"admin\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[\"admin\"],\"approverPos\":[],\"assigneeType\":6,\"formOperates\":[{\"id\":\"bill_no\",\"name\":\"流程单据\",\"read\":true,\"write\":false},{\"id\":\"apply_user\",\"name\":\"申请人\",\"read\":true,\"write\":false},{\"id\":\"apply_date\",\"name\":\"申请日期\",\"read\":true,\"write\":true},{\"id\":\"cause\",\"name\":\"加班事由\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[\"haocy\"],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"R2rVPr\",\"prevId\":\"H2rVPr\"}}', '{\"formRef\":\"elForm\",\"formModel\":\"dataForm\",\"size\":\"medium\",\"labelPosition\":\"right\",\"labelWidth\":100,\"formRules\":\"rules\",\"popupType\":\"general\",\"generalWidth\":\"600px\",\"fullScreenWidth\":\"100%\",\"gutter\":15,\"disabled\":false,\"span\":24,\"formBtns\":false,\"cancelButtonText\":\"取 消\",\"confirmButtonText\":\"确 定\",\"formStyle\":\"\",\"idGlobal\":109,\"fields\":[{\"__config__\":{\"boosKey\":\"billRule\",\"label\":\"流程单据\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"defaultValue\":null,\"tagIcon\":\"icon-ym icon-ym-generator-documents\",\"layout\":\"colFormItem\",\"required\":false,\"span\":24,\"dragDisabled\":false,\"trigger\":\"change\",\"rule\":\"testfortable\",\"formId\":101,\"renderKey\":1645171323727},\"readonly\":true,\"placeholder\":\"系统自动生成\",\"__vModel__\":\"bill_no\"},{\"__config__\":{\"boosKey\":\"comInput\",\"label\":\"申请人\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"icon-ym icon-ym-generator-input\",\"required\":false,\"layout\":\"colFormItem\",\"span\":24,\"dragDisabled\":false,\"regList\":[],\"trigger\":\"blur\",\"formId\":102,\"renderKey\":1645171415350},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"maxlength\":null,\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"apply_user\"},{\"__config__\":{\"boosKey\":\"date\",\"label\":\"申请日期\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-date-picker\",\"tagIcon\":\"icon-ym icon-ym-generator-date\",\"defaultValue\":null,\"required\":false,\"layout\":\"colFormItem\",\"span\":24,\"dragDisabled\":false,\"regList\":[],\"trigger\":\"change\",\"formId\":104,\"renderKey\":1645171527415},\"placeholder\":\"请选择\",\"type\":\"date\",\"style\":{\"width\":\"100%\"},\"disabled\":false,\"clearable\":true,\"format\":\"yyyy-MM-dd\",\"value-format\":\"timestamp\",\"readonly\":false,\"__vModel__\":\"apply_date\"},{\"__config__\":{\"boosKey\":\"textarea\",\"label\":\"加班事由\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"icon-ym icon-ym-generator-textarea\",\"required\":false,\"layout\":\"colFormItem\",\"span\":24,\"dragDisabled\":false,\"regList\":[],\"trigger\":\"blur\",\"formId\":109,\"renderKey\":1645171580143},\"type\":\"textarea\",\"placeholder\":\"请输入\",\"autosize\":{\"minRows\":4,\"maxRows\":4},\"style\":{\"width\":\"100%\"},\"maxlength\":null,\"show-word-limit\":true,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"cause\"}]}', '', 0, '1', '0', '2022-02-18 16:07:28', 'admin', '2022-02-21 17:36:08', 'admin');
INSERT INTO `flow_engine` VALUES (1495590898239070209, 'testnotable_1645411631527', '测试流程无表_1645411631527', 0, 'warehouseManagement', 2, NULL, '[]', 0, 'icon-ym icon-ym-generator-Panel', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"aSy4Pr\",\"childNode\":{\"type\":\"approver\",\"content\":\"发起者本人\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[],\"approverPos\":[],\"assigneeType\":3,\"formOperates\":[{\"id\":\"billRuleField102\",\"name\":\"单据组件\",\"read\":true,\"write\":false},{\"id\":\"comInputField101\",\"name\":\"姓名\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"vQy4Pr\",\"prevId\":\"aSy4Pr\"}}', '{\"formRef\":\"elForm\",\"formModel\":\"dataForm\",\"size\":\"medium\",\"labelPosition\":\"right\",\"labelWidth\":100,\"formRules\":\"rules\",\"popupType\":\"general\",\"generalWidth\":\"600px\",\"fullScreenWidth\":\"100%\",\"gutter\":15,\"disabled\":false,\"span\":24,\"formBtns\":false,\"cancelButtonText\":\"取 消\",\"confirmButtonText\":\"确 定\",\"formStyle\":\"\",\"idGlobal\":102,\"fields\":[{\"__config__\":{\"boosKey\":\"billRule\",\"label\":\"单据组件\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"defaultValue\":null,\"tagIcon\":\"icon-ym icon-ym-generator-documents\",\"layout\":\"colFormItem\",\"required\":false,\"span\":24,\"dragDisabled\":false,\"trigger\":\"change\",\"rule\":\"testnotable\",\"formId\":102,\"renderKey\":1645165611608},\"readonly\":true,\"placeholder\":\"系统自动生成\",\"__vModel__\":\"billRuleField102\"},{\"__config__\":{\"boosKey\":\"comInput\",\"label\":\"姓名\",\"labelWidth\":null,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"icon-ym icon-ym-generator-input\",\"required\":false,\"layout\":\"colFormItem\",\"span\":24,\"dragDisabled\":false,\"regList\":[],\"trigger\":\"blur\",\"formId\":101,\"renderKey\":1645163897379},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"maxlength\":null,\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"comInputField101\"}]}', '', 0, '1', '0', '2022-02-21 10:47:12', 'admin', '2022-02-18 14:27:37', 'admin');
INSERT INTO `flow_engine` VALUES (1495602945991434241, 'infcrptgz', '传染病', 0, 'costRelated', 1, NULL, '[]', 0, 'icon-ym icon-ym-zujian', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"EQ7vfr\",\"childNode\":{\"type\":\"approver\",\"content\":\"admin\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[\"admin\"],\"approverPos\":[],\"assigneeType\":6,\"formOperates\":[{\"id\":\"patient_name\",\"name\":\"患者姓名\",\"read\":true,\"write\":false},{\"id\":\"id_no\",\"name\":\"身份证件号码\",\"read\":true,\"write\":false},{\"id\":\"ged_code\",\"name\":\"性别代码\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"9Y7vfr\",\"prevId\":\"EQ7vfr\"}}', '[{\"filedName\":\"患者姓名\",\"filedId\":\"patient_name\",\"required\":false},{\"filedName\":\"身份证件号码\",\"filedId\":\"id_no\",\"required\":false},{\"filedName\":\"性别代码\",\"filedId\":\"ged_code\",\"required\":false}]', '', 0, '1', '0', '2022-02-21 11:35:04', 'admin', '2022-02-21 11:35:04', 'admin');
INSERT INTO `flow_engine` VALUES (1495667931619090433, 'payDistribution', '薪酬发放', 0, 'costRelated', 1, NULL, '[]', 0, 'icon-ym icon-ym-wf-quotationApproval', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"yf7ygr\",\"childNode\":{\"type\":\"approver\",\"content\":\"admin\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[\"admin\"],\"approverPos\":[],\"assigneeType\":6,\"formOperates\":[{\"id\":\"id\",\"name\":\"主键\",\"read\":true,\"write\":false},{\"id\":\"flowId\",\"name\":\"流程主键\",\"read\":true,\"write\":false},{\"id\":\"flowTitle\",\"name\":\"流程标题\",\"read\":true,\"write\":false},{\"id\":\"flowUrgent\",\"name\":\"紧急程度\",\"read\":true,\"write\":false},{\"id\":\"billNo\",\"name\":\"流程单据\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"HY7ygr\",\"prevId\":\"yf7ygr\"}}', '[{\"filedName\":\"主键\",\"filedId\":\"id\",\"required\":false},{\"filedName\":\"流程主键\",\"filedId\":\"flowId\",\"required\":false},{\"filedName\":\"流程标题\",\"filedId\":\"flowTitle\",\"required\":false},{\"filedName\":\"紧急程度\",\"filedId\":\"flowUrgent\",\"required\":false},{\"filedName\":\"流程单据\",\"filedId\":\"billNo\",\"required\":false}]', '', 0, '1', '0', '2022-02-21 15:53:18', 'admin', '2022-02-21 15:53:18', 'admin');
INSERT INTO `flow_engine` VALUES (1495668859516575745, 'rewardPunishment', '行政赏罚', 0, 'costRelated', 1, NULL, '[]', 0, 'icon-ym icon-ym-flowTodo', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"HL5zgr\",\"childNode\":{\"type\":\"approver\",\"content\":\"admin\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[\"admin\"],\"approverPos\":[],\"assigneeType\":6,\"formOperates\":[{\"id\":\"id\",\"name\":\"主键\",\"read\":true,\"write\":false},{\"id\":\"flowId\",\"name\":\"流程主键\",\"read\":true,\"write\":false},{\"id\":\"flowTitle\",\"name\":\"流程标题\",\"read\":true,\"write\":false},{\"id\":\"flowUrgent\",\"name\":\"紧急程度\",\"read\":true,\"write\":false},{\"id\":\"fullName\",\"name\":\"员工姓名\",\"read\":true,\"write\":false},{\"id\":\"fillFromDate\",\"name\":\"填表日期\",\"read\":true,\"write\":false},{\"id\":\"department\",\"name\":\"员工部门\",\"read\":true,\"write\":false},{\"id\":\"position\",\"name\":\"员工职务\",\"read\":true,\"write\":false},{\"id\":\"rewardPun\",\"name\":\"赏罚金额\",\"read\":true,\"write\":false},{\"id\":\"reason\",\"name\":\"赏罚原因\",\"read\":true,\"write\":false},{\"id\":\"billNo\",\"name\":\"流程单据\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"OQ5zgr\",\"prevId\":\"HL5zgr\"}}', '[{\"filedName\":\"主键\",\"filedId\":\"id\",\"required\":false},{\"filedName\":\"流程主键\",\"filedId\":\"flowId\",\"required\":false},{\"filedName\":\"流程标题\",\"filedId\":\"flowTitle\",\"required\":false},{\"filedName\":\"紧急程度\",\"filedId\":\"flowUrgent\",\"required\":false},{\"filedName\":\"员工姓名\",\"filedId\":\"fullName\",\"required\":false},{\"filedName\":\"填表日期\",\"filedId\":\"fillFromDate\",\"required\":false},{\"filedName\":\"员工部门\",\"filedId\":\"department\",\"required\":false},{\"filedName\":\"员工职务\",\"filedId\":\"position\",\"required\":false},{\"filedName\":\"赏罚金额\",\"filedId\":\"rewardPun\",\"required\":false},{\"filedName\":\"赏罚原因\",\"filedId\":\"reason\",\"required\":false},{\"filedName\":\"流程单据\",\"filedId\":\"billNo\",\"required\":false}]', '', 0, '1', '0', '2022-02-21 15:56:59', 'admin', '2022-02-21 15:56:59', 'admin');
INSERT INTO `flow_engine` VALUES (1495670118185263106, 'debitBill', '借支申请', 0, 'costRelated', 1, NULL, '[]', 0, 'icon-ym icon-ym-wf-travelReimbursement', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"HqK0hr\",\"childNode\":{\"type\":\"approver\",\"content\":\"admin\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[\"admin\"],\"approverPos\":[],\"assigneeType\":6,\"formOperates\":[{\"id\":\"id\",\"name\":\"主键\",\"read\":true,\"write\":false},{\"id\":\"flowTitle\",\"name\":\"流程标题\",\"read\":true,\"write\":false},{\"id\":\"flowUrgent\",\"name\":\"紧急程度\",\"read\":true,\"write\":false},{\"id\":\"departmental\",\"name\":\"所属部门\",\"read\":true,\"write\":false},{\"id\":\"applyDate\",\"name\":\"申请日期\",\"read\":true,\"write\":false},{\"id\":\"staffName\",\"name\":\"员工姓名\",\"read\":true,\"write\":false},{\"id\":\"staffPost\",\"name\":\"员工职务\",\"read\":true,\"write\":false},{\"id\":\"staffId\",\"name\":\"员工编码\",\"read\":true,\"write\":false},{\"id\":\"loanMode\",\"name\":\"借款方式\",\"read\":true,\"write\":false},{\"id\":\"transferAccount\",\"name\":\"转账账户\",\"read\":true,\"write\":false},{\"id\":\"paymentMethod\",\"name\":\"支付方式\",\"read\":true,\"write\":false},{\"id\":\"amountDebit\",\"name\":\"借支金额\",\"read\":true,\"write\":false},{\"id\":\"repaymentBill\",\"name\":\"还款票据\",\"read\":true,\"write\":false},{\"id\":\"teachingDate\",\"name\":\"还款日期\",\"read\":true,\"write\":false},{\"id\":\"reason\",\"name\":\"借款原因\",\"read\":true,\"write\":false},{\"id\":\"billNo\",\"name\":\"流程单据\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"EnK0hr\",\"prevId\":\"HqK0hr\"}}', '[{\"filedName\":\"主键\",\"filedId\":\"id\",\"required\":false},{\"filedName\":\"流程标题\",\"filedId\":\"flowTitle\",\"required\":false},{\"filedName\":\"紧急程度\",\"filedId\":\"flowUrgent\",\"required\":false},{\"filedName\":\"所属部门\",\"filedId\":\"departmental\",\"required\":false},{\"filedName\":\"申请日期\",\"filedId\":\"applyDate\",\"required\":false},{\"filedName\":\"员工姓名\",\"filedId\":\"staffName\",\"required\":false},{\"filedName\":\"员工职务\",\"filedId\":\"staffPost\",\"required\":false},{\"filedName\":\"员工编码\",\"filedId\":\"staffId\",\"required\":false},{\"filedName\":\"借款方式\",\"filedId\":\"loanMode\",\"required\":false},{\"filedName\":\"转账账户\",\"filedId\":\"transferAccount\",\"required\":false},{\"filedName\":\"支付方式\",\"filedId\":\"paymentMethod\",\"required\":false},{\"filedName\":\"借支金额\",\"filedId\":\"amountDebit\",\"required\":false},{\"filedName\":\"还款票据\",\"filedId\":\"repaymentBill\",\"required\":false},{\"filedName\":\"还款日期\",\"filedId\":\"teachingDate\",\"required\":false},{\"filedName\":\"借款原因\",\"filedId\":\"reason\",\"required\":false},{\"filedName\":\"流程单据\",\"filedId\":\"billNo\",\"required\":false}]', '', 0, '1', '0', '2022-02-21 16:01:59', 'admin', '2022-02-21 16:01:59', 'admin');
INSERT INTO `flow_engine` VALUES (1495671157412159489, 'applyBanquet', '宴请申请', 0, 'costRelated', 1, NULL, '[]', 0, 'icon-ym icon-ym-wf-payDistribution', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"KEP1hr\",\"childNode\":{\"type\":\"approver\",\"content\":\"admin\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[\"admin\"],\"approverPos\":[],\"assigneeType\":6,\"formOperates\":[{\"id\":\"id\",\"name\":\"主键\",\"read\":true,\"write\":false},{\"id\":\"flowId\",\"name\":\"流程主键\",\"read\":true,\"write\":false},{\"id\":\"flowTitle\",\"name\":\"流程标题\",\"read\":true,\"write\":false},{\"id\":\"flowUrgent\",\"name\":\"紧急程度\",\"read\":true,\"write\":false},{\"id\":\"billNo\",\"name\":\"流程单据\",\"read\":true,\"write\":false},{\"id\":\"productName\",\"name\":\"产品名称\",\"read\":true,\"write\":false},{\"id\":\"production\",\"name\":\"生产车间\",\"read\":true,\"write\":false},{\"id\":\"compactor\",\"name\":\"编制人员\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"HGP1hr\",\"prevId\":\"KEP1hr\"}}', '[{\"filedName\":\"主键\",\"filedId\":\"id\",\"required\":false},{\"filedName\":\"流程主键\",\"filedId\":\"flowId\",\"required\":false},{\"filedName\":\"流程标题\",\"filedId\":\"flowTitle\",\"required\":false},{\"filedName\":\"紧急程度\",\"filedId\":\"flowUrgent\",\"required\":false},{\"filedName\":\"流程单据\",\"filedId\":\"billNo\",\"required\":false},{\"filedName\":\"产品名称\",\"filedId\":\"productName\",\"required\":false},{\"filedName\":\"生产车间\",\"filedId\":\"production\",\"required\":false},{\"filedName\":\"编制人员\",\"filedId\":\"compactor\",\"required\":false}]', '', 0, '1', '0', '2022-02-21 16:06:07', 'admin', '2022-02-21 16:06:07', 'admin');
INSERT INTO `flow_engine` VALUES (1495672498545057794, 'testpurchase', '采购申请', 0, 'warehouseManagement', 1, NULL, '[]', 0, 'icon-ym icon-ym-extend-truck', '#008cff', NULL, '{\"type\":\"start\",\"content\":\"所有人\",\"properties\":{\"title\":\"发起节点\",\"hasInitFunc\":false,\"initInterfaceUrl\":\"\",\"initInterfaceType\":\"POST\",\"hasEndFunc\":false,\"endInterfaceUrl\":\"\",\"endInterfaceType\":\"POST\",\"initiator\":[],\"initiatePos\":[]},\"nodeId\":\"Qpg2hr\",\"childNode\":{\"type\":\"approver\",\"content\":\"发起者本人\",\"properties\":{\"title\":\"审批节点\",\"approvers\":[],\"approverPos\":[],\"assigneeType\":3,\"formOperates\":[{\"id\":\"applyPerson\",\"name\":\"采购申请人\",\"read\":true,\"write\":false},{\"id\":\"sex\",\"name\":\"性别\",\"read\":true,\"write\":false},{\"id\":\"company\",\"name\":\"所属公司\",\"read\":true,\"write\":false},{\"id\":\"depname\",\"name\":\"所属部门\",\"read\":true,\"write\":false},{\"id\":\"post\",\"name\":\"所属岗位\",\"read\":true,\"write\":false},{\"id\":\"area\",\"name\":\"所属区域\",\"read\":true,\"write\":false},{\"id\":\"progress\",\"name\":\"进度\",\"read\":true,\"write\":false},{\"id\":\"luckyColor\",\"name\":\"幸运色\",\"read\":true,\"write\":false},{\"id\":\"score\",\"name\":\"评分\",\"read\":true,\"write\":false},{\"id\":\"purchaseDate\",\"name\":\"采购申请日期\",\"read\":true,\"write\":false},{\"id\":\"purchaseTime\",\"name\":\"采购申请时间\",\"read\":true,\"write\":false},{\"id\":\"purchaseNumber\",\"name\":\"采购单号\",\"read\":true,\"write\":false},{\"id\":\"reasons\",\"name\":\"采购原因\",\"read\":true,\"write\":false},{\"id\":\"createBy\",\"name\":\"创建用户\",\"read\":true,\"write\":false},{\"id\":\"createTime\",\"name\":\"创建时间\",\"read\":true,\"write\":false},{\"id\":\"updateTime\",\"name\":\"修改用户\",\"read\":true,\"write\":false},{\"id\":\"enabledFlag\",\"name\":\"有效标志\",\"read\":true,\"write\":false},{\"id\":\"filePath\",\"name\":\"附件\",\"read\":true,\"write\":false},{\"id\":\"photoPath\",\"name\":\"头像\",\"read\":true,\"write\":false},{\"id\":\"remarks\",\"name\":\"备注\",\"read\":true,\"write\":false},{\"id\":\"remarksRich\",\"name\":\"图文备注\",\"read\":true,\"write\":false},{\"id\":\"productCode\",\"name\":\"产品编码\",\"read\":true,\"write\":false},{\"id\":\"productName\",\"name\":\"产品名称\",\"read\":true,\"write\":false},{\"id\":\"productCategory\",\"name\":\"产品分类\",\"read\":true,\"write\":false},{\"id\":\"specifications\",\"name\":\"产品规格\",\"read\":true,\"write\":false},{\"id\":\"productUnit\",\"name\":\"产品单位\",\"read\":true,\"write\":false}],\"circulatePosition\":[],\"circulateUser\":[],\"progress\":\"50\",\"rejectStep\":\"0\",\"description\":\"\",\"hasApproverFunc\":false,\"approverInterfaceUrl\":\"\",\"approverInterfaceType\":\"GET\",\"hasRecallFunc\":false,\"recallInterfaceUrl\":\"\"},\"nodeId\":\"Svg2hr\",\"prevId\":\"Qpg2hr\"}}', '[{\"filedName\":\"采购申请人\",\"filedId\":\"applyPerson\",\"required\":false},{\"filedName\":\"性别\",\"filedId\":\"sex\",\"required\":false},{\"filedName\":\"所属公司\",\"filedId\":\"company\",\"required\":false},{\"filedName\":\"所属部门\",\"filedId\":\"depname\",\"required\":false},{\"filedName\":\"所属岗位\",\"filedId\":\"post\",\"required\":false},{\"filedName\":\"所属区域\",\"filedId\":\"area\",\"required\":false},{\"filedName\":\"进度\",\"filedId\":\"progress\",\"required\":false},{\"filedName\":\"幸运色\",\"filedId\":\"luckyColor\",\"required\":false},{\"filedName\":\"评分\",\"filedId\":\"score\",\"required\":false},{\"filedName\":\"采购申请日期\",\"filedId\":\"purchaseDate\",\"required\":false},{\"filedName\":\"采购申请时间\",\"filedId\":\"purchaseTime\",\"required\":false},{\"filedName\":\"采购单号\",\"filedId\":\"purchaseNumber\",\"required\":false},{\"filedName\":\"采购原因\",\"filedId\":\"reasons\",\"required\":false},{\"filedName\":\"创建用户\",\"filedId\":\"createBy\",\"required\":false},{\"filedName\":\"创建时间\",\"filedId\":\"createTime\",\"required\":false},{\"filedName\":\"修改用户\",\"filedId\":\"updateTime\",\"required\":false},{\"filedName\":\"有效标志\",\"filedId\":\"enabledFlag\",\"required\":false},{\"filedName\":\"附件\",\"filedId\":\"filePath\",\"required\":false},{\"filedName\":\"头像\",\"filedId\":\"photoPath\",\"required\":false},{\"filedName\":\"备注\",\"filedId\":\"remarks\",\"required\":false},{\"filedName\":\"图文备注\",\"filedId\":\"remarksRich\",\"required\":false},{\"filedName\":\"产品编码\",\"filedId\":\"productCode\",\"required\":false},{\"filedName\":\"产品名称\",\"filedId\":\"productName\",\"required\":false},{\"filedName\":\"产品分类\",\"filedId\":\"productCategory\",\"required\":false},{\"filedName\":\"产品规格\",\"filedId\":\"specifications\",\"required\":false},{\"filedName\":\"产品单位\",\"filedId\":\"productUnit\",\"required\":false}]', '', 0, '1', '0', '2022-02-21 16:11:27', 'admin', '2022-02-21 16:11:27', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
