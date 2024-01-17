package com.project.userservice.users.data;

import com.project.userservice.users.data.enums.role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class user {

    @Id
    private String _id;

    private String user_nickname;
    private role user_role;
    private String email;
    private String user_phoneNumber;
    private Socials user_socials;
    private String user_photo;
    private String company;
    private String user_pinCode;
    private String user_activeSession;
    private Set<String> user_ips;
    private LocalDateTime ban_till;
    private Boolean activated;

    public user(String user_nickname, role user_role, String email, String user_phoneNumber, Socials user_socials, String user_photo, String user_company, String user_pinCode, Boolean user_activated) {
        this.user_nickname = user_nickname;
        this.user_role = user_role;
        this.email = email;
        this.user_phoneNumber = user_phoneNumber;
        this.user_socials = user_socials;
        this.user_photo = user_photo;
        this.company = user_company;
        this.user_pinCode = user_pinCode;
        this.activated = user_activated;
    }
}
