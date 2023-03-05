package com.saranganrajan.apps.coredomainservice.dto.domain;

import com.saranganrajan.apps.coredomainservice.dto.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
public class LinkedCustomer {
    private String linkType;
    private Customer customer;
}
