package utily;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
	public static Connection getConnection(String dbPropertiesFile) {
		String connectionString = DBPropertyUtil.getConnectionString(dbPropertiesFile);
		String[] connectionInfo = connectionString.split(",");
		String url = connectionInfo[0];
		String username = connectionInfo[1];
		String password = connectionInfo[2];
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}