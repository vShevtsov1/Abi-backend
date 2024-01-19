package com.project.projectservice.projects.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class projectNameDTO {

    private String name;
    private String location;
    private projectDTOImages.Photo image;

}
