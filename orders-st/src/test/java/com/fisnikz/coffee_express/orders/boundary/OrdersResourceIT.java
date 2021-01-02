package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.entity.Order;
import com.fisnikz.coffee_express.orders.entity.OrderItem;
import com.fisnikz.coffee_express.orders.control.OrderSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;
import java.net.URI;
import java.util.List;
import java.util.Random;
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
        for (int i = 0; i < 3; i++) {
            Order order = newOrder();
            URI createdOrderLocation = this.orderSystem.placeOrder(order);
            assertNotNull(createdOrderLocation);

            String orderId = createdOrderLocation.toString().substring(createdOrderLocation.toString().lastIndexOf("/"));
            System.out.println("orderId = " + orderId);
//            JsonObject responseOrder = this.orderSystem.getOrder(orderId);
//            assertNotNull(responseOrder);
//            System.out.println(responseOrder);
//            assertEquals("PLACED", responseOrder.getString("order-state"));
//            System.out.println(responseOrder);
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

        List<String> accs = List.of("70d273a8-03ec-11eb-adc1-0242ac120002", "47918cc0-96e9-4bd7-9486-9fbdcc9b147b", "3ce1ce5c-46aa-4ddb-8430-0d2c0d27c7fb");
        String bankAccountId = accs.get(new Random().nextInt(3));

        return new Order("e90770f3-0522-47d3-a9be-c9ef9402326e", "70d273a8-03ec-11eb-adc1-0242ac120002" ,items);
    }
}