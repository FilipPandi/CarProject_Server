package com.zadaca.zadacaprojekt.dao;

import com.zadaca.zadacaprojekt.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository <Manufacturer, Long> {

}
