package com.zadaca.zadacaprojekt.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zadaca.zadacaprojekt.domain.Address;
import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Long oib;

    private Address address;

    private List<CarDTO> car = new ArrayList<>();

    public OwnerDTO(Owner owner) {
        this.id = owner.getId();
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
        this.oib = owner.getOib();
        this.address = owner.getAddress();
        owner.getCars().forEach(c -> {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(c.getId());
            carDTO.setName(c.getName());
            carDTO.setManufacturer(c.getManufacturer());
            carDTO.setSpeed(c.getSpeed());
            carDTO.setWeight(c.getWeight());

            car.add(carDTO);
        });
    }
}

