package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.CarRepository;
import com.zadaca.zadacaprojekt.domain.Cars;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Cars> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Cars updateCar(Cars cars) {
        return null;
    }

    @Override
    public Optional<Cars> findOne(Long id) {
        return carRepository.findById(id);
    }
}
