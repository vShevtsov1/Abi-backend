package com.project.savedservice.savedProjects.services;

import com.project.savedservice.feigns.UserFeign;
import com.project.savedservice.savedProjects.data.DTO.userSavedProjectsDTO;
import com.project.savedservice.savedProjects.data.savedProjects;
import com.project.userservice.users.data.user;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class savedService {

    @Autowired
    private savedRepo savedRepo;
    @Autowired
    private UserFeign userFeign;

    public savedProjects manageUserSaved(String projectId, String email) {
        user user = userFeign.getUserByEmail(email);
        savedProjects savedProjects = savedRepo.findByUserId(user.get_id());
        if (savedProjects == null) {
            savedProjects = new savedProjects(user.get_id(), Arrays.asList(projectId));
            savedRepo.save(savedProjects);
            return savedProjects;
        } else {
            if (savedProjects.getSavedProjects().contains(projectId)) {
                savedProjects.getSavedProjects().remove(projectId);
            } else {
                savedProjects.getSavedProjects().add(projectId);
            }
            savedRepo.save(savedProjects);
            return savedProjects;
        }
    }

    public savedProjects getUsersSaved(String email){
        user user = userFeign.getUserByEmail(email);
        return savedRepo.findByUserId(user.get_id());
    }

    public userSavedProjectsDTO getUserSaved(String email){
        user user = userFeign.getUserByEmail(email);
        return savedRepo.getUserSavedProjects(user.get_id());

    }
}
