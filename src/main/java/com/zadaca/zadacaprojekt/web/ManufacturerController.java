package com.zadaca.zadacaprojekt.web;


import com.zadaca.zadacaprojekt.domain.Manufacturer;
import com.zadaca.zadacaprojekt.service.ManufacturerManager;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/manufacturer"})
public class ManufacturerController {

    private final ManufacturerManager manufacturerManager;


    public ManufacturerController(ManufacturerManager manufacturerManager) {
        this.manufacturerManager = manufacturerManager;
    }

    @PostMapping("/create")
    public Manufacturer save(@RequestBody Manufacturer manufacturer) {
        manufacturer.setManufacturerName("Ferrari");
        manufacturer.setCountry("France");
        manufacturer.setNumberOfCars(260312813);
        return manufacturerManager.save(manufacturer);
    }

    @GetMapping("/create/{id}")
    public List<Manufacturer> getAllManufacturers()  {
        return manufacturerManager.getAllManufacturers();
    }

}
