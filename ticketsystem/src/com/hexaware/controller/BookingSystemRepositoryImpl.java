package com.hexaware.controller;

import util.DBConnUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import com.hexaware.model.Booking;
import com.hexaware.model.Customer;
import com.hexaware.model.Event;

import exception.EventNotFoundException;
import exception.InvalidBookingIDException;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

    private final EventDAO eventDAO;
    private final BookingDAO bookingDAO;
    private Connection connection;
    
    
    public BookingSystemRepositoryImpl() {
        this.eventDAO = new EventDAOImpl();
        this.bookingDAO = new BookingDAOImpl(new EventDAOImpl(), new CustomerDAOImpl()); 
    }

    @Override
    public Event createEvent(String event_name, String date, String time, int total_seats, float ticket_price,
                             String event_type, String venue) throws SQLException {
        Event event = new Event(0, event_name, Date.valueOf(date), Time.valueOf(time), venue, total_seats, total_seats, ticket_price, event_type);
        eventDAO.createEvent(event);
        return event;	
    }

    @Override
    public Event getEventByName(String event_name) throws SQLException,EventNotFoundException {
        int venueId = getVenueIdForEvent(event_name);
        return eventDAO.getEventByName(event_name, venueId);
        
       
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    @Override
    public void bookTickets(String event_name, int num_tickets, Customer[] customers) throws SQLException,EventNotFoundException {
     
        int venueId = getVenueIdForEvent(event_name);

        Event event = eventDAO.getEventByName(event_name, venueId);

        if (event != null) {
            int availableSeats = event.getAvailable_seats();

            if (num_tickets <= availableSeats) {
            	double ticket_price = event.getTicket_price();
                float totalCost = num_tickets*(float)ticket_price; 
                Booking booking = new Booking(0, event, customers, num_tickets, totalCost);
                bookingDAO.createBooking(booking);

                
                event.setAvailable_seats(availableSeats - num_tickets);
                eventDAO.updateEvent(event);

                System.out.println("Tickets booked successfully. Total cost: " + totalCost);
            } else {
                System.out.println("Not enough available seats for the booking.");
            }
        } 
    }

   
    @Override
    public void cancelBooking(int booking_id) throws SQLException,InvalidBookingIDException {
        bookingDAO.deleteBooking(booking_id);
    }

    @Override
    public void displayAvailableSeats() throws SQLException {
       
        List<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            System.out.println("Event: " + event.getEvent_name());
            System.out.println("Available Seats: " + event.getAvailable_seats());
            System.out.println("-----------------------");
        }
    }



    private int getVenueIdForEvent(String event_name) {
        int venueId = -1;  

       
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
      
        }

        return venueId;
    }

}
