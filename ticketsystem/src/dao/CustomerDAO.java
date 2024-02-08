package dao;

import entity.model.Customer;
import java.util.List;

public interface CustomerDAO {

    // Create
    void createCustomer(Customer customer);
    int createCustomerAndGetId(Customer customer);

    // Read
    Customer getCustomerById(int customerId);
    List<Customer> getAllCustomers();

    // Update
    void updateCustomer(Customer customer);

    // Delete
    void deleteCustomer(int customerId);
}
