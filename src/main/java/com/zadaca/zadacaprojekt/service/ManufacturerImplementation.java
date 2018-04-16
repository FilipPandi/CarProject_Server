package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.ManufacturerRepository;
import com.zadaca.zadacaprojekt.domain.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerImplementation implements ManufacturerManager {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerImplementation(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }
}
