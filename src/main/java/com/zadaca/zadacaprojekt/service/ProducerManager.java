package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.ProducerMessenger;

import java.util.List;

public interface ProducerManager {

    ProducerMessenger save(ProducerMessenger producerMessenger);

    List<ProducerMessenger> findByReceiverId(Long receiverId);
}
