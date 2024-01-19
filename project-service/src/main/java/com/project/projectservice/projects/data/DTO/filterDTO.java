package com.project.projectservice.projects.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class filterDTO {

    private String name;
    private Price price;

    private Size size;

    private List<String> bedrooms;
    private List<String> propertyType;



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Price{
        private Integer priceFrom;
        private Integer priceTo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Size{
        private Integer sizeFrom;
        private Integer sizeTo;
    }

}
