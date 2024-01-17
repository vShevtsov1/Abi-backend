package com.project.developersservice.feigns;

import com.project.userservice.users.data.user;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service")
public interface UserFeign {

    @GetMapping(value = "/users/feign-get-user")
    user getUserByEmail(@RequestParam(value = "email") String email);

}
