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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    
    public boolean addAppointment(Appointment appointment) throws SQLException {
        return addAppointmentAndGetId(appointment) > 0;
    }

    
    public int addAppointmentAndGetId(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments "
                + "(pet_id, vet_id, appointment_date, "
                + "service_description, service_fee, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, appointment.getPetId());
            pst.setInt(2, appointment.getVetId());
            pst.setTimestamp(3, appointment.getAppointmentDate());
            pst.setString(4, appointment.getServiceDescription());
            pst.setDouble(5, appointment.getServiceFee());
            pst.setString(6, appointment.getStatus());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        appointment.setAppointmentId(generatedId);
                        return generatedId;
                    }
                }
                return 1;
            }
            return -1;
        }
    }

    
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT a.appointment_id, a.pet_id, a.vet_id, a.appointment_date, "
                + "a.service_description, a.service_fee, a.status, "
                + "COALESCE(p.pet_name, 'Unknown Pet') AS pet_name, "
                + "COALESCE(v.full_name, 'Unknown Vet') AS vet_name "
                + "FROM appointments a "
                + "LEFT JOIN pets p ON a.pet_id = p.pet_id "
                + "LEFT JOIN veterinarians v ON a.vet_id = v.vet_id "
                + "ORDER BY a.appointment_id ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Appointment appointment = new Appointment();

                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setPetId(rs.getInt("pet_id"));
                appointment.setVetId(rs.getInt("vet_id"));
                appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));
                appointment.setServiceDescription(rs.getString("service_description"));
                appointment.setServiceFee(rs.getDouble("service_fee"));
                appointment.setStatus(rs.getString("status"));
                appointment.setPetName(rs.getString("pet_name"));
                appointment.setVetName(rs.getString("vet_name"));

                appointments.add(appointment);
            }
        }

        return appointments;
    }

    
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        String sql = "SELECT a.appointment_id, a.pet_id, a.vet_id, a.appointment_date, "
                + "a.service_description, a.service_fee, a.status, "
                + "COALESCE(p.pet_name, 'Unknown Pet') AS pet_name, "
                + "COALESCE(v.full_name, 'Unknown Vet') AS vet_name "
                + "FROM appointments a "
                + "LEFT JOIN pets p ON a.pet_id = p.pet_id "
                + "LEFT JOIN veterinarians v ON a.vet_id = v.vet_id "
                + "WHERE a.appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, appointmentId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Appointment appointment = new Appointment();

                    appointment.setAppointmentId(rs.getInt("appointment_id"));
                    appointment.setPetId(rs.getInt("pet_id"));
                    appointment.setVetId(rs.getInt("vet_id"));
                    appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));
                    appointment.setServiceDescription(rs.getString("service_description"));
                    appointment.setServiceFee(rs.getDouble("service_fee"));
                    appointment.setStatus(rs.getString("status"));
                    appointment.setPetName(rs.getString("pet_name"));
                    appointment.setVetName(rs.getString("vet_name"));

                    return appointment;
                }
            }
        }
        return null;
    }

    
    public boolean updateAppointment(Appointment appointment) throws SQLException {
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

   
    public boolean deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, appointmentId);

            return pst.executeUpdate() > 0;
        }
    }

    
    public boolean hasPaymentRecords(int appointmentId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM payments WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, appointmentId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    
    public boolean isVetDoubleBooked(int vetId, Timestamp appointmentDate, int excludeAppointmentId) throws SQLException {
        String sql;
        if (excludeAppointmentId > 0) {
            sql = "SELECT COUNT(*) FROM appointments "
                + "WHERE vet_id = ? AND appointment_date = ? "
                + "AND status != 'Cancelled' AND appointment_id != ?";
        } else {
            sql = "SELECT COUNT(*) FROM appointments "
                + "WHERE vet_id = ? AND appointment_date = ? "
                + "AND status != 'Cancelled'";
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, vetId);
            pst.setTimestamp(2, appointmentDate);

            if (excludeAppointmentId > 0) {
                pst.setInt(3, excludeAppointmentId);
            }

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

   
    public List<Appointment> searchAppointments(String keyword) throws SQLException {

    List<Appointment> appointments = new ArrayList<>();

    String sql = "SELECT a.*, p.pet_name, v.full_name AS vet_name "
            + "FROM appointments a "
            + "LEFT JOIN pets p ON a.pet_id = p.pet_id "
            + "LEFT JOIN veterinarians v ON a.vet_id = v.vet_id "
            + "WHERE LOWER(p.pet_name) LIKE ? "
            + "ORDER BY a.appointment_date DESC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "%" + keyword.trim().toLowerCase() + "%");

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Appointment a = new Appointment();

                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setPetId(rs.getInt("pet_id"));
                a.setVetId(rs.getInt("vet_id"));
                a.setAppointmentDate(rs.getTimestamp("appointment_date"));
                a.setServiceDescription(rs.getString("service_description"));
                a.setServiceFee(rs.getDouble("service_fee"));
                a.setStatus(rs.getString("status"));
                a.setPetName(rs.getString("pet_name"));
                a.setVetName(rs.getString("vet_name"));

                appointments.add(a);
            }
        }
    }

    return appointments;
}
}