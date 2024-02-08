package app;

import entity.model.Customer;
import entity.model.Event;
import service.BookingSystemRepositoryImpl;
import service.BookingSystemServiceProviderImpl;
import exception.EventNotFoundException;
import exception.InvalidBookingIDException;
import exception.SQLExceptionHandler;

import java.sql.SQLException;
import java.util.Scanner;

public class TicketBookingSystem {

	private static final BookingSystemRepositoryImpl bookingSystem = new BookingSystemRepositoryImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Implement logic for your menu or user interactions
        // For example:
        int choice;
        do {
            System.out.println("1. Create Event");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Display Available Seats");
            System.out.println("5. Get Event Details");
            System.out.println("6. Get Available Number of Tickets");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    createEvent(scanner);
                    break;
                case 2:
                    bookTickets(scanner);
                    break;
                case 3:
                    cancelBooking(scanner);
                    break;
                case 4:
                    displayAvailableSeats();
                    break;
                case 5:
                    getEventDetails(scanner);
                    break;
                case 6:
                    getAvailableNoOfTickets();
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        // Close resources
        try {
            bookingSystem.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEvent(Scanner scanner) {
        System.out.print("Enter event name: ");
        String event_name = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM:SS): ");
        String time = scanner.nextLine();
        System.out.print("Enter total seats: ");
        int total_seats = scanner.nextInt();
        System.out.print("Enter ticket price: ");
        float ticket_price = scanner.nextFloat();
        System.out.print("Enter event type: ");
        String event_type = scanner.next();
        System.out.print("Enter venue: ");
        String venue = scanner.next();

        try {
            bookingSystem.createEvent(event_name, date, time, total_seats, ticket_price, event_type, venue);
            System.out.println("Event created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating event. Please try again.");
        }
    }

    private static void bookTickets(Scanner scanner) {
        System.out.print("Enter event name: ");
        String event_name = scanner.next();
        System.out.print("Enter number of tickets to book: ");
        int num_tickets = scanner.nextInt();

        // Assuming you have logic to input customer details
        // For simplicity, let's assume customer details are hardcoded here
        Customer[] customers = new Customer[num_tickets];
        for (int i = 0; i < num_tickets; i++) {
            System.out.print("Enter customer name for ticket " + (i + 1) + ": ");
            String customerName = scanner.next();
            System.out.print("Enter customer email for ticket " + (i + 1) + ": ");
            String customerEmail = scanner.next();
            System.out.print("Enter customer phone number for ticket " + (i + 1) + ": ");
            String customerPhoneNumber = scanner.next();
            customers[i] = new Customer(0, customerName, customerEmail, customerPhoneNumber);
        }

        try {
            bookingSystem.bookTickets(event_name, num_tickets, customers);
            System.out.println("Tickets booked successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error booking tickets. Please try again.");
        }
    }

    private static void cancelBooking(Scanner scanner) {
        System.out.print("Enter booking ID to cancel: ");
        int booking_id = scanner.nextInt();

        try {
            bookingSystem.cancelBooking(booking_id);
            System.out.println("Booking canceled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error canceling booking. Please try again.");
        }
    }

    private static void displayAvailableSeats() {
        // Implement logic to display available seats using bookingSystem.displayAvailableSeats() method
        try {
            bookingSystem.displayAvailableSeats();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error displaying available seats. Please try again.");
        }
    }

    private static void getEventDetails(Scanner scanner) {
        // Implement logic to take user input and get event details using bookingSystem.getEventDetails() method
        System.out.print("Enter event name: ");
        String event_name = scanner.next();
        try {
            Event event = bookingSystem.getEventByName(event_name);
            if (event != null) {
                System.out.println("Event details:");
                System.out.println("Name: " + event.getEvent_name());
                System.out.println("Date: " + event.getEvent_date());
                System.out.println("Time: " + event.getEvent_time());
                System.out.println("Total Seats: " + event.getTotal_seats());
                System.out.println("Available Seats: " + event.getAvailable_seats());
                System.out.println("Ticket Price: " + event.getTicket_price());
                System.out.println("Event Type: " + event.getEvent_type());
                System.out.println("Venue: " + event.getVenue_name());
            } else {
                System.out.println("Event not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error getting event details. Please try again.");
        }
    }

    private static void getAvailableNoOfTickets() {
        // Implement logic to get available number of tickets using bookingSystem.getAvailableNoOfTickets() method
        try {
            int availableTickets = bookingSystem.getAvailableNoOfTickets();
            System.out.println("Available number of tickets: " + availableTickets);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error getting available number of tickets. Please try again.");
        }
    }
}
