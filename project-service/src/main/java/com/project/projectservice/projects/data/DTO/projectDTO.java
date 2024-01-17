package com.project.projectservice.projects.data.DTO;

import com.project.projectservice.projects.data.PaymentPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.LazyMap;
import org.springframework.web.multipart.MultipartFile;
import com.project.projectservice.projects.data.location;
import com.project.projectservice.projects.data.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor

public class projectDTO {
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
    private MultipartFile imageMain;
    private MultipartFile imageExterior;
    private String handover;
    private String propertyType;
    private String trakheesi;
    private List<MultipartFile> exteriorPhoto;
    private List<MultipartFile> interiorPhoto;
    private String completionStatus;
    private Map<String, List<floorPlansDTO>> floorPlans = new HashMap<>();
    private Map<String, PaymentPlan> paymentPlan;

    public projectDTO() {
        floorPlans.put("Studio",new ArrayList<>());
        floorPlans.put("1",new ArrayList<>());
        floorPlans.put("2",new ArrayList<>());
        floorPlans.put("3",new ArrayList<>());
        floorPlans.put("4",new ArrayList<>());
        floorPlans.put("5",new ArrayList<>());
        floorPlans.put("6",new ArrayList<>());
        floorPlans.put("7", new ArrayList<>());
    }
}
