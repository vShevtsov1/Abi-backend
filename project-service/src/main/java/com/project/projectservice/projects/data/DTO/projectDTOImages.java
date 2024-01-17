package com.project.projectservice.projects.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class projectDTOImages {
    private String _id;
    private String name;
    private Coordinates coordinates;
    private String developer;
    private int price;
    private String location;
    private String ownLocation;
    private String handover;
    private String image;
    private String company;
    private List<Photo> photo;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Coordinates {
        private String lng;
        private String lat;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Photo {
        private String photo_url;
        private String photo_preview;

    }
}
