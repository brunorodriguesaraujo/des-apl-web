package com.example.demoapp.controller;

import com.example.demoapp.request.CarRequest;
import com.example.demoapp.response.CarResponse;
import com.example.demoapp.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarResponse> getById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CarRequest user) {
        service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody CarRequest user) {
        service.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
