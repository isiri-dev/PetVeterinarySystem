/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import model.Payment;
import model.PaymentDAO;
import java.sql.Timestamp;
import java.sql.SQLException;

public class PaymentDAOTest {

    public static void main(String[] args) throws SQLException {

        Payment payment = new Payment();

        payment.setAppointmentId(1);
        System.out.println("Appointment ID: " + payment.getAppointmentId());
        payment.setAmount(1500.00);
        payment.setPaymentDate(Timestamp.valueOf("2026-10-16 10:00:00"));
        payment.setPaymentMethod("Cash");
        System.out.println("Payment method: " + payment.getPaymentMethod());

        payment.setPaymentStatus("Paid");
        payment.setPaymentStatus("Paid");

        PaymentDAO dao = new PaymentDAO();

        
        try {
            boolean result = dao.addPayment(payment);

            if (result) {
                System.out.println("Payment added successfully!");
            } else {
                System.out.println("Payment was not added.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}