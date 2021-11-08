/***************************************************************************************************
 * <pre>
* FILE : SqlCreator.java
* CLASS : SqlCreator
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
* 		  |2010-8-30| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/
/**
 * $Id: SqlCreator.java,v 0.1 2010-8-30 ����05:30:34 SuMMeR Exp $
 */

package com.sandrew.bury.sql;


import com.sandrew.bury.bean.PO;
import com.sandrew.bury.common.POMapping;

import java.util.List;

/**
 * Function    : SQL生成接口
 * @author     : SuMMeR
 * CreateDate  : 2010-8-30
 * @version    :
 */
public interface SqlCreator
{
	String selectCreator(POMapping mapping, PO po);

	String updateCreator(POMapping mapping, PO cond, PO value);

	String deleteCreator(POMapping mapping, PO po);

	String insertCreator(POMapping mapping, PO po);

	String insertAllCreator(POMapping mapping, PO po);

	String selectCreatorForOrder(POMapping mapping, PO po, String order, String... colName);

	String getProdOrFuncSql(String functionName, List<Object> ins, List<Integer> outs, boolean isProcedure);
}
