package service;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.CustomerDAOImpl;
import dao.EventDAO;
import dao.EventDAOImpl;
import entity.model.Booking;
import entity.model.Customer;
import entity.model.Event;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

public class BookingSystemServiceProviderImpl implements IBookingSystemServiceProvider {

	private final EventDAO eventDAO;
    private final BookingDAO bookingDAO;

    public BookingSystemServiceProviderImpl() {
        this.eventDAO = new EventDAOImpl();
        this.bookingDAO = new BookingDAOImpl(new EventDAOImpl(), new CustomerDAOImpl());
    }
    

    @Override
    public Event createEvent(String event_name, String date, String time, int total_seats, float ticket_price,
            String event_type, String venue) {
Event event = new Event(0, event_name, Date.valueOf(date), Time.valueOf(time), venue, total_seats, total_seats, ticket_price, event_type);
eventDAO.createEvent(event);
return event;
}

    @Override
    public float calculateBookingCost(int num_tickets) {
        // Implement logic to calculate the total cost of the booking based on ticket price and number of tickets
        // For simplicity, assume a fixed ticket price for all events
        float ticketPrice = 50.0f; // Set your ticket price here
        return num_tickets * ticketPrice;
    }

    @Override
    public void bookTickets(String event_name, int num_tickets, Customer[] arrayOfCustomer) {
        try {
            Event event = eventDAO.getEventByName(event_name);
            int availableSeats = event.getAvailable_seats(); // Update method name

            if (num_tickets <= availableSeats) {
                float totalCost = calculateBookingCost(num_tickets);
                List<Customer> customers = Arrays.asList(arrayOfCustomer);

                Booking booking = new Booking(0, event, customers.toArray(new Customer[0]), num_tickets, totalCost);
                bookingDAO.createBooking(booking);

                // Update the available seats after booking
                event.setAvailable_seats(availableSeats - num_tickets);
                eventDAO.updateEvent(event);

                System.out.println("Booking successful. Total cost: " + totalCost);
            } else {
                System.out.println("Not enough available seats for the booking.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's error-handling strategy
        }
    }

    @Override
    public void cancelBooking(int booking_id) {
        Booking booking = bookingDAO.getBookingById(booking_id);
		if (booking != null) {
		    // Update available seats after cancellation
		    Event event = booking.getEvent();
		    int availableSeats = event.getAvailableSeats();
		    event.setAvailable_seats(availableSeats + booking.getNumTickets());
		    eventDAO.updateEvent(event);

		    // Delete the booking
		    bookingDAO.deleteBooking(booking_id);

		    System.out.println("Booking canceled successfully.");
		} else {
		    System.out.println("Booking not found.");
		}
    }

    @Override
    public int getAvailableNoOfTickets() {
        List<Event> events = eventDAO.getAllEvents();
		int totalAvailableTickets = 0;
		for (Event event : events) {
		    totalAvailableTickets += event.getAvailableSeats();
		}
		return totalAvailableTickets;
    }

    @Override
    public Event getEventDetails(String event_name) {
        try {
            return eventDAO.getEventByName(event_name);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's error-handling strategy
            return null;
        }
    }

    @Override
    public void displayAvailableSeats() {
        List<Event> events = eventDAO.getAllEvents();
		for (Event event : events) {
		    System.out.println("Event: " + event.getEvent_name());
		    System.out.println("Available Seats: " + event.getAvailableSeats());
		    System.out.println("-----------------------");
		}
    }
}
