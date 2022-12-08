-- ----------------------------
-- Table structure for base_common_fields
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_common_fields]') AND type IN ('U'))
	DROP TABLE [dbo].[base_common_fields]
GO

CREATE TABLE [dbo].[base_common_fields] (
  [id] bigint  NOT NULL,
  [field_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [field_comment] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [data_type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [data_length] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [allow_null] int  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_common_fields] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列名',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'field_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列说明',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'field_comment'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'data_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'长度',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'data_length'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否为空（1允许，0不允许）',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'allow_null'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述说明',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'常用表字段',
'SCHEMA', N'dbo',
'TABLE', N'base_common_fields'
GO


-- ----------------------------
-- Table structure for base_data_interface
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_data_interface]') AND type IN ('U'))
	DROP TABLE [dbo].[base_data_interface]
GO

CREATE TABLE [dbo].[base_data_interface] (
  [id] bigint  NOT NULL,
  [full_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [encode] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [category_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [path] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [request_method] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [response_type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [query] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [request_parameters] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [response_parameters] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [db_link_id] nvarchar(100) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [data_type] int  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_data_interface] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键ID',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口名称',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口编码',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'encode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述或说明',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分组ID',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'category_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口路径',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求方式',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'request_method'
GO

EXEC sp_addextendedproperty
'MS_Description', N'返回类型',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'response_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'查询语句',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'query'
GO

EXEC sp_addextendedproperty
'MS_Description', N'请求参数JSON',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'request_parameters'
GO

EXEC sp_addextendedproperty
'MS_Description', N'返回参数JSON',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'response_parameters'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据源id',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'db_link_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据类型(1-动态数据SQL查询，2-静态数据)',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'data_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码(默认0)',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'启用标志(0-默认，禁用，1-启用)',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志(默认0)',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户id',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户id',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据接口',
'SCHEMA', N'dbo',
'TABLE', N'base_data_interface'
GO


-- ----------------------------
-- Table structure for base_db_link
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_db_link]') AND type IN ('U'))
	DROP TABLE [dbo].[base_db_link]
GO

CREATE TABLE [dbo].[base_db_link] (
  [id] bigint  NOT NULL,
  [full_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [db_type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [host] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [port] int  NULL,
  [user_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [password] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [service_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_db_link] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'连接名称',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'连接驱动',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'db_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'主机地址',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'host'
GO

EXEC sp_addextendedproperty
'MS_Description', N'端口',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'port'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'user_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'密码',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'服务名称',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'service_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据连接',
'SCHEMA', N'dbo',
'TABLE', N'base_db_link'
GO


-- ----------------------------
-- Table structure for base_portal
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_portal]') AND type IN ('U'))
	DROP TABLE [dbo].[base_portal]
GO

CREATE TABLE [dbo].[base_portal] (
  [id] bigint  NOT NULL,
  [full_name] nvarchar(100) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [encode] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [category] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [form_data] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_portal] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'encode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分类（数据字典）',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'表单配置JSON',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'form_data'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'启用标志(0-默认，禁用，1-启用)',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'base_portal',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'门户表',
'SCHEMA', N'dbo',
'TABLE', N'base_portal'
GO


-- ----------------------------
-- Table structure for base_visual_data
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_visual_data]') AND type IN ('U'))
	DROP TABLE [dbo].[base_visual_data]
GO

CREATE TABLE [dbo].[base_visual_data] (
  [id] bigint  NOT NULL,
  [full_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [encode] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [category_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [screen_shot] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [password] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [component] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [detail] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [copy_id] bigint  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_visual_data] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键ID',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编号',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'encode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述或说明',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分类ID',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'category_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大屏截图',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'screen_shot'
GO

EXEC sp_addextendedproperty
'MS_Description', N'访问密码',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'控件属性JSON包',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'component'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大屏配置JSON包',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'detail'
GO

EXEC sp_addextendedproperty
'MS_Description', N'复制id',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'copy_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大屏数据',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data'
GO


-- ----------------------------
-- Table structure for base_visual_data_map
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_visual_data_map]') AND type IN ('U'))
	DROP TABLE [dbo].[base_visual_data_map]
GO

CREATE TABLE [dbo].[base_visual_data_map] (
  [id] bigint  NOT NULL,
  [full_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [encode] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [data] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_visual_data_map] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键ID',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编号',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'encode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'地图数据',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'data'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述或说明',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'大屏地图',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_data_map'
GO


-- ----------------------------
-- Table structure for base_visual_dev
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_visual_dev]') AND type IN ('U'))
	DROP TABLE [dbo].[base_visual_dev]
GO

CREATE TABLE [dbo].[base_visual_dev] (
  [id] bigint  NOT NULL,
  [full_name] nvarchar(100) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [encode] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [category] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [state] int  NULL,
  [type] int  NULL,
  [db_link_id] bigint  NULL,
  [ref_tables] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [form_data] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [column_data] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [template_data] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ((0)) NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_visual_dev] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'名称',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'编码',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'encode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'分类',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态(0-暂存（默认），1-发布)',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'类型(1-Web在线开发,2-移动在线开发,3-流程表单,4-Web表单,5-移动表单)',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'db_link外键',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'db_link_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'关联的表',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'ref_tables'
GO

EXEC sp_addextendedproperty
'MS_Description', N'表单配置JSON',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'form_data'
GO

EXEC sp_addextendedproperty
'MS_Description', N'列表配置JSON',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'column_data'
GO

EXEC sp_addextendedproperty
'MS_Description', N'前端模板JSON',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'template_data'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'启用标志(0-默认，禁用，1-启用)',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'可视化开发功能表',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev'
GO


-- ----------------------------
-- Table structure for base_visual_dev_model_data
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[base_visual_dev_model_data]') AND type IN ('U'))
	DROP TABLE [dbo].[base_visual_dev_model_data]
GO

CREATE TABLE [dbo].[base_visual_dev_model_data] (
  [id] bigint  NOT NULL,
  [visual_dev_id] bigint  NULL,
  [parent_id] bigint  NULL,
  [data] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[base_visual_dev_model_data] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'主键',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'功能ID',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'visual_dev_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'区分主子表',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'parent_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据包',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'data'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'启用标志(0-默认，禁用，1-启用)',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'0代码功能数据表',
'SCHEMA', N'dbo',
'TABLE', N'base_visual_dev_model_data'
GO


-- ----------------------------
-- Primary Key structure for table base_common_fields
-- ----------------------------
ALTER TABLE [dbo].[base_common_fields] ADD CONSTRAINT [PK__base_com__3213E83FFE9D5294] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_data_interface
-- ----------------------------
ALTER TABLE [dbo].[base_data_interface] ADD CONSTRAINT [PK__base_dat__3213E83F09DAFE86] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_db_link
-- ----------------------------
ALTER TABLE [dbo].[base_db_link] ADD CONSTRAINT [PK__base_db___3213E83F1702CA8C] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_portal
-- ----------------------------
ALTER TABLE [dbo].[base_portal] ADD CONSTRAINT [PK__base_por__3213E83FB77C8096] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_visual_data
-- ----------------------------
ALTER TABLE [dbo].[base_visual_data] ADD CONSTRAINT [PK__base_vis__3213E83F526B83FE] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_visual_data_map
-- ----------------------------
ALTER TABLE [dbo].[base_visual_data_map] ADD CONSTRAINT [PK__base_vis__3213E83F1F1EA80D] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_visual_dev
-- ----------------------------
ALTER TABLE [dbo].[base_visual_dev] ADD CONSTRAINT [PK__base_vis__3213E83FBCCBF8DF] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table base_visual_dev_model_data
-- ----------------------------
ALTER TABLE [dbo].[base_visual_dev_model_data] ADD CONSTRAINT [PK__base_vis__3213E83F08ABC005] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

