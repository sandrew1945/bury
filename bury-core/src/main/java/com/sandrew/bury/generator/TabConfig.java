package com.sandrew.bury.generator;

import com.sandrew.bury.exception.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by summer on 2019/6/4.
 */
public class TabConfig
{
    private String tableName;
    private String poName;
    private Map<String, PKColumn> pkMap = new HashMap<String, PKColumn>();
    private boolean hasIncreasePK = false;

    public List<String> getPk()
    {
        if (null == this.pkMap)
        {
            return null;
        }
        List<String> pks = new ArrayList<String>();

        for (PKColumn pk : this.pkMap.values())
        {
            pks.add(pk.getColName());
        }
        return pks;
    }

    public boolean isAutoIncrease(String pkName)
    {
        if (null == this.pkMap)
        {
            return false;
        }
        return this.pkMap.get(pkName).isIncrease();
    }

    /**
     *  新增主键
     * @param pkName
     * @param isIncrease
     */
    public void addPk(String pkName, boolean isIncrease)
    {
        if (isIncrease == hasIncreasePK)
        {
            throw new ParseException("not only one auto increase pk.");
        }
        PKColumn pc = new PKColumn();
        pc.setColName(pkName);
        pc.setIncrease(isIncrease);
        this.hasIncreasePK = isIncrease;
        this.pkMap.put(pkName, pc);
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getPoName()
    {
        return poName;
    }

    public void setPoName(String poName)
    {
        this.poName = poName;
    }

    class PKColumn
    {
        private String colName;
        private boolean isIncrease;

        public String getColName()
        {
            return colName;
        }

        public void setColName(String colName)
        {
            this.colName = colName;
        }

        public boolean isIncrease()
        {
            return isIncrease;
        }

        public void setIncrease(boolean increase)
        {
            isIncrease = increase;
        }
    }
}
