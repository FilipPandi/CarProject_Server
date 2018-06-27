package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.OwnerRepository;
import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OwnerManagerImplementation implements OwnerManager {

    private final OwnerRepository ownerRepository;

    private final CarManager carManager;


    public OwnerManagerImplementation(OwnerRepository ownerRepository, CarManager carManager) {
        this.ownerRepository = ownerRepository;
        this.carManager = carManager;
    }

    @Override
    public Owner save(Owner owner) {

        return ownerRepository.save(owner);
    }


    @Override
    public Page<Owner> getAllOwnerPages(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }


    @Override
    public void deleteOwner(Long id) {
        Owner owner = getById(id);
        for (Car car : owner.getCars()) {
            carManager.deleteCarById(car.getId());
        }
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner getById(Long id) {
        return ownerRepository.findById(id).get();
    }

    @Override
    public Long getOwnerCount() {
        return ownerRepository.count();
    }
}
