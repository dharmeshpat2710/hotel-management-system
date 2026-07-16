/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/hotel_db";
            String user = "root";    
            String password = "@mokshck2006";   

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to DB ");
            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
