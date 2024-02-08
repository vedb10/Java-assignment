package dao;

import entity.model.Event;

import java.sql.SQLException;
import java.util.List;

public interface EventDAO {

    // Create
    void createEvent(Event event);

    // Read
//    Event getEventByName(String eventName, int venueId) throws SQLException;
    Event getEventByName(String eventName, int venueId) throws SQLException;
    Event getEventByName(String eventName) throws SQLException;
    Event getEventById(int eventId);
    List<Event> getAllEvents();

    // Update
    void updateEvent(Event event);

    // Delete
    void deleteEvent(int eventId);
    
    void deleteEventByName(String eventName);
    
    

	
}
