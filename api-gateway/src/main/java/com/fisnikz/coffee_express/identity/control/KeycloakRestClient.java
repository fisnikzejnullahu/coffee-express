package com.fisnikz.coffee_express.identity.control;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(configKey = "keycloak")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface KeycloakRestClient {

    @POST
    @Path("realms/public/protocol/openid-connect/token")
    Response login(@FormParam("username") String username, @FormParam("password") String password,
                   @FormParam("grant_type") String grantType, @FormParam("client_id") String clientId, @FormParam("refresh_token") String refreshToken);

    @POST
    @Path("realms/public/protocol/openid-connect/logout")
    Response logout(@FormParam("refresh_token") String refreshToken, @FormParam("client_id") String clientId);

    @POST
    @Path("admin/realms/public/users")
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(@HeaderParam("Authorization") String authorization, JsonObject body);

    @GET
    @Path("admin/realms/public/users")
    Response findUser(@HeaderParam("Authorization") String authorization, @QueryParam("search") String username);

    @PUT
    @Path("admin/realms/public/users/{accountId}/")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(@HeaderParam("Authorization") String authorization, @PathParam("accountId") String accountId, JsonObject body);
}
