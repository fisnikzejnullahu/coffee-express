package com.fisnikz.coffee_express.barista;

import com.fisnikz.coffee_express.barista.entity.MenuItem;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class BaristasResourceTest {

    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        requestData();
    }

    @Test
    public void testMenuItemCreate() {
        String menuItemJson = this.itemToJson(this.menuItem);
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(menuItemJson)
                .post("/baristas/items");

        assertEquals(201, response.statusCode());
        assertNotNull(response.getHeader("Location"));
        String location = response.getHeader("Location");

        String itemId = location.substring(location.lastIndexOf("/"));

        Response findResponse = given()
                .when()
                .contentType(ContentType.JSON)
                .get("baristas/item/" + itemId);

        assertEquals(200, findResponse.getStatusCode());
        MenuItem menuItem = fromJson(findResponse.body().toString());
        assertEquals(itemId, menuItem.id);
        assertEquals(this.menuItem.price, menuItem.price);
        assertEquals(this.menuItem.name, menuItem.name);
        assertEquals(false, menuItem.removed);
    }

    public void requestData() {
        this.menuItem = new MenuItem("kafe", 0.5);
    }

    private String itemToJson(MenuItem item) {
        return JsonbBuilder.create().toJson(menuItem);
    }

    public MenuItem fromJson(String json) {
        return JsonbBuilder.create()
                .fromJson(json, MenuItem.class);
    }
}