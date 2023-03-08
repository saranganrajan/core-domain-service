package com.saranganrajan.apps.coredomainservice.external.publisher.feign;

import com.saranganrajan.apps.coredomainservice.dto.domain.CustomerDomain;
import com.saranganrajan.apps.coredomainservice.dto.domain.CustomerPoliciesDomain;
import com.saranganrajan.apps.coredomainservice.dto.domain.PolicyDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="core-domain-publisher", url="http://20.120.120.231:8083")
public interface PublisherFeignClient {

    @PostMapping(path = "/domain/policy/publish", consumes = "application/json")
    public ResponseEntity publishPolicyDomain(@RequestBody PolicyDomain policyDomain);

    @PostMapping(path = "/domain/customer/publish", consumes = "application/json")
    public ResponseEntity publishCustomerDomain(@RequestBody CustomerPoliciesDomain customerPoliciesDomain);
}
