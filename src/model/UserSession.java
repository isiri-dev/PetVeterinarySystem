/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class UserSession {

    private static String username;
    private static String role;

    private UserSession() {
       
    }

    
    public static void login(String user, String userRole) {
        username = user;
        role = userRole;
    }

   
    public static void clear() {
        username = null;
        role = null;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static boolean isLoggedIn() {
        return username != null && !username.trim().isEmpty();
    }

    
    public static String getWelcomeMessage() {
        if (role != null && role.trim().equalsIgnoreCase("Veterinarian")) {
            return "Welcome, Veterinarian!";
        }
        return "Welcome, Admin!";
    }
}
