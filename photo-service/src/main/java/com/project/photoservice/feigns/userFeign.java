package com.project.photoservice.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface userFeign {

    @PostMapping("/users/set-avatar")
    void setUserAvatar(@RequestParam String avatarId, @RequestParam  String email);
}
