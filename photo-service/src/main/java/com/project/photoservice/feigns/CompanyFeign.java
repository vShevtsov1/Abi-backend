package com.project.photoservice.feigns;

import com.project.companyservice.company.data.Company;
import com.project.photoservice.photo.data.photo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service")
public interface CompanyFeign {


    @PostMapping("/company/update-company")
    void updateCompany(@RequestParam("photo_id") String photo, @RequestParam("company_id") String company_id);

}
