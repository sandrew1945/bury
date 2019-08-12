package com.sandrew.bury;

import com.sandrew.bury.configuration.ConfigParser;
import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.configuration.XMLConfigParser;
import com.sandrew.bury.datasource.DataSourceWrapper;
import com.sandrew.bury.util.BuryConstants;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DataSourceTest
{

	@Test
	public void test()
	{
		try
		{
			String configFile = "bury-config.xml";
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
			ConfigParser parser = new XMLConfigParser();
			Configuration configuration = parser.parse(is);
			DataSourceWrapper wrapper = configuration.getDataSource(BuryConstants.DEFAULT_DATASOURCE_KEY);
			Connection conn = wrapper.getConnection();
			System.out.println(conn);
			PreparedStatement stat = conn.prepareStatement("INSERT INTO tt_session (session_id, session, create_by, create_date) VALUES ('qoweiqweuyqwemqweni', '111123', -1, SYSDATE())", Statement.RETURN_GENERATED_KEYS);
			int i = stat.executeUpdate();
			System.out.println("----------------" + i);
			//conn.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
