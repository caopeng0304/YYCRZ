SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `booster`;

-- ----------------------------
-- Records of 身份证件类别代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204234139049984, 'id_doc_type_code', '身份证件类别代码', '身份证件类别代码', '0', '0', '2022-02-28 15:51:37', '2022-02-28 15:51:37');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204234139049985, 1498204234139049984, '1', '居民身份证', 'id_doc_type_code', '居民身份证', 0, '', '0', '2022-02-28 15:51:37', '2022-02-28 15:51:37');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204234143244288, 1498204234139049984, '3', '护照', 'id_doc_type_code', '护照', 1, '', '0', '2022-02-28 15:51:37', '2022-02-28 15:51:37');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204234143244289, 1498204234139049984, '4', '军官证', 'id_doc_type_code', '军官证', 2, '', '0', '2022-02-28 15:51:37', '2022-02-28 15:51:37');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204234143244290, 1498204234139049984, '6', '港澳居民来往内地通行证', 'id_doc_type_code', '港澳居民来往内地通行证', 3, '', '0', '2022-02-28 15:51:37', '2022-02-28 15:51:37');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204234143244291, 1498204234139049984, '7', '台湾居民来往内地通行证', 'id_doc_type_code', '台湾居民来往内地通行证', 4, '', '0', '2022-02-28 15:51:37', '2022-02-28 15:51:37');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204234143244292, 1498204234139049984, '99', '其他法定有效证件', 'id_doc_type_code', '其他法定有效证件', 5, '', '0', '2022-02-28 15:51:37', '2022-02-28 15:51:37');
COMMIT;

-- ----------------------------
-- Records of 婚姻状况代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626839224320, 'mari_sta_code', '婚姻状况代码', '婚姻状况代码', '0', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626839224321, 1498204626839224320, '10', '未婚', 'mari_sta_code', '未婚', 0, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626839224322, 1498204626839224320, '20', '已婚', 'mari_sta_code', '已婚', 1, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626843418624, 1498204626839224320, '30', '丧偶', 'mari_sta_code', '丧偶', 2, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626843418625, 1498204626839224320, '40', '离婚', 'mari_sta_code', '离婚', 3, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626843418626, 1498204626839224320, '90', '未说明的婚姻状况', 'mari_sta_code', '未说明的婚姻状况', 4, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626843418627, 1498204626839224320, '10', '未婚', 'mari_sta_code', '未婚', 5, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626847612928, 1498204626839224320, '20', '已婚', 'mari_sta_code', '已婚', 6, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626847612929, 1498204626839224320, '30', '丧偶', 'mari_sta_code', '丧偶', 7, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626847612930, 1498204626839224320, '40', '离婚', 'mari_sta_code', '离婚', 8, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498204626847612931, 1498204626839224320, '90', '未说明的婚姻状况', 'mari_sta_code', '未说明的婚姻状况', 9, '', '0', '2022-02-28 15:53:11', '2022-02-28 15:53:11');
COMMIT;

-- ----------------------------
-- Records of 治疗类型代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759976574976, 'trt_type_code', '治疗类型代码', '治疗类型代码', '0', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759980769280, 1498205759976574976, '1', '药物治疗', 'trt_type_code', '药物治疗', 0, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759980769281, 1498205759976574976, '11', '片剂', 'trt_type_code', '片剂', 1, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759980769282, 1498205759976574976, '12', '乳剂', 'trt_type_code', '乳剂', 2, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759980769283, 1498205759976574976, '2', '手术治疗', 'trt_type_code', '手术治疗', 3, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759984963584, 1498205759976574976, '3', '未治疗', 'trt_type_code', '未治疗', 4, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759984963585, 1498205759976574976, '4', '呼吸辅助', 'trt_type_code', '呼吸辅助', 5, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759984963586, 1498205759976574976, '41', '鼻导管吸氧', 'trt_type_code', '鼻导管吸氧', 6, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759984963587, 1498205759976574976, '42', '无创正压通气', 'trt_type_code', '无创正压通气', 7, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759984963588, 1498205759976574976, '43', '气管插管', 'trt_type_code', '气管插管', 8, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759984963589, 1498205759976574976, '44', '气管切开', 'trt_type_code', '气管切开', 9, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759984963590, 1498205759976574976, '45', '体外膜肺氧合（ECMO）', 'trt_type_code', '体外膜肺氧合（ECMO）', 10, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759989157888, 1498205759976574976, '46', '氧气治疗', 'trt_type_code', '氧气治疗', 11, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759989157889, 1498205759976574976, '5', '首次用药', 'trt_type_code', '首次用药', 12, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759989157890, 1498205759976574976, '6', '随访用药', 'trt_type_code', '随访用药', 13, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759989157891, 1498205759976574976, '7', '放射治疗', 'trt_type_code', '放射治疗', 14, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759989157892, 1498205759976574976, '8', '化学治疗', 'trt_type_code', '化学治疗', 15, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759989157893, 1498205759976574976, '9', '内分泌治疗', 'trt_type_code', '内分泌治疗', 16, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759993352192, 1498205759976574976, '10', '靶向治疗', 'trt_type_code', '靶向治疗', 17, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759993352193, 1498205759976574976, '11', '免疫治疗', 'trt_type_code', '免疫治疗', 18, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759993352194, 1498205759976574976, '12', '不明', 'trt_type_code', '不明', 19, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498205759993352195, 1498205759976574976, '999', '其他', 'trt_type_code', '其他', 20, '', '0', '2022-02-28 15:57:41', '2022-02-28 15:57:41');
COMMIT;

-- ----------------------------
-- Records of 临床严重程度代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206122016305152, 'clic_serv_code', '临床严重程度代码', '临床严重程度代码', '0', '0', '2022-02-28 15:59:08', '2022-02-28 15:59:08');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206122016305153, 1498206122016305152, '2', '轻型', 'clic_serv_code', '轻型', 0, '', '0', '2022-02-28 15:59:08', '2022-02-28 15:59:08');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206122016305154, 1498206122016305152, '3', '重型', 'clic_serv_code', '重型', 1, '', '0', '2022-02-28 15:59:08', '2022-02-28 15:59:08');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206122020499456, 1498206122016305152, '4', '危重型', 'clic_serv_code', '危重型', 2, '', '0', '2022-02-28 15:59:08', '2022-02-28 15:59:08');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206122020499457, 1498206122016305152, '5', '无症状感染者', 'clic_serv_code', '无症状感染者', 3, '', '0', '2022-02-28 15:59:08', '2022-02-28 15:59:08');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206122020499458, 1498206122016305152, '6', '普通型', 'clic_serv_code', '普通型', 4, '', '0', '2022-02-28 15:59:08', '2022-02-28 15:59:08');
COMMIT;

-- ----------------------------
-- Records of 户籍类别代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403936399360, 'hreg_type_code', '户籍类别代码', '户籍类别代码', '0', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403936399361, 1498206403936399360, '1', '本县区', 'hreg_type_code', '本县区', 0, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403936399362, 1498206403936399360, '2', '本市其它县区', 'hreg_type_code', '本市其它县区', 1, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593664, 1498206403936399360, '3', '本省其它地市', 'hreg_type_code', '本省其它地市', 2, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593665, 1498206403936399360, '4', '其他省', 'hreg_type_code', '其他省', 3, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593666, 1498206403936399360, '5', '港澳台', 'hreg_type_code', '港澳台', 4, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593667, 1498206403936399360, '6', '外籍', 'hreg_type_code', '外籍', 5, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593668, 1498206403936399360, '0', '未落常住户口', 'hreg_type_code', '未落常住户口', 6, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593669, 1498206403936399360, '1', '非农业家庭户口', 'hreg_type_code', '非农业家庭户口', 7, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593670, 1498206403936399360, '2', '农业家庭户口', 'hreg_type_code', '农业家庭户口', 8, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403940593671, 1498206403936399360, '3', '非农业集体户口', 'hreg_type_code', '非农业集体户口', 9, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403944787968, 1498206403936399360, '4', '农业集体户口', 'hreg_type_code', '农业集体户口', 10, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403944787969, 1498206403936399360, '5', '自理口粮户口', 'hreg_type_code', '自理口粮户口', 11, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403944787970, 1498206403936399360, '6', '寄住户口', 'hreg_type_code', '寄住户口', 12, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403948982272, 1498206403936399360, '7', '暂住户口', 'hreg_type_code', '暂住户口', 13, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206403948982273, 1498206403936399360, '8', '其它户口', 'hreg_type_code', '其它户口', 14, '', '0', '2022-02-28 16:00:15', '2022-02-28 16:00:15');
COMMIT;

-- ----------------------------
-- Records of 诊断状态代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206847828049920, 'diag_sta_type_code', '诊断状态代码', '诊断状态代码', '0', '0', '2022-02-28 16:02:01', '2022-02-28 16:02:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206847832244224, 1498206847828049920, '1', '临床诊断病例', 'diag_sta_type_code', '临床诊断病例', 0, '', '0', '2022-02-28 16:02:01', '2022-02-28 16:02:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206847832244225, 1498206847828049920, '2', '确诊病例', 'diag_sta_type_code', '确诊病例', 1, '', '0', '2022-02-28 16:02:01', '2022-02-28 16:02:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206847832244226, 1498206847828049920, '3', '疑似病例', 'diag_sta_type_code', '疑似病例', 2, '', '0', '2022-02-28 16:02:01', '2022-02-28 16:02:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206847832244227, 1498206847828049920, '4', '病原携带者', 'diag_sta_type_code', '病原携带者', 3, '', '0', '2022-02-28 16:02:01', '2022-02-28 16:02:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206847832244228, 1498206847828049920, '5', '阳性检测', 'diag_sta_type_code', '阳性检测', 4, '', '0', '2022-02-28 16:02:01', '2022-02-28 16:02:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498206847836438528, 1498206847828049920, '6', '埃博拉留观病例', 'diag_sta_type_code', '埃博拉留观病例', 5, '', '0', '2022-02-28 16:02:01', '2022-02-28 16:02:01');
COMMIT;

-- ----------------------------
-- Records of 停止治疗原因代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097619779584, 'end_trt_caus_code', '停止治疗原因代码', '停止治疗原因代码', '0', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097623973888, 1498207097619779584, '1', '治愈', 'end_trt_caus_code', '治愈', 0, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097623973889, 1498207097619779584, '2', '完成疗程', 'end_trt_caus_code', '完成疗程', 1, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097623973890, 1498207097619779584, '3', '结核死亡', 'end_trt_caus_code', '结核死亡', 2, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097623973891, 1498207097619779584, '4', '非结核死亡', 'end_trt_caus_code', '非结核死亡', 3, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097623973892, 1498207097619779584, '5', '失败', 'end_trt_caus_code', '失败', 4, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168192, 1498207097619779584, '6', '丢失', 'end_trt_caus_code', '丢失', 5, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168193, 1498207097619779584, '7', '诊断变更', 'end_trt_caus_code', '诊断变更', 6, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168194, 1498207097619779584, '8', '不良反应', 'end_trt_caus_code', '不良反应', 7, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168195, 1498207097619779584, '9', '转入耐多药治疗', 'end_trt_caus_code', '转入耐多药治疗', 8, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168196, 1498207097619779584, '10', '死亡', 'end_trt_caus_code', '死亡', 9, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168197, 1498207097619779584, '11', '心率减慢', 'end_trt_caus_code', '心率减慢', 10, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168198, 1498207097619779584, '12', '血压降低', 'end_trt_caus_code', '血压降低', 11, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207097628168199, 1498207097619779584, '99', '其他', 'end_trt_caus_code', '其他', 12, '', '0', '2022-02-28 16:03:00', '2022-02-28 16:03:00');
COMMIT;

-- ----------------------------
-- Records of 现住址类别代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207348007223296, 'pre_addr_type_code', '现住址类别代码', '现住址类别代码', '0', '0', '2022-02-28 16:04:00', '2022-02-28 16:04:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207348011417600, 1498207348007223296, '1', '本县区', 'pre_addr_type_code', '本县区', 0, '', '0', '2022-02-28 16:04:00', '2022-02-28 16:04:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207348011417601, 1498207348007223296, '2', '本市其它县区', 'pre_addr_type_code', '本市其它县区', 1, '', '0', '2022-02-28 16:04:00', '2022-02-28 16:04:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207348011417602, 1498207348007223296, '3', '本省其它地市', 'pre_addr_type_code', '本省其它地市', 2, '', '0', '2022-02-28 16:04:00', '2022-02-28 16:04:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207348011417603, 1498207348007223296, '4', '其他省', 'pre_addr_type_code', '其他省', 3, '', '0', '2022-02-28 16:04:00', '2022-02-28 16:04:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207348011417604, 1498207348007223296, '5', '港澳台', 'pre_addr_type_code', '港澳台', 4, '', '0', '2022-02-28 16:04:00', '2022-02-28 16:04:00');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207348011417605, 1498207348007223296, '6', '外籍', 'pre_addr_type_code', '外籍', 5, '', '0', '2022-02-28 16:04:00', '2022-02-28 16:04:00');
COMMIT;

-- ----------------------------
-- Records of 病例分类代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207611115835392, 'case_type_code', '病例分类代码', '病例分类代码', '0', '0', '2022-02-28 16:05:03', '2022-02-28 16:05:03');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207611120029696, 1498207611115835392, '1', '急性', 'case_type_code', '急性', 0, '', '0', '2022-02-28 16:05:03', '2022-02-28 16:05:03');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207611120029697, 1498207611115835392, '2', '慢性', 'case_type_code', '慢性', 1, '', '0', '2022-02-28 16:05:03', '2022-02-28 16:05:03');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207611124224000, 1498207611115835392, '3', '未分型', 'case_type_code', '未分型', 2, '', '0', '2022-02-28 16:05:03', '2022-02-28 16:05:03');
COMMIT;

