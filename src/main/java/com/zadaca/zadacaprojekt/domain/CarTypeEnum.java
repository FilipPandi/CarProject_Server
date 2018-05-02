package com.zadaca.zadacaprojekt.domain;


import lombok.Getter;

@Getter
public enum CarTypeEnum {

    FORD_FALCON("FORD_FALCON"),
    PEUGEOT_RCZ("PEUGEOT_RCZ"),
    AUDI_R8("AUDI_R8"),
    BMW_X5("BMW_X5"),
    OPEL_ASTRA("OPEL_ASTRA"),
    VOLKSWAGEN_GOLF("VOLKSWAGEN_GOLF");

    private final String name;


    CarTypeEnum(String name) {
        this.name = name;
    }
}
