package com.project.developersservice.developers.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class developersDTO {

    private String name;
    private String manager_name;
    private String phoneNumber;
    private String website;
    private String whatsApp_url;

}