-- ----------------------------
-- Records of 病情转归代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856130260992, 'otcm_code', '病情转归代码', '病情转归代码', '0', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455296, 1498207856130260992, '1', '治愈', 'otcm_code', '治愈', 0, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455297, 1498207856130260992, '2', '好转', 'otcm_code', '好转', 1, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455298, 1498207856130260992, '3', '稳定', 'otcm_code', '稳定', 2, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455299, 1498207856130260992, '4', '恶化', 'otcm_code', '恶化', 3, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455300, 1498207856130260992, '5', '未愈', 'otcm_code', '未愈', 4, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455301, 1498207856130260992, '6', '生存', 'otcm_code', '生存', 5, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455302, 1498207856130260992, '7', '死亡', 'otcm_code', '死亡', 6, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498207856134455303, 1498207856130260992, '8', '其他', 'otcm_code', '其他', 7, '', '0', '2022-02-28 16:06:01', '2022-02-28 16:06:01');
COMMIT;

-- ----------------------------
-- Records of 人群分类代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439771885568, 'popu_type_code', '人群分类代码', '人群分类代码', '0', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439771885569, 1498208439771885568, '1', '幼托儿童', 'popu_type_code', '幼托儿童', 0, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079872, 1498208439771885568, '2', '散居儿童', 'popu_type_code', '散居儿童', 1, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079873, 1498208439771885568, '3', '学生', 'popu_type_code', '学生', 2, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079874, 1498208439771885568, '4', '教师', 'popu_type_code', '教师', 3, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079875, 1498208439771885568, '5', '保育员及保姆', 'popu_type_code', '保育员及保姆', 4, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079876, 1498208439771885568, '6', '餐饮食品业', 'popu_type_code', '餐饮食品业', 5, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079877, 1498208439771885568, '7', '公共场所服务员', 'popu_type_code', '公共场所服务员', 6, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079878, 1498208439771885568, '8', '商业服务', 'popu_type_code', '商业服务', 7, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439776079879, 1498208439771885568, '9', '医务人员', 'popu_type_code', '医务人员', 8, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439780274176, 1498208439771885568, '10', '工人', 'popu_type_code', '工人', 9, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439780274177, 1498208439771885568, '11', '民工', 'popu_type_code', '民工', 10, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439780274178, 1498208439771885568, '12', '农民', 'popu_type_code', '农民', 11, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439780274179, 1498208439771885568, '13', '牧民', 'popu_type_code', '牧民', 12, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439784468480, 1498208439771885568, '14', '渔(船)民', 'popu_type_code', '渔(船)民', 13, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439784468481, 1498208439771885568, '15', '海员及长途驾驶员', 'popu_type_code', '海员及长途驾驶员', 14, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439784468482, 1498208439771885568, '16', '干部职员', 'popu_type_code', '干部职员', 15, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439784468483, 1498208439771885568, '17', '离退人员', 'popu_type_code', '离退人员', 16, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439788662784, 1498208439771885568, '18', '家务及待业', 'popu_type_code', '家务及待业', 17, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439788662785, 1498208439771885568, '19', '羁押人员', 'popu_type_code', '羁押人员', 18, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439788662786, 1498208439771885568, '20', '不详', 'popu_type_code', '不详', 19, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208439788662787, 1498208439771885568, '99', '其他', 'popu_type_code', '其他', 20, '', '0', '2022-02-28 16:08:20', '2022-02-28 16:08:20');
COMMIT;

-- ----------------------------
-- Records of 随访方式代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751278731264, 'flup_type_code', '随访方式代码', '随访方式代码', '0', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751278731265, 1498208751278731264, '1', '门诊', 'flup_type_code', '门诊', 0, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751278731266, 1498208751278731264, '2', '家庭', 'flup_type_code', '家庭', 1, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751278731267, 1498208751278731264, '3', '电话', 'flup_type_code', '电话', 2, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751282925568, 1498208751278731264, '4', '短信', 'flup_type_code', '短信', 3, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751282925569, 1498208751278731264, '5', '网络', 'flup_type_code', '网络', 4, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751282925570, 1498208751278731264, '6', '调查住院病人', 'flup_type_code', '调查住院病人', 5, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751282925571, 1498208751278731264, '7', '入户调查病人', 'flup_type_code', '入户调查病人', 6, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751287119872, 1498208751278731264, '8', '未见到病人，询问家人', 'flup_type_code', '未见到病人，询问家人', 7, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751287119873, 1498208751278731264, '9', '视频', 'flup_type_code', '视频', 8, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498208751287119874, 1498208751278731264, '99', '其他', 'flup_type_code', '其他', 9, '', '0', '2022-02-28 16:09:34', '2022-02-28 16:09:34');
COMMIT;

-- ----------------------------
-- Records of 疾病诊断代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029054889984, 'diag_dise_code', '疾病诊断代码', '疾病诊断代码', '0', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029054889985, 1498209029054889984, '100', '鼠疫', 'diag_dise_code', '鼠疫', 0, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029054889986, 1498209029054889984, '200', '霍乱', 'diag_dise_code', '霍乱', 1, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029054889987, 1498209029054889984, '300', '肝炎', 'diag_dise_code', '肝炎', 2, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084288, 1498209029054889984, '301', '甲肝', 'diag_dise_code', '甲肝', 3, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084289, 1498209029054889984, '302', '乙肝', 'diag_dise_code', '乙肝', 4, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084290, 1498209029054889984, '303', '丙肝', 'diag_dise_code', '丙肝', 5, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084291, 1498209029054889984, '306', '丁肝', 'diag_dise_code', '丁肝', 6, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084292, 1498209029054889984, '304', '戊肝', 'diag_dise_code', '戊肝', 7, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084293, 1498209029054889984, '305', '肝炎（未分型）', 'diag_dise_code', '肝炎（未分型）', 8, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084294, 1498209029054889984, '400', '痢疾', 'diag_dise_code', '痢疾', 9, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029059084295, 1498209029054889984, '401', '细菌性痢疾', 'diag_dise_code', '细菌性痢疾', 10, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029063278592, 1498209029054889984, '402', '阿米巴性痢疾', 'diag_dise_code', '阿米巴性痢疾', 11, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029063278593, 1498209029054889984, '500', '伤寒+副伤寒', 'diag_dise_code', '伤寒+副伤寒', 12, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029063278594, 1498209029054889984, '501', '伤 寒', 'diag_dise_code', '伤 寒', 13, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029063278595, 1498209029054889984, '502', '副伤寒', 'diag_dise_code', '副伤寒', 14, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029063278596, 1498209029054889984, '600', '艾滋病', 'diag_dise_code', '艾滋病', 15, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029063278597, 1498209029054889984, '601', 'HIV', 'diag_dise_code', 'HIV', 16, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029063278598, 1498209029054889984, '700', '淋病', 'diag_dise_code', '淋病', 17, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472896, 1498209029054889984, '800', '梅毒', 'diag_dise_code', '梅毒', 18, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472897, 1498209029054889984, '801', 'Ⅰ期梅毒', 'diag_dise_code', 'Ⅰ期梅毒', 19, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472898, 1498209029054889984, '802', 'Ⅱ期梅毒', 'diag_dise_code', 'Ⅱ期梅毒', 20, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472899, 1498209029054889984, '803', 'III期梅毒', 'diag_dise_code', 'III期梅毒', 21, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472900, 1498209029054889984, '804', '胎传梅毒', 'diag_dise_code', '胎传梅毒', 22, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472901, 1498209029054889984, '805', '隐性梅毒', 'diag_dise_code', '隐性梅毒', 23, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472902, 1498209029054889984, '900', '脊灰', 'diag_dise_code', '脊灰', 24, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029067472903, 1498209029054889984, '1000', '麻疹', 'diag_dise_code', '麻疹', 25, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667200, 1498209029054889984, '1100', '百日咳', 'diag_dise_code', '百日咳', 26, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667201, 1498209029054889984, '1200', '白喉', 'diag_dise_code', '白喉', 27, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667202, 1498209029054889984, '1300', '流脑', 'diag_dise_code', '流脑', 28, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667203, 1498209029054889984, '1400', '猩红热', 'diag_dise_code', '猩红热', 29, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667204, 1498209029054889984, '1500', '出血热', 'diag_dise_code', '出血热', 30, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667205, 1498209029054889984, '1600', '狂犬病', 'diag_dise_code', '狂犬病', 31, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667206, 1498209029054889984, '1700', '钩体病', 'diag_dise_code', '钩体病', 32, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667207, 1498209029054889984, '1800', '布病', 'diag_dise_code', '布病', 33, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667208, 1498209029054889984, '1900', '炭疽', 'diag_dise_code', '炭疽', 34, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667209, 1498209029054889984, '1901', '肺炭疽', 'diag_dise_code', '肺炭疽', 35, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667210, 1498209029054889984, '1902', '皮肤炭疽', 'diag_dise_code', '皮肤炭疽', 36, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667211, 1498209029054889984, '1903', '炭疽（未分型）', 'diag_dise_code', '炭疽（未分型）', 37, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667212, 1498209029054889984, '2000', '斑疹伤寒', 'diag_dise_code', '斑疹伤寒', 38, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667213, 1498209029054889984, '2100', '乙脑', 'diag_dise_code', '乙脑', 39, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029071667214, 1498209029054889984, '2200', '黑热病', 'diag_dise_code', '黑热病', 40, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861504, 1498209029054889984, '2300', '疟疾', 'diag_dise_code', '疟疾', 41, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861505, 1498209029054889984, '2301', '间日疟', 'diag_dise_code', '间日疟', 42, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861506, 1498209029054889984, '2302', '恶性疟', 'diag_dise_code', '恶性疟', 43, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861507, 1498209029054889984, '2303', '疟疾（未分型）', 'diag_dise_code', '疟疾（未分型）', 44, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861508, 1498209029054889984, '2400', '登革热', 'diag_dise_code', '登革热', 45, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861509, 1498209029054889984, '2500', '新生儿破伤风', 'diag_dise_code', '新生儿破伤风', 46, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861510, 1498209029054889984, '2600', '肺结核', 'diag_dise_code', '肺结核', 47, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861511, 1498209029054889984, '2605', '利福平耐药', 'diag_dise_code', '利福平耐药', 48, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861512, 1498209029054889984, '2606', '病原学阳性', 'diag_dise_code', '病原学阳性', 49, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861513, 1498209029054889984, '2607', '病原学阴性', 'diag_dise_code', '病原学阴性', 50, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861514, 1498209029054889984, '2608', '无病原学结果', 'diag_dise_code', '无病原学结果', 51, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861515, 1498209029054889984, '2700', '传染性非典', 'diag_dise_code', '传染性非典', 52, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029075861516, 1498209029054889984, '3100', '血吸虫病', 'diag_dise_code', '血吸虫病', 53, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055808, 1498209029054889984, '3200', '丝虫病', 'diag_dise_code', '丝虫病', 54, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055809, 1498209029054889984, '3300', '包虫病', 'diag_dise_code', '包虫病', 55, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055810, 1498209029054889984, '3400', '麻风病', 'diag_dise_code', '麻风病', 56, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055811, 1498209029054889984, '3500', '流行性感冒', 'diag_dise_code', '流行性感冒', 57, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055812, 1498209029054889984, '3600', '流行性腮腺炎', 'diag_dise_code', '流行性腮腺炎', 58, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055813, 1498209029054889984, '3700', '风疹', 'diag_dise_code', '风疹', 59, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055814, 1498209029054889984, '3800', '急性出血性结膜炎', 'diag_dise_code', '急性出血性结膜炎', 60, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055815, 1498209029054889984, '3900', '其它感染性腹泻病', 'diag_dise_code', '其它感染性腹泻病', 61, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055816, 1498209029054889984, '4000', '手足口病', 'diag_dise_code', '手足口病', 62, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055817, 1498209029054889984, '9900', '人感染高致病性禽流感', 'diag_dise_code', '人感染高致病性禽流感', 63, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055818, 1498209029054889984, '5200', '人感染H7N9禽流感', 'diag_dise_code', '人感染H7N9禽流感', 64, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055819, 1498209029054889984, '5300', '新型冠状病毒肺炎', 'diag_dise_code', '新型冠状病毒肺炎', 65, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055820, 1498209029054889984, '9801', '非淋菌性尿道炎', 'diag_dise_code', '非淋菌性尿道炎', 66, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055821, 1498209029054889984, '9802', '尖锐湿疣', 'diag_dise_code', '尖锐湿疣', 67, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055822, 1498209029054889984, '9803', '生殖器疱疹', 'diag_dise_code', '生殖器疱疹', 68, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055823, 1498209029054889984, '9891', '不明原因肺炎', 'diag_dise_code', '不明原因肺炎', 69, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029080055824, 1498209029054889984, '9813', '结核性胸膜炎', 'diag_dise_code', '结核性胸膜炎', 70, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250112, 1498209029054889984, '9898', '不明原因', 'diag_dise_code', '不明原因', 71, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250113, 1498209029054889984, '9811', '水痘', 'diag_dise_code', '水痘', 72, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250114, 1498209029054889984, '9812', '森林脑炎', 'diag_dise_code', '森林脑炎', 73, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250115, 1498209029054889984, '9815', '人感染猪链球菌', 'diag_dise_code', '人感染猪链球菌', 74, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250116, 1498209029054889984, '9804', '生殖道沙眼衣原体感染', 'diag_dise_code', '生殖道沙眼衣原体感染', 75, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250117, 1498209029054889984, '9821', '肝吸虫病', 'diag_dise_code', '肝吸虫病', 76, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250118, 1498209029054889984, '9822', '恙虫病', 'diag_dise_code', '恙虫病', 77, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250119, 1498209029054889984, '9823', '人粒细胞无形体病发', 'diag_dise_code', '人粒细胞无形体病发', 78, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250120, 1498209029054889984, '9824', '发热伴血小板减少综合征', 'diag_dise_code', '发热伴血小板减少综合征', 79, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250121, 1498209029054889984, '9899', '其它', 'diag_dise_code', '其它', 80, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250122, 1498209029054889984, '5000', '其它疾病', 'diag_dise_code', '其它疾病', 81, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250123, 1498209029054889984, '9825', 'AFP', 'diag_dise_code', 'AFP', 82, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250124, 1498209029054889984, '9827', '中东呼吸综合征（MERS）', 'diag_dise_code', '中东呼吸综合征（MERS）', 83, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250125, 1498209029054889984, '9828', '埃博拉出血热', 'diag_dise_code', '埃博拉出血热', 84, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498209029084250126, 1498209029054889984, '9829', '寨卡病毒病', 'diag_dise_code', '寨卡病毒病', 85, '', '0', '2022-02-28 16:10:41', '2022-02-28 16:10:41');
COMMIT;

