package com.project.amenitiesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AmenitiesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmenitiesServiceApplication.class, args);
    }

}
