package com.project.developersservice.developers.services;

import com.project.developersservice.developers.data.DTO.developersDTO;
import com.project.developersservice.developers.data.developers;
import com.project.developersservice.feigns.UserFeign;
import com.project.userservice.users.data.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class developersService {

    @Autowired
    private developersRepo developersRepo;

    @Autowired
    private UserFeign userFeign;

    public developers createDeveloper(developersDTO developersDTO,String email){
        user user = userFeign.getUserByEmail(email);
        developers developers = new developers(developersDTO.getName(),developersDTO.getManager_name(),developersDTO.getPhoneNumber(),developersDTO.getWebsite(),developersDTO.getWhatsApp_url(),user.getCompany());
        developersRepo.save(developers);
        return developers;
    }
    public List<developers> getMyCompanyDevelopers(String email){
        user user = userFeign.getUserByEmail(email);
        return developersRepo.findAllByCompanyId(user.getCompany());
    }
}
