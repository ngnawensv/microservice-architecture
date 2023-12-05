package com.belrose.writereposervice.controller.agre;

import com.belrose.writereposervice.constant.uri.Uri;
import com.belrose.writereposervice.model.agre.Agre;
import com.belrose.writereposervice.model.agre.AgreEncoded;
import com.belrose.writereposervice.model.file.SaveFileDetails;
import com.belrose.writereposervice.service.agre.AgreEncodedService;
import com.belrose.writereposervice.service.agre.AgreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Uri.AGRE)
@Slf4j
public class AgreEncodedController {
    private final AgreEncodedService agreEncodedService;

    public AgreEncodedController(AgreEncodedService agreEncodedService) {
        this.agreEncodedService = agreEncodedService;
    }

    @PostMapping(path = Uri.URI_SAVE_AGRE_ENCODED, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AgreEncoded> saveAgreAndAgreEncoded(@RequestBody AgreEncoded agreEncoded){
        return agreEncodedService.saveAgreEncoded(agreEncoded);
    }
    @GetMapping(path = Uri.LIST_AGRE_ENCODED, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AgreEncoded> getAllAgreEncoded(){
        var response = agreEncodedService.getAllAgreEncoded();
        log.info("Controller->getAllAgreEncoded: response {}",response);
        return agreEncodedService.getAllAgreEncoded();
    }
}
