package org.laboratories.lab2.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener()
public class AppListener implements ServletContextListener {
    public static String currentUser;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        currentUser = sce.getServletContext().getInitParameter("currentUserName");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        currentUser = null;
    }
}
