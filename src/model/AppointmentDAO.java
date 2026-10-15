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

public class AppointmentDAO {

    
    public boolean addAppointment(Appointment appointment)
            throws SQLException {

        String sql = "INSERT INTO appointments "
                + "(pet_id, vet_id, appointment_date, "
                + "service_description, service_fee, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, appointment.getPetId());
            pst.setInt(2, appointment.getVetId());
            pst.setTimestamp(3, appointment.getAppointmentDate());
            pst.setString(4, appointment.getServiceDescription());
            pst.setDouble(5, appointment.getServiceFee());
            pst.setString(6, appointment.getStatus());

            return pst.executeUpdate() > 0;
        }
    }

   
    public List<Appointment> getAllAppointments() throws SQLException {

        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointments";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Appointment appointment = new Appointment();

                appointment.setAppointmentId(
                        rs.getInt("appointment_id"));

                appointment.setPetId(rs.getInt("pet_id"));
                appointment.setVetId(rs.getInt("vet_id"));

                appointment.setAppointmentDate(
                        rs.getTimestamp("appointment_date"));

                appointment.setServiceDescription(
                        rs.getString("service_description"));

               appointment.setServiceFee(
                        rs.getDouble("service_fee"));

                appointment.setStatus(rs.getString("status"));

                appointments.add(appointment);
            }
        }

        return appointments;
    }

    
    public boolean updateAppointment(Appointment appointment)
            throws SQLException {

        String sql = "UPDATE appointments SET "
                + "pet_id = ?, vet_id = ?, appointment_date = ?, "
                + "service_description = ?, service_fee = ?, status = ? "
                + "WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, appointment.getPetId());
            pst.setInt(2, appointment.getVetId());

            pst.setTimestamp(3, appointment.getAppointmentDate());

            pst.setString(4, appointment.getServiceDescription());
            pst.setDouble(5, appointment.getServiceFee());
            pst.setString(6, appointment.getStatus());

            pst.setInt(7, appointment.getAppointmentId());

            return pst.executeUpdate() > 0;
        }
    }

    
    public boolean deleteAppointment(int appointmentId)
            throws SQLException {

        String sql = "DELETE FROM appointments "
                + "WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, appointmentId);

            return pst.executeUpdate() > 0;
        }
    }
}