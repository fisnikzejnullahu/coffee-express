package com.fisnikz.coffee_express.identity.boundary;

import com.fisnikz.coffee_express.identity.control.IdentityService;
import com.fisnikz.coffee_express.identity.entity.LoginInfo;
import com.fisnikz.coffee_express.logging.Logged;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Logged
public class IdentityResource {

    @Inject
    IdentityService identityService;

    @POST
    @Path("login")
    public Response login(@Valid LoginInfo loginInfo) {
        //validate and return appropriate response
        return identityService.login(loginInfo);
    }

    @POST
    @Path("login/refresh")
    public Response refreshToken(@CookieParam("refresh_token") String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("x-reason", "refresh token is required for this action")
                    .build();
        }
        return identityService.refreshToken(refreshToken);
    }

    @POST
    @Path("logout")
    public Response logout(@CookieParam("refresh_token") String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("reason", "refresh token is required for this action")
                    .build();
        }

        return identityService.logout(refreshToken);
    }

}
