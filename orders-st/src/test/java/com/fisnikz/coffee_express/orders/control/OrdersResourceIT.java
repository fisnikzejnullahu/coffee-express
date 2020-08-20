package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.boundary.OrderSystem;
import com.fisnikz.coffee_express.orders.entity.Order;
import com.fisnikz.coffee_express.orders.entity.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;
import java.net.URI;
import java.util.List;
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
    public void init(){
        this.orderSystem = new OrderSystem();
    }

    @Test
    public void placeAndFind() {
        Order order = newOrder();
        URI createdOrderLocation = this.orderSystem.placeOrder(order);
        assertNotNull(createdOrderLocation);

        String orderId = createdOrderLocation.toString().substring(createdOrderLocation.toString().lastIndexOf("/"));
        System.out.println(orderId);
        JsonObject responseOrder = this.orderSystem.getOrder(orderId);
        assertNotNull(responseOrder);
        System.out.println(responseOrder);
    }

    Order newOrder(){
        List<OrderItem> items = Stream.of(
                new OrderItem(1, "espresso", 0.7d, (short) 2),
                new OrderItem(2, "machiato", 0.5d, (short) 1)
        ).collect(Collectors.toList());

        return new Order("1d95d653-762e-45c5-bac3-bd1717492cd3", items);
    }


}