DROP DATABASE IF EXISTS `booster_infection`;

CREATE DATABASE  `booster_infection` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `booster_infection`;

-- ----------------------------
-- Table structure for infc_rpt_gz
-- ----------------------------
DROP TABLE IF EXISTS `infc_rpt_gz`;
CREATE TABLE `infc_rpt_gz`  (
    `id` bigint NOT NULL COMMENT '主键',
    `infec_card_no` varchar(20) COMMENT '卡片编号',
    `patient_name` varchar(50) COMMENT '患者姓名',
    `id_doc_type_code` varchar(2) COMMENT '身份证件类别代码',
    `id_no` varchar(18) COMMENT '身份证件号码',
    `ged_code` varchar(2) COMMENT '性别代码',
    `birthdate` Date COMMENT '出生日期',
    `nati_code` varchar(3) COMMENT '国籍代码',
    `nati_area_code` varchar(3) COMMENT '国际地区代码',
    `race_code` varchar(2) COMMENT '民族代码',
    `edu_code` varchar(2) COMMENT '学历代码',
    `hreg_type_code` varchar(2) COMMENT '户籍代码',
    `herg_area_code` varchar(14) COMMENT '户籍地区代码',
    `hreg_addr_detl` varchar(200) COMMENT '户籍详细地址',
    `pre_addr_type_code` varchar(2) COMMENT '现住址类别代码',
    `pre_addr_area_code` varchar(14) COMMENT '现住地区代码',
    `pre_addr_detl` varchar(200) COMMENT '现住详细地址',
    `pt_local_type_code` varchar(2) COMMENT '病人所属地类型',
    `org_sch_name` varchar(70) COMMENT '工作单位/学校名称',
    `popu_type_code` varchar(2) COMMENT '人群分类代码',
    `popu_type_otr` varchar(50) COMMENT '人群分类其他',
    `mari_sta_code` varchar(2) COMMENT '婚姻状况代码',
    `cnta` varchar(50) COMMENT '联系人/监护人姓名',
    `cnta_tel` varchar(20) COMMENT '联系人/监护人电话号码',
    `cnta_rel_code` varchar(2) COMMENT '联系人/监护人与本人关系代码',
    `rpt_area_code` varchar(14) COMMENT '报告地区代码',
    `rpt_org_code` varchar(22) COMMENT '报告单位代码',
    `rep_date` Date COMMENT '报告日期',
    `ill_beg_date` Date COMMENT '发病日期',
    `diag_date` DateTime COMMENT '诊断日期时间',
    `diag_dise_code` varchar(5) COMMENT '疾病诊断代码',
    `diag_dise_name` varchar(50) COMMENT '疾病名称',
    `diag_sta_type_code` varchar(2) COMMENT '诊断状态',
    `case_type_code` varchar(2) COMMENT '病例分类',
    `clic_serv_code` varchar(2) COMMENT '临床严重程度',
    `imp_case_code` varchar(1) COMMENT '输入病例类型',
    `imp_src_nation_code` varchar(10) COMMENT '输入来源地',
    `imp_src_nation_otr_name` varchar(50) COMMENT '输入来源地-其他国家',
    `die_date` Date COMMENT '死亡日期',
    `dr_name` varchar(50) COMMENT '医师姓名',
    `crt_area_code` varchar(14) COMMENT '添加地区',
    `crt_org_code` varchar(100) COMMENT '添加机代号',
    `crt_org_name` varchar(100) COMMENT '添加机构名称',
    `crt_usr_id` int(11) COMMENT '添加用户ID',
    `crt_usr_name` varchar(50) COMMENT '添加用户名称',
    `crt_time` DateTime COMMENT '添加时间',
    `upt_area_code` varchar(14) COMMENT '修改地区',
    `upt_org_code` varchar(22) COMMENT '修改机构',
    `upt_org_name` varchar(100) COMMENT '修改机构名称',
    `upt_usr_id` int(11) COMMENT '修改用户ID',
    `upt_time` DateTime COMMENT '修改时间',
    `sts` TinyInt(1) COMMENT '是否有效',
    `rmk` varchar(2000) COMMENT '备注',
    `tot_audi_sts` TinyInt(1) COMMENT '总体审核状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '传染病个案报告卡';

-- ----------------------------
-- Table structure for rpt_audit
-- ----------------------------
DROP TABLE IF EXISTS `rpt_audit`;
CREATE TABLE `rpt_audit`  (
    `id` bigint NOT NULL COMMENT '主键',
    `sour_id` bigint NOT NULL COMMENT '业务数据外键',
    `sour_table` varchar(20) COMMENT '来源业务表',
    `nati_audit_time` DateTime COMMENT '国家级审核时间',
    `nati_audit_usr` bigint(8) COMMENT '国家级审核人',
    `nati_audit_sts` varchar(2) COMMENT '国家级审核状态',
    `nati_audit_comm` varchar(1000) COMMENT '国家级审核意见',
    `prov_audit_time` DateTime COMMENT '省级审核时间',
    `prov_audit_usr` bigint(8) COMMENT '省级审核人',
    `prov_audit_sts` varchar(2) COMMENT '省级状态',
    `prov_audit_comm` varchar(1000) COMMENT '省级审核意见',
    `muni_audit_time` DateTime COMMENT '市级审核时间',
    `muni_audit_usr` bigint(8) COMMENT '市级审核人',
    `muni_audit_sts` varchar(2) COMMENT '市级审核状态',
    `muni_audit_comm` varchar(1000) COMMENT '市级审核意见',
    `coun_audit_time` DateTime COMMENT '县级审核时间',
    `coun_audit_usr` bigint(8) COMMENT '县级审核人',
    `coun_audit_sts` varchar(2) COMMENT '县级审核状态',
    `coun_audit_comm` varchar(1000) COMMENT '县级审核意见',
    `crt_area_code` varchar(14) COMMENT '添加地区',
    `crt_org_code` varchar(100) COMMENT '添加机构代号',
    `crt_org_name` varchar(100) COMMENT '添加机构名称',
    `crt_usr_id` int(11) COMMENT '添加用户ID',
    `crt_usr_name` varchar(50) COMMENT '添加用户名称',
    `crt_time` DateTime COMMENT '添加时间',
    `upt_area_code` varchar(14) COMMENT '修改地区',
    `upt_org_code` varchar(22) COMMENT '修改机构',
    `upt_org_name` varchar(100) COMMENT '修改机构名称',
    `upt_usr_id` int(11) COMMENT '修改用户ID',
    `upt_time` DateTime COMMENT '修改时间',
    `sts` TinyInt(1) COMMENT '是否有效',
    `rmk` varchar(2000) COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '传染病报告卡审核信息';

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
    `id` bigint NOT NULL COMMENT '主键',
    `infc_rpt_id` bigint NOT NULL COMMENT '外键',
    `samp_no` varchar(15) COMMENT '检查标本号',
    `samp_take_time` DateTime COMMENT '标本采样日期时间',
    `take_samp_date` Date COMMENT '检查日期',
    `samp_type_code` varchar(3) COMMENT '样本类别代码',
    `take_org_area_code` varchar(14) COMMENT '采样单位所属地区代码',
    `take_org_code` varchar(22) COMMENT '采样单位代码',
    `recv_samp_date` DateTime COMMENT '接收标本日期时间',
    `test_type_code` varchar(2) COMMENT '检测类别代码',
    `test_item_code` varchar(2) COMMENT '检验项目代码',
    `test_meth_code` varchar(2) COMMENT '检测方法代码',
    `test_rest_code` varchar(2) COMMENT '检测结果代码',
    `test_rest_munit_code` varchar(2) COMMENT '检测结果计量单位代码',
    `test_rpt_org_area_code` varchar(14) COMMENT '检测报告机构所属地区代码',
    `test_rpt_org_code` varchar(22) COMMENT '检测报告机构代码',
    `test_rpt_date` Date COMMENT '检测报告日期',
    `crt_area_code` varchar(14) COMMENT '添加地区',
    `crt_org_code` varchar(100) COMMENT '添加机构代号',
    `crt_org_name` varchar(100) COMMENT '添加机构名称',
    `crt_usr_id` int(11) COMMENT '添加用户ID',
    `crt_usr_name` varchar(50) COMMENT '添加用户名称',
    `crt_time` DateTime COMMENT '添加时间',
    `upt_area_code` varchar(14) COMMENT '修改地区',
    `upt_org_code` varchar(22) COMMENT '修改机构',
    `upt_org_name` varchar(100) COMMENT '修改机构名称',
    `upt_usr_id` int(11) COMMENT '修改用户ID',
    `upt_time` DateTime COMMENT '修改时间',
    `sts` TinyInt(1) COMMENT '是否有效',
    `rmk` varchar(2000) COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '检验检测信息';

-- ----------------------------
-- Table structure for trt
-- ----------------------------
DROP TABLE IF EXISTS `trt`;
CREATE TABLE `trt`  (
    `id` bigint NOT NULL COMMENT '主键',
    `infc_rpt_id` bigint NOT NULL COMMENT '外键',
    `trt_date` Date COMMENT '治疗日期',
    `trt_type_code` varchar(2) COMMENT '治疗类型代码',
    `trt_plan_code` varchar(3) COMMENT '治疗方案代码',
    `beg_trt_date` Date COMMENT '开始治疗日期',
    `beg_ae_date` Date COMMENT '不良反应出现日期',
    `adr_diag_date` Date COMMENT '不良反应确诊日期',
    `adr_diag_code` varchar(5) COMMENT '不良反应诊断代码',
    `adr_drug_code` varchar(3) COMMENT '不良反应发生药物代码',
    `end_trt_date` Date COMMENT '停止治疗日期',
    `end_trt_caus_code` varchar(2) COMMENT '停止治疗原因代码',
    `otcm_code` varchar(2) COMMENT '病情转归代码',
    `ihos_date` Date COMMENT '入院日期',
    `ohos_date` Date COMMENT '出院日期',
    `case_no` varchar(20) COMMENT '登记号/治疗号',
    `crt_area_code` varchar(14) COMMENT '添加地区',
    `crt_org_code` varchar(100) COMMENT '添加机构代号',
    `crt_org_name` varchar(100) COMMENT '添加机构名称',
    `crt_usr_id` int(11) COMMENT '添加用户ID',
    `crt_usr_name` varchar(50) COMMENT '添加用户名称',
    `crt_time` DateTime COMMENT '添加时间',
    `upt_area_code` varchar(14) COMMENT '修改地区',
    `upt_org_code` varchar(22) COMMENT '修改机构',
    `upt_org_name` varchar(100) COMMENT '修改机构名称',
    `upt_usr_id` int(11) COMMENT '修改用户ID',
    `upt_time` DateTime COMMENT '修改时间',
    `sts` TinyInt(1) COMMENT '是否有效',
    `rmk` varchar(2000) COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '治疗用药信息';

-- ----------------------------
-- Table structure for flup
-- ----------------------------
DROP TABLE IF EXISTS `flup`;
CREATE TABLE `flup`  (
    `id` bigint NOT NULL COMMENT '主键',
    `infc_rpt_id` bigint NOT NULL COMMENT '外键',
    `flup_date` Date COMMENT '本次随访日期',
    `flup_type_code` varchar(2) COMMENT '随访方式代码',
    `flup_sts_code` varchar(2) COMMENT '随访状态代码',
    `symp_code` varchar(4) COMMENT '症状代码',
    `flup_cont` varchar(2) COMMENT '本次随访内容代码',
    `inve_name` varchar(40) COMMENT '调查人姓名',
    `flup_rpt_org_area_code` varchar(14) COMMENT '随访报告机构所属地区代码',
    `flup_rpt_org_code` varchar(22) COMMENT '随访报告机构代码',
    `crt_area_code` varchar(14) COMMENT '添加地区',
    `crt_org_code` varchar(100) COMMENT '添加机构代号',
    `crt_org_name` varchar(100) COMMENT '添加机构名称',
    `crt_usr_id` int(11) COMMENT '添加用户ID',
    `crt_usr_name` varchar(50) COMMENT '添加用户名称',
    `crt_time` DateTime COMMENT '添加时间',
    `upt_area_code` varchar(14) COMMENT '修改地区',
    `upt_org_code` varchar(22) COMMENT '修改机构',
    `upt_org_name` varchar(100) COMMENT '修改机构名称',
    `upt_usr_id` int(11) COMMENT '修改用户ID',
    `upt_time` DateTime COMMENT '修改时间',
    `sts` TinyInt(1) COMMENT '是否有效',
    `rmk` varchar(2000) COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '随访管理信息';

-- ----------------------------
-- Table structure for trac
-- ----------------------------
DROP TABLE IF EXISTS `trac`;
CREATE TABLE `trac`  (
    `id` bigint NOT NULL COMMENT '主键',
    `infc_rpt_id` bigint NOT NULL COMMENT '报告卡主键',
    `trac_area_code` varchar(14) COMMENT '追踪地区编码',
    `trac_org_code` varchar(22) COMMENT '追踪单位编码',
    `beg_trac_code` DateTime COMMENT '开始追踪时间',
    `trac_sts_code` varchar(2) COMMENT '追踪状态编码',
    `trac_meth_code` varchar(2) COMMENT '追踪方法',
    `trac_yes_time` DateTime COMMENT '追踪到位时间',
    `trac_no_caus` varchar(1000) COMMENT '追踪未到位的原因',
    `crt_area_code` varchar(14) COMMENT '添加地区',
    `crt_org_code` varchar(100) COMMENT '添加机构代号',
    `crt_org_name` varchar(100) COMMENT '添加机构名称',
    `crt_usr_id` int(11) COMMENT '添加用户ID',
    `crt_usr_name` varchar(50) COMMENT '添加用户名称',
    `crt_time` DateTime COMMENT '添加时间',
    `upt_area_code` varchar(14) COMMENT '修改地区',
    `upt_org_code` varchar(22) COMMENT '修改机构',
    `upt_org_name` varchar(100) COMMENT '修改机构名称',
    `upt_usr_id` int(11) COMMENT '修改用户ID',
    `upt_time` DateTime COMMENT '修改时间',
    `sts` TinyInt(1) COMMENT '是否有效',
    `rmk` varchar(2000) COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '追踪信息管理';

SET FOREIGN_KEY_CHECKS = 1;
