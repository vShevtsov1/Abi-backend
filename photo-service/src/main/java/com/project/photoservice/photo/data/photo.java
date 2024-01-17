package com.project.photoservice.photo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "photo")
public class photo {

    @Id
    private String _id;

    private String photo_url;
    private String photo_preview;

    public photo(String photo_url, String photo_preview) {
        this.photo_url = photo_url;
        this.photo_preview = photo_preview;
    }
}
