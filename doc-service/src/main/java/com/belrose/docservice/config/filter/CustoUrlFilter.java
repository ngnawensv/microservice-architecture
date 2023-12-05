package com.belrose.docservice.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Map;

//@Component
@Slf4j
@Getter
@Setter
@WebFilter(urlPatterns = {"/docs"},dispatcherTypes = {DispatcherType.REQUEST})
public class CustoUrlFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if(httpServletRequest.getRequestURI().contains("/docs")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        if(StringUtils.isEmpty(httpServletRequest.getHeader("x-request-id"))){
            httpServletResponse.sendError(httpServletResponse.SC_UNAUTHORIZED, "Invalid Request Id");
           return;
        }
        //BearerTokenAuthentication bearerTokenAuthentication = httpServletRequest.getUserPrincipal(); //todo
        var bearerTokenAuthentication = httpServletRequest.getUserPrincipal();
        if(isValidToken(bearerTokenAuthentication)){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Token");
            return;
        }
        //here we map all the headers
        Map<String,String> rqMap = Map.of(
                "x-request-id",httpServletRequest.getHeader("x-request-id")
        );

        servletRequest.setAttribute("REQUEST_MAP",httpServletRequest);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    //here we check the token values
    // todo  use BearerTokenAuthentication instead Principal directly
    //do this &&ObjectUtils.isNotEmpty(bearerTokenAuthentication.getokenAttribute().get(xxxx)
    //xxx should be a attribute inside the token, use jwt.io to see
    private boolean isValidToken(Principal bearerTokenAuthentication) {
        return (ObjectUtils.isEmpty(bearerTokenAuthentication));
    }
}
