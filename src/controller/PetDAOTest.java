/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Pet;
import model.PetDAO;
import java.sql.SQLException;

public class PetDAOTest {

    public static void main(String[] args) {

        PetDAO dao = new PetDAO();

        Pet pet = new Pet();

        pet.setCustomerId(1);
        pet.setPetName("Milo");
        pet.setSpecies("Dog");
        pet.setBreed("Labrador");
        pet.setAge(3);

        try {

            boolean success = dao.addPet(pet);

            if (success) {
                System.out.println(
                        "Pet added successfully!");
            } else {
                System.out.println(
                        "Pet was not added.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database operation failed.");

            e.printStackTrace();
        }
    }
}
