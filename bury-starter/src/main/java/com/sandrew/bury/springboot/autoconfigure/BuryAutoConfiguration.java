package com.sandrew.bury.springboot.autoconfigure;

import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.datasource.DataSourceWrapper;
import com.sandrew.bury.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by summer on 2019/8/7.
 */
@Configuration
@ConditionalOnClass({ SqlSessionFactoryBean.class })
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(BuryConfigurePerproties.class)
@AutoConfigureAfter({ DataSourceAutoConfiguration.class})
public class BuryAutoConfiguration
{
    private BuryConfigurePerproties configurePerproties;

    public BuryAutoConfiguration(BuryConfigurePerproties configurePerproties)
    {
        this.configurePerproties = configurePerproties;
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        Map<String, DataSourceWrapper> dataSources = new HashMap<String, DataSourceWrapper>();
        DataSourceWrapper wrapper = new DataSourceWrapper();
        wrapper.setDefault();
        wrapper.setDatabaseType(this.configurePerproties.getDatabaseType());
        wrapper.setDataSourceName(this.configurePerproties.getDatasourceName());
        wrapper.setDataSource(dataSource);
        wrapper.setAutocommit(false);
        dataSources.put("default", wrapper);
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSources(dataSources);
        return factory.getObject();
    }
}
