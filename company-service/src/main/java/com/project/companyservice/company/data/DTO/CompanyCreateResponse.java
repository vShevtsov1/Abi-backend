package com.project.companyservice.company.data.DTO;

import com.project.companyservice.company.data.Company;
import com.project.companyservice.company.data.enums.CompanyCreateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateResponse {

    private CompanyCreateStatus create_status;
    private String message;
    private Company created_company;
}
