package com.example.demoapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private String color;
    private String plate;
    private String year;

}
