package com.fisnikz;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author Fisnik Zejnullahu
 */
@Provider
public class LowerCaseWithDashesJsonbNamingStrategy implements ContextResolver<Jsonb> {

    @Override
    public Jsonb getContext(Class type) {
        JsonbConfig config = new JsonbConfig().
                withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_DASHES);
        return JsonbBuilder.newBuilder().
                withConfig(config).
                build();
    }
}
