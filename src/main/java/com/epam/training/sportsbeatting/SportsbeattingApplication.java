package com.epam.training.sportsbeatting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:ValidationMessages.properties")
public class SportsbeattingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsbeattingApplication.class, args);
    }
}
