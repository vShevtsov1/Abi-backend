package com.project.companyservice.company.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Company")
public class Company {

    @Id
    private String _id;

    private String company_name;

    private ObjectId company_logo;

    private Integer employeeCount;

    private CompanyStyle company_style;

    private CompanyDetails company_details;


    public Company(String company_name, ObjectId company_logo, Integer employeeCount, CompanyStyle company_style, CompanyDetails company_details) {
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.employeeCount = employeeCount;
        this.company_style = company_style;
        this.company_details = company_details;
    }
}