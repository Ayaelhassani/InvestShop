package com.investshop.investshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.investshop")
@EnableJpaRepositories(basePackages = "com.investshop.repository")
@EntityScan(basePackages = "com.investshop.entity")
public class InvestshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestshopApplication.class, args);
    }
}