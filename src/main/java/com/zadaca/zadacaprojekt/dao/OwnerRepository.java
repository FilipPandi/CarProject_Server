package com.zadaca.zadacaprojekt.dao;

import com.zadaca.zadacaprojekt.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
