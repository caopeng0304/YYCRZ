DROP DATABASE IF EXISTS `booster_config`;

CREATE DATABASE  `booster_config` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE booster_config;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
    `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
    `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
    `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
    `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
    `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
    `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
    `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
    `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'application-dev.yml', 'DEFAULT_GROUP', '# 加解密根密码\njasypt:\n  encryptor:\n    password: booster #根密码\n\n# Spring 相关\nspring:\n  redis:\n    host: booster-redis\n    password: root\n  cloud:\n    sentinel:\n      eager: true\n      transport:\n        dashboard: booster-sentinel:5003\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\n# mybaits-plus配置\nmybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  global-config:\n    banner: false\n    db-config:\n      id-type: auto\n      table-underline: true\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n  configuration:\n    map-underscore-to-camel-case: true\n\n# spring security 配置\nsecurity:\n  oauth2:\n    resource:\n      loadBalanced: true\n      token-info-uri: http://booster-auth/oauth/check_token\n    # 通用放行URL，服务个性化，请在对应配置文件覆盖\n    ignore:\n      urls:\n        - /v2/api-docs\n        - /actuator/**\n# swagger 配置\nswagger:\n  enabled: true\n  title: booster Swagger API\n  license: Powered By Booster Cloud\n  licenseUrl: https://www.xxx.com\n  terms-of-service-url: https://www.xxx.com\n  contact:\n    email: admin@xxx.com\n    url: https://www.xxx.com\n  authorization:\n    name: booster cloud OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://${GATEWAY_HOST:booster-gateway}:${GATEWAY-PORT:9999}/auth/oauth/token\n  basic:\n    enabled: true\n    username: booster\n    password: booster', 'bce6412f48758a7cdab16936b62257b6', '2019-11-29 16:31:20', '2021-11-16 13:16:07', 'nacos', '192.168.4.1', '', '', '通用配置', 'null', 'null', 'yaml', 'null', '');
