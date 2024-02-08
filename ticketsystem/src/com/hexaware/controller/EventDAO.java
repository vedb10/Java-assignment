package com.hexaware.controller;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.model.Event;

import exception.EventNotFoundException;

public interface EventDAO {


    void createEvent(Event event);

    Event getEventByName(String eventName, int venueId) throws SQLException,EventNotFoundException;  
    
    List<Event> getAllEvents();

    void updateEvent(Event event);

   
    
    
    
    

	
}
