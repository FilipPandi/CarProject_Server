package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.Manufacturer;

import java.util.List;

public interface ManufacturerManager {

   Manufacturer save(Manufacturer manufacturer);

   List<Manufacturer> getAllManufacturers();

}
