package com.project.amenitiesservice.amenities.services;

import com.project.amenitiesservice.amenities.data.amenitiesImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class amenitiesService {

    @Autowired
    private amenitiesRepo amenitiesRepo;

    public List<amenitiesImages> getAmenitiesWithImages(List<String> amenities){
        return amenitiesRepo.getAmenitiesWithImages(amenities);
    }
}
