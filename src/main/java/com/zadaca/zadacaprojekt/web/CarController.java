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
        c.setCarType(car.getCarType());
        c.setRegistrationNumber(car.getRegistrationNumber());
        c.setYearOfProduction(car.getYearOfProduction());
        c.setColor(car.getColor());
        log.info("got car: {}", c);
        CarDTO saveCar = new CarDTO(carManager.save(c));


        return saveCar;
    }


    @PutMapping("/save/{id}")
    public Car updateCars(@PathVariable("id") Long id, @RequestBody CarDTO car) {

        Car c = carManager.getById(id);
        c.setName(car.getName());
        c.setCarType(car.getCarType());
        c.setRegistrationNumber(car.getRegistrationNumber());
        c.setYearOfProduction(car.getYearOfProduction());
        c.setColor(car.getColor());
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

        carManager.deleteCarById(id);
    }

    @GetMapping("/list")
    public Page<Car> findAllPage(Pageable pageable) {

        return carManager.getAllCarPages(pageable);
    }


    @GetMapping("/count")
    public Long getCarCount() {
        return carManager.getCarCount();
    }


}
