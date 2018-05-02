package com.zadaca.zadacaprojekt.domain;


import lombok.Getter;

@Getter
public enum ManufacturerEnum {


    FORD("FORD"),
    PEUGEOT("PEUGEOT"),
    AUDI("AUDI"),
    BMW("BMW"),
    OPEL("OPEL"),
    VOLKSWAGEN("VOLKSWAGEN");

    private final String name;


    ManufacturerEnum(String name) {
        this.name = name;
    }
}
