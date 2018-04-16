package com.zadaca.zadacaprojekt.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "cars")
public class Cars {


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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
