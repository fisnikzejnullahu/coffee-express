package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderEvent;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class EventJsonbSerializer implements JsonbSerializer<OrderEvent> {

    @Override
    public void serialize(final OrderEvent event, final JsonGenerator generator, final SerializationContext ctx) {
        generator.writeStartObject();
//        generator.write("class", event.getClass().getCanonicalName());
        ctx.serialize(event.getClass().getCanonicalName(), event, generator);
//        generator.writeEnd();
//        generator.close();
    }

}
