package com.zadaca.zadacaprojekt.web;


import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.Owner;
import com.zadaca.zadacaprojekt.dto.CarDTO;
import com.zadaca.zadacaprojekt.kafka.producer.Sender;
import com.zadaca.zadacaprojekt.kafka.producer.SenderConfig;
import com.zadaca.zadacaprojekt.service.CarManager;
import com.zadaca.zadacaprojekt.service.OwnerManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

//import com.zadaca.zadacaprojekt.kafka.consumer.Receiver;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    private final CarManager carManager;

    private final OwnerManager ownerManager;

//    private final Receiver receiver;

    private final Sender sender;

    private final SenderConfig senderConfig;

    public CarController(CarManager carManager, OwnerManager ownerManager, Sender sender, SenderConfig senderConfig) {
        this.carManager = carManager;
        this.ownerManager = ownerManager;
        this.sender = sender;
        this.senderConfig = senderConfig;
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

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        properties.setProperty("acks", "1");
        properties.setProperty("retries", "3");
        properties.setProperty("linger.ms", "1");

        Producer<String, String> producer = new KafkaProducer<String, String>(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("cars", "3",
                "Car name: " + car.getName() + " | "
                        + "Registration number: " + car.getRegistrationNumber() + " | "
                        + "Car Owner: " + car.getOwner().getFirstName() + " " + car.getOwner().getLastName() + " | "
                        + "Car manufacturer and model: " + car.getManufacturer().getManufacturerType() + " " + car.getManufacturer().getCarModelType() + " | "
                        + "Year of production: " + car.getYearOfProduction());

        producer.send(producerRecord);
        return saveCar;
    }

    @PutMapping("/save/{id}")
    public CarDTO updateCars(@PathVariable("id") Long id, @RequestBody CarDTO car) {
        Car c = carManager.getById(id);

        Owner owner = ownerManager.getById(car.getOwner().getId());
        c.setOwner(owner);

        c.setName(car.getName());
        c.setCarType(car.getCarType());
        c.setRegistrationNumber(car.getRegistrationNumber());
        c.setYearOfProduction(car.getYearOfProduction());
        c.setColor(car.getColor());
        c.setManufacturer(car.getManufacturer());


        CarDTO updateCar = new CarDTO(carManager.save(c));

        sender.send("Car that was updated: " + car.getName() + ". With registration number: " + car.getRegistrationNumber());
        return updateCar;
    }


    @GetMapping("/{id}")
    public CarDTO findById(@PathVariable("id") Long id) {
        CarDTO carDTO = new CarDTO(carManager.getById(id));
        return carDTO;
    }


    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {

        carManager.deleteCarById(id);
    }

    @GetMapping("/list")
    public Page<CarDTO> findAllPage(Pageable pageable) {

        Page<Car> cars = carManager.getAllCarPages(pageable);

        List<CarDTO> carDTOList = cars.getContent()
                .stream()
                .map(CarDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(carDTOList, pageable, cars.getTotalElements());
    }

    @GetMapping("/count")
    public Long getCarCount() {
        return carManager.getCarCount();
    }


}
