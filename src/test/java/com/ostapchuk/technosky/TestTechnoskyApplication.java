package com.ostapchuk.technosky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTechnoskyApplication {

    public static void main(String[] args) {
        SpringApplication.from(TechnoskyApplication::main).with(TestTechnoskyApplication.class).run(args);
    }

}
