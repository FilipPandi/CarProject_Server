package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.ProducerMessenger;

import java.util.List;

public interface ProducerManager {

    ProducerMessenger save(ProducerMessenger producerMessenger);

    List<ProducerMessenger> findByReceiver(Long receiverId);

    List<ProducerMessenger> findMessages(Long senderId, Long receiverId);
}
