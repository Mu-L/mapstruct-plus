---
home: true
icon: home
title: 主页
description: MapStructPlus MapStruct Home 首页
heroText: MapstructPlus
tagline: 可能是最简单最强大的Java Bean转换工具
actions:
  - text: 快速开始 💡
    link: /introduction/quick-start/
    type: primary

features:
  - title: 快速
    icon: launch
    details:  Java 类之间的转换，仅需要增加一个注解，减少了乏味且容易出错的开发任务

  - title: 效率
    icon: light
    details: 基于注解处理器，所有生成工作都在编译期完成

  - title: 转换
    icon: change
    details: 属性转换基于类中的 getter/setter 方法

  - title: 兼容
    icon: any
    details: 支持JDK8~17、SpringBoot2~3

  - title: 多类转换
    icon: structure
    details: 支持单个类配置多个类型转换

  - title: Map转对象
    icon: type
    details: 更强大的Map转对象功能

copyright: false
---

## 最新版本

- maven

```xml
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
    <version>1.1.7</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.1.7'
```

## 更新日志

### 1.1.7

- fixBug: [issue#8](https://github.com/linpeilie/mapstruct-plus/issues/11) Converter 转换已有对象不生效的问题
- 添加寻找转换接口的缓存，转换速度更快
- 使用 Java 17 打包，解决 Java 17 环境下编译警告的问题

……

## 代码仓库

- [Github](https://github.com/linpeilie/mapstruct-plus)
- [Gitee](https://gitee.com/linpeilie/mapstruct-plus)

## 参与贡献

- 贡献代码：欢迎提交 Issue 或 Pull Requests
- 维护文档：项目目录下的 docs 目录，欢迎参与翻译与修订

## 推荐文章

- [彻底干掉 BeanUtils，最优雅的 Mapstruct 增强工具全新出炉](https://juejin.cn/post/7204307381688909882)

## 友情链接

- [RuoYi-Vue-Plus](https://gitee.com/JavaLionLi/RuoYi-Vue-Plus/wikis/pages)
- [RuoYi-Cloud-Plus](https://gitee.com/JavaLionLi/RuoYi-Cloud-Plus/wikis/pages)

## 联系我

![Wechat --- Clue8a796d01](/assets/contact-me.png =346x403)

