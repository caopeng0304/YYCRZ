DROP DATABASE IF EXISTS `booster_workflow`;

CREATE DATABASE  `booster_workflow` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `booster_workflow`;

-- ----------------------------
-- Table structure for flow_delegate
-- ----------------------------
DROP TABLE IF EXISTS `flow_delegate`;
CREATE TABLE `flow_delegate`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `to_user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被委托人',
  `to_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被委托人',
  `flow_id` bigint NULL DEFAULT NULL COMMENT '委托流程Id',
  `flow_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委托流程名称',
  `flow_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程分类',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程委托' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_engine
-- ----------------------------
DROP TABLE IF EXISTS `flow_engine`;
CREATE TABLE `flow_engine`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `encode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程编码',
  `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '流程类型 0发起流程 1功能流程',
  `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程分类',
  `form_type` int(11) NULL DEFAULT NULL COMMENT '表单分类 1系统表单 2自定义表单',
  `form` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程表单',
  `ref_tables` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '关联的表',
  `visible_type` int(11) NULL DEFAULT NULL COMMENT '可见类型',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `icon_background` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标背景色',
  `version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程版本',
  `flow_template_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '流程模板',
  `form_template_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '表单模板',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
  `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程引擎' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_engine_visible
-- ----------------------------
DROP TABLE IF EXISTS `flow_engine_visible`;
CREATE TABLE `flow_engine_visible`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
  `operator_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办类型',
  `operator_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办主键',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
  `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程可见' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_task
-- ----------------------------
DROP TABLE IF EXISTS `flow_task`;
CREATE TABLE `flow_task`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `process_id` bigint NULL DEFAULT NULL COMMENT '实例进程',
  `encode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编号',
  `full_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务标题',
  `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
  `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
  `flow_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程编号',
  `flow_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `flow_type` int(11) NULL DEFAULT NULL COMMENT '流程分类',
  `flow_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程类型',
  `flow_form` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '流程表单',
  `flow_form_content_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '表单内容',
  `flow_template_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '流程模板',
  `flow_version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程版本',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `this_step` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前步骤',
  `this_step_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前步骤Id',
  `grade` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重要等级',
  `status` int(11) NULL DEFAULT NULL COMMENT '任务状态',
  `completion` int(11) NULL DEFAULT NULL COMMENT '完成情况',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
  `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_task_circulate
-- ----------------------------
DROP TABLE IF EXISTS `flow_task_circulate`;
CREATE TABLE `flow_task_circulate`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `object_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型',
  `object_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象主键',
  `node_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点编号',
  `node_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `task_node_id` bigint NULL DEFAULT NULL COMMENT '节点主键',
  `task_id` bigint NULL DEFAULT NULL COMMENT '任务主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程传阅' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_task_node
