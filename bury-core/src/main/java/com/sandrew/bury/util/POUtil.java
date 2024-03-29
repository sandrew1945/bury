/***************************************************************************************************
 * <pre>
* FILE : InterfaceUtil.java
* CLASS : InterfaceUtil
*
* AUTHOR : SuMMeR
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2010-3-19| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/
/**
 * $Id: InterfaceUtil.java,v 0.1 2010-3-19
 */

package com.sandrew.bury.util;


import com.sandrew.bury.bean.AbstractIntervalPack;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.Pack;
import com.sandrew.bury.common.POMapping;
import com.sandrew.bury.exception.POException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-3-19
 * @version    :
 */
public class POUtil
{

	/**
	 * 
	 * Function    : 根据字段名称生成JavaBean的getXxx方法
	 * LastUpdate  : 2010-4-20
	 * @param fieldName
	 * @return
	 */
	public static String getMethodOfGetByFieldName(final String fieldName)
	{
		return getBeanMethodByFieldName(fieldName, "get");
	}

	/**
	 * 
	 * Function    : 根据字段名称生成JavaBean的setXxx方法
	 * LastUpdate  : 2010-4-20
	 * @param fieldName
	 * @return
	 */
	public static String getMethodOfSetByFieldName(final String fieldName)
	{
		return getBeanMethodByFieldName(fieldName, "set");
	}

	/**
	 * 
	 * Function    : 根据字段名称生成JavaBean的setXxx或getXxx方法,私有方法,供getMethodOfGetByFieldName和getMethodOfSetByFieldName调用
	 * LastUpdate  : 2010-4-20
	 * @param fieldName
	 * @param methodPrefix
	 * @return
	 */
	private static String getBeanMethodByFieldName(final String fieldName, String methodPrefix)
	{
		String dealFieldName = toUpCaseOfInitial(fieldName);
		return methodPrefix + dealFieldName;
	}

	/**
	 * 
	 * Function    : 首字母大写(可能返回null,注意处理)
	 * LastUpdate  : 2010-4-20
	 * @param str
	 * @return
	 */
	public static String toUpCaseOfInitial(String str)
	{
		String retStr = null;
		if (null != str && !str.equals(""))
		{
			String initial = str.substring(0, 1);
			initial = initial.toUpperCase();
			retStr = initial + str.substring(1, str.length());
		}
		return retStr;
	}

