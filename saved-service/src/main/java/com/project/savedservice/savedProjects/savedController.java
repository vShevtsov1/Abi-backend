package com.project.savedservice.savedProjects;

import com.project.savedservice.savedProjects.services.savedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saved-projects")
public class savedController {

    @Autowired
    private savedService savedService;


    @PostMapping("/update")
    private ResponseEntity<?> manageUserProjects(@RequestParam String projectId, Authentication authentication) {
        try {

            return new ResponseEntity<>(savedService.manageUserSaved(projectId, authentication.getPrincipal().toString()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user-saved")
    private ResponseEntity<?> getAllUserSaved(Authentication authentication) {
        try {
            return new ResponseEntity<>(savedService.getUsersSaved(authentication.getPrincipal().toString()),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/my-projects")
    private ResponseEntity<?> getAllUserSavedProjects(Authentication authentication) {
        try {
            return new ResponseEntity<>(savedService.getUserSaved(authentication.getPrincipal().toString()),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
