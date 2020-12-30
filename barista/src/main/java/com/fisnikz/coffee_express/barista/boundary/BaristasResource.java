package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.barista.control.MenuService;
import com.fisnikz.coffee_express.barista.entity.MenuItem;
import com.fisnikz.coffee_express.logging.Logged;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
@Path("baristas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Logged
public class BaristasResource {

    @Inject
    MenuService menuService;

    @GET
    @Path("items")
    public Response allItems() {
        return Response.ok(MenuItem.listAll()).build();
    }

    @GET
    @Path("items/{id}")
    public Response findItem(@PathParam("id") long id) {
        MenuItem item = MenuItem.findById(id);
        if (item == null) {
            return Response.status(404).build();
        }
        return Response.ok(item).build();
    }

    @POST
    @Path("items/bulk")
    public Response addItems(List<MenuItem> menuItems) {
        this.menuService.addItems(menuItems);
        return Response.ok().build();
    }

    @POST
    @Path("items")
    public Response addItemInMenu(MenuItem item, @Context UriInfo uriInfo) {
        this.menuService.addItem(item);
        return Response.created(uriInfo.getBaseUriBuilder().path(BaristasResource.class, "findItem").build(item.id)).build();
    }

    @DELETE
    @Path("items/{id}")
    @RolesAllowed({"full_access"})
    public Response removeItem(@PathParam("id") long id) {
        int updated = menuService.removeItem(id);

        JsonObjectBuilder response = Json.createObjectBuilder()
                .add("id", id);

        if (updated != 0) {
            response.add("success", true);
        }
        else {
            response.add("success", false);
        }

        return Response.ok(response.build()).build();
    }

}
