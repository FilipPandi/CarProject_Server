package com.zadaca.zadacaprojekt.dao;

import com.zadaca.zadacaprojekt.domain.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Cars, Long> {
}
