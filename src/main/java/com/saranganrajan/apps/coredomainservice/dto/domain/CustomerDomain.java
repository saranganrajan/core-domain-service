package com.saranganrajan.apps.coredomainservice.dto.domain;

import com.saranganrajan.apps.coredomainservice.dto.CustomerPolicy;
import com.saranganrajan.apps.coredomainservice.dto.Policy;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerDomain {
    private String customerNumber;
    private String customerName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private LinkedPolicy linkedPolicy;
}
