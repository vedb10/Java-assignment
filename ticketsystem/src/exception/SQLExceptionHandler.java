package exception;



import java.sql.SQLException;

public class SQLExceptionHandler {

    public static void handleSQLException(SQLException e) {
        System.out.println("SQL Exception occurred:");

       
        e.printStackTrace();
    }
    
    public  static void eventNotFoundException(SQLException e) {
		System.out.println("InvalidBookingIDException");
		
		e.printStackTrace();
	}
    
    public void invalidBookingIDException(SQLException e) {
		System.out.println("InvalidBookingIDException");
		
		e.printStackTrace();
	}
    
    
    
    
}

