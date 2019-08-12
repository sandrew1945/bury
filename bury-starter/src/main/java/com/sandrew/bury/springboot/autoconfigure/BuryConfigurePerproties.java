package com.sandrew.bury.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by summer on 2019/8/7.
 */
@ConfigurationProperties("spring.bury")
public class BuryConfigurePerproties
{
    private String databaseType;
    private String datasourceName;

    public String getDatasourceName()
    {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName)
    {
        this.datasourceName = datasourceName;
    }

    public String getDatabaseType()
    {
        return databaseType;
    }

    public void setDatabaseType(String databaseType)
    {
        this.databaseType = databaseType;
    }
}
