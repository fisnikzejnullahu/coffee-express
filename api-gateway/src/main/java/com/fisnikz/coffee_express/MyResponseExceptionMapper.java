package com.fisnikz.coffee_express;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.lang.System.Logger;

/**
 * @author Fisnik Zejnullahu
 */
public class MyResponseExceptionMapper implements ResponseExceptionMapper<WebApplicationException> {

    @Inject
    Logger LOG;

    @Override
    public WebApplicationException toThrowable(Response response) {
        LOG.log(Logger.Level.ERROR, "Mapping Exception");
        return new WebApplicationException(response.getStatus());
    }
}