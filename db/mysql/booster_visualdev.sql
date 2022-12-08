DROP DATABASE IF EXISTS `booster_visualdev`;

CREATE DATABASE  `booster_visualdev` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `booster_visualdev`;

-- ----------------------------
-- Table structure for base_visual_dev
-- ----------------------------
DROP TABLE IF EXISTS `base_visual_dev`;
CREATE TABLE `base_visual_dev`  (
    `id` bigint NOT NULL COMMENT '主键',
    `full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
    `encode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
    `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述',
    `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类（数据字典）',
    `state` int(11) NULL DEFAULT NULL COMMENT '状态(0-暂存（默认），1-发布)',
    `type` int(11) NULL DEFAULT NULL COMMENT '类型(1-Web在线开发,2-移动在线开发,3-流程表单,4-Web表单,5-移动表单)',
    `db_link_id` bigint NULL COMMENT 'db_link外键',
    `ref_tables` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '关联的表',
    `form_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单配置JSON',
    `column_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '列表配置JSON',
    `template_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '前端模板JSON',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '启用标志(0-默认，禁用，1-启用)',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '可视化开发功能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_visual_dev_model_data
-- ----------------------------
DROP TABLE IF EXISTS `base_visual_dev_model_data`;
CREATE TABLE `base_visual_dev_model_data`  (
    `id` bigint NOT NULL COMMENT '主键',
    `visual_dev_id` bigint NULL DEFAULT NULL COMMENT '功能ID',
    `parent_id` bigint NULL DEFAULT NULL COMMENT '区分主子表',
    `data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '数据包',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '启用标志(0-默认，禁用，1-启用)',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '0代码功能数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_portal
-- ----------------------------
DROP TABLE IF EXISTS `base_portal`;
CREATE TABLE `base_portal`  (
    `id` bigint NOT NULL COMMENT '主键',
    `full_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    `encode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
    `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类（数据字典）',
    `form_data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '表单配置JSON',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '启用标志(0-默认，禁用，1-启用)',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_data_interface
-- ----------------------------
DROP TABLE IF EXISTS `base_data_interface`;
CREATE TABLE `base_data_interface`  (
    `id` bigint NOT NULL COMMENT '主键ID',
    `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口名称',
    `encode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口编码',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述或说明',
    `category_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组ID',
    `path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口路径',
    `request_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求方式',
    `response_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '返回类型',
    `query` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '查询语句',
    `request_parameters` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数JSON',
    `response_parameters` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回参数JSON',
    `db_link_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据源id',
    `data_type` int(11) NULL DEFAULT NULL COMMENT '数据类型(1-动态数据SQL查询，2-静态数据)',
    `sort` int(11) NOT NULL COMMENT '排序码(默认0)',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '启用标志(0-默认，禁用，1-启用)',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志(默认0)',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户id',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据接口' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_db_link
-- ----------------------------
DROP TABLE IF EXISTS `base_db_link`;
CREATE TABLE `base_db_link`  (
    `id` bigint NOT NULL COMMENT '自然主键',
    `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接名称',
    `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
    `db_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接驱动',
    `host` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机地址',
    `port` int(11) NULL DEFAULT NULL COMMENT '端口',
    `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户',
    `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
    `service_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务名称',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据连接' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_visual_data
-- ----------------------------
DROP TABLE IF EXISTS `base_visual_data`;
CREATE TABLE `base_visual_data`  (
    `id` bigint NOT NULL COMMENT '主键ID',
    `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    `encode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
    `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述或说明',
    `category_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类ID',
    `screen_shot` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '大屏截图',
    `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问密码',
    `component` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '控件属性JSON包',
    `detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '大屏配置JSON包',
    `copy_id` bigint NULL DEFAULT NULL COMMENT '复制id',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '大屏数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_common_fields
-- ----------------------------
DROP TABLE IF EXISTS `base_common_fields`;
CREATE TABLE `base_common_fields`  (
    `id` bigint NOT NULL COMMENT '主键',
    `field_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
    `field_comment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列说明',
    `data_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
    `data_length` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '长度',
    `allow_null` int(11) NULL DEFAULT NULL COMMENT '是否为空（1允许，0不允许）',
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述说明',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '常用表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_visual_data_map
-- ----------------------------
DROP TABLE IF EXISTS `base_visual_data_map`;
CREATE TABLE `base_visual_data_map`  (
`id` bigint NOT NULL COMMENT '主键ID',
`full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
`encode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
`data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '地图数据',
`description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述或说明',
`sort` int(11) NULL DEFAULT NULL COMMENT '排序',
`enabled_flag` char(1) DEFAULT '1' COMMENT '有效标志',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
`update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '大屏地图' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
