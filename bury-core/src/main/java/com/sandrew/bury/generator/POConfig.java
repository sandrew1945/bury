package com.sandrew.bury.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer on 2019/6/4.
 */
public class POConfig
{
    private String dbType;                  // 数据库类型
    private String dbUrl;                   // 数据库URL
    private String dbUser;                  // 用户名
    private String dbPassword;              // 密码
    private String genPoPath;               // 生成绝对路径
    private String packageName;             // 包名
    private List<TabConfig> tabConfigList;  // 生成的表


    public String getDbType()
    {
        return dbType;
    }

    public void setDbType(String dbType)
    {
        this.dbType = dbType;
    }

    public String getDbUrl()
    {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl)
    {
        this.dbUrl = dbUrl;
    }

    public String getDbUser()
    {
        return dbUser;
    }

    public void setDbUser(String dbUser)
    {
        this.dbUser = dbUser;
    }

    public String getDbPassword()
    {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword)
    {
        this.dbPassword = dbPassword;
    }

    public String getGenPoPath()
    {
        return genPoPath;
    }

    public void setGenPoPath(String genPoPath)
    {
        this.genPoPath = genPoPath;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public List<TabConfig> getTabConfigList()
    {
        return tabConfigList;
    }

    public void setTabConfigList(List<TabConfig> tabConfigList)
    {
        this.tabConfigList = tabConfigList;
    }

    public void addTable(TabConfig table)
    {
        if (null == tabConfigList)
        {
            tabConfigList = new ArrayList<TabConfig>();
        }
        tabConfigList.add(table);
    }
}
