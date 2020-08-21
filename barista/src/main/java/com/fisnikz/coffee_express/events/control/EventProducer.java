package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.DomainEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class EventProducer {

    private Producer<String, DomainEvent> kafkaProducer;
    private String topic;

    @Inject
    Properties kafkaProperties;

    @PostConstruct
    public void init(){
        kafkaProperties.put("transactional.id", UUID.randomUUID().toString());
        kafkaProducer = new KafkaProducer<>(kafkaProperties);
        topic = kafkaProperties.getProperty("baristas.topic");
        kafkaProducer.initTransactions();
    }

    public void publish(DomainEvent... events) {
        try {
            kafkaProducer.beginTransaction();
            send(events);
            kafkaProducer.commitTransaction();
        }catch (KafkaException e){
            kafkaProducer.abortTransaction();
            e.printStackTrace();
        }
    }

    private void send(DomainEvent... events) {
        for (DomainEvent event : events) {
            ProducerRecord<String, DomainEvent> record = new ProducerRecord<>(topic, event);
            this.kafkaProducer.send(record);
        }
    }

}