-- ----------------------------
-- Records of 样本分类代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077517602816, 'samp_type_code', '样本分类代码', '样本分类代码', '0', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077521797120, 1498210077517602816, '1', '血液类', 'samp_type_code', '血液类', 0, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077521797121, 1498210077517602816, '101', '血液', 'samp_type_code', '血液', 1, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077525991424, 1498210077517602816, '102', '血清', 'samp_type_code', '血清', 2, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077525991425, 1498210077517602816, '103', '餐后30分钟血清', 'samp_type_code', '餐后30分钟血清', 3, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077525991426, 1498210077517602816, '104', '餐后60分钟血清', 'samp_type_code', '餐后60分钟血清', 4, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077525991427, 1498210077517602816, '105', '餐后120分钟血清', 'samp_type_code', '餐后120分钟血清', 5, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077530185728, 1498210077517602816, '106', '餐后180分钟血清', 'samp_type_code', '餐后180分钟血清', 6, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077530185729, 1498210077517602816, '107', '餐后300分钟血清', 'samp_type_code', '餐后300分钟血清', 7, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077530185730, 1498210077517602816, '108', '运动后30分钟血清', 'samp_type_code', '运动后30分钟血清', 8, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077530185731, 1498210077517602816, '109', '运动后60分钟血清', 'samp_type_code', '运动后60分钟血清', 9, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077530185732, 1498210077517602816, '110', '运动后90分钟血清', 'samp_type_code', '运动后90分钟血清', 10, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077530185733, 1498210077517602816, '111', '激发试验30分钟血清', 'samp_type_code', '激发试验30分钟血清', 11, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077530185734, 1498210077517602816, '112', '激发试验60分钟血清', 'samp_type_code', '激发试验60分钟血清', 12, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077534380032, 1498210077517602816, '113', '激发试验90分钟血清', 'samp_type_code', '激发试验90分钟血清', 13, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077534380033, 1498210077517602816, '114', '激发试验120分钟血清', 'samp_type_code', '激发试验120分钟血清', 14, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077534380034, 1498210077517602816, '120', '血浆', 'samp_type_code', '血浆', 15, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077534380035, 1498210077517602816, '121', '餐后30分钟血浆', 'samp_type_code', '餐后30分钟血浆', 16, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077538574336, 1498210077517602816, '122', '餐后60分钟血浆', 'samp_type_code', '餐后60分钟血浆', 17, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077538574337, 1498210077517602816, '123', '餐后120分钟血浆', 'samp_type_code', '餐后120分钟血浆', 18, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077538574338, 1498210077517602816, '124', '餐后180分钟血浆', 'samp_type_code', '餐后180分钟血浆', 19, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077538574339, 1498210077517602816, '125', '餐后300分钟血浆', 'samp_type_code', '餐后300分钟血浆', 20, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077538574340, 1498210077517602816, '126', '运动后30分钟血浆', 'samp_type_code', '运动后30分钟血浆', 21, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077538574341, 1498210077517602816, '127', '运动后60分钟血浆', 'samp_type_code', '运动后60分钟血浆', 22, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077542768640, 1498210077517602816, '128', '运动后90分钟血浆', 'samp_type_code', '运动后90分钟血浆', 23, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077542768641, 1498210077517602816, '129', '干血斑', 'samp_type_code', '干血斑', 24, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077542768642, 1498210077517602816, '130', '急性期血', 'samp_type_code', '急性期血', 25, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077542768643, 1498210077517602816, '131', '恢复期血', 'samp_type_code', '恢复期血', 26, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210077542768644, 1498210077517602816, '2', '体液类', 'samp_type_code', '体液类', 27, '', '0', '2022-02-28 16:14:50', '2022-02-28 16:14:50');
COMMIT;

-- ----------------------------
-- Records of 检测类别代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383768956928, 'test_type_code', '检测类别代码', '检测类别代码', '0', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383768956929, 1498210383768956928, '1', '血常规', 'test_type_code', '血常规', 0, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383768956930, 1498210383768956928, '2', '尿常规', 'test_type_code', '尿常规', 1, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383768956931, 1498210383768956928, '3', '便常规', 'test_type_code', '便常规', 2, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383773151232, 1498210383768956928, '4', '脑脊液常规', 'test_type_code', '脑脊液常规', 3, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383773151233, 1498210383768956928, '5', '影像学检查', 'test_type_code', '影像学检查', 4, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383773151234, 1498210383768956928, '6', '分子生物学检测', 'test_type_code', '分子生物学检测', 5, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383777345536, 1498210383768956928, '7', '免疫学检测', 'test_type_code', '免疫学检测', 6, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383777345537, 1498210383768956928, '8', '病原学检测', 'test_type_code', '病原学检测', 7, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383777345538, 1498210383768956928, '9', '代谢', 'test_type_code', '代谢', 8, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383777345539, 1498210383768956928, '10', '血生化检验', 'test_type_code', '血生化检验', 9, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383777345540, 1498210383768956928, '11', '血液学检测', 'test_type_code', '血液学检测', 10, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383781539840, 1498210383768956928, '12', '病原学检测', 'test_type_code', '病原学检测', 11, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383781539841, 1498210383768956928, '13', '细胞学', 'test_type_code', '细胞学', 12, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383781539842, 1498210383768956928, '14', '肺功能检查', 'test_type_code', '肺功能检查', 13, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383781539843, 1498210383768956928, '15', '病理', 'test_type_code', '病理', 14, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383781539844, 1498210383768956928, '16', '致病菌分子分型', 'test_type_code', '致病菌分子分型', 15, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210383781539845, 1498210383768956928, '17', '致病菌全基因组分析', 'test_type_code', '致病菌全基因组分析', 16, '', '0', '2022-02-28 16:16:04', '2022-02-28 16:16:04');
COMMIT;

-- ----------------------------
-- Records of 检验项目代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666335002624, 'test_item_code', '检验项目代码', '检验项目代码', '0', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666335002625, 1498210666335002624, '1', '血小板', 'test_item_code', '血小板', 0, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666335002626, 1498210666335002624, '2', '白细胞计数', 'test_item_code', '白细胞计数', 1, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666339196928, 1498210666335002624, '3', '血红蛋白', 'test_item_code', '血红蛋白', 2, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666339196929, 1498210666335002624, '4', '中性粒细胞比例（%）', 'test_item_code', '中性粒细胞比例（%）', 3, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666339196930, 1498210666335002624, '5', '血肌酐', 'test_item_code', '血肌酐', 4, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666339196931, 1498210666335002624, '6', '血糖', 'test_item_code', '血糖', 5, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666339196932, 1498210666335002624, '6', '甘油三酯', 'test_item_code', '甘油三酯', 6, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666339196933, 1498210666335002624, '7', '总胆固醇 ', 'test_item_code', '总胆固醇 ', 7, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666339196934, 1498210666335002624, '8', '空腹血糖值', 'test_item_code', '空腹血糖值', 8, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666343391232, 1498210666335002624, '9', '糖化血红蛋白', 'test_item_code', '糖化血红蛋白', 9, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666343391233, 1498210666335002624, '10', '低密度脂蛋白', 'test_item_code', '低密度脂蛋白', 10, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666343391234, 1498210666335002624, '11', '高密度脂蛋白', 'test_item_code', '高密度脂蛋白', 11, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666343391235, 1498210666335002624, '12', '血氧饱和度', 'test_item_code', '血氧饱和度', 12, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666343391236, 1498210666335002624, '13', '血尿酸', 'test_item_code', '血尿酸', 13, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666347585536, 1498210666335002624, '14', '全血硒', 'test_item_code', '全血硒', 14, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666347585537, 1498210666335002624, '15', '血清总蛋白', 'test_item_code', '血清总蛋白', 15, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666347585538, 1498210666335002624, '16', '血清维生素E', 'test_item_code', '血清维生素E', 16, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666347585539, 1498210666335002624, '17', 'CD4', 'test_item_code', 'CD4', 17, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666347585540, 1498210666335002624, '18', 'CD8', 'test_item_code', 'CD8', 18, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666347585541, 1498210666335002624, '19', 'CD4%（CD4/CD45）', 'test_item_code', 'CD4%（CD4/CD45）', 19, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666351779840, 1498210666335002624, '20', 'HIV病毒载量', 'test_item_code', 'HIV病毒载量', 20, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666351779841, 1498210666335002624, '21', 'HCV RNA', 'test_item_code', 'HCV RNA', 21, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666351779842, 1498210666335002624, '22', 'HCV亚型', 'test_item_code', 'HCV亚型', 22, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666351779843, 1498210666335002624, '23', 'HbeAg', 'test_item_code', 'HbeAg', 23, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666351779844, 1498210666335002624, '24', 'HBV DNA', 'test_item_code', 'HBV DNA', 24, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666351779845, 1498210666335002624, '25', '氯化物检测', 'test_item_code', '氯化物检测', 25, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666351779846, 1498210666335002624, '26', '心肌酶', 'test_item_code', '心肌酶', 26, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974144, 1498210666335002624, '27', '转氨酶 ', 'test_item_code', '转氨酶 ', 27, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974145, 1498210666335002624, '28', 'HIV抗体', 'test_item_code', 'HIV抗体', 28, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974146, 1498210666335002624, '29', 'AntiHCV', 'test_item_code', 'AntiHCV', 29, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974147, 1498210666335002624, '30', 'HBsAg', 'test_item_code', 'HBsAg', 30, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974148, 1498210666335002624, '31', '乙脑特异性抗体IgM', 'test_item_code', '乙脑特异性抗体IgM', 31, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974149, 1498210666335002624, '32', '乙脑特异性抗体IgG', 'test_item_code', '乙脑特异性抗体IgG', 32, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974150, 1498210666335002624, '33', '乙脑特异性IgG效价', 'test_item_code', '乙脑特异性IgG效价', 33, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974151, 1498210666335002624, '34', '抗-HBc IgM', 'test_item_code', '抗-HBc IgM', 34, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974152, 1498210666335002624, '35', '抗-HAV IgM', 'test_item_code', '抗-HAV IgM', 35, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974153, 1498210666335002624, '36', '抗-HEV IgM ', 'test_item_code', '抗-HEV IgM ', 36, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974154, 1498210666335002624, '37', 'ALT', 'test_item_code', 'ALT', 37, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974155, 1498210666335002624, '38', 'T.BIL', 'test_item_code', 'T.BIL', 38, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974156, 1498210666335002624, '39', 'AST ', 'test_item_code', 'AST ', 39, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974157, 1498210666335002624, '40', '尿微量蛋白', 'test_item_code', '尿微量蛋白', 40, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974158, 1498210666335002624, '41', '尿肌酐 ', 'test_item_code', '尿肌酐 ', 41, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974159, 1498210666335002624, '42', '舒张试验前FEV1', 'test_item_code', '舒张试验前FEV1', 42, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974160, 1498210666335002624, '43', '舒张试验前FVC', 'test_item_code', '舒张试验前FVC', 43, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666355974161, 1498210666335002624, '44', '舒张试验前PEF', 'test_item_code', '舒张试验前PEF', 44, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168448, 1498210666335002624, '45', '使用支气管扩张剂前FEV1', 'test_item_code', '使用支气管扩张剂前FEV1', 45, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168449, 1498210666335002624, '46', '使用支气管扩张剂前FVC', 'test_item_code', '使用支气管扩张剂前FVC', 46, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168450, 1498210666335002624, '47', '使用支气管扩张剂后FEV1', 'test_item_code', '使用支气管扩张剂后FEV1', 47, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168451, 1498210666335002624, '48', '使用支气管扩张剂后FVC', 'test_item_code', '使用支气管扩张剂后FVC', 48, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168452, 1498210666335002624, '49', '支气管舒张试验前肺通气功能检查', 'test_item_code', '支气管舒张试验前肺通气功能检查', 49, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168453, 1498210666335002624, '50', '支气管舒张试验后肺通气功能检查', 'test_item_code', '支气管舒张试验后肺通气功能检查', 50, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168454, 1498210666335002624, '51', '心电图', 'test_item_code', '心电图', 51, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168455, 1498210666335002624, '52', '心率', 'test_item_code', '心率', 52, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168456, 1498210666335002624, '53', '颈动脉中层厚度', 'test_item_code', '颈动脉中层厚度', 53, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168457, 1498210666335002624, '54', '视网膜检查', 'test_item_code', '视网膜检查', 54, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666360168458, 1498210666335002624, '55', '血管造影', 'test_item_code', '血管造影', 55, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666364362752, 1498210666335002624, '56', '磁共振', 'test_item_code', '磁共振', 56, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666364362753, 1498210666335002624, '57', '超声', 'test_item_code', '超声', 57, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666364362754, 1498210666335002624, '58', 'X线', 'test_item_code', 'X线', 58, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666364362755, 1498210666335002624, '59', '胸片', 'test_item_code', '胸片', 59, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666364362756, 1498210666335002624, '60', 'CT', 'test_item_code', 'CT', 60, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666364362757, 1498210666335002624, '61', '胸透', 'test_item_code', '胸透', 61, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210666364362758, 1498210666335002624, '62', 'MRI（核磁共振）', 'test_item_code', 'MRI（核磁共振）', 62, '', '0', '2022-02-28 16:17:11', '2022-02-28 16:17:11');
COMMIT;

