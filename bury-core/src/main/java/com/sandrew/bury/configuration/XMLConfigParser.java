package com.sandrew.bury.configuration;

import com.sandrew.bury.datasource.DataSourceFactory;
import com.sandrew.bury.datasource.DataSourceWrapper;
import com.sandrew.bury.util.BuryConstants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by summer on 2019/5/13.
 */
public class XMLConfigParser implements ConfigParser
{
    final static Logger logger = LoggerFactory.getLogger(XMLConfigParser.class);

    public Configuration parse(InputStream is)
    {

        try
        {
            SAXReader reader = new SAXReader();
            Document document = reader.read(is);
            return parseConfig(document);
        }
        catch (DocumentException e)
        {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("解析配置文件失败", e);
        }
        finally
        {
            if (null != is)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }

    /**
     *  解析配置文件
     * @param document
     * @return
     */
    private Configuration parseConfig(Document document) throws RuntimeException
    {
        try
        {

            Element root = document.getRootElement();
            // 解析事务管理器
            //String tansactionManager = parseTransactionManager(root);
            // 解析数据库连接
            List<ConfigConnection> connections = parseConnections(root);
            String defaultConnection = parseDefaultConnection(root);
            logger.debug("default datasource : " + defaultConnection);
            // 创建Configuration
            Configuration configuration = new Configuration();
            //configuration.setTransactionManager(tansactionManager);
            for (ConfigConnection connection : connections)
            {
                DataSourceWrapper dsw = DataSourceFactory.newDataSourceWrapper(connection);
                if (defaultConnection.equalsIgnoreCase(connection.getId()))
                {
                    configuration.addDataSource(BuryConstants.DEFAULT_DATASOURCE_KEY, dsw);
                }
                else
                {
                    configuration.addDataSource(connection.getId(), dsw);
                }
            }

            return configuration;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("解析XML失败", e);
        }
    }

    /**
     *  解析事务管理器
     * @param root
     * @return
     */
    private String parseTransactionManager(Element root)
    {

        Element settingsRoot = root.element("settings");
        List<Element> settingList = settingsRoot.elements();
        String transactionManager = null;
        for (Element element : settingList)
        {
            logger.debug("name:" + element.attribute("name").getText() + "   value:" + element.attribute("value").getText());
            if ("transactionManager".equals(element.attribute("name").getText()))
            {
                transactionManager = element.attribute("value").getText();
            }
        }
        return transactionManager;
    }

    /**
     *  解析数据源
     * @param root
     * @return
     */
    private List<ConfigConnection> parseConnections(Element root)
    {
        List<ConfigConnection> connections = new ArrayList<ConfigConnection>();
        Element connectionRoot = root.element("connections");
        List<Element> connectionList = connectionRoot.elements();
        Map<String, String> tempMap = new HashMap<String, String>();
        ConfigConnection connection = null;
        for (Element element : connectionList)
        {
            String id = element.attribute("id").getText();
            if (null == id || "".equals(id))
            {
                throw new RuntimeException("解析失败, connection的id不id允许为空");
            }
            List<Element> properties = element.elements();
            for (Element property : properties)
            {
                tempMap.put(property.attribute("name").getText(), property.attribute("value").getText());
            }
            connection = new ConfigConnection();
            connection.setId(id);
            connection.setDatabaseType(tempMap.get("databaseType"));
            connection.setDatasource(tempMap.get("datasource"));
            connection.setUrl(tempMap.get("url"));
            connection.setUsername(tempMap.get("username"));
            connection.setPassword(tempMap.get("password"));
            connection.setInitialPoolSize(Integer.valueOf(tempMap.get("initialPoolSize")));
            connection.setMaxPoolSize(Integer.valueOf(tempMap.get("maxPoolSize")));
            connection.setMinPoolSize(Integer.valueOf(tempMap.get("minPoolSize")));
            connection.setAutocommit(Boolean.valueOf(tempMap.get("autocommit")));
            connections.add(connection);
        }
        return connections;
    }

    /**
     *  解析默认连接
     * @param root
     * @return
     */
    private String parseDefaultConnection(Element root)
    {
        Element connectionRoot = root.element("connections");
        String defaultDS = connectionRoot.attribute("default").getText();
        return defaultDS;
    }
}
