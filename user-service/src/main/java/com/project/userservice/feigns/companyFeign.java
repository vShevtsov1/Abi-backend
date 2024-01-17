package com.project.userservice.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "company-service")
public interface companyFeign {
    @GetMapping("/company/company-capacity")
    Integer getCompanyCapacity(@RequestParam String company_id);
}
