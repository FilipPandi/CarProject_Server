package com.zadaca.zadacaprojekt.dto;

import com.zadaca.zadacaprojekt.domain.CarService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarServiceDTO {

    private Long id;

    private String serviceName;

    private Date serviceDate;

    private String description;

    private String workerFirstName;

    private String workerLastName;

    private int price;

    private boolean payed = false;

    private CarDTO car;

    public CarServiceDTO(CarService carService) {
        this.id = carService.getId();
        this.serviceName = carService.getServiceName();
        this.serviceDate = carService.getServiceDate();
        this.description = carService.getDescription();
        this.price = carService.getPrice();
        this.payed = carService.isPayed();
        this.setWorkerFirstName(carService.getWorkerFirstName());
        this.setWorkerLastName(carService.getWorkerLastName());
        this.car = new CarDTO(carService.getCar());
    }
}
