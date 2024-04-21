package dealdrop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDataBase {
    private final String URL = "jdbc:mysql://localhost:3306/dealdrop";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static MyDataBase instance;

    private static final Logger LOGGER = Logger.getLogger(MyDataBase.class.getName());

    private MyDataBase() {
        try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.log(Level.INFO, "Connection successful!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection failed: ", e);
        }
    }

    public static MyDataBase getInstance() {
        if (instance == null) {
            instance = new MyDataBase();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Test method to check database connection using Logger
    public static void testConnection() {
        try {
            Connection conn = MyDataBase.getInstance().getConnection();
            if (conn != null) {
                LOGGER.log(Level.INFO, "Successfully connected to the database!");
            } else {
                LOGGER.log(Level.WARNING, "Failed to connect to the database.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error testing database connection", e);
        }
    }
}
