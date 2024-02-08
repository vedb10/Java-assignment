package service;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import entity.model.Customer;

public class CustomerServiceProviderImpl implements CustomerServiceProvider {

    private final CustomerDAO customerDAO;

    public CustomerServiceProviderImpl() {
        this.customerDAO = new CustomerDAOImpl(); // You can use dependency injection for better design
    }

    @Override
    public void createCustomer(Customer customer) {
        customerDAO.createCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerDAO.deleteCustomer(customerId);
    }
}
