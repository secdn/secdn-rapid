**项目介绍**

secdnrapid是一个基于`Spring Boot`搭建的企业级快速开发脚手架。集成了`MyBatis-Plus`和`MyBatis-Plus AutoGenerator`，支持自动
生成CURD接口所有相关代码；集成了`Shiro`，已经实现了按钮粒度级的权限控制，已经集成完整的用户体系代码。只需要专注于业务代码
的开发，接私活的利器




**项目结构** 
```
secdn-secdnrapid
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─business 业务模块
│  ├─oss 文件服务模块
│  └─sys 用户体系模块
│ 
├─SecdnRapidApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源

```



**技术栈：** 
- 核心框架：Spring Boot 2.1.3
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 5.1.5
- 持久层框架：MyBatis 3.3、MyBatis-Plus 3.1.0
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
<br> 


 **部署**
- 下载源码
- 创建数据库，数据库编码为UTF-8，执行`db`文件夹下的sql
- 安装`lombok`插件
- 修改`application-dev.yml`，更新MySQL账号和密码
- 启动`SecdnRapidApplication.java`
- `Swagger`路径：http://localhost:8986/swagger-ui.html
- 登陆名：`admin`，密码：`admin`

<br> 


**生成代码**
- 建表
- 修改`mybatis-plus.properties`下的账户和密码，更新tableName,输入需要生成代码的表名
- 修改`mybatis-plus.properties`下的`parent`值
- 运行`generator/Generator.java`即可在对应目录下生成业务代码

<br> 

