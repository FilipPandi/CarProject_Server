package com.zadaca.zadacaprojekt.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "cars")
public class Cars{


    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(name= "car_seq", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name= "name")
    private String name;

    @Column(name= "speed")
    private int speed;

    @Column(name= "weight")
    private int weight;

    @Embedded
    private Manufacturer manufacturer;
}
