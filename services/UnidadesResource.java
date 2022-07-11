/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
//https://tomcat.apache.org/tomcat-7.0-doc/config/filter.html
//http://www.java2s.com/Code/Jar/g/Downloadgson14jar.htm
package services;

import controllers.crudUnidades;
import entitys.Unidades;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        crudUnidades crud = new crudUnidades();
        ArrayList<Unidades> lista = crud.getAllUnidades();
        ArrayList<JSONObject> listResult = getJsonUnidadesList(lista);
        return listResult.toString();
    }

    private ArrayList<JSONObject> getJsonUnidadesList(ArrayList<Unidades> lista) {
        ArrayList<JSONObject> listResult = new ArrayList<JSONObject>();
        for (int i = 0; i < lista.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("idBus", lista.get(i).getIdbus());
            obj.put("fecha_ingreso", lista.get(i).getFecha_ingreso().toString());
            obj.put("modelo", lista.get(i).getModelo());
            obj.put("placa", lista.get(i).getPlaca());
            obj.put("marca", lista.get(i).getMarca());
            obj.put("tipo", lista.get(i).getTipo());
            obj.put("estado", lista.get(i).getActivo());
            listResult.add(obj);

        }
        return listResult;
    }

    @POST
    @Path("addProductos")
    @Produces("APPLICATION/JSON")
    @Consumes("APPLICATION/JSON")
    public String addProducto(String prod) throws ParseException {
        ArrayList<JSONObject> listResult = new ArrayList<JSONObject>();
        Unidades un = getUnidadFromJsonString(prod);
        JOptionPane.showMessageDialog(null, "fecha: " + un.getFecha_ingreso());
        crudUnidades crud = new crudUnidades();
        boolean res = crud.add(un);
        if (res) {
            ArrayList<Unidades> lista = crud.getAllUnidades();
            return getJsonUnidadesList(lista).toString();
        } else {
            return listResult.toString();
        }

    }
    @DELETE
    @Path("deleteProductos")
    @Produces("APPLICATION/JSON")
    @Consumes("APPLICATION/JSON")
    public String deleteProducto(String prod) throws ParseException {
        ArrayList<JSONObject> listResult = new ArrayList<JSONObject>();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(prod);
        Unidades un = new Unidades();
        int idbus = Integer.parseInt(json.get("idBus").toString());
        un.setIdbus(idbus);
        crudUnidades crud = new crudUnidades();
        boolean res = crud.delete(un);
        if (res) {
            JOptionPane.showMessageDialog(null, "deleted");
            ArrayList<Unidades> lista = crud.getAllUnidades();
            return getJsonUnidadesList(lista).toString();
        } else {
            JOptionPane.showMessageDialog(null, "not deleted");
            return listResult.toString();
            
        }

    }

    private Unidades getUnidadFromJsonString(String prod) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(prod);
        Unidades un = new Unidades();
        un.setMarca(json.get("marca").toString());
        int tipo = Integer.parseInt(json.get("tipo").toString());
        int modelo = Integer.parseInt(json.get("modelo").toString());
        int estado = Integer.parseInt(json.get("estado").toString());
        Date date = new Date(json.get("fecha_ingreso").toString());
        un.setTipo(tipo);
        un.setModelo(modelo);
        un.setActivo(estado);
        un.setFecha_ingreso(date);
        un.setPlaca(json.get("placa").toString());
        return un;
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
/**
 * var xhr = new XMLHttpRequest(); xhr.open("POST",
 * "http://localhost:8180/ApiTest01/webresources/unidades/addProductos", true);
 * xhr.setRequestHeader('Content-Type', 'application/json');
 * xhr.send(JSON.stringify( { "marca": "marca02", "tipo": 1, "estado": 1,
 * "idBus": 22, "fecha_ingreso": 2022-07-03, "modelo": 2514, "placa": "kkk" }
 * ));
 *
 const response = await fetch("http://localhost:8180/ApiTest01/webresources/unidades/addProductos", {
method: 'POST',
headers: {
  'Accept': 'application/json',
  'Content-Type': 'application/json'
},
body: JSON.stringify(
    {
        "marca": "marca02",
        "tipo": 1,
        "estado": 1,
        "idBus": 22,
        "fecha_ingreso": "03/07/2022",
        "modelo": 2514,
        "placa": "xxx"
    }
),
});

response.json().then(data => {
  console.log(data);
});
* 
* const response = await fetch("http://localhost:8180/ApiTest01/webresources/unidades/deleteProductos", {
method: 'DELETE',
headers: {
  'Accept': 'application/json',
  'Content-Type': 'application/json'
},
body: JSON.stringify(
    {
        "idBus": 3
    }
),
});

response.json().then(data => {
  console.log(data);
});
 */