-- ----------------------------
-- Records of 检测方法代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918140067840, 'test_meth_code', '检测方法代码', '检测方法代码', '0', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918140067841, 1498210918140067840, '1', '显微镜检测', 'test_meth_code', '显微镜检测', 0, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918140067842, 1498210918140067840, '2', '病毒分离', 'test_meth_code', '病毒分离', 1, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918140067843, 1498210918140067840, '3', '细菌培养分离', 'test_meth_code', '细菌培养分离', 2, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918144262144, 1498210918140067840, '4', '基因测序', 'test_meth_code', '基因测序', 3, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918144262145, 1498210918140067840, '5', '恒温扩增PCR', 'test_meth_code', '恒温扩增PCR', 4, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918144262146, 1498210918140067840, '6', '实时荧光定量多聚核苷酸链式反应（Quantitative Real-time PCR）', 'test_meth_code', '实时荧光定量多聚核苷酸链式反应（Quantitative Real-time PCR）', 5, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918148456448, 1498210918140067840, '7', '巢氏RT-PCR', 'test_meth_code', '巢氏RT-PCR', 6, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918148456449, 1498210918140067840, '8', '巢氏PCR', 'test_meth_code', '巢氏PCR', 7, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918148456450, 1498210918140067840, '9', '疟原虫快速诊断试纸(RDT)', 'test_meth_code', '疟原虫快速诊断试纸(RDT)', 8, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918148456451, 1498210918140067840, '10', '胶体金免疫层析试验（IgM抗体）', 'test_meth_code', '胶体金免疫层析试验（IgM抗体）', 9, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918148456452, 1498210918140067840, '11', '胶体金免疫层析试验（IgG抗体）', 'test_meth_code', '胶体金免疫层析试验（IgG抗体）', 10, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918152650752, 1498210918140067840, '12', '胶体金免疫层析试验检测NS1抗原', 'test_meth_code', '胶体金免疫层析试验检测NS1抗原', 11, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918152650753, 1498210918140067840, '13', '免疫荧光试验检测（抗原）', 'test_meth_code', '免疫荧光试验检测（抗原）', 12, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918152650754, 1498210918140067840, '14', '免疫荧光试验检测（抗体）', 'test_meth_code', '免疫荧光试验检测（抗体）', 13, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918152650755, 1498210918140067840, '15', '虎红平板凝集试验', 'test_meth_code', '虎红平板凝集试验', 14, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918152650756, 1498210918140067840, '16', '试管凝集试验', 'test_meth_code', '试管凝集试验', 15, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918152650757, 1498210918140067840, '17', '抗人球蛋白试验（Coomb’s 试验）', 'test_meth_code', '抗人球蛋白试验（Coomb’s 试验）', 16, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918152650758, 1498210918140067840, '18', '补体结合试验', 'test_meth_code', '补体结合试验', 17, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845056, 1498210918140067840, '19', '半胱氨酸凝集试验', 'test_meth_code', '半胱氨酸凝集试验', 18, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845057, 1498210918140067840, '20', '荧光偏振试验', 'test_meth_code', '荧光偏振试验', 19, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845058, 1498210918140067840, '21', '显微镜凝集试验(MAT)', 'test_meth_code', '显微镜凝集试验(MAT)', 20, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845059, 1498210918140067840, '22', 'Dri-dot快诊试验', 'test_meth_code', 'Dri-dot快诊试验', 21, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845060, 1498210918140067840, '23', '直接免疫荧光法DFAT', 'test_meth_code', '直接免疫荧光法DFAT', 22, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845061, 1498210918140067840, '24', '免疫组化检测', 'test_meth_code', '免疫组化检测', 23, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845062, 1498210918140067840, '25', '快速免疫荧光抑制实验 RFFIT', 'test_meth_code', '快速免疫荧光抑制实验 RFFIT', 24, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918156845063, 1498210918140067840, '26', '荧光抗体病毒中和实验', 'test_meth_code', '荧光抗体病毒中和实验', 25, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039360, 1498210918140067840, '27', '固定化的免疫层析快速检测', 'test_meth_code', '固定化的免疫层析快速检测', 26, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039361, 1498210918140067840, '28', '间接血凝试验（IHA）', 'test_meth_code', '间接血凝试验（IHA）', 27, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039362, 1498210918140067840, '29', '酶联免疫吸附试验（ELISA）通用方法', 'test_meth_code', '酶联免疫吸附试验（ELISA）通用方法', 28, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039363, 1498210918140067840, '30', '胶体染料试纸条(DDIA)', 'test_meth_code', '胶体染料试纸条(DDIA)', 29, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039364, 1498210918140067840, '31', '环卵沉淀试验(COPT)', 'test_meth_code', '环卵沉淀试验(COPT)', 30, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039365, 1498210918140067840, '32', '斑点金免疫渗滤试验(DIGFA)', 'test_meth_code', '斑点金免疫渗滤试验(DIGFA)', 31, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039366, 1498210918140067840, '33', 'In-house方法（实验室自建）', 'test_meth_code', 'In-house方法（实验室自建）', 32, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039367, 1498210918140067840, '34', 'TRUEGENETM方法（西门子）', 'test_meth_code', 'TRUEGENETM方法（西门子）', 33, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039368, 1498210918140067840, '35', 'ViroSeqTM方法(雅培)', 'test_meth_code', 'ViroSeqTM方法(雅培)', 34, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039369, 1498210918140067840, '36', 'Sentasa SQ基因分型', 'test_meth_code', 'Sentasa SQ基因分型', 35, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918161039370, 1498210918140067840, '37', '结核分枝杆菌涂片检测', 'test_meth_code', '结核分枝杆菌涂片检测', 36, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233664, 1498210918140067840, '38', '结核分枝菌核酸检测', 'test_meth_code', '结核分枝菌核酸检测', 37, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233665, 1498210918140067840, '39', '分枝杆菌菌种鉴定', 'test_meth_code', '分枝杆菌菌种鉴定', 38, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233666, 1498210918140067840, '40', '传统表型药敏实验', 'test_meth_code', '传统表型药敏实验', 39, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233667, 1498210918140067840, '41', '耐药分子生物学检测', 'test_meth_code', '耐药分子生物学检测', 40, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233668, 1498210918140067840, '42', '改良加藤法', 'test_meth_code', '改良加藤法', 41, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233669, 1498210918140067840, '43', '尼龙绢集卵孵化法', 'test_meth_code', '尼龙绢集卵孵化法', 42, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233670, 1498210918140067840, '44', '粪便直接涂片法', 'test_meth_code', '粪便直接涂片法', 43, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233671, 1498210918140067840, '45', '活组织镜检法', 'test_meth_code', '活组织镜检法', 44, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918165233672, 1498210918140067840, '46', '尿液沉渣镜检', 'test_meth_code', '尿液沉渣镜检', 45, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427968, 1498210918140067840, '47', '酶联免疫吸附试验', 'test_meth_code', '酶联免疫吸附试验', 46, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427969, 1498210918140067840, '48', '血凝抑制试验', 'test_meth_code', '血凝抑制试验', 47, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427970, 1498210918140067840, '49', '反向血凝抑制试验', 'test_meth_code', '反向血凝抑制试验', 48, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427971, 1498210918140067840, '50', '间接荧光试验', 'test_meth_code', '间接荧光试验', 49, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427972, 1498210918140067840, '51', '抗体中和试验', 'test_meth_code', '抗体中和试验', 50, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427973, 1498210918140067840, '52', '寄生虫（细胞或组织）分离培养', 'test_meth_code', '寄生虫（细胞或组织）分离培养', 51, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427974, 1498210918140067840, '53', '脉冲场凝胶电泳（PFGE）', 'test_meth_code', '脉冲场凝胶电泳（PFGE）', 52, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918169427975, 1498210918140067840, '54', '多位点串联重复序列（MLVA）', 'test_meth_code', '多位点串联重复序列（MLVA）', 53, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498210918173622272, 1498210918140067840, '55', '全基因组测序分析', 'test_meth_code', '全基因组测序分析', 54, '', '0', '2022-02-28 16:18:11', '2022-02-28 16:18:11');
COMMIT;

-- ----------------------------
-- Records of 检测结果代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166514143232, 'test_rest_code', '检测结果代码', '检测结果代码', '0', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166514143233, 1498211166514143232, '1', '-', 'test_rest_code', '-', 0, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337536, 1498211166514143232, '2', '士', 'test_rest_code', '士', 1, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337537, 1498211166514143232, '3', '+', 'test_rest_code', '+', 2, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337538, 1498211166514143232, '4', '++', 'test_rest_code', '++', 3, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337539, 1498211166514143232, '5', '+++', 'test_rest_code', '+++', 4, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337540, 1498211166514143232, '6', '结核分枝杆菌', 'test_rest_code', '结核分枝杆菌', 5, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337541, 1498211166514143232, '7', '非结核分枝杆菌', 'test_rest_code', '非结核分枝杆菌', 6, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337542, 1498211166514143232, '8', '阴性', 'test_rest_code', '阴性', 7, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211166518337543, 1498211166514143232, '9', '阳性', 'test_rest_code', '阳性', 8, '', '0', '2022-02-28 16:19:10', '2022-02-28 16:19:10');
COMMIT;

-- ----------------------------
-- Records of 检测结果计量单位代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211419736834048, 'test_rest_munit_code', '检测结果计量单位代码', '检测结果计量单位代码', '0', '0', '2022-02-28 16:20:11', '2022-02-28 16:20:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211419741028352, 1498211419736834048, '1', '细胞数(正常值0~15/μl)', 'test_rest_munit_code', '细胞数(正常值0~15/μl)', 0, '', '0', '2022-02-28 16:20:11', '2022-02-28 16:20:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211419741028353, 1498211419736834048, '2', '蛋白(正常值<0.45g/l)', 'test_rest_munit_code', '蛋白(正常值<0.45g/l)', 1, '', '0', '2022-02-28 16:20:11', '2022-02-28 16:20:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211419741028354, 1498211419736834048, '3', 'mmol/L', 'test_rest_munit_code', 'mmol/L', 2, '', '0', '2022-02-28 16:20:11', '2022-02-28 16:20:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211419745222656, 1498211419736834048, '4', 'mmol/L', 'test_rest_munit_code', 'mmol/L', 3, '', '0', '2022-02-28 16:20:11', '2022-02-28 16:20:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211419745222657, 1498211419736834048, '5', '×10^9/L）', 'test_rest_munit_code', '×10^9/L）', 4, '', '0', '2022-02-28 16:20:11', '2022-02-28 16:20:11');
COMMIT;

-- ----------------------------
-- Records of 是否值域代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211687635464192, 'sts', '是否值域代码', '是否值域代码', '0', '0', '2022-02-28 16:21:14', '2022-02-28 16:21:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211687639658496, 1498211687635464192, '1', '是', 'sts', '是', 0, '', '0', '2022-02-28 16:21:14', '2022-02-28 16:21:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211687639658497, 1498211687635464192, '0', '否', 'sts', '否', 1, '', '0', '2022-02-28 16:21:14', '2022-02-28 16:21:14');
COMMIT;

