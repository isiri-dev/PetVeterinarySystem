/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {

    
    private int getCount(String tableName) {

        String sql;

        switch (tableName) {
            case "customers":
            case "pets":
            case "veterinarians":
                sql = "SELECT COUNT(*) FROM " + tableName;
                break;

            case "appointments":
                sql = "SELECT COUNT(*) FROM appointments "
                    + "WHERE DATE(appointment_date) = CURDATE()";
                break;

            default:
                throw new IllegalArgumentException(
                    "Invalid table name: " + tableName
                );
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println(
                "Error retrieving dashboard count: " + e.getMessage()
            );
        }

        return 0;
    }

    public int getTotalCustomers() {
        return getCount("customers");
    }

    public int getTotalPets() {
        return getCount("pets");
    }

    public int getTotalVeterinarians() {
        return getCount("veterinarians");
    }

    public int getTodaysAppointments() {
        return getCount("appointments");
    }
    
    public java.util.List<Object[]> getUpcomingAppointments() {

    java.util.List<Object[]> appointments = new java.util.ArrayList<>();

    String sql = "SELECT a.appointment_id, "
            + "p.pet_name, "
            + "c.full_name AS customer_name, "
            + "v.full_name AS vet_name, "
            + "a.appointment_date "
            + "FROM appointments a "
            + "JOIN pets p ON a.pet_id = p.pet_id "
            + "JOIN customers c ON p.customer_id = c.customer_id "
            + "JOIN veterinarians v ON a.vet_id = v.vet_id "
            + "WHERE a.appointment_date >= NOW() "
            + "ORDER BY a.appointment_date ASC "
            + "LIMIT 10";

    try (java.sql.Connection con = database.DBConnection.getConnection();
         java.sql.PreparedStatement pst = con.prepareStatement(sql);
         java.sql.ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {

            Object[] row = {
                rs.getInt("appointment_id"),
                rs.getString("pet_name"),
                rs.getString("customer_name"),
                rs.getString("vet_name"),
                rs.getTimestamp("appointment_date")
            };

            appointments.add(row);
        }

    } catch (java.sql.SQLException e) {
        System.err.println(
                "Error loading upcoming appointments: " + e.getMessage()
        );
    }

    return appointments;
}
    
    
}
