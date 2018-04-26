package com.zadaca.zadacaprojekt.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Manufacturer {


    @NotNull
    private String manufacturerName;


    private String country;


    private int numberOfCars;
}
