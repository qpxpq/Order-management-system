package cn.byau.homework.utils;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
public class JDBCUtils {
  public static DataSource ds = null;
	static {
		// 新建一个配置文件对象
		Properties prop = new Properties();
		try {
			// 通过类加载器找到文件路径，读配置文件
			InputStream in = new JDBCUtils().getClass().getClassLoader()
					.getResourceAsStream("jdbc.properties");
			// 把文件以输入流的形式加载到配置对象中
			prop.load(in);
			// 创建数据源对象
			ds = DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
  public static DataSource getDataSource() {
	  return ds;
  }
  
  public static Connection getConnection() throws SQLException {
	  return ds.getConnection();
  }
}
