package com.fisnikz.coffee_express.barista.control;

import com.fisnikz.coffee_express.AuthorizationHeaderClientRequestCheck;
import com.fisnikz.coffee_express.MyResponseExceptionMapper;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(configKey = "baristas")
@Path("baristas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterClientHeaders
@RegisterProvider(MyResponseExceptionMapper.class)
@RegisterProvider(AuthorizationHeaderClientRequestCheck.class)
public interface BaristasRestClient {

    @GET
    @Path("items")
    Response getItems();

    @GET
    @Path("items/{id}")
    Response getItemDetails(@PathParam("id") long id);
}
