/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import controllers.crudTipoUnidades;
import controllers.crudUnidades;
import entitys.Unidades;
import entitys.tipoUnidades;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author esdra
 */
@Path("tipoUnidades")
public class TipoUnidadesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TipoUnidadesResource
     */
    public TipoUnidadesResource() {
    }

    /**
     * Retrieves representation of an instance of services.TipoUnidadesResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        return "<h1>hola a todos</h1>";
    }

    /**
     * PUT method for updating or creating an instance of TipoUnidadesResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @GET
    @Path("TipoUnidadesJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonProductos() {
        //https://www.javatpoint.com/java-json-example
        crudTipoUnidades crud = new crudTipoUnidades();
        ArrayList<tipoUnidades> lista = crud.getAllTipoUnidades();
        ArrayList<JSONObject> listResult = getJsonTipoUnidadesList(lista);
        return listResult.toString();
    }

    private ArrayList<JSONObject> getJsonTipoUnidadesList(ArrayList<tipoUnidades> lista) {
        ArrayList<JSONObject> listResult = new ArrayList<JSONObject>();
        for (int i = 0; i < lista.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("idtipo", lista.get(i).getIdtipo());
            obj.put("descripcion", lista.get(i).getDescripcion().toString());
            obj.put("puertas", lista.get(i).getPuertas());
            listResult.add(obj);

        }
        return listResult;
    }
}
