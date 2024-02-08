package service;

import entity.model.Booking;
import entity.model.Customer;
import entity.model.Event;

import java.sql.SQLException;

public interface IBookingSystemRepository {

    Event createEvent(String event_name, String date, String time, int total_seats, float ticket_price,
                      String event_type, String venue) throws SQLException;

    Event getEventByName(String event_name) throws SQLException;

    void updateEvent(Event event) throws SQLException;

    void deleteEvent(String event_name) throws SQLException;

    void createBooking(Customer[] customers, Event event, int num_tickets, float total_cost) throws SQLException;

    void updateBooking(Booking booking) throws SQLException;

    Booking getBookingById(int booking_id) throws SQLException;
    
    void bookTickets(String event_name, int num_tickets, Customer[] customers) throws SQLException;

    float calculateBookingCost(int num_tickets);
    void cancelBooking(int booking_id) throws SQLException;
    void displayAvailableSeats() throws SQLException;
    int getAvailableNoOfTickets() throws SQLException;

    
    void deleteBooking(int booking_id) throws SQLException;

    void close() throws SQLException;
}
