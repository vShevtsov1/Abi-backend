package com.project.projectservice.projects.data.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.projectservice.projects.data.projects;
import com.project.projectservice.projects.data.projectsSmall;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUploadDTO {
    private projects projects;
    private projectsSmall projectsSmall;
    private projectDTO projectDTO;
}
