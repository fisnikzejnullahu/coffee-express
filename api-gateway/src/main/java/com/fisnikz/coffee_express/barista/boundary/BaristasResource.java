package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.barista.control.BaristasRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@Path("baristas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BaristasResource {

    @Inject
    @RestClient
    BaristasRestClient baristasRestClient;

    @GET
    @Path("items")
    public Response getMenu() {
        return baristasRestClient.getItems();
    }

    @GET
    @Path("items/{id}")
    public Response getMenu(@PathParam("id") long id) {
        return baristasRestClient.getItemDetails(id);
    }
}
