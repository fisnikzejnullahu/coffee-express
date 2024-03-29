package com.fisnikz.coffee_express;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
public class AuthorizationHeaderClientRequestCheck implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) {
//        System.out.println(requestContext.getUri());
//        System.out.println("AuthorizationHeaderClientRequestCheck...");
        String authorization = requestContext.getHeaderString("Authorization");
//        System.out.println(authorization);
//        System.out.println("###############################");
//        System.out.println(requestContext.getHeaderString("X-Authorization"));
//        System.out.println("###############################");
        if (requestContext.getHeaderString("X-Authorization") != null) {
            requestContext.getHeaders().put("Authorization", List.of(requestContext.getHeaderString("X-Authorization")));
        }
        if (authorization == null || authorization.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
