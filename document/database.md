# 如何在后端使用数据库
环境：
Ubuntu 20.04 LTS, sqlite3 3.37.2, maven 5.15.0, openjdk-17
依赖：必要的依赖已经推送到了main的pom.xml中
## 1. 安装sqlite3
去往[sqlite3](https://www.sqlite.org/download.html)网站下载对应系统的编译好的可执行文件，解压后将可执行文件路径加入环境变量。
## 2. 简介Hibernate
Hibernate 是一个开源的 Java 持久化框架，它对 JDBC 进行了非常轻量级的对象封装，它允许开发者采用面向对象的方式来操作数据库。
使用Hibernate，可以为java中的对象建立一个到数据库的数据库。
## 3. Hibernate使用方法
例如，以下为你想储存于数据库的学生类
```java
public class Student {
    private String id;
    private String name;
    // getter and setter
}
```
首先需要使用注解标注该类为实体类
```java
@Entity
public class Student {
    private String id;
    private String name;
    // getter and setter
}
```
然后需要标注该类的主键
```java
@Entity
public class Student {
    @Id
    private String id;
    private String name;
    // getter and setter
}
```
接下来需要配置hibernate的配置文件src/main/resources/hibernate.cfg.xml
```xml
<!-- Mention annotated class or mapping xml files -->
```
下方添加一条
```xml
<mapping class="com.studentDemo.student.Student"/>
```
class名为你的实体类的全名
接下来你需要编写一些调用hibernate存储你的实体的方法，推荐的方法是使用DAO模式，即创建一个DAO类，将所有的数据库操作封装在这个类中。例如src/main/java/com/studentDemo/user/UserDAO.java
```java
public interface UserDAO {
    User getUserById(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);
}
```
实现请查看
src/main/java/com/studentDemo/user/UserDAOImpl.java

在刚开始可以照葫芦画瓢去做，还有一些更高级的用法，例如实现一对多的关系，例如继承的也加入映射可以参考网上资料，同时也可以查看[这里](https://github.com/zhangyushao0/studentServer/blob/db_bank_shop/src/main/java/com/studentDemo/campusStore/Product.java)，这个是我实现商店模块时的代码，其中有一对多的关系，可以参考。