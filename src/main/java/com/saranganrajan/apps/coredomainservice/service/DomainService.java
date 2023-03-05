package com.saranganrajan.apps.coredomainservice.service;

import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import org.springframework.stereotype.Service;

@Service
public interface DomainService {
    void buildDomainObjects(PolicyAggregate policyAggregate);
}
