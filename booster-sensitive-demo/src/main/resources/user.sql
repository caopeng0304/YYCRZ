/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : seata

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/09/2022 16:39:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sm2` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国密SM2非对称算法',
  `sm3` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国密SM3签名算法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (100, 'hi china', '123456', 'asd@qq.com', 'asd', 'asd');
INSERT INTO `user` VALUES (101, 'BKD+FoJkDLvJHy6YLAHxQQjzeX0wxdqWRwyuI9hl0dRxedOzaZ/cjTkfLglZzQ216DufbRwLbtridQ7wqN/YVkNK7yfKAiHj5Si+INWO9QUvBVj5MZm8jTM8FEU9ShDPr7KPf3gi6Pxv', '123456', 'BKx1FUDAoqeRZJTADsQQjnRpzrSu9EzsOn3G1YPvedBnNs1QOmk+Ss1zVkRja4fMh2tuqQs1T5zoRirGP43gAqftHHPgeMP+7h/6DMzArBPlEBu/3ImvPRLm79Xu7ql7nWJJVVKuGW575Nk=', 'BHXdxS67FSn1w0lyBmSazZZfzasIMpUltaHgftm3YIpb+m4GQ20OaD9u8Qv3KtHD5KeIeRidYIZw7+rcN9Fe8YFtqkTVcSUHYsRUXP2UoI+51xSc6e+wSn7pCOdQjRHtFZU0Wg==', 'asd');

SET FOREIGN_KEY_CHECKS = 1;
