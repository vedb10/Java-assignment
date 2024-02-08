package com.hexaware.controller;



import java.sql.SQLException;

import com.hexaware.model.Booking;

import exception.InvalidBookingIDException;

public interface BookingDAO {

 
    void createBooking(Booking booking);

    void deleteBooking(int bookingId)throws SQLException,InvalidBookingIDException;
}
