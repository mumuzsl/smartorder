package com.cqjtu.smartorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartorderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartorderApplication.class, args);
    }

}
