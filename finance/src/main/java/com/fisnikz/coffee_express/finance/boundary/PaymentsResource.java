package com.fisnikz.coffee_express.finance.boundary;

import javax.enterprise.context.Dependent;
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
public class PaymentsResource {

    @GET
    public Response paymentsOfCustomer(@QueryParam("customerId") UUID customerId) {
        return Response.ok().build();
    }
}
