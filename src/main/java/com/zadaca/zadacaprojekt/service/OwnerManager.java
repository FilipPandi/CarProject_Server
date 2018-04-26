package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerManager {

    Owner save(Owner owner);

    List<Owner> getAllOwnerList();

    List<Car> findAllCars();

    Page<Owner> getAllOwnerPage(Pageable pageable);

    void deleteOwner(Long id);

    Owner getById(Long id);
}
