package com.project.companyservice.feigns;

import com.project.companyservice.company.data.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "photo-service")
public interface PhotoFeign {

    @PostMapping(value = "/photos/upload-company-logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void addCompanyLogo(@RequestParam("company_id") String company_id, @RequestPart("file") MultipartFile file);
}