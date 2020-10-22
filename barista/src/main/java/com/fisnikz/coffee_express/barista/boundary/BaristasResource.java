package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.barista.control.MenuService;
import com.fisnikz.coffee_express.barista.entity.MenuItem;

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
public class BaristasResource {

    @Inject
    MenuService menuService;

    @GET
    @Path("items")
    public List<MenuItem> allItems() {
        return MenuItem.listAll();
    }

    @GET
    @Path("items/{id}")
    public MenuItem findItem(@PathParam("id") long id) {
        return MenuItem.findById(id);
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
