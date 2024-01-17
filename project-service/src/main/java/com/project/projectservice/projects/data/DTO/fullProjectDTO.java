package com.project.projectservice.projects.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fullProjectDTO {
    private String _id;
    private String name;
    private projectDTOImages.Coordinates coordinates;
    private String location;
    private String ownLocation;
    private String description;
    private String developer;
    private int priceFrom;
    private int sizeFrom;
    private List<String> bedrooms;
    private List<String> amenities;
    private String VrUrl;
    private String Url3D;
    private Photo imageMain;
    private Photo imageExterior;
    private String company;
    private String handover;
    private String propertyType;
    private String trakheesi;
    private List<Photo> interiorPhoto;
    private List<Photo> exteriorPhoto;
    private String completionStatus;
    private Map<String, PaymentPlan> paymentPlan;
    private Map<String, List<FloorPlan>> floorPlan;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Photo {
        private String _id;
        private String photo_url;
        private String photo_preview;



    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentPlan {
        private int totalPrice;
        private List<Stage> stages;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Stage {
        private String name;
        private int percent;
        private int price;

        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
    public static class FloorPlan {
        private String price;
        private String size;
        private Photo imageUrl;





    }

}
