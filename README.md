moana V1.0
====  
这是一个电影票抢购系统demo，使用Java语言实现<br>
# 系统部署
CentOS-6.5<br>
Nginx-1.11.0<br> 
Tomcat-7.0<br>
mariadb-10.0.28<br>
redis-3.2.6<br>
## 部署方式
win7/win10环境下使用虚拟机CentOS6.5单机部署以上组件，nignx做请求分发，Tomcat为servlet容器，mariadb用于磁盘持久化数据，redis用于缓存高并发访问数据。<br>
(数据库脚本见src/main/resources/initTable.sql)
# 技术选型
## 后端
Spring4.1.7+SpringMVC4.1.7+DataNucleus4.2 JDO完全整合
## 前端
jquery+BootStrap3.0
## 前后端数据交互
Ajax+json异步数据交互
## 项目管理
Maven+Git
# 特性
## 解决并发安全问题
1.由于spring默认单例需要考虑并发读写bean的安全，在action、service、dao中避免使用有状态的成员变量，所有变量都是通过方法传参解决并发访问安全问题。<br>
2.数据库层面配置spring隔离级别防止脏读，避免了数据库并发读写安全。同时配置声明式事务解决事务原子性问题。
## 抢购逻辑功能需求
1.为电影票添加开始时间和结束时间，防止用户提前抢购或在抢购结束后继续抢购。<br>
2.服务端动态random抢购uri发给客户端，防止用户获取uri规律，篡改url进行抢购。<br>
3.每个QQ号只能抢购一部电影一次，否则js会提醒重复抢购。<br>
4.登录界面由于申请三方登录接口失败，只要求输入正确的QQ号以便后面接收邮件，不对QQ密码校验。<br>
5.实时库存功能，客户端调用js定时3s轮询服务端，服务端返回数据后客户端异步回调更新实时库存。<br>
6.邮件功能，使用javax.mail实现。<br>
7.增加抢购倒计时功能。<br>
8.解决js跨域问题。<br>
## TPS/QPS(等待测试)
# 页面效果
## Login
![image](https://github.com/youHappyOK/moana/raw/master/screenshots/login.png)<br>
##MovieList
![image](https://github.com/youHappyOK/moana/raw/master/screenshots/movielist.png)<br>
## MovieDetail
可以抢购<br>
![image](https://github.com/youHappyOK/moana/raw/master/screenshots/detail_1.png)<br>

----------
未开始，倒计时<br>
![image](https://github.com/youHappyOK/moana/raw/master/screenshots/detail_2.png)<br>
