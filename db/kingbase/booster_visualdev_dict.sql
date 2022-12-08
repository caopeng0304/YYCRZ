-- ----------------------------
-- Records of 流程表单分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498485316906405888, 'codegen_flow_form_category', '流程表单分类', '流程表单分类', '0', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316910600192, 1498485316906405888, 'contractManage', '合同管理', 'codegen_flow_form_category', '合同管理', 0, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316910600193, 1498485316906405888, 'personnelManage', '人事管理', 'codegen_flow_form_category', '人事管理', 1, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316910600194, 1498485316906405888, 'routine', '日常工作', 'codegen_flow_form_category', '日常工作', 2, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316910600195, 1498485316906405888, 'administrationManage', '行政管理', 'codegen_flow_form_category', '行政管理', 3, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316910600196, 1498485316906405888, 'saleManage', '销售管理', 'codegen_flow_form_category', '销售管理', 4, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316914794496, 1498485316906405888, 'crm', 'CRM应用', 'codegen_flow_form_category', 'CRM应用', 5, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316914794497, 1498485316906405888, 'warehouseManagement', '仓库管理', 'codegen_flow_form_category', '仓库管理', 6, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316914794498, 1498485316906405888, 'documentManage', '公文管理', 'codegen_flow_form_category', '公文管理', 7, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485316914794499, 1498485316906405888, 'costRelated', '费用相关', 'codegen_flow_form_category', '费用相关', 8, '', '0', '2022-03-01 10:28:33', '2022-03-01 10:28:33');
COMMIT;

-- ----------------------------
-- Records of 移动表单分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498485576206692352, 'codegen_app_form_category', '移动表单分类', '移动表单分类', '0', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576206692353, 1498485576206692352, 'personnelManage', '人事管理', 'codegen_app_form_category', '人事管理', 0, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576206692354, 1498485576206692352, 'contractManage', '合同管理', 'codegen_app_form_category', '合同管理', 1, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576210886656, 1498485576206692352, 'costRelated', '费用相关', 'codegen_app_form_category', '费用相关', 2, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576210886657, 1498485576206692352, 'saleManage', '销售管理', 'codegen_app_form_category', '销售管理', 3, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576210886658, 1498485576206692352, 'crm', 'CRM应用', 'codegen_app_form_category', 'CRM应用', 4, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576210886659, 1498485576206692352, 'documentManage', '公文管理', 'codegen_app_form_category', '公文管理', 5, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576210886660, 1498485576206692352, 'administrationManage', '行政管理', 'codegen_app_form_category', '行政管理', 6, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576210886661, 1498485576206692352, 'warehouseManagement', '仓库管理', 'codegen_app_form_category', '仓库管理', 7, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485576215080960, 1498485576206692352, 'routine', '日常工作', 'codegen_app_form_category', '日常工作', 8, '', '0', '2022-03-01 10:29:35', '2022-03-01 10:29:35');
COMMIT;

-- ----------------------------
-- Records of 功能表单分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498485843522281472, 'codegen_web_form_category', '功能表单分类', '功能表单分类', '0', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843526475776, 1498485843522281472, 'costRelated', '费用相关', 'codegen_web_form_category', '费用相关', 0, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843526475777, 1498485843522281472, 'warehouseManagement', '仓库管理', 'codegen_web_form_category', '仓库管理', 1, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843526475778, 1498485843522281472, 'contractManage', '合同管理', 'codegen_web_form_category', '合同管理', 2, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843530670080, 1498485843522281472, 'administrationManage', '行政管理', 'codegen_web_form_category', '行政管理', 3, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843530670081, 1498485843522281472, 'personnelManage', '人事管理', 'codegen_web_form_category', '人事管理', 4, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843530670082, 1498485843522281472, 'documentManage', '公文管理', 'codegen_web_form_category', '公文管理', 5, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843534864384, 1498485843522281472, 'crm', 'CRM应用', 'codegen_web_form_category', 'CRM应用', 6, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843534864385, 1498485843522281472, 'saleManage', '销售管理', 'codegen_web_form_category', '销售管理', 7, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498485843534864386, 1498485843522281472, 'routine', '日常工作', 'codegen_web_form_category', '日常工作', 8, '', '0', '2022-03-01 10:30:38', '2022-03-01 10:30:38');
COMMIT;

