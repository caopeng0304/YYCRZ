alter session set NLS_DATE_FORMAT='yyyy-mm-dd hh24:mi:ss';

-- ----------------------------
-- Table structure for flow_delegate
-- ----------------------------
CREATE TABLE flow_delegate (
  id number(20) NOT NULL,
  to_user_id varchar2(50),
  to_user_name varchar2(50),
  flow_id number(20),
  flow_name varchar2(50),
  flow_category varchar2(50),
  start_time date,
  end_time date,
  description clob,
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN flow_delegate.id IS '自然主键';
COMMENT ON COLUMN flow_delegate.to_user_id IS '被委托人';
COMMENT ON COLUMN flow_delegate.to_user_name IS '被委托人';
COMMENT ON COLUMN flow_delegate.flow_id IS '委托流程Id';
COMMENT ON COLUMN flow_delegate.flow_name IS '委托流程名称';
COMMENT ON COLUMN flow_delegate.flow_category IS '流程分类';
COMMENT ON COLUMN flow_delegate.start_time IS '开始时间';
COMMENT ON COLUMN flow_delegate.end_time IS '结束时间';
COMMENT ON COLUMN flow_delegate.description IS '描述';
COMMENT ON COLUMN flow_delegate.sort IS '排序';
COMMENT ON COLUMN flow_delegate.enabled_flag IS '有效标志';
COMMENT ON COLUMN flow_delegate.del_flag IS '删除标志';
COMMENT ON COLUMN flow_delegate.create_time IS '创建时间';
COMMENT ON COLUMN flow_delegate.create_by IS '创建用户';
COMMENT ON COLUMN flow_delegate.update_time IS '修改时间';
COMMENT ON COLUMN flow_delegate.update_by IS '修改用户';
COMMENT ON TABLE flow_delegate IS '流程委托';

-- ----------------------------
-- Table structure for flow_engine
-- ----------------------------
CREATE TABLE flow_engine (
  id number(20) NOT NULL,
  encode varchar2(50),
  full_name varchar2(50),
  type number(8),
  category varchar2(50),
  form_type number(8),
  form varchar2(50),
  ref_tables clob,
  visible_type number(8),
  icon varchar2(50),
  icon_background varchar2(50),
  version varchar2(50),
  flow_template_json clob,
  form_template_json clob,
  description clob,
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN flow_engine.id IS '自然主键';
COMMENT ON COLUMN flow_engine.encode IS '流程编码';
COMMENT ON COLUMN flow_engine.full_name IS '流程名称';
COMMENT ON COLUMN flow_engine.type IS '流程类型 0发起流程 1功能流程';
COMMENT ON COLUMN flow_engine.category IS '流程分类';
COMMENT ON COLUMN flow_engine.form_type IS '表单分类 1系统表单 2自定义表单';
COMMENT ON COLUMN flow_engine.form IS '流程表单';
COMMENT ON COLUMN flow_engine.ref_tables IS '关联的表';
COMMENT ON COLUMN flow_engine.visible_type IS '可见类型';
COMMENT ON COLUMN flow_engine.icon IS '图标';
COMMENT ON COLUMN flow_engine.icon_background IS '图标背景色';
COMMENT ON COLUMN flow_engine.version IS '流程版本';
COMMENT ON COLUMN flow_engine.flow_template_json IS '流程模板';
COMMENT ON COLUMN flow_engine.form_template_json IS '表单模板';
COMMENT ON COLUMN flow_engine.description IS '描述';
COMMENT ON COLUMN flow_engine.sort IS '排序码';
COMMENT ON COLUMN flow_engine.enabled_flag IS '有效标志';
COMMENT ON COLUMN flow_engine.del_flag IS '删除标志';
COMMENT ON COLUMN flow_engine.create_time IS '创建时间';
COMMENT ON COLUMN flow_engine.create_by IS '创建用户';
COMMENT ON COLUMN flow_engine.update_time IS '修改时间';
COMMENT ON COLUMN flow_engine.update_by IS '修改用户';
COMMENT ON TABLE flow_engine IS '流程引擎';

-- ----------------------------
-- Table structure for flow_engine_visible
-- ----------------------------
CREATE TABLE flow_engine_visible (
  id number(20) NOT NULL,
  flow_id number(20),
  operator_type varchar2(50),
  operator_id varchar2(50),
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN flow_engine_visible.id IS '自然主键';
COMMENT ON COLUMN flow_engine_visible.flow_id IS '流程主键';
COMMENT ON COLUMN flow_engine_visible.operator_type IS '经办类型';
COMMENT ON COLUMN flow_engine_visible.operator_id IS '经办主键';
COMMENT ON COLUMN flow_engine_visible.sort IS '排序码';
COMMENT ON COLUMN flow_engine_visible.enabled_flag IS '有效标志';
COMMENT ON COLUMN flow_engine_visible.del_flag IS '删除标志';
COMMENT ON COLUMN flow_engine_visible.create_time IS '创建时间';
COMMENT ON COLUMN flow_engine_visible.create_by IS '创建用户';
COMMENT ON COLUMN flow_engine_visible.update_time IS '修改时间';
COMMENT ON COLUMN flow_engine_visible.update_by IS '修改用户';
COMMENT ON TABLE flow_engine_visible IS '流程可见';

-- ----------------------------
-- Table structure for flow_task
-- ----------------------------
CREATE TABLE flow_task (
  id number(20) NOT NULL,
  process_id number(20),
  encode varchar2(50),
  full_name varchar2(200),
  flow_urgent number(8),
  flow_id number(20),
  flow_code varchar2(50),
  flow_name varchar2(50),
  flow_type number(8),
  flow_category varchar2(50),
  flow_form clob,
  flow_form_content_json clob,
  flow_template_json clob,
  flow_version varchar2(50),
  start_time date,
  end_time date,
  this_step varchar2(50),
  this_step_id varchar2(50),
  grade varchar2(50),
  status number(8),
  completion number(8),
  description clob,
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN flow_task.id IS '自然主键';
COMMENT ON COLUMN flow_task.process_id IS '实例进程';
COMMENT ON COLUMN flow_task.encode IS '任务编号';
COMMENT ON COLUMN flow_task.full_name IS '任务标题';
COMMENT ON COLUMN flow_task.flow_urgent IS '紧急程度';
COMMENT ON COLUMN flow_task.flow_id IS '流程主键';
COMMENT ON COLUMN flow_task.flow_code IS '流程编号';
COMMENT ON COLUMN flow_task.flow_name IS '流程名称';
COMMENT ON COLUMN flow_task.flow_type IS '流程分类';
COMMENT ON COLUMN flow_task.flow_category IS '流程类型';
COMMENT ON COLUMN flow_task.flow_form IS '流程表单';
COMMENT ON COLUMN flow_task.flow_form_content_json IS '表单内容';
COMMENT ON COLUMN flow_task.flow_template_json IS '流程模板';
COMMENT ON COLUMN flow_task.flow_version IS '流程版本';
COMMENT ON COLUMN flow_task.start_time IS '开始时间';
COMMENT ON COLUMN flow_task.end_time IS '结束时间';
COMMENT ON COLUMN flow_task.this_step IS '当前步骤';
COMMENT ON COLUMN flow_task.this_step_id IS '当前步骤Id';
COMMENT ON COLUMN flow_task.grade IS '重要等级';
COMMENT ON COLUMN flow_task.status IS '任务状态';
COMMENT ON COLUMN flow_task.completion IS '完成情况';
COMMENT ON COLUMN flow_task.description IS '描述';
COMMENT ON COLUMN flow_task.sort IS '排序码';
COMMENT ON COLUMN flow_task.enabled_flag IS '有效标志';
COMMENT ON COLUMN flow_task.del_flag IS '删除标志';
COMMENT ON COLUMN flow_task.create_time IS '创建时间';
COMMENT ON COLUMN flow_task.create_by IS '创建用户';
COMMENT ON COLUMN flow_task.update_time IS '修改时间';
COMMENT ON COLUMN flow_task.update_by IS '修改用户';
COMMENT ON TABLE flow_task IS '流程任务';

-- ----------------------------
-- Table structure for flow_task_circulate
-- ----------------------------
CREATE TABLE flow_task_circulate (
  id number(20) NOT NULL,
  object_type varchar2(50),
  object_id varchar2(50),
  node_code varchar2(50),
  node_name varchar2(50),
  task_node_id number(20),
  task_id number(20),
  create_time date
)
;
COMMENT ON COLUMN flow_task_circulate.id IS '自然主键';
COMMENT ON COLUMN flow_task_circulate.object_type IS '对象类型';
COMMENT ON COLUMN flow_task_circulate.object_id IS '对象主键';
COMMENT ON COLUMN flow_task_circulate.node_code IS '节点编号';
COMMENT ON COLUMN flow_task_circulate.node_name IS '节点名称';
COMMENT ON COLUMN flow_task_circulate.task_node_id IS '节点主键';
COMMENT ON COLUMN flow_task_circulate.task_id IS '任务主键';
COMMENT ON COLUMN flow_task_circulate.create_time IS '创建时间';
COMMENT ON TABLE flow_task_circulate IS '流程传阅';

-- ----------------------------
-- Table structure for flow_task_node
-- ----------------------------
CREATE TABLE flow_task_node (
  id number(20) NOT NULL,
  node_code varchar2(50),
  node_name varchar2(50),
  node_type varchar2(50),
  node_property_json clob,
  node_up varchar2(50),
  node_next varchar2(50),
  task_id number(20),
  state varchar2(50),
  completion number(8),
  description clob,
  sort number(8),
  create_time date
)
;
COMMENT ON COLUMN flow_task_node.id IS '自然主键';
COMMENT ON COLUMN flow_task_node.node_code IS '节点编号';
COMMENT ON COLUMN flow_task_node.node_name IS '节点名称';
COMMENT ON COLUMN flow_task_node.node_type IS '节点类型';
COMMENT ON COLUMN flow_task_node.node_property_json IS '节点属性Json';
COMMENT ON COLUMN flow_task_node.node_up IS '上一节点';
COMMENT ON COLUMN flow_task_node.node_next IS '下一节点';
COMMENT ON COLUMN flow_task_node.task_id IS '任务主键';
COMMENT ON COLUMN flow_task_node.state IS '状态';
COMMENT ON COLUMN flow_task_node.completion IS '是否完成';
COMMENT ON COLUMN flow_task_node.description IS '描述';
COMMENT ON COLUMN flow_task_node.sort IS '排序码';
COMMENT ON COLUMN flow_task_node.create_time IS '创建时间';
COMMENT ON TABLE flow_task_node IS '流程节点';

-- ----------------------------
-- Table structure for flow_task_operator
-- ----------------------------
CREATE TABLE flow_task_operator (
  id number(20) NOT NULL,
  handle_type varchar2(50),
  handle_id varchar2(50),
  handle_status number(8),
  handle_time date,
  node_code varchar2(50),
  node_name varchar2(50),
  task_node_id number(20),
  task_id number(20),
  type varchar2(50),
  state varchar2(50),
  completion number(8),
  description clob,
  create_time date
)
;
COMMENT ON COLUMN flow_task_operator.id IS '自然主键';
COMMENT ON COLUMN flow_task_operator.handle_type IS '经办对象';
COMMENT ON COLUMN flow_task_operator.handle_id IS '经办主键';
COMMENT ON COLUMN flow_task_operator.handle_status IS '处理状态';
COMMENT ON COLUMN flow_task_operator.handle_time IS '处理时间';
COMMENT ON COLUMN flow_task_operator.node_code IS '节点编号';
COMMENT ON COLUMN flow_task_operator.node_name IS '节点名称';
COMMENT ON COLUMN flow_task_operator.task_node_id IS '节点主键';
COMMENT ON COLUMN flow_task_operator.task_id IS '任务主键';
COMMENT ON COLUMN flow_task_operator.type IS '节点类型';
COMMENT ON COLUMN flow_task_operator.state IS '状态';
COMMENT ON COLUMN flow_task_operator.completion IS '是否完成';
COMMENT ON COLUMN flow_task_operator.description IS '描述';
COMMENT ON COLUMN flow_task_operator.create_time IS '创建时间';
COMMENT ON TABLE flow_task_operator IS '流程经办';

-- ----------------------------
-- Table structure for flow_task_operator_record
-- ----------------------------
CREATE TABLE flow_task_operator_record (
  id number(20) NOT NULL,
  node_code varchar2(50),
  node_name varchar2(50),
  handle_status number(8),
  handle_id varchar2(50),
  handle_time date,
  handle_opinion clob,
  task_operator_id number(20),
  task_node_id number(20),
  task_id number(20)
)
;
COMMENT ON COLUMN flow_task_operator_record.id IS '自然主键';
COMMENT ON COLUMN flow_task_operator_record.node_code IS '节点编号';
COMMENT ON COLUMN flow_task_operator_record.node_name IS '节点名称';
COMMENT ON COLUMN flow_task_operator_record.handle_status IS '经办状态';
COMMENT ON COLUMN flow_task_operator_record.handle_id IS '经办人员';
COMMENT ON COLUMN flow_task_operator_record.handle_time IS '经办时间';
COMMENT ON COLUMN flow_task_operator_record.handle_opinion IS '经办理由';
COMMENT ON COLUMN flow_task_operator_record.task_operator_id IS '经办主键';
COMMENT ON COLUMN flow_task_operator_record.task_node_id IS '节点主键';
COMMENT ON COLUMN flow_task_operator_record.task_id IS '任务主键';
COMMENT ON TABLE flow_task_operator_record IS '流程经办记录';

-- ----------------------------
-- Primary Key structure for table flow_delegate
-- ----------------------------
ALTER TABLE flow_delegate ADD CONSTRAINT flow_delegate_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table flow_engine
-- ----------------------------
ALTER TABLE flow_engine ADD CONSTRAINT flow_engine_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table flow_engine_visible
-- ----------------------------
ALTER TABLE flow_engine_visible ADD CONSTRAINT flow_engine_visible_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table flow_task
-- ----------------------------
ALTER TABLE flow_task ADD CONSTRAINT flow_task_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table flow_task_circulate
-- ----------------------------
ALTER TABLE flow_task_circulate ADD CONSTRAINT flow_task_circulate_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table flow_task_node
-- ----------------------------
ALTER TABLE flow_task_node ADD CONSTRAINT flow_task_node_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table flow_task_operator
-- ----------------------------
ALTER TABLE flow_task_operator ADD CONSTRAINT flow_task_operator_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table flow_task_operator_record
-- ----------------------------
ALTER TABLE flow_task_operator_record ADD CONSTRAINT flow_task_operator_record_pkey PRIMARY KEY (id);
