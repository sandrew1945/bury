package com.sandrew.bury.configuration;

/**
 * Created by summer on 2019/5/15.
 */
public class ConfigConnection
{
    private String id;
    private String datasource;
    private String databaseType;
    private String url;
    private String username;
    private String password;
    private Integer initialPoolSize;
    private Integer maxPoolSize;
    private Integer minPoolSize;
    private boolean autocommit;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDatasource()
    {
        return datasource;
    }

    public void setDatasource(String datasource)
    {
        this.datasource = datasource;
    }

    public String getDatabaseType()
    {
        return databaseType;
    }

    public void setDatabaseType(String databaseType)
    {
        this.databaseType = databaseType;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getInitialPoolSize()
    {
        return initialPoolSize;
    }

    public void setInitialPoolSize(Integer initialPoolSize)
    {
        this.initialPoolSize = initialPoolSize;
    }

    public Integer getMaxPoolSize()
    {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize)
    {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getMinPoolSize()
    {
        return minPoolSize;
    }

    public void setMinPoolSize(Integer minPoolSize)
    {
        this.minPoolSize = minPoolSize;
    }

    public boolean isAutocommit()
    {
        return autocommit;
    }

    public void setAutocommit(boolean autocommit)
    {
        this.autocommit = autocommit;
    }
}
