package com.belrose.microsvclistener.service.impl;

import com.belrose.microsvclistener.config.AppConfig;
import com.belrose.microsvclistener.model.TriggerEvent;
import com.belrose.microsvclistener.model.agre.Agre;
import com.belrose.microsvclistener.model.file.SaveFileDetails;
import com.belrose.microsvclistener.repository.AgreRepository;
import com.belrose.microsvclistener.service.AgreService;
import com.belrose.microsvclistener.util.file.SaveFileDetailsBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AgreServiceImpl implements AgreService {

    private final AgreRepository agreRepository;
    private final WebClient docsServiceWebClient;
    private final AppConfig appConfig;

    @Autowired
    public AgreServiceImpl(AgreRepository agreRepository, @Qualifier("docsServiceWebClient") WebClient docsServiceWebClient, AppConfig appConfig) {
        this.agreRepository = agreRepository;
        this.docsServiceWebClient = docsServiceWebClient;
        this.appConfig = appConfig;
    }

    public AgreServiceImpl(AgreRepository agreRepository,String docsServiceUrl, AppConfig appConfig) {
        this.agreRepository = agreRepository;
        this.docsServiceWebClient = WebClient.create(docsServiceUrl);
        this.appConfig = appConfig;
    }

 /*   @Override
    public String docsWithFileToSave(Agre agre,byte[] file) {
        var saveFileDetailsBuilder = SaveFileDetailsBuilder.buildSaveFileDetails(agre);
        var multipartData = SaveFileDetailsBuilder.buildMultipartFile(file,saveFileDetailsBuilder);
        var response=  docsServiceWebClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(appConfig.getSendToDocsUri())
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(multipartData))
                .retrieve()
                .onStatus(HttpStatusCode::isError,clientResponse -> clientResponse.bodyToMono(String.class)
                        .doOnNext(body->log.error("AgreServiceImpl->agreWithFileToSave: Error to send agre body {}",body))
                        .flatMap(body->Mono.error(new RuntimeException(body))))
                .bodyToMono(String.class)
                .onErrorResume(ex->{
                    log.error("AgreServiceImpl->agreWithFileToSave: Exception cause to send {}", ExceptionUtils.getRootCauseMessage(ex));
                    return Mono.just("");
                })
                //.doFinally(signalType -> log.error("AgreServiceImpl->agreWithFileToSave: Error to send agre body {}",signalType))
                .block();
        log.info("AgreServiceImpl->agreWithFileToSave: response {}",response);
        return response;
    }*/

    @Override
    public Mono<Agre> updateAgre(TriggerEvent triggerEvent) {
        return this.agreRepository.updateAgre(triggerEvent).switchIfEmpty(Mono.empty());
    }

}
