/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Appointment;
import model.AppointmentDAO;
import java.sql.Timestamp;
import java.sql.SQLException;

public class AppointmentDAOTest {

    public static void main(String[] args) {

        Appointment appointment = new Appointment();

        appointment.setPetId(1);
        appointment.setVetId(1);

        appointment.setAppointmentDate(
                Timestamp.valueOf("2026-10-16 10:00:00"));

        appointment.setServiceDescription("General Checkup");
        appointment.setServiceFee(1500.00);
        appointment.setStatus("Scheduled");

        AppointmentDAO dao = new AppointmentDAO();

        try {
            boolean result = dao.addAppointment(appointment);

            if (result) {
                System.out.println("Appointment added successfully!");
            } else {
                System.out.println("Appointment was not added.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
