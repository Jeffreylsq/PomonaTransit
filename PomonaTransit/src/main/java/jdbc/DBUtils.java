package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;



/**
 * Create instance of Connection class by using getConnection()
 * method
 * @author Tianyu Wei
 *
 */
public class DBUtils {
	private static BasicDataSource datasource;
	static {
		Properties prop = new Properties();
		//load in the JDBC information from jdbc.properties
		InputStream ips = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		
		try {
			prop.load(ips);
			//load information
			String driverClassName = prop.getProperty("driver");
	    	String url = prop.getProperty("url");
	    	String username = prop.getProperty("username");
	    	String password = prop.getProperty("password");
	    	
	    	//instance of BasicDataSource
	    	datasource = new BasicDataSource();
	    	datasource.setDriverClassName(driverClassName);
	    	datasource.setUrl(url);
	    	datasource.setUsername(username);
	    	datasource.setPassword(password);
	    	
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
	}
	/**
	 * Singleton design to get connection
	 * @return
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Connection conn = datasource.getConnection();
		return conn;
	}
}
