package com.zadaca.zadacaprojekt.web;

import com.zadaca.zadacaprojekt.domain.Car;
import com.zadaca.zadacaprojekt.domain.CarService;
import com.zadaca.zadacaprojekt.dto.CarServiceDTO;
import com.zadaca.zadacaprojekt.service.CarManager;
import com.zadaca.zadacaprojekt.service.ServiceManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public CarServiceDTO updateCarService(@PathVariable("id") Long id, @RequestBody CarServiceDTO carServiceDTO) {

        CarService cs = serviceManager.findService(id);

        cs.setServiceName(carServiceDTO.getServiceName());
        cs.setServiceDate(carServiceDTO.getServiceDate());
        cs.setDescription(carServiceDTO.getDescription());
        cs.setWorkerFirstName(carServiceDTO.getWorkerFirstName());
        cs.setWorkerLastName(carServiceDTO.getWorkerLastName());
        cs.setPrice(carServiceDTO.getPrice());
        cs.setPayed(carServiceDTO.isPayed());

        CarServiceDTO updateCars = new CarServiceDTO(serviceManager.save(cs));
        return  updateCars;

    }

    @GetMapping("/{id}")
    public List<CarServiceDTO> getCarServices(@PathVariable("id") Long id) {

        List<CarService> carServiceList = serviceManager.getService(id);

        List<CarServiceDTO> carServiceDTOList = new ArrayList<>();

        carServiceList.forEach(c -> {
            CarServiceDTO csDTO = new CarServiceDTO(c);
            carServiceDTOList.add(csDTO);
        });
        return carServiceDTOList;
    }

    @GetMapping("/detail/{id}")
    public CarServiceDTO getCarService(@PathVariable("id") Long id) {

        CarServiceDTO carServiceDTO = new CarServiceDTO(serviceManager.findService(id));

        return carServiceDTO;
    }

    @DeleteMapping({"/{id}"})
    public void deleteService(@PathVariable("id") Long id) {
        serviceManager.deleteServices(id);
    }

    @GetMapping("/top")
    public List<CarServiceDTO> top10Services() {

        List<CarService> carService = serviceManager.getTopTen();

        List<CarServiceDTO> carServiceDTOList = new ArrayList<>();

        carService.forEach(cs -> {
            CarServiceDTO csDTO = new CarServiceDTO(cs);
            carServiceDTOList.add(csDTO);
        });
        return carServiceDTOList;
    }
}