-- ----------------------------
-- Records of 流程引擎类型
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498486107268575232, 'workflow_flow_engine_type', '流程引擎类型', '流程引擎类型', '0', '0', '2022-03-01 10:31:41', '2022-03-01 10:31:41');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486107272769536, 1498486107268575232, '2', '功能流程', 'workflow_flow_engine_type', '功能流程', 0, '', '0', '2022-03-01 10:31:41', '2022-03-01 10:31:41');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486107272769537, 1498486107268575232, '1', '发起流程', 'workflow_flow_engine_type', '发起流程', 1, '', '0', '2022-03-01 10:31:41', '2022-03-01 10:31:41');
COMMIT;

-- ----------------------------
-- Records of 流程设计分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498486315767418880, 'workflow_flow_design_category', '流程设计分类', '流程设计分类', '0', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315771613184, 1498486315767418880, 'costRelated', '费用相关', 'workflow_flow_design_category', '费用相关', 0, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315771613185, 1498486315767418880, 'warehouseManagement', '仓库管理', 'workflow_flow_design_category', '仓库管理', 1, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315771613186, 1498486315767418880, 'personnelManage', '人事管理', 'workflow_flow_design_category', '人事管理', 2, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315771613187, 1498486315767418880, 'projectManage', '项目管理', 'workflow_flow_design_category', '项目管理', 3, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807488, 1498486315767418880, 'saleManage', '销售管理', 'workflow_flow_design_category', '销售管理', 4, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807489, 1498486315767418880, 'routine', '日常工作', 'workflow_flow_design_category', '日常工作', 5, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807490, 1498486315767418880, 'serviceSupport', '服务支持', 'workflow_flow_design_category', '服务支持', 6, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807491, 1498486315767418880, 'assetsManage', '资产管理', 'workflow_flow_design_category', '资产管理', 7, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807492, 1498486315767418880, 'cheliang', '车辆管理', 'workflow_flow_design_category', '车辆管理', 8, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807493, 1498486315767418880, 'administrationManage', '行政管理', 'workflow_flow_design_category', '行政管理', 9, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807494, 1498486315767418880, 'contractManage', '合同管理', 'workflow_flow_design_category', '合同管理', 10, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807495, 1498486315767418880, 'documentManage', '公文管理', 'workflow_flow_design_category', '公文管理', 11, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486315775807496, 1498486315767418880, 'crm', 'CRM应用', 'workflow_flow_design_category', 'CRM应用', 12, '', '0', '2022-03-01 10:32:31', '2022-03-01 10:32:31');
COMMIT;

-- ----------------------------
-- Records of 流程表单类型
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498486578293002240, 'workflow_flow_form_type', '流程表单类型', '流程表单类型', '0', '0', '2022-03-01 10:33:33', '2022-03-01 10:33:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486578297196544, 1498486578293002240, '2', '自定义表单', 'workflow_flow_form_type', '自定义表单', 0, '', '0', '2022-03-01 10:33:33', '2022-03-01 10:33:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486578297196545, 1498486578293002240, '1', '系统表单', 'workflow_flow_form_type', '系统表单', 1, '', '0', '2022-03-01 10:33:33', '2022-03-01 10:33:33');
COMMIT;

-- ----------------------------
-- Records of 门户设计分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498486834154041344, 'visuadev_portal_design_category', '门户设计分类', '门户设计分类', '0', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486834154041345, 1498486834154041344, '2', '项目管理', 'visuadev_portal_design_category', '项目管理', 0, '', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486834154041346, 1498486834154041344, '1', '集团门户', 'visuadev_portal_design_category', '集团门户', 1, '', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486834154041347, 1498486834154041344, '5', '工程门户', 'visuadev_portal_design_category', '工程门户', 2, '', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486834158235648, 1498486834154041344, '6', '外部门户', 'visuadev_portal_design_category', '外部门户', 3, '', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486834158235649, 1498486834154041344, '4', '财务门户', 'visuadev_portal_design_category', '财务门户', 4, '', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486834158235650, 1498486834154041344, '0', '其他门户', 'visuadev_portal_design_category', '其他门户', 5, '', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498486834162429952, 1498486834154041344, '3', '营销门户', 'visuadev_portal_design_category', '营销门户', 6, '', '0', '2022-03-01 10:34:34', '2022-03-01 10:34:34');
COMMIT;

