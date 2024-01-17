package com.project.projectservice.feigns;

import com.project.projectservice.projects.data.DTO.ProjectUploadDTO;
import com.project.projectservice.projects.data.DTO.projectDTO;
import com.project.projectservice.projects.data.projects;
import com.project.projectservice.projects.data.projectsSmall;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "photo-service")
public interface PhotoFeign {

    @PostMapping(value = "/photos/upload-projectsPhoto",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadProjectsPhoto(@RequestPart("file") MultipartFile file, @RequestParam String projectId);
}