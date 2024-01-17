package com.projects.foldersservice.folders.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class folderContent {
    private String owner;
    private String name;
    private List<Project> projects;
}
