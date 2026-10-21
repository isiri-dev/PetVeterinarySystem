/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

public class Appointment {

    private int appointmentId;
    private int petId;
    private int vetId;
    private Timestamp appointmentDate;
    private String serviceDescription;
    private double serviceFee;
    private String status;

    public Appointment() {
    }

    public Appointment(int appointmentId, int petId, int vetId,
            Timestamp appointmentDate, String serviceDescription,
            double serviceFee, String status) {

        this.appointmentId = appointmentId;
        this.petId = petId;
        this.vetId = vetId;
        this.appointmentDate = appointmentDate;
        this.serviceDescription = serviceDescription;
        this.serviceFee = serviceFee;
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getServiceFee() {
        return serviceFee;
    }
 
    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}