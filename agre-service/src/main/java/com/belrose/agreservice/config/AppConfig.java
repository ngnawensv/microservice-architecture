package com.belrose.agreservice.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class AppConfig {

    @Value("${repo.write.api.base}")
    private String repoServiceBaseUrl;

    @Bean
    @Qualifier("repoServiceWebClient")
    WebClient getRepoServiceWebClient(){
        HttpClient httpClient=HttpClient.create();
        return WebClient.builder()
                .baseUrl(repoServiceBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
