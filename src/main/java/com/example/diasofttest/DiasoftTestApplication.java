package com.example.diasofttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class DiasoftTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiasoftTestApplication.class, args);
    }

}
