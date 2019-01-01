package com.epam.training.sportsbeatting.configuration.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class SpringSecurityConfigTest {

    @Test
    public void test(){
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }
}