package com.saranganrajan.apps.coredomainservice.dto.domain;

import com.saranganrajan.apps.coredomainservice.dto.Customer;
import com.saranganrajan.apps.coredomainservice.dto.Policy;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class LinkedPolicy {
    private String linkType;
    private Policy policy;
}
