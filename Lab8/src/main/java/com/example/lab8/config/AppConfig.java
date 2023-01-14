package com.example.lab8.config;

import com.example.lab8.annotations.RegistrationNumber;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.UUID;

@ApplicationScoped
public class AppConfig {
    @Produces
    @RegistrationNumber
    public String generateRegistrationNumber() {
        return UUID.randomUUID().toString();
    }
}
