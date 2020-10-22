package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.entity.Order;
import com.fisnikz.coffee_express.orders.entity.OrderItem;
import com.fisnikz.coffee_express.orders.control.OrderSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author airhacks.com
 */
public class OrdersResourceIT {

    private OrderSystem orderSystem;

    @BeforeEach
    void init(){
        this.orderSystem = new OrderSystem();
    }

    @Test
    void placeAndFind() {
        for (int i = 0; i < 1; i++) {
            Order order = newOrder();
            URI createdOrderLocation = this.orderSystem.placeOrder(order);
            assertNotNull(createdOrderLocation);

            String orderId = createdOrderLocation.toString().substring(createdOrderLocation.toString().lastIndexOf("/"));
            JsonObject responseOrder = this.orderSystem.getOrder(orderId);
            assertNotNull(responseOrder);
            System.out.println(responseOrder);
            assertEquals("PLACED", responseOrder.getString("state"));
            System.out.println(responseOrder);
        }
    }

    @Test
    void placeOrderFail() {
        Order order = newOrder();
        order.getItems().get(0).setMenuItemId(-99);

        this.orderSystem.placeInvalidOrder(order);
    }

    Order newOrder(){
        List<OrderItem> items = Stream.of(
                new OrderItem(2, (short) 2),
                new OrderItem(3, (short) 1)
        ).collect(Collectors.toList());

        return new Order("045cf19e-34b9-4d1e-a566-921874129ff0", "70d273a8-03ec-11eb-adc1-0242ac120002",items);
    }
}