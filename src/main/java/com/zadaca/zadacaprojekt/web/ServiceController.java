package com.zadaca.zadacaprojekt.web;

import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.CarService;
import com.zadaca.zadacaprojekt.dto.CarServiceDTO;
import com.zadaca.zadacaprojekt.service.CarManager;
import com.zadaca.zadacaprojekt.service.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/service"})
public class ServiceController {

    private final ServiceManager serviceManager;
    private final CarManager carManager;

    public ServiceController(ServiceManager serviceManager, CarManager carManager) {
        this.serviceManager = serviceManager;
        this.carManager = carManager;
    }


    @PostMapping("/save")
    public CarServiceDTO saveService(@RequestBody CarServiceDTO carServiceDTO) {

        CarService carService = new CarService();
        Car c = carManager.getById(carServiceDTO.getCar().getId());
        carService.setCar(c);

        carService.setServiceName(carServiceDTO.getServiceName());
        carService.setServiceDate(carServiceDTO.getServiceDate());
        carService.setDescription(carServiceDTO.getDescription());
        carService.setWorkerFirstName(carServiceDTO.getWorkerFirstName());
        carService.setWorkerLastName(carServiceDTO.getWorkerLastName());
        carService.setPrice(carServiceDTO.getPrice());
        carService.setPayed(carServiceDTO.isPayed());


        CarServiceDTO saveCarService = new CarServiceDTO(serviceManager.save(carService));
        return saveCarService;
    }

    @PutMapping("/save/{id}")
    public CarService updateCarService(@PathVariable("id") Long id, @RequestBody CarServiceDTO carServiceDTO) {

        CarService cs = serviceManager.findService(id);

        cs.setServiceName(carServiceDTO.getServiceName());
        cs.setServiceDate(carServiceDTO.getServiceDate());
        cs.setDescription(carServiceDTO.getDescription());
        cs.setWorkerFirstName(carServiceDTO.getWorkerFirstName());
        cs.setWorkerLastName(carServiceDTO.getWorkerLastName());
        cs.setPrice(carServiceDTO.getPrice());
        cs.setPayed(carServiceDTO.isPayed());

        return serviceManager.save(cs);
    }

    @GetMapping("/{id}")
    public List<CarService> getCarServices(@PathVariable("id") Long id) {
        return serviceManager.getService(id);
    }

    @GetMapping("/detail/{id}")
    public CarService getCarService(@PathVariable("id") Long id) {
        return serviceManager.findService(id);
    }

    @DeleteMapping({"/{id}"})
    public void deleteService(@PathVariable("id") Long id) {
        serviceManager.deleteServices(id);
    }

    @GetMapping("/top")
    public List<CarService> top10Services() {
        return serviceManager.getTopTen();
    }
}
