package com.example.lab9.decorators;

import com.example.lab9.controllers.BusinessHoursRestrictedRegistration;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.time.LocalTime;

@Decorator
public abstract class BusinessHoursDecoratorRegistration implements BusinessHoursRestrictedRegistration {
    private final LocalTime startTime = LocalTime.of(8, 0);
    private final LocalTime endTime = LocalTime.of(18, 30);
    @Inject
    @Delegate
    @Any
    private BusinessHoursRestrictedRegistration businessHoursRestrictedRegistration;

    @Override
    public String register() {
        LocalTime now = LocalTime.now();
        if (((now.getHour() >= startTime.getHour()) && (now.getHour() <= endTime.getHour()))) {
            if (now.getHour() == startTime.getHour()) {
                if (now.getMinute() >= startTime.getMinute()) {
                    return businessHoursRestrictedRegistration.register();
                } else {
                    System.err.println("Operation cannot be done outside the schedule");
                }
            } else {
                if (now.getHour() == endTime.getHour()) {
                    if (now.getMinute() <= endTime.getMinute()) {
                        return businessHoursRestrictedRegistration.register();
                    } else {
                        System.err.println("Operation cannot be done outside the schedule");
                    }
                } else {
                    businessHoursRestrictedRegistration.register();
                }
            }
        } else {
            System.err.println("Operation cannot be done outside the schedule");
        }
        return "/login.xhtml?faces-redirect=true";
    }
}
