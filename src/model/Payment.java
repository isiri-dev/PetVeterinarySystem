/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

public class Payment {
    
    private int paymentId;
    private int appointmentId;
    private double amount;
    private Timestamp paymentDate;
    private String paymentMethod;
    private String paymentStatus;
    
    public Payment(){
    }
    
    public Payment(int paymentId, int appointmentId,
        double amount, Timestamp paymentDate,
        String paymentMethod, String paymentStatus) {

            this.paymentId = paymentId;
            this.appointmentId = appointmentId;
            this.amount = amount;
            this.paymentDate = paymentDate;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
    }
    
    
    public int getPaymentId(){
        return paymentId;
    }
    
    public void setPaymentId(int paymentId){
        this.paymentId = paymentId;
    }
    
    public int getAppointmentId(){
        return appointmentId;
    }
    
    public void setAppointmentId(int appointmentId) {
         this.appointmentId = appointmentId;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public Timestamp getPaymentDate(){
        return paymentDate;
    }
    
    public void setPaymentDate(Timestamp paymentDate){
        this.paymentDate = paymentDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }   
    
    public String getPaymentStatus(){
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus){
        this.paymentStatus = paymentStatus;
    }
            
    
}


