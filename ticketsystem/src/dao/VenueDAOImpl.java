package dao;

import entity.model.Venue;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueDAOImpl implements VenueDAO {

    // SQL queries for Venue table
    private static final String INSERT_VENUE = "INSERT INTO Venue (venue_name, address) VALUES (?, ?)";
    private static final String SELECT_VENUE_BY_ID = "SELECT * FROM Venue WHERE venue_id = ?";
    private static final String SELECT_ALL_VENUES = "SELECT * FROM Venue";
    private static final String UPDATE_VENUE = "UPDATE Venue SET venue_name = ?, address = ? WHERE venue_id = ?";
    private static final String DELETE_VENUE = "DELETE FROM Venue WHERE venue_id = ?";

    @Override
    public void createVenue(Venue venue) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VENUE)) {
            preparedStatement.setString(1, venue.getVenue_name());
            preparedStatement.setString(2, venue.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public Venue getVenueById(int venueId) {
        Venue venue = null;
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VENUE_BY_ID)) {
            preparedStatement.setInt(1, venueId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    venue = extractVenueFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return venue;
    }

    @Override
    public List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VENUES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Venue venue = extractVenueFromResultSet(resultSet);
                venues.add(venue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return venues;
    }

    @Override
    public void updateVenue(Venue venue) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VENUE)) {
            preparedStatement.setString(1, venue.getVenue_name());
            preparedStatement.setString(2, venue.getAddress());
            preparedStatement.setInt(3, venue.getVenue_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void deleteVenue(int venueId) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VENUE)) {
            preparedStatement.setInt(1, venueId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    // Helper method to extract Venue object from ResultSet
    private Venue extractVenueFromResultSet(ResultSet resultSet) throws SQLException {
        Venue venue = new Venue();
        venue.setVenue_id(resultSet.getInt("venue_id"));
        venue.setVenue_name(resultSet.getString("venue_name"));
        venue.setAddress(resultSet.getString("address"));
        return venue;
    }
}