-- ----------------------------
DROP TABLE IF EXISTS `flow_task_node`;
CREATE TABLE `flow_task_node`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `node_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点编号',
  `node_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `node_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点类型',
  `node_property_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '节点属性Json',
  `node_up` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上一节点',
  `node_next` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下一节点',
  `task_id` bigint NULL DEFAULT NULL COMMENT '任务主键',
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `completion` int(11) NULL DEFAULT NULL COMMENT '是否完成',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程节点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_task_operator
-- ----------------------------
DROP TABLE IF EXISTS `flow_task_operator`;
CREATE TABLE `flow_task_operator`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `handle_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办对象',
  `handle_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办主键',
  `handle_status` int(11) NULL DEFAULT NULL COMMENT '处理状态',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `node_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点编号',
  `node_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `task_node_id` bigint NULL DEFAULT NULL COMMENT '节点主键',
  `task_id` bigint NULL DEFAULT NULL COMMENT '任务主键',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点类型',
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `completion` int(11) NULL DEFAULT NULL COMMENT '是否完成',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程经办' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_task_operator_record
-- ----------------------------
DROP TABLE IF EXISTS `flow_task_operator_record`;
CREATE TABLE `flow_task_operator_record`  (
  `id` bigint NOT NULL COMMENT '自然主键',
  `node_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点编号',
  `node_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `handle_status` int(11) NULL DEFAULT NULL COMMENT '经办状态',
  `handle_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经办人员',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '经办时间',
  `handle_opinion` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '经办理由',
  `task_operator_id` bigint NULL DEFAULT NULL COMMENT '经办主键',
  `task_node_id` bigint NULL DEFAULT NULL COMMENT '节点主键',
  `task_id` bigint NULL DEFAULT NULL COMMENT '任务主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程经办记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_apply_banquet
-- ----------------------------
DROP TABLE IF EXISTS `form_apply_banquet`;
CREATE TABLE `form_apply_banquet`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人员',
    `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属职务',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `banquet_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宴请人数',
    `banquet_people` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '宴请人员',
    `total` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员总数',
    `place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宴请地点',
    `expected_cost` decimal(18, 2) NULL DEFAULT NULL COMMENT '预计费用',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '宴请申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_apply_deliver_goods
-- ----------------------------
DROP TABLE IF EXISTS `form_apply_deliver_goods`;
CREATE TABLE `form_apply_deliver_goods`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `customer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
    `contacts` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
    `contact_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `customer_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户地址',
    `goods_belonged` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货品所属',
    `invoice_date` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
    `freight_company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货运公司',
    `delivery_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货类型',
    `transport_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货运单号',
    `freight_charges` decimal(18, 2) NULL DEFAULT NULL COMMENT '货运费',
    `cargo_insurance` decimal(18, 2) NULL DEFAULT NULL COMMENT '保险金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `invoice_value` decimal(18, 2) NULL DEFAULT NULL COMMENT '发货金额',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发货申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_apply_deliver_goods_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_apply_deliver_goods_entry`;
CREATE TABLE `form_apply_deliver_goods_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `invoice_id` bigint NULL DEFAULT NULL COMMENT '发货主键',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位',
    `qty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发货明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_apply_meeting
-- ----------------------------
DROP TABLE IF EXISTS `form_apply_meeting`;
CREATE TABLE `form_apply_meeting`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属职务',
    `conference_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议名称',
    `conference_theme` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议主题',
    `conference_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议类型',
    `estimate_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预计人数',
    `conference_room` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议室',
    `administrator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理人',
    `look_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '查看人',
    `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纪要员',
    `attendees` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出席人',
    `apply_material` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请材料',
    `estimated_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '预计金额',
    `other_attendee` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他出席人',
    `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `describe` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '会议描述',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会议申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_archival_borrow
-- ----------------------------
DROP TABLE IF EXISTS `form_archival_borrow`;
CREATE TABLE `form_archival_borrow`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `borrowing_department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借阅部门',
    `archives_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案名称',
    `Archival_Attributes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案属性',
    `borrow_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借阅方式',
    `apply_reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '申请原因',
    `archives_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案编号',
    `borrowing_date` datetime(0) NULL DEFAULT NULL COMMENT '借阅时间',
    `return_date` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '档案借阅申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_articles_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `form_articles_warehouse`;
CREATE TABLE `form_articles_warehouse`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
    `articles` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用品库存',
    `classification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用品分类',
    `articles_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用品编号',
    `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
    `estimate_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `apply_reasons` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '申请原因',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用品入库申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_batch_pack
-- ----------------------------
DROP TABLE IF EXISTS `form_batch_pack`;
CREATE TABLE `form_batch_pack`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
    `production` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产车间',
    `compactor` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编制人员',
    `compactor_date` datetime(0) NULL DEFAULT NULL COMMENT '编制日期',
    `standard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品规格',
    `warehouse_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库序号',
    `production_quty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批产数量',
    `operation_date` datetime(0) NULL DEFAULT NULL COMMENT '操作日期',
    `regulations` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工艺规程',
    `packing` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包装规格',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批包装指令' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_batch_table
-- ----------------------------
DROP TABLE IF EXISTS `form_batch_table`;
CREATE TABLE `form_batch_table`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `file_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件标题',
    `drafted_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主办单位',
    `file_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件编号',
    `send_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发往单位',
    `typing` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打字',
    `writing_date` datetime(0) NULL DEFAULT NULL COMMENT '发文日期',
    `share_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '份数',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行文呈批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_con_billing
-- ----------------------------
DROP TABLE IF EXISTS `form_con_billing`;
CREATE TABLE `form_con_billing`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `drawer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票人',
    `bill_date` datetime(0) NULL DEFAULT NULL COMMENT '开票日期',
    `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
    `con_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联名称',
    `bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
    `amount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户账号',
    `bill_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '开票金额',
    `tax_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税号',
    `invoice_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票号',
    `invo_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票地址',
    `pay_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '付款金额',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同开票流程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_contract_approval
-- ----------------------------
DROP TABLE IF EXISTS `form_contract_approval`;
CREATE TABLE `form_contract_approval`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `first_party_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '甲方单位',
    `second_party_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乙方单位',
    `first_party_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '甲方负责人',
    `second_party_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乙方负责人',
    `first_party_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '甲方联系方式',
    `second_party_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乙方联系方式',
    `contract_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同名称',
    `contract_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同分类',
    `contract_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同类型',
    `contract_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
    `business_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务人员',
    `income_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '收入金额',
    `input_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填写人员',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `primary_coverage` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '主要内容',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `signing_date` datetime(0) NULL DEFAULT NULL COMMENT '签约时间',
    `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同审批' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_contract_approval_sheet
-- ----------------------------
DROP TABLE IF EXISTS `form_contract_approval_sheet`;
CREATE TABLE `form_contract_approval_sheet`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `contract_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号支出',
    `contract_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同号',
    `first_party` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签署方(甲方)',
    `second_party` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乙方',
    `contract_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同名称',
    `contract_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同类型',
    `person_charge` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合作负责人',
    `lead_department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
    `sign_area` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签订地区',
    `income_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '收入金额',
    `total_expenditure` decimal(18, 2) NULL DEFAULT NULL COMMENT '支出总额',
    `contract_period` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同期限',
    `payment_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
    `budgetary_approval` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算批付',
    `start_contract_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_contract_date` datetime(0) NULL DEFAULT NULL,
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `contract_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容简要',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同申请单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_debit_bill
-- ----------------------------
DROP TABLE IF EXISTS `form_debit_bill`;
CREATE TABLE `form_debit_bill`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `departmental` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作部门',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `staff_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
    `staff_post` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工职务',
    `staff_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',
    `loan_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借款方式',
    `amount_debit` decimal(18, 2) NULL DEFAULT NULL COMMENT '借支金额',
    `transfer_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转账账户',
    `repayment_bill` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '还款票据',
    `teaching_date` datetime(0) NULL DEFAULT NULL COMMENT '还款日期',
    `payment_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
    `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '借款原因',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借支单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_document_approval
-- ----------------------------
DROP TABLE IF EXISTS `form_document_approval`;
CREATE TABLE `form_document_approval`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
    `drafted_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拟稿人',
    `service_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文单位',
    `file_preparation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件拟办',
    `file_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件编号',
    `receipt_date` datetime(0) NULL DEFAULT NULL COMMENT '收文日期',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `modify_opinion` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '修改意见',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件签批意见表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_document_signing
-- ----------------------------
DROP TABLE IF EXISTS `form_document_signing`;
CREATE TABLE `form_document_signing`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
    `file_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件编号',
    `drafted_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拟稿人',
    `reader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签阅人',
    `file_preparation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件拟办',
    `check_date` datetime(0) NULL DEFAULT NULL COMMENT '签阅时间',
    `publication_date` datetime(0) NULL DEFAULT NULL COMMENT '发稿日期',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `document_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文件内容',
    `advice_column` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '建议栏',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件签阅表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_expense_expenditure
-- ----------------------------
DROP TABLE IF EXISTS `form_expense_expenditure`;
CREATE TABLE `form_expense_expenditure`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '流程等级',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人员',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在部门',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `contract_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
    `non_contract` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '非合同支出',
    `account_opening_bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
    `bank_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
    `open_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户姓名',
    `total` decimal(18, 2) NULL DEFAULT NULL COMMENT '合计费用',
    `payment_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
    `amount_payment` decimal(18, 2) NULL DEFAULT NULL COMMENT '支付金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '费用支出单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_finished_product
-- ----------------------------
DROP TABLE IF EXISTS `form_finished_product`;
CREATE TABLE `form_finished_product`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `warehouse_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库人',
    `warehouse` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `reservoir_date` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '成品入库单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_finished_product_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_finished_product_entry`;
CREATE TABLE `form_finished_product_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `warehouse_id` bigint NULL DEFAULT NULL COMMENT '入库单号',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
    `qty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '成品入库单明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_income_recognition
-- ----------------------------
DROP TABLE IF EXISTS `form_income_recognition`;
CREATE TABLE `form_income_recognition`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主题',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `settlement_month` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结算月份',
    `customer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
    `contract_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
    `total_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '合同金额',
    `money_bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到款银行',
    `actual_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '到款金额',
    `contact_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
    `contact_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `contact_qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系QQ',
    `unpaid_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '未付金额',
    `amount_paid` decimal(18, 2) NULL DEFAULT NULL COMMENT '已付金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `payment_date` datetime(0) NULL DEFAULT NULL COMMENT '到款日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收入确认分析表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_leave_apply
-- ----------------------------
DROP TABLE IF EXISTS `form_leave_apply`;
CREATE TABLE `form_leave_apply`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据编号',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人员',
    `apply_dept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请部门',
    `apply_post` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请职位',
    `leave_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假类别',
    `leave_reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请假原因',
    `leave_day_count` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假天数',
    `leave_hour` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假小时',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `leave_start_time` datetime(0) NULL DEFAULT NULL COMMENT '请假时间',
    `leave_end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程表单【请假申请】' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_letter_service
-- ----------------------------
DROP TABLE IF EXISTS `form_letter_service`;
CREATE TABLE `form_letter_service`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `host_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主办单位',
    `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文标题',
    `issued_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文字号',
    `writing_date` datetime(0) NULL DEFAULT NULL COMMENT '发文日期',
    `share_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '份数',
    `main_delivery` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主送',
    `Copy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄送',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发文单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_material_requisition
-- ----------------------------
DROP TABLE IF EXISTS `form_material_requisition`;
CREATE TABLE `form_material_requisition`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `lead_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领料人',
    `lead_department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领料部门',
    `lead_date` datetime(0) NULL DEFAULT NULL COMMENT '领料日期',
    `warehouse` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '领料单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_material_requisition_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_material_requisition_entry`;
CREATE TABLE `form_material_requisition_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `lead_id` bigint NULL DEFAULT NULL COMMENT '领料主键',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
    `material_demand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需数量',
    `proportioning` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '领料明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_monthly_report
-- ----------------------------
DROP TABLE IF EXISTS `form_monthly_report`;
CREATE TABLE `form_monthly_report`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
    `apply_dept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
    `apply_post` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属职务',
    `plan_end_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
    `overall_evaluate` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '总体评价',
    `np_work_matter` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作事项',
    `np_finish_time` datetime(0) NULL DEFAULT NULL COMMENT '次月日期',
    `np_finish_method` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '次月目标',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '月工作总结' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_office_supplies
-- ----------------------------
DROP TABLE IF EXISTS `form_office_supplies`;
CREATE TABLE `form_office_supplies`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请部门',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
    `use_stock` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领用仓库',
    `classification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用品分类',
    `articles_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用品名称',
    `articles_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用品数量',
    `articles_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用品编号',
    `apply_reasons` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '申请原因',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '领用办公用品申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_out_bound_order
-- ----------------------------
DROP TABLE IF EXISTS `form_out_bound_order`;
CREATE TABLE `form_out_bound_order`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `customer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
    `warehouse` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库',
    `out_storage` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库人',
    `business_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务人员',
    `business_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
    `out_bound_date` datetime(0) NULL DEFAULT NULL COMMENT '出库日期',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_out_bound_order_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_out_bound_order_entry`;
CREATE TABLE `form_out_bound_order_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `out_bound_id` bigint NULL DEFAULT NULL COMMENT '出库单号',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
    `qty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库单明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_out_going_apply
-- ----------------------------
DROP TABLE IF EXISTS `form_out_going_apply`;
CREATE TABLE `form_out_going_apply`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在部门',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `out_going_total` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外出总计',
    `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `destination` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目的地',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `out_going_cause` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '外出事由',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '外出申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_pay_distribution
-- ----------------------------
DROP TABLE IF EXISTS `form_pay_distribution`;
CREATE TABLE `form_pay_distribution`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `month` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属月份',
    `issuing_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发放单位',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工部门',
    `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工职位',
    `base_salary` decimal(18, 2) NULL DEFAULT NULL COMMENT '基本薪资',
    `actual_attendance` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出勤天数',
    `allowance` decimal(18, 2) NULL DEFAULT NULL COMMENT '员工津贴',
    `income_tax` decimal(18, 2) NULL DEFAULT NULL COMMENT '所得税',
    `insurance` decimal(18, 2) NULL DEFAULT NULL COMMENT '员工保险',
    `performance` decimal(18, 2) NULL DEFAULT NULL COMMENT '员工绩效',
    `overtime_pay` decimal(18, 2) NULL DEFAULT NULL COMMENT '加班费用',
    `gross_pay` decimal(18, 2) NULL DEFAULT NULL COMMENT '应发工资',
    `payroll` decimal(18, 2) NULL DEFAULT NULL COMMENT '实发工资',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '薪酬发放' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_payment_apply
-- ----------------------------
DROP TABLE IF EXISTS `form_payment_apply`;
CREATE TABLE `form_payment_apply`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `departmental` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请部门',
    `purpose_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用途名称',
    `project_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目类别',
    `project_leader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目负责人',
    `opening_bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
    `beneficiary_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款账号',
    `receivable_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
    `payment_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款单位',
    `apply_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '申请金额',
    `settlement_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结算方式',
    `payment_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款类型',
    `amount_paid` decimal(18, 2) NULL DEFAULT NULL COMMENT '付款金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '付款申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_post_batch_table
-- ----------------------------
DROP TABLE IF EXISTS `form_post_batch_table`;
CREATE TABLE `form_post_batch_table`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `file_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件标题',
    `drafted_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主办单位',
    `send_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发往单位',
    `writing_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文编号',
    `writing_date` datetime(0) NULL DEFAULT NULL COMMENT '发文日期',
    `share_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '份数',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发文呈批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_procurement_material
-- ----------------------------
DROP TABLE IF EXISTS `form_procurement_material`;
CREATE TABLE `form_procurement_material`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `departmental` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请部门',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `purchase_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购单位',
    `delivery_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货方式',
    `delivery_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货地址',
    `payment_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
    `payment_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '付款金额',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用途原因',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购原材料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_procurement_material_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_procurement_material_entry`;
CREATE TABLE `form_procurement_material_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `procurement_id` bigint NULL DEFAULT NULL COMMENT '采购主键',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位',
    `qty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_purchase_list
-- ----------------------------
DROP TABLE IF EXISTS `form_purchase_list`;
CREATE TABLE `form_purchase_list`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `departmental` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在部门',
    `vendor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
    `buyer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购人员',
    `purchase_date` datetime(0) NULL DEFAULT NULL COMMENT '采购日期',
    `warehouse` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库',
    `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
    `payment_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
    `payment_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '支付总额',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用途原因',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日常物品采购清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_purchase_list_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_purchase_list_entry`;
CREATE TABLE `form_purchase_list_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `purchase_id` bigint NULL DEFAULT NULL COMMENT '采购主键',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
    `qty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日常物品采购清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_quotation_approval
-- ----------------------------
DROP TABLE IF EXISTS `form_quotation_approval`;
CREATE TABLE `form_quotation_approval`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '流程等级',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `writer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报人',
    `write_date` datetime(0) NULL DEFAULT NULL COMMENT '填表日期',
    `customer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
    `quotation_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
    `partner_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合作人名',
    `standard_file` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板参考',
    `describe_situation` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '情况描述',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报价审批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_receipt_processing
-- ----------------------------
DROP TABLE IF EXISTS `form_receipt_processing`;
CREATE TABLE `form_receipt_processing`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `file_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件标题',
    `communication_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来文单位',
    `letter_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来文字号',
    `receipt_date` datetime(0) NULL DEFAULT NULL COMMENT '收文日期',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收文处理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_receipt_sign
-- ----------------------------
DROP TABLE IF EXISTS `form_receipt_sign`;
CREATE TABLE `form_receipt_sign`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `receipt_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收文标题',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收文部门',
    `collector` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收文人',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `receipt_paper` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '收文简述',
    `receipt_date` datetime(0) NULL DEFAULT NULL COMMENT '收文日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收文签呈单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_reward_punishment
-- ----------------------------
DROP TABLE IF EXISTS `form_reward_punishment`;
CREATE TABLE `form_reward_punishment`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
    `fill_from_date` datetime(0) NULL DEFAULT NULL COMMENT '填表日期',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工部门',
    `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工职位',
    `reward_pun` decimal(18, 2) NULL DEFAULT NULL COMMENT '赏罚金额',
    `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '赏罚原因',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行政赏罚单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_sales_order
-- ----------------------------
DROP TABLE IF EXISTS `form_sales_order`;
CREATE TABLE `form_sales_order`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '流程等级',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `sales_man` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务人员',
    `customer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
    `contacts` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
    `contact_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `customer_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户地址',
    `ticket_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票编号',
    `invoice_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票类型',
    `payment_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
    `payment_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '付款金额',
    `sales_date` datetime(0) NULL DEFAULT NULL COMMENT '销售日期',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `ticket_date` datetime(0) NULL DEFAULT NULL COMMENT '开票日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_sales_order_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_sales_order_entry`;
CREATE TABLE `form_sales_order_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `sales_order_id` bigint NULL DEFAULT NULL COMMENT '订单主键',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位',
    `qty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `sort` int(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售订单明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_sales_support
-- ----------------------------
DROP TABLE IF EXISTS `form_sales_support`;
CREATE TABLE `form_sales_support`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `apply_dept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请部门',
    `customer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关客户',
    `project` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关项目',
    `p_sale_sup_info` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售前支持',
    `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
    `p_sale_sup_days` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支持天数',
    `p_sale_pre_days` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '准备天数',
    `consul_manager` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构咨询',
    `p_sal_sup_consul` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售前顾问',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    `sal_sup_conclusion` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '销售总结',
    `consult_result` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '交付说明',
    `i_evaluation` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '咨询评价',
    `conclusion` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '发起人总结',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售支持表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_staff_overtime
-- ----------------------------
DROP TABLE IF EXISTS `form_staff_overtime`;
CREATE TABLE `form_staff_overtime`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程标题',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请部门',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `total_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总计时间',
    `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记入类别',
    `cause` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '加班事由',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工加班申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_supplement_card
-- ----------------------------
DROP TABLE IF EXISTS `form_supplement_card`;
CREATE TABLE `form_supplement_card`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在部门',
    `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在职务',
    `witness` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证明人',
    `supplement_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '补卡次数',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
    `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '补卡申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_travel_apply
-- ----------------------------
DROP TABLE IF EXISTS `form_travel_apply`;
CREATE TABLE `form_travel_apply`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `travel_man` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出差人',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
    `departmental` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
    `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属职务',
    `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
    `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
    `start_place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '起始地点',
    `destination` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目的地',
    `prepaid_travel` decimal(18, 2) NULL DEFAULT NULL COMMENT '预支旅费',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出差预支申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_travel_reimbursement
-- ----------------------------
DROP TABLE IF EXISTS `form_travel_reimbursement`;
CREATE TABLE `form_travel_reimbursement`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '流程等级',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `apply_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
    `departmental` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请部门',
    `bills_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '票据数',
    `business_mission` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '出差任务',
    `setout_date` datetime(0) NULL DEFAULT NULL COMMENT '出发日期',
    `return_date` datetime(0) NULL DEFAULT NULL COMMENT '回归日期',
    `destination` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达地',
    `plane_ticket` decimal(18, 2) NULL DEFAULT NULL COMMENT '机票费',
    `fare` decimal(18, 2) NULL DEFAULT NULL COMMENT '车船费',
    `get_accommodation` decimal(18, 2) NULL DEFAULT NULL COMMENT '住宿费用',
    `travel_allowance` decimal(18, 2) NULL DEFAULT NULL COMMENT '出差补助',
    `other` decimal(18, 2) NULL DEFAULT NULL COMMENT '其他费用',
    `total` decimal(18, 2) NULL DEFAULT NULL COMMENT '合计',
    `reimbursement_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '报销金额',
    `loan_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '借款金额',
    `sum_of_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '补找金额',
    `traveler_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `vehicle_mileage` decimal(18, 2) NULL DEFAULT NULL COMMENT '车辆里程',
    `road_fee` decimal(18, 2) NULL DEFAULT NULL COMMENT '过路费',
    `parking_rate` decimal(18, 2) NULL DEFAULT NULL COMMENT '停车费',
    `meal_allowance` decimal(18, 2) NULL DEFAULT NULL COMMENT '餐补费用',
    `breakdown_fee` decimal(18, 2) NULL DEFAULT NULL COMMENT '故障报修费',
    `reimbursement_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报销编号',
    `rail_transit` decimal(18, 2) NULL DEFAULT NULL COMMENT '轨道交通费',
    `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '差旅报销申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_vehicle_apply
-- ----------------------------
DROP TABLE IF EXISTS `form_vehicle_apply`;
CREATE TABLE `form_vehicle_apply`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `car_man` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用车人',
    `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在部门',
    `plate_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
    `destination` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目的地',
    `road_fee` decimal(18, 2) NULL DEFAULT NULL COMMENT '路费金额',
    `kilometre_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公里数',
    `entourage` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '随行人数',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
    `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_violation_handling
-- ----------------------------
DROP TABLE IF EXISTS `form_violation_handling`;
CREATE TABLE `form_violation_handling`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `plate_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
    `driver` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驾驶人',
    `leading_official` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
    `violation_site` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '违章地点',
    `violation_behavior` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '违章行为',
    `deduction` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '违章扣分',
    `amount_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '违章罚款',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `notice_date` datetime(0) NULL DEFAULT NULL COMMENT '通知日期',
    `peccancy_date` datetime(0) NULL DEFAULT NULL COMMENT '违章日期',
    `limit_date` datetime(0) NULL DEFAULT NULL COMMENT '限处理日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '违章处理申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_warehouse_receipt
-- ----------------------------
DROP TABLE IF EXISTS `form_warehouse_receipt`;
CREATE TABLE `form_warehouse_receipt`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `supplier_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
    `contact_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `warehouse_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库类别',
    `warehouse` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库',
    `warehouse_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库人',
    `delivery_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单号',
    `warehouse_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库单号',
    `warehouse_date` datetime(0) NULL DEFAULT NULL COMMENT '入库日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_warehouse_receipt_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_warehouse_receipt_entry`;
CREATE TABLE `form_warehouse_receipt_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `warehouse_id` bigint NULL DEFAULT NULL COMMENT '入库主键',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
    `qty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库单明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_work_contact_sheet
-- ----------------------------
DROP TABLE IF EXISTS `form_work_contact_sheet`;
CREATE TABLE `form_work_contact_sheet`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `flow_id` bigint NULL DEFAULT NULL COMMENT '流程主键',
    `flow_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程标题',
    `flow_urgent` int(11) NULL DEFAULT NULL COMMENT '紧急程度',
    `bill_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程单据',
    `draw_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件人',
    `issuing_department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件部门',
    `service_department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件部门',
    `recipients` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
    `coordination` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '协调事项',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '相关附件',
    `to_date` datetime(0) NULL DEFAULT NULL COMMENT '发件日期',
    `collection_date` datetime(0) NULL DEFAULT NULL COMMENT '收件日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作联系单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_order_receivable
-- ----------------------------
DROP TABLE IF EXISTS `form_order_receivable`;
CREATE TABLE `form_order_receivable`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `order_id` bigint NULL DEFAULT NULL COMMENT '订单主键',
    `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '收款摘要',
    `receivable_date` datetime(0) NULL DEFAULT NULL COMMENT '收款日期',
    `receivable_rate` decimal(18, 2) NULL DEFAULT NULL COMMENT '收款比率',
    `receivable_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '收款金额',
    `receivable_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方式',
    `receivable_state` int(11) NULL DEFAULT NULL COMMENT '收款状态',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单收款' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_order
-- ----------------------------
DROP TABLE IF EXISTS `form_order`;
CREATE TABLE `form_order`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `customer_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户Id',
    `customer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
    `sales_man_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务员Id',
    `sales_man_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务员',
    `order_date` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
    `order_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
    `transport_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运输方式',
    `delivery_date` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
    `delivery_address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '发货地址',
    `payment_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
    `receivable_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '应收金额',
    `earnest_rate` decimal(18, 2) NULL DEFAULT NULL COMMENT '定金比率',
    `prepay_earnest` decimal(18, 2) NULL DEFAULT NULL COMMENT '预付定金',
    `current_state` int(11) NULL DEFAULT NULL COMMENT '当前状态',
    `file_json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '附件信息',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for form_order_entry
-- ----------------------------
DROP TABLE IF EXISTS `form_order_entry`;
CREATE TABLE `form_order_entry`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `order_id` bigint NULL DEFAULT NULL COMMENT '订单主键',
    `goods_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品Id',
    `goods_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编码',
    `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `specifications` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
    `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
    `qty` decimal(18, 2) NULL DEFAULT NULL COMMENT '数量',
    `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '单价',
    `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
    `discount` decimal(18, 2) NULL DEFAULT NULL COMMENT '折扣%',
    `cess` decimal(18, 2) NULL DEFAULT NULL COMMENT '税率%',
    `actual_price` decimal(18, 2) NULL DEFAULT NULL COMMENT '实际单价',
    `actual_amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '实际金额',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单明细' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
