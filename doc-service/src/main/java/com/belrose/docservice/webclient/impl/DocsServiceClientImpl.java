package com.belrose.docservice.webclient.impl;

import com.belrose.docservice.config.AppConfig;
import com.belrose.docservice.exception.ErrorDetails;
import com.belrose.docservice.exception.RepoWriteServiceException;
import com.belrose.docservice.model.docs.Docs;
import com.belrose.docservice.webclient.DocsServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.belrose.docservice.exception.ErrorCode.*;

@Service
@Slf4j
public class DocsServiceClientImpl implements DocsServiceClient {
    private final WebClient repoWriteServiceWebClient;
    private final AppConfig appConfig;

    @Autowired
    public DocsServiceClientImpl(@Qualifier("repoWriteServiceWebClient") WebClient repoWriteServiceWebClient, AppConfig appConfig) {
        this.repoWriteServiceWebClient = repoWriteServiceWebClient;
        this.appConfig = appConfig;
    }

    //Have to be use for the unit test
    public DocsServiceClientImpl(String repoWriteServiceBaseUrl, AppConfig appConfig) {
        this.repoWriteServiceWebClient = WebClient.create(repoWriteServiceBaseUrl);
        this.appConfig = appConfig;
    }

    @Override
    public Docs sendDocsToRepoWriteV1(Docs docs) {
        return repoWriteServiceWebClient
                .post()
                .uri(uriBuilder -> uriBuilder.path(appConfig.getSendToRepo()).build())
                .body(BodyInserters.fromValue(docs))
                .headers(httpHeaders -> httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON)))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, this::throwRepoWriteServiceExceptionV1)
                .onStatus(HttpStatusCode::is5xxServerError,this::throwRepoWriteServiceExceptionV1)
                .bodyToMono(Docs.class)
                .block();
    }

    private Mono<RepoWriteServiceException> throwRepoWriteServiceExceptionV1(ClientResponse clientResponse){
        log.error("Inside throwRepoWriteServiceExceptionV1 Exception: {}",clientResponse);
        return switch (clientResponse.statusCode().value()){
            case 400->Mono.error(new RepoWriteServiceException(getDetails(REPO_WRITE_BAD_REQUEST)));
            case 404,401->Mono.empty();
            default -> Mono.error(new RepoWriteServiceException(getDetails(REPO_WRITE_INTERNAL_SERVER_ERROR)));
        };
    }

    @Override
    public Docs sendDocsToRepoWriteV2(Docs docs) {
        return repoWriteServiceWebClient
                .post()
                .uri(appConfig.getSendToRepo())
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::throwRepoWriteServiceExceptionV2)
                 .bodyToMono(Docs.class)
                .block();
    }

    private Mono<RepoWriteServiceException> throwRepoWriteServiceExceptionV2(ClientResponse clientResponse){
        log.error("Inside throwRepoWriteServiceExceptionV2 Exception: {}",clientResponse);
        return clientResponse.bodyToMono(String.class)
                .flatMap(body->{
                    log.error("Inside throwRepoWriteServiceExceptionV2 Exception: {}",body);
                    return switch (clientResponse.statusCode().value()){
                        case 400->Mono.error(new RepoWriteServiceException(clientResponse.statusCode().toString(),
                                ErrorDetails.builder().code(getDetails(REPO_WRITE_BAD_REQUEST).getCode())
                                .type(getDetails(REPO_WRITE_BAD_REQUEST).getType())
                                .msg(getDetails(REPO_WRITE_BAD_REQUEST).getMsg())
                                .additionalInfos(List.of(body))
                                .build()));
                        case 404,401->Mono.empty();
                        default -> Mono.error(new RepoWriteServiceException(clientResponse.statusCode().toString(),
                                ErrorDetails.builder().code(getDetails(REPO_WRITE_INTERNAL_SERVER_ERROR).getCode())
                                .type(getDetails(REPO_WRITE_INTERNAL_SERVER_ERROR).getType())
                                .msg(getDetails(REPO_WRITE_INTERNAL_SERVER_ERROR).getMsg())
                                .additionalInfos(List.of(body))
                                .build()));
                    };
                }
        );
    }


/*
    @Override
    public Docs sendDocsToRepoWriteV3(Docs docs) {
        return repoWriteServiceWebClient
                .post()
                .uri(appConfig.getSendToRepo())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.just(new RepoWriteServiceException(clientResponse)))
                .onStatus(HttpStatusCode::is5xxServerError,clientResponse ->Mono.just(new RepoWriteServiceException(clientResponse)))
                .bodyToMono(Docs.class)
                .block();
    }*/
}
