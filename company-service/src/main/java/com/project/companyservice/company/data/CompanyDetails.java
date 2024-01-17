package com.project.companyservice.company.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetails {
    private String company_description;
    private String company_website;
    private String company_location;
    private String company_contact;
    private Socials company_socials;
}