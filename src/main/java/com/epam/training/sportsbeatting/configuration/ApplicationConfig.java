package com.epam.training.sportsbeatting.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
@PropertySource({"classpath:messages.properties"})
public class ApplicationConfig {

    @Bean
    public Scanner getScanner(){
        return new Scanner(System.in);
    }

}
