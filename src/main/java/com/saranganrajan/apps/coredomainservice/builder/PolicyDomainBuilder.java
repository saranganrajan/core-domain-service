package com.saranganrajan.apps.coredomainservice.builder;

import com.saranganrajan.apps.coredomainservice.constant.EnumLinkType;
import com.saranganrajan.apps.coredomainservice.dto.PolicyAggregate;
import com.saranganrajan.apps.coredomainservice.dto.domain.LinkedCustomer;
import com.saranganrajan.apps.coredomainservice.dto.domain.PolicyDomain;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PolicyDomainBuilder {

    private PolicyDomainBuilder() {
    }

    public PolicyDomain fromPolicyAggregate(PolicyAggregate policyAggregate) {
        if(policyAggregate.getCustomers().size() > 1) {
            return PolicyDomain.builder()
                    .agentName(policyAggregate.getPolicy().getAgentName())
                    .paymentMode(policyAggregate.getPolicy().getPaymentMode())
                    .plan(policyAggregate.getPolicy().getPlan())
                    .policyEffectiveDate(policyAggregate.getPolicy().getPolicyEffectiveDate())
                    .policyNumber(policyAggregate.getPolicy().getPolicyNumber())
                    .policySubmissionDate(policyAggregate.getPolicy().getPolicySubmissionDate())
                    .premiumDue(policyAggregate.getPolicy().getPremiumDue())
                    .premiumPaid(policyAggregate.getPolicy().getPremiumPaid())
                    .status(policyAggregate.getPolicy().getStatus())
                    .linkedCustomers(policyAggregate.getCustomerPolicies().stream()
                            .map(c -> LinkedCustomer.builder()
                            .customer(policyAggregate.getCustomers().stream()
                                    .filter(d -> d.getCustomerNumber().equals(c.getCustomerNumber()))
                                    .findFirst().get())
                                    .linkType(EnumLinkType.valueOf(c.getLinkType()).linkType)
                            .build()).collect(Collectors.toList())).build();
        }
        return null;
    }

}
