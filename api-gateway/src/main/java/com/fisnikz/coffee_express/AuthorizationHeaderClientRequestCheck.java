package com.fisnikz.coffee_express;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
public class AuthorizationHeaderClientRequestCheck implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) {
        System.out.println("AuthorizationHeaderClientRequestCheck...");
        String authorization = requestContext.getHeaderString("Authorization");
        System.out.println(authorization);
        if (authorization == null || authorization.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
