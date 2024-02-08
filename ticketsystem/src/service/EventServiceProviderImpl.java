package service;

import dao.EventDAO;
import dao.EventDAOImpl;
import entity.model.Event;

public class EventServiceProviderImpl implements IEventServiceProvider {

    private final EventDAO eventDAO;

    public EventServiceProviderImpl() {
        this.eventDAO = new EventDAOImpl(); // You can use dependency injection for better design
    }

    @Override
    public void createEvent(Event event) {
        eventDAO.createEvent(event);
    }

    @Override
    public Event getEventById(int eventId) {
        return eventDAO.getEventById(eventId);
    }

    @Override
    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
    }

    @Override
    public void deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
    }
}
