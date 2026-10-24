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


public class PetDAO {
    
    public boolean addPet(Pet pet) throws SQLException {

        String sql = "INSERT INTO pets "
                + "(customer_id, pet_name, species, breed, age) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, pet.getCustomerId());
            pst.setString(2, pet.getPetName());
            pst.setString(3, pet.getSpecies());
            pst.setString(4, pet.getBreed());
            pst.setInt(5, pet.getAge());

            return pst.executeUpdate() > 0;
        }
    }
    
    public List<Pet> getAllPets() throws SQLException {

        List<Pet> pets = new ArrayList<>();

        String sql = "SELECT pet_id, customer_id, "
                + "pet_name, species, breed, age FROM pets";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Pet pet = new Pet();

                pet.setPetId(rs.getInt("pet_id"));
                pet.setCustomerId(rs.getInt("customer_id"));
                pet.setPetName(rs.getString("pet_name"));
                pet.setSpecies(rs.getString("species"));
                pet.setBreed(rs.getString("breed"));
                pet.setAge(rs.getInt("age"));

                pets.add(pet);
            }
        }

        return pets;
    }
    
    public boolean updatePet(Pet pet) throws SQLException {

        String sql = "UPDATE pets SET "
                + "customer_id = ?, pet_name = ?, "
                + "species = ?, breed = ?, age = ? "
                + "WHERE pet_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, pet.getCustomerId());
            pst.setString(2, pet.getPetName());
            pst.setString(3, pet.getSpecies());
            pst.setString(4, pet.getBreed());
            pst.setInt(5, pet.getAge());
            pst.setInt(6, pet.getPetId());

            return pst.executeUpdate() > 0;
        }
    }
    
     public boolean deletePet(int petId) throws SQLException {

        String sql = "DELETE FROM pets WHERE pet_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, petId);

            return pst.executeUpdate() > 0;
        }
    }

    public List<Pet> searchPets(String keyword) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT pet_id, customer_id, pet_name, species, breed, age FROM pets "
                + "WHERE pet_name LIKE ? OR species LIKE ? OR breed LIKE ? OR CAST(pet_id AS CHAR) LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            String term = "%" + keyword + "%";
            pst.setString(1, term);
            pst.setString(2, term);
            pst.setString(3, term);
            pst.setString(4, term);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Pet pet = new Pet();
                    pet.setPetId(rs.getInt("pet_id"));
                    pet.setCustomerId(rs.getInt("customer_id"));
                    pet.setPetName(rs.getString("pet_name"));
                    pet.setSpecies(rs.getString("species"));
                    pet.setBreed(rs.getString("breed"));
                    pet.setAge(rs.getInt("age"));
                    pets.add(pet);
                }
            }
        }
        return pets;
    }
}
