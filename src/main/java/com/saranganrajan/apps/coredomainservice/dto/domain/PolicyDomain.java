package com.saranganrajan.apps.coredomainservice.dto.domain;

import com.saranganrajan.apps.coredomainservice.dto.CustomerPolicy;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PolicyDomain {
    private String policyTransactionId;
    private String policyNumber;
    private String plan;
    private LocalDate policyEffectiveDate;
    private LocalDate policySubmissionDate;
    private double premiumPaid;
    private double premiumDue;
    private String paymentMode;
    private String status;
    private String agentName;
    private List<LinkedCustomer> linkedCustomers;
}
