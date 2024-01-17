package com.project.userservice.users.data.DTO;

import com.project.userservice.users.data.Socials;
import com.project.userservice.users.data.enums.role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class userRegisterDTO {


    private String user_nickname;
    private String user_email;
    private String user_phoneNumber;
    private Socials user_socials;
    private String user_company;
    private String user_pinCode;
}
