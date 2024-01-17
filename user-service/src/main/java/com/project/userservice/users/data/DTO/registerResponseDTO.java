package com.project.userservice.users.data.DTO;

import com.project.userservice.users.data.enums.registerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class registerResponseDTO {

    private registerStatus status;
    private String message;
    private userDTO registered_user;
}
