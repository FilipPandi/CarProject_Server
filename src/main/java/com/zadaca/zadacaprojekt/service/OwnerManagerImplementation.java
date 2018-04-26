package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.CarRepository;
import com.zadaca.zadacaprojekt.dao.OwnerRepository;
import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerManagerImplementation implements OwnerManager {

    private final OwnerRepository ownerRepository;
    private final CarRepository carRepository;

    public OwnerManagerImplementation(OwnerRepository ownerRepository, CarRepository carRepository) {
        this.ownerRepository = ownerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Owner save(Owner owner) {

        return ownerRepository.save(owner);
    }

    @Override
    public List<Owner> getAllOwnerList() {
        return ownerRepository.findAll();

    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Page<Owner> getAllOwnerPage(Pageable pageable) {

        return ownerRepository.findAll(pageable);
    }


    @Override
    public void deleteOwner(Long id) {

        carRepository.deleteByOwnerId(id);
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner getById(Long id) {
        return ownerRepository.findById(id).get();
    }
}
