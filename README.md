## 系统说明

- 基于 Spring Cloud 2021 、Spring Boot 2.7、 OAuth2 的 RBAC **权限管理系统**
- 基于数据驱动视图的理念封装 element-ui，即使没有 vue 的使用经验也能快速上手
- 提供对常见容器化支持 Docker、Kubernetes、Rancher2 支持
- 提供 lambda 、stream api 、webflux 的生产实践

### 核心依赖

| 依赖                   | 版本          |
| ---------------------- | ------------- |
| Spring Boot            | 2.7.5 |
| Spring Cloud           | 2021.0.4 |
| Spring Cloud Alibaba   | 2021.0.4.0 |
| Spring Security OAuth2 | 2.3.6 |
| Mybatis Plus           | 3.5.2 |
| hutool                 | 5.8.8 |
| Avue                   | 2.6.18 |

### 模块说明

```lua
booster-cloud-ui

booster-cloud
├── booster-dependencies -- 平台依赖版本统一定义
├── booster-auth -- 授权服务提供[3000]
├── booster-common -- 系统公共模块
     ├── booster-common-core -- 公共工具类核心包
     ├── booster-common-database -- 关系数据库元数据查询组件
     ├── booster-common-feign -- feign 扩展封装
     ├── booster-common-job -- xxl-job 封装
     ├── booster-common-log -- 日志服务
     ├── booster-common-minio -- minio存储服务
     ├── booster-common-mybatis -- mybatis 扩展封装
     ├── booster-common-risk -- 风控引擎集成组件
     ├── booster-common-security -- 安全工具类
     ├── booster-common-swagger -- 接口文档
     ├── booster-common-test -- oauth2.0 单元测试扩展封装
     ├── booster-common-visualdev -- 可视化开发模块公共工具类
     └── booster-common-encrypt -- 加密、脱敏工具类
├── booster-register -- Nacos Server[8848]
├── booster-gateway -- Spring Cloud Gateway网关[9999]
├── booster-seata -- seata Server[8091]
├─ booster-upms -- 通用用户权限管理模块
     ├── booster-upms-api -- 通用用户权限管理系统公共api模块
     └── booster-upms-biz -- 通用用户权限管理系统业务处理模块[4000]
├─ booster-visual
     ├── booster-monitor -- 服务监控 [5001]
     ├── booster-sentinel-dashboard -- 流量高可用 [5003]
     ├── booster-xxl-job-admin -- 分布式定时任务管理台 [5004]
     ├── booster-visualdev -- 可视化表单开发 [5006]
     ├── booster-workflow -- 工作流 [5007]
     └── booster-datareport -- 数据报表ureport [5008]
├─ booster-infection -- 传染病演示项目
     ├── booster-infection-api -- 传染病演示项目公共api模块
     └── booster-infection-biz -- 传染病演示项目业务处理模块[7000]
└── booster-mqroute -- 数据总线
     ├── booster-mqroute-api -- 数据总线公共api模块
     └── booster-mqroute-biz -- 数据总线业务处理模块[7001]
```

### Docker 运行

```
# 运行服务端代码
cd booster-cloud && mvn clean install && docker-compose up -d

# 运行前端UI
cd booster-cloud-ui && npm install -g cnpm --registry=https://registry.npm.taobao.org

npm run build:docker && docker-compose up -d
```

### 系统账号说明

- 前端默认登录 http://ip:8080
  
  | 用户名 | 密码 |
  | ----- | ---- |
  | admin | 123456 |
  
- [booster-register注册配置模块](http://booster-register:8848/nacos)

  | 用户名 | 密码 |
    | ----- | ---- |
  | nacos | nacos |

- [booster-monitor监控模块](http://booster-monitor:5001)

  | 用户名 | 密码 |
      | ----- | ---- |
  | booster | booster |

- [booster-sentinel-dashboard监控模块](http://booster-sentinel:5003)

  | 用户名 | 密码 |
        | ----- | ---- |
  | sentinel | sentinel |

- [booster-xxl-job-admin任务调度](http://booster-job:5004/xxl-job-admin)

  | 用户名 | 密码 |
          | ----- | ---- |
  | admin | 123456 |

- [booster-gateway swagger聚合模块](http://booster-gateway:9999/swagger-ui/index.html)

  | 用户名 | 密码 |
  | ----- | ---- |
  | booster | booster |
