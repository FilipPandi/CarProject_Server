package com.zadaca.zadacaprojekt.web;


import com.zadaca.zadacaprojekt.domain.Cars;
import com.zadaca.zadacaprojekt.service.CarManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/cars"})
public class CarController {


    private final CarManager carManager;

    public CarController(CarManager carManager) {
        this.carManager = carManager;
    }


    @PostMapping("/create")
    public Cars save(@RequestBody Cars cars) {
        return carManager.save(cars);
    }

    @DeleteMapping({"/{id}"})
    public void deleteCar(@PathVariable("id") long id) {
        carManager.deleteCar(id);
    }

    @GetMapping("/create")
    public List<Cars> findAll() {
        return carManager.getAllCars();
    }

    @PutMapping("/create/{id}")
    public Cars updateCars(@PathVariable("id") Long id, @RequestBody Cars cars) {

        cars.setName(cars.getName());
        cars.setSpeed(cars.getSpeed());
        cars.setWeight(cars.getWeight());
        return carManager.save(cars);
    }
}
