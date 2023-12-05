package com.belrose.agreservice.service.impl;


import com.belrose.agreservice.constants.Uri;
import com.belrose.agreservice.exception.AgreServiceException;
import com.belrose.agreservice.model.ActionDetails;
import com.belrose.agreservice.model.Agre;
import com.belrose.agreservice.model.AgreEncoded;
import com.belrose.agreservice.model.AgreResponse;
import com.belrose.agreservice.service.AgreService;
import com.belrose.agreservice.util.ModelToXmlGenerator;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

import static com.belrose.agreservice.constants.AppConstants.AGRE_SERVICE_CIRCUIT_BREAKER;

@Slf4j
@Service
public class AgreServiceImpl implements AgreService {

    private final WebClient repoServiceWebClient;

    @Autowired
    public AgreServiceImpl(@Qualifier("repoServiceWebClient") WebClient repoServiceOneWebClient) {
        this.repoServiceWebClient = repoServiceOneWebClient;
    }


    public AgreServiceImpl(String repoServiceOneUrl) {
        this.repoServiceWebClient = WebClient.create(repoServiceOneUrl);
    }

    @Override
   @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "saveDefaultAgreAndAgreEncoded")
   @Retry(name = "${spring.application.name}",fallbackMethod = "saveDefaultAgreAndAgreEncoded")
    public AgreResponse saveAgreAndAgreEncoded(Agre agre) {
        log.info("AgreServiceImpl->saveAgreAndAgreEncoded ()");
        ActionDetails actionDetails = ActionDetails.builder()
                .agreTransaction(UUID.randomUUID().toString())
                .creationDate(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")))
                .build();
        agre.setActionDetails(actionDetails);
        var agreResponse = repoServiceWebClient
                .post()
                .uri(uriBuilder -> uriBuilder.path(Uri.URI_SAVE_AGRE).build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(agre))
                .retrieve()
                .bodyToMono(AgreResponse.class)
                .block();
        if (agreResponse==null){
            throw  new  AgreServiceException("Error to save Agre");
        }
        var xml = ModelToXmlGenerator.modelToXml(agre).getBytes();
        var xmlEncoded = Base64.getEncoder().encodeToString(xml);
        var agreEncoded = AgreEncoded.builder().dataEncoded(xmlEncoded).build();
        var agreEncodedRes = saveAgreEncoded(agreEncoded);

        return AgreResponse.builder()
                .agreId(agreResponse.getAgreId())
                .type(agreResponse.getType())
                .name(agreResponse.getName())
                .version(agreResponse.getVersion())
                .addressList(agreResponse.getAddressList())
                .locationList(agreResponse.getLocationList())
                .dataEncoded(agreEncodedRes.getDataEncoded())
                .actionDetails(agreResponse.getActionDetails())
                .build();
    }


    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "saveDefaultAgreEncoded")
    @Retry(name = "${spring.application.name}",fallbackMethod = "saveDefaultAgreEncoded")
    private AgreResponse saveAgreEncoded(AgreEncoded agreEncoded) {
        log.info("AgreServiceImpl->saveAgreEncoded ()");
        agreEncoded.setTimestamp(System.currentTimeMillis()/1000);
        return repoServiceWebClient
                .post()
                .uri(uriBuilder -> uriBuilder.path(Uri.URI_SAVE_AGRE_ENCODED).build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(agreEncoded))
                .retrieve()
                .bodyToMono(AgreResponse.class)
                .block();
    }

    public AgreResponse saveDefaultAgreAndAgreEncoded(Agre agre, Exception ex) {
        log.info("AgreServiceImpl->saveDefaultAgreAndAgreEncoded ()");
        // If you don't want to throw exception, you can build the default response object
      /*  return AgreResponse.builder()
                .agreId("1111111")
                .type("cb_type")
                .name("cb_name")
                .version("cb_version")
                .addressList(null)
                .locationList(null)
                .dataEncoded("cb_data_encoded")
                .actionDetails(null)
                .build();*/
        throw new AgreServiceException("Circuit Breaker Response: Cause {} "+ex);
    }

    public AgreResponse saveDefaultAgreEncoded(AgreEncoded agreEncoded, Exception ex) {
        log.info("AgreServiceImpl->saveDefaultAgreEncoded ()");
        // If you don't want to throw exception, you can build the default response object
      /* return AgreResponse.builder()
                .agreId("1111111")
                .type("cb_type")
                .name("cb_name")
                .version("cb_version")
                .addressList(null)
                .locationList(null)
                .dataEncoded("cb_data_encoded")
                .actionDetails(null)
                .build();*/
        throw new AgreServiceException("Circuit Breaker Response: Cause {}"+ex);
    }
}
