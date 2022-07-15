/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import data.Credentials;
import entitys.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author esdra
 */
public class crudUsuario {

    public Usuario login(String contrasenya, String usuario) {
        Usuario us = new Usuario();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Credentials.getConnector();
            //here sonoo is database name, root is username and password 
            PreparedStatement ps
                    = con.prepareStatement("select "
                            + "persona_idpersona,"
                            + " idusuario,"
                            + " nombreUsuario,"
                            + " contrasenya from usuario where nombreUsuario=? and contrasenya =?");
            ps.setString(1, usuario);
            ps.setString(2, contrasenya);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                us.setIdPersona(rs.getInt(1));
                us.setIdUsuario(rs.getInt(2));
                us.setNombreUsuario(rs.getString(3));
                us.setContrasenya(rs.getString(4));
            }
            con.close();
            return us;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
