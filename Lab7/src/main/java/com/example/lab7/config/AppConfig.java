package com.example.lab7.config;

import com.example.lab7.annotations.RegistrationNumber;

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
