package com.project.userservice.users.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socials {
    private String telegram_url;
    private String whatsapp_url;
    private String instagram_url;
}