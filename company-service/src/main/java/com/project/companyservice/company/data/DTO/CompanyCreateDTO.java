package com.project.companyservice.company.data.DTO;

import com.project.companyservice.company.data.CompanyDetails;
import com.project.companyservice.company.data.CompanyStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDTO {
    private String company_name;

    private MultipartFile company_logo;

    private Integer employeeCount;

    private String  company_style;

    private String company_details;
}
