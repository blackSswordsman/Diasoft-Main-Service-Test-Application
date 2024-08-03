package com.example.diasofttest.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AnimalResponse {
    private String species;
    private String name;
    private int age;
}
