package com.project.projectservice.projects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.projectservice.projects.data.DTO.floorPlansDTO;
import com.project.projectservice.projects.data.DTO.fullProjectDTO;
import com.project.projectservice.projects.data.DTO.projectDTO;
import com.project.projectservice.projects.data.PaymentPlan;
import com.project.projectservice.projects.data.projectsSmall;
import com.project.projectservice.projects.services.projectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.project.projectservice.projects.data.location;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class projectController {
    @Autowired
    private projectsService projectsService;

    @PostMapping("/uploadProject")
    public ResponseEntity<?> uploadProject(@ModelAttribute projectDTO projectDTO,
                                           @RequestPart("projectCoordinates") String projectCoordinates,
                                           @RequestPart("projectPaymentPlan") String projectPaymentPlan, Authentication authentication) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            projectDTO.setCoordinates(objectMapper.readValue(projectCoordinates, location.class));

            TypeReference<Map<String, PaymentPlan>> mapType = new TypeReference<Map<String, PaymentPlan>>() {
            };
            Map<String, PaymentPlan> paymentPlanMap = objectMapper.readValue(projectPaymentPlan, mapType);
            System.out.println(paymentPlanMap);
            projectDTO.setPaymentPlan(paymentPlanMap);

            Iterator<Map.Entry<String, List<floorPlansDTO>>> iterator = projectDTO.getFloorPlans().entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, List<floorPlansDTO>> entry = iterator.next();
                List<floorPlansDTO> floorPlanList = entry.getValue();

                if (floorPlanList == null || floorPlanList.isEmpty()) {
                    iterator.remove();
                }
            }

            projectsSmall projectsSmall = projectsService.createNewProject(projectDTO,authentication.getPrincipal().toString());
            System.out.println(projectDTO);
            return new ResponseEntity<>( projectsSmall,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/my-team-projects")
    public ResponseEntity<?> getMyTeamProjects(Authentication authentication){
        try {
            return new ResponseEntity<>(projectsService.getMyCompanyProjects(authentication.getPrincipal().toString()),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/project-info")
    public ResponseEntity<?> getFullProjectInfo(@RequestParam String projectId){
        try {
            fullProjectDTO projectDTO = projectsService.getProjectById(projectId);
            return new ResponseEntity<>(projectDTO,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
