package com.project.developersservice.developers.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "developers")
public class developers {

    @Id
    private String _id;
    private String name;
    private String manager_name;
    private String phoneNumber;
    private String website;
    private String whatsApp_url;
    private String companyId;

    public developers(String name, String manager_name, String phoneNumber, String website, String whatsApp_url, String companyId) {
        this.name = name;
        this.manager_name = manager_name;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.whatsApp_url = whatsApp_url;
        this.companyId = companyId;
    }
}
