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

## 使用方法：

### 生成model
编写生产model配置文件，如下：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<PO_CONFIG>
	<DB_CONNECTION TYPE="oracle" VALID="TRUE">
		<PROPERTY NAME="DB_URL">jdbc:mysql://dbip:port/schema</PROPERTY>
		<!--<PROPERTY NAME="DB_URL">jdbc:oracle:thin:@dbip:port:schema</PROPERTY>-->
		<PROPERTY NAME="DB_USER">user</PROPERTY>
		<PROPERTY NAME="DB_PASSWORD">password</PROPERTY>
	</DB_CONNECTION>

	<GEN_PO_PATH NAME="model actual disk path. eg:/Users/xxx/Documents/bury/src/java/com/bury/model" />

	<TABLES PACKAGE="model package name" IS_GENERATOR="TRUE">
		<TABLE TAB_NAME="table name" PO_NAME="model name"></TABLE>
	</TABLES>
</PO_CONFIG>
```
然后执行如下代码生成：
```java
try
{
	String configFile = "POConf.xml";
	POGenerator poGenerator = new POGenerator();
	poGenerator.gen("POConf.xml");
}
catch (Exception e)
{
	e.printStackTrace();
}
```
这样就会在GEN_PO_PATH中设置的磁盘目录中看到生产的model

## 基本API
```java
    /**
     *
     * Function    : 通过SQL的insert方法
     * @param sql		SQL
     * @param params	参数集合
     * @return			插入记录数
     */
    int insert(String sql, List<Object> params);

    /**
     *
     * Function    : 通过SQL的insert方法(LOB版本)
     * @param sql
     * @param params
     * @return
     */
    int insertForLob(String sql, List<Object> params);

    /**
     *
     * Function    : 通过PO的insert方法
     * @param po		PO对象
     * @return			插入记录数
     * @throws POException
     */
    int insert(PO po) throws POException;
	
    /**
     *
     * Function    : 通过SQL的delete方法
     * @param sql		SQL
     * @param params	参数集合
     * @return			删除记录数
     * @throws POException
     */
    int delete(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过PO的delete方法
     * @param po		PO对象
     * @return			删除记录数
     * @throws POException
     */
    int delete(PO po) throws POException;

    /**
     *
     * Function    : 通过SQL的update方法
     * @param sql		SQL
     * @param params	参数集合
     * @return			更新记录数
     * @throws POException
     */
    int update(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过PO的update操作
     * @param cond		作为where条件的PO对象
     * @param value		作为更新值的PO对象
     * @return			更新记录数
     * @throws POException
     */
    int update(PO cond, PO value) throws POException;

    /**
     *
     * Function    : 通过SQL的update方法(LOB版本)
     * @param sql
     * @param params
     * @return
     * @throws POException
     */
    int updateForLob(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过SQL的select操作
     * @param sql		SQL
     * @param params	参数集合
     * @return			返回一个装载着Map的集合，Map的Key为表列名，Value为此列的值
     * @throws POException
     */
    List<HashMap<String, Object>> select(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过PO的排序select操作
     * @param po		PO对象
     * @param order		排序方式 ASC|DESC
     * @param colNames	排序字段名，可多个
     * @return
     * @throws POException
     */
    List<HashMap<String, Object>> selectForOrder(PO po, String order, String... colNames) throws POException;

    /**
     *
     * Function    : 通过SQL的select操作
     * @param <T>
     * @param sql		SQL
     * @param params	参数集合
     * @param callback	回调函数，将结果集封装到PO或者自定义Bean里
     * @return			装载着PO或自定义Bean的集合
     * @throws POException
     */
    <T> List<T> select(String sql, List<Object> params, DAOCallback<T> callback) throws POException;

    /**
     *
     * Function    : 通过PO的select操作
     * @param <T>
     * @param po		PO对象
     * @return			装载着PO或自定义Bean的集合
     * @throws POException
     */
    <T> List<T> select(PO po) throws POException;

    /**
     *
     * Function    : 通过PO的排序select操作
     * @param <T>
     * @param po		PO对象
     * @param callback	回调函数
     * @param order		排序方式 ASC|DESC
     * @param colNames	排序字段，可多个
     * @return
     * @throws POException
     */
    <T> List<T> selectForOrder(PO po, DAOCallback<T> callback, String order, String... colNames) throws POException;


    /**
     *
     * Function    : 通过主键查询
     * @param <T>
     * @return
     * @throws POException
     */
    <T> T selectById(T t) throws POException;

    /**
     *
     * Function    : 分页查询操作
     * @param <T>
     * @param sql		SQL
     * @param params	参数集合
     * @param callback	回调函数
     * @param pageSize	页面显示记录数
     * @param curPage	当前页
     * @return			装载着PageResult对象的集合
     * @throws POException
     */
    <T> PageResult<T> pageQuery(String sql, List<Object> params, DAOCallback<T> callback, int pageSize, int curPage) throws POException;

    /**
     *
     * Function    : 调用Function
     * @param functionName	Function名称
     * @param ins			输入参数集合
     * @param outType		输出类型
     * @return
     */
    Object callFunction(String functionName, List<Object> ins, int outType);

    /**
     *
     * Function    : 调用Procedure
     * @param procedureName	Procedure名称
     * @param ins			输入参数集合
     * @param outs			输出参数集合
     * @return				输出结果集合
     */
    List<Object> callProcedure(String procedureName, List<Object> ins, List<Integer> outs);

    /**
     *
     * Function    : 特殊的Procedure调用,只返回一个CURSOR
     * @param <T>
     * @param procedureName	Procedure名称
     * @param ins			输入参数集合
     * @param callback		回调函数
     * @return				装载着PO或自定义bean的集合
     */
    <T> List<T> callProcedure(String procedureName, List<Object> ins, DAOCallback<T> callback);

    /**
     *
     * Function    : 获取Long类型序列ID
     * @param sequenceName	sequence名称
     * @return
     */
    Long getLongPK(String sequenceName);

    /**
     *
     * Function    : 获取Integer类型序列ID
     * @param sequenceName	sequence名称
     * @return
     */
    Integer getIntegerPK(String sequenceName);

    /**
     *
     * Function    : 获取String类型序列ID
     * @param sequenceName	sequence名称
     * @return
     */
    String getStringPK(String sequenceName);

    /**
     *
     * Function    : 提交
     */
    void commit() throws POException;

    /**
     *
     * Function    : 回滚
     */
    void rollback() throws POException;

    /**
     *  关闭Session
     * @throws POException
     */
    void close() throws POException;

    /**
     *
     * Function    : 读取BLOB字段
     * @param colName
     * @param sql
     * @param params
     * @return
     */
    byte[] readBlob(String colName, String sql, List<Object> params) throws POException;
```
### 单独使用教程
数据库配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<bury>
	<connections default="ds001">
		<connection id="ds001">
			<!-- 数据源类型: 允许值: direct|c3p0|druid -->
			<property name="datasource" value="druid" />
			<!-- 数据库类型: 允许值:mysql|oracle|mssql -->
			<property name="databaseType" value="mysql" />
			<property name="url" value="jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8" />
			<property name="username" value="root" />
			<property name="password" value="password" />
			<property name="initialPoolSize" value="2" />
			<property name="maxPoolSize" value="4" />
			<property name="minPoolSize" value="2" />
			<property name="autocommit" value="false" />
		</connection>
		<connection id="ds002">
			<!-- 数据源类型: 允许值: direct|c3p0|druid -->
			<property name="datasource" value="c3p0" />
			<!-- 数据库类型: 允许值:mysql|oracle|mssql -->
			<property name="databaseType" value="oracle" />
			<property name="url" value="jdbc:oracle:thin:@localhost:1521:orcl" />
			<property name="username" value="user" />
			<property name="password" value="password" />
			<property name="initialPoolSize" value="5" />
			<property name="maxPoolSize" value="10" />
			<property name="minPoolSize" value="2" />
			<property name="autocommit" value="false" />
		</connection>
	</connections>
</bury>
```
Bury可以支持多数据源连接，支持直接连接或使用连接池连接(c3p0|druid),具体配置信息请看xml，其中`<connections default="ds001">`指定默认连接的数据库

基本操作
```java
// insert操作
	@Test
	public void test()
	{
		try
		{
			String configFile = "bury-config.xml";
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(configFile);
			for (int j = 0 ; j < 10 ; j ++)
			{
				Session session = factory.openSession();		// 默认连接default数据库，在xml中配置
				//Session session = factory.openSession("ds002");		// 可以指定要连接的数据库，在xml中配置
				try
				{
					TestOraPO ora = new TestOraPO();
					ora.setId(session.getIntegerPK("SEQ_TEST_ORA"));
					ora.setName("DS003");
					ora.setBirthday(new Date());
					ora.setZipCode("1300001");
					int count = session.insert(ora);
					session.commit();
				}
				catch (POException e)
				{
					e.printStackTrace();
				}
				finally
				{
					session.close();
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

// 使用id查询
	@Test
	public void test()
	{
		String configFile = "bury-config.xml";
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configFile);
		Session session = factory.openSession();
		try
		{
			TestPO po = new TestPO();
			po.setId(1);
			TestPO value = session.selectById(po);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
// 使用sql
	@Test
	public void test()
	{
		String configFile = "bury-config.xml";
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configFile);
		Session session = factory.openSession();
		try
		{
			StringBuilder sql = new StringBuilder();
            sql.append("select * \n");
            sql.append("from tt_test tr\n");
            sql.append("where tr.id = ?\n");
            sql.append("and tr.name = ?\n");
            Parameters parameters = new Parameters(1, "name");
            return session.select(sql.toString(), parameters.getParams(), new POCallBack(RolePO.class));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

// 分页查询
	@Test
	public void test()
	{
		String configFile = "bury-config.xml";
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configFile);
		Session session = factory.openSession();
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
            sql.append("from tt_test tr\n");
            sql.append("where tr.id = ?\n");
            sql.append("and tr.name = ?\n");
			PageResult<TmUserPO> result = session.pageQuery(sql.toString(), null, new POCallBack(Test.class), 10, 1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
```