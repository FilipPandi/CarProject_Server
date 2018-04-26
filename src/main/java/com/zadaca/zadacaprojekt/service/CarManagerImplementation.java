package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.CarRepository;
import com.zadaca.zadacaprojekt.domain.Car;
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
    public Car save(Car car) {

        return carRepository.save(car);
    }

    @Override
    public Page<Car> getAllCarsPage(Pageable pageable) {

        return carRepository.findAll(pageable);
    }

    @Override
    public void deleteCar(Long id) {

        carRepository.deleteById(id);
    }


    @Override
    public List<Car> getAllCarsList() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findAllCarsListById(Long id) {
        return carRepository.findAllById(id);
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id).get();
    }

}
