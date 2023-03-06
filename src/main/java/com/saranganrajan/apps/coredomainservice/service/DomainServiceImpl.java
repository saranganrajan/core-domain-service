package com.saranganrajan.apps.coredomainservice.service;

import com.google.gson.Gson;
import com.saranganrajan.apps.coredomainservice.builder.CustomerDomainBuilder;
import com.saranganrajan.apps.coredomainservice.builder.PolicyDomainBuilder;
import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import com.saranganrajan.apps.coredomainservice.dto.domain.CustomerDomain;
import com.saranganrajan.apps.coredomainservice.dto.domain.PolicyDomain;
import com.saranganrajan.apps.coredomainservice.external.publisher.feign.PublisherFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        publisherFeignClient.publishCustomerDomain(customerDomains);
    }

}
