# 学生作业管理系统

[![GitHub stars](https://img.shields.io/github/stars/itning/Student-Homework-Management-System.svg)](https://github.com/itning/Student-Homework-Management-System/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/itning/Student-Homework-Management-System.svg)](https://github.com/itning/Student-Homework-Management-System/network)
[![GitHub watchers](https://img.shields.io/github/watchers/itning/Student-Homework-Management-System.svg?style=social&label=Watch)]()
[![GitHub followers](https://img.shields.io/github/followers/itning.svg?style=social&label=Follow)]()
[![GitHub issues](https://img.shields.io/github/issues/itning/Student-Homework-Management-System.svg)](https://github.com/itning/Student-Homework-Management-System/issues)

[![GitHub license](https://img.shields.io/github/license/itning/Student-Homework-Management-System.svg)](https://github.com/itning/Student-Homework-Management-System/blob/master/LICENSE)
[![GitHub last commit](https://img.shields.io/github/last-commit/itning/Student-Homework-Management-System.svg)]()
[![GitHub release](https://img.shields.io/github/release/itning/Student-Homework-Management-System.svg)]()
[![GitHub repo size in bytes](https://img.shields.io/github/repo-size/itning/Student-Homework-Management-System.svg)]()
[![language](https://img.shields.io/badge/language-JAVA-orange.svg)]()

**实现功能：**

学生上传文件到系统中，后台统一命名存储文件，管理员登陆后可以批量下载后台打包过后的文件。

## 架构

### 前端

1. jQuery
2. Bootstrap3
3. Bootstrap-fileinput

### 后端

1. Spring MVC
2. Spring
3. Mybatis
4. Shiro （安全框架）
5. Druid（阿里巴巴的开源连接池）
6. MySql

## 部署

1. 下载源码

   你可以在Git（[Git是什么，如何使用？](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/)）输入以下命令进行clone项目到本地

   ``git clone https://github.com/itning/Student-Homework-Management-System.git``

   或者直接下载主分支的[源码](https://github.com/itning/Student-Homework-Management-System/archive/master.zip)

2. 使用编译器打开本项目

   该项目使用IntelliJ IDEA编译器编写打包，建议同学们使用该编译器进行打开

   项目使用JDK1.8进行编写，用到了JDK1.8+特性，所以最低JDK版本为1.8

3. 导入SQL文件

   由于该项目不会自动建库建表，所以你需要手动导入SQL

   更改JDBC连接信息（数据库名，用户名，密码）

   [context.properties](https://github.com/itning/Student-Homework-Management-System/blob/master/src/main/resources/context.properties)

   其中 **user** 键为数据库的用户名

   其中 **password** 键为数据库的密码

4. 更改上传文件存储目录

   用户上传的文件需要持久化到硬盘上，你需要配置持久化目录

   [在这里配置](https://github.com/itning/Student-Homework-Management-System/blob/master/src/main/resources/context.properties#L8)

5. 安装QQ互联SDK

   这一步是为了能够使用QQ进行登陆

   [官方下载互联SDK](http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/doc/qqConnect_Server_SDK_java_v2.0.zip)

   ```shell
   mvn install:install-file -Dfile=D:\a.jar -DgroupId=com.qq.connect -DartifactId=qq-connect -Dversion=2.0.0.RELEASE -Dpackaging=jar
   ```

6. 运行

   项目使用的Tomcat版本为8.5.20，你最好与我同步。[下载该版本](https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.20/bin/)

7. 查看Druid管理面板

   默认用户名：``itning``

   默认密码：``kingston``

   页面：``http://localhost:8080/druid``

   [如何更改用户名密码？](https://github.com/itning/Student-Homework-Management-System/blob/master/src/main/webapp/WEB-INF/web.xml#L84)

## SQL

1. 创建数据库

   ```sql
   CREATE DATABASE IF NOT EXISTS shw CHARACTER SET utf8mb4;
   USE shw;
   ```

2. 导入表结构和数据

   ```sql
   SET FOREIGN_KEY_CHECKS=0;
   
   -- ----------------------------
   -- Table structure for history
   -- ----------------------------
   DROP TABLE IF EXISTS `history`;
   CREATE TABLE `history` (
     `hid` varchar(255) NOT NULL,
     `huid` varchar(255) NOT NULL,
     `hoid` int(11) NOT NULL,
     `type` varchar(255) NOT NULL,
     `filepath` varchar(255) NOT NULL,
     `uptime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
     `filesize` double NOT NULL,
     PRIMARY KEY (`hid`),
     KEY `FK_hoid_oid` (`hoid`) USING BTREE,
     KEY `FK_huid_uid` (`huid`) USING BTREE,
     CONSTRAINT `history_ibfk_1` FOREIGN KEY (`hoid`) REFERENCES `orderinfo` (`oid`),
     CONSTRAINT `history_ibfk_2` FOREIGN KEY (`huid`) REFERENCES `user` (`uid`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   -- ----------------------------
   -- Table structure for orderinfo
   -- ----------------------------
   DROP TABLE IF EXISTS `orderinfo`;
   CREATE TABLE `orderinfo` (
     `oid` int(11) NOT NULL,
     `oname` varchar(255) NOT NULL,
     `osubject` varchar(255) NOT NULL,
     `ostate` bit(1) NOT NULL,
     `otime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (`oid`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   -- ----------------------------
   -- Records of orderinfo
   -- ----------------------------
   INSERT INTO `orderinfo` VALUES ('1492109980', '第二次作业', 'UI交互设计', 1, '2018-11-28 14:48:53');
   INSERT INTO `orderinfo` VALUES ('795960272', '第二次作业', '软件测试', 1, '2018-11-28 14:38:11');
   
   -- ----------------------------
   -- Table structure for user
   -- ----------------------------
   DROP TABLE IF EXISTS `user`;
   CREATE TABLE `user` (
     `uid` varchar(255) NOT NULL,
     `username` varchar(255) NOT NULL,
     `password` varchar(255) NOT NULL,
     `headimg` varchar(255) DEFAULT NULL,
     `firstlogin` bit(1) NOT NULL DEFAULT b'1',
     `name` varchar(255) NOT NULL,
     `percode` varchar(255) NOT NULL,
     `userOpenID` varchar(255) DEFAULT NULL,
     PRIMARY KEY (`uid`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   -- ----------------------------
   -- Records of user
   -- ----------------------------
   INSERT INTO `user` VALUES ('1', '000000000000', '0123456789', null, 1, '管理员', 'admin', null);
   INSERT INTO `user` VALUES ('2', '111111111111', '123456789', null, 1, '用户1', 'user', null);
   ```

   ## 预览

   