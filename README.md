## 前后端分离后端基础模板
### 说明
- 基于Spring Boot的前后端分离后端基础模板，前端界面完善中

### 特点
- 点后端分离，通过token进行数据交互
- 灵活的权限控制，具体到每个API的访问权限
- 数据库基础操作封装，提高开发效率

### API访问权限设计思想
- 角色管理，用户管理，权限管理，菜单管理
- 根据不同角色获取对应API访问权限

### 项目结构
```
BootOAuthor
  |-db    初始化数据SQL
  |-src
    -main
      -java
        -kennerl
          -configure  配置类
          -controller API
          -dao        数据操作dao层
          -model      数据实体和数据传输bean
          -service    业务逻辑处理层
          -utils      工具类
      -resource
        -META-INF     前端模板beetl配置文件
        -sqls         自定义SQL文件
        -static       前端静态资源
        -templates    前端视图页面
        -application*.yml 启动配置文件
        -beetl.yml    beetl模板配置文件
```


### 技术选型
- 核心框架：Spring Boot 2.0.0
- 安全框架：Apache Shiro 1.4.0
- 持久层框架：Nutz 1.r.63
- 数据库连接池：druid 1.1.8
- 数据库：PostgreSQL

### 本地部署
- 通过Git下载源码
- 创建数据库boot_oauthor，数据库编码为UTF-8
- 配置文件application.yml修改username，password
- 运行HomeStarter.java
- 数据库执行db.sql文件初始化基础数据
- API访问地址：例：http://localhost:8080/api/login
