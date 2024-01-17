package com.project.userservice.users.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userProfileDataDTO {
    private userDTO user_data;
    private Object user_photo;
    private List<userDTO> user_team;
    private Integer company_capacity;
    private List<userDTO> new_users;

}
