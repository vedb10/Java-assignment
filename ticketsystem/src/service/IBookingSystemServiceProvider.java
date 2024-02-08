package service;

import entity.model.Event;
import entity.model.Customer;

public interface IBookingSystemServiceProvider {

    // Create
    Event createEvent(String event_name, String date, String time, int total_seats, float ticket_price,
                      String event_type, String venue);

    // Calculate and set the total cost of the booking
    float calculateBookingCost(int num_tickets);

    // Book a specified number of tickets for an event
    void bookTickets(String event_name, int num_tickets, Customer[] arrayOfCustomer);

    // Cancel the booking and update the available seats
    void cancelBooking(int booking_id);

    // Get the total available tickets
    int getAvailableNoOfTickets();

    // Get event details
    Event getEventDetails(String event_name);

    // Additional method to display available seats
    void displayAvailableSeats();
}
