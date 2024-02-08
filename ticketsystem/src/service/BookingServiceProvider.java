package service;

import entity.model.Booking;

public interface BookingServiceProvider {

    // Create
    void createBooking(Booking booking);

    // Read
    Booking getBookingById(int bookingId);

    // Update
    void updateBooking(Booking booking);

    // Delete
    void deleteBooking(int bookingId);
}