-- ----------------------------
-- Records of 治疗方案代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937594990592, 'trt_plan_code', '治疗方案代码', '治疗方案代码', '0', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937594990593, 1498211937594990592, '1', '2HRZE/4HR', 'trt_plan_code', '2HRZE/4HR', 0, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937594990594, 1498211937594990592, '2', '2HRZES/6HRE', 'trt_plan_code', '2HRZES/6HRE', 1, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937594990595, 1498211937594990592, '3', '3HRZE/6HRE', 'trt_plan_code', '3HRZE/6HRE', 2, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937599184896, 1498211937594990592, '4', '2HRZE/7-10HRE', 'trt_plan_code', '2HRZE/7-10HRE', 3, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937599184897, 1498211937594990592, '5', '9RZELfx', 'trt_plan_code', '9RZELfx', 4, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937599184898, 1498211937594990592, '6', '6 Lfx (Mfx）BdqLzd Cs Cfz/14 Lfx（Mfx） Cs Cfz', 'trt_plan_code', '6 Lfx (Mfx）BdqLzd Cs Cfz/14 Lfx（Mfx） Cs Cfz', 5, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937599184899, 1498211937594990592, '7', '4-6 Am MfxPtoCfz Z H（高剂量）E/5 MfxCfz Z H（高剂量）', 'trt_plan_code', '4-6 Am MfxPtoCfz Z H（高剂量）E/5 MfxCfz Z H（高剂量）', 6, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937599184900, 1498211937594990592, '8', '病原治疗', 'trt_plan_code', '病原治疗', 7, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937599184901, 1498211937594990592, '9', '单纯脾切除', 'trt_plan_code', '单纯脾切除', 8, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937603379200, 1498211937594990592, '10', '脾切除+贲门周围血管离断术', 'trt_plan_code', '脾切除+贲门周围血管离断术', 9, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937603379201, 1498211937594990592, '11', '脾切除+分流术', 'trt_plan_code', '脾切除+分流术', 10, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937603379202, 1498211937594990592, '12', '脾切除+食管横断吻合术', 'trt_plan_code', '脾切除+食管横断吻合术', 11, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937603379203, 1498211937594990592, '13', '结肠局部切除', 'trt_plan_code', '结肠局部切除', 12, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573504, 1498211937594990592, '14', '左半结肠切除', 'trt_plan_code', '左半结肠切除', 13, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573505, 1498211937594990592, '15', '右半结肠切除', 'trt_plan_code', '右半结肠切除', 14, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573506, 1498211937594990592, '16', '乙状结肠切除', 'trt_plan_code', '乙状结肠切除', 15, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573507, 1498211937594990592, '17', '结肠造口术', 'trt_plan_code', '结肠造口术', 16, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573508, 1498211937594990592, '18', '上消化道出血急症手术', 'trt_plan_code', '上消化道出血急症手术', 17, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573509, 1498211937594990592, '19', '腹水治疗', 'trt_plan_code', '腹水治疗', 18, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573510, 1498211937594990592, '20', '侏儒症治疗', 'trt_plan_code', '侏儒症治疗', 19, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573511, 1498211937594990592, '21', '上消化道出血内科治疗', 'trt_plan_code', '上消化道出血内科治疗', 20, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573512, 1498211937594990592, '22', '原发性腹膜炎内科治疗', 'trt_plan_code', '原发性腹膜炎内科治疗', 21, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573513, 1498211937594990592, '23', '感想脑病内科治疗', 'trt_plan_code', '感想脑病内科治疗', 22, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937607573514, 1498211937594990592, '24', 'ABZ片剂', 'trt_plan_code', 'ABZ片剂', 23, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767808, 1498211937594990592, '25', 'ABZ乳剂', 'trt_plan_code', 'ABZ乳剂', 24, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767809, 1498211937594990592, '26', '手术治疗', 'trt_plan_code', '手术治疗', 25, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767810, 1498211937594990592, '27', '观察', 'trt_plan_code', '观察', 26, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767811, 1498211937594990592, '28', '自我行为疗法（适量平地行走和关节活动度的锻炼）', 'trt_plan_code', '自我行为疗法（适量平地行走和关节活动度的锻炼）', 27, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767812, 1498211937594990592, '29', '针灸', 'trt_plan_code', '针灸', 28, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767813, 1498211937594990592, '30', '按摩', 'trt_plan_code', '按摩', 29, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767814, 1498211937594990592, '31', '物理疗法（包括热疗、水疗和超声波等）', 'trt_plan_code', '物理疗法（包括热疗、水疗和超声波等）', 30, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937611767815, 1498211937594990592, '32', '行动支持（手杖、拐杖、助行器等）', 'trt_plan_code', '行动支持（手杖、拐杖、助行器等）', 31, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962112, 1498211937594990592, '33', '局部药物治疗', 'trt_plan_code', '局部药物治疗', 32, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962113, 1498211937594990592, '34', '口服药物治疗', 'trt_plan_code', '口服药物治疗', 33, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962114, 1498211937594990592, '35', '关节腔内注射', 'trt_plan_code', '关节腔内注射', 34, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962115, 1498211937594990592, '36', '关节内游离体摘除', 'trt_plan_code', '关节内游离体摘除', 35, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962116, 1498211937594990592, '37', '关节置换', 'trt_plan_code', '关节置换', 36, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962117, 1498211937594990592, '38', '药物治疗', 'trt_plan_code', '药物治疗', 37, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962118, 1498211937594990592, '39', '物理疗法', 'trt_plan_code', '物理疗法', 38, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962119, 1498211937594990592, '40', '阿苯达唑药物治疗', 'trt_plan_code', '阿苯达唑药物治疗', 39, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962120, 1498211937594990592, '41', '其他一线方案', 'trt_plan_code', '其他一线方案', 40, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962121, 1498211937594990592, '42', '其他二线方案', 'trt_plan_code', '其他二线方案', 41, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498211937615962122, 1498211937594990592, '99', '其他', 'trt_plan_code', '其他', 42, '', '0', '2022-02-28 16:22:14', '2022-02-28 16:22:14');
COMMIT;

-- ----------------------------
-- Records of 随访状态代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169489645568, 'flup_sts_code', '随访状态代码', '随访状态代码', '0', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169489645569, 1498212169489645568, '1', '感染者随访', 'flup_sts_code', '感染者随访', 0, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169489645570, 1498212169489645568, '2', '外出', 'flup_sts_code', '外出', 1, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169493839872, 1498212169489645568, '3', '拒绝随访', 'flup_sts_code', '拒绝随访', 2, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169493839873, 1498212169489645568, '4', '因羁押未随访', 'flup_sts_code', '因羁押未随访', 3, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169493839874, 1498212169489645568, '5', '转入时地址不详', 'flup_sts_code', '转入时地址不详', 4, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169493839875, 1498212169489645568, '6', '查无此人（仅首次随访表可以填）', 'flup_sts_code', '查无此人（仅首次随访表可以填）', 5, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169493839876, 1498212169489645568, '7', '在治', 'flup_sts_code', '在治', 6, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169493839877, 1498212169489645568, '8', '失访', 'flup_sts_code', '失访', 7, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169493839878, 1498212169489645568, '9', '死亡', 'flup_sts_code', '死亡', 8, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169498034176, 1498212169489645568, '10', '转出', 'flup_sts_code', '转出', 9, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169498034177, 1498212169489645568, '11', '停药', 'flup_sts_code', '停药', 10, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169498034178, 1498212169489645568, '12', '转入成人治疗', 'flup_sts_code', '转入成人治疗', 11, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169498034179, 1498212169489645568, '13', '生存', 'flup_sts_code', '生存', 12, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169498034180, 1498212169489645568, '131', '拒访', 'flup_sts_code', '拒访', 13, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169498034181, 1498212169489645568, '132', '搬迁', 'flup_sts_code', '搬迁', 14, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169502228480, 1498212169489645568, '133', '失联', 'flup_sts_code', '失联', 15, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169502228481, 1498212169489645568, '134', '查无此人', 'flup_sts_code', '查无此人', 16, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169502228482, 1498212169489645568, '139', '其他', 'flup_sts_code', '其他', 17, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169502228483, 1498212169489645568, '14', '接受随访', 'flup_sts_code', '接受随访', 18, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169502228484, 1498212169489645568, '15', '已搬迁', 'flup_sts_code', '已搬迁', 19, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212169502228485, 1498212169489645568, '16', '在管', 'flup_sts_code', '在管', 20, '', '0', '2022-02-28 16:23:09', '2022-02-28 16:23:09');
COMMIT;

-- ----------------------------
-- Records of 性别代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212429393948672, 'ged_code', '性别代码', '性别代码', '0', '0', '2022-02-28 16:24:11', '2022-02-28 16:24:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212429393948673, 1498212429393948672, '0', '未知的性别', 'ged_code', '未知的性别', 0, '', '0', '2022-02-28 16:24:11', '2022-02-28 16:24:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212429393948674, 1498212429393948672, '1', '男性', 'ged_code', '男性', 1, '', '0', '2022-02-28 16:24:11', '2022-02-28 16:24:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212429393948675, 1498212429393948672, '2', '女性', 'ged_code', '女性', 2, '', '0', '2022-02-28 16:24:11', '2022-02-28 16:24:11');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212429398142976, 1498212429393948672, '9', '未说明的性别', 'ged_code', '未说明的性别', 3, '', '0', '2022-02-28 16:24:11', '2022-02-28 16:24:11');
COMMIT;

