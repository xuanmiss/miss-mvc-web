### 实践一个类似 `springboot` 的 `web` 框架

### 项目目录
```text
miss-mvc-web
├── framework
│   ├── DEV.md
│   ├── miss-core
│   ├── miss-server
│   ├── miss-web
│   └── pom.xml
├── miss-test
│   ├── miss-test.http
│   ├── pom.xml
│   ├── src
│   └── target
├── pom.xml
├── src
│   ├── main
│   └── test
├── test.http
└── tomcat.8080
    └── work

```

### 项目功能 `feature`

- [x] 完善 `@ResponseBody` 的 `json` 序列化和反序列化相关
- [x] 完善 `@RequestMapping` 的 `method` 属性，区别请求类型，支持 `GET`, `POST`
- [x] 完善 `classLoader` 允许扫描符合规范的外部jar包和加载 `bean`
- [ ] 添加 `@AliasFor` 注解，处理别名和注解的注解 
- [ ] 完善 `GET`, `POST`, `PUT`, `DELETE` 等 `RESTful api`
- [x] 添加 `web server` 抽离，支持自定义web服务器，以 `starter` 方式加载  
- [ ] 增加支持现有的 `data` 相关框架 `mybatis` 的 `starter` 版本
