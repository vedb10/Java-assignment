package exception;

public class InvalidBookingIDException extends Exception {

	public InvalidBookingIDException(String message) {
		super(message);
		System.out.println("Invalid-booking-ID-Exception");
		// BookingDAO line 80
	}
	
	
	@Override
	public String toString() {
		return "Invalid booking exception!";
	}
}