-- ----------------------------
-- Records of 国籍代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650131734528, 'nati_code', '国籍代码', '国籍代码', '0', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650131734529, 1498212650131734528, '4', '阿富汗', 'nati_code', '阿富汗', 0, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650131734530, 1498212650131734528, '8', '阿尔巴尼亚', 'nati_code', '阿尔巴尼亚', 1, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650140123136, 1498212650131734528, '12', '阿尔及利亚', 'nati_code', '阿尔及利亚', 2, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650140123137, 1498212650131734528, '16', '美属萨摩亚', 'nati_code', '美属萨摩亚', 3, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650140123138, 1498212650131734528, '20', '安道尔', 'nati_code', '安道尔', 4, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650140123139, 1498212650131734528, '24', '安哥拉', 'nati_code', '安哥拉', 5, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650140123140, 1498212650131734528, '660', '安圭拉', 'nati_code', '安圭拉', 6, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650140123141, 1498212650131734528, '10', '南极洲', 'nati_code', '南极洲', 7, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650140123142, 1498212650131734528, '28', '安提瓜和巴布达', 'nati_code', '安提瓜和巴布达', 8, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650144317440, 1498212650131734528, '32', '阿根廷', 'nati_code', '阿根廷', 9, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650144317441, 1498212650131734528, '51', '亚美尼亚', 'nati_code', '亚美尼亚', 10, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650144317442, 1498212650131734528, '533', '阿鲁巴', 'nati_code', '阿鲁巴', 11, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650144317443, 1498212650131734528, '36', '澳大利亚', 'nati_code', '澳大利亚', 12, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511744, 1498212650131734528, '40', '奥地利', 'nati_code', '奥地利', 13, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511745, 1498212650131734528, '31', '阿塞拜疆', 'nati_code', '阿塞拜疆', 14, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511746, 1498212650131734528, '44', '巴哈马', 'nati_code', '巴哈马', 15, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511747, 1498212650131734528, '48', '巴林', 'nati_code', '巴林', 16, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511748, 1498212650131734528, '50', '孟加拉国', 'nati_code', '孟加拉国', 17, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511749, 1498212650131734528, '52', '巴巴多斯', 'nati_code', '巴巴多斯', 18, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511750, 1498212650131734528, '112', '白俄罗斯', 'nati_code', '白俄罗斯', 19, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650148511751, 1498212650131734528, '56', '比利时', 'nati_code', '比利时', 20, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706048, 1498212650131734528, '84', '伯利兹', 'nati_code', '伯利兹', 21, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706049, 1498212650131734528, '204', '贝宁', 'nati_code', '贝宁', 22, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706050, 1498212650131734528, '60', '百慕大', 'nati_code', '百慕大', 23, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706051, 1498212650131734528, '64', '不丹', 'nati_code', '不丹', 24, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706052, 1498212650131734528, '68', '玻利维亚', 'nati_code', '玻利维亚', 25, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706053, 1498212650131734528, '70', '波黑', 'nati_code', '波黑', 26, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706054, 1498212650131734528, '72', '博茨瓦纳', 'nati_code', '博茨瓦纳', 27, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706055, 1498212650131734528, '74', '布维岛', 'nati_code', '布维岛', 28, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706056, 1498212650131734528, '76', '巴西', 'nati_code', '巴西', 29, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706057, 1498212650131734528, '86', '英属印度洋领地', 'nati_code', '英属印度洋领地', 30, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706058, 1498212650131734528, '96', '文莱', 'nati_code', '文莱', 31, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650152706059, 1498212650131734528, '100', '保加利亚', 'nati_code', '保加利亚', 32, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900352, 1498212650131734528, '854', '布基纳法索', 'nati_code', '布基纳法索', 33, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900353, 1498212650131734528, '108', '布隆迪', 'nati_code', '布隆迪', 34, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900354, 1498212650131734528, '116', '柬埔寨', 'nati_code', '柬埔寨', 35, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900355, 1498212650131734528, '120', '喀麦隆', 'nati_code', '喀麦隆', 36, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900356, 1498212650131734528, '124', '加拿大', 'nati_code', '加拿大', 37, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900357, 1498212650131734528, '132', '佛得角', 'nati_code', '佛得角', 38, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900358, 1498212650131734528, '136', '开曼群岛', 'nati_code', '开曼群岛', 39, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900359, 1498212650131734528, '140', '中非', 'nati_code', '中非', 40, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900360, 1498212650131734528, '148', '乍得', 'nati_code', '乍得', 41, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900361, 1498212650131734528, '152', '智利', 'nati_code', '智利', 42, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900362, 1498212650131734528, '156', '中国', 'nati_code', '中国', 43, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900363, 1498212650131734528, '344', '香港', 'nati_code', '香港', 44, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900364, 1498212650131734528, '446', '澳门', 'nati_code', '澳门', 45, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900365, 1498212650131734528, '158', '台湾', 'nati_code', '台湾', 46, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900366, 1498212650131734528, '162', '圣诞岛', 'nati_code', '圣诞岛', 47, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650156900367, 1498212650131734528, '166', '科科斯（基林）群岛', 'nati_code', '科科斯（基林）群岛', 48, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094656, 1498212650131734528, '170', '哥伦比亚', 'nati_code', '哥伦比亚', 49, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094657, 1498212650131734528, '174', '科摩罗', 'nati_code', '科摩罗', 50, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094658, 1498212650131734528, '178', '刚果（布）', 'nati_code', '刚果（布）', 51, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094659, 1498212650131734528, '180', '刚果（金）', 'nati_code', '刚果（金）', 52, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094660, 1498212650131734528, '184', '库克群岛', 'nati_code', '库克群岛', 53, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094661, 1498212650131734528, '188', '哥斯达黎加', 'nati_code', '哥斯达黎加', 54, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094662, 1498212650131734528, '384', '科特迪瓦', 'nati_code', '科特迪瓦', 55, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094663, 1498212650131734528, '191', '克罗地亚', 'nati_code', '克罗地亚', 56, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094664, 1498212650131734528, '192', '古巴', 'nati_code', '古巴', 57, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094665, 1498212650131734528, '196', '塞浦路斯', 'nati_code', '塞浦路斯', 58, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094666, 1498212650131734528, '203', '捷克', 'nati_code', '捷克', 59, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094667, 1498212650131734528, '208', '丹麦', 'nati_code', '丹麦', 60, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094668, 1498212650131734528, '262', '吉布提', 'nati_code', '吉布提', 61, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094669, 1498212650131734528, '212', '多米尼克', 'nati_code', '多米尼克', 62, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094670, 1498212650131734528, '214', '多米尼加', 'nati_code', '多米尼加', 63, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094671, 1498212650131734528, '626', '东帝汶', 'nati_code', '东帝汶', 64, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650161094672, 1498212650131734528, '218', '厄瓜多尔', 'nati_code', '厄瓜多尔', 65, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288960, 1498212650131734528, '818', '埃及', 'nati_code', '埃及', 66, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288961, 1498212650131734528, '222', '萨尔瓦多', 'nati_code', '萨尔瓦多', 67, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288962, 1498212650131734528, '226', '赤道几内亚', 'nati_code', '赤道几内亚', 68, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288963, 1498212650131734528, '232', '厄立特里亚', 'nati_code', '厄立特里亚', 69, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288964, 1498212650131734528, '233', '爱沙尼亚', 'nati_code', '爱沙尼亚', 70, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288965, 1498212650131734528, '231', '埃塞俄比亚', 'nati_code', '埃塞俄比亚', 71, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288966, 1498212650131734528, '238', '福克兰群岛（马尔维纳斯）', 'nati_code', '福克兰群岛（马尔维纳斯）', 72, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288967, 1498212650131734528, '234', '法罗群岛', 'nati_code', '法罗群岛', 73, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288968, 1498212650131734528, '242', '斐济', 'nati_code', '斐济', 74, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288969, 1498212650131734528, '246', '芬兰', 'nati_code', '芬兰', 75, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288970, 1498212650131734528, '250', '法国', 'nati_code', '法国', 76, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288971, 1498212650131734528, '254', '法属圭亚那', 'nati_code', '法属圭亚那', 77, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288972, 1498212650131734528, '258', '法属波利尼西亚', 'nati_code', '法属波利尼西亚', 78, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288973, 1498212650131734528, '260', '法属南部领地', 'nati_code', '法属南部领地', 79, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288974, 1498212650131734528, '266', '加蓬', 'nati_code', '加蓬', 80, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288975, 1498212650131734528, '270', '冈比亚', 'nati_code', '冈比亚', 81, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288976, 1498212650131734528, '268', '格鲁吉亚', 'nati_code', '格鲁吉亚', 82, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288977, 1498212650131734528, '276', '德国', 'nati_code', '德国', 83, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288978, 1498212650131734528, '288', '加纳', 'nati_code', '加纳', 84, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288979, 1498212650131734528, '292', '直布罗陀', 'nati_code', '直布罗陀', 85, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650165288980, 1498212650131734528, '300', '希腊', 'nati_code', '希腊', 86, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483264, 1498212650131734528, '304', '格陵兰', 'nati_code', '格陵兰', 87, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483265, 1498212650131734528, '308', '格林纳达', 'nati_code', '格林纳达', 88, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483266, 1498212650131734528, '312', '瓜德罗普', 'nati_code', '瓜德罗普', 89, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483267, 1498212650131734528, '316', '关岛', 'nati_code', '关岛', 90, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483268, 1498212650131734528, '320', '危地马拉', 'nati_code', '危地马拉', 91, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483269, 1498212650131734528, '324', '几内亚', 'nati_code', '几内亚', 92, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483270, 1498212650131734528, '624', '几内亚比绍', 'nati_code', '几内亚比绍', 93, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483271, 1498212650131734528, '328', '圭亚那', 'nati_code', '圭亚那', 94, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483272, 1498212650131734528, '332', '海地', 'nati_code', '海地', 95, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483273, 1498212650131734528, '334', '赫德岛和麦克唐纳岛', 'nati_code', '赫德岛和麦克唐纳岛', 96, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483274, 1498212650131734528, '340', '洪都拉斯', 'nati_code', '洪都拉斯', 97, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483275, 1498212650131734528, '348', '匈牙利', 'nati_code', '匈牙利', 98, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483276, 1498212650131734528, '352', '冰岛', 'nati_code', '冰岛', 99, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483277, 1498212650131734528, '356', '印度', 'nati_code', '印度', 100, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483278, 1498212650131734528, '360', '印度尼西亚', 'nati_code', '印度尼西亚', 101, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483279, 1498212650131734528, '364', '伊朗', 'nati_code', '伊朗', 102, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483280, 1498212650131734528, '368', '伊拉克', 'nati_code', '伊拉克', 103, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650169483281, 1498212650131734528, '372', '爱尔兰', 'nati_code', '爱尔兰', 104, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677568, 1498212650131734528, '376', '以色列', 'nati_code', '以色列', 105, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677569, 1498212650131734528, '380', '意大利', 'nati_code', '意大利', 106, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677570, 1498212650131734528, '388', '牙买加', 'nati_code', '牙买加', 107, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677571, 1498212650131734528, '392', '日本', 'nati_code', '日本', 108, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677572, 1498212650131734528, '400', '约旦', 'nati_code', '约旦', 109, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677573, 1498212650131734528, '398', '哈萨克斯坦', 'nati_code', '哈萨克斯坦', 110, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677574, 1498212650131734528, '404', '肯尼亚', 'nati_code', '肯尼亚', 111, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677575, 1498212650131734528, '296', '基里巴斯', 'nati_code', '基里巴斯', 112, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677576, 1498212650131734528, '408', '朝鲜', 'nati_code', '朝鲜', 113, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677577, 1498212650131734528, '410', '韩国', 'nati_code', '韩国', 114, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677578, 1498212650131734528, '414', '科威特', 'nati_code', '科威特', 115, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677579, 1498212650131734528, '417', '吉尔吉斯斯坦', 'nati_code', '吉尔吉斯斯坦', 116, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677580, 1498212650131734528, '418', '老挝', 'nati_code', '老挝', 117, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677581, 1498212650131734528, '428', '拉脱维亚', 'nati_code', '拉脱维亚', 118, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677582, 1498212650131734528, '422', '黎巴嫩', 'nati_code', '黎巴嫩', 119, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677583, 1498212650131734528, '426', '莱索托', 'nati_code', '莱索托', 120, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677584, 1498212650131734528, '430', '利比里亚', 'nati_code', '利比里亚', 121, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677585, 1498212650131734528, '434', '利比亚', 'nati_code', '利比亚', 122, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650173677586, 1498212650131734528, '438', '列支敦士登', 'nati_code', '列支敦士登', 123, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871872, 1498212650131734528, '440', '立陶宛', 'nati_code', '立陶宛', 124, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871873, 1498212650131734528, '442', '卢森堡', 'nati_code', '卢森堡', 125, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871874, 1498212650131734528, '807', '前南巴其顿', 'nati_code', '前南巴其顿', 126, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871875, 1498212650131734528, '450', '马达加斯加', 'nati_code', '马达加斯加', 127, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871876, 1498212650131734528, '454', '马拉维', 'nati_code', '马拉维', 128, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871877, 1498212650131734528, '458', '马来西亚', 'nati_code', '马来西亚', 129, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871878, 1498212650131734528, '462', '马尔代夫', 'nati_code', '马尔代夫', 130, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871879, 1498212650131734528, '466', '马里', 'nati_code', '马里', 131, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871880, 1498212650131734528, '470', '马耳他', 'nati_code', '马耳他', 132, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871881, 1498212650131734528, '584', '马绍尔群岛', 'nati_code', '马绍尔群岛', 133, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871882, 1498212650131734528, '474', '马提尼克', 'nati_code', '马提尼克', 134, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871883, 1498212650131734528, '478', '毛里塔尼亚', 'nati_code', '毛里塔尼亚', 135, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871884, 1498212650131734528, '480', '毛里求斯', 'nati_code', '毛里求斯', 136, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871885, 1498212650131734528, '175', '马约特', 'nati_code', '马约特', 137, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871886, 1498212650131734528, '484', '墨西哥', 'nati_code', '墨西哥', 138, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871887, 1498212650131734528, '583', '密克罗尼西亚联邦', 'nati_code', '密克罗尼西亚联邦', 139, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871888, 1498212650131734528, '498', '摩尔多瓦', 'nati_code', '摩尔多瓦', 140, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871889, 1498212650131734528, '492', '摩纳哥', 'nati_code', '摩纳哥', 141, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871890, 1498212650131734528, '496', '蒙古', 'nati_code', '蒙古', 142, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871891, 1498212650131734528, '500', '蒙特塞拉特', 'nati_code', '蒙特塞拉特', 143, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871892, 1498212650131734528, '504', '摩洛哥', 'nati_code', '摩洛哥', 144, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871893, 1498212650131734528, '508', '莫桑比克', 'nati_code', '莫桑比克', 145, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650177871894, 1498212650131734528, '104', '缅甸', 'nati_code', '缅甸', 146, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066176, 1498212650131734528, '516', '纳米比亚', 'nati_code', '纳米比亚', 147, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066177, 1498212650131734528, '520', '瑙鲁', 'nati_code', '瑙鲁', 148, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066178, 1498212650131734528, '524', '尼泊尔', 'nati_code', '尼泊尔', 149, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066179, 1498212650131734528, '528', '荷兰', 'nati_code', '荷兰', 150, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066180, 1498212650131734528, '530', '荷属安的列斯', 'nati_code', '荷属安的列斯', 151, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066181, 1498212650131734528, '540', '新喀里多尼亚', 'nati_code', '新喀里多尼亚', 152, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066182, 1498212650131734528, '554', '新西兰', 'nati_code', '新西兰', 153, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066183, 1498212650131734528, '558', '尼加拉瓜', 'nati_code', '尼加拉瓜', 154, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066184, 1498212650131734528, '562', '尼日尔', 'nati_code', '尼日尔', 155, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066185, 1498212650131734528, '566', '尼日利亚', 'nati_code', '尼日利亚', 156, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066186, 1498212650131734528, '570', '纽埃', 'nati_code', '纽埃', 157, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066187, 1498212650131734528, '574', '诺福克岛', 'nati_code', '诺福克岛', 158, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066188, 1498212650131734528, '580', '北马里亚纳', 'nati_code', '北马里亚纳', 159, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066189, 1498212650131734528, '578', '挪威', 'nati_code', '挪威', 160, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066190, 1498212650131734528, '512', '阿曼', 'nati_code', '阿曼', 161, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066191, 1498212650131734528, '586', '巴基斯坦', 'nati_code', '巴基斯坦', 162, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066192, 1498212650131734528, '585', '帕劳', 'nati_code', '帕劳', 163, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066193, 1498212650131734528, '275', '巴勒斯坦', 'nati_code', '巴勒斯坦', 164, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066194, 1498212650131734528, '591', '巴拿马', 'nati_code', '巴拿马', 165, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066195, 1498212650131734528, '598', '巴布亚新几内亚', 'nati_code', '巴布亚新几内亚', 166, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066196, 1498212650131734528, '600', '巴拉圭', 'nati_code', '巴拉圭', 167, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066197, 1498212650131734528, '604', '秘鲁', 'nati_code', '秘鲁', 168, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066198, 1498212650131734528, '608', '菲律宾', 'nati_code', '菲律宾', 169, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066199, 1498212650131734528, '612', '皮特凯恩', 'nati_code', '皮特凯恩', 170, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066200, 1498212650131734528, '616', '波兰', 'nati_code', '波兰', 171, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066201, 1498212650131734528, '620', '葡萄牙', 'nati_code', '葡萄牙', 172, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066202, 1498212650131734528, '630', '波多黎各', 'nati_code', '波多黎各', 173, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066203, 1498212650131734528, '634', '卡塔尔', 'nati_code', '卡塔尔', 174, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066204, 1498212650131734528, '638', '留尼汪', 'nati_code', '留尼汪', 175, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650182066205, 1498212650131734528, '642', '罗马尼亚', 'nati_code', '罗马尼亚', 176, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260480, 1498212650131734528, '643', '俄罗斯联邦', 'nati_code', '俄罗斯联邦', 177, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260481, 1498212650131734528, '646', '卢旺达', 'nati_code', '卢旺达', 178, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260482, 1498212650131734528, '654', '圣赫勒拿', 'nati_code', '圣赫勒拿', 179, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260483, 1498212650131734528, '659', '圣基茨和尼维斯', 'nati_code', '圣基茨和尼维斯', 180, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260484, 1498212650131734528, '662', '圣卢西亚', 'nati_code', '圣卢西亚', 181, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260485, 1498212650131734528, '666', '圣皮埃尔和密克隆', 'nati_code', '圣皮埃尔和密克隆', 182, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260486, 1498212650131734528, '670', '圣文森特和格林纳丁斯', 'nati_code', '圣文森特和格林纳丁斯', 183, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260487, 1498212650131734528, '882', '萨摩亚', 'nati_code', '萨摩亚', 184, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260488, 1498212650131734528, '674', '圣马力诺', 'nati_code', '圣马力诺', 185, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260489, 1498212650131734528, '678', '圣多美和普林西比', 'nati_code', '圣多美和普林西比', 186, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260490, 1498212650131734528, '682', '沙特阿拉伯', 'nati_code', '沙特阿拉伯', 187, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260491, 1498212650131734528, '686', '塞内加尔', 'nati_code', '塞内加尔', 188, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260492, 1498212650131734528, '690', '塞舌尔', 'nati_code', '塞舌尔', 189, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260493, 1498212650131734528, '694', '塞拉利昂', 'nati_code', '塞拉利昂', 190, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260494, 1498212650131734528, '702', '新加坡', 'nati_code', '新加坡', 191, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260495, 1498212650131734528, '703', '斯洛伐克', 'nati_code', '斯洛伐克', 192, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260496, 1498212650131734528, '705', '斯洛文尼亚', 'nati_code', '斯洛文尼亚', 193, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260497, 1498212650131734528, '90', '所罗门群岛', 'nati_code', '所罗门群岛', 194, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260498, 1498212650131734528, '706', '索马里', 'nati_code', '索马里', 195, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260499, 1498212650131734528, '710', '南非', 'nati_code', '南非', 196, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260500, 1498212650131734528, '239', '南乔治亚岛和南桑德韦奇岛', 'nati_code', '南乔治亚岛和南桑德韦奇岛', 197, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260501, 1498212650131734528, '724', '西班牙', 'nati_code', '西班牙', 198, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260502, 1498212650131734528, '144', '斯里兰卡', 'nati_code', '斯里兰卡', 199, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260503, 1498212650131734528, '736', '苏丹', 'nati_code', '苏丹', 200, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260504, 1498212650131734528, '740', '苏里南', 'nati_code', '苏里南', 201, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260505, 1498212650131734528, '744', '斯瓦尔巴岛和扬马延岛', 'nati_code', '斯瓦尔巴岛和扬马延岛', 202, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260506, 1498212650131734528, '748', '斯威士兰', 'nati_code', '斯威士兰', 203, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260507, 1498212650131734528, '752', '瑞典', 'nati_code', '瑞典', 204, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260508, 1498212650131734528, '756', '瑞士', 'nati_code', '瑞士', 205, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650186260509, 1498212650131734528, '760', '叙利亚', 'nati_code', '叙利亚', 206, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454784, 1498212650131734528, '762', '塔吉克斯坦', 'nati_code', '塔吉克斯坦', 207, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454785, 1498212650131734528, '834', '坦桑尼亚', 'nati_code', '坦桑尼亚', 208, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454786, 1498212650131734528, '764', '泰国', 'nati_code', '泰国', 209, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454787, 1498212650131734528, '768', '多哥', 'nati_code', '多哥', 210, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454788, 1498212650131734528, '772', '托克劳', 'nati_code', '托克劳', 211, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454789, 1498212650131734528, '776', '汤加', 'nati_code', '汤加', 212, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454790, 1498212650131734528, '780', '特立尼克和多巴哥', 'nati_code', '特立尼克和多巴哥', 213, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454791, 1498212650131734528, '788', '突尼斯', 'nati_code', '突尼斯', 214, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454792, 1498212650131734528, '792', '土耳其', 'nati_code', '土耳其', 215, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454793, 1498212650131734528, '795', '土库曼斯坦', 'nati_code', '土库曼斯坦', 216, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454794, 1498212650131734528, '796', '特克斯和凯科斯群岛', 'nati_code', '特克斯和凯科斯群岛', 217, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650190454795, 1498212650131734528, '798', '图瓦卢', 'nati_code', '图瓦卢', 218, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649088, 1498212650131734528, '800', '乌干达', 'nati_code', '乌干达', 219, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649089, 1498212650131734528, '804', '乌克兰', 'nati_code', '乌克兰', 220, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649090, 1498212650131734528, '784', '阿联酋', 'nati_code', '阿联酋', 221, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649091, 1498212650131734528, '826', '英国', 'nati_code', '英国', 222, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649092, 1498212650131734528, '840', '美国', 'nati_code', '美国', 223, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649093, 1498212650131734528, '581', '美国本土外小岛屿', 'nati_code', '美国本土外小岛屿', 224, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649094, 1498212650131734528, '858', '乌拉圭', 'nati_code', '乌拉圭', 225, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649095, 1498212650131734528, '860', '乌兹别克斯坦', 'nati_code', '乌兹别克斯坦', 226, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649096, 1498212650131734528, '548', '瓦努阿图', 'nati_code', '瓦努阿图', 227, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649097, 1498212650131734528, '336', '梵蒂冈', 'nati_code', '梵蒂冈', 228, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649098, 1498212650131734528, '862', '委内瑞拉', 'nati_code', '委内瑞拉', 229, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649099, 1498212650131734528, '704', '越南', 'nati_code', '越南', 230, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649100, 1498212650131734528, '92', '英属维尔京群岛', 'nati_code', '英属维尔京群岛', 231, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649101, 1498212650131734528, '850', '美属维尔京群岛', 'nati_code', '美属维尔京群岛', 232, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649102, 1498212650131734528, '876', '瓦利斯和富图纳', 'nati_code', '瓦利斯和富图纳', 233, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649103, 1498212650131734528, '732', '西撒哈拉', 'nati_code', '西撒哈拉', 234, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649104, 1498212650131734528, '887', '也门', 'nati_code', '也门', 235, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649105, 1498212650131734528, '891', '南斯拉夫', 'nati_code', '南斯拉夫', 236, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649106, 1498212650131734528, '894', '赞比亚', 'nati_code', '赞比亚', 237, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212650194649107, 1498212650131734528, '716', '津巴布韦', 'nati_code', '津巴布韦', 238, '', '0', '2022-02-28 16:25:04', '2022-02-28 16:25:04');
COMMIT;

