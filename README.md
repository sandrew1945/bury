# Bury

**Bury是一个轻量级的持久层框架**

Bury是用Java开发，是一个轻量级的ORM持久层框架，暂时支持Oracle、mssql、mysql数据库，可以单独使用，也可以配合Spring使用，主要功能如下：

* 可以提供ORM及SQL两种形式操作数据库
* 多数据库支持
* 数据库事务支持
* 分布式事务支持
* 根据数据库表结构生成Model
* 分页查询功能
* 支持与SpringMVC集成

##使用方法：

###生成model
```xml
<?xml version="1.0" encoding="UTF-8"?>
<PO_CONFIG>
	<DB_CONNECTION TYPE="oracle" VALID="TRUE">
		<PROPERTY NAME="DB_URL">jdbc:mysql://dbip:port/schema</PROPERTY>
		<!--<PROPERTY NAME="DB_URL">jdbc:oracle:thin:@dbip:port:schema</PROPERTY>-->
		<PROPERTY NAME="DB_USER">root</PROPERTY>
		<PROPERTY NAME="DB_PASSWORD">workout</PROPERTY>
	</DB_CONNECTION>

	<GEN_PO_PATH NAME="model actual disk path. eg:/Users/xxx/Documents/bury/src/java/com/bury/model" />

	<TABLES PACKAGE="model package name" IS_GENERATOR="TRUE">
		<TABLE TAB_NAME="table name" PO_NAME="model name"></TABLE>
	</TABLES>
</PO_CONFIG>
```