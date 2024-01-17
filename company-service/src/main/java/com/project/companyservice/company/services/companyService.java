package com.project.companyservice.company.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.companyservice.company.data.*;

import com.project.companyservice.company.data.DTO.*;
import com.project.companyservice.company.data.enums.CompanyCreateStatus;
import com.project.companyservice.feigns.PhotoFeign;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.project.companyservice.company.services.companyRepo;

@Service
public class companyService {


    private final PhotoFeign photoFeign;
    private final companyRepo companyRepo;
    @Autowired
    private ObjectMapper objectMapper;

    public companyService(PhotoFeign photoFeign, companyRepo companyRepo) {
        this.photoFeign = photoFeign;
        this.companyRepo = companyRepo;
    }

    public CompanyCreateResponse createNewCompany(CompanyCreateDTO companyCreate) throws JsonProcessingException {
        CompanyStyle companyStyle = objectMapper.readValue(companyCreate.getCompany_style(), CompanyStyle.class);
        CompanyDetails companyDetails = objectMapper.readValue(companyCreate.getCompany_details(), CompanyDetails.class);

        Company company = new Company(companyCreate.getCompany_name(), null,companyCreate.getEmployeeCount(),companyStyle,companyDetails);
        companyRepo.save(company);
        CompletableFuture.runAsync(() -> {
           photoFeign.addCompanyLogo(company.get_id(),companyCreate.getCompany_logo());
        });
        return new CompanyCreateResponse(CompanyCreateStatus.OK,"new company successfully created",company);
    }
    public void UpdateCompanyLogo(String photo,String company_id){
        Company company  = companyRepo.findById(company_id).get();
        company.setCompany_logo(new ObjectId(photo));
        companyRepo.save(company);
    }

    public List<companyRegisterDTO> getAllCompaniesForRegister(){
        return companyRepo.findAll().stream().map(obj->objectMapper.convertValue(obj, companyRegisterDTO.class)).collect(Collectors.toList());
    }

    public Integer getCompanyCapacity(String companyId){
        return companyRepo.findBy_id(companyId).getEmployeeCount();
    }




}
