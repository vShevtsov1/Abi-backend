package com.project.amenitiesservice.amenities.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class amenitiesImages {

    private String _id;
    private String name;
    private String description;
    private ImageDetails image;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageDetails {
        private String _id;
        private String photo_url;
        private String photo_preview;
    }
}