	/**
	 * 
	 * Function    : 根据PO类名(不包含包名)解析表名
	 * Example	   : TmVehiclePO 解析后为 tm_vehicle
	 * LastUpdate  : 2010-8-30
	 * @param poName
	 * @return
	 */
	public static String getTabNameByPOName(String poName)
	{
		// 先去掉PO后缀
		if (poName.contains(BuryConstants.PO_SUFFIX))
		{
			poName = poName.substring(0, poName.length() - BuryConstants.PO_SUFFIX.length());
		}
		// 遍历poName, 如果遇到大写字母前加"_"
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < poName.length(); i++)
		{
			char c = poName.charAt(i);
			if (c >= 'A' && c <= 'Z' && i != 0)
			{
				sB.append('_');
			}
			sB.append(Character.toLowerCase(c));
		}
		return sB.toString();
	}

	/**
	 * 
	 * Function    : 根据fieldName解析列名
	 * LastUpdate  : 2010-8-30
	 * @param fieldName
	 * @return
	 */
	public static String getColNameByFieldName(String fieldName)
	{
		// 遍历fieldName, 如果遇到大写字母前加"_"
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < fieldName.length(); i++)
		{
			char c = fieldName.charAt(i);
			if (c >= 'A' && c <= 'Z' && i != 0)
			{
				sB.append('_');
			}
			sB.append(Character.toUpperCase(c));
		}
		return sB.toString();
	}

	/**
	 * 
	 * Function    : 根据表列名获取PO字段属性名
	 * LastUpdate  : 2010-9-1
	 * @param fieldName
	 * @return
	 */
	public static String getAttributeNameByFieldName(String fieldName)
	{
		// 将字段名变为小写
		fieldName = fieldName.toLowerCase();
		// 使用"_"分割,然后首字母大写
		String[] parts = fieldName.split("_");
		StringBuilder attribute = new StringBuilder();
		for (int i = 0; i < parts.length; i++)
		{
			// 属性名首字母小写
			String temp = null;
			if (i != 0)
			{
				temp = POUtil.toUpCaseOfInitial(parts[i]);
			}
			else
			{
				temp = parts[i];
			}
			attribute.append(temp);
		}
		return attribute.toString();
	}

	/**
	 * 
	 * Function    : 通过PO的属性调用getXXX方法
	 * LastUpdate  : 2010-8-20
	 * @param fieldName
	 * @return
	 */
	public static Object invokeGetMethodByField(PO po, String fieldName)
	{
		Object value = null;
		Class<?> cls = po.getClass();
		String methodName = getMethodOfGetByFieldName(fieldName);
		Method meth = null;
		try
		{
			meth = cls.getMethod(methodName, null);
			Class<?> retType = meth.getReturnType();
			Object[] arglist = new Object[0];
			value = meth.invoke(po, arglist);
		}
		catch (Exception e)
		{
			throw new RuntimeException("调用" + po.getClass().getSimpleName() + meth.getName() + "方法失败", e);
		}
		return value;
	}

	/**
	 *  通过反射获取PO中字段的值
	 * @param po
	 * @param fieldName
	 * @return
	 */
	public static Object reflectGetByField(PO po, String fieldName)
	{
		try
		{
			Field field = po.getClass().getDeclaredField(fieldName);
			//设置对象的访问权限，保证对private的属性的访问
			field.setAccessible(true);
			return field.get(po);
		}
		catch (NoSuchFieldException e)
		{
			throw new RuntimeException("获取" + po.getClass().getSimpleName() + "字段" + fieldName + "的值失败", e);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException("获取" + po.getClass().getSimpleName() + "字段" + fieldName + "的值失败", e);
		}

	}


	/**
	 *
	 * Function    : 通过PO的属性调用SetXXX方法
	 * LastUpdate  : 2010-8-20
	 * @param fieldName
	 * @return
	 */
	public static Object invokeSetMethodByField(PO po, String fieldName, Class fieldClass, Object setValue)
	{
		Object value = null;
		Class<?> cls = po.getClass();
		String methodName = getMethodOfSetByFieldName(fieldName);
		Method meth = null;
		try
		{
			if (null == fieldClass)
			{
				fieldClass = cls.getDeclaredField(fieldName).getType();
			}
//			meth = cls.getMethod(methodName, fieldClass);

			if (fieldClass.equals(Pack.class))
			{
				/*
				Field field = cls.getDeclaredField(fieldName);
				String originTypeName = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName();
				Class paramType = null;
				// TODO not graceful :(
				if (originTypeName.endsWith("[]"))
				{
					// 单独处理大对象的情况
					if (originTypeName.equals("byte[]"))
					{
						paramType = Class.forName("[B");
					}
					else
					{
						throw new RuntimeException("调用" + po.getClass().getSimpleName() + "." + meth.getName() + "方法失败，不支持的原始数据类型:" + originTypeName);
					}
				}
				else
				{
					paramType = Class.forName(originTypeName);
				}
				*/
				// release.2 支持PO字段为Pack类型字段,获取Pack泛型类型
				// 放弃根据泛行获取实际类型的方式，根据参数来判断类型
				Class paramType = setValue.getClass();
				if (paramType.equals(Timestamp.class) || paramType.equals(java.sql.Date.class))
				{
					// 特殊处理时间类型
					paramType = Date.class;
				}
				meth = cls.getMethod(methodName, paramType);
			}
			else
			{
				// 支持老版本PO字段为真实类型
				//meth = cls.getMethod(methodName, cls.getDeclaredField(fieldName).getType());
				meth = cls.getMethod(methodName, fieldClass);
			}

			Object[] arglist = new Object[] { setValue };
			value = meth.invoke(po, arglist);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("调用" + po.getClass().getSimpleName() + "." + meth + "方法失败, 参数:" + setValue.getClass() + "[" + setValue + "]", e);
		}
		return value;
	}

	/**
	 * 
	 * Function    : 通过PO属性是否有值来生成SQL参数
	 * LastUpdate  : 2010-9-1
	 * @param mapping
	 * @param po
	 * @return
	 */
	public static LinkedList<Object> encapParams(POMapping mapping, PO... po)
	{
		// 封装参数List
		LinkedList<Object> params = new LinkedList<Object>();
		for (int j = 0; j < po.length; j++)
		{
			for (int i = 0; i < mapping.getColSize(); i++)
			{
				// 如果PO此属性不为null，则添加AND添件
				// 获取此属性字段的get方法
				Object value = POUtil.reflectGetByField(po[j], mapping.getPropertyName(i));
				if (value instanceof Pack)
				{
					Pack pack = (Pack) value;
					// 特殊处理AbstractIntervalPack
					if (value instanceof AbstractIntervalPack)
					{
						AbstractIntervalPack abstractIntervalPack = (AbstractIntervalPack) pack;
						params.add(abstractIntervalPack.getValue());
						params.add(abstractIntervalPack.getMax());
					}
					else if (null != pack)
					{
						params.add(pack.getValue());
					}
				}
				else
				{
					// 兼容1.0版本
					if (null != value)
					{
						params.add(value);
					}
				}

			}
		}
		return params;
	}

	/**
	 *
	 * Function    : 通过PO属性是来生成SQL参数(不论是否有值)
	 * LastUpdate  : 2010-9-1
	 * @param mapping
	 * @param po
	 * @return
	 */
	public static LinkedList<Object> encapAllParams(POMapping mapping, PO... po)
	{
		// 封装参数List
		LinkedList<Object> params = new LinkedList<Object>();
		for (int j = 0; j < po.length; j++)
		{
			for (int i = 0; i < mapping.getColSize(); i++)
			{
				// 如果PO此属性不为null，则添加AND添件
				// 获取此属性字段的get方法
				Object value = POUtil.reflectGetByField(po[j], mapping.getPropertyName(i));
				if (value instanceof Pack)
				{
					Pack pack = (Pack) value;
					if (null != pack)
					{
						params.add(pack.getValue());
					}
					else
					{
						params.add(null);
					}
				}
				else
				{
					// 兼容1.0版本
					if (null != value)
					{
						params.add(value);
					}
					else
					{
						params.add(null);
					}
				}

			}
		}
		return params;
	}

	/**
	 * 
	 * Function    :
	 * LastUpdate  : 2010-9-2
	 * @param rs
	 * @param colName
	 * @param targetType
	 * @return
	 */
	public static Object getValue(ResultSet rs, String colName, Class targetType)
	{
		try
		{
			if (String.class.equals(targetType))
			{
				return rs.getString(colName);
			}
			else if (Integer.class.equals(targetType))
			{
				return rs.getInt(colName);
			}
			else if (Long.class.equals(targetType))
			{
				return rs.getLong(colName);
			}
			else if (java.sql.Blob.class.equals(targetType))
			{
				// update 2011-12-14
				// return null;
				return rs.getBlob(colName);
			}
			else if (java.sql.Clob.class.equals(targetType))
			{
				// update 2011-12-14
				// return null;
				return rs.getClob(colName);
			}
			else if (java.util.Date.class.equals(targetType))
			{
				Timestamp t = rs.getTimestamp(colName);
				return t != null ? new java.util.Date(t.getTime()) : null;
			}
			else if (Float.class.equals(targetType))
			{
				return rs.getFloat(colName);
			}
			else if (Double.class.equals(targetType))
			{
				return rs.getDouble(colName);
			}
			else if (java.math.BigInteger.class.equals(targetType))
			{
				BigDecimal b = rs.getBigDecimal(colName);
				return b != null ? b.toBigInteger() : null;
			}
			else if (BigDecimal.class.equals(targetType))
			{
				return rs.getBigDecimal(colName);
			}
			else if (Boolean.class.equals(targetType))
			{
				int bint = rs.getInt(colName);
				/**
				 * 0 == true; 其他false;
				 */
				return bint == 0 ? true : false;
			}
			else
			{
				throw new POException("not supported type:" + targetType);
			}
		}
		catch (SQLException e)
		{
			throw new POException("get " + colName + " error", e);
		}
		catch (Exception e)
		{
			throw new POException(e);
		}
	}

	public static String getDriverClass(String databaseType)
	{
		switch (databaseType)
		{
			case BuryConstants.DATABASE_TPYE_ORACLE:
			{
				return "oracle.jdbc.driver.OracleDriver";
			}
			case BuryConstants.DATABASE_TPYE_MYSQL:
			{
				return "com.mysql.jdbc.Driver";
			}
			default:
			{
				return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			}
		}
	}

	public static void main(String[] args)
	{
		try
		{
			Class clz = Class.forName("java.lang.Byte[]");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
