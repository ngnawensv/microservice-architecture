package com.belrose.docservice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Getter
@Setter
public class AppConfig {
    @Value("${repo.write.api.base}")
    private String repoWriteBaseUrl;
    @Value("${repo.write.api.uri.sendToRepoUri}")
    private String sendToRepo;
    @Value("${repo.write.api.uri.fileUri}")
    private String fileUri;
    @Value("${repo.write.api.uri.fileGetUri}")
    private String fileGetUri;

    @Value("${agre.api.base}")
    private String agreServiceBaseUrl;
    @Value("${agre.api.uri.agreEncodedList}")
    private String agreEncodedList;

    @Bean
    @Qualifier("repoWriteServiceWebClient")
    public WebClient getRepoWriteServiceWebclient() {
        ExchangeStrategies strategies = getExchangeStrategies();
        return WebClient.builder()
                .exchangeStrategies(strategies) //when we retrieve the large data
                .baseUrl(this.getRepoWriteBaseUrl())
                //.filter(new ServletBearerExchangeFilterFunction()) //for JWT
                .build();
    }

    @Bean
    @Qualifier("agreServiceWebClient")
    public WebClient getAgreServiceServiceWebclient() {
        return WebClient.builder()
                .baseUrl(this.getRepoWriteBaseUrl())
                .build();
    }

    @Bean
    @Qualifier("repoServiceObjectMapper")
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    }

    private static ExchangeStrategies getExchangeStrategies() {
        int size=100*1024*1024;
        return ExchangeStrategies.builder()
                .codecs(codecs->codecs.defaultCodecs().maxInMemorySize(size))
                .build();
    }

}
