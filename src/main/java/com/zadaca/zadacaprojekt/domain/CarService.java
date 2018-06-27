package com.zadaca.zadacaprojekt.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq")
    @SequenceGenerator(name = "service_seq", allocationSize = 10)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @NotNull
    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_date")
    private Instant serviceDate;

    @Column(name = "worker_first_name")
    private String workerFirstName;

    @Column(name = "worker_last_name")
    private String workerLastName;


    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "payed")
    private boolean payed = false;

    @ManyToOne()
    @JoinColumn(name = "carId")
    private Car car;
}
