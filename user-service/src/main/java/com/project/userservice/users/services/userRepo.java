package com.project.userservice.users.services;


import com.project.userservice.users.data.user;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface userRepo extends MongoRepository<user,String >{
    user findByEmail(String user_email);
    List<user> findByCompanyAndActivatedIsTrue(String company);
    List<user> findByCompanyAndActivatedIsFalse(String company);
    Integer countByCompany(String company_id);

}