-- ----------------------------
-- Records of 国际地区代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212961785294848, 'nati_area_code', '国际地区代码', '国际地区代码', '0', '0', '2022-02-28 16:26:18', '2022-02-28 16:26:18');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212961789489152, 1498212961785294848, '852', '香港', 'nati_area_code', '香港', 0, '', '0', '2022-02-28 16:26:18', '2022-02-28 16:26:18');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212961789489153, 1498212961785294848, '853', '澳门', 'nati_area_code', '澳门', 1, '', '0', '2022-02-28 16:26:18', '2022-02-28 16:26:18');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498212961789489154, 1498212961785294848, '886', '台湾', 'nati_area_code', '台湾', 2, '', '0', '2022-02-28 16:26:18', '2022-02-28 16:26:18');
COMMIT;

-- ----------------------------
-- Records of 民族代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238202486784, 'race_code', '民族代码', '民族代码', '0', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238206681088, 1498213238202486784, 'HA', '汉族', 'race_code', '汉族', 0, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238206681089, 1498213238202486784, 'MG', '蒙古族', 'race_code', '蒙古族', 1, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238206681090, 1498213238202486784, 'HU', '回族', 'race_code', '回族', 2, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238206681091, 1498213238202486784, 'ZA', '藏族', 'race_code', '藏族', 3, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238206681092, 1498213238202486784, 'UG', '维吾尔族', 'race_code', '维吾尔族', 4, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238206681093, 1498213238202486784, 'MH', '苗族', 'race_code', '苗族', 5, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238210875392, 1498213238202486784, 'YI', '彝族', 'race_code', '彝族', 6, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238210875393, 1498213238202486784, 'ZH', '壮族', 'race_code', '壮族', 7, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238210875394, 1498213238202486784, 'BY', '布依族', 'race_code', '布依族', 8, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238210875395, 1498213238202486784, 'CS', '朝鲜族', 'race_code', '朝鲜族', 9, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238210875396, 1498213238202486784, 'MA', '满族', 'race_code', '满族', 10, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238210875397, 1498213238202486784, 'DO', '侗族', 'race_code', '侗族', 11, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238215069696, 1498213238202486784, 'YA', '瑶族', 'race_code', '瑶族', 12, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238215069697, 1498213238202486784, 'BA', '白族', 'race_code', '白族', 13, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238215069698, 1498213238202486784, 'TJ', '土家族', 'race_code', '土家族', 14, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238215069699, 1498213238202486784, 'HN', '哈尼族', 'race_code', '哈尼族', 15, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238215069700, 1498213238202486784, 'KZ', '哈萨克族', 'race_code', '哈萨克族', 16, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238219264000, 1498213238202486784, 'DA', '傣族', 'race_code', '傣族', 17, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238219264001, 1498213238202486784, 'LI', '黎族', 'race_code', '黎族', 18, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238219264002, 1498213238202486784, 'LS', '傈僳族', 'race_code', '傈僳族', 19, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238219264003, 1498213238202486784, 'VA', '佤族', 'race_code', '佤族', 20, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238219264004, 1498213238202486784, 'SH', '畲族', 'race_code', '畲族', 21, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238219264005, 1498213238202486784, 'GS', '高山族', 'race_code', '高山族', 22, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238219264006, 1498213238202486784, 'LH', '拉祜族', 'race_code', '拉祜族', 23, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458304, 1498213238202486784, 'SU', '水族', 'race_code', '水族', 24, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458305, 1498213238202486784, 'DX', '东乡族', 'race_code', '东乡族', 25, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458306, 1498213238202486784, 'NX', '纳西族', 'race_code', '纳西族', 26, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458307, 1498213238202486784, 'JP', '景颇族', 'race_code', '景颇族', 27, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458308, 1498213238202486784, 'KG', '柯尔克孜族', 'race_code', '柯尔克孜族', 28, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458309, 1498213238202486784, 'TU', '土族', 'race_code', '土族', 29, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458310, 1498213238202486784, 'DU', '达斡尔族', 'race_code', '达斡尔族', 30, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458311, 1498213238202486784, 'ML', '仫佬族', 'race_code', '仫佬族', 31, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458312, 1498213238202486784, 'QI', '羌族', 'race_code', '羌族', 32, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458313, 1498213238202486784, 'BL', '布朗族', 'race_code', '布朗族', 33, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458314, 1498213238202486784, 'SL', '撒拉族', 'race_code', '撒拉族', 34, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458315, 1498213238202486784, 'MN', '毛难族', 'race_code', '毛难族', 35, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458316, 1498213238202486784, 'GL', '仡佬族', 'race_code', '仡佬族', 36, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458317, 1498213238202486784, 'XB', '锡伯族', 'race_code', '锡伯族', 37, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238223458318, 1498213238202486784, 'AC', '阿昌族', 'race_code', '阿昌族', 38, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652608, 1498213238202486784, 'PM', '普米族', 'race_code', '普米族', 39, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652609, 1498213238202486784, 'TA', '塔吉克族', 'race_code', '塔吉克族', 40, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652610, 1498213238202486784, 'NU', '怒族', 'race_code', '怒族', 41, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652611, 1498213238202486784, 'UZ', '乌孜别克族', 'race_code', '乌孜别克族', 42, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652612, 1498213238202486784, 'RS', '俄罗斯族', 'race_code', '俄罗斯族', 43, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652613, 1498213238202486784, 'EW', '鄂温克族', 'race_code', '鄂温克族', 44, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652614, 1498213238202486784, 'DE', '德昂族', 'race_code', '德昂族', 45, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652615, 1498213238202486784, 'BN', '保安族', 'race_code', '保安族', 46, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652616, 1498213238202486784, 'YG', '裕固族', 'race_code', '裕固族', 47, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652617, 1498213238202486784, 'GI', '京族', 'race_code', '京族', 48, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652618, 1498213238202486784, 'TT', '塔塔尔族', 'race_code', '塔塔尔族', 49, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652619, 1498213238202486784, 'DR', '独龙族', 'race_code', '独龙族', 50, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238227652620, 1498213238202486784, 'OR', '鄂伦春族', 'race_code', '鄂伦春族', 51, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238231846912, 1498213238202486784, 'HZ', '赫哲族', 'race_code', '赫哲族', 52, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238231846913, 1498213238202486784, 'MB', '门巴族', 'race_code', '门巴族', 53, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238231846914, 1498213238202486784, 'LB', '珞巴族', 'race_code', '珞巴族', 54, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213238231846915, 1498213238202486784, 'JN', '基诺族', 'race_code', '基诺族', 55, '', '0', '2022-02-28 16:27:24', '2022-02-28 16:27:24');
COMMIT;

