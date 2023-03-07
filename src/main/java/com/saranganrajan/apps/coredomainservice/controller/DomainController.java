package com.saranganrajan.apps.coredomainservice.controller;

import com.google.gson.Gson;
import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import com.saranganrajan.apps.coredomainservice.service.DomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DomainController {

    @Autowired
    DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping(path = "/domain/process", consumes = "application/json")
    public ResponseEntity<PolicyAggregate> processAggregate(@RequestBody PolicyAggregate policyAggregate) {
       domainService.buildDomainObjects(policyAggregate);
        return ResponseEntity.ok(policyAggregate);
    }

}
