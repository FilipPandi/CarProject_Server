package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.CarRepository;
import com.zadaca.zadacaprojekt.domain.Cars;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarManagerImplementation implements CarManager {

    private final CarRepository carRepository;

    public CarManagerImplementation(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Cars save(Cars car) {
        return carRepository.save(car);
    }

    @Override
    public Page<Cars> getAllCarsPage(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Cars> getAllCarsList() {
        return carRepository.findAll();
    }

    @Override
    public Cars getById(Long id) {
        return carRepository.findById(id).get();
    }
}
