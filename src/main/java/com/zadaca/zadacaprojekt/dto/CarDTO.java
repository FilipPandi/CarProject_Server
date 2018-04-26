package com.zadaca.zadacaprojekt.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.Manufacturer;
import com.zadaca.zadacaprojekt.domain.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private Long id;

    private String name;

    private int speed;

    private int weight;

    private Manufacturer manufacturer;

    private OwnerDTO owner;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.manufacturer = car.getManufacturer();
        this.name = car.getName();
        this.owner = new OwnerDTO(car.getOwner());
        this.speed = car.getSpeed();
        this.weight = car.getWeight();
    }

}

