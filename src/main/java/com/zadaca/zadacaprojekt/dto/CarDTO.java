package com.zadaca.zadacaprojekt.dto;

import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.CarTypeEnum;
import com.zadaca.zadacaprojekt.domain.Manufacturer;
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
public class CarDTO {

    private Long id;

    private String name;

    private CarTypeEnum carType;

    private String registrationNumber;

    private int yearOfProduction;

    private String color;

    private Manufacturer manufacturer;

    private OwnerDTO owner;

    private List<CarServiceDTO> carServices = new ArrayList<>();

    public CarDTO(Car car) {
        this.id = car.getId();
        this.manufacturer = car.getManufacturer();
        this.name = car.getName();
        this.carType = car.getCarType();
        this.owner = new OwnerDTO(car.getOwner());
        this.registrationNumber = car.getRegistrationNumber();
        this.color = car.getColor();
        this.yearOfProduction = car.getYearOfProduction();
        car.getCarServices().forEach(cs -> {
            CarServiceDTO carServiceDTO = new CarServiceDTO();
            carServiceDTO.setId(cs.getId());
            carServiceDTO.setServiceName(cs.getServiceName());
            carServiceDTO.setServiceDate(cs.getServiceDate());
            carServiceDTO.setDescription(cs.getDescription());
            carServiceDTO.setPrice(cs.getPrice());
            carServiceDTO.setPayed(cs.isPayed());

            carServices.add(carServiceDTO);
        });

    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carType=" + carType +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", color='" + color + '\'' +
                ", manufacturer=" + manufacturer +
                ", owner=" + owner +
                ", carServices=" + carServices +
                '}';
    }
}

