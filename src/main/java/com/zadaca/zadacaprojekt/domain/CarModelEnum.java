package com.zadaca.zadacaprojekt.domain;


import lombok.Getter;

@Getter
public enum CarModelEnum {

    FALCON("FALCON"),
    RCZ("RCZ"),
    R8("R8"),
    X5("X5"),
    ASTRA("ASTRA"),
    GOLF("GOLF");

    private final String name;


    CarModelEnum(String name) {
        this.name = name;
    }
}
