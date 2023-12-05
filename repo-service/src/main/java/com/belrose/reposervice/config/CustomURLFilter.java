package com.belrose.reposervice.config;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.logging.LogRecord;

public class CustomURLFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // Http
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
