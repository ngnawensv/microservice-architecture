package com.belrose.reposervice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@Component
public class AppConfig {
    public FilterRegistrationBean<CustomURLFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<CustomURLFilter> registrationBean = new FilterRegistrationBean<>();
        CustomURLFilter customURLFilter=new CustomURLFilter();

        registrationBean.setFilter(customURLFilter);
        return registrationBean;
    }
}
