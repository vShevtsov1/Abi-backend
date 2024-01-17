package com.project.savedservice.savedProjects.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userSavedProjectsDTO {
    private String _id;
    private String userId;
    private List<Project> savedProjects;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Project{
        private String _id;
        private String name;
        private Coordinates coordinates;
        private String developer;
        private Integer price;
        private String location;
        private String ownLocation;
        private String handover;
        private Image image;
        private String company;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {
        private String _id;
        private String photo_url;
        private String photo_preview;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Coordinates {
        private String lng;
        private String lat;
    }

}
