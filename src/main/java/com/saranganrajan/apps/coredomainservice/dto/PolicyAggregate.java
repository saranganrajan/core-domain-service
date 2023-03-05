package com.saranganrajan.apps.coredomainservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PolicyAggregate {
    Policy policy;
    List<Customer> customers;
    List<CustomerPolicy> customerPolicies;
}
