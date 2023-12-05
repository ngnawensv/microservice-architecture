package com.belrose.docservice.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
    private CustoUrlFilter custoUrlFilter;

    public FilterConfig(CustoUrlFilter custoUrlFilter) {
        this.custoUrlFilter = custoUrlFilter;
    }

    @Bean
    public FilterRegistrationBean<CustoUrlFilter> selfFilterRegistration(){
        FilterRegistrationBean<CustoUrlFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(custoUrlFilter);
        return filterRegistrationBean;
    }
}
