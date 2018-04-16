package com.zadaca.zadacaprojekt.domain;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "manufacturer")
public class Manufacturer {


    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manufacturer_seq")
    @SequenceGenerator(name = "manufacturer_seq", sequenceName = "manufacturer_sequence", allocationSize = 10)
    private Long id;

    @Column(name= "manufacturer_name")
    private String manufacturerName;

    @Column(name= "country")
    private String country;

    @Column(name= "number_of_cars")
    private int numberOfCars;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Cars cars;




    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
