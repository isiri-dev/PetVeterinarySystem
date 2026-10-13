/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    
     public boolean addCustomer(Customer customer) throws SQLException {

        String sql = "INSERT INTO customers "
                   + "(full_name, phone, email, address) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, customer.getName());
            pst.setString(2, customer.getPhone());
            pst.setString(3, customer.getEmail());
            pst.setString(4, customer.getAddress());

            return pst.executeUpdate() > 0;
        }
        
     }
     
     public List<Customer> getAllCustomers() throws SQLException {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT customer_id, full_name, phone, email, address "
                   + "FROM customers";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Customer customer = new Customer();

                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("full_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));

                customers.add(customer);
            }
        }

        return customers;
    }
     
     public boolean updateCustomer(Customer customer) throws SQLException {

        String sql = "UPDATE customers "
                   + "SET full_name = ?, phone = ?, email = ?, address = ? "
                   + "WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, customer.getName());
            pst.setString(2, customer.getPhone());
            pst.setString(3, customer.getEmail());
            pst.setString(4, customer.getAddress());
            pst.setInt(5, customer.getCustomerId());

            return pst.executeUpdate() > 0;
        }
    }
     
      public boolean deleteCustomer(int customerId) throws SQLException {

        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, customerId);

            return pst.executeUpdate() > 0;
        }
    }
     
     
}
