package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderCommand;
import com.fisnikz.coffee_express.events.entity.OrderEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.StringReader;
import java.lang.System.Logger;

@ApplicationScoped
public class OrderEventJsonbSerializer {

    @Inject
    Logger LOG;

    private final Jsonb JSONB = JsonbBuilder.create();

    public String serialize(final Object event) {

        JsonObject data = Json.createReader(new StringReader(JSONB.toJson(event))).readObject();
        return Json.createObjectBuilder()
                .add("class", event.getClass().getCanonicalName())
                .add("data", data)
                .build()
                .toString();
    }

    public Object deserialize(String body) {
        try {
            final JsonObject jsonObject = Json.createReader(new StringReader(body)).readObject();
            final Class<? extends Object> eventClass = Class.forName(jsonObject.getString("class"));
            return JSONB.fromJson(jsonObject.getJsonObject("data").toString(), eventClass);
        }catch (Exception e) {
            LOG.log(Logger.Level.ERROR, e.getMessage(), e.getCause());
            throw new RuntimeException("Something went wrong");
        }
    }
}
