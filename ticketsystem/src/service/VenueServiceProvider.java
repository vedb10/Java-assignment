package service;

import entity.model.Venue;

public interface VenueServiceProvider {

    // Create
    void createVenue(Venue venue);

    // Read
    Venue getVenueById(int venueId);

    // Update
    void updateVenue(Venue venue);

    // Delete
    void deleteVenue(int venueId);
}
