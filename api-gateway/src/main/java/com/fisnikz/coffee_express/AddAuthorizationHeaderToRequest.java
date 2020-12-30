package com.fisnikz.coffee_express;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Fisnik Zejnullahu
 */
@Provider
@Priority(10)
public class AddAuthorizationHeaderToRequest implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Cookie accessToken = requestContext.getCookies().get("access_token");
        if (accessToken != null && !accessToken.getValue().isEmpty()) {
            requestContext.getHeaders().add("Authorization", "Bearer " + accessToken.getValue());
        }
    }
}
