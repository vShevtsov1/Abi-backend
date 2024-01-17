package com.project.projectservice.projects.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stages {

    private String name;
    private Integer percent;
    private Float price;
}
