

package com.hexaware.model;


public class Customer {
    private int customer_id; 
    private String customer_name;
    private String email;
    private String phone_number;
   

    
    public Customer() {
    }

    public Customer(int customer_id, String customer_name, String email, String phone_number) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    // Getter and Setter methods
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public void displayCustomerDetails() {
        System.out.println("Customer ID: " + customer_id);
        System.out.println("Customer Name: " + customer_name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phone_number);
    }
}
