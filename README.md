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

- 完善 `@ResponseBody` 的 `json` 序列化和反序列化相关
- 完善 `@RequestMapping` 的 `method` 属性，区别请求类型，支持 `GET`, `POST`
- 完善 `GET`, `POST`, `PUT`, `DELETE` 等 `RESTful api`
- 增加支持现有的 `data` 相关框架 `mybatis` 的 `starter` 版本
