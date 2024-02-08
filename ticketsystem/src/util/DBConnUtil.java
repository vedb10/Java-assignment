package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    // JDBC database URL
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sample2";
    
    // JDBC database username
    private static final String JDBC_USER = "root";
    
    // JDBC database password
    private static final String JDBC_PASSWORD = "admin";

    // Establish a connection to the database
    public static Connection getDBConn() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Failed to establish a database connection.", e);
        }
    }
}
