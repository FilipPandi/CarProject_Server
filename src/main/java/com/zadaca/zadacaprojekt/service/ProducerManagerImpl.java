package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.ProducerRepository;
import com.zadaca.zadacaprojekt.domain.ProducerMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
    public List<ProducerMessenger> findByReceiver(Long receiverId) {
        return producerRepository.findByReceiverIdOrderByMessageTimeStampDesc(receiverId);
    }

    @Override
    public List<ProducerMessenger> findMessages(Long senderId, Long receiverId) {

        List<ProducerMessenger> messages1 = producerRepository.findByUser_IdAndReceiverIdOrderByMessageTimeStampDesc(senderId, receiverId);
        List<ProducerMessenger> messages2 = producerRepository.findByUser_IdAndReceiverIdOrderByMessageTimeStampDesc(receiverId, senderId);
        List<ProducerMessenger> allMessages = new ArrayList<>();

        allMessages.addAll(messages1);
        allMessages.addAll(messages2);
        allMessages.sort(Comparator.comparing(ProducerMessenger::getMessageTimeStamp).reversed());

        return allMessages;
    }
}
