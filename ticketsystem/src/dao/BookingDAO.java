package dao;

import entity.model.Booking;
import java.util.List;

public interface BookingDAO {

    // Create
    void createBooking(Booking booking);

    // Read
    Booking getBookingById(int bookingId);
    List<Booking> getAllBookings();

    // Update
    void updateBooking(Booking booking);

    // Delete
    void deleteBooking(int bookingId);
}
