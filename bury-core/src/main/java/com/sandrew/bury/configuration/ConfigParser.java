package com.sandrew.bury.configuration;

import java.io.InputStream;

/**
 * Created by summer on 2019/5/13.
 */
public interface ConfigParser
{
    /**
     *  解析配置文件
     * @param is
     */
    Configuration parse(InputStream is);
}
