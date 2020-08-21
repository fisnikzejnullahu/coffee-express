package com.fisnikz.coffee_express.events.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class KafkaConfig {

    private Properties kafkaProperties;

    @PostConstruct
    public void init() {
        try {
            kafkaProperties = new Properties();
            kafkaProperties.load(KafkaConfig.class.getResourceAsStream("/kafka.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Produces
    public Properties getKafkaProperties() {
        return this.kafkaProperties;
    }


}
