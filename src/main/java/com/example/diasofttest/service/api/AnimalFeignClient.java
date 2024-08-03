package com.example.diasofttest.service.api;

import com.example.diasofttest.service.dto.AnimalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "animal-service", url = "${animal-server-url}/animals")
public interface AnimalFeignClient {
    @GetMapping
    ResponseEntity<List<AnimalResponse>> getAnimals();
}
