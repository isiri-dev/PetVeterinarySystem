/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import model.Customer;
import model.CustomerDAO;
import java.sql.SQLException;

public class CustomerDAOTest {

    public static void main(String[] args) {

        CustomerDAO dao = new CustomerDAO();

        Customer customer = new Customer();

        customer.setName("Test Customer");
        customer.setPhone("0771234567");
        customer.setEmail("test@example.com");
        customer.setAddress("Galle");

        try {

            boolean success = dao.addCustomer(customer);

            if (success) {
                System.out.println(
                        "Customer added successfully!");
            } else {
                System.out.println(
                        "Customer was not added.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database operation failed.");

            e.printStackTrace();
        }
    }
}