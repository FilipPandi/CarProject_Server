package com.zadaca.zadacaprojekt.kafka.consumer;

import com.zadaca.zadacaprojekt.kafka.MessageStorage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Receiver {
    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    MessageStorage storage;

    @KafkaListener(topics = "${kafka.topic.cars}")
    public void listen(String message) {
        LOG.info("received message='{}'", message);
        storage.put(message);

    }
    @KafkaListener(topics = "${kafka.topic.owners}")
    public void listenOwner(String message) {
        LOG.info("received message='{}'", message);
        storage.put(message);

    }

    @KafkaListener(topics = "Messenger")
    public void receive(String message) {
        LOG.info("received message='{}'", message);
        storage.put(message);
    }


}
