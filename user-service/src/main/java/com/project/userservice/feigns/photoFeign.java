package com.project.userservice.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "photo-service")
public interface photoFeign {

    @PostMapping(value = "/photos/update-user-avatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void updateUserAvatar(@RequestParam("email") String email,@RequestPart("avatar") MultipartFile avatar);

    @GetMapping(value = "/photos/get-photo")
    Object getPhotoById(@RequestParam String photo_id);

}
