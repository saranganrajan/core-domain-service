package com.saranganrajan.apps.coredomainservice.service;

import com.google.gson.Gson;
import com.saranganrajan.apps.coredomainservice.builder.CustomerDomainBuilder;
import com.saranganrajan.apps.coredomainservice.builder.PolicyDomainBuilder;
import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import com.saranganrajan.apps.coredomainservice.dto.domain.CustomerDomain;
import com.saranganrajan.apps.coredomainservice.dto.domain.PolicyDomain;
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

    public DomainServiceImpl(PolicyDomainBuilder policyDomainBuilder, CustomerDomainBuilder customerDomainBuilder) {
        this.policyDomainBuilder = policyDomainBuilder;
        this.customerDomainBuilder = customerDomainBuilder;
    }

    @Override
    public void buildDomainObjects(PolicyAggregate policyAggregate) {
        PolicyDomain policyDomain = policyDomainBuilder.fromPolicyAggregate(policyAggregate);
        List<CustomerDomain> customerDomains = customerDomainBuilder.fromPolicyAggregate(policyAggregate);
        log.info("Policy Domain : {} ", new Gson().toJson(policyDomain));
        log.info("Customer Domains : {} ", new Gson().toJson(customerDomains));
    }
}
