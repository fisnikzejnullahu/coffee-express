package com.fisnikz.coffee_express.identity.entity;

import javax.ws.rs.core.NewCookie;

/**
 * @author Fisnik Zejnullahu
 */
public class Token {
    private String tokenString;
    private TokenType tokenType;
    private int expiresIn;

    public Token(String tokenString, TokenType tokenType, long expiresIn) {
        this.tokenString = tokenString;
        this.tokenType = tokenType;
        this.expiresIn = (int) expiresIn;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public NewCookie toCookie() {
        return new NewCookie(tokenType.name().toLowerCase(), tokenString, "/", null, null, expiresIn, false, true);
    }

    public enum TokenType {
        ACCESS_TOKEN,
        REFRESH_TOKEN
    }
}
