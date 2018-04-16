package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.Cars;

import java.util.List;
import java.util.Optional;

public interface CarManager {

    Cars save(Cars car);

    List<Cars> getAllCars();

   void deleteCar(Long id);

    Cars updateCar(Cars cars);

    Optional<Cars> findOne(Long id);
}
