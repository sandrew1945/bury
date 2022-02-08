/***************************************************************************************************
 * <pre>
* FILE : POCallBack.java
* CLASS : POCallBack
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
* 		  |2010-9-1| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/
/**
 * $Id: POCallBack.java,v 0.1 2010-9-1 ����04:41:24 SuMMeR Exp $
 */

package com.sandrew.bury.callback;


import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.Pack;
import com.sandrew.bury.common.POMapping;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.util.POUtil;
import org.apache.commons.collections.map.CaseInsensitiveMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-9-1
 * @version    :
 */
public class POCallBack<T extends PO> implements DAOCallback<T>
{
	private Class<T> clz = null;

	public POCallBack(Class<T> clz)
	{
		this.clz = clz;
	}

	/* (non-Javadoc)
	 * @see com.autosys.po3.callback.DAOCallback#wrapper(java.sql.ResultSet, int)
	 */
	public T wrapper(ResultSet rs, int index)
	{
		try
		{
			T po = this.clz.newInstance();
			// 先将ResultSet转成Map再进行绑定
			Map<String, Object> resultMap = getResultMap(rs, po);
			POMapping mapping = new POMapping(po);
			for (int i = 0; i < mapping.getColSize(); i++)
			{
				Object value = resultMap.get(mapping.getColName(i));
				if (null != value)
				{
					/**
					 *   TODO Oracle将全部NUMBER自动映射为BigDecimal,暂时处理一下
					 *   如果model类型为Integer,ResultSet中类型为BigDecimal,那么转为Integer
					 */
					value = handleOracleNumber(mapping, i, value);
					POUtil.invokeSetMethodByField(po, mapping.getPropertyName(i), mapping.getColType(i), value);
				}
			}
			return po;
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
			throw new POException("POCallBack 调用失败", e);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
			throw new POException("POCallBack 调用失败", e);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new POException("POCallBack 调用失败", e);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new POException("POCallBack 调用失败", e);
		}

	}

	/**
	 *   Oracle将全部NUMBER自动映射为BigDecimal,暂时处理一下
	 *   如果model类型为Integer,ResultSet中类型为BigDecimal,那么转为Integer
	 * @param mapping
	 * @param i
	 * @param value
     * @return
     */
	private Object handleOracleNumber(POMapping mapping, int i, Object value) throws SQLException, IOException
	{
		if (value instanceof BigDecimal && mapping.getColType(i).equals(Integer.class))
        {
            value = new Integer(((BigDecimal) value).intValue());
        }
		else if (value instanceof Blob && mapping.getColType(i).equals(byte[].class))
		{
			InputStream is = ((Blob) value).getBinaryStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = is.read(buffer))) {
				output.write(buffer, 0, n);
			}
			value = output.toByteArray();
		}
		return value;
	}

	/**
	 * 
	 * Function    : 将ResultSet转成Map
	 * LastUpdate  : 2016年12月7日
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Map<String, Object> getResultMap(ResultSet rs, T po) throws SQLException
	{
		Field[] poFields = po.getClass().getDeclaredFields();
		// 使用CaseInsensitiveMap忽略key的大小写，避免因数据库不同而区分大小写，引起的无法取到值的问题
		Map<String, Object> resultMap = new CaseInsensitiveMap();
		ResultSetMetaData meta = rs.getMetaData();
		Arrays.stream(poFields).forEach(field -> {
			try
			{
				Class fieldClz = null;
				if (field.getType().equals(Pack.class))
				{
					// 兼容2.x版本
					String fieldType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName();
					if (fieldType.endsWith("[]"))
					{
						// 单独处理大对象的情况
						if (fieldType.equals("byte[]"))
						{
							fieldClz = Class.forName("[B");
						}
						else
						{
							throw new RuntimeException("不支持的原始数据类型:" + fieldType);
						}
					}
					else
					{
						fieldClz = Class.forName(fieldType);
					}
//					System.out.println(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName() + "    " + field.getName() + "    " + fieldType);

				}
				else
				{
					fieldClz = field.getType();
				}

				ColumnName columnName = field.getAnnotation(ColumnName.class);
				String fieldName = field.getName();
				String colName = null;
				if (null != columnName && !"".equals(columnName.value()))
				{
					// 通过注解获取表列名
					colName = columnName.value();
				}
				else
				{
					// 根据属性字段名字解析表列名
					colName = POUtil.getColNameByFieldName(fieldName);
				}
				// 判断列名是否存在
				try
				{
					rs.findColumn(colName);
				}
				catch (SQLException e)
				{
					return;
				}
				if (fieldClz.equals(Long.class))
				{
					Object value = rs.getLong(colName);
					resultMap.put(colName, value);
				}
				else if (fieldClz.equals(String.class))
				{
					Object value = rs.getString(colName);
					resultMap.put(colName, value);
				}
				else if (fieldClz.equals(BigDecimal.class))
				{
					Object value = rs.getBigDecimal(colName);
					resultMap.put(colName, value);
				}
				else if (fieldClz.equals(Integer.class))
				{
					Object value = rs.getInt(colName);
					resultMap.put(colName, value);
				}
				else if (fieldClz.equals(Date.class))
				{
					Object value = rs.getDate(colName);
					resultMap.put(colName, value);
				}
				else if (fieldClz.equals(Double.class))
				{
					Object value = rs.getDouble(colName);
					resultMap.put(colName, value);
				}
				else if (fieldClz.equals(Float.class))
				{
					Object value = rs.getFloat(colName);
					resultMap.put(colName, value);
				}
				else
				{
					Object value = rs.getObject(colName);
					resultMap.put(colName, value);
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (SQLException err)
			{
				err.printStackTrace();
			}

		});
//		for (int i = 1; i <= meta.getColumnCount(); i++)
//		{
//			String key = meta.getColumnLabel(i);
//			Object value = rs.getObject(key);
//			resultMap.put(key, value);
//		}
		return resultMap;
	}

}
