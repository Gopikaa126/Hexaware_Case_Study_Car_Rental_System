package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionUtil {

    public static Connection getConnection() throws SQLException {
        Connection connection = null; 

        try (InputStream input = DatabaseConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new SQLException("Sorry, unable to find db.properties");
            }

            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new SQLException("Error in connecting to the database: " + e.getMessage(), e);
        }
        
        return connection; 
    }
}

