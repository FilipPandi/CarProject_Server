package com.zadaca.zadacaprojekt.web;

import com.zadaca.zadacaprojekt.domain.ProducerMessenger;
import com.zadaca.zadacaprojekt.domain.User;
import com.zadaca.zadacaprojekt.service.ProducerManager;
import com.zadaca.zadacaprojekt.service.UserManager;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    private ProducerManager producerManager;

    @Autowired
    private UserManager userManager;

    @RequestMapping("/producer/{id}")
    public void messenger(@RequestBody(required = false) ProducerMessenger messages, @PathVariable Long id) {
        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        properties.setProperty("acks", "1");
        properties.setProperty("retries", "3");
        properties.setProperty("linger.ms", "1");

        Producer<String, String> producer = new KafkaProducer<String, String>(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("Messenger", 1, "1", messages.getMessage());

        producer.send(producerRecord);
        ProducerMessenger prodcerSave = new ProducerMessenger();

        User user = userManager.findById(id);

        prodcerSave.setMessage(messages.getMessage());
        prodcerSave.setUser(user);
        prodcerSave.setReceiverId(messages.getReceiverId());
        producerManager.save(prodcerSave);
    }

    @RequestMapping("/getMessages/{receiverId}")
    public List<ProducerMessenger> getProducerMessages(@PathVariable Long receiverId) {



     return producerManager.findByReceiverId(receiverId);
    }
}
