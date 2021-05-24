# 简介

基于SpringBoot + React实现的权限管理脚手架，使用它可以快速帮助我们开发后台管理系统，
可以实时管理用户的在线状态以及实时管理用户的上下线状态，
项目后端核心技术采用 SpringBoot、MyBatisPlus、SpringSecurity；前端主要用于 React、Redux、ReactRouterDom、TypeScript(考虑中) 模块化结构简单易上手

# 功能

1. 流程化管理:
2. 用户管理:
3. 菜单管理:
4. 角色管理:
5. 系统设置:
6. 通知公告:
7. 操作日志:
8. 登录日志:
9. 动态权限:
10. 抢占登陆：
11. 在线状态：

# 目录说明

```text
├─io-common                             公共包
│  ├─io-common-core                     核心包，工具类，通用配置等都在此包
│  └─io-common-security                 权限
├─io-service                            业务模块，新增业务直接在此模块下创建一个新的子模块
│  └─io-system                          系统管理模块
│      └─src
│          ├─main
│          │  ├─java
│          │  │  └─io
│          │  │      └─mvvm
│          │  │          ├─controller   API 接口
│          │  │          ├─entity       实体
│          │  │          │  ├─domain    领域对象，简单说就是对应数据库表的字段
│          │  │          │  ├─dto       数据传输对象，用来在controller层接收前端传递来的参数
│          │  │          │  └─vo        视图对象，将数据返回给前端，进一步封装
│          │  │          ├─mapper       数据访问层
│          │  │          └─service      业务实现
│          │  │              └─impl
│          │  └─resources
│          │      └─mapper              mybatis 的 xml
│          └─test                       测试
├─io-web                                web模块，主要用于启动和配置一些唯一配置
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─io
│      │  │      └─mvvm
│      │  │          └─config           一些配置
│      │  └─resources
│      │      └─config
└─sql                                   SQL
```