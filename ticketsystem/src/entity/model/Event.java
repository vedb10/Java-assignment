package entity.model;

import java.sql.Date;
import java.sql.Time;

public class Event {
    private int event_id;
    private String event_name;
    private Date event_date;
    private Time event_time;
    private String venue_name;
    private int total_seats;
    private int available_seats;
    private double ticket_price;
    private String event_type;


    public Event() {
    }

    public Event(int event_id, String event_name, Date event_date, Time event_time, String venue_name,
                 int total_seats, int available_seats, double ticket_price, String event_type) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = event_time;
        this.venue_name = venue_name;
        this.total_seats = total_seats;
        this.available_seats = available_seats;
        this.ticket_price = ticket_price;
        this.event_type = event_type;
    }

    // Getter and Setter methods (add as needed)

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public Time getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Time event_time) {
        this.event_time = event_time;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public int getAvailableSeats() {
        return Math.max(0, total_seats - available_seats);
    }

 
    public void displayEventDetails() {
        System.out.println("Event ID: " + event_id);
        System.out.println("Event Name: " + event_name);
        System.out.println("Date: " + event_date);
        System.out.println("Time: " + event_time);
        System.out.println("Venue: " + venue_name);
        System.out.println("Total Seats: " + total_seats);
        System.out.println("Available Seats: " + available_seats);
        System.out.println("Ticket Price: " + ticket_price);
        System.out.println("Event Type: " + event_type);
    }
}
