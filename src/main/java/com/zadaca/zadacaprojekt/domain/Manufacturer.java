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
    @Column(name= "car_model")
    @Enumerated(EnumType.STRING)
    private CarModelEnum carModelType;

    @NotNull
    @Column(name= "manufacturer_enum")
    @Enumerated(EnumType.STRING)
    private ManufacturerEnum manufacturerType;

}
