package com.example.diasofttest.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "requests")
@Entity
@Accessors(chain = true)
public class Request {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "response_status")
    private int responseStatus;

    @Column(name = "animal_names")
    private String animalNames;


}
