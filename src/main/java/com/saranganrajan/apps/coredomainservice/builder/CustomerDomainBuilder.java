package com.saranganrajan.apps.coredomainservice.builder;

import com.saranganrajan.apps.coredomainservice.constant.EnumLinkType;
import com.saranganrajan.apps.coredomainservice.dto.Customer;
import com.saranganrajan.apps.coredomainservice.dto.CustomerPolicy;
import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import com.saranganrajan.apps.coredomainservice.dto.domain.CustomerDomain;
import com.saranganrajan.apps.coredomainservice.dto.domain.LinkedPolicy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDomainBuilder {

    private CustomerDomainBuilder() {
    }

    public List<CustomerDomain> fromPolicyAggregate(PolicyAggregate policyAggregate) {
        List<CustomerPolicy> customerPolicies = policyAggregate.getCustomerPolicies();
        List<CustomerDomain> customerDomains = new ArrayList<>();
      for(CustomerPolicy customerPolicy : customerPolicies) {
          Customer customer = policyAggregate.getCustomers().stream()
                  .filter(c -> c.getCustomerNumber().equals(customerPolicy.getCustomerNumber()))
                  .findFirst().get();

          CustomerDomain customerDomain = CustomerDomain.builder()
                  .customerName(customer.getCustomerName())
                  .customerNumber(customerPolicy.getCustomerNumber())
                  .dateOfBirth(customer.getDateOfBirth())
                  .email(customer.getEmail())
                  .phoneNumber(customer.getPhoneNumber())
                 .linkedPolicy(LinkedPolicy.builder()
                         .policy(policyAggregate.getPolicy())
                         .linkType(EnumLinkType.valueOf(customerPolicy.getLinkType()).linkType)
                         .build())
                  .build();
          customerDomains.add(customerDomain);
      }
        return customerDomains;
    }
}
