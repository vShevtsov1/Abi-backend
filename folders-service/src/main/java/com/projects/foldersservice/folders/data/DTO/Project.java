package com.projects.foldersservice.folders.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String _id;
    private String name;
    private Coordinates coordinates;
    private String developer;
    private int price;
    private String location;
    private String ownLocation;
    private String handover;
    private Image image;
    private String company;
}
