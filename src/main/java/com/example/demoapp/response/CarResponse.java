package com.example.demoapp.response;

import lombok.Data;

@Data
public class CarResponse {

    private Long id;
    private String name;
    private String model;
    private String color;
    private String plate;
    private String year;

}
