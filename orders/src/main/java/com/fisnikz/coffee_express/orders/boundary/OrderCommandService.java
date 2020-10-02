package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.AuthorizeCard;
import com.fisnikz.coffee_express.events.entity.OrderAccepted;
import com.fisnikz.coffee_express.events.entity.OrderCreated;
import com.fisnikz.coffee_express.events.entity.OrderPlaced;
import com.fisnikz.coffee_express.orders.entity.Order;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class OrderCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    @ConfigProperty(name = "customers.queue")
    String customersQueue;

    @Inject
    @ConfigProperty(name = "finances.queue")
    String financesQueue;

    @Inject
    @ConfigProperty(name = "baristas.queue")
    String baristasQueue;

    @Inject
    @ConfigProperty(name = "orderhistory.queue")
    String ordersHistoryQueue;

    @Inject
    System.Logger LOG;

    public void placeOrder(Order order) {
        eventProducer.publish(new OrderCreated(order.id, order.orderDetails, order.customerId, order.bankAccountId), ordersHistoryQueue);
        eventProducer.publish(new OrderPlaced(order.id, order.customerId), customersQueue);
    }

    public void authorizeCard(Order order) {
        eventProducer.publish(new AuthorizeCard(order.id, order.bankAccountId, order.orderDetails.getTotalOfOrder()), financesQueue);
    }

    public void acceptOrder(Order order) {
        eventProducer.publish(new OrderAccepted(order.id, order.orderDetails.items), baristasQueue);
    }

    //  TODO: Maybe publish in a topic that all services consume the cancelorder
    public void cancelOrder(UUID orderId) {
//        eventProducer.publish(new CancelOrder(orderId));
    }
}
