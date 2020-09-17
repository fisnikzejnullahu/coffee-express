package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.AuthorizeCard;
import com.fisnikz.coffee_express.events.entity.CancelOrder;
import com.fisnikz.coffee_express.events.entity.OrderAccepted;
import com.fisnikz.coffee_express.events.entity.OrderPlaced;
import com.fisnikz.coffee_express.orders.entity.Order;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Properties;
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

    public void placeOrder(Order order) {
        eventProducer.publish(new OrderPlaced(order.id, order.customerId), customersQueue);
    }

    public void authorizeCard(Order order) {
        eventProducer.publish(new AuthorizeCard(order.id, order.customerId, order.orderDetails.getTotalOfOrder(), order.paymentInformation), financesQueue);
    }

    public void acceptOrder(Order order) {
        eventProducer.publish(new OrderAccepted(order.id, order.orderDetails.items), baristasQueue);
    }

//  TODO: Maybe publish in a topic that all services consume the cancelorder
    public void cancelOrder(UUID orderId) {
//        eventProducer.publish(new CancelOrder(orderId));
    }
}
