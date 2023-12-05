package com.belrose.docservice.aop;

import com.belrose.docservice.config.AppConfig;
import com.belrose.docservice.exception.DocsServiceException;
import com.belrose.docservice.model.agre.AgreEncoded;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class CheckingAgreEncodedAspect {
    private final WebClient repoWriteServiceWebClient;
    private final AppConfig appConfig;

    @Autowired
    public CheckingAgreEncodedAspect(@Qualifier("repoWriteServiceWebClient")WebClient repoWriteServiceWebClient, AppConfig appConfig) {
        this.repoWriteServiceWebClient = repoWriteServiceWebClient;
        this.appConfig = appConfig;
    }

    //Contructor got unit test
    public CheckingAgreEncodedAspect(String repoWriteServiceUrl, AppConfig appConfig){
        this.repoWriteServiceWebClient = WebClient.create(repoWriteServiceUrl);
        this.appConfig = appConfig;
    }

    @AfterReturning(value = "execution(* com.belrose.docservice.service.AgreEncodedCheckService.checkAgreEncoded(..))", returning = "elmentsCheckingList")
    public void checkAgreEncodec(List<String> elmentsCheckingList){
        List<AgreEncoded>  response= List.of(Objects.requireNonNull(repoWriteServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(appConfig.getAgreEncodedList())
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AgreEncoded[].class)
                .block()));

        var responseAsString= response.stream()
                .map(agreEncoded ->new  String (Base64.getDecoder().decode(agreEncoded.getDataEncoded()))).findFirst().orElse("");
        if(StringUtils.isEmpty(responseAsString) || !responseAsString.contains("<Name>")){
            throw new DocsServiceException(" invalid request");
        }
        /*if(CollectionUtils.isEmpty(responseAsString) || !responseAsString.containsAll(elmentsCheckingList)){
            throw new DocsServiceException(" invalid request");
        }*/

    }
}
