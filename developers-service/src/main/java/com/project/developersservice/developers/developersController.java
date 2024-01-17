package com.project.developersservice.developers;

import com.project.developersservice.developers.data.DTO.developersDTO;
import com.project.developersservice.developers.data.developers;
import com.project.developersservice.developers.services.developersService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developer")
public class developersController {

    @Autowired
    private developersService developersService;

    @PostMapping("/create-new-developer")
    public ResponseEntity<?> createNewDeveloper(@RequestBody developersDTO developersDTO, Authentication authentication){
        try {
            developers developers = developersService.createDeveloper(developersDTO,authentication.getPrincipal().toString());
            return new ResponseEntity<>(developers,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-developers")
    public ResponseEntity<?> getMyTeamDevelopers( Authentication authentication){
        try {
            List<developers> developers = developersService.getMyCompanyDevelopers(authentication.getPrincipal().toString());
            return new ResponseEntity<>(developers,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
