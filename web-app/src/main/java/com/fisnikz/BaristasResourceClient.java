package com.fisnikz;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(baseUri = "http://localhost:8083")
@Path("baristas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BaristasResourceClient {

    @GET
    @Path("items")
    Response getItems();

    @GET
    @Path("items/{id}")
    Response getItemDetails(@PathParam("id") long id);
}
