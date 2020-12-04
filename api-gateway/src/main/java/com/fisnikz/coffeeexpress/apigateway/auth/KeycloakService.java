package com.fisnikz.coffeeexpress.apigateway.auth;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.time.Duration;
import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
@Component
public class KeycloakService {

    WebClient.Builder webClientBuilder;

    @Autowired
    public KeycloakService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(String.class).map(data -> Json.createReader(new StringReader(data)).readObject())
                .flatMap(jsonObject -> {
                    Keycloak keycloak = KeycloakClient.getAdminKeycloak();
                    UserRepresentation userRepresentation = new UserRepresentation();

                    CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
                    credentialRepresentation.setTemporary(false);
                    credentialRepresentation.setType("password");
                    credentialRepresentation.setValue(jsonObject.getString("password"));

                    userRepresentation.setCredentials(List.of(credentialRepresentation));

                    userRepresentation.setFirstName(jsonObject.getString("firstName"));
                    userRepresentation.setLastName(jsonObject.getString("lastName"));
                    userRepresentation.setUsername(jsonObject.getString("username"));
                    userRepresentation.setEmail(jsonObject.getString("email"));
                    userRepresentation.setEmailVerified(true);
                    userRepresentation.setEnabled(true);

                    userRepresentation.setRealmRoles(List.of("user"));

                    UsersResource users = keycloak.realm("public").users();

                    Response response = users.create(userRepresentation);

                    ServerResponse.BodyBuilder responseBuilder = ServerResponse.status(response.getStatus());
                    if (response.getStatus() == 201) {
                        return responseBuilder.header("Location", response.getLocation().toString()).build();
                    } else if (response.getStatus() == 409) {
                        return responseBuilder.contentType(MediaType.APPLICATION_JSON).bodyValue(response.readEntity(String.class));
                    } else {
                        return responseBuilder.build();
                    }
                });

    }

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(String.class).map(data -> Json.createReader(new StringReader(data)).readObject())
                .doOnSuccess(jsonObject -> {
                    System.out.println(jsonObject.containsKey("password"));
                    boolean containsParams = false;
                    if (jsonObject != null) {
                        if (jsonObject.containsKey("username") && jsonObject.containsKey("password")) {
                            if (jsonObject.getString("username").length() > 2 && jsonObject.getString("password").length() > 2) {
                                containsParams = true;
                            }
                        }
                    }
                    if (!containsParams) {
                        throw new RuntimeException("Username and password are required!");
                    }
                })
                .flatMap(jsonObject -> {
                    ResponseCookie[] tokensCookies = tokensOfUserIntoCookies(jsonObject.getString("username"), jsonObject.getString("password"));
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .cookie(tokensCookies[0])
                            .cookie(tokensCookies[1])
                            .build();
                })
                .onErrorResume(throwable -> {
                    throwable.printStackTrace();
                    if (throwable.getMessage().equals("HTTP 401 Unauthorized")) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and password are required!"));
                });
    }

    private ResponseCookie[] tokensOfUserIntoCookies(String username, String password) {
        Keycloak keycloak = KeycloakClient.getUserKeycloak(username, password);

        TokenManager tokenManager = keycloak.tokenManager();

        return tokensToCookies(tokenManager.getAccessTokenString(), tokenManager.refreshToken().getRefreshToken(), tokenManager.getAccessToken().getExpiresIn(), tokenManager.refreshToken().getExpiresIn());
    }

    private ResponseCookie[] tokensToCookies(String accessToken, String refreshToken, long accessTokenExpiresIn, long refreshTokenExpiresIn) {
        System.out.println("accessTokenExpiresIn = " + accessTokenExpiresIn);
        System.out.println("refreshTokenExpiresIn = " + refreshTokenExpiresIn);
        ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", accessToken)
                .httpOnly(true)
                .path("/")
                .maxAge(accessTokenExpiresIn)
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(refreshTokenExpiresIn)
                .build();

        return new ResponseCookie[]{accessTokenCookie, refreshTokenCookie};
    }

    public Mono<ServerResponse> logout(ServerRequest serverRequest) {
        return revokeSession(serverRequest.cookies().getFirst("refresh_token").getValue())
                .flatMap(revokeSessionResponseData -> {
                    ResponseCookie[] tokensCookies = tokensToCookies("", "", 0, 0);
                    System.out.println(revokeSessionResponseData);
                    System.out.println(revokeSessionResponseData.isEmpty());
                    return revokeSessionResponseData.isEmpty()
                            ? ServerResponse.noContent().cookie(tokensCookies[0]).cookie(tokensCookies[1]).build()
                            : ServerResponse.badRequest().build();
                });
    }

    private Mono<String> revokeSession(String refreshToken) {
        System.out.println(refreshToken);
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:9099/auth/realms/public/protocol/openid-connect/logout")
                .body(BodyInserters.fromFormData("client_id", "coffee-express-admin-api-client")
                        .with("refresh_token", refreshToken))
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.NO_CONTENT)) {
                        return Mono.just("");
                    } else {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
                    }
                });
    }

    public Mono<ServerResponse> refreshToken(ServerRequest serverRequest) {
//        return serverRequest.bodyToMono(MultiValueMap.class)
//                .flatMap(multiValueMap -> {
//                    System.out.println(multiValueMap.get("refresh_token").toString());
//                    return webClientBuilder.build()
//                            .post()
//                            .uri("http://localhost:9099/auth/realms/public/protocol/openid-connect/token")
//                            .body(BodyInserters.fromFormData("grant_type", "refresh_token")
//                                    .with("client_id", "coffee-express-admin-api-client")
//                                    .with("refresh_token", removeBracketsFromParam(multiValueMap.get("refresh_token").toString())))
//                            .retrieve()
//                            .bodyToMono(String.class)
//                            .flatMap(this::tokensFromBody);
//                });

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:9099/auth/realms/public/protocol/openid-connect/token")
                .body(BodyInserters.fromFormData("grant_type", "refresh_token")
                        .with("client_id", "coffee-express-admin-api-client")
                        .with("refresh_token", serverRequest.cookies().getFirst("refresh_token").getValue()))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(this::tokensFromBody);
    }

    private Mono<ServerResponse> tokensFromBody(String body) {
        JsonObject jsonBody = Json.createReader(new StringReader(body)).readObject();
        ResponseCookie[] tokensCookies = tokensOfUserIntoCookies(jsonBody.getString("access_token"), jsonBody.getString("refresh_token"));
        return ServerResponse.ok()
                .cookie(tokensCookies[0])
                .cookie(tokensCookies[1])
                .build();
    }
}
