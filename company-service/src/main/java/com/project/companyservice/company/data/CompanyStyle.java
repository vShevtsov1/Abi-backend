package com.project.companyservice.company.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyStyle {
    private String primaryColor;
    private String secondaryColor;

    public static CompanyStyle fromJson(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, CompanyStyle.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}