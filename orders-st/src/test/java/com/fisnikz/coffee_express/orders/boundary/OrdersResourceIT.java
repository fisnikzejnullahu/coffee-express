package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrderSystem;
import com.fisnikz.coffee_express.orders.entity.Order;
import com.fisnikz.coffee_express.orders.entity.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;
import java.net.URI;
import java.time.LocalDate;
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

        return new Order("045cf19e-34b9-4d1e-a566-921874129ff0", items, 1234123412341234L, LocalDate.now().plusYears(2), (short) 312);
    }


}