package com.project.userservice.users.data.DTO;

import com.project.userservice.users.data.Socials;
import com.project.userservice.users.data.enums.role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDTO {

    private String _id;
    private String user_nickname;
    private role user_role;
    private String email;
    private String user_phoneNumber;
    private Socials user_socials;
    private ObjectId user_photo;
    private String company;
}
