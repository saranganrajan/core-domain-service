package com.saranganrajan.apps.coredomainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class CoreDomainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreDomainServiceApplication.class, args);
	}

}
