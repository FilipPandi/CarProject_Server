package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarManager {

    Car save(Car car);

    Page<Car> getAllCarsPage(Pageable pageable);

    List<Car> getAllCarsList();

    List<Car> findAllCarsListById(Long id);

   void deleteCar(Long id);

    Car getById(Long id);

}
