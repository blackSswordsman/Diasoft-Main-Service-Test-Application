package com.example.diasofttest.controller;

import com.example.diasofttest.dao.Request;
import com.example.diasofttest.service.impl.AnimalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class AnimalController {

    private final AnimalServiceImpl animalFeignClientService;

    @GetMapping("/fetch-animal-names")
    public Request fetchAnimalNames() {
        return animalFeignClientService.fetchAndSaveAnimalNames();
    }



}
