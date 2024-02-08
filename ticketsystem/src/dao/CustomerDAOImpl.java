package dao;

import entity.model.Customer;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    // SQL queries for Customer table
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer (customer_name, email, phone_number) VALUES (?, ?, ?)";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM Customer WHERE customer_id = ?";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET customer_name = ?, email = ?, phone_number = ? WHERE customer_id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE customer_id = ?";

    @Override
    public void createCustomer(Customer customer) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {
            preparedStatement.setString(1, customer.getCustomer_name());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone_number());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customer = extractCustomerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = extractCustomerFromResultSet(resultSet);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER)) {
            preparedStatement.setString(1, customer.getCustomer_name());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone_number());
            preparedStatement.setInt(4, customer.getCustomer_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    @Override
    public int createCustomerAndGetId(Customer customer) {
        int generatedCustomerId = -1;  // Default value if not found

        try (Connection connection = DBConnUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getCustomer_name());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone_number());
            preparedStatement.executeUpdate();

            // Retrieve the generated customer_id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedCustomerId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return generatedCustomerId;
    }

    // Helper method to extract Customer object from ResultSet
    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomer_id(resultSet.getInt("customer_id"));
        customer.setCustomer_name(resultSet.getString("customer_name"));
        customer.setEmail(resultSet.getString("email"));
        customer.setPhone_number(resultSet.getString("phone_number"));
        return customer;
    }
}
