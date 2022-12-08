alter session set NLS_DATE_FORMAT='yyyy-mm-dd hh24:mi:ss';

-- ----------------------------
-- Table structure for base_common_fields
-- ----------------------------
CREATE TABLE base_common_fields (
  id number(20) NOT NULL,
  field_name varchar2(50),
  field_comment varchar2(50),
  data_type varchar2(50),
  data_length varchar2(50),
  allow_null number(8),
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
COMMENT ON COLUMN base_common_fields.id IS '主键';
COMMENT ON COLUMN base_common_fields.field_name IS '列名';
COMMENT ON COLUMN base_common_fields.field_comment IS '列说明';
COMMENT ON COLUMN base_common_fields.data_type IS '类型';
COMMENT ON COLUMN base_common_fields.data_length IS '长度';
COMMENT ON COLUMN base_common_fields.allow_null IS '是否为空（1允许，0不允许）';
COMMENT ON COLUMN base_common_fields.description IS '描述说明';
COMMENT ON COLUMN base_common_fields.sort IS '排序码';
COMMENT ON COLUMN base_common_fields.enabled_flag IS '有效标志';
COMMENT ON COLUMN base_common_fields.del_flag IS '删除标志';
COMMENT ON COLUMN base_common_fields.create_time IS '创建时间';
COMMENT ON COLUMN base_common_fields.create_by IS '创建用户';
COMMENT ON COLUMN base_common_fields.update_time IS '修改时间';
COMMENT ON COLUMN base_common_fields.update_by IS '修改用户';
COMMENT ON TABLE base_common_fields IS '常用表字段';

-- ----------------------------
-- Table structure for base_data_interface
-- ----------------------------
CREATE TABLE base_data_interface (
  id number(20) NOT NULL,
  full_name varchar2(50),
  encode varchar2(50),
  description clob,
  category_id varchar2(50),
  path varchar2(50),
  request_method varchar2(50),
  response_type varchar2(50),
  query clob,
  request_parameters clob,
  response_parameters clob,
  db_link_id varchar2(100),
  data_type number(8),
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN base_data_interface.id IS '主键ID';
COMMENT ON COLUMN base_data_interface.full_name IS '接口名称';
COMMENT ON COLUMN base_data_interface.encode IS '接口编码';
COMMENT ON COLUMN base_data_interface.description IS '描述或说明';
COMMENT ON COLUMN base_data_interface.category_id IS '分组ID';
COMMENT ON COLUMN base_data_interface.path IS '接口路径';
COMMENT ON COLUMN base_data_interface.request_method IS '请求方式';
COMMENT ON COLUMN base_data_interface.response_type IS '返回类型';
COMMENT ON COLUMN base_data_interface.query IS '查询语句';
COMMENT ON COLUMN base_data_interface.request_parameters IS '请求参数JSON';
COMMENT ON COLUMN base_data_interface.response_parameters IS '返回参数JSON';
COMMENT ON COLUMN base_data_interface.db_link_id IS '数据源id';
COMMENT ON COLUMN base_data_interface.data_type IS '数据类型(1-动态数据SQL查询，2-静态数据)';
COMMENT ON COLUMN base_data_interface.sort IS '排序码(默认0)';
COMMENT ON COLUMN base_data_interface.enabled_flag IS '启用标志(0-默认，禁用，1-启用)';
COMMENT ON COLUMN base_data_interface.del_flag IS '删除标志(默认0)';
COMMENT ON COLUMN base_data_interface.create_time IS '创建时间';
COMMENT ON COLUMN base_data_interface.create_by IS '创建用户id';
COMMENT ON COLUMN base_data_interface.update_time IS '修改时间';
COMMENT ON COLUMN base_data_interface.update_by IS '修改用户id';
COMMENT ON TABLE base_data_interface IS '数据接口';

-- ----------------------------
-- Table structure for base_db_link
-- ----------------------------
CREATE TABLE base_db_link (
  id number(20) NOT NULL,
  full_name varchar2(50),
  description clob,
  db_type varchar2(50),
  host varchar2(50),
  port number(8),
  user_name varchar2(50),
  password varchar2(50),
  service_name varchar2(50),
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN base_db_link.id IS '自然主键';
COMMENT ON COLUMN base_db_link.full_name IS '连接名称';
COMMENT ON COLUMN base_db_link.description IS '描述';
COMMENT ON COLUMN base_db_link.db_type IS '连接驱动';
COMMENT ON COLUMN base_db_link.host IS '主机地址';
COMMENT ON COLUMN base_db_link.port IS '端口';
COMMENT ON COLUMN base_db_link.user_name IS '用户';
COMMENT ON COLUMN base_db_link.password IS '密码';
COMMENT ON COLUMN base_db_link.service_name IS '服务名称';
COMMENT ON COLUMN base_db_link.sort IS '排序';
COMMENT ON COLUMN base_db_link.enabled_flag IS '有效标志';
COMMENT ON COLUMN base_db_link.del_flag IS '删除标志';
COMMENT ON COLUMN base_db_link.create_time IS '创建时间';
COMMENT ON COLUMN base_db_link.create_by IS '创建用户';
COMMENT ON COLUMN base_db_link.update_time IS '修改时间';
COMMENT ON COLUMN base_db_link.update_by IS '修改用户';
COMMENT ON TABLE base_db_link IS '数据连接';

-- ----------------------------
-- Table structure for base_portal
-- ----------------------------
CREATE TABLE base_portal (
  id number(20) NOT NULL,
  full_name varchar2(100),
  encode varchar2(50),
  description clob,
  category varchar2(50),
  form_data clob,
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN base_portal.id IS '主键';
COMMENT ON COLUMN base_portal.full_name IS '名称';
COMMENT ON COLUMN base_portal.encode IS '编码';
COMMENT ON COLUMN base_portal.description IS '描述';
COMMENT ON COLUMN base_portal.category IS '分类（数据字典）';
COMMENT ON COLUMN base_portal.form_data IS '表单配置JSON';
COMMENT ON COLUMN base_portal.sort IS '排序码';
COMMENT ON COLUMN base_portal.enabled_flag IS '启用标志(0-默认，禁用，1-启用)';
COMMENT ON COLUMN base_portal.del_flag IS '删除标志';
COMMENT ON COLUMN base_portal.create_time IS '创建时间';
COMMENT ON COLUMN base_portal.create_by IS '创建用户';
COMMENT ON COLUMN base_portal.update_time IS '修改时间';
COMMENT ON COLUMN base_portal.update_by IS '修改用户';
COMMENT ON TABLE base_portal IS '门户表';

-- ----------------------------
-- Table structure for base_visual_data
-- ----------------------------
CREATE TABLE base_visual_data (
  id number(20) NOT NULL,
  full_name varchar2(50),
  encode varchar2(50),
  description clob,
  category_id varchar2(50),
  screen_shot clob,
  password varchar2(50),
  component clob,
  detail clob,
  copy_id number(20),
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN base_visual_data.id IS '主键ID';
COMMENT ON COLUMN base_visual_data.full_name IS '名称';
COMMENT ON COLUMN base_visual_data.encode IS '编号';
COMMENT ON COLUMN base_visual_data.description IS '描述或说明';
COMMENT ON COLUMN base_visual_data.category_id IS '分类ID';
COMMENT ON COLUMN base_visual_data.screen_shot IS '大屏截图';
COMMENT ON COLUMN base_visual_data.password IS '访问密码';
COMMENT ON COLUMN base_visual_data.component IS '控件属性JSON包';
COMMENT ON COLUMN base_visual_data.detail IS '大屏配置JSON包';
COMMENT ON COLUMN base_visual_data.copy_id IS '复制id';
COMMENT ON COLUMN base_visual_data.sort IS '排序';
COMMENT ON COLUMN base_visual_data.enabled_flag IS '有效标志';
COMMENT ON COLUMN base_visual_data.del_flag IS '删除标志';
COMMENT ON COLUMN base_visual_data.create_time IS '创建时间';
COMMENT ON COLUMN base_visual_data.create_by IS '创建用户';
COMMENT ON COLUMN base_visual_data.update_time IS '修改时间';
COMMENT ON COLUMN base_visual_data.update_by IS '修改用户';
COMMENT ON TABLE base_visual_data IS '大屏数据';

-- ----------------------------
-- Table structure for base_visual_data_map
-- ----------------------------
CREATE TABLE base_visual_data_map (
  id number(20) NOT NULL,
  full_name varchar2(50),
  encode varchar2(50),
  data clob,
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
COMMENT ON COLUMN base_visual_data_map.id IS '主键ID';
COMMENT ON COLUMN base_visual_data_map.full_name IS '名称';
COMMENT ON COLUMN base_visual_data_map.encode IS '编号';
COMMENT ON COLUMN base_visual_data_map.data IS '地图数据';
COMMENT ON COLUMN base_visual_data_map.description IS '描述或说明';
COMMENT ON COLUMN base_visual_data_map.sort IS '排序';
COMMENT ON COLUMN base_visual_data_map.enabled_flag IS '有效标志';
COMMENT ON COLUMN base_visual_data_map.del_flag IS '删除标志';
COMMENT ON COLUMN base_visual_data_map.create_time IS '创建时间';
COMMENT ON COLUMN base_visual_data_map.create_by IS '创建用户';
COMMENT ON COLUMN base_visual_data_map.update_time IS '修改时间';
COMMENT ON COLUMN base_visual_data_map.update_by IS '修改用户';
COMMENT ON TABLE base_visual_data_map IS '大屏地图';

-- ----------------------------
-- Table structure for base_visual_dev
-- ----------------------------
CREATE TABLE base_visual_dev (
  id number(20) NOT NULL,
  full_name varchar2(100),
  encode varchar2(50),
  description clob,
  category varchar2(50),
  state number(8),
  type number(8),
  db_link_id number(20),
  ref_tables clob,
  form_data clob,
  column_data clob,
  template_data clob,
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN base_visual_dev.id IS '主键';
COMMENT ON COLUMN base_visual_dev.full_name IS '名称';
COMMENT ON COLUMN base_visual_dev.encode IS '编码';
COMMENT ON COLUMN base_visual_dev.description IS '描述';
COMMENT ON COLUMN base_visual_dev.category IS '分类（数据字典）';
COMMENT ON COLUMN base_visual_dev.state IS '状态(0-暂存（默认），1-发布)';
COMMENT ON COLUMN base_visual_dev.type IS '类型(1-Web在线开发,2-移动在线开发,3-流程表单,4-Web表单,5-移动表单)';
COMMENT ON COLUMN base_visual_dev.db_link_id IS 'db_link外键';
COMMENT ON COLUMN base_visual_dev.ref_tables IS '关联的表';
COMMENT ON COLUMN base_visual_dev.form_data IS '表单配置JSON';
COMMENT ON COLUMN base_visual_dev.column_data IS '列表配置JSON';
COMMENT ON COLUMN base_visual_dev.template_data IS '前端模板JSON';
COMMENT ON COLUMN base_visual_dev.sort IS '排序码';
COMMENT ON COLUMN base_visual_dev.enabled_flag IS '启用标志(0-默认，禁用，1-启用)';
COMMENT ON COLUMN base_visual_dev.del_flag IS '删除标志';
COMMENT ON COLUMN base_visual_dev.create_time IS '创建时间';
COMMENT ON COLUMN base_visual_dev.create_by IS '创建用户';
COMMENT ON COLUMN base_visual_dev.update_time IS '修改时间';
COMMENT ON COLUMN base_visual_dev.update_by IS '修改用户';
COMMENT ON TABLE base_visual_dev IS '可视化开发功能表';

-- ----------------------------
-- Table structure for base_visual_dev_model_data
-- ----------------------------
CREATE TABLE base_visual_dev_model_data (
  id number(20) NOT NULL,
  visual_dev_id number(20),
  parent_id number(20),
  data clob,
  sort number(8),
  enabled_flag char(1) DEFAULT 1,
  del_flag char(1) DEFAULT 0,
  create_time date,
  create_by varchar2(64),
  update_time date,
  update_by varchar2(64)
)
;
COMMENT ON COLUMN base_visual_dev_model_data.id IS '主键';
COMMENT ON COLUMN base_visual_dev_model_data.visual_dev_id IS '功能ID';
COMMENT ON COLUMN base_visual_dev_model_data.parent_id IS '区分主子表';
COMMENT ON COLUMN base_visual_dev_model_data.data IS '数据包';
COMMENT ON COLUMN base_visual_dev_model_data.sort IS '排序码';
COMMENT ON COLUMN base_visual_dev_model_data.enabled_flag IS '启用标志(0-默认，禁用，1-启用)';
COMMENT ON COLUMN base_visual_dev_model_data.del_flag IS '删除标志';
COMMENT ON COLUMN base_visual_dev_model_data.create_time IS '创建时间';
COMMENT ON COLUMN base_visual_dev_model_data.create_by IS '创建用户';
COMMENT ON COLUMN base_visual_dev_model_data.update_time IS '修改时间';
COMMENT ON COLUMN base_visual_dev_model_data.update_by IS '修改用户';
COMMENT ON TABLE base_visual_dev_model_data IS '0代码功能数据表';

-- ----------------------------
-- Primary Key structure for table base_common_fields
-- ----------------------------
ALTER TABLE base_common_fields ADD CONSTRAINT base_common_fields_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table base_data_interface
-- ----------------------------
ALTER TABLE base_data_interface ADD CONSTRAINT base_data_interface_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table base_db_link
-- ----------------------------
ALTER TABLE base_db_link ADD CONSTRAINT base_db_link_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table base_portal
-- ----------------------------
ALTER TABLE base_portal ADD CONSTRAINT base_portal_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table base_visual_data
-- ----------------------------
ALTER TABLE base_visual_data ADD CONSTRAINT base_visual_data_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table base_visual_data_map
-- ----------------------------
ALTER TABLE base_visual_data_map ADD CONSTRAINT base_visual_data_map_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table base_visual_dev
-- ----------------------------
ALTER TABLE base_visual_dev ADD CONSTRAINT base_visual_dev_pkey PRIMARY KEY (id);

-- ----------------------------
-- Primary Key structure for table base_visual_dev_model_data
-- ----------------------------
ALTER TABLE base_visual_dev_model_data ADD CONSTRAINT base_visual_dev_model_data_pk PRIMARY KEY (id);
