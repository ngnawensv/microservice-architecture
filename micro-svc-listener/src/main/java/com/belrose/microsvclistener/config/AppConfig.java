package com.belrose.microsvclistener.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


@Configuration
@Getter
@Setter
public class AppConfig {

  @Value("${repo.write.api.base}")
  private String baseUrl;

  @Value("${docs.api.base}")
  private String docsBaseUrl;
  @Value("${docs.api.uri.sendToDocs}")
  private String sendToDocsUri;

  @Bean
  @Qualifier("docsServiceWebClient")
  public WebClient geDocsServiceWebclient() {
    ExchangeStrategies strategies = getExchangeStrategies();
    return WebClient.builder()
            .exchangeStrategies(strategies) //when we retrieve the large data
            .baseUrl(this.getDocsBaseUrl())
            //.filter(new ServletBearerExchangeFilterFunction()) //for JWT
            .build();
  }

  @Bean
  @Qualifier("microServiceObjectMapper")
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
