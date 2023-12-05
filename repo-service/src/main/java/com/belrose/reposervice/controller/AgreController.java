package com.belrose.reposervice.controller;


import com.belrose.reposervice.model.agre.Agre;
import com.belrose.reposervice.service.AgreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/agre")
public class AgreController {

    private final AgreService agreService;

    public AgreController( AgreService agreService) {
        this.agreService = agreService;
    }

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Agre>> saveAgre(@Valid @RequestBody Agre agre){
            return ResponseEntity.ok(agreService.saveAgre(agre));
    }

}
