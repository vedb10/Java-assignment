package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/sample2";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static Connection getDBConn() throws SQLException {
        try {
            // Load the JDBC driver (this step is optional for Java SE 6 and later versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
    public static Date convertStringToDate(String dateString) throws SQLException {
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new SQLException("Error converting String to Date", e);
        }
    }

    public static Time convertStringToTime(String timeString) throws SQLException {
        try {
            java.util.Date utilTime = new SimpleDateFormat("HH:mm:ss").parse(timeString);
            return new Time(utilTime.getTime());
        } catch (ParseException e) {
            throw new SQLException("Error converting String to Time", e);
        }
    }
    // You might want to include additional methods for handling Date and Time conversions
    // if needed in your application.
}
