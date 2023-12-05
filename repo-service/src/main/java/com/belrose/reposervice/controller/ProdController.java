package com.belrose.reposervice.controller;


import com.belrose.reposervice.model.prod.Prod;
import com.belrose.reposervice.service.ProdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/prod")
public class ProdController {

    private final ProdService prodService;

    public ProdController(ProdService prodService) {
        this.prodService = prodService;
    }

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Prod>> saveProd(@Valid @RequestBody Prod prod){
            return ResponseEntity.ok(prodService.saveProd(prod));
    }

}
