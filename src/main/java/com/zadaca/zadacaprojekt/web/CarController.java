package com.zadaca.zadacaprojekt.web;


import com.zadaca.zadacaprojekt.domain.Cars;
import com.zadaca.zadacaprojekt.service.CarManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/cars"})
public class CarController {

    private final CarManager carManager;

    public CarController(CarManager carManager) {
        this.carManager = carManager;
    }



    @PostMapping("/save")
    public Cars save(@RequestBody Cars cars) {

        Cars car = new Cars(cars.getId(),cars.getName(), cars.getSpeed(), cars.getWeight(), cars.getManufacturer());

        return carManager.save(car);
    }


    @PutMapping("/save/{id}")
    public Cars updateCars(@PathVariable("id") Long id, @RequestBody Cars cars) {


        carManager.getById(id);
        cars.setName(cars.getName());
        cars.setSpeed(cars.getSpeed());
        cars.setWeight(cars.getWeight());
        cars.setManufacturer(cars.getManufacturer());

        return carManager.save(cars);
    }


    @GetMapping("/list/{id}")
    public Cars findById(@PathVariable("id") Long id) {
        return carManager.getById(id);
    }


    @DeleteMapping({"/{id}"})
    public void deleteCar(@PathVariable("id") long id) {
        carManager.deleteCar(id);
    }


    @GetMapping("/list")
    public Page<Cars> findAllPage(Pageable pageable) {
        return carManager.getAllCarsPage(pageable);
    }

    @GetMapping("/list/edit")
    public List<Cars> findAllList() {
        return carManager.getAllCarsList();
    }
}
