package com.zadaca.zadacaprojekt.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


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

    @Column(name= "speed")
    private int speed;

    @Column(name= "weight")
    private int weight;

    @Embedded
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name= "owner_id")
    private Owner owner;

}
