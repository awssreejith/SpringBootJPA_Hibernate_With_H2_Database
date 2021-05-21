package com.JPAH2Combination;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Jpah2CombinationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jpah2CombinationApplication.class, args);

    }

}
