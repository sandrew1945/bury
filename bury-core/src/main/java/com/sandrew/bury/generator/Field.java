/***************************************************************************************************
 * <pre>
* FILE : Field.java
* CLASS : Field
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
* 		  |2010-4-26| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/

package com.sandrew.bury.generator;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-4-26
 * @version    :
 */
public class Field
{
	// 字段名称
	private String fieldName;

	// PO的属性名
	private String attributeName;

	private boolean isPK;
	
	// 是否自增字段
	private boolean autoIncrement;

	// 字段类型
	private Class<?> fieldType;

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public Class<?> getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(Class<?> fieldType)
	{
		this.fieldType = fieldType;
	}

	public String getAttributeName()
	{
		return attributeName;
	}

	public void setAttributeName(String attributeName)
	{
		this.attributeName = attributeName;
	}

	public boolean isPK()
	{
		return isPK;
	}

	public void setPK(boolean PK)
	{
		isPK = PK;
	}

	public boolean isAutoIncrement()
	{
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement)
	{
		this.autoIncrement = autoIncrement;
	}

	/**
	 * 
	 * Function    : 生成属性String
	 * LastUpdate  : 2010-4-30
	 * @return
	 */
	public String toAttributeStr()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("\t");
		sb.append("private").append(" ");
		sb.append(fieldType.getSimpleName()).append(" ");
		sb.append(attributeName).append(";");
		return sb.toString();
	}

	/**
	 * 
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(fieldName).append(":").append(fieldType.getSimpleName());
		return sb.toString();
	}
}
