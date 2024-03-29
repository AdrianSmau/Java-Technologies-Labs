package org.laboratories.lab2.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.laboratories.lab2.listeners.AppListener.currentUser;
import static org.laboratories.lab2.utils.AppUtils.COMPULSORY_PARAM_NAME;
import static org.laboratories.lab2.utils.AppUtils.HOMEWORK_PARAM_NAME;

public class Lab2Service {

    public void processDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("userName") != null && !request.getParameter("userName").isEmpty()) {
            currentUser = request.getParameter("userName");
        }
        response.setContentType("text/html");

        String word = request.getParameter(COMPULSORY_PARAM_NAME);

        int size = Integer.parseInt(request.getParameter(HOMEWORK_PARAM_NAME));
        if (size > word.length())
            size = word.length();
        Set<String> result = new HashSet<>();
        if (size == 0) {
            for (int i = 1; i <= word.length(); i++) {
                permutation(word, i, result);
            }
        } else {
            permutation(word, size, result);
        }
        result = result.stream().map(String::toUpperCase).collect(Collectors.toSet());
        request.setAttribute("permutationList", result);

        Cookie c = new Cookie("userName", currentUser);
        response.addCookie(c);

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

    private void permutation(String str, int size, Set<String> result) {
        permutation("", str, size, result);
    }

    private void permutation(String prefix, String str, int size, Set<String> result) {
        int n = str.length();
        int currentSize = prefix.length();
        if (n == 0 || currentSize == size) result.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), size, result);
        }
    }
}
