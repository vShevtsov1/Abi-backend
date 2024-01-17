package com.project.amenitiesservice.amenities.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "amenity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class amenities {

    @Id
    private String _id;
    private String name;
    private String description;
    private String image;
}
