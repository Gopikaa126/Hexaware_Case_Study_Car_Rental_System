package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabasePropertyUtil {
	
    public static String getPropertyString(String propertyFileName) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(propertyFileName)) {
            properties.load(input);
            return properties.getProperty("db.url");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}


