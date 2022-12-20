package com.example.lab7.events;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named("Subject")
@RequestScoped
public class Subject {
    @Inject
    Event<String> event;

    public void documentAdded(String name) {
        event.fire("Event Triggered - Document " + name + " was submitted!");
    }
}
