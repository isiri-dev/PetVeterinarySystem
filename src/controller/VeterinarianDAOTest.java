/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Veterinarian;
import model.VeterinarianDAO;

public class VeterinarianDAOTest {

    public static void main(String[] args) {

        try {
            VeterinarianDAO dao = new VeterinarianDAO();

            Veterinarian vet = new Veterinarian();
            vet.setName("Dr. Silva");
            vet.setPhone("0771234567");
            vet.setSpecialization("Animal Surgery");

            boolean result = dao.addVeterinarian(vet);

            if (result) {
                System.out.println("Veterinarian added successfully!");
            } else {
                System.out.println("Veterinarian was not added.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
