package com.saranganrajan.apps.coredomainservice.dto.domain;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerDomain {
    private String customerTransactionId;
    private String customerNumber;
    private String customerName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private LinkedPolicy linkedPolicy;
}
