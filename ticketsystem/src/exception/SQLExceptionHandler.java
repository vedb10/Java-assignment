package exception;



import java.sql.SQLException;

public class SQLExceptionHandler {

    public static void handleSQLException(SQLException e) {
        System.out.println("SQL Exception occurred:");

        // Handle or log the exception based on your application's error-handling strategy
        e.printStackTrace();
    }
}

