package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.DomainEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import java.io.ByteArrayInputStream;

public class OrderEventDeserializer implements Deserializer<DomainEvent> {

    @Override
    public DomainEvent deserialize(final String topic, final byte[] data) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(data)) {
            final JsonObject jsonObject = Json.createReader(input).readObject();
            final Class<? extends DomainEvent> eventClass = (Class<? extends DomainEvent>) Class.forName(jsonObject.getString("class"));
            return JsonbBuilder.create().fromJson(jsonObject.getJsonObject("data").toString(), eventClass);
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize event", e);
        }
    }

}
