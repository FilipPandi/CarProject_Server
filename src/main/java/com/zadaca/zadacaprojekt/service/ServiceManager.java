package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public interface ServiceManager {

    CarService save(CarService carService);

    List<CarService> getService(Long Id);

    CarService findService(Long id);

    List<CarService> getServicesList();

    Page<CarService> getAll(Pageable pageable);

    List<CarService> getTopTen();

    void deleteServices(Long id);

}
