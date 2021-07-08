package com.fisnikz.coffee_express;

import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.lang.System.Logger;

/**
 * @author Fisnik Zejnullahu
 */
@Provider
public class CircuitBreakerOpenExceptionProvider implements ExceptionMapper<CircuitBreakerOpenException> {

    @Inject
    Logger LOG;

    @Override
    public Response toResponse(CircuitBreakerOpenException exception) {
        LOG.log(Logger.Level.ERROR, "Mapping Exception");

        JsonObject body = Json.createObjectBuilder()
                .add("success", false)
                .add("messageeee", "We're facing some technical problems right now. Please try again later!")
                .build();

        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(body).build();
    }
}
