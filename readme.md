# 后端

## 拉取

```bash
git clone https://github.com/zhangyushao0/studentServer.git --recursive
```

## 介绍

```bash
src/main/java/com/studentDemo/
```

为代码源文件路径，结构模仿 student，写自己的模块。
例如 student 模块，需要在 student 文件夹下的两个文件，Student.java 和 StudentServiceImpl.java。分别为学生类的实现和接口的实现。

## 实现接口

同时需要先在

```bash
src/main/proto
```

下对接口和消息作出定义，例如 student.proto，定义了学生的接口和消息。
需要先使用 grpc 的编译器，例如使用终端的命令

```bash
mvn clean compile
```

或者 IDE 中也有图形化选项，将 student.proto 编译成 java 文件，生成的文件在

```bash
target/generated-sources/protobuf/grpc-java/com/studentDemo/
```

下。然后就可以对 StudentServiceImpl.java 进行实现。

## 将接口添加进入服务

在

```bash
src/main/java/com/studentDemo/server/SystemServer.java
```

中，仿照已有的接口，将自己的接口添加进去。

## 如何使用数据库

本作品使用的是 mysql 数据库，并且使用了 hibernate 框架。依赖已经加好了。首先需要在

```bash
src/main/hibernate/hibernate.cfg.xml
```

进行配置你的数据库，配置什么已在文件里标出。后面就可以在你的代码中使用 hibernate 框架进行数据库操作了。可以模仿 user 模块中的代码进行操作。使用 Data Access Object (DAO) 模式，后续的代码中，访问数据库的操作可以仿照 src/main/java/com/studentDemo/user/UserDAOImpl.java

src/main/java/com/studentDemo/student 仅作为示例，实际的 student 和其它的例如 teacher，admin 均是继承于 user。
