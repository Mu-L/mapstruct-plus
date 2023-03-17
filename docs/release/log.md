---
title: 更新日志
order: 1
category:
- 更新日志
description: MapStructPlus release log
---

## 1.1.7

- fixBug: [issue#8](https://github.com/linpeilie/mapstruct-plus/issues/11) Converter 转换已有对象不生效的问题
- 添加寻找转换接口的缓存，转换速度更快
- 使用 Java 17 打包，解决 Java 17 环境下编译警告的问题

## 1.1.6

- 支持在添加 `AutoMapper` 的类中，配置目标类到当前类的转换规则，适配多种场景下的使用；
- `AutoMapper` 增加注解，提供可以配置是否生成转换接口的功能；
- `AutoMapping` 的 target 属性默认可以不填，不填则取当前字段
- 升级 mapstruct 版本为 1.5.3.FINAL

## 1.1.5

- `AutoMapping` 增加 `source` 和 `defaultValue` 属性支持

## 1.1.4

- 增加反向转换配置功能
- 解决树状结构转换bug

## 1.1.3

- 适配 SpringBoot3

## 1.1.1

- 增加 Map 转对象的功能
- 增加单个对象与多个对象转换并配置的功能