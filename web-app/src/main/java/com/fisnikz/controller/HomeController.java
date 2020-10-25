package com.fisnikz.controller;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Fisnik Zejnullahu
 */
@Controller
@Path("home")
public class HomeController {

    @Inject
    Models models;

    @GET
    @View("home.jsp")
    public void home() {
        models.put("current", "home");
    }
}
