package com.example.diasofttest.service.impl;

import com.example.diasofttest.service.api.AnimalFeignClient;
import com.example.diasofttest.dao.Request;
import com.example.diasofttest.repository.RequestRepository;
import com.example.diasofttest.service.dto.AnimalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements com.example.diasofttest.service.api.AnimalService {

    private final RequestRepository requestRepository;
    private final AnimalFeignClient animalFeignClient;


    @Override
    public Request fetchAndSaveAnimalNames() {
        UUID requestId = UUID.randomUUID();
        ResponseEntity<List<AnimalResponse>> animalsResponse = animalFeignClient.getAnimals();
        int responseStatus = animalsResponse.getStatusCode().value();
        String animalNames = ofNullable(animalsResponse.getBody())
                .stream()
                .flatMap(Collection::stream)
                .map(AnimalResponse::getName)
                .collect(Collectors.joining(","));
            Request request = new Request()
                    .setId(requestId)
                .setResponseStatus(responseStatus)
                .setAnimalNames(animalNames);
        request = requestRepository.save(request);
        log.info("Attempt to fetch animal names, UUID: {}, HTTP Status: {}, Animal Names: {}", requestId, responseStatus, animalNames);
        return request;

    }

}
