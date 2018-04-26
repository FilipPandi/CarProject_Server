package com.zadaca.zadacaprojekt.web;


import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.Owner;
import com.zadaca.zadacaprojekt.dto.CarDTO;
import com.zadaca.zadacaprojekt.service.CarManager;
import com.zadaca.zadacaprojekt.service.OwnerManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/cars"})
@Slf4j
public class CarController {

    private final CarManager carManager;

    private final OwnerManager ownerManager;

    public CarController(CarManager carManager, OwnerManager ownerManager) {
        this.carManager = carManager;
        this.ownerManager = ownerManager;
    }



    @PostMapping("/save")
    public CarDTO save(@RequestBody CarDTO car) {


        Car c = new Car();

        Owner o = ownerManager.getById(car.getOwner().getId());
        c.setOwner(o);



        c.setManufacturer(car.getManufacturer());
        c.setName(car.getName());
        c.setSpeed(car.getSpeed());
        c.setWeight(car.getWeight());
        log.info("got car: {}", c);
        CarDTO saveCar = new CarDTO(carManager.save(c));


        return saveCar;
    }


    @PutMapping("/save/{id}")
    public Car updateCars(@PathVariable("id") Long id, @RequestBody CarDTO car) {

        Car c = carManager.getById(id);
        c.setName(car.getName());
        c.setSpeed(car.getSpeed());
        c.setWeight(car.getWeight());
        c.setManufacturer(car.getManufacturer());

        Owner owner = ownerManager.getById(car.getOwner().getId());
        c.setOwner(owner);

        return carManager.save(c);
    }


    @GetMapping("/{id}")
    public CarDTO findById(@PathVariable("id") Long id) {
        CarDTO carDTO = new CarDTO(carManager.getById(id));
        return carDTO;
    }


    @DeleteMapping({"/{id}"})
    public void deleteCar(@PathVariable("id") Long id) {

        carManager.deleteCar(id);
    }


    @GetMapping("/list")
    public Page<Car> findAllPage(Pageable pageable) {

        return carManager.getAllCarsPage(pageable);
    }
}
