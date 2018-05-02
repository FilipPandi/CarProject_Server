package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.CarRepository;
import com.zadaca.zadacaprojekt.dao.ServiceRepository;
import com.zadaca.zadacaprojekt.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarManagerImplementation implements CarManager {

    private final CarRepository carRepository;
    private final ServiceRepository serviceRepository;

    public CarManagerImplementation(CarRepository carRepository, ServiceRepository serviceRepository) {

        this.carRepository = carRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Car save(Car car) {

        return carRepository.save(car);
    }

    @Override
    public Page<Car> getAllCarPages(Pageable pageable) {

        return carRepository.findAll(pageable);
    }

    @Override
    public Long getCarCount() {
        return carRepository.count();
    }


    @Override
    public void deleteCarById(Long id) {
        serviceRepository.deleteCarServicesByCarId(id);
        carRepository.deleteById(id);
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id).get();
    }

}
