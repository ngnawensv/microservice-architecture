package com.belrose.writereposervice.controller.agre;

import com.belrose.writereposervice.constant.uri.Uri;
import com.belrose.writereposervice.model.agre.Agre;
import com.belrose.writereposervice.model.agre.AgreEncoded;
import com.belrose.writereposervice.model.docs.Docs;
import com.belrose.writereposervice.service.agre.AgreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Uri.AGRE)
@Slf4j
public class AgreController {
    private final AgreService agreService;

    public AgreController(AgreService agreService) {
        this.agreService = agreService;
    }

    @PostMapping(path = Uri.URI_SAVE_AGRE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Agre> saveAgre(@RequestBody Agre agre){
        return agreService.saveAgre(agre);
    }

    @GetMapping(path = Uri.LIST_AGRE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Agre> getAllAgre(){
        var response = agreService.getAllAgre();
        log.info("AgreController->getAllAgre: response {}",response);
        return agreService.getAllAgre();
    }
}
