package com.saranganrajan.apps.coredomainservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Customer {
    private String customerNumber;
    private String customerName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
}
