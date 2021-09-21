package com.fisnikz.coffee_express;

import com.fisnikz.coffee_express.logging.Logged;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.lang.System.Logger;

/**
 * @author Fisnik Zejnullahu
 */
@Logged
public class ResponseWebApplicationExceptionMapper implements ResponseExceptionMapper<WebApplicationException> {

    @Override
    public WebApplicationException toThrowable(Response response) {
        if (response.getStatusInfo().getFamily().equals(Response.Status.Family.SERVER_ERROR)) {

        }
        JsonObject body = Json.createObjectBuilder()
                .add("success", false)
                .add("message", "We're facing some technical problems right now. Please try again later!")
                .build();

        WebApplicationException ex = new WebApplicationException(Response.status(response.getStatus())
                .entity(response.getEntity())
                .build());

//        return new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(body).build());
        return ex;
    }
}