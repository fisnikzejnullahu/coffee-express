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
//        String authorization = requestContext.getHeaderString("Authorization");
//        if (authorization == null || authorization.isEmpty()) {
            System.out.println("FILTER /..///");
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//        }
    }
}
