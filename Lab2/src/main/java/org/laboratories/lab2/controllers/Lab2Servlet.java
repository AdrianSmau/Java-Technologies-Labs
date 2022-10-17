package org.laboratories.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.laboratories.lab2.services.Lab2Service;

import java.io.IOException;

@WebServlet(name = "lab2Servlet", value = "/my-other-servlet")
public class Lab2Servlet extends HttpServlet {
    private final Lab2Service lab2Service = new Lab2Service();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lab2Service.processDoGet(request, response);
    }
}