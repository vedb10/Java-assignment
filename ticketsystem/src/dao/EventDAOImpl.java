package dao;

import entity.model.*;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {

    // SQL queries for Event table
	
    private static final String INSERT_EVENT = "INSERT INTO Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_EVENT_BY_ID = "SELECT * FROM Event WHERE event_id = ?";
    private static final String SELECT_ALL_EVENTS = "SELECT * FROM Event";
    private static final String UPDATE_EVENT = "UPDATE Event SET event_name = ?, event_date = ?, event_time = ?, venue_name = ?, total_seats = ?, available_seats = ?, ticket_price = ?, event_type = ? WHERE event_id = ?";
    private static final String DELETE_EVENT = "DELETE FROM Event WHERE event_id = ?";
    private static final String SELECT_EVENT_BY_NAME = "SELECT * FROM Event WHERE event_name = ? AND venue_id = ?";

    private static final String DELETE_EVENT_BY_NAME = "DELETE FROM Event WHERE event_name = ?";
    @Override
    public void createEvent(Event event) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT)) {
            preparedStatement.setString(1, event.getEvent_name());
            preparedStatement.setDate(2, event.getEvent_date());
            preparedStatement.setTime(3, event.getEvent_time());
            preparedStatement.setString(4, event.getVenue_name());
            preparedStatement.setInt(5, event.getTotal_seats());
            preparedStatement.setInt(6, event.getAvailable_seats());
            preparedStatement.setDouble(7, event.getTicket_price());
            preparedStatement.setString(8, event.getEvent_type());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public Event getEventById(int eventId) {
        Event event = null;
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_ID)) {
            preparedStatement.setInt(1, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    event = extractEventFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EVENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Event event = extractEventFromResultSet(resultSet);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return events;
    }

    @Override
    public void updateEvent(Event event) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EVENT)) {
            preparedStatement.setString(1, event.getEvent_name());
            preparedStatement.setDate(2, event.getEvent_date());
            preparedStatement.setTime(3, event.getEvent_time());
            preparedStatement.setString(4, event.getVenue_name());
            preparedStatement.setInt(5, event.getTotal_seats());
            preparedStatement.setInt(6, event.getAvailable_seats());
            preparedStatement.setDouble(7, event.getTicket_price());
            preparedStatement.setString(8, event.getEvent_type());
            preparedStatement.setInt(9, event.getEvent_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void deleteEvent(int eventId) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EVENT)) {
            preparedStatement.setInt(1, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    @Override
    public void deleteEventByName(String eventName) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EVENT_BY_NAME)) {
            preparedStatement.setString(1, eventName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    // Helper method to extract Event object from ResultSet
    private Event extractEventFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        event.setEvent_id(resultSet.getInt("event_id"));
        event.setEvent_name(resultSet.getString("event_name"));
        event.setEvent_date(resultSet.getDate("event_date"));
        event.setEvent_time(resultSet.getTime("event_time"));
        event.setVenue_name(resultSet.getString("venue_name"));
        event.setTotal_seats(resultSet.getInt("total_seats"));
        event.setAvailable_seats(resultSet.getInt("available_seats"));
        event.setTicket_price(resultSet.getDouble("ticket_price"));
        event.setEvent_type(resultSet.getString("event_type"));
        return event;
    }

//	@Override
//	public Event getEventByName(String eventName) throws SQLException {
//        Event event = null;
//        try (Connection connection = DBConnUtil.getDBConn();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_NAME)) {
//            preparedStatement.setString(1, eventName);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    event = extractEventFromResultSet(resultSet);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception as needed
//        }
//        return event;
//    }
    @Override
    public Event getEventByName(String eventName) throws SQLException {
        Event event = null;
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_NAME)) {
            preparedStatement.setString(1, eventName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    event = extractEventFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return event;
    }
    @Override
    public Event getEventByName(String eventName, int venueId) throws SQLException {
        Event event = null;
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_NAME)) {
            preparedStatement.setString(1, eventName);
            preparedStatement.setInt(2, venueId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    event = extractEventFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return event;
    }
}
