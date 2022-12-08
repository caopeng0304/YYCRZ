DROP DATABASE IF EXISTS `booster_ureport`;

CREATE DATABASE  `booster_ureport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `booster_ureport`;

-- ----------------------------
-- Table structure for data_report
-- ----------------------------
DROP TABLE IF EXISTS `data_report`;
CREATE TABLE `data_report`  (
    `id` bigint NOT NULL COMMENT '	主键ID',
    `category_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组ID',
    `full_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报表名称',
    `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '报表内容',
    `encode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
    `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述或说明',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序码',
    `enabled_flag` char(1) DEFAULT '1' COMMENT '状态(0-禁用，1-启用)',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据报表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
