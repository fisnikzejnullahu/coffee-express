package com.fisnikz.coffeeexpress.apigateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;


/**
 * @author Fisnik Zejnullahu
 */
public class HeaderRoutePredicateFactory extends
        AbstractRoutePredicateFactory<HeaderRoutePredicateFactory.Config> {

    public HeaderRoutePredicateFactory() {
        super(Config.class);
    }

    // ... setup code omitted
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                // ... predicate logic omitted
                exchange.getRequest().getCookies().forEach((key, val) -> {
                    System.out.println(key + ": ");
                    val.stream().forEach(c -> System.out.println(c.getValue()));
                    System.out.println("++");
                });
                return true;
            }
        };
    }

    @Validated
    public static class Config {
        public Config(boolean isGolden, String customerIdCookie) {
            // ... constructor details omitted
        }
        // ...getters/setters omitted
    }
}