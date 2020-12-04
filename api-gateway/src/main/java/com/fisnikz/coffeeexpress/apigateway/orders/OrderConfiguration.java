package com.fisnikz.coffeeexpress.apigateway.orders;

import com.fisnikz.coffeeexpress.apigateway.HeaderRoutePredicateFactory;
import com.fisnikz.coffeeexpress.apigateway.auth.KeycloakService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

/**
 * @author Fisnik Zejnullahu
 */
@Configuration
@EnableConfigurationProperties
public class OrderConfiguration {

    @Bean
    public HeaderRoutePredicateFactory headerRoutePredicateFactory() {
        return new HeaderRoutePredicateFactory();
    }

    @Bean
    public RouteLocator orderProxyRouting(RouteLocatorBuilder builder, HeaderRoutePredicateFactory predicateFactory) {
        return builder.routes()
//                .route(r -> r.path("/orders").and().method("GET").uri("http://localhost:9999/orders"))
                .route(r -> r.path("/orders/**").and().method("GET").uri("http://localhost:9999/orders"))
                .route(r -> r.path("/orders").and().method("PUT").uri("http://localhost:9999/orders"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> loginHandler(KeycloakService keycloakService) {
        return RouterFunctions.route(POST("/login").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), keycloakService::login);
    }

    @Bean
    public RouterFunction<ServerResponse> createNewUserHandler(KeycloakService keycloakService) {
        return RouterFunctions.route(POST("/register").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), keycloakService::createUser);
    }

    @Bean
    public RouterFunction<ServerResponse> refreshToken(KeycloakService keycloakService) {
        return RouterFunctions.route(POST("/refresh/token").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), keycloakService::refreshToken);
    }

    @Bean
    public RouterFunction<ServerResponse> logout(KeycloakService keycloakService) {
        return RouterFunctions.route(POST("/logout").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), keycloakService::logout);
    }
}
