package com.belrose.prodservice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Value("${repo.api.url}")
    private String repoServiceBaseUrl;

    @Bean
    @Qualifier("repoServiceWebClient")
    public WebClient getRepoServiceWebclient() {
        int size=100*1024*1024;
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs->codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        return WebClient.builder()
                .exchangeStrategies(strategies) //when we retrieve the large data
                .baseUrl(repoServiceBaseUrl)
                //.filter(new ServletBearerExchangeFilterFunction()) for JWT
                .build();
    }

    @Bean
    @Qualifier("repoServiceObjectMapper")
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    }

}
