package org.laboratories.lab2.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.laboratories.lab2.utils.SimpleResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

import static org.laboratories.lab2.listeners.AppListener.currentUser;

@WebFilter(urlPatterns = {"/*"}, filterName = "WrapperFilter")
public class WrapperFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        SimpleResponseWrapper wrapper = new SimpleResponseWrapper((HttpServletResponse) res);
        chain.doFilter(req, wrapper);
        String content = wrapper.toString();
        content = "<p>[PRELUDE] WrapperFilter was here! </p>" + content;
        content += "<p>[CONDA] WrapperFilter was here! </p>";
        content += "<p>[CONDA] Current User is: " + currentUser + "! </p>";
        PrintWriter out = res.getWriter();
        out.write(content);
    }
}
