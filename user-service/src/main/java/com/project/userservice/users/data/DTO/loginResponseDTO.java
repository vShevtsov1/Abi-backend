package com.project.userservice.users.data.DTO;

import com.project.userservice.users.data.enums.loginStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginResponseDTO {

    private loginStatus loginStatus;
    private String message;
    private String session_id;
    private String authorization_token;
    private userDTO userDTO;
}
