package com.project.companyservice.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.companyservice.company.data.Company;
import com.project.companyservice.company.data.CompanyStyle;
import com.project.companyservice.company.data.DTO.CompanyCreateDTO;
import com.project.companyservice.company.data.DTO.CompanyCreateResponse;
import com.project.companyservice.company.data.DTO.companyRegisterDTO;
import com.project.companyservice.company.data.enums.CompanyCreateStatus;
import com.project.companyservice.company.services.companyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private companyService companyService;


    @PostMapping("/update-company")
    public void UpdateCompanyLogo(@RequestParam("photo_id") String photo, @RequestParam("company_id") String company_id){
        companyService.UpdateCompanyLogo(photo,company_id);
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<CompanyCreateResponse> createNewCompany(@ModelAttribute  CompanyCreateDTO companyCreate) {
        try {

            CompanyCreateResponse response = companyService.createNewCompany(companyCreate);
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new CompanyCreateResponse(CompanyCreateStatus.FAILED, "Error creating company", null));
        }
    }

    @GetMapping("/all-companies")
    public ResponseEntity<List<companyRegisterDTO>> getAllCompaniesForRegister() {
        try {
            List<companyRegisterDTO> companies = companyService.getAllCompaniesForRegister();
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/company-capacity")
    public Integer getCompanyCapacity(@RequestParam String company_id){
            return companyService.getCompanyCapacity(company_id);
    }


}
