package service;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.CustomerDAOImpl;
import dao.EventDAOImpl;
import entity.model.Booking;

public class BookingServiceProviderImpl implements BookingServiceProvider {

    private final BookingDAO bookingDAO;

    public BookingServiceProviderImpl() {
        // Provide the required dependencies when creating BookingDAOImpl
        this.bookingDAO = new BookingDAOImpl(new EventDAOImpl(), new CustomerDAOImpl());
    }

    @Override
    public void createBooking(Booking booking) {
        bookingDAO.createBooking(booking);
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingDAO.updateBooking(booking);
    }

    @Override
    public void deleteBooking(int bookingId) {
        bookingDAO.deleteBooking(bookingId);
    }
}
