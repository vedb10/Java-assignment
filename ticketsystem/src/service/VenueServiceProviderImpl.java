package service;

import dao.VenueDAO;
import dao.VenueDAOImpl;
import entity.model.Venue;

public class VenueServiceProviderImpl implements VenueServiceProvider {

    private final VenueDAO venueDAO;

    public VenueServiceProviderImpl() {
        this.venueDAO = new VenueDAOImpl(); // You can use dependency injection for better design
    }

    @Override
    public void createVenue(Venue venue) {
        venueDAO.createVenue(venue);
    }

    @Override
    public Venue getVenueById(int venueId) {
        return venueDAO.getVenueById(venueId);
    }

    @Override
    public void updateVenue(Venue venue) {
        venueDAO.updateVenue(venue);
    }

    @Override
    public void deleteVenue(int venueId) {
        venueDAO.deleteVenue(venueId);
    }
}
