package com.ostapchuk.technosky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry(proxyTargetClass=true)
@SpringBootApplication
public class TechnoskyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnoskyApplication.class, args);
    }

}
