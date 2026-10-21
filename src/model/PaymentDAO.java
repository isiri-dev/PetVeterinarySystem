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

public class PaymentDAO {

    
    public boolean addPayment(Payment payment) throws SQLException {

        String sql = "INSERT INTO payments "
                + "(appointment_id, amount, payment_date, "
                + "payment_method, payment_status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, payment.getAppointmentId());
            pst.setDouble(2, payment.getAmount());
            pst.setTimestamp(3, payment.getPaymentDate());
            pst.setString(4, payment.getPaymentMethod());
            pst.setString(5, payment.getPaymentStatus());

            return pst.executeUpdate() > 0;
        }
    }

    
    public List<Payment> getAllPayments() throws SQLException {

        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM payments";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setAppointmentId(
                        rs.getInt("appointment_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentDate(
                        rs.getTimestamp("payment_date"));
                payment.setPaymentMethod(
                        rs.getString("payment_method"));
                payment.setPaymentStatus(
                        rs.getString("payment_status"));

                payments.add(payment);
            }
        }

        return payments;
    }

    
    public boolean updatePayment(Payment payment) throws SQLException {

    String sql = "UPDATE payments SET "
            + "appointment_id = ?, "
            + "amount = ?, "
            + "payment_method = ?, "
            + "payment_status = ? "
            + "WHERE payment_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, payment.getAppointmentId());
        pst.setDouble(2, payment.getAmount());
        pst.setString(3, payment.getPaymentMethod());
        pst.setString(4, payment.getPaymentStatus());
        pst.setInt(5, payment.getPaymentId());

        return pst.executeUpdate() > 0;
    }
}

    
    public boolean deletePayment(int paymentId) throws SQLException {

        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, paymentId);

            return pst.executeUpdate() > 0;
        }
    }
}