

package com.hexaware.model;

public class Venue {
    private int venue_id; 
    private String venue_name;
    private String address;

    
    public Venue() {
    }

    public Venue(int venue_id, String venue_name, String address) {
        this.venue_id = venue_id;
        this.venue_name = venue_name;
        this.address = address;
    }

    
    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void displayVenueDetails() {
        System.out.println("Venue ID: " + venue_id);
        System.out.println("Venue Name: " + venue_name);
        System.out.println("Address: " + address);
    }
}
