package com.project.projectservice.projects.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "projects")
public class projects {
    @Id
    private String _id;
    private String name;
    private location coordinates;
    private String location;
    private String ownLocation;
    private String description;
    private String developer;
    private Float priceFrom;
    private Float sizeFrom;
    private List<String> bedrooms;
    private List<String> amenities;
    private String VrUrl;
    private String Url3D;
    private String imageMain;
    private String imageExterior;
    private String company;
    private String handover;
    private String propertyType;
    private String trakheesi;
    private List<String> exteriorPhoto;
    private List<String> interiorPhoto;
    private String completionStatus;
    private Map<String,List<floorPlans>> floorPlans;
    private Map<String,PaymentPlan> paymentPlan;
}
