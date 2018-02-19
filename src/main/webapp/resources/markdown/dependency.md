# 项目依赖

Wise man 曾经说过，要站在巨人的肩膀上，因此本 `repo` 踩了很多巨人的肩膀，具体说明如下： 
 
## 服务依赖

考虑实验室环境实际，尽量少的依赖外部服务，依赖的服务如下： 

* `Active MQ`： 项目使用 ActiveMQ 作为消息总线，同时，为了兼容`MQTT`协议，需要配置 MQ 支持 MQTT，项目前端使用了`STOMP` over `WebSocket`，MQ 也需要进行相应的配置。MQ 的地址需在 `WEB-INF/spring/activemq-context.xml`中配置相应的`connectionFactory`。
* `MySQL`：项目使用 MySQL 作为数据库，开发使用的是 5.7.16，部署项目前请先执行根目录下的`cloudtesting.sql`。

## 后端依赖

后端使用 `java` 编写，使用 `maven` 管理依赖，具体详见 `pom.xml`。  
几个主要的依赖说明如下： 

* `Spring` 编织整个项目
* `SpringMVC` 作为 web 框架
* `Thymeleaf` 模板渲染引擎
* `Hibernate` ORM
* `ActiveMQ` JMS client
* `Ehcache` 进程内 cache

## 前端依赖

> 不要跟我说什么 webpack，React，Vue.js，也不要说什么 ES6 ，老夫就用 jQuery，复制！粘贴！拿起键盘就是干！

秉承着以上原则，前端进行了比较挫的实现。  
前端主要使用`jQuery`和`Bootstrap`，另外还依赖了许多开源项目，由于`repo`中没有记录这些第三方库，所以在此进行整理说明。  
依赖列表如下：
* [jQuery 3](https://github.com/jquery/jquery)
* [Bootstrap 3](https://github.com/twbs/bootstrap/releases/tag/v3.3.6)
* [font awesome 4](https://github.com/FortAwesome/Font-Awesome)
* [echarts 3](https://github.com/ecomfe/echarts) 画图
 
部署项目时需要建立`src/main/webapp/resources/dist/`路径，并将前端依赖按照如下目录结构存放至该路径下  

```
dist/ -- 
```

  
 
