package com.fisnikz.coffee_express;

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
public class ResponseWebApplicationExceptionMapper implements ResponseExceptionMapper<WebApplicationException> {

    @Inject
    Logger LOG;

    @Override
    public WebApplicationException toThrowable(Response response) {
        LOG.log(Logger.Level.ERROR, "Mapping Exception");

        JsonObject body = Json.createObjectBuilder()
                .add("success", false)
                .add("message", "We're facing some technical problems right now. Please try again later!")
                .build();

        return new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(body).build());
    }
}