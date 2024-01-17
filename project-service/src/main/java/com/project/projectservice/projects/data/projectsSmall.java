package com.project.projectservice.projects.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "projects-preview")
public class projectsSmall {

    @Id
    private String _id;

    private String name;
    private location coordinates;
    private String developer;
    private Float price;
    private String location;
    private String ownLocation;
    private String handover;
    private String image;
    private String company;

    public projectsSmall(String name, com.project.projectservice.projects.data.location coordinates, String developer, Float price, String location, String ownLocation, String handover, String image, String company) {
        this.name = name;
        this.coordinates = coordinates;
        this.developer = developer;
        this.price = price;
        this.location = location;
        this.ownLocation = ownLocation;
        this.handover = handover;
        this.image = image;
        this.company = company;
    }
}
