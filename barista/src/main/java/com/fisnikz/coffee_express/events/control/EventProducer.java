package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.lang.System.Logger;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class EventProducer {

    private Producer<String, OrderEvent> kafkaProducer;
    private String topic;

    @Inject
    Properties kafkaProperties;

    @Inject
    Logger LOG;

    @PostConstruct
    public void init(){
        kafkaProperties.put("transactional.id", UUID.randomUUID().toString());
        kafkaProducer = new KafkaProducer<>(kafkaProperties);
        topic = kafkaProperties.getProperty("baristas.topic");
        kafkaProducer.initTransactions();
    }

    public void publish(OrderEvent... events) {
        try {
            kafkaProducer.beginTransaction();
            send(events);
            kafkaProducer.commitTransaction();
        }catch (KafkaException e){
            kafkaProducer.abortTransaction();
            e.printStackTrace();
        }
    }

    private void send(OrderEvent... events) {
        for (OrderEvent event : events) {
            LOG.log(Logger.Level.INFO, "--Publishing: " + event.getClass().getName() + ", data: " + JsonbBuilder.create().toJson(event));
            ProducerRecord<String, OrderEvent> record = new ProducerRecord<>(topic, event);
            this.kafkaProducer.send(record);
        }
    }

}
