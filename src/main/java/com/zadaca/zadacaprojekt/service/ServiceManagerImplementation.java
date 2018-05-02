package com.zadaca.zadacaprojekt.service;


import com.zadaca.zadacaprojekt.dao.ServiceRepository;
import com.zadaca.zadacaprojekt.domain.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceManagerImplementation implements ServiceManager {

    private final ServiceRepository serviceRepository;

    public ServiceManagerImplementation(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public CarService save(CarService carService) {
        return serviceRepository.save(carService);
    }

    @Override
    public List<CarService> getService(Long Id) {
        return serviceRepository.findAllByCarId(Id);
    }

    @Override
    public CarService findService(Long id) {
        return serviceRepository.findById(id).get();
    }

    @Override
    public List<CarService> getServicesList() {
        return serviceRepository.findAll();
    }

    @Override
    public Page<CarService> getAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public List<CarService> getTopTen() {
        return serviceRepository.findTop10ByOrderByServiceDateDesc();
    }

    @Override
    public void deleteServices(Long id) {
        serviceRepository.deleteById(id);
    }

}
