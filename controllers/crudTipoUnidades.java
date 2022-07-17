/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import data.Credentials;
import entitys.tipoUnidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author esdra
 */
public class crudTipoUnidades {

    public ArrayList<tipoUnidades> getAllTipoUnidades() {
        ArrayList<tipoUnidades> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Credentials.getConnector();
            //here sonoo is database name, root is username and password 
            PreparedStatement ps
                    = con.prepareStatement("select "
                            + "idtipo_unidades,"
                            + " puertas,"
                            + " descripcion from tipo_unidades"
                    );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoUnidades un = new tipoUnidades();
                un.setIdtipo(rs.getInt(1));
                un.setPuertas(rs.getInt(2));
                un.setDescripcion(rs.getString(3));
                list.add(un);
            }
            con.close();
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