INSERT INTO `config_info` VALUES (2, 'booster-auth-dev.yml', 'DEFAULT_GROUP', '# 数据源\nspring:\n  datasource:\n    hikari:\n      maximum-pool-size: 10\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://booster-mysql:3306/booster?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai\n  freemarker:\n    allow-request-override: false\n    allow-session-override: false\n    cache: true\n    charset: UTF-8\n    check-template-location: true\n    content-type: text/html\n    enabled: true\n    expose-request-attributes: false\n    expose-session-attributes: false\n    expose-spring-macro-helpers: true\n    prefer-file-system-access: true\n    suffix: .ftl\n    template-loader-path: classpath:/templates/', '284a15c1a9b175d5f8e39afca8d704f5', '2019-11-29 16:31:48', '2022-01-15 11:29:33', 'nacos', '192.168.4.1', '', '', '认证中心配置', 'null', 'null', 'yaml', 'null', '');
INSERT INTO `config_info` VALUES (3, 'booster-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      httpclient:\n        connect-timeout: 60000\n        response-timeout: 300s\n      routes:\n        # 认证中心\n        - id: booster-auth\n          uri: lb://booster-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n            # 验证码处理\n            #- ValidateCodeGatewayFilter\n            # 前端密码解密\n            - PasswordDecoderFilter\n        #UPMS 模块\n        - id: booster-upms-biz\n          uri: lb://booster-upms-biz\n          predicates:\n            - Path=/admin/**\n          filters:\n            - StripPrefix=1\n            # 限流配置\n            - name: RequestRateLimiter\n              args:\n                key-resolver: \'#{@remoteAddrKeyResolver}\'\n                redis-rate-limiter.replenishRate: 10000\n                redis-rate-limiter.burstCapacity: 20000\n        # 可视化开发模块\n        - id: booster-visualdev-biz\n          uri: lb://booster-visualdev-biz\n          predicates:\n            - Path=/visualdev/**\n          filters:\n            - StripPrefix=1\n        # 工作流模块\n        - id: booster-workflow-biz\n          uri: lb://booster-workflow-biz\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 报表模块\n        - id: booster-report-console\n          uri: lb://booster-report-console\n          predicates:\n            - Path=/report/**\n        # demo演示项目模块\n        - id: booster-demo-biz\n          uri: lb://booster-demo-biz\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n         # 传染病演示项目模块\n        - id: booster-infection-biz\n          uri: lb://booster-infection-biz\n          predicates:\n            - Path=/infection/**\n          filters:\n            - StripPrefix=1\n\ngateway:\n  encode-key: \'hi,booster-cloud\'\n  ignore-clients:\n    - test\n\nswagger:\n  ignore-providers:\n    - booster-auth\n    - booster-report-console\n', '16296457b4e8fdcccae85d3e7f423c74', '2019-11-29 16:32:42', '2022-01-14 14:15:05', 'nacos', '192.168.4.1', '', '', '网关配置', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (4, 'booster-monitor-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # 安全配置\n  security:\n    user:\n      name: ENC(OAy1Lx5MZp0on+sKYn+U6A==)     # booster\n      password: ENC(soNsxGmzbWFJHKluWFLwDg==) # booster\n', '85509c6f8c67c364dc78301896274f26', '2019-11-29 16:33:05', '2019-11-29 16:33:05', NULL, '127.0.0.1', '', '', '监控配置', NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (5, 'booster-upms-biz-dev.yml', 'DEFAULT_GROUP', 'security:\n  oauth2:\n    client:\n      client-id: ENC(OAy1Lx5MZp0on+sKYn+U6A==)     # booster\n      client-secret: ENC(soNsxGmzbWFJHKluWFLwDg==) # booster\n      scope: server\n    ignore:\n      urls:\n        - /v2/api-docs\n        - /actuator/**\n        - /Message/websocket\n\n# 数据源\nspring:\n  datasource:\n    hikari:\n      maximum-pool-size: 10\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://booster-mysql:3306/booster?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai\n\n# 文件系统\nminio:\n  url: http://booster-minio:9000\n  access-key: booster_cloud\n  secret-key: booster_cloud\n\n#Mybatis开启控制台打印sql语句\n#mybatis-plus:\n  #configuration:\n    #log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n', 'e2ed1ef95dc624f26e18b1386630760b', '2019-11-29 16:52:32', '2022-01-15 11:30:16', 'nacos', '192.168.4.1', '', '', '统一权限', 'null', 'null', 'yaml', 'null', '');
INSERT INTO `config_info` VALUES (6, 'booster-visualdev-biz-dev.yml', 'DEFAULT_GROUP', 'security:\n  oauth2:\n    client:\n      client-id: ENC(OAy1Lx5MZp0on+sKYn+U6A==)     # booster\n      client-secret: ENC(soNsxGmzbWFJHKluWFLwDg==) # booster\n      scope: server\n    ignore:\n      urls:\n        - /v2/api-docs\n        - /actuator/**\n        - /DataScreen/Images/**\n        - /Base/DataMap/**/Data\n        - /Generater/DownloadVisCode\n        - /file/Image/**\n        - /file/Download\n\n# 数据源\nspring:\n  datasource:\n    hikari:\n      maximum-pool-size: 10\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://booster-mysql:3306/booster_visualdev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai\n\n#Mybatis开启控制台打印sql语句\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'd31fab9e1476a906a67ed31d143bfb9b', '2021-09-24 10:19:33', '2022-01-17 15:37:51', 'nacos', '192.168.4.1', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (7, 'booster-report-console-dev.yml', 'DEFAULT_GROUP', 'security:\n  oauth2:\n    client:\n      client-id: ENC(OAy1Lx5MZp0on+sKYn+U6A==)     # booster\n      client-secret: ENC(soNsxGmzbWFJHKluWFLwDg==) # booster\n      scope: server\n    ignore:\n      urls:\n        - /v2/api-docs\n        - /actuator/**\n        - /report/excel/**\n        - /report/word/**\n        - /report/pdf/**\n        - /report/import/**\n        - /report/DataReport/*/Actions/Export/*\n\n# 数据源\nspring:\n  datasource:\n    hikari:\n      maximum-pool-size: 10\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://booster-mysql:3306/booster_ureport?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai\n\n#Mybatis开启控制台打印sql语句\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '4081675d40ef2aad30b7ac49ea61ce21', '2021-10-25 15:03:08', '2022-01-17 15:43:08', 'nacos', '192.168.4.1', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (8, 'booster-workflow-biz-dev.yml', 'DEFAULT_GROUP', 'security:\n  oauth2:\n    client:\n      client-id: ENC(OAy1Lx5MZp0on+sKYn+U6A==)     # booster\n      client-secret: ENC(soNsxGmzbWFJHKluWFLwDg==) # booster\n      scope: server\n\n# 数据源\nspring:\n  datasource:\n    hikari:\n      maximum-pool-size: 10\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://booster-mysql:3306/booster_workflow?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai\n\n#Mybatis开启控制台打印sql语句\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'f84b40575222b037904817219947413d', '2022-01-17 15:45:58', '2022-01-17 15:48:40', 'nacos', '192.168.4.1', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (9, 'seataServer', 'SEATA_GROUP', 'transport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableClientBatchSendRequest=false\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\nservice.vgroupMapping.my_test_tx_group=default\nservice.vgroupMapping.nacos_group=default\nservice.default.grouplist=booster-seata:8091\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=false\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nstore.mode=db\nstore.publicKey=\nstore.file.dir=file_store/data\nstore.file.maxBranchSessionSize=16384\nstore.file.maxGlobalSessionSize=512\nstore.file.fileWriteBufferCacheSize=16384\nstore.file.flushDiskMode=async\nstore.file.sessionReloadReadSize=100\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.jdbc.Driver\nstore.db.url=jdbc:mysql://booster-mysql:3306/booster_seata?useUnicode=true&rewriteBatchedStatements=true\nstore.db.user=root\nstore.db.password=root\nstore.db.minConn=5\nstore.db.maxConn=1000\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=50000\nstore.redis.mode=single\nstore.redis.single.host=booster-redis\nstore.redis.single.port=6379\nstore.redis.sentinel.masterName=\nstore.redis.sentinel.sentinelHosts=\nstore.redis.maxConn=10\nstore.redis.minConn=1\nstore.redis.maxTotal=100\nstore.redis.database=0\nstore.redis.password=\nstore.redis.queryLimit=100\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\nlog.exceptionRate=100\ntransport.serialization=seata\ntransport.compressor=none\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898', '097bde2919384e08c8054353a20c43f8', '2022-03-14 14:17:27', '2022-04-06 17:18:23', 'nacos', '192.168.4.1', '', '', 'SeaTac配置', '', '', 'properties', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
    `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
    `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
    `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
    `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
    `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
    `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
    `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
    `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
    `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
    `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
    `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
    `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
    `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
    `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
    `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
    `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
    `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
    `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
    `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
    `id` bigint(0) NOT NULL COMMENT 'id',
    `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
    `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
    `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
    `nid` bigint(0) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`nid`) USING BTREE,
    UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
    INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
    `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
    `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
    `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
    `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
    `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
    `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
    `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
    `id` bigint(0) UNSIGNED NOT NULL,
    `nid` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
    `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
    `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
    `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
    `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
    `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
    PRIMARY KEY (`nid`) USING BTREE,
    INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
    INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
    INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
    `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `resource` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    UNIQUE INDEX `uk_username_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
    `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
    `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
    `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
    `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
    `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
    `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
    `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
    `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
    `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
    `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
    `gmt_create` bigint(0) NOT NULL COMMENT '创建时间',
    `gmt_modified` bigint(0) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
    INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
