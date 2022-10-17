package org.laboratories.lab2.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebFilter(urlPatterns = {"/my-other-servlet"}, filterName = "LoggingTimeFilter")
public class LoggingTimeFilter extends HttpFilter {
    private static final Logger LOGGER = LogManager.getLogger(LoggingTimeFilter.class);

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
        Enumeration<String> params = req.getParameterNames();
        List<String> paramsList = new ArrayList<>();
        while (params.hasMoreElements()) {
            String currentParam = params.nextElement();
            paramsList.add(currentParam + " - " + req.getParameter(currentParam));
        }
        LOGGER.info("HTTP method: {}, IP address: {}, user-agent: {}, client language(s): {}, parameters: {}", req.getMethod(), req.getRemoteAddr(), req.getHeader("User-Agent"), req.getHeader("Accept-Language"), paramsList);
    }
}
