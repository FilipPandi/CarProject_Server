package com.zadaca.zadacaprojekt.dao;

import com.zadaca.zadacaprojekt.domain.CarService;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ServiceRepository extends JpaRepository<CarService, Long> {
    @Transactional
    void deleteCarServicesByCarId(Long carId);
    List<CarService> findAllByCarId(Long id);

    List<CarService> findTop10ByOrderByServiceDateDesc();

}
