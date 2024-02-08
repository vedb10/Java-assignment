package service;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.CustomerDAOImpl;
import dao.EventDAO;
import dao.EventDAOImpl;
import entity.model.Booking;
import entity.model.Customer;
import entity.model.Event;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

    private final EventDAO eventDAO;
    private final BookingDAO bookingDAO;
    private Connection connection;
    
    
    public BookingSystemRepositoryImpl() {
        this.eventDAO = new EventDAOImpl();
        this.bookingDAO = new BookingDAOImpl(new EventDAOImpl(), new CustomerDAOImpl()); // Adjust this line according to your actual implementation
    }

    @Override
    public Event createEvent(String event_name, String date, String time, int total_seats, float ticket_price,
                             String event_type, String venue) throws SQLException {
        Event event = new Event(0, event_name, Date.valueOf(date), Time.valueOf(time), venue, total_seats, total_seats, ticket_price, event_type);
        eventDAO.createEvent(event);
        return event;	
    }

    @Override
    public Event getEventByName(String event_name) throws SQLException {
        int venueId = getVenueIdForEvent(event_name);
        return eventDAO.getEventByName(event_name, venueId);
    }

    @Override
    public void updateEvent(Event event) throws SQLException {
        eventDAO.updateEvent(event);
    }

    @Override
    public void deleteEvent(String event_name) throws SQLException {
        eventDAO.deleteEventByName(event_name);
    }

    @Override
    public void createBooking(Customer[] customers, Event event, int num_tickets, float total_cost) throws SQLException {
        Booking booking = new Booking(0, event, List.of(customers).toArray(new Customer[0]), num_tickets, total_cost);
        bookingDAO.createBooking(booking);
    }

    @Override
    public void updateBooking(Booking booking) throws SQLException {
        bookingDAO.updateBooking(booking);
    }

    @Override
    public Booking getBookingById(int booking_id) throws SQLException {
        return bookingDAO.getBookingById(booking_id);
    }

    @Override
    public void deleteBooking(int booking_id) throws SQLException {
        bookingDAO.deleteBooking(booking_id);
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    @Override
    public void bookTickets(String event_name, int num_tickets, Customer[] customers) throws SQLException {
//        Event event = eventDAO.getEventByName(event_name);
        int venueId = getVenueIdForEvent(event_name);

        Event event = eventDAO.getEventByName(event_name, venueId);

        if (event != null) {
            int availableSeats = event.getAvailable_seats();

            if (num_tickets <= availableSeats) {
                float totalCost = calculateBookingCost(num_tickets); // Assuming you have a method to calculate the total cost
                Booking booking = new Booking(0, event, customers, num_tickets, totalCost);
                bookingDAO.createBooking(booking);

                // Update the available seats after booking
                event.setAvailable_seats(availableSeats - num_tickets);
                eventDAO.updateEvent(event);

                System.out.println("Tickets booked successfully. Total cost: " + totalCost);
            } else {
                System.out.println("Not enough available seats for the booking.");
            }
        } else {
            System.out.println("Event not found.");
        }
    }

    @Override
    public float calculateBookingCost(int num_tickets) {
        // Implement logic to calculate the total cost of the booking based on ticket price and number of tickets
        // For simplicity, assume a fixed ticket price for all events
        float ticketPrice = 50.0f; // Set your ticket price here
        return num_tickets * ticketPrice;
    }
    @Override
    public void cancelBooking(int booking_id) throws SQLException {
        bookingDAO.deleteBooking(booking_id);
    }

    @Override
    public void displayAvailableSeats() throws SQLException {
        // Implement logic to display available seats
        List<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            System.out.println("Event: " + event.getEvent_name());
            System.out.println("Available Seats: " + event.getAvailable_seats());
            System.out.println("-----------------------");
        }
    }

    @Override
    public int getAvailableNoOfTickets() throws SQLException {
        // Implement logic to get available number of tickets
        List<Event> events = eventDAO.getAllEvents();
        int totalAvailableTickets = 0;
        for (Event event : events) {
            totalAvailableTickets += event.getAvailable_seats();
        }
        return totalAvailableTickets;
    }
//    private int getVenueIdForEvent(String event_name) {
//        int venueId = -1;  // Default value if not found
//
//        // Implement logic to query the database and retrieve the venue_id based on event_name
//        String query = "SELECT venue_id FROM Event WHERE event_name = ?";
//        
//        try (Connection connection = DBConnUtil.getDBConn();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, event_name);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    venueId = resultSet.getInt("venue_id");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception as needed
//        }
//
//        return venueId;
//    }
    private int getVenueIdForEvent(String event_name) {
        int venueId = -1;  // Default value if not found

        // Implement logic to query the database and retrieve the venue_id based on event_name
        String query = "SELECT venue_id FROM Event WHERE event_name = ?";
        
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, event_name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    venueId = resultSet.getInt("venue_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return venueId;
    }

}
