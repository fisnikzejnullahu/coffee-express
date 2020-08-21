package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.DomainEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderEventSerializer implements Serializer<DomainEvent> {
    @Override
    public byte[] serialize(String s, DomainEvent event) {
        try {
            if (event == null)
                return null;

            /*
            ---- MAYBE ISSUE WITH JSON-B, THROWING EXCEPTION

            final JsonbConfig config = new JsonbConfig()
                    .withAdapters(new UUIDAdapter())
                    .withSerializers(new EventJsonbSerializer());

            */

            String json = JsonbBuilder.create().toJson(event);

            JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();
            byte[] bytes = Json.createObjectBuilder()
                    .add("class", event.getClass().getCanonicalName())
                    .add("data", jsonObject)
                    .build()
                    .toString().getBytes(StandardCharsets.UTF_8);

            return bytes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SerializationException("Could not serialize event");
        }
    }
}
