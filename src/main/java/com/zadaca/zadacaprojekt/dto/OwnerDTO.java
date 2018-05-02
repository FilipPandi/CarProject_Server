package com.zadaca.zadacaprojekt.dto;

import com.zadaca.zadacaprojekt.domain.Address;
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
            carDTO.setRegistrationNumber(c.getRegistrationNumber());
            carDTO.setYearOfProduction(c.getYearOfProduction());
            carDTO.setColor(c.getColor());
            carDTO.setCarType(c.getCarType());

            car.add(carDTO);
        });
    }
}

