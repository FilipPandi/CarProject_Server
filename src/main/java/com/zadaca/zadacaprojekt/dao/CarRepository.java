package com.zadaca.zadacaprojekt.dao;

import com.zadaca.zadacaprojekt.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Transactional
    void deleteByOwnerId(Long ownerId);
    List<Car> findAllById(Long Id);

}
