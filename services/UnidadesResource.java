/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
//https://tomcat.apache.org/tomcat-7.0-doc/config/filter.html
package services;

import controllers.crudUnidades;
import entitys.Unidades;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author esdra
 */
@Path("unidades")
public class UnidadesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UnidadesResource
     */
    public UnidadesResource() {
    }

    /**
     * Retrieves representation of an instance of services.UnidadesResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<h1>hola a todos</h1>";
    }

    @GET
    @Path("productos")
    @Produces(MediaType.TEXT_HTML)
    public String getProductos() {

        String result = "<div><h1>Productos</h1>";
        crudUnidades crud = new crudUnidades();
        ArrayList<Unidades> lista = crud.getAllUnidades();
        for (int i = 0; i < lista.size(); i++) {
            result += "<h1>1idBus: " + lista.get(i).getIdbus() + "</h1>";
            //JOptionPane.showMessageDialog(null, "result "+lista.get(i).getIdbus());
        }
        //JOptionPane.showMessageDialog(null, "result");
        result += "</div>";
        return result;
    }

    @GET
    @Path("unidadJson")
    @Produces("APPLICATION/JSON")
    public String getJsonProductos() {
        //https://www.javatpoint.com/java-json-example
        ArrayList<JSONObject> listResult = new ArrayList<JSONObject>();
        String result = "{ unidades:";
        crudUnidades crud = new crudUnidades();
        ArrayList<Unidades> lista = crud.getAllUnidades();
        for (int i = 0; i < lista.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("idBus", lista.get(i).getIdbus());
            obj.put("fecha_ingreso", lista.get(i).getFecha_ingreso());
            obj.put("modelo", lista.get(i).getModelo());
            obj.put("placa", lista.get(i).getPlaca());
            obj.put("marca", lista.get(i).getMarca());
            obj.put("tipo", lista.get(i).getTipo());
            obj.put("estado", lista.get(i).getActivo());
            listResult.add(obj);
        }
        //JOptionPane.showMessageDialog(null, "result");
        result += "}";
        return listResult.toString();
    }

    @POST
    @Path("addProductos")
    @Produces("APPLICATION/JSON")
    //@Consumes("APPLICATION/JSON")
    public String doPost() {
        ArrayList<JSONObject> listResult = new ArrayList<JSONObject>();
        JOptionPane.showMessageDialog(null, "hola");
        return listResult.toString();
    }

    /**
     * PUT method for updating or creating an instance of UnidadesResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    
    public void putHtml(String content) {
        JOptionPane.showMessageDialog(null, content);
    }
}
