package dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Util class for connecting to database with JDBC
 */
public class DaoConnectionManager {
    private static Connection connection = null;

    // all needed information for connection
    private static String DRIVER = "org.mariadb.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/datebook";
    private static String USERNAME = "coi";
    private static String PASSWORD = "coi175";

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                String driver = DRIVER;
                String url = URL;
                String user = USERNAME;
                String password = PASSWORD;
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
