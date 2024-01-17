package com.project.userservice.users;

import com.project.userservice.users.data.DTO.*;
import com.project.userservice.users.data.enums.loginStatus;
import com.project.userservice.users.data.enums.registerStatus;
import com.project.userservice.users.data.user;
import com.project.userservice.users.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("/new-user")
    public ResponseEntity<registerResponseDTO> registerNewUser(@RequestBody userRegisterDTO userRegisterDTO) {
        try {
            registerResponseDTO response = userService.registerNewUser(userRegisterDTO);
            if (response.getStatus() == registerStatus.SUCCESSFULLY_REGISTERED) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else if (response.getStatus() == registerStatus.USER_EXISTS) {
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login-user")
    public ResponseEntity<loginResponseDTO> loginUser(@RequestBody loginDTO loginDTO) {
        try {
            loginResponseDTO response = userService.loginUser(loginDTO);
            if (response.getLoginStatus() == loginStatus.SUCCESS) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else if (response.getLoginStatus() == loginStatus.FAILURE || response.getLoginStatus() == loginStatus.ACCOUNT_DISABLED || response.getLoginStatus() == loginStatus.LOCKED) {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/update-avatar", consumes = "multipart/form-data")
    public ResponseEntity<Object> updateUserAvatar(@RequestPart("avatar") MultipartFile avatar, Authentication authentication) {
        try {
            userService.updateUserAvatar(authentication.getPrincipal().toString(), avatar);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/set-avatar")
    public void setUserAvatar(@RequestParam String avatarId, @RequestParam  String email) {
        userService.setUserAvatar(avatarId,email);

    }
    @GetMapping(value = "/get-my-data")
    public ResponseEntity<userProfileDataDTO> getUserProfileData(Authentication authentication){
        try {
                userProfileDataDTO userProfileDataDTO = userService.getUserProfileData(authentication.getPrincipal().toString());
                return new ResponseEntity<>(userProfileDataDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/process")
    public ResponseEntity<Object> processNewRegistration(@RequestParam String user_id, @RequestParam String action,Authentication authentication) {
        try {
            userService.processNewRegistration(user_id, action,authentication.getPrincipal().toString());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid action. Supported actions are 'approve' or 'deny'.", HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found with ID: " + user_id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam String user_id){
        try {
            userService.deleteUser(user_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/feign-get-user")
    public ResponseEntity<user> getUserByEmail(@RequestParam(value = "email") String email){
        try {
           ;
            return new ResponseEntity<>( userService.getUserByEmail(email),HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
