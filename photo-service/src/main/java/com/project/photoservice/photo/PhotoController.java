package com.project.photoservice.photo;

import com.project.companyservice.company.data.Company;
import com.project.photoservice.photo.data.DTO.projectsResponseDTO;
import com.project.photoservice.photo.data.photo;
import com.project.photoservice.photo.services.PhotoService;
import com.project.projectservice.projects.data.DTO.ProjectUploadDTO;
import com.project.projectservice.projects.data.DTO.projectDTO;
import com.project.projectservice.projects.data.projects;
import com.project.projectservice.projects.data.projectsSmall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) {
        return photoService.uploadPhoto(file);
    }

    @PostMapping(value = "/upload-company-logo")
    public void uploadCompanyLogo(@RequestParam("company_id") String company_id,@RequestPart("file") MultipartFile file) {
        photoService.uploadCompanyLogo(company_id, file);
    }
    @PostMapping(value = "/update-user-avatar")
    public void updateUserAvatar(@RequestParam("email") String email,@RequestPart("avatar") MultipartFile avatar) {
        photoService.uploadUserAvatar(email,avatar);
    }
    @GetMapping("/get-photo")
    public Object getPhotoById(@RequestParam String photo_id){
        return photoService.getPhotoById(photo_id);
    }

    @PostMapping(value = "/upload-projectsPhoto", consumes = "multipart/form-data")
    public String uploadProjectsPhoto(@RequestPart("file") MultipartFile file, @RequestParam String projectId) {
        return  photoService.uploadProjectsData(file, projectId).get_id();
    }



}
