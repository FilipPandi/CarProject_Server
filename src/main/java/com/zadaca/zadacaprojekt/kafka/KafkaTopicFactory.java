package com.zadaca.zadacaprojekt.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaTopicFactory {

    @Bean
    public AdminClient admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                StringUtils.arrayToCommaDelimitedString(new String[]{"localhost:9092"}));
        return AdminClient.create(configs);
    }

}
