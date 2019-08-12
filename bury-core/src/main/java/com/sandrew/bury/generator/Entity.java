package com.sandrew.bury.generator;

/**
 * Created by summer on 2019/6/5.
 */
public class Entity
{
    private String type;
    private String colName;
    private String poName;
    private boolean pk;
    private boolean autoIncrement;

    public Entity(String type, String colName, String poName, boolean pk, boolean autoIncrement)
    {
        this.type = type;
        this.colName = colName;
        this.poName = poName;
        this.pk = pk;
        this.autoIncrement = autoIncrement;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getColName()
    {
        return colName;
    }

    public void setColName(String colName)
    {
        this.colName = colName;
    }

    public String getPoName()
    {
        return poName;
    }

    public void setPoName(String poName)
    {
        this.poName = poName;
    }

    public boolean isPk()
    {
        return pk;
    }

    public void setPk(boolean pk)
    {
        this.pk = pk;
    }

    public boolean isAutoIncrement()
    {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement)
    {
        this.autoIncrement = autoIncrement;
    }
}
