package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.logging.Logged;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Path("payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Logged
public class PaymentsResource {

    @Inject
    JsonWebToken jsonWebToken;

    @GET
    public Response paymentsOfCustomer(@QueryParam("customerId") UUID customerId) {
        return Response.ok().build();
    }
}