-- ----------------------------
-- Records of 移动设计分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498487079801819136, 'visuadev_app_design_category', '移动设计分类', '移动设计分类', '0', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079801819137, 1498487079801819136, 'warehouseManagement', '仓库管理', 'visuadev_app_design_category', '仓库管理', 0, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079806013440, 1498487079801819136, 'costRelated', '费用相关', 'visuadev_app_design_category', '费用相关', 1, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079806013441, 1498487079801819136, 'documentManage', '公文管理', 'visuadev_app_design_category', '公文管理', 2, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079806013442, 1498487079801819136, 'administrationManage', '行政管理', 'visuadev_app_design_category', '行政管理', 3, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079806013443, 1498487079801819136, 'crm', 'CRM应用', 'visuadev_app_design_category', 'CRM应用', 4, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079806013444, 1498487079801819136, 'personnelManage', '人事管理', 'visuadev_app_design_category', '人事管理', 5, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079810207744, 1498487079801819136, 'saleManage', '销售管理', 'visuadev_app_design_category', '销售管理', 6, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079810207745, 1498487079801819136, 'routine', '日常工作', 'visuadev_app_design_category', '日常工作', 7, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487079810207746, 1498487079801819136, 'contractManage', '合同管理', 'visuadev_app_design_category', '合同管理', 8, '', '0', '2022-03-01 10:35:33', '2022-03-01 10:35:33');
COMMIT;

-- ----------------------------
-- Records of 报表设计分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498487300732571648, 'visuadev_report_design_category', '报表设计分类', '报表设计分类', '0', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300732571649, 1498487300732571648, 'costRelated', '费用相关', 'visuadev_report_design_category', '费用相关', 0, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300736765952, 1498487300732571648, '1', '默认', 'visuadev_report_design_category', '默认', 1, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300736765953, 1498487300732571648, 'documentManage', '公文管理', 'visuadev_report_design_category', '公文管理', 2, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300736765954, 1498487300732571648, 'contractManage', '合同管理', 'visuadev_report_design_category', '合同管理', 3, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300736765955, 1498487300732571648, 'crm', 'CRM应用', 'visuadev_report_design_category', 'CRM应用', 4, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300736765956, 1498487300732571648, 'saleManage', '销售管理', 'visuadev_report_design_category', '销售管理', 5, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300740960256, 1498487300732571648, 'administrationManage', '行政管理', 'visuadev_report_design_category', '行政管理', 6, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300740960257, 1498487300732571648, 'personnelManage', '人事管理', 'visuadev_report_design_category', '人事管理', 7, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300740960258, 1498487300732571648, 'routine', '日常工作', 'visuadev_report_design_category', '日常工作', 8, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487300740960259, 1498487300732571648, 'warehouseManagement', '仓库管理', 'visuadev_report_design_category', '仓库管理', 9, '', '0', '2022-03-01 10:36:26', '2022-03-01 10:36:26');
COMMIT;

