-- ----------------------------
-- Table structure for flow_delegate
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_delegate]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_delegate]
GO

CREATE TABLE [dbo].[flow_delegate] (
  [id] bigint  NOT NULL,
  [to_user_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [to_user_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_id] bigint  NULL,
  [flow_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_category] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [start_time] datetime  NULL,
  [end_time] datetime  NULL,
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

ALTER TABLE [dbo].[flow_delegate] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'被委托人',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'to_user_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'被委托人',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'to_user_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'委托流程Id',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'flow_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'委托流程名称',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'flow_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程分类',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'flow_category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'开始时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'start_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'结束时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'end_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程委托',
'SCHEMA', N'dbo',
'TABLE', N'flow_delegate'
GO


-- ----------------------------
-- Table structure for flow_engine
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_engine]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_engine]
GO

CREATE TABLE [dbo].[flow_engine] (
  [id] bigint  NOT NULL,
  [encode] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [full_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [type] int  NULL,
  [category] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [form_type] int  NULL,
  [form] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [ref_tables] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [visible_type] int  NULL,
  [icon] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [icon_background] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [version] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_template_json] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [form_template_json] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
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

ALTER TABLE [dbo].[flow_engine] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程编码',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'encode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程名称',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程类型 0发起流程 1功能流程',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程分类',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'表单分类 1系统表单 2自定义表单',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'form_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程表单',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'form'
GO

EXEC sp_addextendedproperty
'MS_Description', N'关联的表',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'ref_tables'
GO

EXEC sp_addextendedproperty
'MS_Description', N'可见类型',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'visible_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'图标',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'icon'
GO

EXEC sp_addextendedproperty
'MS_Description', N'图标背景色',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'icon_background'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程版本',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'version'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程模板',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'flow_template_json'
GO

EXEC sp_addextendedproperty
'MS_Description', N'表单模板',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'form_template_json'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程引擎',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine'
GO


-- ----------------------------
-- Table structure for flow_engine_visible
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_engine_visible]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_engine_visible]
GO

CREATE TABLE [dbo].[flow_engine_visible] (
  [id] bigint  NULL,
  [flow_id] bigint  NULL,
  [operator_type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [operator_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [enabled_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('1') NULL,
  [del_flag] char(1) COLLATE Chinese_PRC_CS_AI_WS DEFAULT ('0') NULL,
  [create_time] datetime  NULL,
  [create_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [update_time] datetime  NULL,
  [update_by] nvarchar(64) COLLATE Chinese_PRC_CS_AI_WS  NULL
)
GO

ALTER TABLE [dbo].[flow_engine_visible] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'flow_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办类型',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'operator_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'operator_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程可见',
'SCHEMA', N'dbo',
'TABLE', N'flow_engine_visible'
GO


-- ----------------------------
-- Table structure for flow_task
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_task]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_task]
GO

CREATE TABLE [dbo].[flow_task] (
  [id] bigint  NOT NULL,
  [process_id] bigint  NULL,
  [encode] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [full_name] nvarchar(200) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_urgent] int  NULL,
  [flow_id] bigint  NULL,
  [flow_code] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_type] int  NULL,
  [flow_category] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_form] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_form_content_json] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_template_json] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [flow_version] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [start_time] datetime  NULL,
  [end_time] datetime  NULL,
  [this_step] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [this_step_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [grade] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [status] int  NULL,
  [completion] int  NULL,
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

ALTER TABLE [dbo].[flow_task] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'实例进程',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'process_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务编号',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'encode'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务标题',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'full_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'紧急程度',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_urgent'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程编号',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程名称',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程分类',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程类型',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_category'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程表单',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_form'
GO

EXEC sp_addextendedproperty
'MS_Description', N'表单内容',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_form_content_json'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程模板',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_template_json'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程版本',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'flow_version'
GO

EXEC sp_addextendedproperty
'MS_Description', N'开始时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'start_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'结束时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'end_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'当前步骤',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'this_step'
GO

EXEC sp_addextendedproperty
'MS_Description', N'当前步骤Id',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'this_step_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'重要等级',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'grade'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务状态',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'完成情况',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'completion'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有效标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'enabled_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'删除标志',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'del_flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改用户',
'SCHEMA', N'dbo',
'TABLE', N'flow_task',
'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程任务',
'SCHEMA', N'dbo',
'TABLE', N'flow_task'
GO


-- ----------------------------
-- Table structure for flow_task_circulate
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_task_circulate]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_task_circulate]
GO

