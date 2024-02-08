package dao;

import entity.model.Booking;
import entity.model.Customer;
import entity.model.Event;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    // SQL queries for Booking table
    private static final String INSERT_BOOKING = "INSERT INTO Booking (customer_id, event_id, num_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BOOKING_BY_ID = "SELECT * FROM Booking WHERE booking_id = ?";
    private static final String SELECT_ALL_BOOKINGS = "SELECT * FROM Booking";
    private static final String UPDATE_BOOKING = "UPDATE Booking SET customer_id = ?, event_id = ?, num_tickets = ?, total_cost = ?, booking_date = ? WHERE booking_id = ?";
    private static final String DELETE_BOOKING = "DELETE FROM Booking WHERE booking_id = ?";

    private final EventDAO eventDAO;
    private final CustomerDAO customerDAO;

    public BookingDAOImpl(EventDAO eventDAO, CustomerDAO customerDAO) {
        this.eventDAO = eventDAO;
        this.customerDAO = customerDAO;
    }

    
    @Override
    public void createBooking(Booking booking) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING)) {

            for (Customer customer : booking.getCustomers()) {
                int generatedCustomerId = customerDAO.createCustomerAndGetId(customer);

                preparedStatement.setInt(1, generatedCustomerId);
                preparedStatement.setInt(2, booking.getEvent().getEvent_id());
                preparedStatement.setInt(3, booking.getNumTickets());
                preparedStatement.setDouble(4, booking.getTotalCost());
                preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));  // Set booking_date to the current date
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public Booking getBookingById(int bookingId) {
        Booking booking = null;
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_BY_ID)) {
            preparedStatement.setInt(1, bookingId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    booking = extractBookingFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKINGS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Booking booking = extractBookingFromResultSet(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return bookings;
    }

    @Override
    public void updateBooking(Booking booking) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING)) {

            Customer[] customers = booking.getCustomers();

            for (int i = 0; i < customers.length; i++) {
                preparedStatement.setInt(1, customers[i].getCustomer_id());
                preparedStatement.setInt(2, booking.getEvent().getEvent_id());
                preparedStatement.setInt(3, booking.getNumTickets());
                preparedStatement.setDouble(4, booking.getTotalCost());
                preparedStatement.setDate(5, booking.getBookingDate());
                preparedStatement.setInt(6, booking.getBookingId());  // This is fine

                // Increment the parameter index for each customer
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void deleteBooking(int bookingId) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING)) {
            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    // Helper method to extract Booking object from ResultSet
    private Booking extractBookingFromResultSet(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(resultSet.getInt("booking_id"));
        
        Customer customer = extractCustomerFromResultSet(resultSet);
        booking.setCustomers(new Customer[]{customer});

        // Assuming you have a method to retrieve an Event object from the ResultSet
        Event event = extractEventFromResultSet(resultSet);
        booking.setEvent(event);

        booking.setNumTickets(resultSet.getInt("num_tickets"));
        booking.setTotalCost(resultSet.getFloat("total_cost"));
        booking.setBookingDate(resultSet.getDate("booking_date"));

        return booking;
    }

    // Helper method to extract Customer object from ResultSet
    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomer_id(resultSet.getInt("customer_id"));
        // Set other fields for the Customer as needed
        return customer;
    }

    // Helper method to extract Event object from ResultSet
    private Event extractEventFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        event.setEvent_id(resultSet.getInt("event_id"));
        // Set other fields for the Event as needed
        return event;
    }
}
