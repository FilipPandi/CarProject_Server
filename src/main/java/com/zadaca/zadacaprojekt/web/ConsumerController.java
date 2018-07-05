package com.zadaca.zadacaprojekt.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadaca.zadacaprojekt.kafka.KafkaTopicFactory;
import com.zadaca.zadacaprojekt.kafka.MessageStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
@Slf4j
public class ConsumerController {

    @Autowired
    MessageStorage storage;

    @Autowired
    KafkaTopicFactory kafkaTopicFactory;

    @Autowired
    private AdminClient adminClinet;

    @GetMapping(value = "/consumer")
    public String getAllRecievedMessage() throws JsonProcessingException {
        String jsonString = "";
        String messages = storage.toString();
        ObjectMapper mapper = new ObjectMapper();
        jsonString = mapper.writeValueAsString(messages);
        storage.clear();
        return jsonString;
    }


    @RequestMapping(value = "/topic")
    public String topicFactory(@RequestBody(required = false) String topicName) {
        String jsonString = "";
        NewTopic topic = null;

        try {
            if (topicName != null) {
            topic = new NewTopic(topicName, 10, (short) 1);
                adminClinet.createTopics(Lists.newArrayList(topic));
                log.warn("Topic {} was created!", topic.name());
            } else {
                log.error("Topic was not created!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            jsonString = mapper.writeValueAsString(topicName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


}

