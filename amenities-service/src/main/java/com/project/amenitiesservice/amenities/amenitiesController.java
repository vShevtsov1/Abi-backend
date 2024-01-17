package com.project.amenitiesservice.amenities;

import com.project.amenitiesservice.amenities.services.amenitiesService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/amenities")
public class amenitiesController {

    @Autowired
    private amenitiesService amenitiesService;

    @PostMapping("/get-amenities")
    public ResponseEntity<?> getAmenitiesWithImages(@RequestBody List<String> amenities)
    {
        try {

            return new ResponseEntity<>(amenitiesService.getAmenitiesWithImages(amenities),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