-- ----------------------------
-- Records of 功能设计分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498487546938204160, 'visuadev_web_design_category', '功能设计分类', '功能设计分类', '0', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546938204161, 1498487546938204160, 'personnelManage', '人事管理', 'visuadev_web_design_category', '人事管理', 0, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546942398464, 1498487546938204160, 'crm', 'CRM应用', 'visuadev_web_design_category', 'CRM应用', 1, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546942398465, 1498487546938204160, 'contractManage', '合同管理', 'visuadev_web_design_category', '合同管理', 2, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546942398466, 1498487546938204160, 'saleManage', '销售管理', 'visuadev_web_design_category', '销售管理', 3, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546942398467, 1498487546938204160, 'documentManage', '公文管理', 'visuadev_web_design_category', '公文管理', 4, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546942398468, 1498487546938204160, 'administrationManage', '行政管理', 'visuadev_web_design_category', '行政管理', 5, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546946592768, 1498487546938204160, 'costRelated', '费用相关', 'visuadev_web_design_category', '费用相关', 6, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546946592769, 1498487546938204160, 'routine', '日常工作', 'visuadev_web_design_category', '日常工作', 7, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487546946592770, 1498487546938204160, 'warehouseManagement', '仓库管理', 'visuadev_web_design_category', '仓库管理', 8, '', '0', '2022-03-01 10:37:24', '2022-03-01 10:37:24');
COMMIT;

-- ----------------------------
-- Records of 大屏设计分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498487773069910016, 'visuadev_screen_design_category', '大屏设计分类', '大屏设计分类', '0', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773069910017, 1498487773069910016, 'personnelManage', '人事管理', 'visuadev_screen_design_category', '人事管理', 0, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773069910018, 1498487773069910016, 'saleManage', '销售管理', 'visuadev_screen_design_category', '销售管理', 1, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773074104320, 1498487773069910016, 'routine', '日常工作', 'visuadev_screen_design_category', '日常工作', 2, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773074104321, 1498487773069910016, '1', '默认', 'visuadev_screen_design_category', '默认', 3, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773074104322, 1498487773069910016, 'crm', 'CRM应用', 'visuadev_screen_design_category', 'CRM应用', 4, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773074104323, 1498487773069910016, 'costRelated', '费用相关', 'visuadev_screen_design_category', '费用相关', 5, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773074104324, 1498487773069910016, 'test', '测试分类', 'visuadev_screen_design_category', '测试分类', 6, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773074104325, 1498487773069910016, 'administrationManage', '行政管理', 'visuadev_screen_design_category', '行政管理', 7, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773078298624, 1498487773069910016, 'warehouseManagement', '仓库管理', 'visuadev_screen_design_category', '仓库管理', 8, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773078298625, 1498487773069910016, 'contractManage', '合同管理', 'visuadev_screen_design_category', '合同管理', 9, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498487773078298626, 1498487773069910016, 'documentManage', '公文管理', 'visuadev_screen_design_category', '公文管理', 10, '', '0', '2022-03-01 10:38:18', '2022-03-01 10:38:18');
COMMIT;

-- ----------------------------
-- Records of 数据接口分类
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498488016582750208, 'data_interface_category', '数据接口分类', '数据接口分类', '0', '0', '2022-03-01 10:39:16', '2022-03-01 10:39:16');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498488016582750209, 1498488016582750208, '1', '默认', 'data_interface_category', '默认', 0, '', '0', '2022-03-01 10:39:16', '2022-03-01 10:39:16');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498488016582750210, 1498488016582750208, '2', '大屏图表', 'data_interface_category', '大屏图表', 1, '', '0', '2022-03-01 10:39:16', '2022-03-01 10:39:16');
COMMIT;

-- ----------------------------
-- Records of 支付方式代码
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict"("id", "type", "description", "remarks", "system_flag", "del_flag", "create_time", "update_time")
VALUES (1498488242056060928, 'pay_type_code', '支付方式代码', '支付方式代码', '0', '0', '2022-03-01 10:40:10', '2022-03-01 10:40:10');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498488242060255232, 1498488242056060928, '0', '微信支付', 'pay_type_code', '微信支付', 0, '', '0', '2022-03-01 10:40:10', '2022-03-01 10:40:10');
INSERT INTO "sys_dict_item"("id", "dict_id", "value", "label", "type", "description", "sort", "remarks", "del_flag", "create_time", "update_time")
VALUES (1498488242060255233, 1498488242056060928, '1', '支付宝支付', 'pay_type_code', '支付宝支付', 1, '', '0', '2022-03-01 10:40:10', '2022-03-01 10:40:10');
COMMIT;
