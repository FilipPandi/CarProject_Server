package com.zadaca.zadacaprojekt.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "cars")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Car {


    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(name= "car_seq", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @NotNull
    @Column(name= "name")
    private String name;

    @Column(name= "car_type")
    @Enumerated(EnumType.STRING)
    private CarTypeEnum carType;

    @Column(name= "year_of_production")
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date yearOfProduction;

    @Column(name= "registration_number")
    private String registrationNumber;

    @Column(name= "color")
    private String color;

    @Embedded
    private Manufacturer manufacturer;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name= "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "car")
    private List<CarService> carServices = new ArrayList<>();
}
