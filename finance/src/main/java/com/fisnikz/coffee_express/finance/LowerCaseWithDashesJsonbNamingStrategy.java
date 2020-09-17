package com.fisnikz.coffee_express.finance;

import io.quarkus.jsonb.JsonbConfigCustomizer;

import javax.inject.Singleton;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;

/**
 * @author Fisnik Zejnullahu
 */
@Singleton
public class LowerCaseWithDashesJsonbNamingStrategy implements JsonbConfigCustomizer {
    @Override
    public void customize(JsonbConfig jsonbConfig) {
        jsonbConfig.withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_DASHES);
    }
}