-- ----------------------------
-- Records of 学历代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528729370624, 'edu_code', '学历代码', '学历代码', '0', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528733564928, 1498213528729370624, '0', '研究生', 'edu_code', '研究生', 0, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528733564929, 1498213528729370624, '1', '研究生毕业', 'edu_code', '研究生毕业', 1, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528733564930, 1498213528729370624, '9', '研究生肄业', 'edu_code', '研究生肄业', 2, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528733564931, 1498213528729370624, '10', '大学本科（简称大学）', 'edu_code', '大学本科（简称大学）', 3, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528733564932, 1498213528729370624, '11', '大学毕业', 'edu_code', '大学毕业', 4, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528737759232, 1498213528729370624, '18', '相当大学毕业', 'edu_code', '相当大学毕业', 5, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528737759233, 1498213528729370624, '19', '大学肄业', 'edu_code', '大学肄业', 6, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528737759234, 1498213528729370624, '20', '大学专科和专科学校', 'edu_code', '大学专科和专科学校', 7, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528737759235, 1498213528729370624, '21', '专科毕业', 'edu_code', '专科毕业', 8, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953536, 1498213528729370624, '28', '相当专科毕业', 'edu_code', '相当专科毕业', 9, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953537, 1498213528729370624, '29', '专科肄业', 'edu_code', '专科肄业', 10, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953538, 1498213528729370624, '30', '中等专业学校或中等技术学校', 'edu_code', '中等专业学校或中等技术学校', 11, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953539, 1498213528729370624, '31', '中专毕业', 'edu_code', '中专毕业', 12, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953540, 1498213528729370624, '32', '中技毕业', 'edu_code', '中技毕业', 13, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953541, 1498213528729370624, '38', '相当中专或中技毕业', 'edu_code', '相当中专或中技毕业', 14, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953542, 1498213528729370624, '39', '中专或中技肄业', 'edu_code', '中专或中技肄业', 15, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953543, 1498213528729370624, '40', '技工学校', 'edu_code', '技工学校', 16, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953544, 1498213528729370624, '41', '技工学校毕业', 'edu_code', '技工学校毕业', 17, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528741953545, 1498213528729370624, '49', '技工学校肄业', 'edu_code', '技工学校肄业', 18, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147840, 1498213528729370624, '50', '高中', 'edu_code', '高中', 19, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147841, 1498213528729370624, '51', '高中毕业', 'edu_code', '高中毕业', 20, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147842, 1498213528729370624, '52', '职业高中毕业', 'edu_code', '职业高中毕业', 21, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147843, 1498213528729370624, '53', '农业高中毕业', 'edu_code', '农业高中毕业', 22, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147844, 1498213528729370624, '58', '相当高中毕业', 'edu_code', '相当高中毕业', 23, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147845, 1498213528729370624, '59', '高中肄业', 'edu_code', '高中肄业', 24, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147846, 1498213528729370624, '60', '初中', 'edu_code', '初中', 25, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147847, 1498213528729370624, '61', '初中毕业', 'edu_code', '初中毕业', 26, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147848, 1498213528729370624, '62', '职业初中毕业', 'edu_code', '职业初中毕业', 27, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147849, 1498213528729370624, '63', '农业初中毕业', 'edu_code', '农业初中毕业', 28, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528746147850, 1498213528729370624, '68', '相当初中毕业', 'edu_code', '相当初中毕业', 29, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528750342144, 1498213528729370624, '69', '初中肄业', 'edu_code', '初中肄业', 30, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528750342145, 1498213528729370624, '70', '小学', 'edu_code', '小学', 31, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528750342146, 1498213528729370624, '71', '小学毕业', 'edu_code', '小学毕业', 32, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528750342147, 1498213528729370624, '78', '相当小学毕业', 'edu_code', '相当小学毕业', 33, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528750342148, 1498213528729370624, '79', '小学肄业', 'edu_code', '小学肄业', 34, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498213528750342149, 1498213528729370624, '80', '文盲或半文盲', 'edu_code', '文盲或半文盲', 35, '', '0', '2022-02-28 16:28:33', '2022-02-28 16:28:33');
COMMIT;

-- ----------------------------
-- Records of 户籍代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088316559360, 'hreg_type_code', '户籍代码', '户籍代码', '0', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088320753664, 1498214088316559360, '0', '未落常住户口', 'hreg_type_code', '未落常住户口', 0, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088320753665, 1498214088316559360, '1', '非农业家庭户口', 'hreg_type_code', '非农业家庭户口', 1, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088320753666, 1498214088316559360, '2', '农业家庭户口', 'hreg_type_code', '农业家庭户口', 2, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088324947968, 1498214088316559360, '3', '非农业集体户口', 'hreg_type_code', '非农业集体户口', 3, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088324947969, 1498214088316559360, '4', '农业集体户口', 'hreg_type_code', '农业集体户口', 4, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088324947970, 1498214088316559360, '5', '自理口粮户口', 'hreg_type_code', '自理口粮户口', 5, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088324947971, 1498214088316559360, '6', '寄住户口', 'hreg_type_code', '寄住户口', 6, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088324947972, 1498214088316559360, '7', '暂住户口', 'hreg_type_code', '暂住户口', 7, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214088324947973, 1498214088316559360, '8', '其它户口', 'hreg_type_code', '其它户口', 8, '', '0', '2022-02-28 16:30:47', '2022-02-28 16:30:47');
COMMIT;

-- ----------------------------
-- Records of 病人所属地类型
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214334992035840, 'pt_local_type_code', '病人所属地类型', '病人所属地类型', '0', '0', '2022-02-28 16:31:46', '2022-02-28 16:31:46');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214334996230144, 1498214334992035840, '1', '本地', 'pt_local_type_code', '本地', 0, '', '0', '2022-02-28 16:31:46', '2022-02-28 16:31:46');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498214334996230145, 1498214334992035840, '2', '异地', 'pt_local_type_code', '异地', 1, '', '0', '2022-02-28 16:31:46', '2022-02-28 16:31:46');
COMMIT;

-- ----------------------------
-- Records of 人群分类代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585976795136, 'popu_type_code', '人群分类代码', '人群分类代码', '0', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585980989440, 1498215585976795136, '1', '国家机关、党群组织、企业、事业单位负责人', 'popu_type_code', '国家机关、党群组织、企业、事业单位负责人', 0, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585980989441, 1498215585976795136, '2', '专业技术人员', 'popu_type_code', '专业技术人员', 1, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585980989442, 1498215585976795136, '3', '办事人员和有关人员', 'popu_type_code', '办事人员和有关人员', 2, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585980989443, 1498215585976795136, '4', '商业、服务业人员', 'popu_type_code', '商业、服务业人员', 3, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585985183744, 1498215585976795136, '5', '农、林、牧、渔、水利业生产人员', 'popu_type_code', '农、林、牧、渔、水利业生产人员', 4, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585985183745, 1498215585976795136, '6', '生产、运输设备操作人员及有关人员', 'popu_type_code', '生产、运输设备操作人员及有关人员', 5, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585985183746, 1498215585976795136, '7', '军人', 'popu_type_code', '军人', 6, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498215585985183747, 1498215585976795136, '8', '不便分类的其他从业人员', 'popu_type_code', '不便分类的其他从业人员', 7, '', '0', '2022-02-28 16:36:44', '2022-02-28 16:36:44');
COMMIT;

-- ----------------------------
-- Records of 婚姻状况代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498216658443517952, 'mari_sta_code', '婚姻状况代码', '婚姻状况代码', '0', '0', '2022-02-28 16:40:59', '2022-02-28 16:40:59');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498216658447712256, 1498216658443517952, '10', '未婚', 'mari_sta_code', '未婚', 0, '', '0', '2022-02-28 16:40:59', '2022-02-28 16:40:59');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498216658447712257, 1498216658443517952, '20', '已婚', 'mari_sta_code', '已婚', 1, '', '0', '2022-02-28 16:40:59', '2022-02-28 16:40:59');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498216658447712258, 1498216658443517952, '30', '丧偶', 'mari_sta_code', '丧偶', 2, '', '0', '2022-02-28 16:40:59', '2022-02-28 16:40:59');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498216658447712259, 1498216658443517952, '40', '离婚', 'mari_sta_code', '离婚', 3, '', '0', '2022-02-28 16:40:59', '2022-02-28 16:40:59');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498216658447712260, 1498216658443517952, '90', '未说明的婚姻状况', 'mari_sta_code', '未说明的婚姻状况', 4, '', '0', '2022-02-28 16:40:59', '2022-02-28 16:40:59');
COMMIT;

-- ----------------------------
-- Records of 家庭关系代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115718971392, 'cnta_rel_code', '家庭关系代码', '家庭关系代码', '0', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115723165696, 1498483115718971392, '0', '本人或户主', 'cnta_rel_code', '本人或户主', 0, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115723165697, 1498483115718971392, '1', '配偶', 'cnta_rel_code', '配偶', 1, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115723165698, 1498483115718971392, '2', '子', 'cnta_rel_code', '子', 2, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115723165699, 1498483115718971392, '3', '女', 'cnta_rel_code', '女', 3, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115723165700, 1498483115718971392, '4', '孙子、孙女，或外孙子、外孙女', 'cnta_rel_code', '孙子、孙女，或外孙子、外孙女', 4, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115727360000, 1498483115718971392, '5', '父母', 'cnta_rel_code', '父母', 5, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115727360001, 1498483115718971392, '6', '祖父母或外祖父母', 'cnta_rel_code', '祖父母或外祖父母', 6, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115727360002, 1498483115718971392, '7', '兄、弟、姐、妹', 'cnta_rel_code', '兄、弟、姐、妹', 7, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483115727360003, 1498483115718971392, '8', '其他', 'cnta_rel_code', '其他', 8, '', '0', '2022-03-01 10:19:48', '2022-03-01 10:19:48');
COMMIT;

-- ----------------------------
-- Records of 症状代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483472524210176, 'symp_code', '症状代码', '症状代码', '0', '0', '2022-03-01 10:21:13', '2022-03-01 10:21:13');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483472524210177, 1498483472524210176, '1', '咳嗽、咳痰2周以上', 'symp_code', '咳嗽、咳痰2周以上', 0, '', '0', '2022-03-01 10:21:13', '2022-03-01 10:21:13');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483472524210178, 1498483472524210176, '2', '咯血或血痰', 'symp_code', '咯血或血痰', 1, '', '0', '2022-03-01 10:21:13', '2022-03-01 10:21:13');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483472524210179, 1498483472524210176, '3', '胸痛、胸闷、低热、盗汗、乏力', 'symp_code', '胸痛、胸闷、低热、盗汗、乏力', 2, '', '0', '2022-03-01 10:21:13', '2022-03-01 10:21:13');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483472528404480, 1498483472524210176, '4', '食欲减退和体重减轻', 'symp_code', '食欲减退和体重减轻', 3, '', '0', '2022-03-01 10:21:13', '2022-03-01 10:21:13');
COMMIT;

-- ----------------------------
-- Records of 本次随访内容代码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483734319992832, 'flup_cont_code', '本次随访内容代码', '本次随访内容代码', '0', '0', '2022-03-01 10:22:15', '2022-03-01 10:22:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483734324187136, 1498483734319992832, '1', '临床随访/领药+实验室检测', 'flup_cont_code', '临床随访/领药+实验室检测', 0, '', '0', '2022-03-01 10:22:15', '2022-03-01 10:22:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483734324187137, 1498483734319992832, '2', '临床随访/领药', 'flup_cont_code', '临床随访/领药', 1, '', '0', '2022-03-01 10:22:15', '2022-03-01 10:22:15');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498483734324187138, 1498483734319992832, '3', '他人代替领药', 'flup_cont_code', '他人代替领药', 2, '', '0', '2022-03-01 10:22:15', '2022-03-01 10:22:15');
COMMIT;

-- ----------------------------
-- Records of 追踪状态编码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484010766635008, 'trac_sts_code', '追踪状态编码', '追踪状态编码', '0', '0', '2022-03-01 10:23:21', '2022-03-01 10:23:21');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484010766635009, 1498484010766635008, '1', '未跟踪', 'trac_sts_code', '未跟踪', 0, '', '0', '2022-03-01 10:23:21', '2022-03-01 10:23:21');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484010766635010, 1498484010766635008, '2', '已跟踪', 'trac_sts_code', '已跟踪', 1, '', '0', '2022-03-01 10:23:21', '2022-03-01 10:23:21');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484010766635011, 1498484010766635008, '3', '联系未果', 'trac_sts_code', '联系未果', 2, '', '0', '2022-03-01 10:23:21', '2022-03-01 10:23:21');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484010770829312, 1498484010766635008, '4', '转发区县疾控', 'trac_sts_code', '转发区县疾控', 3, '', '0', '2022-03-01 10:23:21', '2022-03-01 10:23:21');
COMMIT;

-- ----------------------------
-- Records of 追踪方法编码
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`(`id`, `type`, `description`, `remarks`, `system_flag`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484254942167040, 'trac_meth_code', '追踪方法编码', '追踪方法编码', '0', '0', '2022-03-01 10:24:20', '2022-03-01 10:24:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484254942167041, 1498484254942167040, '1', '电话', 'trac_meth_code', '电话', 0, '', '0', '2022-03-01 10:24:20', '2022-03-01 10:24:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484254942167042, 1498484254942167040, '2', '视频', 'trac_meth_code', '视频', 1, '', '0', '2022-03-01 10:24:20', '2022-03-01 10:24:20');
INSERT INTO `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `remarks`, `del_flag`, `create_time`, `update_time`)
VALUES (1498484254946361344, 1498484254942167040, '3', '现场', 'trac_meth_code', '现场', 2, '', '0', '2022-03-01 10:24:20', '2022-03-01 10:24:20');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
