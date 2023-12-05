package com.belrose.docservice.webclient.impl;

import com.belrose.docservice.config.AppConfig;
import com.belrose.docservice.exception.DocsServiceException;
import com.belrose.docservice.exception.ErrorDetails;
import com.belrose.docservice.model.file.SaveFileDetails;
import com.belrose.docservice.model.file.SaveFileDetailsResponse;
import com.belrose.docservice.webclient.FileServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class FileServiceClientImpl implements FileServiceClient {
    private final WebClient repoWriteServiceWebClient;
    private final AppConfig appConfig;

    public FileServiceClientImpl(@Qualifier("repoWriteServiceWebClient")WebClient repoWriteServiceWebClient, AppConfig appConfig) {
        this.repoWriteServiceWebClient = repoWriteServiceWebClient;
        this.appConfig = appConfig;
    }

    @Override
    public SaveFileDetailsResponse saveFile(SaveFileDetails saveFileDetails, MultipartFile multipartFile) {

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file",multipartFile.getResource(),MediaType.APPLICATION_PDF);
        multipartBodyBuilder.part("saveFileDetails",saveFileDetails);

        return repoWriteServiceWebClient
                .post()
                .uri(uriBuilder -> uriBuilder.path(appConfig.getFileUri()).build())
                .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
                .retrieve()
                .bodyToMono(SaveFileDetailsResponse.class)
                .block();
    }

    @Override
    @CircuitBreaker(name = "repo-write-service",fallbackMethod = "getDefaultFileDetails")
    public List<SaveFileDetails> getAllFiles() {
        return List.of(Objects.requireNonNull(repoWriteServiceWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(appConfig.getFileGetUri())
                        .build())
                .retrieve()
                .bodyToMono(SaveFileDetails[].class)
                .block()));
    }

    public List<SaveFileDetails>  getDefaultFileDetails(Exception ex) {
        ErrorDetails errorResponse = ErrorDetails.builder().msg(ex.getMessage()).type(ex.getClass().getName()).code(HttpStatus.INTERNAL_SERVER_ERROR.toString()).additionalInfos(List.of(ex.getCause().toString())).build();
        log.info("FileServiceClientImpl->getAllFiles ()");
        throw new DocsServiceException(errorResponse.toString());
    }

}
