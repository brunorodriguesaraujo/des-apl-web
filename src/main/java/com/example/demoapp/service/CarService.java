package com.example.demoapp.service;

import com.example.demoapp.entity.Car;
import com.example.demoapp.exception.ResourceNotFoundException;
import com.example.demoapp.repository.CarRepository;
import com.example.demoapp.request.CarRequest;
import com.example.demoapp.response.CarResponse;
import com.example.demoapp.utils.ConvertUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repository;

    private final ConvertUtils convertUtils;

    public CarService(CarRepository repository, ConvertUtils convertUtils) {
        this.repository = repository;
        this.convertUtils = convertUtils;
    }

    public List<CarResponse> getAll() {
        List<Car> list = repository.findAll();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Car is empty");
        }
        return (List<CarResponse>)
                convertUtils.convertToListResponse(list, CarResponse.class);
    }

    public CarResponse getById(Long id) {
        return (CarResponse)
                convertUtils.convertEntityToResponse(repository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Car not found")
                ), CarResponse.class);
    }

    public Car save(CarRequest request) {
        if (repository.existsById(request.getId())) {
            throw new ResourceNotFoundException("Id already exists");
        }
        return repository.save((Car) convertUtils.convertRequestToEntity(request, Car.class));
    }


    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Car not found");
        }
    }

    public void update(long id, CarRequest request) {
        Car car = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        var entityUpdate = (Car)
                convertUtils.convertRequestToEntity(request, car.getClass());
        entityUpdate.setId(id);

        repository.save(entityUpdate);
    }

}
