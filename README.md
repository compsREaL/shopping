基于Spring Boot2 开发的一个电商购物demo

后端技术：

	框架使用：Spring，SpringMVC，Mybatis;
	数据库：Mysql
	数据库连接池：Alibaba Druid;
	缓存管理：Redis;
	日志管理：Slf4j，log4j;
	使用mybatis-generator插件自动生成对应DataObject，Dao以及mapper映射文件。
前端技术：

	框架使用：jQuery;
	使用Ajax发送异步请求；
	使用HTML5完成前端页面的编写。


项目部署成功之后，访问主页 http://localhost:8090/list.html 即可进行相关操作：
	
	用户的注册及登录（使用MD5对密码加密，数据表中所有用户登录密码都为123456）；
	商品的创建及购买（商品的创建需要"admin"权限，新注册用户权限默认为"customer"）；
	商品的秒杀活动。
