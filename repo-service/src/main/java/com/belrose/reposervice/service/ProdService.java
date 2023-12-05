package com.belrose.reposervice.service;


import com.belrose.reposervice.model.prod.Prod;
import reactor.core.publisher.Mono;

public interface ProdService {
    Mono<Prod> saveProd(Prod prod);
}
