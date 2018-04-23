package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.Cars;
import com.zadaca.zadacaprojekt.domain.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarManager {

    Cars save(Cars car);

    Page<Cars> getAllCarsPage(Pageable pageable);

    List<Cars> getAllCarsList();

   void deleteCar(Long id);

    Cars getById(Long id);
}
