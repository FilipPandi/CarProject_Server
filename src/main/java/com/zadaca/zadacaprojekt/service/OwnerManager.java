package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OwnerManager {

    Owner save(Owner owner);

    Page<Owner> getAllOwnerPages(Pageable pageable);

    void deleteOwner(Long id);

    Owner getById(Long id);

    Long getOwnerCount();
}
