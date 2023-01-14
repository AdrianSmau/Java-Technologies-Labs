package com.example.lab8;

import com.example.lab8.controllers.JaxRsController;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("resources")
@ApplicationScoped
public class JaxRsApplication extends Application {
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(JaxRsController.class);
    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
}
