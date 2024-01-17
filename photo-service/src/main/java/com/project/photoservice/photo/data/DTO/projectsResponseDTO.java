package com.project.photoservice.photo.data.DTO;

import com.project.projectservice.projects.data.projects;
import com.project.projectservice.projects.data.projectsSmall;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class projectsResponseDTO {
private projects projects;
private projectsSmall projectsSmall;

}
