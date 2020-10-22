package com.fisnikz.coffee_express.orders;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class MyResponseExceptionMapper implements ResponseExceptionMapper {
    @Override
    public Throwable fromResponse(Response response) {
        return null;
    }


}
