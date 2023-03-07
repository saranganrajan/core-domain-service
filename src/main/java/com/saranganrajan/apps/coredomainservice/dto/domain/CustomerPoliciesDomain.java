package com.saranganrajan.apps.coredomainservice.dto.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerPoliciesDomain {
    private String customerNumber;
    private String customerName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private List<LinkedPolicy> linkedPolicies;
}
