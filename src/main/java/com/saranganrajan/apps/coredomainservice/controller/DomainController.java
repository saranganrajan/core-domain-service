package com.saranganrajan.apps.coredomainservice.controller;

import com.google.gson.Gson;
import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DomainController {
    @PostMapping(path = "/domain/process", consumes = "application/json")
    public ResponseEntity<PolicyAggregate> processAggregate(@RequestBody PolicyAggregate policyAggregate) {
       log.info("policy Object: {}", new Gson().toJson(policyAggregate));
        return ResponseEntity.ok(policyAggregate);
    }

}
