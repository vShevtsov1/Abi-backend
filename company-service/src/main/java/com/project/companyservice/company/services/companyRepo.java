package com.project.companyservice.company.services;

import com.project.companyservice.company.data.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface companyRepo extends MongoRepository<Company,String> {

    Company findBy_id(String id);
}
