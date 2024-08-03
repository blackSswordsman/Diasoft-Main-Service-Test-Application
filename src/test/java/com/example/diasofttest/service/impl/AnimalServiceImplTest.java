package com.example.diasofttest.service.impl;

import com.example.diasofttest.dao.Request;
import com.example.diasofttest.repository.RequestRepository;
import com.example.diasofttest.service.api.AnimalFeignClient;
import com.example.diasofttest.service.dto.AnimalResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {
    @InjectMocks
    private AnimalServiceImpl sut;

    @Mock
    private RequestRepository mockRepository;
    @Mock
    private AnimalFeignClient mockAnimalFeignClient;
    @Mock
    private ResponseEntity<List<AnimalResponse>> mockResponseEntity;


    @Test
    void given_FeignClientSuccessResponse_when_fetchAndSaveAnimalNames_then_SuccessfulResult() {
        // given
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockResponseEntity.getBody()).thenReturn(List.of(createAnimal("webby")
                , createAnimal("berny")));
        when(mockAnimalFeignClient.getAnimals()).thenReturn(mockResponseEntity);
        // возвращает входящий аргумент
        when(mockRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        // when
        Request actualResult = sut.fetchAndSaveAnimalNames();
        //then
        assertNotNull(actualResult);
        assertNotNull(actualResult.getId());
        assertEquals(200, actualResult.getResponseStatus());
        assertEquals("webby,berny", actualResult.getAnimalNames());
    }


    @Test
    void given_FeignClientInternalErrorResponse_when_fetchAndSaveAnimalNames_then_ResultWithInternalError() {
        // given
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);
        when(mockResponseEntity.getBody()).thenReturn(null);
        when(mockAnimalFeignClient.getAnimals()).thenReturn(mockResponseEntity);
        // возвращает входящий аргумент
        when(mockRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        // when
        Request actualResult = sut.fetchAndSaveAnimalNames();
        //then
        assertNotNull(actualResult);
        assertNotNull(actualResult.getId());
        assertEquals(500, actualResult.getResponseStatus());
        assertEquals("", actualResult.getAnimalNames());
    }

    private static AnimalResponse createAnimal(String animalName) {
        return new AnimalResponse("spider", animalName, 10);
    }

}