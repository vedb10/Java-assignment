package entity.model;

import java.util.Arrays;
import java.sql.Date; 

public class Booking {
    private int bookingId;
    private Event event;
    private Customer[] customers;
    private int numTickets;
    private float totalCost;
    private Date bookingDate;

  
    public Booking() {
    
    }
    public Booking(int bookingId, Event event, Customer[] customers, int numTickets, float totalCost) {
        this.bookingId = bookingId;
        this.event = event;
        this.customers = customers;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
    }

    public Booking(int bookingId, Event event, Customer[] customers, int numTickets, float totalCost, Date bookingDate) {
        this.bookingId = bookingId;
        this.event = event;
        this.customers = Arrays.copyOf(customers, customers.length);
        this.numTickets = numTickets;
        this.totalCost = totalCost;
        this.bookingDate = bookingDate;
    }

   
    public Date getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Customer[] getCustomers() {
        return Arrays.copyOf(customers, customers.length);
    }

    public void setCustomers(Customer[] customers) {
        this.customers = Arrays.copyOf(customers, customers.length);
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
}
