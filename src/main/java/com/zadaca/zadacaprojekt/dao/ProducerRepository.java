package com.zadaca.zadacaprojekt.dao;

import com.zadaca.zadacaprojekt.domain.ProducerMessenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<ProducerMessenger, Long> {

    List<ProducerMessenger> findByReceiverId(Long receiverId);
}
