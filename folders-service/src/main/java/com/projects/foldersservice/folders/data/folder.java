package com.projects.foldersservice.folders.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "folders")
public class folder {

    @Id
    private String _id;

    private String name;
    private String owner;
    private List<String> projects;

    public folder(String name, String owner, List<String> projects) {
        this.name = name;
        this.owner = owner;
        this.projects = projects;
    }
}
