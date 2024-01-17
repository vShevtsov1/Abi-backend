package com.project.developersservice.developers.services;

import com.project.developersservice.developers.data.developers;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface developersRepo extends MongoRepository<developers,String> {

    List<developers> findAllByCompanyId(String companyId);
}
