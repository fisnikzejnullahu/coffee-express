package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

/**
 * @author Fisnik Zejnullahu
 */
public class EventConsumer implements Runnable {

    private final AtomicBoolean running = new AtomicBoolean();
    private final KafkaConsumer<String, OrderEvent> consumer;
    private final Consumer<OrderEvent> eventConsumer;

    public EventConsumer(Properties kafkaProperties, Consumer<OrderEvent> eventConsumer, String... topics) {
        kafkaProperties.put("group.id", "finances-handler");
        this.eventConsumer = eventConsumer;
        consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(asList(topics));
        this.running.set(true);
    }

    @Override
    public void run() {
        try {
            while (running.get()) {
                consume();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // will wakeup for closing
        } finally {
            consumer.close();
        }
    }

    private void consume() {
        ConsumerRecords<String, OrderEvent> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, OrderEvent> record : records) {
            eventConsumer.accept(record.value());
        }
        consumer.commitSync();
    }

    public void stop() {
        running.set(false);
        consumer.wakeup();
    }
}
