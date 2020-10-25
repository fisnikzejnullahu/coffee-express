package com.fisnikz.controller;

import com.fisnikz.BaristasResourceClient;
import com.fisnikz.model.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.json.bind.JsonbBuilder;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Path("menu")
public class MenuController {

  @Inject
  Models models;

  @RestClient
  @Inject
  BaristasResourceClient baristasResourceClient;

  @GET
  @View("menu.jsp")
  public void index() {
    models.put("current", "menu");
    models.put("items", getItems());
  }

  @GET
  @Path("/details/{id}")
  @View("menu-item.jsp")
  public void details(@PathParam("id") long id) {
    models.put("item", getItem(id));
  }

  private MenuItem getItem(long id) {
    Response itemDetails = baristasResourceClient.getItemDetails(id);
    return toMenuItem(itemDetails.readEntity(JsonValue.class));
  }

  private List<MenuItem> getItems() {
    Response response = baristasResourceClient.getItems();
    return response.readEntity(JsonArray.class).stream().map(this::toMenuItem).collect(Collectors.toList());
  }

  private MenuItem toMenuItem(JsonValue jsonValue) {
    return JsonbBuilder.create().fromJson(jsonValue.toString(), MenuItem.class);
  }

}
