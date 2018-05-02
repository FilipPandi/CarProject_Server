package com.zadaca.zadacaprojekt.domain;

import com.fasterxml.jackson.annotation.*;
import com.zadaca.zadacaprojekt.dto.CarDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owners")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq")
    @SequenceGenerator(name = "owner_seq", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "OIB")
    private Long oib;

    @Embedded
    private Address address;

    @JsonBackReference
    @OneToMany(mappedBy = "owner")
    List<Car> cars = new ArrayList<>();

}

