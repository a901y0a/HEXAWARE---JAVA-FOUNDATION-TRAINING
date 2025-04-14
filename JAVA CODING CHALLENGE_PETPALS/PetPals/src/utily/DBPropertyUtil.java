package utily;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String filename) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filename)) {
            properties.load(input);
            System.out.println("Property read is "+properties.getProperty("db.url")+properties.getProperty("db.username"));
            return properties.getProperty("db.url")+","+properties.getProperty("db.username")+","+properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
