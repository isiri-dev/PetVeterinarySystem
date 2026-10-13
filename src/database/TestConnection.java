/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package database;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.println("Database connected successfully!");

            con.close();

        } catch (SQLException e) {

            System.out.println("Database connection failed!");

            e.printStackTrace();
        }
    }
}