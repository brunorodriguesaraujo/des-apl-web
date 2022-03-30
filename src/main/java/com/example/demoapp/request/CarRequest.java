package com.example.demoapp.request;

import lombok.Data;

@Data
public class CarRequest {

    private Long id;
    private String name;
    private String model;
    private String color;
    private String plate;
    private String year;
}
