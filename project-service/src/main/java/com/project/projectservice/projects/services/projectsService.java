package com.project.projectservice.projects.services;

import com.project.projectservice.feigns.PhotoFeign;
import com.project.projectservice.feigns.UserFeign;
import com.project.projectservice.projects.data.DTO.*;
import com.project.projectservice.projects.data.floorPlans;
import com.project.projectservice.projects.data.projects;
import com.project.projectservice.projects.data.projectsSmall;
import com.project.userservice.users.data.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class projectsService {

    @Autowired
    private projectRepo projectRepo;
    @Autowired
    private projectsSmallRepo projectsSmallRepo;
    @Autowired
    private PhotoFeign photoFeign;
    @Autowired
    private UserFeign userFeign;

    public projectsSmall createNewProject(projectDTO projectDTO,String email) {
        user user = userFeign.getUserByEmail(email);
        projectsSmall newProjects = new projectsSmall(projectDTO.getName(), projectDTO.getCoordinates(), projectDTO.getDeveloper(), projectDTO.getPriceFrom(), projectDTO.getLocation(), projectDTO.getOwnLocation(), projectDTO.getHandover(),null,user.getCompany());
        projectsSmallRepo.save(newProjects);
        projects projects = new projects(newProjects.get_id(), projectDTO.getName(), projectDTO.getCoordinates(), projectDTO.getLocation(), projectDTO.getOwnLocation(), projectDTO.getDescription(), projectDTO.getDeveloper(), projectDTO.getPriceFrom(), projectDTO.getSizeFrom(), projectDTO.getBedrooms(), projectDTO.getAmenities(), projectDTO.getVrUrl(), projectDTO.getUrl3D(), null, null,user.getCompany(), projectDTO.getHandover(), projectDTO.getPropertyType(), projectDTO.getTrakheesi(), null, null, projectDTO.getCompletionStatus(), null, projectDTO.getPaymentPlan());
        projectRepo.save(projects);
        String imageMainFuture = photoFeign.uploadProjectsPhoto(projectDTO.getImageMain(),newProjects.get_id());
        newProjects.setImage(imageMainFuture);
        projects.setImageMain(imageMainFuture);

        String imageExteriorFuture = photoFeign.uploadProjectsPhoto(projectDTO.getImageExterior(),newProjects.get_id());
        projects.setImageExterior(imageExteriorFuture);

        projects.setExteriorPhoto(new ArrayList<>());
        projects.setInteriorPhoto(new ArrayList<>());
        for(MultipartFile file:projectDTO.getExteriorPhoto()){
            String image= photoFeign.uploadProjectsPhoto(file,newProjects.get_id());
            projects.getExteriorPhoto().add(image);
        }
        for(MultipartFile file:projectDTO.getInteriorPhoto()){
            String image= photoFeign.uploadProjectsPhoto(file,newProjects.get_id());
            projects.getInteriorPhoto().add(image);
        }
        Map<String, List<floorPlans>> floorPlans = new HashMap<>();

        for (Map.Entry<String, List<floorPlansDTO>> entry : projectDTO.getFloorPlans().entrySet()) {
            List<floorPlansDTO> value = entry.getValue();
            List<floorPlans> updatedFloorPlans = new ArrayList<>();
            for (floorPlansDTO floorPlan : value) {
                String image= photoFeign.uploadProjectsPhoto(floorPlan.getImageUrl(),newProjects.get_id());
                floorPlans updatedFloorPlan = new floorPlans(floorPlan.getPrice(),floorPlan.getSize(),image);
                updatedFloorPlans.add(updatedFloorPlan);

            }
            floorPlans.put(entry.getKey(), updatedFloorPlans);

        }
        projects.setFloorPlans(floorPlans);
        projectsSmallRepo.save(newProjects);
        projectRepo.save(projects);
        return newProjects;
    }

    public List<projectDTOImages> getMyCompanyProjects(String email){
        user user = userFeign.getUserByEmail(email);
        return projectsSmallRepo.getAllProjects(user.getCompany());
    }

    public fullProjectDTO getProjectById(String id){
        return projectRepo.customAggregation(id);
    }


}
