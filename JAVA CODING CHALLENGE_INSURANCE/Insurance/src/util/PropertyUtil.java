package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("❌ Failed to load config.properties");
            e.printStackTrace();
        }
    }

    public static String getPropertyString(String key) {
        return properties.getProperty(key);
    }
}
