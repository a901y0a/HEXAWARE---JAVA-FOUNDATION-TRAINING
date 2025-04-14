package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    // Method to get database connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // ✅ Fetching values from the properties file
                String url = PropertyUtil.getPropertyString("db.url");
                String user = PropertyUtil.getPropertyString("db.user");
                String password = PropertyUtil.getPropertyString("db.password");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("✅ Database connected successfully!");

            } catch (SQLException e) {
                System.err.println("❌ Failed to connect to the database!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
