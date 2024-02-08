package service;

import entity.model.Event;

public interface IEventServiceProvider {

    // Create
    void createEvent(Event event);

    // Read
    Event getEventById(int eventId);

    // Update
    void updateEvent(Event event);

    // Delete
    void deleteEvent(int eventId);
}
