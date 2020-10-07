package com.fisnikz.coffee_express.orders;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
public class InvalidMenuItemException extends WebApplicationException {

    public InvalidMenuItemException(String message) {
        super(Response.status(500).header("message", message).build());
    }
}
