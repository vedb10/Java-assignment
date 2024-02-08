package com.hexaware.controller;

import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.hexaware.model.Booking;
import com.hexaware.model.Customer;

import exception.InvalidBookingIDException;


public class BookingDAOImpl implements BookingDAO {

   
    private static final String INSERT_BOOKING = "INSERT INTO Booking (customer_id, event_id, num_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_BOOKING = "DELETE FROM Booking WHERE booking_id = ?";

  
    private final CustomerDAO customerDAO;

    public BookingDAOImpl(EventDAO eventDAO, CustomerDAO customerDAO) {
      
        this.customerDAO = customerDAO;
    }

    
    @Override
    public void createBooking(Booking booking) {
        try (Connection connection = DBConnUtil.getDBConn()) {
            for (Customer customer : booking.getCustomers()) {
                if (customerExists(customer.getEmail(), customer.getPhone_number())) {
                    System.out.println("Customer with email or mobile already exists. Skipping creation.");
                    continue;
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS)) {
                    int generatedCustomerId = customerDAO.createCustomerAndGetId(customer);

                    preparedStatement.setInt(1, generatedCustomerId);
                    preparedStatement.setInt(2, booking.getEvent().getEvent_id());
                    preparedStatement.setInt(3, booking.getNumTickets());
                    preparedStatement.setDouble(4, booking.getTotalCost());
                    preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));

                    preparedStatement.executeUpdate();

                    try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            int generatedBookingId = resultSet.getInt(1);
                            System.out.println("Booking ID: " + generatedBookingId);
                        } else {
                            System.out.println("Failed to retrieve generated booking ID.");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void deleteBooking(int bookingId) throws SQLException,InvalidBookingIDException {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING)) {
            preparedStatement.setInt(1, bookingId);
//           
            int affectedrows = preparedStatement.executeUpdate();
            if(affectedrows <= 0) {
            	throw new InvalidBookingIDException("Invalid booking ID");
            }
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    
    
    private boolean customerExists(String email, String phone_number) throws SQLException {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT customer_id FROM customer WHERE email = ? OR phone_number = ?")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone_number);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a record with the email or mobile number exists
            }
        }
    }
    



}