CREATE TABLE [dbo].[flow_task_circulate] (
  [id] int  NOT NULL,
  [object_type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [object_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_code] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [task_node_id] bigint  NULL,
  [task_id] bigint  NULL,
  [create_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[flow_task_circulate] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'对象类型',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'object_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'对象主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'object_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点编号',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'node_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点名称',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'node_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'task_node_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'task_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程传阅',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_circulate'
GO


-- ----------------------------
-- Table structure for flow_task_node
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_task_node]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_task_node]
GO

CREATE TABLE [dbo].[flow_task_node] (
  [id] bigint  NOT NULL,
  [node_code] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_property_json] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_up] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_next] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [task_id] bigint  NULL,
  [state] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [completion] int  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [sort] int  NULL,
  [create_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[flow_task_node] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点编号',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'node_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点名称',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'node_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点类型',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'node_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点属性Json',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'node_property_json'
GO

EXEC sp_addextendedproperty
'MS_Description', N'上一节点',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'node_up'
GO

EXEC sp_addextendedproperty
'MS_Description', N'下一节点',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'node_next'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'task_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否完成',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'completion'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'排序码',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程节点',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_node'
GO


-- ----------------------------
-- Table structure for flow_task_operator
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_task_operator]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_task_operator]
GO

CREATE TABLE [dbo].[flow_task_operator] (
  [id] bigint  NOT NULL,
  [handle_type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [handle_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [handle_status] int  NULL,
  [handle_time] datetime  NULL,
  [node_code] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [task_node_id] bigint  NULL,
  [task_id] bigint  NULL,
  [type] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [state] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [completion] int  NULL,
  [description] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [create_time] datetime  NULL
)
GO

ALTER TABLE [dbo].[flow_task_operator] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办对象',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'handle_type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'handle_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'处理状态',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'handle_status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'处理时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'handle_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点编号',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'node_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点名称',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'node_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'task_node_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'task_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点类型',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否完成',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'completion'
GO

EXEC sp_addextendedproperty
'MS_Description', N'描述',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程经办',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator'
GO


-- ----------------------------
-- Table structure for flow_task_operator_record
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flow_task_operator_record]') AND type IN ('U'))
	DROP TABLE [dbo].[flow_task_operator_record]
GO

CREATE TABLE [dbo].[flow_task_operator_record] (
  [id] bigint  NOT NULL,
  [node_code] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [node_name] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [handle_status] int  NULL,
  [handle_id] nvarchar(50) COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [handle_time] datetime  NULL,
  [handle_opinion] ntext COLLATE Chinese_PRC_CS_AI_WS  NULL,
  [task_operator_id] bigint  NULL,
  [task_node_id] bigint  NULL,
  [task_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[flow_task_operator_record] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'自然主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点编号',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'node_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点名称',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'node_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办状态',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'handle_status'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办人员',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'handle_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办时间',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'handle_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办理由',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'handle_opinion'
GO

EXEC sp_addextendedproperty
'MS_Description', N'经办主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'task_operator_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'节点主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'task_node_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'任务主键',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record',
'COLUMN', N'task_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流程经办记录',
'SCHEMA', N'dbo',
'TABLE', N'flow_task_operator_record'
GO


-- ----------------------------
-- Primary Key structure for table flow_delegate
-- ----------------------------
ALTER TABLE [dbo].[flow_delegate] ADD CONSTRAINT [PK__flow_del__3213E83FEC37D1DF] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table flow_engine
-- ----------------------------
ALTER TABLE [dbo].[flow_engine] ADD CONSTRAINT [PK__flow_eng__3213E83F47C3502F] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table flow_task
-- ----------------------------
ALTER TABLE [dbo].[flow_task] ADD CONSTRAINT [PK__flow_tas__3213E83FAEE098CF] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table flow_task_circulate
-- ----------------------------
ALTER TABLE [dbo].[flow_task_circulate] ADD CONSTRAINT [PK__flow_tas__3213E83FC6D31BB0] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table flow_task_node
-- ----------------------------
ALTER TABLE [dbo].[flow_task_node] ADD CONSTRAINT [PK__flow_tas__3213E83F5053ADD3] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table flow_task_operator
-- ----------------------------
ALTER TABLE [dbo].[flow_task_operator] ADD CONSTRAINT [PK__flow_tas__3213E83F54E2A858] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table flow_task_operator_record
-- ----------------------------
ALTER TABLE [dbo].[flow_task_operator_record] ADD CONSTRAINT [PK__flow_tas__3213E83FDE726553] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

