package com.example.demoapp.service;

import com.example.demoapp.entity.Car;
import com.example.demoapp.exception.EntityNotFoundException;
import com.example.demoapp.repository.CarRepository;
import com.example.demoapp.request.CarRequest;
import com.example.demoapp.response.CarResponse;
import com.example.demoapp.utils.ConvertUtils;
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
        return (List<CarResponse>)
                convertUtils.convertToListResponse(repository.findAll(), CarResponse.class);
    }

    public CarResponse getById(Long id) {
        return (CarResponse)
                convertUtils.convertEntityToResponse(repository.findById(id).get(), CarResponse.class);
    }

    public Car save(CarRequest request) {
        return repository.save((Car) convertUtils.convertRequestToEntity(request, Car.class));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(long id, CarRequest request) {
        Car user = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        var entityUpdate = (Car)
                convertUtils.convertRequestToEntity(request, user.getClass());
        entityUpdate.setId(id);

        repository.save(entityUpdate);
    }

}
