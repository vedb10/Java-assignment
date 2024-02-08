package com.hexaware.controller;

import java.sql.SQLException;


import com.hexaware.model.Customer;
import com.hexaware.model.Event;

import exception.EventNotFoundException;
import exception.InvalidBookingIDException;

public interface IBookingSystemRepository {

    Event createEvent(String event_name, String date, String time, int total_seats, float ticket_price,
                      String event_type, String venue) throws SQLException; 

    Event getEventByName(String event_name) throws SQLException,EventNotFoundException; 
   
    void bookTickets(String event_name, int num_tickets, Customer[] customers) throws SQLException,EventNotFoundException;

    
    void cancelBooking(int booking_id) throws SQLException,InvalidBookingIDException;
    
    void displayAvailableSeats() throws SQLException;
 

    void close() throws SQLException;
}
