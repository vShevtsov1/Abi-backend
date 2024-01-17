package com.project.projectservice.projects.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPlan {

    private Float totalPrice;
    private List<Stages> stages;
}
