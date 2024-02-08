package service;

import entity.model.Customer;

public interface CustomerServiceProvider {

    // Create
    void createCustomer(Customer customer);

    // Read
    Customer getCustomerById(int customerId);

    // Update
    void updateCustomer(Customer customer);

    // Delete
    void deleteCustomer(int customerId);
}
