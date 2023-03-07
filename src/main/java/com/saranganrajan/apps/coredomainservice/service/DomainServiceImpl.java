package com.saranganrajan.apps.coredomainservice.service;

import com.google.gson.Gson;
import com.saranganrajan.apps.coredomainservice.builder.CustomerDomainBuilder;
import com.saranganrajan.apps.coredomainservice.builder.PolicyDomainBuilder;
import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import com.saranganrajan.apps.coredomainservice.dto.domain.CustomerDomain;
import com.saranganrajan.apps.coredomainservice.dto.domain.CustomerPoliciesDomain;
import com.saranganrajan.apps.coredomainservice.dto.domain.LinkedPolicy;
import com.saranganrajan.apps.coredomainservice.dto.domain.PolicyDomain;
import com.saranganrajan.apps.coredomainservice.external.publisher.feign.PublisherFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DomainServiceImpl implements DomainService {

    @Autowired
    PolicyDomainBuilder policyDomainBuilder;

    @Autowired
    CustomerDomainBuilder customerDomainBuilder;

    @Autowired
    PublisherFeignClient publisherFeignClient;

    public DomainServiceImpl(PolicyDomainBuilder policyDomainBuilder,
                             CustomerDomainBuilder customerDomainBuilder,
                             PublisherFeignClient publisherFeignClient) {
        this.policyDomainBuilder = policyDomainBuilder;
        this.customerDomainBuilder = customerDomainBuilder;
        this.publisherFeignClient = publisherFeignClient;
    }

    @Override
    public void buildDomainObjects(PolicyAggregate policyAggregate) {
        PolicyDomain policyDomain = policyDomainBuilder.fromPolicyAggregate(policyAggregate);
        List<CustomerDomain> customerDomains = customerDomainBuilder.fromPolicyAggregate(policyAggregate);


        log.info("Policy Domain : {} ", new Gson().toJson(policyDomain));
        log.info("Customer Domains : {} ", new Gson().toJson(customerDomains));
        publishDomainObjects(policyDomain, customerDomains);
    }

    public void publishDomainObjects(PolicyDomain policyDomain, List<CustomerDomain> customerDomains) {
        publisherFeignClient.publishPolicyDomain(policyDomain);
        //Splitting the customer domains into multiple calls
        if(customerDomains.size() > 1) {
          if(customerDomains.get(0).getCustomerNumber().equals(customerDomains.get(1).getCustomerNumber())) {

              List<LinkedPolicy> linkedPolicies = new ArrayList<>();
              linkedPolicies.add(LinkedPolicy.builder()
                      .policy(customerDomains.get(0).getLinkedPolicy().getPolicy())
                      .linkType(customerDomains.get(0).getLinkedPolicy().getLinkType())
                      .build());
              linkedPolicies.add(LinkedPolicy.builder()
                      .policy(customerDomains.get(1).getLinkedPolicy().getPolicy())
                      .linkType(customerDomains.get(1).getLinkedPolicy().getLinkType())
                      .build());
              CustomerPoliciesDomain customerPoliciesDomain = CustomerPoliciesDomain.builder()
                      .customerName(customerDomains.get(0).getCustomerName())
                      .customerTransactionId(customerDomains.get(0).getCustomerTransactionId())
                      .customerNumber(customerDomains.get(0).getCustomerNumber())
                      .dateOfBirth(customerDomains.get(0).getDateOfBirth())
                      .email(customerDomains.get(0).getEmail())
                      .phoneNumber(customerDomains.get(0).getPhoneNumber())
                      .linkedPolicies(linkedPolicies)
                      .build();
              publisherFeignClient.publishCustomerDomain(customerPoliciesDomain);
          } else {
                List<LinkedPolicy> linkedPolicies = new ArrayList<>();
                linkedPolicies.add(LinkedPolicy.builder()
                        .policy(customerDomains.get(0).getLinkedPolicy().getPolicy())
                        .linkType(customerDomains.get(0).getLinkedPolicy().getLinkType())
                        .build());
                CustomerPoliciesDomain customerPoliciesDomain = CustomerPoliciesDomain.builder()
                        .customerName(customerDomains.get(0).getCustomerName())
                        .customerNumber(customerDomains.get(0).getCustomerNumber())
                        .customerTransactionId(customerDomains.get(0).getCustomerTransactionId())
                        .dateOfBirth(customerDomains.get(0).getDateOfBirth())
                        .email(customerDomains.get(0).getEmail())
                        .phoneNumber(customerDomains.get(0).getPhoneNumber())
                        .linkedPolicies(linkedPolicies)
                        .build();
                publisherFeignClient.publishCustomerDomain(customerPoliciesDomain);

                linkedPolicies = new ArrayList<>();
                linkedPolicies.add(LinkedPolicy.builder()
                        .policy(customerDomains.get(1).getLinkedPolicy().getPolicy())
                        .linkType(customerDomains.get(1).getLinkedPolicy().getLinkType())
                        .build());
                customerPoliciesDomain = CustomerPoliciesDomain.builder()
                        .customerName(customerDomains.get(1).getCustomerName())
                        .customerNumber(customerDomains.get(1).getCustomerNumber())
                        .customerTransactionId(customerDomains.get(1).getCustomerTransactionId())
                        .dateOfBirth(customerDomains.get(1).getDateOfBirth())
                        .email(customerDomains.get(1).getEmail())
                        .phoneNumber(customerDomains.get(1).getPhoneNumber())
                        .linkedPolicies(linkedPolicies)
                        .build();
                publisherFeignClient.publishCustomerDomain(customerPoliciesDomain);
          }
        } else if(customerDomains.size() == 1) {
              List<LinkedPolicy> linkedPolicies = new ArrayList<>();
              linkedPolicies.add(LinkedPolicy.builder()
                      .policy(customerDomains.get(0).getLinkedPolicy().getPolicy())
                      .linkType(customerDomains.get(0).getLinkedPolicy().getLinkType())
                      .build());
              CustomerPoliciesDomain customerPoliciesDomain = CustomerPoliciesDomain.builder()
                      .customerName(customerDomains.get(0).getCustomerName())
                      .customerNumber(customerDomains.get(0).getCustomerNumber())
                      .dateOfBirth(customerDomains.get(0).getDateOfBirth())
                      .email(customerDomains.get(0).getEmail())
                      .phoneNumber(customerDomains.get(0).getPhoneNumber())
                      .linkedPolicies(linkedPolicies)
                      .build();
              publisherFeignClient.publishCustomerDomain(customerPoliciesDomain);
        } else {
            log.info("No customer domains to publish");
        }
    }

}
