package com.hexaware.controller;

import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.hexaware.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

 
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer (customer_name, email, phone_number) VALUES (?, ?, ?)";



    @Override
    public int createCustomerAndGetId(Customer customer) {
        int generatedCustomerId = -1;  

        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getCustomer_name());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone_number());
            preparedStatement.executeUpdate();

          
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedCustomerId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }

        return generatedCustomerId;
    }
    
  
}
