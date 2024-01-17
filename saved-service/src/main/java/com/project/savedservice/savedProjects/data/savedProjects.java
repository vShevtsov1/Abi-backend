package com.project.savedservice.savedProjects.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "savedProjects")
public class savedProjects {

    private String _id;
    private String userId;
    private List<String> savedProjects;

    public savedProjects(String userId, List<String> savedProjects) {
        this.userId = userId;
        this.savedProjects = savedProjects;
    }
}
