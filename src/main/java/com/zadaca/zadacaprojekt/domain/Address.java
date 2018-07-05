package com.zadaca.zadacaprojekt.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Address {

    @NotNull
    private String street;

    @NotNull
    private String residenceNumber;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private int zipCode;


    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", residenceNumber='" + residenceNumber + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
