package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.entity.Order;

import javax.json.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import java.io.StringReader;

/**
 * @author Fisnik Zejnullahu
 */
public class RequestJsonBuilder {

    public JsonObject toJson(Order order) {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        String items = fromJsonB(order.getItems());
        JsonArray jsonItems = Json.createReader(new StringReader(items)).readArray();

        return builder.add("bank_account_id", order.getBankAccountId())
                .add("items", jsonItems)
                .build();
    }

    String fromJsonB(Object object){
        JsonbConfig jsonbConfig = new JsonbConfig();
        jsonbConfig.withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);

        Jsonb jsonb = JsonbBuilder.newBuilder()
                .withConfig(jsonbConfig)
                .build();

        return jsonb.toJson(object);
    }
}
