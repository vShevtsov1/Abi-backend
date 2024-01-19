package com.project.projectservice.projects.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class shareDTO {

    private String _id;
    private String name;
    private String developer;
    private projectDTOImages.Photo image;
}
