package com.example.lab7.decorators;

import com.example.lab7.controllers.BusinessHoursRestrictedSubmission;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalTime;

@Decorator
public abstract class BusinessHoursDecoratorSubmission implements BusinessHoursRestrictedSubmission {
    private final LocalTime startTime = LocalTime.of(8, 0);
    private final LocalTime endTime = LocalTime.of(18, 30);
    @Inject
    @Delegate
    @Any
    private BusinessHoursRestrictedSubmission businessHoursRestrictedSubmission;

    @Override
    public void addDocument() throws IOException {
        LocalTime now = LocalTime.now();
        if (((now.getHour() >= startTime.getHour()) && (now.getHour() <= endTime.getHour()))) {
            if (now.getHour() == startTime.getHour()) {
                if (now.getMinute() >= startTime.getMinute()) {
                    businessHoursRestrictedSubmission.addDocument();
                } else {
                    System.err.println("Operation cannot be done outside the schedule");
                }
            } else {
                if (now.getHour() == endTime.getHour()) {
                    if (now.getMinute() <= endTime.getMinute()) {
                        businessHoursRestrictedSubmission.addDocument();
                    } else {
                        System.err.println("Operation cannot be done outside the schedule");
                    }
                } else {
                    businessHoursRestrictedSubmission.addDocument();
                }
            }
        } else {
            System.err.println("Operation cannot be done outside the schedule");
        }
    }
}