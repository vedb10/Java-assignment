package dao;

import entity.model.Venue;
import java.util.List;

public interface VenueDAO {

    // Create
    void createVenue(Venue venue);

    // Read
    Venue getVenueById(int venueId);
    List<Venue> getAllVenues();

    // Update
    void updateVenue(Venue venue);

    // Delete
    void deleteVenue(int venueId);
}
