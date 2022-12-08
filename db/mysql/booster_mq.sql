/*
 Navicat Premium Data Transfer

 Source Server         : 测试环境
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 192.168.2.140:13306
 Source Schema         : booster_mq

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 02/09/2022 17:37:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `consumeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消费者名称',
  `topId` int(0) NULL DEFAULT NULL COMMENT '主题id',
  `callbackurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调url',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `state` int(0) NULL DEFAULT 2 COMMENT '状态，0：删除，1：停用，2：正常使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer
-- ----------------------------
INSERT INTO `consumer` VALUES (8, 'test-c4', 23, 'http://c4', '2022-03-22 10:46:52', 2);
INSERT INTO `consumer` VALUES (9, 'test-c4', 24, 'http://c4', '2022-03-22 10:46:57', 2);
INSERT INTO `consumer` VALUES (10, 'test-c5', 23, 'http://c5', '2022-03-22 10:47:02', 2);

-- ----------------------------
-- Table structure for producer
-- ----------------------------
DROP TABLE IF EXISTS `producer`;
CREATE TABLE `producer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `produceName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产者名称',
  `topIds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题id,用逗号分隔',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `state` int(0) NULL DEFAULT 2 COMMENT '状态，0：删除，1：停用，2：正常使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of producer
-- ----------------------------
INSERT INTO `producer` VALUES (14, 'test-p1', '1,2,3', '2022-03-17 11:09:57', 2);
INSERT INTO `producer` VALUES (15, 'test-p2', '1,3,4,5', '2022-03-17 10:42:10', 2);
INSERT INTO `producer` VALUES (16, 'test-p1', '1,2,3', '2022-03-17 11:16:57', 2);
INSERT INTO `producer` VALUES (17, 'test-p1', '1,2,3', '2022-03-17 11:20:35', 2);
INSERT INTO `producer` VALUES (18, 'test-p1', '1,2,3', '2022-03-17 11:26:47', 2);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `topicname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题名称',
  `create_Time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `state` int(0) NULL DEFAULT 2 COMMENT '状态，0：删除，1：停用，2：正常使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (23, 'test5', '2022-03-22 17:42:02', 2);
INSERT INTO `topic` VALUES (24, 'test6', '2022-03-22 17:43:46', 2);

SET FOREIGN_KEY_CHECKS = 1;
