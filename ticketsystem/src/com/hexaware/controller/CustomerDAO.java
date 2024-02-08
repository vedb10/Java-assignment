package com.hexaware.controller;



import com.hexaware.model.Customer;

public interface CustomerDAO {

    int createCustomerAndGetId(Customer customer);

}
