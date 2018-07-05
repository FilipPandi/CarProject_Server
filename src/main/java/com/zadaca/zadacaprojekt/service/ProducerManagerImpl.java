package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.ProducerRepository;
import com.zadaca.zadacaprojekt.domain.ProducerMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerManagerImpl implements ProducerManager {

    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public ProducerMessenger save(ProducerMessenger producerMessenger) {
        return producerRepository.save(producerMessenger);
    }

    @Override
    public List<ProducerMessenger> findByReceiverId(Long receiverId) {
        return producerRepository.findByReceiverId(receiverId);
    }
}
