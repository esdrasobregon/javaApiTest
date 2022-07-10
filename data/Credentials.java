/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author esdra
 */
public class Credentials {
 public static Connection getConnector() {
        String contrasenya = "esnija103";
        String db = "prueba";
        String dbUser = "root";
        String url = "jdbc:mysql://localhost:3306/";
        try {
            Connection con = DriverManager.getConnection(
                    url + db,
                    dbUser,
                    contrasenya);
            return con;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }
   
}
