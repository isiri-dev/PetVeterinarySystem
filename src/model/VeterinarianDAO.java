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

public class VeterinarianDAO {
        
    public boolean addVeterinarian(Veterinarian vet) throws SQLException {

        String sql = "INSERT INTO veterinarians "
                + "(full_name, phone, specialization) "
                + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, vet.getName());
            pst.setString(2, vet.getPhone());
            pst.setString(3, vet.getSpecialization());

            return pst.executeUpdate() > 0;
        }
    }
    
     public List<Veterinarian> getAllVeterinarians() throws SQLException {

        List<Veterinarian> veterinarians = new ArrayList<>();

        String sql = "SELECT * FROM veterinarians";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Veterinarian vet = new Veterinarian();

                vet.setVetId(rs.getInt("vet_id"));
                vet.setName(rs.getString("full_name"));
                vet.setPhone(rs.getString("phone"));
                vet.setSpecialization(rs.getString("specialization"));

                veterinarians.add(vet);
            }
        }

        return veterinarians;
    }
     
    public boolean updateVeterinarian(Veterinarian vet) throws SQLException {

        String sql = "UPDATE veterinarians "
                + "SET full_name = ?, phone = ?, specialization = ? "
                + "WHERE vet_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, vet.getName());
            pst.setString(2, vet.getPhone());
            pst.setString(3, vet.getSpecialization());
            pst.setInt(4, vet.getVetId());

            return pst.executeUpdate() > 0;
        }
    }
    
     public boolean deleteVeterinarian(int vetId) throws SQLException {

        String sql = "DELETE FROM veterinarians WHERE vet_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, vetId);

            return pst.executeUpdate() > 0;
        }
    }
}
